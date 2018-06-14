package com.qxh.action.stockQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.StockQueryService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class GetStockRecordAction extends MainAction implements Controller{
	
	private StockQueryService stockQueryService;
	
	public void setStockQueryService(StockQueryService stockQueryService) {
		this.stockQueryService = stockQueryService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String stockFlag = req.getParameter("stockFlag");
		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String goodsId = req.getParameter("goodsId");
		String page = req.getParameter("page");
		if(StringUtils.isEmpty(page))
			page="1";
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result = stockQueryService.getStockRecord(stockFlag,stime,etime,page,
				goodsId,user.getStructId());
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
