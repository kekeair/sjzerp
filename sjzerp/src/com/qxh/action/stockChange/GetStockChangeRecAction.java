package com.qxh.action.stockChange;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.StockChangeService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class GetStockChangeRecAction extends MainAction implements Controller {
	
	private StockChangeService stockChangeService;
	
	public void setStockChangeService(StockChangeService stockChangeService) {
		this.stockChangeService = stockChangeService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String stime=req.getParameter("stime");
		String etime=req.getParameter("etime");
		String name=req.getParameter("name");
		String kindCode=req.getParameter("kindCode");
		String goodsCode = req.getParameter("goodsCode");
		if(StringUtils.isEmpty(goodsCode))
			goodsCode="";
		if(name==null)
			name="";
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result = stockChangeService.getStockChangeRec(stime,etime,
				user.getStructId(),name,kindCode,goodsCode);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}