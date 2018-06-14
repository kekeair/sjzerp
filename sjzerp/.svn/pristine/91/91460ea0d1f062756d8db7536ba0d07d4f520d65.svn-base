package com.qxh.action.purchaseSumm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.service.PurchaseSummService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * 
 * @Description:[获取分大类汇总 毛利率]
 * 
 * @author:kekeair
 * 
 * @time:2017年3月31日 上午8:19:50
 * 
 */
public class getSelectBigKindForProfitAction extends MainAction implements Controller {
	private PurchaseSummService purchaseSummService;

	public void setPurchaseSummService(PurchaseSummService purchaseSummService) {
		this.purchaseSummService = purchaseSummService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String goodsNm = req.getParameter("goodsNm");
		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String customerIds = req.getParameter("customerIds");
		User user = (User) SessionUtil.getSession().getAttribute("user");
		Result result = purchaseSummService.getSelectBigKindForProfit(goodsNm, stime, etime, user.getStructId(), customerIds);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(), result.getData());
	}

}
