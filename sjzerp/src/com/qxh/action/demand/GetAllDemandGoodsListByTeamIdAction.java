package com.qxh.action.demand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.service.DemandService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : GetAllDemandGoodsListAction
 * @todo : [获取申请物料的列表]
 * 
 * 创建时间 ： 2017年1月5日下午3:27:45
 */
public class GetAllDemandGoodsListByTeamIdAction extends MainAction implements Controller{

	private DemandService demandService;
	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}
	
	/* 
	 * Todo : [获取申请物料的列表]
	 * @时间:2017年1月5日下午3:29:40
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		String teamDemandId = req.getParameter("teamDemandId");
		String teamId = req.getParameter("teamId");
		Result result = demandService.getAllDemandGoodsListByTeamId(teamDemandId,teamId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
