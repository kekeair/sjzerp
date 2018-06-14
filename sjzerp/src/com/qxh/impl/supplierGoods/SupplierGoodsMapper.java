package com.qxh.impl.supplierGoods;

import java.util.List;
import java.util.Map;

import com.qxh.exmodel.AvailableGoods;
import com.qxh.model.SupplierGoods;

public interface SupplierGoodsMapper {

	/**
	 * 查询餐饮中心物料
	 * @param param
	 * @return
	 */
	List<AvailableGoods> getCenterAvaiGoods(Map<String, Object> param);
	/**
	 * 查询餐饮中心物料数量
	 * @param param
	 * @return
	 */
	int getCenterAvaiGoodsNum(Map<String, Object> param);
	/**
	 * 查询供应商物料
	 * @param param
	 * @return
	 */
	List<SupplierGoods> getSupplierGoods(Map<String, Object> param);
	/**
	 * 查询供应商物料数量
	 * @param param
	 * @return
	 */
	int getSupplierGoodsNum(Map<String, Object> param);
	/**
	 * 添加供应商提供的物料
	 * @param param
	 */
	void addSupplierGoods(Map<String, Object> param);
	/**
	 * 删除供应商提供的物料
	 * @param param
	 */
	void delSupplierGoods(Map<String, Object> param);
	/**
	 * @description : [批量查询]
	 * @param param
	 * @return
	 * @时间:2016年12月1日下午5:53:15
	 */
	List<AvailableGoods> addAllGoodsList(Map<String, Object> param);
	/**
	 * @description : [批量添加]
	 * @param param
	 * @时间:2016年12月1日下午5:53:27
	 */
	void addAllCenterGoods(Map<String, Object> param);

	
}
