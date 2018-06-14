package com.qxh.action.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.PurchaseService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;
/**
 * 
 * @author Mr chen
 * @name : GetSupplierSelExistAction
 * 
 * 创建时间 ： 2016年11月29日上午8:47:29
 */
public class GetSupplierSelExistAction extends MainAction implements Controller {
	
	private PurchaseService purchaseService;
	
	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String billId=req.getParameter("billId");
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result = purchaseService.getSuppliserSelExist(billId,user.getStructId());
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}