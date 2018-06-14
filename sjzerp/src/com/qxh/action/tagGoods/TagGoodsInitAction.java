package com.qxh.action.tagGoods;

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
 * @author Mr chen
 * @name : TagGoodsInitAction
 * @todo : [物料管理初始化页面]
 * 
 * 创建时间 ： 2016年11月18日下午3:32:58
 */
public class TagGoodsInitAction extends MainAction  implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		User user = (User)SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user,CodeConstant.NAV_207,CodeConstant.NAV_2);
		mav.addObject("userNm",user.getUserNm());
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("centerConfig/tagGoods");
		
		return CheckUtil.returnResult(mav, CodeConstant.CODE1000, "","");
	}
	
}
