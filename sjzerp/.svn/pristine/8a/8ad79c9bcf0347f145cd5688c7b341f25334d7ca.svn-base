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
 * @name : SelectPurchaseGoodsBygroupAction
 * @todo : [按客户查看]
 * 
 * 创建时间 ： 2016年12月23日下午2:07:32
 */
public class SelectPurchaseGoodsBygroupAction extends MainAction implements Controller{
	private PurchaseService purchaseService;
	
	
	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
	
	/**
	 * 按客户查看
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		String billId = request.getParameter("billId");
		String type = request.getParameter("type");
		User user = (User)SessionUtil.getSession().getAttribute("user");
		
		Result result = purchaseService.selectPurchaseGoodsByGroup(billId,user.getStructId(),type);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
