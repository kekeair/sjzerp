package com.qxh.action.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.model.User;
import com.qxh.service.TagService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class GetTagGoodsAction implements Controller{
	
	private TagService tagService;
	
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String name=req.getParameter("name");
		String code=req.getParameter("code");
		String kindCode=req.getParameter("kindCode");
		String page=req.getParameter("page");
		String tagId=req.getParameter("tagId");
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result = tagService.getTagGoods(name,code,kindCode,page,tagId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
