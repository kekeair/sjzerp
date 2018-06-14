package com.qxh.action.tagGoods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.service.TagGoodsService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : DelTagGoodsAction
 * @todo : [删除右侧的物品项]
 * 
 * 创建时间 ： 2016年11月19日上午8:56:26
 */
public class DelTagGoodsAction extends MainAction implements Controller{

	private TagGoodsService tagGoodsService;
	
	public void setTagGoodsService(TagGoodsService tagGoodsService) {
		this.tagGoodsService = tagGoodsService;
	}
	/* 
	 * Todo : [删除右侧的物品项]
	 * @时间:2016年11月19日上午8:57:16
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {

		ModelAndView mav = new ModelAndView();
		String rightKindId = req.getParameter("rightKindId");
		String rightPage = req.getParameter("rightPage");
		String atNo = req.getParameter("tagGoodsId");
		String goodsId = req.getParameter("goodsId");
		String tagId = req.getParameter("tagId");
		if(StringUtils.isEmpty(rightKindId))
			rightKindId="0";
		if(StringUtils.isEmpty(rightPage))
			rightPage="1";
		//tagIdint structId=(Integer)SessionUtil.getSession().getAttribute("structId");
		Result result=new Result();
		try {
			result = tagGoodsService.delTagGoods(rightKindId,rightPage,
					atNo,tagId,goodsId);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("操作失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
