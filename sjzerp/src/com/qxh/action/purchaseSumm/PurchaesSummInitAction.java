package com.qxh.action.purchaseSumm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.CommonModel;
import com.qxh.model.User;
import com.qxh.service.CommonService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * 
 * @author Mr chen
 * @name : PurchaesSummInitAction
 * @todo : [出入库的采购入库明细表]
 * 
 *       创建时间 ： 2016年12月2日上午10:14:40
 */
public class PurchaesSummInitAction extends MainAction implements Controller {
	private CommonService commonService;

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	/**
	 * @todo : [出入库的采购入库明细表]
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String goodsId = req.getParameter("gId");
		User user = (User) SessionUtil.getSession().getAttribute("user");
		getPowerRight(mav, user, CodeConstant.NAV_502, CodeConstant.NAV_5);
		if (StringUtils.isEmpty(goodsId))
			goodsId = "-1";
		Result result = commonService.getSupplier(user.getStructId());
		List<CommonModel> list = (List<CommonModel>) result.getData();
		CommonModel m = new CommonModel();
		m.setAtNo(-1);
		m.setName("自采");
		list.add(m);
		CommonModel m1 = new CommonModel();
		m1.setAtNo(-2);
		m1.setName("全部");
		list.add(m1);
		mav.addObject("userNm", user.getUserNm());
		mav.addObject("goodsId", goodsId);
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("inoutSumm/purchaseSumm");
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

}
