package com.qxh.action.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.constant.CodeConstant;
import com.qxh.service.TagService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : DelTagByIdAction
 * @todo : [删除标签]
 * 
 * 创建时间 ： 2016年11月18日下午2:58:36
 */
public class DelTagByIdAction implements Controller {

	Logger log = Logger.getLogger(this.getClass());
	private TagService tagService;
	
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	/* 
	 * Todo : [删除标签]
	 * @时间:2016年11月18日下午2:58:56
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		
		ModelAndView mav = new ModelAndView();		
		String tagId = request.getParameter("tagId");
		Integer atNo = Integer.parseInt(tagId);
		
		if(atNo==null){
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		
		Result result=new Result();
		try {
			tagService.delTag(atNo);
			 result.setCode(CodeConstant.CODE1000);
			 result.setMsg("删除成功");
		}catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
