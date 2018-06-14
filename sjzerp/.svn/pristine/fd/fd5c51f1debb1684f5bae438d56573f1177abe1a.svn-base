package com.qxh.action.goods;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.service.GoodsService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

public class AddGoodsAction extends MainAction implements Controller {
	
	private GoodsService goodsService;
	
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		String goodsId = req.getParameter("goodsId");
		String code = req.getParameter("code");
		String goodsNm = req.getParameter("goodsNm");
		String alias = req.getParameter("alias");
		String kindId = req.getParameter("kindId");
		String baseUnit = req.getParameter("baseUnit");
		String shortNm = req.getParameter("shortNm");
		String tradiNm = req.getParameter("tradiNm");
		String illegal = req.getParameter("illegal");
		String spec = req.getParameter("spec");
		String groupStandardCode = req.getParameter("groupStandardCode");
		String assistAttr = req.getParameter("assistAttr");
		String assistUnit = req.getParameter("assistUnit");
		String sequenceUnit = req.getParameter("sequenceUnit");
		String helpCode = req.getParameter("helpCode");
		String barCode = req.getParameter("barCode");
		String approvalNumber = req.getParameter("approvalNumber");
		String picNumber = req.getParameter("picNumber");
		String weightUnit = req.getParameter("weightUnit");
		String lengthUnit = req.getParameter("lengthUnit");
		String grossWeight = req.getParameter("grossWeight");
		String netWeight = req.getParameter("netWeight");
		String length = req.getParameter("length");
		String width = req.getParameter("width");
		String height = req.getParameter("height");
		String volumeUnit = req.getParameter("volumeUnit");
		String volume = req.getParameter("volume");
		String equipAttr = req.getParameter("equipAttr");
		String tradeMark = req.getParameter("tradeMark");
		String brand = req.getParameter("brand");
		String depict = req.getParameter("depict");
		String keyword = req.getParameter("keyword");
		String remark = req.getParameter("remark");
		String summary = req.getParameter("summary");
		String firstLetter = req.getParameter("firstLetter");
		String spell = req.getParameter("spell");
		String englishNm = req.getParameter("englishNm");
		String foreighNm = req.getParameter("foreighNm");
		String groupId = req.getParameter("groupId");
		String criteria = req.getParameter("criteria");
		String minUnit = req.getParameter("minUnit");
		String minUnitNm = req.getParameter("minUnitNm");
		List<String> params = new ArrayList<>();
		params.add(goodsId);
		params.add(goodsNm);
		params.add(kindId);
		params.add(illegal);
		params.add(baseUnit);
		String checkResult = CheckUtil.checkParam(req, params);
		if (StringUtils.isEmpty(checkResult)) {
			return CheckUtil.returnResult(mav, CodeConstant.CODE500,"获取数据失败", "");
		}
		Result result=new Result();
		try {
			result = goodsService.addGoods(goodsId,code,goodsNm,alias,kindId,baseUnit,
					shortNm,tradiNm,illegal,spec,groupStandardCode,assistAttr,
					assistUnit,sequenceUnit,helpCode ,barCode ,approvalNumber ,
					picNumber,weightUnit,lengthUnit,grossWeight,netWeight,
					length,width,height,volumeUnit,volume,equipAttr,
					tradeMark,brand,depict,keyword,remark,summary,firstLetter,
					spell,englishNm,foreighNm,groupId,criteria,minUnit,minUnitNm);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			result.setData(null);
		}
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}