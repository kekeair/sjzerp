package com.qxh.action.tag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.constant.CodeConstant;
import com.qxh.service.TagService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : GetTagByIdAction
 * @todo : [通过id获取标签对象]
 * 
 * 创建时间 ： 2016年11月18日下午2:40:07
 */
public class GetTagByIdAction implements Controller{

	Logger log = Logger.getLogger(this.getClass());
	private TagService tagService;
	
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
	/* 
	 * Todo : [通过id获取标签对象]
	 * @时间:2016年11月18日下午2:40:30
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		String atNo = request.getParameter("tagId");
		
		List<String> params = new ArrayList<>();
        params.add(atNo);
        
        String checkResult = CheckUtil.checkParam(request, params);
		if (StringUtils.isEmpty(checkResult)) {
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		Result result=new Result();
		try {
			result = tagService.getTagById(atNo);
		}catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
