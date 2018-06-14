package com.qxh.action.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.service.PurchaseService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class PurchaseTotalList extends MainAction implements Controller{

		private PurchaseService purchaseService;
		
		public void setPurchaseService(PurchaseService purchaseService) {
			this.purchaseService = purchaseService;
		}
		
		@Override
		public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
			ModelAndView mav = new ModelAndView();
			String centerId = req.getParameter("centerId");
			String code = req.getParameter("code");
			String userNm = req.getParameter("userNm");
			String page = req.getParameter("page");
			//User user=(User)SessionUtil.getSession().getAttribute("user");
			Result result = purchaseService.getPurchaseTotalList(centerId, code, userNm,page);
			return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
					result.getData());
		}
	}
