package com.qxh.action.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.service.TestService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class TestAction extends MainAction implements Controller {
	private TestService testService;
	
	public void setTestService(TestService testService) {
		this.testService = testService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		Result result = testService.testJedis();
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}