package com.qxh.impl.customer;

import java.util.List;
import java.util.Map;

import com.qxh.model.Customer;
import com.qxh.model.User;

public interface CustomerMapper {

	/**
	 * 客户列表
	 * @param param
	 * @return
	 */
	List<Customer> getCustomerList(Map<String, Object> param);
	
	/**
	 * 
	 * @description : [计算总记录数]
	 * @param param
	 * @return
	 * @时间:2016年11月16日下午2:22:38
	 */
	int getCustomerCount(Map<String, Object> param);

	/**
	 * @description : [添加客户]
	 * @param param
	 * @时间:2016年11月17日上午8:10:58
	 */
	void addCustomer(Map<String, Object> param);

	/**
	 * @description : [删除客户]
	 * @param param
	 * @时间:2016年11月17日上午9:29:24
	 */
	void delCustomer(Map<String, Object> param);

	/**
	 * @description : [通过id查询客户对象]
	 * @param param
	 * @return
	 * @时间:2016年11月17日上午10:53:31
	 */
	Customer getCustomerById(Map<String, Object> param);

	/**
	 * @description : [通过id修改客户]
	 * @param param
	 * @时间:2016年11月17日下午12:54:01
	 */
	void editCustomer(Map<String, Object> param);

	/**
	 * @description : [修改餐厅等级]
	 * @param param
	 * @时间:2016年11月21日上午10:45:46
	 */
	void editCustomTag(Map<String, Object> param);

	/**
	 * @description : [通过strutsId获取客户名称]
	 * @param structId
	 * @return
	 * @时间:2016年12月8日下午6:01:51
	 */
	List<Customer> getCustomByStrustId(Map<String, Object> param);

	/**
	 * @description : [获取业务员列表]
	 * @param param
	 * @return
	 * @时间:2016年12月21日上午11:09:18
	 */
	List<User> getCountermanList(Map<String, Object> param);

	
}
