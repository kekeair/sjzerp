package com.qxh.action.purchaseSumm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.service.PurchaseSummService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**

 * @Description:[为数量统计图表查询当月每日此物料的数量]

 * @author:kekeair

 * @time:2017年3月16日 下午5:34:53

 */
public class getOrderNumAction extends MainAction implements Controller {
	private PurchaseSummService purchaseSummService;

	public void setPurchaseSummService(PurchaseSummService purchaseSummService) {
		this.purchaseSummService = purchaseSummService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String goodsId = req.getParameter("goodsId");
		String goodsType = req.getParameter("goodsType");
		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String demandListDId = req.getParameter("demandListDId");
		String purchaseListDId = req.getParameter("purchaseListDId");
		String customerIds = req.getParameter("customerIds");
		User user = (User) SessionUtil.getSession().getAttribute("user");
		Result result = purchaseSummService.getOrderNum(goodsId, goodsType, stime, etime, 
				user.getStructId(), demandListDId, purchaseListDId, customerIds);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(), result.getData());
	}

}
