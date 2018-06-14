package com.qxh.action.tuihuo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.service.TuihuoService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class SaveTuihuoDAction extends MainAction implements Controller {
	private TuihuoService tuihuoService;
	
	public void setTuihuoService(TuihuoService tuihuoService) {
		this.tuihuoService = tuihuoService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String newList = req.getParameter("newList");
		String editList = req.getParameter("editList");
		String delAtNo = req.getParameter("delAtNo");
		String billId = req.getParameter("billId");
		String supplierId = req.getParameter("supplierId");
		String supplierNm = req.getParameter("supplierNm");
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result=new Result();
		try {
			result = tuihuoService.saveTuihuoD(newList,editList,delAtNo,
					billId,supplierId,supplierNm,user.getStructId(),user.getAtNo());
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}