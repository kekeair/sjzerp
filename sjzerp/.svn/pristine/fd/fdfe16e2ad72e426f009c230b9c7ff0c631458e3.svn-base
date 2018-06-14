package com.qxh.action.demand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 
 * @author Mr chen
 * @name : ExportOutDemandBillByStateAndTimeAction
 * @todo : [客户申报汇总]
 * 
 * 创建时间 ： 2016年12月20日上午11:16:47
 */
public class ExportOutDemandBillByStateAndTimeAction implements Controller{
	
	private ExportOutDemandBillByStateAndTimeView  export;
	
	public void setExport(ExportOutDemandBillByStateAndTimeView export) {
		this.export = export;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		return new ModelAndView(export, null);
	}
}