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
 * @author Mr chen
 * @name : GetRedisGoodsPriceListBykeyAction
 * @todo : [获取物料价格的集合]
 * 
 * 创建时间 ： 2017年1月10日上午10:22:57
 */
public class GetRedisGoodsPriceListBykeyAction extends MainAction implements Controller{

	private DemandService demandService;
	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}
	
	/* 
	 * Todo : [获取物料价格的集合]
	 * @时间:2017年1月10日上午10:22:57
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		String redisGoodsIds = req.getParameter("redisGoodsIds");
		String customerId = req.getParameter("customerId");
		User user = (User)SessionUtil.getSession().getAttribute("user");
		Result result = demandService.getRedisGoodsPriceListBykey(user.getStructId(),customerId,redisGoodsIds);
		
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}
