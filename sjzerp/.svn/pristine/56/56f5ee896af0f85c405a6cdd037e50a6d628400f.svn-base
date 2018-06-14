package com.qxh.action.demandSumm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.service.DemandSummService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class DemandSummByGoodsInitAction extends MainAction implements Controller{
	
	private DemandSummService demandSummService;
	
	public void setDemandSummService(DemandSummService demandSummService) {
		this.demandSummService = demandSummService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String goodsId = req.getParameter("goodsId");
		String goodsType = req.getParameter("goodsType");
		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String demandListDId = req.getParameter("demandListDId");
		String customerId = req.getParameter("g_customId");
		User user = (User)SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user,CodeConstant.NAV_501,CodeConstant.NAV_5);
		Result result=demandSummService.getDemandSummByGoods(goodsId,goodsType,
				stime,etime,user.getStructId(),demandListDId,customerId);
		mav.addObject("userNm",user.getUserNm());
		mav.addObject("goodsId", goodsId);
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("inoutSumm/demandSummByGoods");
		return CheckUtil.returnResult(mav,result.getCode(), result.getMsg(),
				result.getData());
	}

}
