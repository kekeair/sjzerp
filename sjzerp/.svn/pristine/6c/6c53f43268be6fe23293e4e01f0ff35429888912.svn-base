package com.qxh.action.tuiku;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ExportTuikuBillListAction implements Controller{
	
	private ExportTuikuBillListView  export;
	
	public void setExport(ExportTuikuBillListView export) {
		this.export = export;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		return new ModelAndView(export, null);
	}
}