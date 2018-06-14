package com.qxh.service;

import com.qxh.utils.Result;
/**
 * 
 * @author Mr chen
 * @name : CustomerService
 * @todo : [客户的service接口]
 * 
 * 创建时间 ： 2016年11月16日下午2:37:26
 */
public interface CustomerService {

	/**
	 * 
	 * @description : [客户管理:查询客户列表]
	 * @param structId 
	 * @param customerNm 客户名称
	 * @param page 页数
	 * @return
	 * @时间:2016年11月16日上午10:41:03
	 */
	Result getCustomerList(int structId,String customerNm,String page);
	
	/**
	 * 
	 * @description : [添加客户]
	 * @param customNm
	 * @param phone
	 * @param provinceId
	 * @param cityId
	 * @param countyId
	 * @param address
	 * @时间:2016年11月17日上午8:06:09
	 */
	void addCustomer(String customNm, String phone, String provinceId, String cityId, 
			String countyId, String address,int structId,String countermanId);

	/**
	 * @description : [删除客户]
	 * @param atNo
	 * @时间:2016年11月17日上午9:26:38
	 */
	void delCustomer(Integer atNo);

	/**
	 * @description : [通过id查询客户对象]
	 * @param atNo
	 * @return
	 * @时间:2016年11月17日上午10:49:19
	 */
	Result getCustomerById(String atNo);

	/**
	 * @description : [通过id修改对象]
	 * @param atNo
	 * @param customNm
	 * @param phone
	 * @param provinceId
	 * @param cityId
	 * @param countyId
	 * @param address
	 * @时间:2016年11月17日下午12:51:40
	 */
	void editCustomer(String atNo, String customNm, String phone, String provinceId, String cityId, String countyId,
			String address,String countermanId);

	/**
	 * @description : [修改标签]
	 * @param tagArray
	 * @时间:2016年11月21日上午10:33:58
	 */
	void editCustomTag(String tagArray,String atNo);

	/**
	 * @description : [通过strutsId获取客户名称]
	 * @param structId
	 * @return
	 * @时间:2016年12月8日下午5:59:25
	 */
	Result getCustomByStrustId(int structId);

	/**
	 * @description : [获取业务员列表]
	 * @param structId
	 * @return
	 * @时间:2016年12月21日上午11:06:34
	 */
	Result getCountermanList(int structId);

	
}
