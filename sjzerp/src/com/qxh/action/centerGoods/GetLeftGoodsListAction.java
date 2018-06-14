package com.qxh.action.centerGoods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.service.CenterGoodsService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class GetLeftGoodsListAction extends MainAction implements Controller {
	private CenterGoodsService centerGoodsService;
	
	public void setCenterGoodsService(CenterGoodsService centerGoodsService) {
		this.centerGoodsService = centerGoodsService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String name = req.getParameter("name");
		String lKindCode = req.getParameter("lKindCode");
		String leftPage = req.getParameter("leftPage");
		String goodsCode = req.getParameter("goodsCode");
		if (name==null)
			name="";
		if(StringUtils.isEmpty(lKindCode))
			lKindCode="";
		if(StringUtils.isEmpty(goodsCode))
			goodsCode="";
		if(StringUtils.isEmpty(leftPage))
			leftPage="1";
		int structId=(Integer)SessionUtil.getSession().getAttribute("structId");
		Result result = centerGoodsService.getLeftGoodsList(name,lKindCode,leftPage,structId,goodsCode);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}