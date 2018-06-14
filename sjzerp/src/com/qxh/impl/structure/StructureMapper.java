package com.qxh.impl.structure;

import java.util.List;
import java.util.Map;

import com.qxh.model.Structure;


public interface StructureMapper {
	
	/**
	 * 获取组织架构
	 * @return
	 */
	List<Structure> getStructure();
	/**
	 * 更新组织架构
	 * @param param
	 */
	void updateStructure(Map<String, Object> param);
	/**
	 * 更新餐饮中心属性
	 * @param param
	 */
	void updateDiningCenter(Map<String, Object> param);
	/**
	 * 添加架构
	 * @param param
	 */
	void addStructure(Map<String, Object> param);
	/**
	 * 添加餐饮中心
	 * @param param
	 */
	void addDiningCenter(Map<String, Object> param);
	/**
	 * 更新架构顺序
	 * @param param
	 */
	void updateStructOrder(Map<String, Object> param);
	/**
	 * 查询最大的架构编码
	 * @param param
	 * @return
	 */
	String getMaxStructCode(Map<String, Object> param);

	
}
