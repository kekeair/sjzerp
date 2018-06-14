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

public class GetTagGoodsDataAction extends MainAction implements Controller {
	
	private TagGoodsService tagGoodsService;
	
	public void setTagGoodsService(TagGoodsService tagGoodsService) {
		this.tagGoodsService = tagGoodsService;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String name = req.getParameter("name");
		String tagId = req.getParameter("tagId");
		String lkindCode = req.getParameter("lkindCode");
		String rkindCode = req.getParameter("rkindCode");
		String leftPage = req.getParameter("leftPage");
		String rightPage = req.getParameter("rightPage");
		String goodsCode = req.getParameter("goodsCode");
		if (name==null)
			name="";
		if(StringUtils.isEmpty(goodsCode))
			goodsCode="";
		if(StringUtils.isEmpty(lkindCode))
			lkindCode="";
		if(StringUtils.isEmpty(leftPage))
			leftPage="1";
		if(StringUtils.isEmpty(rkindCode))
			rkindCode="";
		if(StringUtils.isEmpty(rightPage))
			rightPage="1";
		int structId=(Integer)SessionUtil.getSession().getAttribute("structId");
		Result result = tagGoodsService.getTagGoodsData(name,lkindCode,leftPage,
				rkindCode,rightPage,structId,tagId,goodsCode);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}