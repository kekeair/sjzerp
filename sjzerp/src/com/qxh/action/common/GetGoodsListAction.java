package com.qxh.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.constant.CodeConstant;
import com.qxh.service.CommonService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : GetGoodsListAction
 * @todo : [获取物料列表]
 * 
 * 创建时间 ： 2016年12月26日下午2:35:23
 */
public class GetGoodsListAction  implements Controller{

	private CommonService commonService;
	
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	/* 
	 * Todo : [获取物料列表]
	 * @时间:2016年12月26日下午2:36:27
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		Result result = commonService.getCenterGoodsList();
		return CheckUtil.returnResult(mav, CodeConstant.CODE1000, "",result.getData());
	}

}
