package com.qxh.action.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.service.HomeService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class HomeAction extends MainAction implements Controller{
	
	private HomeService homeService;
	
	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = (User)SessionUtil.getSession().getAttribute("user");
//		Result result = homeService.getIndexData(user);
		mav.addObject("userNm",user.getUserNm());
		getPowerRight(mav, user,CodeConstant.NAV_1001,"");
		mav.addObject("userNm",user.getUserNm());
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("home/home");
		return CheckUtil.returnResult(mav, CodeConstant.CODE1000,"","");
	}

}
