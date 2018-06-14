package com.qxh.action.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.model.User;
import com.qxh.service.TagService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : GetTagListAction
 * @todo : [获取标签列表]
 * 
 * 创建时间 ： 2016年11月18日上午11:05:32
 */
public class GetTagListAction implements Controller{
	Logger log = Logger.getLogger(this.getClass());
	private TagService tagService;
	
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}


	/* 
	 * Todo : [获取标签列表]
	 * @时间:2016年11月18日上午11:06:04
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String page ="1";
		User user=(User)SessionUtil.getSession().getAttribute("user");
		
		Result result = tagService.getTagList(page,user.getStructId());
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
