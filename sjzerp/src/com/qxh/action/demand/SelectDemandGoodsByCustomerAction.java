package com.qxh.action.demand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.DemandService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : SelectDemandGoodsByGoodsAction
 * @todo : [按客户查看]
 * 
 * 创建时间 ： 2016年12月22日上午10:05:12
 */
public class SelectDemandGoodsByCustomerAction extends MainAction implements Controller{
	private DemandService demandService;
	
	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}
	
	/* 
	 * Todo : [按客户查看]
	 * @时间:2016年12月22日上午10:06:10
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		String billId = request.getParameter("billId");
		String type = request.getParameter("type");
		String customerId = request.getParameter("customerId");
		User user = (User)SessionUtil.getSession().getAttribute("user");
		
		Result result = demandService.selectDemandGoodsByCustomer(billId,user.getStructId(),type,customerId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
