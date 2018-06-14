package com.qxh.action.purchaseTotalD;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.PurchaseTotalDService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class GetPurchaseTotalDAction extends MainAction implements Controller {
	
	private PurchaseTotalDService purchaseTotalDService;
	
	public void setPurchaseTotalDService(PurchaseTotalDService purchaseTotalDService) {
		this.purchaseTotalDService = purchaseTotalDService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String stime=req.getParameter("stime");
		String etime=req.getParameter("etime");
		String supplierId=req.getParameter("supplierId");
		String customerId = req.getParameter("g_customId");
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result = purchaseTotalDService.getPurchaseTotalD(stime,etime,
				user.getStructId(),supplierId,customerId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}