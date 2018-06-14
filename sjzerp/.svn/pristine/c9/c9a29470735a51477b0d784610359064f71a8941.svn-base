package com.qxh.action.stockFix;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.StockFixService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class AddExtraGoodsAction extends MainAction implements Controller{
	
	private StockFixService stockFixService;
	
	public void setStockFixService(StockFixService stockFixService) {
		this.stockFixService = stockFixService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String goodsNm = req.getParameter("goodsNm");
		String goodsCode = req.getParameter("goodsCode");
		String goodsUnit = req.getParameter("goodsUnit");
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result = stockFixService.addExtraGoods(goodsNm,goodsCode,goodsUnit,
				user.getStructId());
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
