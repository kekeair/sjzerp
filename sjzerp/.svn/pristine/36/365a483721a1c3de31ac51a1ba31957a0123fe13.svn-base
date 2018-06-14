package com.qxh.action.purchaseSumm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 
 * @author Mr chen
 * @name : ExportOutPurchaseSummAction
 * @todo : [采购汇总导出]
 * 
 * 创建时间 ： 2016年12月19日下午2:31:44
 */
public class ExportOutPurchaseSummAction implements Controller{
	
	private ExportOutPurchaseSummView  export;
	
	public void setExport(ExportOutPurchaseSummView export) {
		this.export = export;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		return new ModelAndView(export, null);
	}
}