package com.qxh.action.customer;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * @todo : []
 * 
 * 创建时间 ： 2016年11月20日下午10:56:10
 */
public class GetTagListAction implements Controller{
	
	private TagService tagService;
	
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	/* 
	 * Todo : [获取tag集合]
	 * @时间:2016年11月20日下午10:56:56
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		String tagId = req.getParameter("tagId");
		User user=(User)SessionUtil.getSession().getAttribute("user");
		int structId = user.getStructId();
		Result result = tagService.getTagListForCustomer(structId);
		Map<String, Object> data =   (Map<String, Object>)result.getData();
		data.put("tagId", tagId);
		//data.put("structId", structId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}
