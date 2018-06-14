package com.qxh.action.stockFix;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.service.StockFixService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class SelTmpGoodsAction extends MainAction implements Controller{
	
	private StockFixService stockFixService;
	
	public void setStockFixService(StockFixService stockFixService) {
		this.stockFixService = stockFixService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, 
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String goodsIdStr = req.getParameter("goodsIdStr");
		String billId=req.getParameter("billId");
		String name=req.getParameter("name");
		String kindCode=req.getParameter("kindCode");
		String page=req.getParameter("page");
		if(StringUtils.isEmpty(page))
			page="1";
		if(StringUtils.isEmpty(kindCode))
			kindCode="-1";
		if(name==null)
			name="";
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result=new Result();
		try {
			result = stockFixService.selTmpGoods(name,kindCode,page,goodsIdStr,
					user.getStructId(),billId,user.getAtNo(),user.getUserNm());
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
