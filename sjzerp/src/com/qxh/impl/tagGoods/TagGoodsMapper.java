package com.qxh.impl.tagGoods;

import java.util.List;
import java.util.Map;

import com.qxh.model.CenterGoods;
import com.qxh.model.TagGoods;

public interface TagGoodsMapper {

	/**
	 * 查询物料库
	 * @param param
	 * @return
	 */
	List<TagGoods> getLeftGoodsList(Map<String, Object> param);
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
	 * 餐饮中心添加物料addTagGoods
	 * @param param
	 */
	void addTagGoods(Map<String, Object> param);
	/**
	 * 餐饮中心删除物料
	 * @param param
	 */
	void delTagGoods(Map<String, Object> param);
	
	
	
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
	 * @description : [修改价格]
	 * @param param
	 * @时间:2016年11月19日下午3:46:19
	 */
	void editTagGoods(Map<String, Object> param);
	/**
	 * @description : [批量查询]
	 * @param param
	 * @return
	 * @时间:2016年12月1日下午8:34:29
	 */
	List<TagGoods> addAllGoodsList(Map<String, Object> param);
	/**
	 * @description : [批量添加]
	 * @param param
	 * @时间:2016年12月1日下午8:34:51
	 */
	void addAllTagGoods(Map<String, Object> param);
	/**
	 * @description : [通过标签id查找标签价格集合]
	 * @param param
	 * @return
	 * @时间:2017年1月11日下午6:20:31
	 */
	List<TagGoods> getTagGoodsPriceListByTagId(Map<String, Object> param);
	/**
	 * @description : [标签价格的导入功能]
	 * @param param
	 * @时间:2017年1月12日上午8:41:19
	 */
	void updateTagGoodsPriceAtNo(Map<String, Object> param);
}
