/**
 * 
 */
package com.qxh.action.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.constant.CodeConstant;
import com.qxh.service.CustomerService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : delCustomer
 * @todo : [删除客户]
 * 
 * 创建时间 ： 2016年11月17日上午9:05:44
 */
public class delCustomer implements Controller{

	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	/* 
	 * Todo : [删除客户]
	 * @时间:2016年11月17日上午9:06:09
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();		
		String customerId = request.getParameter("customerId");
		Integer atNo = Integer.parseInt(customerId);
		
//		if(atNo==null){
//			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
//		}
		
		Result result=new Result();
		try {
			 customerService.delCustomer(atNo);
			 result.setCode(CodeConstant.CODE1000);
			 result.setMsg("删除成功");
		}catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
