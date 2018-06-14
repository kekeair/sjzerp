/**
 * 
 */
package com.qxh.action.customer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
 * @name : addCustomer
 * @todo : [添加客户]
 * 
 * 创建时间 ： 2016年11月16日下午11:26:37
 */
public class addCustomer implements Controller{
	Logger log = Logger.getLogger(this.getClass());
	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	/* 
	 * Todo : [添加用户]
	 * @时间:2016年11月16日下午11:26:59
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String customNm = request.getParameter("customNm");
		String phone = request.getParameter("phone");
		String provinceId = request.getParameter("provinceId");
		String cityId = request.getParameter("cityId");
		String countyId = request.getParameter("countyId");
        String address = request.getParameter("address");
        String countermanId = request.getParameter("countermanId");
        List<String> params = new ArrayList<>();
        params.add(customNm);
        params.add(phone);
        params.add(provinceId);
        params.add(cityId);
        params.add(countyId);
        params.add(address);
        params.add(countermanId);
        
        String checkResult = CheckUtil.checkParam(request, params);
		if (StringUtils.isEmpty(checkResult)) {
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		if (phone==null)
			phone="";
		if(address==null)
			address="";
		Result result=new Result();
		try {
			User user=(User)SessionUtil.getSession().getAttribute("user");
			String atNo = request.getParameter("customerId");
			if("-1".equals(atNo)){
				customerService.addCustomer(customNm,phone,provinceId,cityId,countyId,
						address,user.getStructId(),countermanId);
			 	result.setCode(CodeConstant.CODE1000);
				result.setMsg("添加成功");
			}else{
				customerService.editCustomer(atNo,customNm,phone,provinceId,cityId,countyId,address,countermanId);
				result.setCode(CodeConstant.CODE1000);
				result.setMsg("修改成功");
			}
		}catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
