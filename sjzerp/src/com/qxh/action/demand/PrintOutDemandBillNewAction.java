package com.qxh.action.demand;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.exmodel.GetDemandListByCustomerId;
import com.qxh.service.DemandService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * 
 * @author Mr chen
 * @name : PrintOutDemandBillNewAction
 * @todo : [出库单详情]
 * 
 *       创建时间 ： 2017年1月9日上午10:02:12
 */
public class PrintOutDemandBillNewAction extends MainAction implements Controller {

	private DemandService demandService;

	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}

	/*
	 * Todo : [打印出库单详情]
	 * 
	 * @时间:2016年12月27日下午2:45:18
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		// 获取参数值
		String key = request.getParameter("key"); // 账单id
		Result result = new Result();
		ArrayList<GetDemandListByCustomerId> newList = demandService.getDataListByKey(key);

		result.setData(newList);
		mav.setViewName("excelOut/PrintOutDemand");
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(), result.getData());
	}

}
