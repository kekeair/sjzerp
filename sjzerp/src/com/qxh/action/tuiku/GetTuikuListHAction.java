package com.qxh.action.tuiku;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.TuikuService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class GetTuikuListHAction extends MainAction implements Controller {
	private TuikuService tuikuService;
	
	public void setTuikuService(TuikuService tuikuService) {
		this.tuikuService = tuikuService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String state = req.getParameter("state");
		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String page = req.getParameter("page");
		String customerId = req.getParameter("customerId");
		if(StringUtils.isEmpty(page))
			page="1";
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result = tuikuService.getTuikuListH(state,stime,etime,
				page,user.getStructId(),user.getRoleId(),customerId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}