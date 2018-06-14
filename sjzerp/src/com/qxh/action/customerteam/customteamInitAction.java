/**
 * 
 */
package com.qxh.action.customerteam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.service.CustomerteamService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : customteamInit
 * @todo : [工作组]
 * 
 * 创建时间 ： 2016年11月17日下午1:28:46
 */
public class customteamInitAction extends MainAction implements Controller{

	Logger log = Logger.getLogger(this.getClass());
	private CustomerteamService customerteamService;
	
	public void setCustomerteamService(CustomerteamService customerteamService) {
		this.customerteamService = customerteamService;
	}

	/* 
	 * Todo : [工作组页面初始化]
	 * @时间:2016年11月17日下午1:29:34
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		String headAtNo = req.getParameter("customerId");
		String page = req.getParameter("page");
		String teamNm = req.getParameter("teamNm");
		if (teamNm==null)
			teamNm="";
		if(StringUtils.isEmpty(page))
			page="1";
		if (headAtNo==null)
			headAtNo="";
		Result result = customerteamService.getCustomerList(headAtNo, teamNm,page);
		User user = (User)SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user,CodeConstant.NAV_205,CodeConstant.NAV_2);
		mav.addObject("userNm",user.getUserNm());
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("centerConfig/customteam");
		
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
