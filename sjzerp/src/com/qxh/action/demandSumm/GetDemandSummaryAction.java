package com.qxh.action.demandSumm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.DemandSummService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class GetDemandSummaryAction extends MainAction implements Controller {
	
	private DemandSummService demandSummService;
	
	public void setDemandSummService(DemandSummService demandSummService) {
		this.demandSummService = demandSummService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String customerId = req.getParameter("g_customId");
		String goodsNm = req.getParameter("goodsNm");
		String kindCode = req.getParameter("kindCode");
		if(StringUtils.isEmpty(customerId)){
			customerId="-1";
		}
		if(StringUtils.isEmpty(goodsNm)){
			goodsNm="";
		}
		if(StringUtils.isEmpty(kindCode)){
			kindCode="-1";
		}
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result = demandSummService.getDemandSummary(stime,etime,
				user.getStructId(),customerId,goodsNm,kindCode);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}