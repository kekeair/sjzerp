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

/**
 * @author Mr chen
 * @name : AllAddCenterGoodsAction
 * @todo : [批量添加]
 * 
 * 创建时间 ： 2016年12月1日下午3:23:39
 */
public class AllAddCenterGoodsAction extends MainAction implements Controller{
	private CenterGoodsService centerGoodsService;
	
	public void setCenterGoodsService(CenterGoodsService centerGoodsService) {
		this.centerGoodsService = centerGoodsService;
	}
	/* 
	 * Todo : [批量添加]
	 * @时间:2016年12月1日下午3:25:13
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		String name = req.getParameter("name");
		String lKindCode = req.getParameter("lKindCode");
		String goodsCode = req.getParameter("goodsCode");
		if (name==null)
			name="";
		if(StringUtils.isEmpty(lKindCode))
			lKindCode="";
		if(StringUtils.isEmpty(goodsCode))
			goodsCode="";
		int structId=(Integer)SessionUtil.getSession().getAttribute("structId");
		Result result = centerGoodsService.addAllGoodsList(name,lKindCode,structId,goodsCode);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
