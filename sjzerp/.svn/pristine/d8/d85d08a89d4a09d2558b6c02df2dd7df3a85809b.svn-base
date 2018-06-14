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
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : addAllTagGoodsAction
 * @todo : [批量添加]
 * 
 *       创建时间 ： 2016年12月1日下午8:29:27
 */
public class addAllTagGoodsAction extends MainAction implements Controller {

	private TagGoodsService tagGoodsService;

	public void setTagGoodsService(TagGoodsService tagGoodsService) {
		this.tagGoodsService = tagGoodsService;
	}

	/*
	 * Todo : [批量添加]
	 * 
	 * @时间:2016年12月1日下午8:29:56
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		String name = req.getParameter("name");
		String lKindCode = req.getParameter("lKindCode");
		String goodsCode = req.getParameter("goodsCode");
		String tagId = req.getParameter("tagId");
		if (name == null)
			name = "";
		if (StringUtils.isEmpty(lKindCode))
			lKindCode = "";
		if (StringUtils.isEmpty(tagId))
			tagId = "";
		if (StringUtils.isEmpty(goodsCode))
			goodsCode = "";
		int structId = (Integer) SessionUtil.getSession().getAttribute("structId");
		Result result = tagGoodsService.addAllGoodsList(name, lKindCode, structId, goodsCode,tagId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(), result.getData());
	}

}
