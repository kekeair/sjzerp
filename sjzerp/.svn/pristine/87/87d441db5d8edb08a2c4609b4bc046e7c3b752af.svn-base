package com.qxh.action.demand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 
 * @Description:[导出做账单makeBill]
 * 
 * @author:kekeair
 * 
 * @time:2017年3月21日 上午9:48:56
 * 
 */
public class OutExeclForMakeBillAction implements Controller {

	private OutExcelForMakeBillView export;
	public void setExport(OutExcelForMakeBillView export) {
		this.export = export;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return new ModelAndView(export, null);
	}
}