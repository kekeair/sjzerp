package com.qxh.impl.common;

import java.util.List;
import java.util.Map;

import com.qxh.exmodel.AppyDemandGoods;
import com.qxh.exmodel.BaseGoods;
import com.qxh.exmodel.CommonModel;
import com.qxh.exmodel.ListGoods;
import com.qxh.model.Area;
import com.qxh.model.GoodsKind;
import com.qxh.model.ReviewProcess;
import com.qxh.model.UnitSet;

public interface CommonMapper {
	
	/**
	 * 获取城市列表
	 * @param param
	 * @return
	 */
	List<Area> getCityList(Map<String, Object> param);
	
	/**
	 * 获取县列表
	 * @param param
	 * @return
	 */
	List<Area> getCountyList(Map<String, Object> param);

	/**
	 * 获取省列表
	 * @return
	 */
	List<Area> getProvinceList();
	/**
	 * 获取组织架构列表
	 * @return
	 */
	List<CommonModel> getStructList();
	/**
	 * 获取角色列表
	 * @return
	 */
	List<CommonModel> getRoleList();
	/**
	 * 查询物料编码
	 * @return
	 */
	List<GoodsKind> getGoodsKind();
	/**
	 * 查询单位列表
	 * @param param
	 * @return
	 */
	List<UnitSet> getUnitList();
	/**
	 * 物料列表
	 * @param param
	 * @return
	 */
	List<ListGoods> getGoodsList(Map<String, Object> param);
	/**
	 * 物料数量
	 * @param param
	 * @return
	 */
	int getGoodsCount(Map<String, Object> param);
	/**
	 * 供应商列表
	 * @param param
	 * @return
	 */
	List<CommonModel> getSupplier(Map<String, Object> param);
	/**
	 * 查询作业组列表
	 * @param param
	 * @return
	 */
	List<CommonModel> getTeamList(Map<String, Object> param);
	/**
	 * 查询审理流程
	 * @param param
	 * @return
	 */
	List<ReviewProcess> getReviewProcessList(Map<String, Object> param);
	/**
	 * 根据id查询组织架构编码
	 * @param param
	 * @return
	 */
	String getStructCodeById(Map<String, Object> param);
	/**
	 * 插入审批流程
	 * @param param
	 */
	void addReviewProcess(Map<String, Object> param);
	/**
	 * 根据id获取用户名
	 * @param userId
	 * @return
	 */
	String getUserNmById(String userId);
	/**
	 * 生成采购单头表
	 * @param param
	 */
	void addPurchaseListH(Map<String, Object> param);
	/**
	 * 生成采购详单
	 * @param param
	 */
	void addPurchaseListD(Map<String, Object> param);
	/**
	 * 根据id查询单位名称
	 * @param param
	 * @return
	 */
	String getUnitNmById(Map<String, Object> param);
	/**
	 * 查询作业组名称
	 * @param teamId
	 * @return
	 */
	String getTeamNmById(String teamId);
	/**
	 * 客户列表
	 * @param param
	 * @return
	 */
	List<CommonModel> getCustomerList(Map<String, Object> param);
	/**
	 * 根据id获取客户名
	 * @param customerId
	 * @return
	 */
	String getCustomerNmById(String customerId);
	/**
	 * 查询客户tagId
	 * @param param
	 * @return
	 */
	Integer getCustomerTagId(Map<String, Object> param);

	/**
	 * @description : [通过客户id查询tagId]
	 * @param param
	 * @return
	 * @时间:2016年12月20日下午6:01:07
	 */
	int selectTagIdByCustomerId(Map<String, Object> param);
	/**
	 * 插入退库退货流程
	 * @param param
	 */
	void addReturnProcess(Map<String, Object> param);
	/**
	 * 退库退货审批流程
	 * @param param
	 * @return
	 */
	List<ReviewProcess> getReturnProcessList(Map<String, Object> param);
	/**
	 * 查询所有配送中心物料(出库)
	 * @param param
	 * @return
	 */
	List<BaseGoods> getCenterGoods_demand(Map<String, Object> param);

	/**
	 * @description : [获取物料的集合]
	 * @return
	 * @时间:2016年12月26日下午2:47:59
	 */
	List<AppyDemandGoods> getCenterGoodsList();
	/**
	 * 根据单据id查询客户id
	 * @param param
	 * @return
	 */
	Integer getCustomerIdByBillId(Map<String, Object> param);
	/**
	 * 查询所有配送中心物料(入库)
	 * @param param
	 * @return
	 */
	List<BaseGoods> getCenterGoods_purchase(Map<String, Object> param);
}
