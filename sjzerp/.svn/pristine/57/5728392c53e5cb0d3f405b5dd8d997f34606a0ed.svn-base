package com.qxh.action.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


/**

 * @Description:[采购单汇总]

 * @author:kekeair

 * @time:2017年3月4日 下午3:08:25

 */
public class ExportOutPurchaseBillByStateAndTimeAction implements Controller{
	
	private ExportOutPurchaseBillByStateAndTimeView  export;
	
	public void setExport(ExportOutPurchaseBillByStateAndTimeView export) {
		this.export = export;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		return new ModelAndView(export, null);
	}
}