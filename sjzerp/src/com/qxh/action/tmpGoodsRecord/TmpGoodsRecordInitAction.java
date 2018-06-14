package com.qxh.action.tmpGoodsRecord;

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
 * @name : TmpGoodsRecordInitAction
 * @todo : [临时物料表]
 * 
 * 创建时间 ： 2016年11月23日上午9:16:50
 */
public class TmpGoodsRecordInitAction extends MainAction implements Controller {

	/* 
	 * Todo : [临时物料表初始页面]
	 * @时间:2016年11月23日上午9:26:24
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = (User)SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user,CodeConstant.NAV_403,CodeConstant.NAV_4);
		mav.addObject("userNm",user.getUserNm());
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("stock/TmpGoodsRecord");
		return CheckUtil.returnResult(mav, CodeConstant.CODE1000, "","");
	}

}
