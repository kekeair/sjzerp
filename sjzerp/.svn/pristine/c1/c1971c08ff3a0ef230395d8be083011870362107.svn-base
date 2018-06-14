package com.qxh.impl.role;

import java.util.List;
import java.util.Map;

import com.qxh.model.Role;


public interface RoleMapper {

	/**
	 * 查询角色列表
	 * @return
	 */
	List<Role> getRole();
	/**
	 * 添加角色
	 * @param param
	 */
	void addRole(Map<String, Object> param);
	/**
	 * 编辑角色名称
	 * @param param
	 */
	void editRoleNm(Map<String, Object> param);
	/**
	 * 编辑角色权限
	 * @param param
	 */
	void editRolePower(Map<String, Object> param);
	/**
	 * 查询角色子节点数量
	 * @return
	 */
	int getRoleChildCount(Map<String, Object> param);
	/**
	 * 根据角色查询用户数量
	 * @param param
	 * @return
	 */
	int getUserNumByRole(Map<String, Object> param);
	/**
	 * 删除角色
	 * @param param
	 */
	void delRole(Map<String, Object> param);
	
}
