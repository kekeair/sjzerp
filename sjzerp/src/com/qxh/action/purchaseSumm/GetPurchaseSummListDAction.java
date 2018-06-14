package com.qxh.action.purchaseSumm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.service.PurchaseSummService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : GetPurchaseSummListDAction
 * @todo : [出入库的详情列表]
 * 
 * 创建时间 ： 2016年12月2日下午1:53:41
 */
public class GetPurchaseSummListDAction extends MainAction implements Controller{

	private PurchaseSummService purchaseSummService;
	
	public void setPurchaseSummService(PurchaseSummService purchaseSummService) {
		this.purchaseSummService = purchaseSummService;
	}

	/* 
	 * Todo : [出入库的详情列表]
	 * @时间:2016年12月2日下午1:54:51
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		String goodsId = req.getParameter("goodsId");
		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String goodsType = req.getParameter("goodsType");
		String purchaselistDId = req.getParameter("purchaselistDId");
		String customerId = req.getParameter("customerId");
		
		User user=(User)SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user,CodeConstant.NAV_502,CodeConstant.NAV_5);
		Result result = purchaseSummService.getPurchaseSummDList(goodsId,stime,etime,goodsType,user.getStructId(),purchaselistDId,customerId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
