package com.qxh.action.demand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.DemandService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : SelectDemandGoodsByGoodsAction
 * @todo : [按物料查看]
 * 
 * 创建时间 ： 2016年12月22日上午10:05:12
 */
public class SelectDemandGoodsByGoodsAction extends MainAction implements Controller{
	private DemandService demandService;
	
	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}
	
	/* 
	 * Todo : [按物料查看]
	 * @时间:2016年12月22日上午10:06:10
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		String billId = request.getParameter("billId");
		String type = request.getParameter("type");
		String goodsNm = request.getParameter("goodsNm");
		String customerId = request.getParameter("customerId");
		if(StringUtils.isEmpty(goodsNm)){
			goodsNm="";
		}
		User user = (User)SessionUtil.getSession().getAttribute("user");
		
		Result result = demandService.selectDemandGoodsByGoods(billId,user.getStructId(),type,goodsNm,customerId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
