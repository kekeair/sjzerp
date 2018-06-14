package com.qxh.action.purchaseSumm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**

 * @Description:[分大类汇总导出]

 * @author:kekeair

 * @time:2017年4月14日 上午9:01:34

 */
public class ExportOutBigKindAction implements Controller {

	private ExportOutBigKindView export;

	public void setExport(ExportOutBigKindView export) {
		this.export = export;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return new ModelAndView(export, null);
	}
}