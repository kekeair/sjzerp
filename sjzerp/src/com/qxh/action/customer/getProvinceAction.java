/**
 * 
 */
package com.qxh.action.customer;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.service.CommonService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : getProvinceAction
 * @todo : [获得省列表]
 * 
 * 创建时间 ： 2016年11月16日下午10:12:28
 */
public class getProvinceAction implements Controller{

	
	private CommonService commonService;
	
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	/* 
	 * Todo : [获得省列表]
	 * @时间:2016年11月16日下午10:12:43
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Result result = commonService.getProvinceList();
		Map<String, Object> data = (Map<String, Object>) result.getData();
		mav.setViewName("centerConfig/customer");
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
