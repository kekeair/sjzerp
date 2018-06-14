package com.qxh.action.structure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.service.StructureService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class GetStructureAction extends MainAction implements Controller {
	Logger log = Logger.getLogger(this.getClass());
	private StructureService structureService;
	
	public void setStructureService(StructureService structureService) {
		this.structureService = structureService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		Result result = structureService.getStructure();
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}