package com.qxh.action.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class IndexAction implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ModelAndView mav=new ModelAndView();
		String path = req.getContextPath(); 
		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";		
		mav.addObject("basePath",basePath);		
		mav.setViewName("login/login");
		return mav;
	}
	
	 

}

 