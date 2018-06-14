package com.qxh.action.customerteam;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.constant.CodeConstant;
import com.qxh.service.CustomerteamService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : GetCustomTeamNameListAction
 * @todo : [通过账单id查询工作组]
 * 
 * 创建时间 ： 2016年11月23日下午4:25:57
 */
public class GetCustomTeamNameListAction implements Controller {

	private CustomerteamService customerteamService;
	
	public void setCustomerteamService(CustomerteamService customerteamService) {
		this.customerteamService = customerteamService;
	}
	/* 
	 * Todo : [通过账单id查询工作组]
	 * @时间:2016年11月23日下午4:26:10
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		String teamBillId = request.getParameter("teamBillId");
		
		List<String> params = new ArrayList<>();
        params.add(teamBillId);
        
        String checkResult = CheckUtil.checkParam(request, params);
		if (StringUtils.isEmpty(checkResult)) {
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		Result result=new Result();
		try {
			result = customerteamService.getCustomTeamNameList(teamBillId);
		}catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
