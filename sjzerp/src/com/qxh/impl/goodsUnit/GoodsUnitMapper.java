package com.qxh.impl.goodsUnit;

import java.util.List;
import java.util.Map;

import com.qxh.model.UnitSet;

public interface GoodsUnitMapper {

	/**
	 * 查询单位列表
	 * @param param
	 * @return
	 */
	List<UnitSet> getUnitList(Map<String, Object> param);
	/**
	 * 查询单位总数
	 * @param param
	 * @return
	 */
	int getUnitCount(Map<String, Object> param);
	/**
	 * 判断单位名是否重复
	 * @param param
	 * @return
	 */
	int checkUnitNm(Map<String, Object> param);
	/**
	 * 新增物料单位
	 * @param param
	 */
	void addGoodsUnit(Map<String, Object> param);
	/**
	 * 修改物料单位
	 * @param param
	 */
	void updateGoodsUnit(Map<String, Object> param);
	/**
	 * 判断单位是否已被使用
	 * @param param
	 * @return
	 */
	int checkUnitUsed(Map<String, Object> param);
	/**
	 * 更新关联物料
	 * @param param
	 */
	void updateRelGoods(Map<String, Object> param);
	
	
}
