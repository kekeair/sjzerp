package com.qxh.action.supplierGoods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.service.SupplierGoodsService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : AddAllSupplierGoodsAction
 * @todo : [批量添加]
 * 
 * 创建时间 ： 2016年12月1日下午5:15:39
 */
public class AddAllSupplierGoodsAction extends MainAction implements Controller{
	private  SupplierGoodsService supplierGoodsService;
	
	public void setSupplierGoodsService(SupplierGoodsService supplierGoodsService) {
		this.supplierGoodsService = supplierGoodsService;
	}

	/* 
	 * Todo : [批量添加]
	 * @时间:2016年12月1日下午5:16:05
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		String name = req.getParameter("name");
		String kindCode = req.getParameter("kindCode");
		String goodsCode = req.getParameter("goodsCode");
		String supplierId = req.getParameter("supplierId");
		if (name==null)
			name="";
		if(StringUtils.isEmpty(kindCode))
			kindCode="";
		if(StringUtils.isEmpty(supplierId)){
			return CheckUtil.returnResult(mav, CodeConstant.CODE200, 
					"请先添加供应商","");
		}
		if(StringUtils.isEmpty(goodsCode))
			goodsCode="";
		int structId=(Integer)SessionUtil.getSession().getAttribute("structId");
		Result result = supplierGoodsService.addAllGoodsList(name,kindCode,structId,goodsCode,supplierId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}
