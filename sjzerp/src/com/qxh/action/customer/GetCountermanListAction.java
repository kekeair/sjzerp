package com.qxh.action.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.CustomerService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : GetCountermanListAction
 * @todo : [获取业务员列表]
 * 
 * 创建时间 ： 2016年12月21日上午11:04:16
 */
public class GetCountermanListAction extends MainAction implements Controller {
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	/* 
	 * Todo : [获取业务员列表]
	 * @时间:2016年12月21日上午11:04:40
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
	  ModelAndView mav = new ModelAndView();
		
		User user=(User)SessionUtil.getSession().getAttribute("user");
		int structId = user.getStructId();
		
		Result result = customerService.getCountermanList(structId);
		
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
