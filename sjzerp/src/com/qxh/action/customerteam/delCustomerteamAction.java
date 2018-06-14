/**
 * 
 */
package com.qxh.action.customerteam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.constant.CodeConstant;
import com.qxh.service.CustomerteamService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : delCustomerteamAction
 * @todo : [删除工作组]
 * 
 * 创建时间 ： 2016年11月17日下午6:47:43
 */
public class delCustomerteamAction implements Controller{

	private CustomerteamService customerteamService;
	
	public void setCustomerteamService(CustomerteamService customerteamService) {
		this.customerteamService = customerteamService;
	}
	
	/* 
	 * Todo : [删除工作组]
	 * @时间:2016年11月17日下午6:48:33
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();		
		String customerteamId = request.getParameter("customerteamId");
		Integer atNo = Integer.parseInt(customerteamId);
		
		if(atNo==null){
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		
		Result result=new Result();
		try {
			customerteamService.delCustomerteam(atNo);
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
