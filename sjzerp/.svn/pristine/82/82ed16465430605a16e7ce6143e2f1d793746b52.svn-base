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
 * @name : GetLeftGoodsListtAction
 * @todo : [左查询]
 * 
 * 创建时间 ： 2016年11月20日下午6:51:07
 */
public class GetLeftGoodsListtAction extends MainAction implements Controller {

    private TagGoodsService tagGoodsService;
	
	public void setTagGoodsService(TagGoodsService tagGoodsService) {
		this.tagGoodsService = tagGoodsService;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String name = req.getParameter("name");
		String tagId = req.getParameter("tagId");
		String kindCode = req.getParameter("kindCode");
		String leftPage = req.getParameter("leftPage");
		String goodsCode = req.getParameter("goodsCode");
		if (name==null)
			name="";
		if(StringUtils.isEmpty(kindCode))
			kindCode="";
		if(StringUtils.isEmpty(goodsCode))
			goodsCode="";
		if(StringUtils.isEmpty(leftPage))
			leftPage="1";
		int structId=(Integer)SessionUtil.getSession().getAttribute("structId");
		Result result = tagGoodsService.getLeftGoodsList(name,kindCode,leftPage,structId,tagId,goodsCode);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}
