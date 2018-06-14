package com.qxh.action.tmpGoodsRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.User;
import com.qxh.service.TmpGoodsRecordService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * @author Mr chen
 * @name : GetTmpGoodsRecordListAction
 * @todo : [获取列表]
 * 
 * 创建时间 ： 2016年11月23日上午9:43:27
 */
public class GetTmpGoodsRecordListAction extends MainAction implements Controller{

    private TmpGoodsRecordService tmpGoodsRecordService;
	
	
	public void setTmpGoodsRecordService(TmpGoodsRecordService tmpGoodsRecordService) {
		this.tmpGoodsRecordService = tmpGoodsRecordService;
	}

	/* 
	 * Todo : [获取列表]
	 * @时间:2016年11月23日上午9:43:47
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String page = req.getParameter("page");
		if(StringUtils.isEmpty(page))
			page="1";
		User user=(User)SessionUtil.getSession().getAttribute("user");
		Result result = tmpGoodsRecordService.getTmpGoodsRecordList(stime,etime,page,
				user.getStructId(),user.getRoleId());
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
