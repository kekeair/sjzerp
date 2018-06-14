package com.qxh.action.tuiku;

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
/**
 * 
 * @author Mr chen
 * @name : AddTuikuNewInitAction
 * @todo : [发起退库申请]
 * 
 * 创建时间 ： 2017年1月6日下午2:37:12
 */
public class AddTuikuNewInitAction extends MainAction implements Controller{
	
	private CommonService commonService;
	
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = (User)SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user,CodeConstant.NAV_303,CodeConstant.NAV_3);
		Result result=commonService.getCustomerList(user.getStructId());
		mav.addObject("userNm",user.getUserNm());
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("tuiku/addTuikuNew");
		return CheckUtil.returnResult(mav, result.getCode(), 
				result.getMsg(),result.getData());
	}

}
