package com.qxh.action.role;

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
import com.qxh.service.RoleService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class AddRoleAction extends MainAction implements Controller {
	Logger log = Logger.getLogger(this.getClass());
	private RoleService roleService;
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pId = req.getParameter("pId");
		String levelOrder = req.getParameter("levelOrder");
		String name = req.getParameter("name");
		List<String> params = new ArrayList<>();
		params.add(pId);
		params.add(levelOrder);
		String checkResult = CheckUtil.checkParam(req, params);
		if (StringUtils.isEmpty(checkResult)) {
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		if (StringUtils.isEmpty(name))
			name="";
		Result result = roleService.addRole(pId,levelOrder,name);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}