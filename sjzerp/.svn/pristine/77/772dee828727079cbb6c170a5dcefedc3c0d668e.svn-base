/**
 * 
 */
package com.qxh.service;

import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : CustomerteamService
 * @todo : [工作组]
 * 
 * 创建时间 ： 2016年11月17日下午2:42:42
 */
public interface CustomerteamService {

	/**
	 * @description : [通过客户id查询工作组列表]
	 * @param headAtNo
	 * @param teamNm
	 * @param page
	 * @return
	 * @时间:2016年11月17日下午2:43:40
	 */
	Result getCustomerList(String headAtNo, String teamNm, String page);

	/**
	 * @description : [添加工作组]
	 * @param teamNm
	 * @param headAtNo
	 * @时间:2016年11月17日下午5:26:28
	 */
	void addCustomerteam(String teamNm, String headAtNo);

	/**
	 * @description : [通过id获取工作组]
	 * @param atNo
	 * @return
	 * @时间:2016年11月17日下午6:20:08
	 */
	Result getCustomerteamById(String atNo);

	/**
	 * @description : [修改工作名称]
	 * @param atNo
	 * @param teamNm
	 * @时间:2016年11月17日下午6:31:29
	 */
	void editCustomerteam(String atNo, String teamNm);

	/**
	 * @description : [删除工作组]
	 * @param atNo
	 * @时间:2016年11月17日下午7:16:16
	 */
	void delCustomerteam(Integer atNo);

	/**
	 * @description : [通过账单id查询工作组]
	 * @param teamBillId
	 * @return
	 * @时间:2016年11月23日下午4:29:54
	 */
	Result getCustomTeamNameList(String teamBillId);

	/**
	 * @description : [通过采购单id查询工作组]
	 * @param teamBillId
	 * @return
	 * @时间:2016年11月25日上午9:01:51
	 */
	Result getCustomTeamNameListByPurchase(String teamBillId);

}
