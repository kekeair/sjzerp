package com.qxh.action.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : TagInitAction
 * @todo : [标签管理]
 * 
 * 创建时间 ： 2016年11月18日上午9:38:49
 */
public class TagInitAction extends MainAction implements Controller{

	/* 
	 * Todo : [标签页面]
	 * @时间:2016年11月18日上午9:39:09
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = (User)SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user,CodeConstant.NAV_207,CodeConstant.NAV_2);
		mav.addObject("userNm",user.getUserNm());
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("centerConfig/tag");
		return CheckUtil.returnResult(mav,CodeConstant.CODE1000,"","");
	}

}
