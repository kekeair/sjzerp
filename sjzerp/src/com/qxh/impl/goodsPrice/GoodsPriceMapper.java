package com.qxh.impl.goodsPrice;

import java.util.List;
import java.util.Map;

import com.qxh.model.SupplierGoods;

public interface GoodsPriceMapper {

	/**
	 * 物料价格列表
	 * @param param
	 * @return
	 */
	List<SupplierGoods> getGoodsPrice(Map<String, Object> param);
	/**
	 * 查询物料价格列表数量
	 * @param param
	 * @return
	 */
	int getGoodsPriceNum(Map<String, Object> param);
	/**
	 * 修改物料价格
	 * @param priceList
	 */
	void updateGoodsPrice(Map<String, Object> param);
	/**
	 * 插入物料价格记录
	 * @param param
	 */
	void insertGoodsPriceRecord(Map<String, Object> param);
	/**
	 * 物料价格列表
	 * @param param
	 * @return
	 */
	List<SupplierGoods> getGoodsPriceOutExcel(Map<String, Object> param);
	/**
	 * 为导入做物料查询列表
	 * @param param
	 * @return
	 */
	List<SupplierGoods> getGoodsPriceIn(Map<String, Object> param);
	/**
	 * 为导入做的批量更细
	 * */
	void updateGoodsPriceIn(Map<String, Object> param);
}
