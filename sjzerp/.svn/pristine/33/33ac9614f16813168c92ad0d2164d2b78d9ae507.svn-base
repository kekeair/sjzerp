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
import com.qxh.model.User;
import com.qxh.service.TagService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : GetAddTagAction
 * @todo : [添加/修改标签]
 * 
 * 创建时间 ： 2016年11月18日下午1:36:48
 */
public class AddTagAction implements Controller{

	Logger log = Logger.getLogger(this.getClass());
	private TagService tagService;
	
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
	/* 
	 * Todo : [添加/修改标签]
	 * @时间:2016年11月18日下午1:37:41
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		String atNo = request.getParameter("tagId");
		String tagNm = request.getParameter("tagNm");
		User user = (User)SessionUtil.getSession().getAttribute("user");
		int userId = user.getAtNo();
		String creator = userId+"".trim();
		int structId = user.getStructId();
        List<String> params = new ArrayList<>();
        params.add(tagNm);
        params.add(creator);
        
        String checkResult = CheckUtil.checkParam(request, params);
		if (StringUtils.isEmpty(checkResult)) {
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		if (tagNm==null)
			tagNm="";
		if(creator==null)
			creator="";
		
		Result result=new Result();
		try {
			 if("-1".equals(atNo)){
				 tagService.addTag(tagNm,creator,structId);
				 result.setCode(CodeConstant.CODE1000);
					result.setMsg("添加成功");
			 }else{
				 tagService.editTag(atNo,tagNm);
				 result.setCode(CodeConstant.CODE1000);
				 result.setMsg("修改成功");
			 }
		}catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
