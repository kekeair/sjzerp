package com.qxh.action.goodsUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.service.GoodsUnitService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class GetUnitListAction extends MainAction implements Controller {
	Logger log = Logger.getLogger(this.getClass());
	private GoodsUnitService goodsUnitService;
	
	public void setGoodsUnitService(GoodsUnitService goodsUnitService) {
		this.goodsUnitService = goodsUnitService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String page = req.getParameter("page");
		if(StringUtils.isEmpty(page))
			page="1";
		Result result = goodsUnitService.getUnitList(page);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}