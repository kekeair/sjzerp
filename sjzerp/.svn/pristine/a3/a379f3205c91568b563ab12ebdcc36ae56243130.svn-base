package com.qxh.action.demandSumm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 
 * @author Mr chen
 * @name : ExportOutDemandSummAction
 * @todo : [销售汇总单]
 * 
 * 创建时间 ： 2016年12月19日下午3:23:35
 */
public class ExportOutDemandSummAction implements Controller{
	
	private ExportOutDemandSummView  export;
	
	public void setExport(ExportOutDemandSummView export) {
		this.export = export;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		return new ModelAndView(export, null);
	}
}