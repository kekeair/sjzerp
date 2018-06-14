package com.qxh.action.demand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.BillAndCustomTeam;
import com.qxh.service.DemandService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class GetGoodsByTermAction extends MainAction implements Controller {
	private DemandService demandService;
	
	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String billId = req.getParameter("teamBillId");
		String teamId = req.getParameter("customerTeamId");
		String codes = req.getParameter("codes");
		//按条件查询物料
		Result result = new Result();
		List<BillAndCustomTeam> goodsByTerm = demandService.getGoodsByTerm(billId, teamId, codes);
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("请求成功!");
		result.setData(goodsByTerm);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}