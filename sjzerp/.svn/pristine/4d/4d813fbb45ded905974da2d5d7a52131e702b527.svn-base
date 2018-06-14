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

public class GetCenterGoodsListAction extends MainAction implements Controller {
	private CenterGoodsService centerGoodsService;
	
	public void setCenterGoodsService(CenterGoodsService centerGoodsService) {
		this.centerGoodsService = centerGoodsService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String rKindCode = req.getParameter("rKindCode");
		String rightPage = req.getParameter("rightPage");
		if(StringUtils.isEmpty(rKindCode))
			rKindCode="";
		if(StringUtils.isEmpty(rightPage))
			rightPage="1";
		int structId=(Integer)SessionUtil.getSession().getAttribute("structId");
		Result result = centerGoodsService.getCenterGoodsList(rKindCode,rightPage,structId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}