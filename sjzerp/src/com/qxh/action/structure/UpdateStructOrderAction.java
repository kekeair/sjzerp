package com.qxh.action.structure;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.service.StructureService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class UpdateStructOrderAction extends MainAction implements Controller{
	
	private StructureService structureService;
	
	public void setStructureService(StructureService structureService) {
		this.structureService = structureService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String atNo = req.getParameter("atNo");
		String pId = req.getParameter("pId");
		String levelOrder = req.getParameter("levelOrder");
		List<String> params = new ArrayList<>();
		params.add(atNo);
		params.add(pId);
		params.add(levelOrder);
		String checkResult = CheckUtil.checkParam(req, params);
		if (StringUtils.isEmpty(checkResult)) {
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		Result result = structureService.updateStructOrder(atNo,pId,levelOrder);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
