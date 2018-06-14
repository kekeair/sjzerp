package com.qxh.action.goodsUnit;

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
import com.qxh.service.GoodsUnitService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class EditUnitAction extends MainAction implements Controller {
	Logger log = Logger.getLogger(this.getClass());
	private GoodsUnitService goodsUnitService;
	
	public void setGoodsUnitService(GoodsUnitService goodsUnitService) {
		this.goodsUnitService = goodsUnitService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String atNo = req.getParameter("atNo");
		String name = req.getParameter("name");
		List<String> params = new ArrayList<>();
		params.add(atNo);
		params.add(name);
		String checkResult = CheckUtil.checkParam(req, params);
		if (StringUtils.isEmpty(checkResult)) {
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		Result result=new Result();
		try {
			result = goodsUnitService.editUnit(atNo,name);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}