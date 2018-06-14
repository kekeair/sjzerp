package com.qxh.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.service.UserService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class GetUserListAction extends MainAction implements Controller {
	Logger log = Logger.getLogger(this.getClass());
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String name = req.getParameter("name");
		String structId = req.getParameter("structId");
		String roleId = req.getParameter("roleId");
		String page = req.getParameter("page");
		if (name==null)
			name="";
		if(StringUtils.isEmpty(structId))
			structId="0";
		if(StringUtils.isEmpty(roleId))
			roleId="0";
		if(StringUtils.isEmpty(page))
			page="1";
		Result result = userService.getUserList(name,structId,roleId,page);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}