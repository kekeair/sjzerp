package com.qxh.impl.login;

import java.util.List;
import java.util.Map;

import com.qxh.model.User;
import com.qxh.model.UserRel;

public interface LoginMapper {
	
	/**
	 * 登陆时查找用户
	 * @param param
	 * @return
	 */
	User getLoginUser(Map<String, Object> param);
	/**
	 * 获取用户角色列表
	 * @param param
	 * @return
	 */
	List<UserRel> getUserRel(Map<String, Object> param);
	/**
	 * 根据用户id查询用户
	 * @param param
	 * @return
	 */
	User getUserByIdAndRel(Map<String, Object> param);
	
}
