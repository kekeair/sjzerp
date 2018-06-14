package com.qxh.action.tuihuo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.TuihuoService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class GetTuihuoListHAction extends MainAction implements Controller {
	private TuihuoService tuihuoService;
	
	public void setTuihuoService(TuihuoService tuihuoService) {
		this.tuihuoService = tuihuoService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String state = req.getParameter("state");
		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String page = req.getParameter("page");
		String supplierId = req.getParameter("supplierId");
		if(StringUtils.isEmpty(page))
			page="1";
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result = tuihuoService.getTuihuoListH(state,stime,etime,
				page,user.getStructId(),user.getRoleId(),supplierId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}