package com.qxh.action.stockFix;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.service.StockFixService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class UpdateStockFixDAction extends MainAction implements Controller{
	
	private StockFixService stockFixService;
	
	public void setStockFixService(StockFixService stockFixService) {
		this.stockFixService = stockFixService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String goodsNum = req.getParameter("goodsNum");
		Result resultError=new Result();
		try {
			Double.parseDouble(goodsNum);
		} catch (Exception e) {
			resultError.setCode(CodeConstant.CODE200);
			resultError.setMsg("请输入正确数量!");
			return CheckUtil.returnResult(mav, resultError.getCode(), resultError.getMsg(),"");
		}
		String price = req.getParameter("price");
		try {
			Double.parseDouble(price);
		} catch (Exception e) {
			resultError.setCode(CodeConstant.CODE200);
			resultError.setMsg("请输入正确的价格!");
			return CheckUtil.returnResult(mav, resultError.getCode(), resultError.getMsg(),"");
		}
		String stockFixDId = req.getParameter("stockFixDId");
//		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result = stockFixService.updateStockFixD(goodsNum,price,stockFixDId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
