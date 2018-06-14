package com.qxh.service;

import java.util.List;

import com.qxh.model.SupplierGoods;
import com.qxh.utils.Result;

public interface GoodsPriceService {

	/**
	 * 物料价格列表
	 * @param name
	 * @param code
	 * @param kindId
	 * @param page
	 * @return
	 */
	Result getGoodsPrice(String name, String code, String kindCode, String page,
			String supplierId,int structId);
	/**
	 * 修改物料价格(批量)
	 * @param priceJson
	 * @return
	 */
	Result editGoodsPrice(String priceJson);
	/**
	 * 物料价格列表导出
	 * @param name
	 * @param code
	 * @param kindId
	 * @return
	 */
	Result getGoodsPriceOutExcel(String name, String code, String kindCode,
			String supplierId,int structId);
	/**
	 * 导入的物料价格修改(批量)
	 * @param list
	 * @return
	 */
	Result editGoodsPriceByIn(List<SupplierGoods> list);
	
}
