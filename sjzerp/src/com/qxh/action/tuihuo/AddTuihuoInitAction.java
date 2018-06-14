package com.qxh.action.tuihuo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.service.CommonService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class AddTuihuoInitAction extends MainAction implements Controller{
	
	private CommonService commonService;
	
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = (User)SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user,CodeConstant.NAV_304,CodeConstant.NAV_3);
		Result result=commonService.getSupplier(user.getStructId());
		mav.addObject("userNm",user.getUserNm());
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("tuihuo/addTuihuo");
		return CheckUtil.returnResult(mav, result.getCode(), 
				result.getMsg(),result.getData());
	}

}
