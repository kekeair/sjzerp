package com.qxh.action.purchaseSumm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.service.DemandSummService;
import com.qxh.service.PurchaseSummService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * 
 * @Description:[分类汇总初始化页面]
 * 
 * @author:kekeair
 * 
 * @time:2017年3月28日 下午4:16:56
 * 
 */
public class selectBigKindInitAction extends MainAction implements Controller {
	private PurchaseSummService purchaseSummService;

	public void setPurchaseSummService(PurchaseSummService purchaseSummService) {
		this.purchaseSummService = purchaseSummService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String customerIds = req.getParameter("customerIds");
		String goodsNm = req.getParameter("goodsNm");
		if (StringUtils.isEmpty(customerIds)) {
			customerIds = "-1";
		}
		if (StringUtils.isEmpty(goodsNm)) {
			goodsNm = "";
		}
		ModelAndView mav = new ModelAndView();
		User user = (User) SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user, CodeConstant.NAV_504, CodeConstant.NAV_5);
		Result result = purchaseSummService.excelOutBigKind(goodsNm, stime, etime, user.getStructId(), customerIds);
		mav.addObject("userNm", user.getUserNm());
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("inoutSumm/selectBigKind");
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(), result.getData());
	}

}
