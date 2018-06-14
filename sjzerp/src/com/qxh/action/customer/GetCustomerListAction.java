package com.qxh.action.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.CustomerService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;
/**
 * 
 * @author Mr chen
 * @name : GetCustomerListAction
 * @todo : [客户action层]
 * 
 * 创建时间 ： 2016年11月16日下午2:37:58
 */
public class GetCustomerListAction extends MainAction implements Controller {
	Logger log = Logger.getLogger(this.getClass());
	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		String customerNm = req.getParameter("customerNm");
		String page = req.getParameter("page");
		User user=(User)SessionUtil.getSession().getAttribute("user");
		int structId = user.getStructId();
		if (customerNm==null)
			customerNm="";
		if(StringUtils.isEmpty(page))
			page="1";
		Result result = customerService.getCustomerList(structId, customerNm,page);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}