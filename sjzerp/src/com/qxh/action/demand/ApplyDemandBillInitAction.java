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
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : ApplyDemandBillInitAction
 * @todo : [客户申请初始化页面]
 * 
 * 创建时间 ： 2016年12月26日下午3:27:15
 */
public class ApplyDemandBillInitAction extends MainAction implements Controller {

	
	private DemandService demandService;
	
	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String customerId=req.getParameter("customerId");
		String teamId=req.getParameter("teamId");
		String type=req.getParameter("type");
		User user = (User)SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user,CodeConstant.NAV_301,CodeConstant.NAV_3);
		Result result=demandService.getTeamList(customerId,teamId,type);
		mav.addObject("userNm",user.getUserNm());
		mav.addObject("roleId", user.getRoleId());
		mav.addObject("type",type);
		mav.setViewName("demand/applyDemandBill");
		return CheckUtil.returnResult(mav, result.getCode(), 
				result.getMsg(),result.getData());
	}
	}
