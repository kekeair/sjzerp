package com.qxh.action.suppliManage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.SuppliManageService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class GetSupplierListAction extends MainAction implements Controller {
	Logger log = Logger.getLogger(this.getClass());
	private SuppliManageService suppliManageService;
	
	public void setSuppliManageService(SuppliManageService suppliManageService) {
		this.suppliManageService = suppliManageService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result = suppliManageService.getSupplierList(user.getStructId());
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}