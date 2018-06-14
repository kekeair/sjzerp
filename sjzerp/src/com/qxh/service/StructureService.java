package com.qxh.service;

import com.qxh.utils.Result;

public interface StructureService {

	/*
	 * 查询组织架构
	 */
	Result getStructure();

	/**
	 * 编辑组织架构
	 * @param atNo
	 * @param pId
	 * @param levelOrder
	 * @param name
	 * @param isCenter
	 * @param provinceId
	 * @param cityId
	 * @param countyId
	 * @return
	 */
	Result editStructure(String atNo, String pId, String pCode,String levelOrder, String name, String isCenter, String provinceId,
			String cityId, String countyId,String address);
	/**
	 * 更新架构顺序
	 * @param atNo
	 * @param pId
	 * @param levelOrder
	 * @return
	 */
	Result updateStructOrder(String atNo, String pId, String levelOrder);

}
