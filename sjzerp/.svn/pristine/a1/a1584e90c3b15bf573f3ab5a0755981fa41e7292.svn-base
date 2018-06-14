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

public class GetTagGoodsListAction extends MainAction implements Controller {
    private TagGoodsService tagGoodsService;
	
	public void setTagGoodsService(TagGoodsService tagGoodsService) {
		this.tagGoodsService = tagGoodsService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String rkindCode = req.getParameter("kindCode");
		String rightPage = req.getParameter("rightPage");
		String tagId = req.getParameter("tagId");
		if(StringUtils.isEmpty(rkindCode))
			rkindCode="";
		if(StringUtils.isEmpty(rightPage))
			rightPage="1";
		Result result = tagGoodsService.getTagGoodsList(rkindCode,rightPage,tagId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}