package com.qxh.impl.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.model.Role;
import com.qxh.service.RoleService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.Result;

public class RoleServiceImpl implements RoleService {
	
	Logger log = Logger.getLogger(this.getClass());
	private RoleMapper roleMapper;
	
	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}

	/**
	 * 查询角色
	 */
	@Override
	public Result getRole() {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("获取数据成功");
		try {
			List<Role> roleList=roleMapper.getRole();
			if(roleList==null)
				roleList=new ArrayList<Role>();
			Role baseRole=new Role();
			baseRole.setAtNo(0);
			baseRole.setpId(-1);
			baseRole.setName("角色");
			baseRole.setLevelOrder(-1);
			roleList.add(0, baseRole);
			result.setData(roleList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询角色：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" );
		}
		return result;
	}
	
	/**
	 * 添加角色
	 */
	@Override	
	public Result addRole(String pId, String levelOrder, String name) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("pId", pId);
		param.put("levelOrder", levelOrder);
		param.put("name", name);
		param.put("atNo", -1);
		param.put("power", "");
		try {
			roleMapper.addRole(param);
			result.setData(param.get("atNo"));
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 添加角色：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "pId:" + pId
			+",levelOrder:"+levelOrder +",name:"+name + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 编辑角色名称
	 */
	@Override
	public Result editRoleNm(String atNo, String name) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		param.put("name", name);
		try {
			roleMapper.editRoleNm(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 编辑角色名称：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "atNo:" + atNo +",name:"+name + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 编辑角色权限
	 */
	@Override
	public Result editRolePower(String atNo, String power) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		param.put("power", power);
		try {
			roleMapper.editRolePower(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("操作失败");
			log.error("\r\n 编辑角色权限：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "atNo:" + atNo +",power:"+power + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 删除角色
	 */
	@Override
	public Result delRole(String atNo) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		try {
			int roleChildCount=roleMapper.getRoleChildCount(param);
			if(roleChildCount>0){
				result.setCode(CodeConstant.CODE200);
				result.setMsg("该角色下还有子节点，不能删除");
				return result;
			}
			int userCount=roleMapper.getUserNumByRole(param);
			if(userCount>0){
				result.setCode(CodeConstant.CODE200);
				result.setMsg("该角色下还有其他账户，不能删除");
				return result;
			}
			roleMapper.delRole(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("操作失败");
			log.error("\r\n 删除角色：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "atNo:" + atNo  + "\r\n\r\n");
		}
		return result;
	}

	
}
