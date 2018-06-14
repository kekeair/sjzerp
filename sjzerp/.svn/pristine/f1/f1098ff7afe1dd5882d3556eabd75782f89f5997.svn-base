package com.qxh.action.purchase;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.service.PurchaseService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class DealPurchaseBillAction extends MainAction implements Controller {
	private PurchaseService purchaseService;
	
	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String billId = req.getParameter("billId");
		String reviewState = req.getParameter("reviewState");
		String operType = req.getParameter("operType");
		String remark = req.getParameter("remark");
		String billDate = req.getParameter("billDate");
		String newList = req.getParameter("newList");
		String editList = req.getParameter("editList");
		String delAtNo = req.getParameter("delAtNo");
		String customerId = req.getParameter("customerId");
		String teamId = req.getParameter("teamId");
		if(StringUtils.isEmpty(billDate)){
			billDate="";
		}else{
			Date date = new Date();
			String toStr = date.toString();
			String subDate = toStr.substring(10, 19);
			billDate = billDate+subDate;
		}
		User user = (User)SessionUtil.getSession().getAttribute("user");
		Result result=new Result();
		try {
			result = purchaseService.dealPurchaseBill(billId,reviewState,
					user.getStructId(),user.getAtNo(),user.getUserNm(),operType,
					remark,billDate,newList,editList,delAtNo,
					customerId,teamId);	
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}