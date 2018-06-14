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

public class GetSupplierGoodsDataAction extends MainAction implements Controller {
	private SupplierGoodsService supplierGoodsService;
	
	public void setSupplierGoodsService(SupplierGoodsService supplierGoodsService) {
		this.supplierGoodsService = supplierGoodsService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String name = req.getParameter("name");
		String kindCode = req.getParameter("kindCode");
		String leftPage = req.getParameter("leftPage");
		String rightPage = req.getParameter("rightPage");
		String supplierId = req.getParameter("supplierId");
		String goodsCode = req.getParameter("goodsCode");
		if(StringUtils.isEmpty(goodsCode))
			goodsCode="";
		if (name==null)
			name="";
		if(StringUtils.isEmpty(kindCode))
			kindCode="";
		if(StringUtils.isEmpty(leftPage))
			leftPage="1";
		if(StringUtils.isEmpty(supplierId))
			supplierId="-1";
		if(StringUtils.isEmpty(rightPage))
			rightPage="1";
		int structId=(Integer)SessionUtil.getSession().getAttribute("structId");
		Result result = supplierGoodsService.getSupplierGoodsData(name,kindCode,leftPage,
				supplierId,rightPage,structId,goodsCode);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}