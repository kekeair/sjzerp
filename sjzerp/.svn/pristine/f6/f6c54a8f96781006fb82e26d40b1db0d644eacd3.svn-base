package com.qxh.service;

import com.qxh.utils.Result;

public interface UserService {

	/**
	 * 查询用户列表
	 * @param name
	 * @param structId
	 * @param roleId
	 * @param page
	 * @return
	 */
	Result getUserList(String name, String structId, String roleId, String page);
	/**
	 * 编辑用户
	 * @param userId
	 * @param userNm
	 * @param userAccount
	 * @param pwd
	 * @param phone
	 * @param sex
	 * @param job
	 * @return
	 */
	Result editUser(String userId, String userNm, String userAccount, String pwd, 
			String phone, String sex, String job,String provinceId,String cityId,
			String countyId,String address,String remark);
	/**
	 * 根据id查询用户
	 * @param userId
	 * @return
	 */
	Result getUserById(String userId);
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	Result delUser(String userId);
	
	/**
	 * 修改登录密码
	 * @param oldPwd
	 * @param confirmPwd
	 * @return
	 */
	Result updatePwd(String oldPwd,String confirmPwd);
	/**
	 * 修改名称
	 * @param userName
	 * @return
	 */
	Result updateNm(String userName);
	/**
	 * @description : [获取user列表]
	 * @return
	 * @时间:2016年11月18日下午12:55:25
	 */
	Result getUserListToTag();

}
