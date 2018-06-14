package com.qxh.impl.goods;

import java.util.Map;

import com.qxh.model.Goods;


public interface GoodsMapper {
	
	/**
	 * 查询当前最大物料编号
	 * @param goods
	 * @return
	 */
	String getMaxGoodsCode(Goods goods);
	/**
	 * 查询物料分类编号
	 * @param goods
	 * @return
	 */
	String getGoodsKindCode(Goods goods);
	/**
	 * 添加物料
	 * @param goods
	 */
	void addGoods(Goods goods);
	/**
	 * 添加物料详细属性
	 * @param goods
	 */
	void addGoodsDetail(Goods goods);
	/**
	 * 根据id查询物料
	 * @param param
	 * @return
	 */
	Goods getGoodsById(Map<String, Object> param);
	/**
	 * 根据物料id查询分类id
	 * @param goods
	 * @return
	 */
	int getKindIdByGoodsId(Goods goods);
	/**
	 * 修改物料
	 * @param goods
	 */
	void editGoods(Goods goods);
	/**
	 * 编辑物料详情
	 * @param goods
	 */
	void editGoodsDetail(Goods goods);
	/**
	 * 查询单位名称
	 * @param baseUnit
	 * @return
	 */
	String getUnitNm(String baseUnit);

	
}
