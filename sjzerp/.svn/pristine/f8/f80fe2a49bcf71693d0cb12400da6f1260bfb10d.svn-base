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

public class AddCenterGoodsAction extends MainAction implements Controller {
	private CenterGoodsService centerGoodsService;
	
	public void setCenterGoodsService(CenterGoodsService centerGoodsService) {
		this.centerGoodsService = centerGoodsService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String rightKindId = req.getParameter("rightKindId");
		String rightPage = req.getParameter("rightPage");
		String goodsId = req.getParameter("goodsId");
		if(StringUtils.isEmpty(rightKindId))
			rightKindId="0";
		if(StringUtils.isEmpty(rightPage))
			rightPage="1";
		int structId=(Integer)SessionUtil.getSession().getAttribute("structId");
		Result result = centerGoodsService.addCenterGoods(rightKindId,rightPage,
				goodsId,structId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}