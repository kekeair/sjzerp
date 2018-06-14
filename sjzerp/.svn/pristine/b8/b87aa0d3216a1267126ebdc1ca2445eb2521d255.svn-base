package com.qxh.action.goodsPrice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.service.GoodsPriceService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class GetGoodsPriceAction extends MainAction implements Controller {
	private GoodsPriceService goodsPriceService;
	
	public void setGoodsPriceService(GoodsPriceService goodsPriceService) {
		this.goodsPriceService = goodsPriceService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String name = req.getParameter("name");
		String code = req.getParameter("code");
		String kindCode = req.getParameter("kindCode");
		String page = req.getParameter("page");
		String supplierId = req.getParameter("supplierId");
		if (name==null)
			name="";
		if(code==null)
			code="";
		if(StringUtils.isEmpty(kindCode) || kindCode.equals("-1"))
			kindCode="";
		if(StringUtils.isEmpty(page))
			page="1";
		int structId=(Integer)SessionUtil.getSession().getAttribute("structId");
		Result result = goodsPriceService.getGoodsPrice(name,code,kindCode,page,
				supplierId,structId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}