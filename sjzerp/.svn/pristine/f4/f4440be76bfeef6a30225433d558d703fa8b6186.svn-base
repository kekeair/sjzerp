package com.qxh.impl.centerGoods;

import java.util.List;
import java.util.Map;

import com.qxh.exmodel.ListGoods;
import com.qxh.model.CenterGoods;

public interface CenterGoodsMapper {

	/**
	 * 查询物料库
	 * @param param
	 * @return
	 */
	List<ListGoods> getLeftGoodsList(Map<String, Object> param);
	/**
	 * 物料库数量
	 * @param param
	 * @return
	 */
	int getLeftGoodsCount(Map<String, Object> param);
	/**
	 * 查询餐饮中心物料
	 * @param param
	 * @return
	 */
	List<CenterGoods> getCenterGoodsList(Map<String, Object> param);
	/**
	 * 查询餐饮中心物料数量
	 * @param param
	 * @return
	 */
	int getCenterGoodsCount(Map<String, Object> param);
	/**
	 * 餐饮中心添加物料
	 * @param param
	 */
	void addCenterGoods(Map<String, Object> param);
	/**
	 * 餐饮中心删除物料
	 * @param param
	 */
	void delCenterGoods(Map<String, Object> param);
	/**
	 * 关联删除供应商物料
	 * @param param
	 */
	void delSuppGoodsFromCenterGoods(Map<String, Object> param);
	/**
	 * 查询物料库存
	 * @param param
	 * @return
	 */
	Double getGoodsStock(Map<String, Object> param);
	/**
	 * @description : [批量添加查询]
	 * @param param
	 * @return
	 * @时间:2016年12月1日下午3:34:26
	 */
	List<ListGoods> addAllGoodsList(Map<String, Object> param);
	/**
	 * @description : [批量添加]
	 * @时间:2016年12月1日下午4:46:26
	 */
	void addAllCenterGoods(Map<String, Object> param);
}
