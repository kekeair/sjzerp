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
 * @name : getCustomerteamById
 * @todo : [通过id获取工作组]
 * 
 * 创建时间 ： 2016年11月17日下午6:15:36
 */
public class getCustomerteamByIdAction implements Controller {

	Logger log = Logger.getLogger(this.getClass());
	private CustomerteamService customerteamService;
	
	public void setCustomerteamService(CustomerteamService customerteamService) {
		this.customerteamService = customerteamService;
	}
	
	/* 
	 * Todo : [通过id获取工作组]
	 * @时间:2016年11月17日下午6:15:52
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String atNo = request.getParameter("customerteamId");
		
		List<String> params = new ArrayList<>();
        params.add(atNo);
        
        String checkResult = CheckUtil.checkParam(request, params);
		if (StringUtils.isEmpty(checkResult)) {
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		Result result=new Result();
		try {
			result = customerteamService.getCustomerteamById(atNo);
		}catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
