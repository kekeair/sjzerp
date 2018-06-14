package com.qxh.action.purchaseSumm;

import java.util.List;

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
 * @Description:[按客户查看汇总]
 * 
 * @author:kekeair
 * 
 * @time:2017年4月21日 上午8:48:42
 * 
 */
public class selectCustomerSummInitAction extends MainAction implements Controller {
	private PurchaseSummService purchaseSummService;

	public void setPurchaseSummService(PurchaseSummService purchaseSummService) {
		this.purchaseSummService = purchaseSummService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String customerIds = req.getParameter("customerIds");
		ModelAndView mav = new ModelAndView();
		User user = (User) SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user, CodeConstant.NAV_504, CodeConstant.NAV_5);
		// 通过Id获取餐饮中心名称
		List<String> customerNmList = purchaseSummService.getCustomerNmByCustomerIds(customerIds);
		String customNm = "";
		if (customerNmList != null && customerNmList.size() > 0) {
			for (int i = 0; i < customerNmList.size(); i++) {
				customNm = customNm + " " + customerNmList.get(i);
			}
		}
		mav.addObject("userNm", user.getUserNm());
		mav.addObject("roleId", user.getRoleId());
		mav.addObject("customNm", customNm);
		mav.setViewName("inoutSumm/selectCustomerSumm");
		return CheckUtil.returnResult(mav, CodeConstant.CODE1000, "", "");
	}
}
