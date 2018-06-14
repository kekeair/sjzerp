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
 * @name : StorageDataAction
 * @todo : [将出单的数据存入redis中]
 * 
 * 创建时间 ： 2017年1月10日下午8:39:31
 */
public class StorageDataAction extends MainAction implements Controller{
	
	private DemandService demandService;
	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}
	/* 
	 * Todo : [将出单的数据存入redis中]
	 * @时间:2017年1月10日下午8:39:43
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		//获取参数值
		String billId = request.getParameter("billId");     //账单id
		String customerTeamId = request.getParameter("customerTeamId");			//作业组ID
		String editListStr = request.getParameter("editList");
		String newListStr = request.getParameter("newList");
		
		Result result = demandService.storageDataAction(billId,customerTeamId,editListStr,newListStr);
		//mav.setViewName("excelOut/PrintOutDemand");
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
