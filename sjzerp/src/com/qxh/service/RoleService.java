package com.qxh.service;

import com.qxh.utils.Result;

public interface RoleService {

	/**
	 * 查询角色
	 * @return
	 */
	Result getRole();
	/**
	 * 添加角色
	 * @param pId
	 * @param levelOrder
	 * @param name
	 * @return
	 */
	Result addRole(String pId, String levelOrder, String name);
	/**
	 * 编辑角色名
	 * @param atNo
	 * @param name
	 * @return
	 */
	Result editRoleNm(String atNo, String name);
	/**
	 * 编辑角色权限
	 * @param atNo
	 * @param power
	 * @return
	 */
	Result editRolePower(String atNo, String power);
	/**
	 * 删除角色
	 * @param atNo
	 * @return
	 */
	Result delRole(String atNo);
}
