package com.qxh.impl.goodsKind;

import java.util.Map;


public interface GoodsKindMapper {

	/**
	 * 查询最大的分类编码
	 * @param param
	 * @return
	 */
	String getMaxGoodsKindCode(Map<String, Object> param);
	/**
	 * 添加物料分类
	 * @param param
	 */
	void addGoodsKind(Map<String, Object> param);
	/**
	 * 编辑物料分类名称
	 * @param param
	 */
	void editGoodsKindNm(Map<String, Object> param);
	/**
	 * 根据分类id查询物料数量
	 * @param param
	 * @return
	 */
	int getGoodsNumByKindId(Map<String, Object> param);
	/**
	 * 查询子节点数量
	 * @param param
	 * @return
	 */
	int getChildCount(Map<String, Object> param);
	/**
	 * 删除物料分类
	 * @param param
	 */
	void delGoodsKind(Map<String, Object> param);
	
	
}
