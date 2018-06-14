package com.qxh.action.demand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.DemandService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class SaveDemandDAction extends MainAction implements Controller {
	private DemandService demandService;
	
	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String billId = req.getParameter("billId");
		String newList = req.getParameter("newList");
		String editList = req.getParameter("editList");
		String delAtNo = req.getParameter("delAtNo");
		String headAtNo = req.getParameter("headAtNo");
		String customerId = req.getParameter("customerId");
		String teamId = req.getParameter("teamId");
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result = demandService.saveDemandD(billId,newList,editList,delAtNo,
				headAtNo,customerId,teamId,user.getStructId(),user.getAtNo());
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}