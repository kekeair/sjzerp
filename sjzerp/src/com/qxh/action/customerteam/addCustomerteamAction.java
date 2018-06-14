/**
 * 
 */
package com.qxh.action.customerteam;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.constant.CodeConstant;
import com.qxh.service.CustomerteamService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : addCustomerteamAction
 * @todo : [添加工作组]
 * 
 * 创建时间 ： 2016年11月17日下午5:21:36
 */
public class addCustomerteamAction implements Controller{

	Logger log = Logger.getLogger(this.getClass());
	private CustomerteamService customerteamService;
	
	public void setCustomerteamService(CustomerteamService customerteamService) {
		this.customerteamService = customerteamService;
	}
	
	/* 
	 * Todo : [添加工作组]
	 * @时间:2016年11月17日下午5:21:54
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String teamNm = request.getParameter("teamNm");
		String headAtNo = request.getParameter("headAtNo");
		
        List<String> params = new ArrayList<>();
        params.add(teamNm);
        params.add(headAtNo);
        
        String checkResult = CheckUtil.checkParam(request, params);
		if (StringUtils.isEmpty(checkResult)) {
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		
		if (teamNm==null)
			teamNm="";
		if(headAtNo==null)
			headAtNo="";
		
		Result result=new Result();
		try {
			 String atNo = request.getParameter("customerteamId");
			 if("-1".equals(atNo)){
				 customerteamService.addCustomerteam(teamNm,headAtNo);
				 result.setCode(CodeConstant.CODE1000);
					result.setMsg("添加成功");
			 }else{
				customerteamService.editCustomerteam(atNo,teamNm);
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
