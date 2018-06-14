package com.qxh.action.tagGoods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.constant.CodeConstant;
import com.qxh.service.TagGoodsService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : EditTagGoodsAction
 * @todo : [修改价格]
 * 
 * 创建时间 ： 2016年11月19日下午3:33:17
 */
public class EditTagGoodsAction implements Controller {

    private TagGoodsService tagGoodsService;
	
	public void setTagGoodsService(TagGoodsService tagGoodsService) {
		this.tagGoodsService = tagGoodsService;
	}
	/* 
	 * Todo : [修改价格]
	 * @时间:2016年11月19日下午3:34:19
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		String atNo = req.getParameter("atNo");
		String priceVal = req.getParameter("priceVal");
		Result result=new Result();
		
		
		if(StringUtils.isEmpty(priceVal)){
			result.setCode(CodeConstant.CODE200);
			result.setMsg("操作失败");
			return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),"");
		}
		try{
			Double.parseDouble(priceVal);
		}catch(Exception e){
			result.setCode(CodeConstant.CODE200);
			result.setMsg("请输入正确的价钱!");
			result.setData(null);
		}
		try {
			 tagGoodsService.editTagGoods(atNo,priceVal);
			 result.setMsg("修改成功!");
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("操作失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}

	
}
