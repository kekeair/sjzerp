package com.qxh.action.purchaseTotalD;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 
 * @author Mr chen
 * @name : ExportOutPurchaseTotalDView
 * @todo : [采购汇总明细导出]
 * 
 * 创建时间 ： 2016年12月3日上午8:47:33
 */
public class ExportOutPurchaseTotalDAction implements Controller{
	
	private ExportOutPurchaseTotalDView  export;

	public void setExport(ExportOutPurchaseTotalDView export) {
		this.export = export;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		return new ModelAndView(export, null);
	}
}