package com.qxh.action.tuihuo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.service.TuihuoService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class ViewTuihuoDAction extends MainAction implements Controller {
	private TuihuoService tuihuoService;
	
	public void setTuihuoService(TuihuoService tuihuoService) {
		this.tuihuoService = tuihuoService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String billId = req.getParameter("billId");
		Result result = tuihuoService.viewTuihuoD(billId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}