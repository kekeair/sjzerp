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

import com.qxh.model.User;
import com.qxh.service.CustomerteamService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : getCustomerteamList
 * @todo : [作业组列表]
 * 
 * 创建时间 ： 2016年11月17日下午2:12:34
 */
public class getCustomerteamListAction implements Controller{
	Logger log = Logger.getLogger(this.getClass());
	
	private CustomerteamService customerteamService;
	
	public void setCustomerteamService(CustomerteamService customerteamService) {
		this.customerteamService = customerteamService;
	}

	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String teamNm = req.getParameter("teamNm");
		String headAtNo = req.getParameter("headAtNo");
		String page = req.getParameter("page");
		
		User user=(User)SessionUtil.getSession().getAttribute("user");
		
		if (teamNm==null)
			teamNm="";
		if(StringUtils.isEmpty(page))
			page="1";
		Result result = customerteamService.getCustomerList(headAtNo, teamNm, page);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
		
	}

}
