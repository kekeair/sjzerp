package com.qxh.action.goodsKind;

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
import com.qxh.service.GoodsKindService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class AddGoodsKindAction extends MainAction implements Controller {
	Logger log = Logger.getLogger(this.getClass());
	private GoodsKindService goodsKindService;
	
	public void setGoodsKindService(GoodsKindService goodsKindService) {
		this.goodsKindService = goodsKindService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String pId = req.getParameter("pId");
		String code = req.getParameter("code");
		String name = req.getParameter("name");
		List<String> params = new ArrayList<>();
		params.add(pId);
		String checkResult = CheckUtil.checkParam(req, params);
		if (StringUtils.isEmpty(checkResult)) {
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		if (name==null)
			name="";
		if(code==null)
			code="";
		Result result = goodsKindService.addGoodsKind(pId,code,name);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}