package com.qxh.action.goodsPrice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.service.GoodsPriceService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class EditGoodsPriceAction extends MainAction implements Controller {
	private GoodsPriceService goodsPriceService;
	
	public void setGoodsPriceService(GoodsPriceService goodsPriceService) {
		this.goodsPriceService = goodsPriceService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String priceJson = req.getParameter("priceJson");
		Result result=new Result();
		try {
			result = goodsPriceService.editGoodsPrice(priceJson);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}