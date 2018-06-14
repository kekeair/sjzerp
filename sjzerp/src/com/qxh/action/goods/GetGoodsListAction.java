package com.qxh.action.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.service.GoodsService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class GetGoodsListAction extends MainAction implements Controller {
	private GoodsService goodsService;
	
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String name = req.getParameter("name");
		String code = req.getParameter("code");
		String kindCode = req.getParameter("kindCode");
		String page = req.getParameter("page");
		if (name==null)
			name="";
		if(code==null)
			code="";
		if(kindCode==null)
			kindCode="";
		if(StringUtils.isEmpty(page))
			page="1";
		Result result = goodsService.getGoodsList(name,code,kindCode,page);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}