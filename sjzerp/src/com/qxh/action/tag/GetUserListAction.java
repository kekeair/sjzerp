package com.qxh.action.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.service.UserService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : GetUserListtAction
 * @todo : [获取user集合]
 * 
 * 创建时间 ： 2016年11月18日下午12:52:32
 */
public class GetUserListAction implements Controller{

	Logger log = Logger.getLogger(this.getClass());
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/* 
	 * Todo : [获取user集合]
	 * @时间:2016年11月18日下午12:52:57
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		//获取user集合
		Result result = userService.getUserListToTag();
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
