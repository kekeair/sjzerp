 package com.qxh.action.purchase;

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
 * 
 * @author Mr chen
 * @name : SelectGoodsInitAction
 * @todo : [查询页面初始化]
 * 
 * 创建时间 ： 2016年12月23日上午10:39:38
 */
public class SelectGoodsInitAction extends MainAction implements Controller{
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = (User)SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user,CodeConstant.NAV_302,CodeConstant.NAV_3);
		mav.addObject("userNm",user.getUserNm());
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("purchase/selectPurchaseGoodsView");
		return CheckUtil.returnResult(mav, CodeConstant.CODE1000, "","");
	}

}
