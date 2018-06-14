package com.qxh.action.tuiku;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.service.TuikuService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * 
 * @author Mr chen
 * @name : GetTuikuGoodsListByHeadAtNoAction
 * @todo : [查询退库列表]
 * 
 * 创建时间 ： 2017年1月6日下午3:15:45
 */
public class GetTuikuGoodsListByHeadAtNoAction extends MainAction implements Controller {
	private TuikuService tuikuService;
	
	public void setTuikuService(TuikuService tuikuService) {
		this.tuikuService = tuikuService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String billId = req.getParameter("billId");
		String customerId = req.getParameter("customerId");
		Result result = tuikuService.getTuikuGoodsListByHeadAtNo(billId,customerId);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}