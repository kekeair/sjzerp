package com.qxh.action.demand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 
 * @author Mr chen
 * @name : ExportOutBillAction
 * @todo : [导出]
 * 
 * 创建时间 ： 2016年11月23日下午6:55:41
 */
public class ExportOutBillAction implements Controller{
	
	private ExportOutBillView  export;
	
	public void setExport(ExportOutBillView export) {
		this.export = export;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		return new ModelAndView(export, null);
	}
}