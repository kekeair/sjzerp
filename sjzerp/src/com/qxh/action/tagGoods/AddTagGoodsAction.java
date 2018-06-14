package com.qxh.action.tagGoods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.service.TagGoodsService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : AddTagGoodsAction
 * @todo : [向标签物料库中添加商品]
 * 
 * 创建时间 ： 2016年11月18日下午9:01:18
 */
public class AddTagGoodsAction extends MainAction implements Controller{

	private TagGoodsService tagGoodsService;
	
	public void setTagGoodsService(TagGoodsService tagGoodsService) {
		this.tagGoodsService = tagGoodsService;
	}
	/* 
	 * Todo : [向标签物料库中添加商品]
	 * @时间:2016年11月18日下午9:02:43
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		String rightKindId = req.getParameter("rightKindId");
		String rightPage = req.getParameter("rightPage");
		String goodsId = req.getParameter("goodsId");
		String tagId = req.getParameter("tagId");
		String tagGoodsPrice = req.getParameter("price");
		if(StringUtils.isEmpty(rightKindId))
			rightKindId="0";
		if(StringUtils.isEmpty(rightPage))
			rightPage="1";
		Result result = tagGoodsService.addTagGoods(rightKindId,rightPage,
				goodsId,tagId,tagGoodsPrice);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

	
}
