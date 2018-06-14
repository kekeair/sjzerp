package com.qxh.action.tuiku;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.service.TuikuService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : SaveTuikuDAction
 * @todo : [保存退库列表]
 * 
 * 创建时间 ： 2017年1月6日下午5:18:34
 */
public class SaveTuikuDAction extends MainAction implements Controller {
	private TuikuService tuikuService;
	
	public void setTuikuService(TuikuService tuikuService) {
		this.tuikuService = tuikuService;
	}

	
	/* 
	 * Todo : [保存退库列表]
	 * @时间:2017年1月6日下午5:19:55
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		String newList = req.getParameter("newList");
		String editList = req.getParameter("editList");
		String delAtNo = req.getParameter("delAtNo");
		String billId = req.getParameter("billId");
		String customerId = req.getParameter("customerId");
		String customerNm = req.getParameter("customerNm");
		Result result=new Result();
		if(StringUtils.isEmpty(customerId)){
			result.setCode(CodeConstant.CODE200);
			result.setMsg("请先去添加客户");
			return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
					result.getData());
		}
		User user=(User)SessionUtil.getSession().getAttribute("user");
		try {
			result = tuikuService.saveTuikuD(user.getStructId(),user.getAtNo(),
					newList,editList,delAtNo,customerId,billId,customerNm);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("操作失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
	

}
