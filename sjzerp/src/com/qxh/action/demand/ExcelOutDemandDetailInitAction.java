package com.qxh.action.demand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.service.DemandService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : ExcelOutDemandDetailAction
 * @todo : [出库单详情列表页面]
 * 
 * 创建时间 ： 2016年12月27日上午10:33:27
 */
public class ExcelOutDemandDetailInitAction extends MainAction implements Controller{

	private DemandService demandService;
	
	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}
	/* 
	 * Todo : [出库单详情列表页面]
	 * @时间:2016年12月27日上午10:34:33
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		User user = (User)SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user,CodeConstant.NAV_301,CodeConstant.NAV_3);
		mav.addObject("userNm",user.getUserNm());
		mav.setViewName("demand/DemandDetailView");
		mav.addObject("roleId", user.getRoleId());
		return CheckUtil.returnResult(mav, CodeConstant.CODE1000, "","");
	}

}
