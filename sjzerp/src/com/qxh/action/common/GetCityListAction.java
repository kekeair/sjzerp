package com.qxh.action.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.service.CommonService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class GetCityListAction extends MainAction implements Controller {
	Logger log = Logger.getLogger(this.getClass());
	private CommonService commonService;
	
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String provinceId = req.getParameter("provinceId");
		List<String> params = new ArrayList<>();
		params.add(provinceId);
		String checkResult = CheckUtil.checkParam(req, params);
		if (StringUtils.isEmpty(checkResult)) {
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		Result result = commonService.getCityList(provinceId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}