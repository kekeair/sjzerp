package com.qxh.action.purchaseSumm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.SessionUtil;
/**
 * 
 * @author Mr chen
 * @name : PurchaesSummInitAction
 * @todo : [采购入库明细表]
 * 
 * 创建时间 ： 2016年12月2日上午10:14:40
 */
public class PurchaseSummDInitAction extends MainAction implements Controller{
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String goodsId = req.getParameter("gId");
		User user = (User)SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user,CodeConstant.NAV_502,CodeConstant.NAV_5);
		if(StringUtils.isEmpty(goodsId))
			goodsId="-1";
		mav.addObject("userNm",user.getUserNm());
		mav.addObject("goodsId", goodsId);
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("inoutSumm/purchaseSummD");
		return CheckUtil.returnResult(mav,CodeConstant.CODE1000, "","");
	}

}
