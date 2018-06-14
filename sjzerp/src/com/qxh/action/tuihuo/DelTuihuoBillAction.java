package com.qxh.action.tuihuo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.service.TuihuoService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class DelTuihuoBillAction extends MainAction implements Controller {
	private TuihuoService tuihuoService;
	
	public void setTuihuoService(TuihuoService tuihuoService) {
		this.tuihuoService = tuihuoService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String billId = req.getParameter("billId");
		Result result=new Result();
		try {
			result = tuihuoService.delTuihuoBill(billId);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}