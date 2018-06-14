package com.qxh.action.login;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.service.LoginService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class LoginAction extends MainAction implements Controller {
	Logger log = Logger.getLogger(this.getClass());
	private LoginService loginService;
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String userAccount = req.getParameter("userAccount");
		String pwd = req.getParameter("pwd");
		List<String> params = new ArrayList<>();
		params.add(userAccount);
		params.add(pwd);
		String checkResult = CheckUtil.checkParam(req, params);
		if (StringUtils.isEmpty(checkResult)) {
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		Result result = loginService.login(userAccount,pwd);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}