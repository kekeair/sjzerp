package com.qxh.action.demandSumm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.service.CustomerService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : GetCustomByStrustIDdAction
 * @todo : [通过strutsId获取客户名称]
 * 
 * 创建时间 ： 2016年12月8日下午5:51:23
 */
public class GetCustomByStrustIdAction implements Controller{

	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	/* 
	 * @时间:2016年12月8日下午5:55:26
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result=new Result();
		try {
			result = customerService.getCustomByStrustId(user.getStructId());
		}catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
