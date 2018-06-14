package com.qxh.action.demand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.BillAndCustomTeam;
import com.qxh.model.User;
import com.qxh.service.DemandService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**

 * @Description:[按作业组查看]

 * @author:kekeair

 * @time:2017年2月22日 上午9:52:55

 */
public class SelectDemandGoodsByTeamAction extends MainAction implements Controller{
	private DemandService demandService;
	
	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}
	
	/* 
	 * kekeair : [按作业组查看]
	 *  @time:2017年2月22日 上午9:52:55
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		String billId = request.getParameter("billId");
		//String type = request.getParameter("type");
		//String customerId = request.getParameter("customerId");
		User user = (User)SessionUtil.getSession().getAttribute("user");
		String teamId="";
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("获取数据成功");
		List<BillAndCustomTeam> demandGoods = demandService.getExportOutDemandBill(billId, teamId);
	    result.setData(demandGoods);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
