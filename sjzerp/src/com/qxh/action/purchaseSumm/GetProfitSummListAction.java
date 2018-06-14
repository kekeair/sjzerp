package com.qxh.action.purchaseSumm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.PurchaseSummService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;


/**

 * @Description:[单品毛利汇总查询列表]

 * @author:kekeair

 * @time:2017年3月6日 上午9:06:11

 */
public class GetProfitSummListAction extends MainAction implements Controller{

	private PurchaseSummService purchaseSummService;
	
	public void setPurchaseSummService(PurchaseSummService purchaseSummService) {
		this.purchaseSummService = purchaseSummService;
	}

	/* 
	 * Todo : [单品毛利汇总查询]
	 * @时间:2017年3月6日上午11:15:54
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String customerIds = req.getParameter("customerIds");
		String goodsNm = req.getParameter("goodsNm");
		String kindCode =req.getParameter("kindCode");
		if(StringUtils.isEmpty(goodsNm)){
			goodsNm = "";
		}
		if(StringUtils.isEmpty(customerIds)){
			customerIds = "-1";
		}
		if(StringUtils.isEmpty(kindCode)){
			kindCode="-1";
		}
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result = purchaseSummService.getProfitSummDList(customerIds, stime, etime, user.getStructId(),goodsNm,kindCode);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
