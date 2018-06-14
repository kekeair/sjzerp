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

 * @Description:[为统计图表按天查看多个个客户的物料数量]

 * @author:kekeair

 * @time:2017年4月21日 上午10:53:36

 */
public class getOrderNumByCustomersInDayAction extends MainAction implements Controller {
	private PurchaseSummService purchaseSummService;

	public void setPurchaseSummService(PurchaseSummService purchaseSummService) {
		this.purchaseSummService = purchaseSummService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String customerIds = req.getParameter("customerIds");
		String kindCode = req.getParameter("kindCode");
		User user = (User) SessionUtil.getSession().getAttribute("user");
		Result result = purchaseSummService.getOrderNumByCustomersInDay(stime, etime, user.getStructId(), customerIds, kindCode);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(), result.getData());
	}

}
