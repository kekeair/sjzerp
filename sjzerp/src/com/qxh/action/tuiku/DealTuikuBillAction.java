package com.qxh.action.tuiku;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.service.TuikuService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class DealTuikuBillAction extends MainAction implements Controller {
	private TuikuService tuikuService;
	
	public void setTuikuService(TuikuService tuikuService) {
		this.tuikuService = tuikuService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String billId = req.getParameter("billId");
		String reviewState = req.getParameter("reviewState");
		String billDate = req.getParameter("billDate");
		String operType = req.getParameter("operType");
		String remark = req.getParameter("remark");
		String newList = req.getParameter("newList");
		String editList = req.getParameter("editList");
		String delAtNo = req.getParameter("delAtNo");
		String customerId = req.getParameter("customerId");
		String customerNm = req.getParameter("customerNm");
		User user = (User)SessionUtil.getSession().getAttribute("user");
		if(StringUtils.isEmpty(billDate)){
			billDate = "";
		}else{
			Date date = new Date();
			String toStr = date.toString();
			String subDate = toStr.substring(10, 19);
			billDate = billDate+subDate;
		}
		Result result=new Result();
		try {
			result = tuikuService.dealTuikuBill(billId,reviewState,
					user.getStructId(),user.getAtNo(),operType,remark,
					user.getUserNm(),billDate,newList,editList,delAtNo,
					customerId,customerNm);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
		
	}
}