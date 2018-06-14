package com.qxh.action.demand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author Mr chen
 * @name : ExportOutDemandListDBillAction
 * @todo : [销售单导出]
 * 
 * 创建时间 ： 2017年1月15日上午11:21:52
 */
public class ExportOutDemandListDBillAction implements Controller{
	
	private ExportOutDemandListDBillView  export;
	
	public void setExport(ExportOutDemandListDBillView export) {
		this.export = export;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		return new ModelAndView(export, null);
	}
}