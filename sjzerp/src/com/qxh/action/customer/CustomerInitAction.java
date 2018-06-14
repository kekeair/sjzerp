package com.qxh.action.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.SessionUtil;

public class CustomerInitAction extends MainAction implements Controller{
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = (User)SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user,CodeConstant.NAV_205,CodeConstant.NAV_2);
		mav.addObject("userNm",user.getUserNm());
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("centerConfig/customer");
		return CheckUtil.returnResult(mav,CodeConstant.CODE1000,"","");
	}

}
