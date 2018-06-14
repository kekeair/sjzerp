package com.qxh.action.user;

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
import com.qxh.service.UserService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class EditPwdAction extends MainAction implements Controller {
	Logger log = Logger.getLogger(this.getClass());
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String oldPwd = req.getParameter("oldPwd");
		String confirmPwd = req.getParameter("confirmPwd");
		List<String> params = new ArrayList<>();
		params.add(oldPwd);
		params.add(confirmPwd);
		
		String checkResult = CheckUtil.checkParam(req, params);
		if (StringUtils.isEmpty(checkResult)) {
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		Result result = userService.updatePwd(oldPwd, confirmPwd);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
	
}