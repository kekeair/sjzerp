package com.qxh.action.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.service.CommonService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class AddGoodsInitAction extends MainAction implements Controller{
	
	private CommonService commonService;
	
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String goodsId = req.getParameter("gId");
		User user = (User)SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user,CodeConstant.NAV_106,CodeConstant.NAV_1);
		if(StringUtils.isEmpty(goodsId))
			goodsId="-1";
		Result result = commonService.getGoodsUnit();
		mav.addObject("userNm",user.getUserNm());
		mav.addObject("goodsId", goodsId);
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("baseInfo/addGoods");
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
