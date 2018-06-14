package com.qxh.action.demand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.GetDemandListByCustomerId;
import com.qxh.model.User;
import com.qxh.service.DemandService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;
/**
 * 
 * @author Mr chen
 * @name : GetDemandListByCustomerIdAction
 * @todo : [获取出库详情列表]
 * 
 * 创建时间 ： 2016年12月27日上午11:30:19
 */
public class GetDemandListByCustomerIdAction extends MainAction implements Controller {
	private DemandService demandService;
	
	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}
	
	/**
	 * 获取出库详情列表
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		Result result = new Result();
		String billId = req.getParameter("billId");
		String customerTeamId = req.getParameter("customerTeamId");
		if(StringUtils.isEmpty(customerTeamId)){
			customerTeamId="";
		}
		User user=(User)SessionUtil.getSession().getAttribute("user");
		
		try {
			List<GetDemandListByCustomerId> list = demandService.getDemandListByCustomerId(billId,customerTeamId);
			result.setData(list);
			result.setCode(CodeConstant.CODE1000);
			result.setMsg("成功");
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("失败");
			e.printStackTrace();
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}