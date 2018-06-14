package com.qxh.impl.user;

import java.util.List;
import java.util.Map;

import com.qxh.exmodel.CommonModel;
import com.qxh.exmodel.ListUser;
import com.qxh.model.User;

public interface UserMapper {
	
	/**
	 * 用户列表
	 * @param param
	 * @return
	 */
	List<ListUser> getUserList(Map<String, Object> param);
	/**
	 * 用户数量
	 * @param param
	 * @return
	 */
	int getUserCount(Map<String, Object> param);
	/**
	 * 查询用户所属架构和角色
	 * @param param
	 * @return
	 */
	List<CommonModel> getUserStructAndRole(Map<String, Object> param);
	/**
	 * 添加用户
	 * @param param
	 */
	void addUser(Map<String, Object> param);
	/**
	 * 判断账户名是否重复
	 * @param param
	 * @return
	 */
	int checkUserAccount(Map<String, Object> param);
	/**
	 * 新增用户关系
	 * @param param
	 */
	void addUserRel(Map<String, Object> param);
	/**
	 * 修改用户
	 * @param param
	 */
	void updateUser(Map<String, Object> param);
	/**
	 * 根据用户id删除用户关系
	 * @param param
	 */
	void delUserRelByUserId(Map<String, Object> param);
	/**
	 * 根据id查询用户
	 * @param param
	 * @return
	 */
	User getUserById(Map<String, Object> param);
	/**
	 * 删除用户
	 * @param param
	 */
	void delUser(Map<String, Object> param);
	/**
	 * 添加供应商属性
	 * @param param
	 */
	void addSupplierAttr(Map<String, Object> param);
	/**
	 * 检测用户是否是供应商
	 * @param param
	 * @return
	 */
	int checkSupplier(Map<String, Object> param);
	/**
	 * 更新供应商属性
	 * @param param
	 */
	void updateSupplierAttr(Map<String, Object> param);
	/**
	 * 删除供应商属性
	 * @param param
	 */
	void delSupplierAttr(Map<String, Object> param);
	
	/**
	 * 修改登录密码
	 * @param anNo
	 * @param pwd
	 * @return
	 */
	void updatePwd(Map<String,Object> param);
	
	/**
	 * 修改名称
	 * @param param
	 */
	void updateNm(Map<String,Object> param);
	/**
	 * @description : [获取user列表]
	 * @return
	 * @时间:2016年11月18日下午12:58:56
	 */
	List<ListUser> getUserListToTag();
	
	
}
