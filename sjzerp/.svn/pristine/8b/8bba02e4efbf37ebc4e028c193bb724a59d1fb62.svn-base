package com.qxh.action.purchaseSumm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


/**

 * @Description:[毛利汇总导出]

 * @author:kekeair

 * @time:2017年3月7日 上午11:02:30

 */
public class ExportProfitSummAction implements Controller {

	private ExportProfitSummView export;

	public void setExport(ExportProfitSummView export) {
		this.export = export;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return new ModelAndView(export, null);
	}
}