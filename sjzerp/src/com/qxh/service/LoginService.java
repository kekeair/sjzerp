package com.qxh.service;

import com.qxh.utils.Result;

public interface LoginService {

	/**
	 * 登陆
	 * @param userAccount
	 * @param pwd
	 * @return
	 */
	Result login(String userAccount, String pwd);
	/**
	 * 选择职务
	 * @param relId
	 * @param userId
	 * @return
	 */
	Result selectJob(String relId, String userId);
	
}
