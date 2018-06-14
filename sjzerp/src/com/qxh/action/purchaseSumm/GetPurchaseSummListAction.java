package com.qxh.action.purchaseSumm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.PurchaseSummService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : GetPurchaseSummListAction
 * @todo : [采购汇总列表显示]
 * 
 * 创建时间 ： 2016年12月2日上午11:14:56
 */
public class GetPurchaseSummListAction extends MainAction implements Controller{

	private PurchaseSummService purchaseSummService;
	
	public void setPurchaseSummService(PurchaseSummService purchaseSummService) {
		this.purchaseSummService = purchaseSummService;
	}

	/* 
	 * Todo : [采购汇总列表显示]
	 * @时间:2016年12月2日上午11:15:54
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String customerId = req.getParameter("g_customId");
		String goodsNm = req.getParameter("goodsNm");
		String kindCode = req.getParameter("goodsKind");
		String suppleirId = req.getParameter("supplierId");
		if(StringUtils.isEmpty(kindCode)){
			kindCode ="-1";
		}
		if(StringUtils.isEmpty(suppleirId)){
			suppleirId ="-2";
		}
		
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result = purchaseSummService.getPurchaseSummList(stime,etime,
				user.getStructId(),user.getRoleId(),customerId,goodsNm,kindCode,suppleirId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
