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

public class GetStockListAction extends MainAction implements Controller{
	
	private StockQueryService stockQueryService;
	
	public void setStockQueryService(StockQueryService stockQueryService) {
		this.stockQueryService = stockQueryService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String name = req.getParameter("name");
		String kindCode = req.getParameter("kindCode");
		String page = req.getParameter("page");
		String goodsCode = req.getParameter("goodsCode");
		if(StringUtils.isEmpty(goodsCode))
			goodsCode="";
		if(StringUtils.isEmpty(page))
			page="1";
		if(name==null)
			name="";
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result = stockQueryService.getStockList(name,kindCode,page,
				user.getStructId(),goodsCode);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
