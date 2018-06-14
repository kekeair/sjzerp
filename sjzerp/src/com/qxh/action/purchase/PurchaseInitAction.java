package com.qxh.action.purchase;

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

public class PurchaseInitAction extends MainAction implements Controller {
	private CommonService commonService;

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = (User) SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user, CodeConstant.NAV_302, CodeConstant.NAV_3);
		Result result = commonService.getCustomerList(user.getStructId());
		mav.addObject("roleId", user.getRoleId());
		mav.addObject("userNm", user.getUserNm());
		mav.setViewName("purchase/purchaseList");
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}