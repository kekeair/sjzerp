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

 * @Description:[单品毛利汇总页面]

 * @author:kekeair

 * @time:2017年3月6日 上午8:48:06

 */
public class ProfitSummInitAction extends MainAction implements Controller{
	
	/**
	  * @todo : [出入库的采购入库明细表]
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String goodsId = req.getParameter("gId");
		User user = (User)SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user,CodeConstant.NAV_504,CodeConstant.NAV_5);
		if(StringUtils.isEmpty(goodsId))
			goodsId="-1";
		mav.addObject("userNm",user.getUserNm());
		mav.addObject("goodsId", goodsId);
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("inoutSumm/profitSumm");
		return CheckUtil.returnResult(mav,CodeConstant.CODE1000, "","");
	}

}
