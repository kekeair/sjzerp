package com.qxh.action.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.SessionUtil;

public class LogoutAction extends MainAction implements Controller {
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session=SessionUtil.getSession();
		session.removeAttribute("userAccount");
		session.removeAttribute("pwd");
		session.removeAttribute("user");
		session.removeAttribute("structId");
		return CheckUtil.returnResult(mav, CodeConstant.CODE1000, "","");
	}
}