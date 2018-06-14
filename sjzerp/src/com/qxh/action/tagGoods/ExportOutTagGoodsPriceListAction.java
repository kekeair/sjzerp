package com.qxh.action.tagGoods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author Mr chen
 * @name : ExportOutTagGoodsPriceListAction
 * @todo : [标签价格的导出]
 * 
 * 创建时间 ： 2017年1月11日下午5:58:28
 */
public class ExportOutTagGoodsPriceListAction implements Controller{
	private ExportOutTageGoodsBillView exportOutTageGoodsBillView;
	
	public void setExportOutTageGoodsBillView(ExportOutTageGoodsBillView exportOutTageGoodsBillView) {
		this.exportOutTageGoodsBillView = exportOutTageGoodsBillView;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		return new ModelAndView(exportOutTageGoodsBillView, null);
	}
}
