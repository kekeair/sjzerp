package com.qxh.impl.goodsPrice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.qxh.constant.CodeConstant;
import com.qxh.model.SupplierGoods;
import com.qxh.service.GoodsPriceService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.IPageConstants;
import com.qxh.utils.PageUtil;
import com.qxh.utils.Result;

public class GoodsPriceServiceImpl implements GoodsPriceService {

	Logger log = Logger.getLogger(this.getClass());
	private GoodsPriceMapper goodsPriceMapper;

	public void setGoodsPriceMapper(GoodsPriceMapper goodsPriceMapper) {
		this.goodsPriceMapper = goodsPriceMapper;
	}

	/**
	 * 物料价格列表
	 */
	@Override
	public Result getGoodsPrice(String name, String code, String kindCode, String page, String supplierId,
			int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");

		// ======去掉name中是否"(")"=======================
		StringBuilder builder = new StringBuilder();
		StringBuilder builderLast = new StringBuilder();
		if (StringUtils.isNotEmpty(name)) {
			String[] split = name.split("[(]");
			for (String string : split) {
				builder.append(string);
			}

			String[] lastArray = builder.toString().split("[)]");
			for (String string : lastArray) {
				builderLast.append(string);
			}
			name = builderLast.toString();
		}
		// ======去掉name中是否"(")"=======================
		
		Map<String, Object> param = new HashMap<>();
		param.put("name", name);
		param.put("code", code);
		param.put("kindCode", kindCode);
		param.put("page", (Integer.parseInt(page) - 1) * IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("supplierId", supplierId);
		param.put("structId", structId);
		try {
			List<SupplierGoods> goodsPriceList = goodsPriceMapper.getGoodsPrice(param);
			int totalCount = 0, totalPage = 1;
			if (goodsPriceList != null && goodsPriceList.size() > 0) {
				int l = goodsPriceList.size();
				int orderIndex = (Integer.parseInt(page) - 1) * IPageConstants.PageSize + 1;
				for (int i = 0; i < l; i++) {
					goodsPriceList.get(i).setOrderIndex(orderIndex);
					orderIndex++;
				}
				totalCount = goodsPriceMapper.getGoodsPriceNum(param);
				totalPage = PageUtil.getTotalPage(totalCount, IPageConstants.PageSize);
			}
			Map<String, Object> data = new HashMap<>();
			data.put("goodsPriceList", goodsPriceList);
			data.put("totalPage", totalPage);
			data.put("totalCount", totalCount);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 物料价格列表：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + "page:" + page
					+ ",kindCode:" + kindCode + ",code:" + code + ",name:" + name + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 修改物料价格
	 */
	@Override
	public Result editGoodsPrice(String priceJson) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		try {
			List<SupplierGoods> priceList = JSON.parseArray(priceJson, SupplierGoods.class);
			String idStr = "";
			if (priceList != null && priceList.size() > 0) {
				int l = priceList.size();
				for (int i = 0; i < l; i++) {
					idStr += priceList.get(i).getAtNo() + ",";
				}
				if (idStr.length() > 0)
					idStr = idStr.substring(0, idStr.length() - 1);
				param.put("idStr", idStr);
				param.put("priceList", priceList);
				goodsPriceMapper.updateGoodsPrice(param);
				goodsPriceMapper.insertGoodsPriceRecord(param);
			}
		} catch (Exception e) {
			log.error("\r\n 修改物料价格：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + "priceJson:"
					+ priceJson + "\r\n\r\n");
			throw new RuntimeException();
		}
		return result;
	}
  /**
   * 物料价格列表导出
   * */
	@Override
	public Result getGoodsPriceOutExcel(String name, String code, String kindCode, String supplierId, int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");

		// ======去掉name中是否"(")"=======================
		StringBuilder builder = new StringBuilder();
		StringBuilder builderLast = new StringBuilder();
		if (StringUtils.isNotEmpty(name)) {
			String[] split = name.split("[(]");
			for (String string : split) {
				builder.append(string);
			}

			String[] lastArray = builder.toString().split("[)]");
			for (String string : lastArray) {
				builderLast.append(string);
			}
			name = builderLast.toString();
		}
		// ======去掉name中是否"(")"=======================
		
		Map<String, Object> param = new HashMap<>();
		param.put("name", name);
		param.put("code", code);
		param.put("kindCode", kindCode);
		param.put("supplierId", supplierId);
		param.put("structId", structId);
		try {
			List<SupplierGoods> goodsPriceList = goodsPriceMapper.getGoodsPriceOutExcel(param);
			result.setData(goodsPriceList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 物料价格列表：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" 
					+ ",kindCode:" + kindCode + ",code:" + code + ",name:" + name + "\r\n\r\n");
		}
		return result;
	}
/**
 * 导入物料价格修改（批量）
 * */
@Override
public Result editGoodsPriceByIn(List<SupplierGoods> list) {
	Result result = new Result();
	result.setCode(CodeConstant.CODE1000);
	result.setMsg("成功");
	Map<String, Object> param = new HashMap<>();
	try {
		//拼接插入的條件
		StringBuilder idStr = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			int atNo = list.get(i).getAtNo();
			if(i<list.size()-1){
				idStr.append(atNo).append(",");
			}else{
				idStr.append(atNo);
			}
		}
			param.put("idStr", idStr);
			param.put("priceList", list);
			goodsPriceMapper.updateGoodsPriceIn(param);

	} catch (Exception e) {
		e.printStackTrace();
		System.err.println(e.getMessage());
		log.error("\r\n 修改物料价格：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + "priceJson:"
				+ list + "\r\n\r\n");
		throw new RuntimeException();
	}
	return result;
}

}
