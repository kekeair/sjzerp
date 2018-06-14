package com.qxh.service;

import com.qxh.utils.Result;

public interface CommonService {

	/**
	 * 城市列表
	 * @param provinceId
	 * @return
	 */
	Result getCityList(String provinceId);
	/**
	 * 获取县列表
	 * @param cityId
	 * @return
	 */
	Result getCountyList(String cityId);
	/**
	 * 省列表
	 * @return
	 */
	Result getProvinceList();
	/**
	 * 查询组织架构和角色
	 * @return
	 */
	Result getStructAndRole();
	/**
	 * 查询市和县
	 * @param provinceId
	 * @param cityId
	 * @return
	 */
	Result getCityAndCounty(String provinceId, String cityId);
	/**
	 * 查询分类
	 * @return
	 */
	Result getGoodsKind();
	/**
	 * 查询单位
	 * @return
	 */
	Result getGoodsUnit();
	/**
	 * 供应商列表
	 * @param structId
	 * @return
	 */
	Result getSupplier(int structId);
	/**
	 * 查询审批流程
	 * @param billId
	 * @param billType
	 * @return
	 */
	Result getReviewProcess(String billId, String billType);
	/**
	 * 查询客户列表
	 * @param structId
	 * @return
	 */
	Result getCustomerList(int structId);
	/**
	 * 查询退库退货审批流程
	 * @param billId
	 * @param billType
	 * @return
	 */
	Result getReturnProcess(String billId, String billType);
	/**
	 * 查询所有配送中心物料
	 * @param structId
	 * @return
	 */
	Result getAllCenterGoods(int structId,String billId,String billType,
			String customerId);
	/**
	 * @description : [获取物料列表]
	 * @return
	 * @时间:2016年12月26日下午2:38:00
	 */
	Result getCenterGoodsList();
	
}
