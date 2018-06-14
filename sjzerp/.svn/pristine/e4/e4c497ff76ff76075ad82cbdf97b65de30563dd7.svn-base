package com.qxh.action.demand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.DemandService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * 
 * @Description:TODO
 * 
 * @author:[保存出库修改的做账单信息]
 * 
 * @time:2017年3月20日 上午11:52:47
 * 
 */
public class SaveMakeBillAction extends MainAction implements Controller {

	private DemandService demandService;

	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		// 获取参数值
		String billId = request.getParameter("billId"); // 账单id
		String customerId = request.getParameter("customerId");// 顾客Id
		String customerTeamId = request.getParameter("customerTeamId");// 作业组ID
		String delAtNo = request.getParameter("delAtNo");
		String editListStr = request.getParameter("editList");
		String newListStr = request.getParameter("newList");
		User user = (User) SessionUtil.getSession().getAttribute("user");
		Result result = demandService.saveMakeBill(billId, user.getStructId(), customerId, customerTeamId, delAtNo,editListStr,
				newListStr);

		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(), result.getData());
	}

}
