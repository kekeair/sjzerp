package com.qxh.action.customer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.constant.CodeConstant;
import com.qxh.service.CustomerService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : EditCustomTagAction
 * @todo : [修改等级标签]
 * 
 * 创建时间 ： 2016年11月21日上午10:24:43
 */
public class EditCustomTagAction implements Controller{

	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService= customerService;
	}
	/* 
	 * Todo : [修改等级标签]
	 * @时间:2016年11月21日上午10:25:27
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		String tagArray = request.getParameter("tagArray");
		String atNo = request.getParameter("myCustomAtNo");
		
        List<String> params = new ArrayList<>();
        params.add(tagArray);
        String checkResult = CheckUtil.checkParam(request, params);
		if (StringUtils.isEmpty(checkResult)) {
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		
		Result result=new Result();
		try {
				 customerService.editCustomTag(tagArray, atNo);
				 result.setCode(CodeConstant.CODE1000);
					result.setMsg("修改成功");
		}catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
