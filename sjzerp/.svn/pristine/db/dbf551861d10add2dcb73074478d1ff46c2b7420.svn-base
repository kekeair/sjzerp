package com.qxh.action.supplierGoods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.service.SupplierGoodsService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class GetSupplierGoodsAction extends MainAction implements Controller {
	private SupplierGoodsService supplierGoodsService;
	
	public void setSupplierGoodsService(SupplierGoodsService supplierGoodsService) {
		this.supplierGoodsService = supplierGoodsService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String rightPage = req.getParameter("rightPage");
		String supplierId = req.getParameter("supplierId");
		if(StringUtils.isEmpty(supplierId))
			supplierId="-1";
		if(StringUtils.isEmpty(rightPage))
			rightPage="1";
		int structId=(Integer)SessionUtil.getSession().getAttribute("structId");
		Result result = supplierGoodsService.getSupplierGoods(supplierId,rightPage,structId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}