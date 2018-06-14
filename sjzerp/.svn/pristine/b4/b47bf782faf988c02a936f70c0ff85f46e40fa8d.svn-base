package com.qxh.action.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ExportPurchaseTotallListAction implements Controller{
		
		private ExportPurchaseTotallListView  export;
		
		public void setExport(ExportPurchaseTotallListView export) {
			this.export = export;
		}
		
		@Override
		public ModelAndView handleRequest(HttpServletRequest req,
				HttpServletResponse res) throws Exception {
			return new ModelAndView(export, null);
		}
	}