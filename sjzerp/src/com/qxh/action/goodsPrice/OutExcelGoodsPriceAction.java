package com.qxh.action.goodsPrice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 物料参数导出
 */
public class OutExcelGoodsPriceAction implements Controller{
	
	private OutExcelGoodsPriceView  export;
	
	public void setExport(OutExcelGoodsPriceView export) {
		this.export = export;
	}



	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		return new ModelAndView(export, null);
	}
}