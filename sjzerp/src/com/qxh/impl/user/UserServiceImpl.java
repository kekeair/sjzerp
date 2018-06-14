package com.qxh.impl.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.CommonModel;
import com.qxh.exmodel.ListUser;
import com.qxh.model.User;
import com.qxh.model.UserRel;
import com.qxh.service.UserService;
import com.qxh.utils.AESCoder;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.IPageConstants;
import com.qxh.utils.PageUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class UserServiceImpl implements UserService {
	
	Logger log = Logger.getLogger(this.getClass());
	private UserMapper userMapper;
	
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	/**
	 * 查询用户列表
	 */
	@Override
	public Result getUserList(String name, String structId, String roleId, String page) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("name", name);
		param.put("structId", structId);
		param.put("roleId", roleId);
		param.put("page", (Integer.parseInt(page)-1)*IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		try {
			List<ListUser> userList=userMapper.getUserList(param);
			Map<Integer, ListUser> keyMap=new HashMap<>();
			int totalCount=0,totalPage=1;
			if(userList!=null&&userList.size()>0){
				int l=userList.size();
				int orderIndex=(Integer.parseInt(page)-1)*IPageConstants.PageSize+1;
				String uId="";
				for (int i = 0; i < l; i++) {
					ListUser u=userList.get(i);
					u.setOrderIndex(orderIndex);
					keyMap.put(u.getAtNo(), u);
					uId+=u.getAtNo()+",";
					orderIndex++;
				}
				uId=uId.substring(0, uId.length()-1);
				param.put("uId", uId);
				List<CommonModel> jobList=userMapper.getUserStructAndRole(param); 
				if(jobList!=null&&jobList.size()>0){
					int jobLen=jobList.size();
					for (int i = 0; i < jobLen; i++) {
						CommonModel job=jobList.get(i);
						ListUser u=keyMap.get(job.getAtNo());
						if(u!=null){
							u.getJobList().add(job.getName());
							if(job.getpId()==2)
								u.setIsAdmin(1);
						}
							
					}
				}
				totalCount=userMapper.getUserCount(param);
				totalPage=PageUtil.getTotalPage(totalCount, IPageConstants.PageSize);
			}
			Map<String, Object> data = new HashMap<>();
			data.put("userList", userList);
			data.put("totalPage", totalPage);
			data.put("totalCount", totalCount);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询用户列表：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "page:" + page +",roleId:"+roleId
			+",structId:"+structId +",name:"+name + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 编辑用户
	 */
	@Override
	public Result editUser(String userId, String userNm, String userAccount, String pwd, 
			String phone, String sex,String job,String provinceId,String cityId,
			String countyId,String address,String remark) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("userId", userId);
		param.put("userNm", userNm);
		param.put("userAccount", userAccount);
		param.put("pwd", AESCoder.encode(pwd.getBytes()));
		param.put("phone", phone);
		param.put("sex", sex);
		param.put("provinceId", provinceId);
		param.put("cityId", cityId);
		param.put("countyId", countyId);
		param.put("address", address);
		param.put("remark", remark);
		try {
			//首先判断账户名是否重复
			int count=userMapper.checkUserAccount(param);
			if(count>0){
				result.setCode(CodeConstant.CODE206);
				result.setMsg("账户名已被使用，请重新填写");
				return result;
			}
			List<UserRel> relList=JSON.parseArray(job, UserRel.class);
			param.put("relList", relList);
			if(userId.equals("-1")){//新增用户
				userMapper.addUser(param);
				//新增用户关系
				userMapper.addUserRel(param);
				if(!provinceId.equals("-1")){
					//如果用户是供应商
					userMapper.addSupplierAttr(param);
				}
			}else{//修改用户
				userMapper.updateUser(param);
				//先删除原来用户关系
				userMapper.delUserRelByUserId(param);
				//新增用户关系
				userMapper.addUserRel(param);
				if(!provinceId.equals("-1")){
					//如果用户是供应商
					int supplierCount=userMapper.checkSupplier(param);
					if(supplierCount>0)
						userMapper.updateSupplierAttr(param);
					else
						userMapper.addSupplierAttr(param);
				}else{
					userMapper.delSupplierAttr(param);
				}
			}
		} catch (Exception e) {
			log.error("\r\n 编辑用户：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "userId:" + userId +",userNm:"+userNm
			+",userAccount:"+userAccount +",pwd:"+pwd
			+",phone:"+phone +",sex:"+sex +",job:"+job + "\r\n\r\n");
			throw new RuntimeException();
		}
		return result;
	}

	/**
	 * 根据id查询用户
	 */
	@Override
	public Result getUserById(String userId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("userId", userId);
		try {
			User user=userMapper.getUserById(param);
			user.setPwd(new String(AESCoder.decode(user.getPwd())));
			result.setData(user);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 根据id查询用户：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "userId:" + userId + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 删除用户
	 */
	@Override
	public Result delUser(String userId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("userId", userId);
		try {
			userMapper.delUser(param);
			userMapper.delUserRelByUserId(param);
			userMapper.delSupplierAttr(param);
		} catch (Exception e) {
			log.error("\r\n 删除用户：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "userId:" + userId + "\r\n\r\n");
			throw new RuntimeException();
		}
		return result;
	}

	
	//===========更新/修改密码/修改用户名======================
	// 修改登录密码
	@Override
	public Result updatePwd(String oldPwd, String confirmPwd) {
		Result result = new Result();
		result.setMsg("登录密码修改成功");
		Map<String, Object> param = new HashMap<>();
		HttpSession session=SessionUtil.getSession();
		User user=(User)session.getAttribute("user");
		//获取原始密码
		String oldPwd2 = user.getPwd();
		 byte[] oldPassword = AESCoder.decode(oldPwd2);//解密
		 String opw = new String(oldPassword);
		 //修改后密码
		 byte[] conPwd = confirmPwd.getBytes();
		 String confirmPassword = AESCoder.encode(conPwd);//加密
		try {
			param.put("confirmPwd", confirmPassword);
			param.put("userId", user.getAtNo());
			if(!opw.equals(oldPwd)){
				result.setCode(CodeConstant.CODE200);
				result.setMsg("原密码不正确");
				return result;
			}
			userMapper.updatePwd(param);
			//将信息放入session
			session.setAttribute("pwd", confirmPassword);
			user.setPwd(confirmPassword);
			session.setAttribute("user", user);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 修改密码：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "oldPwd:" + oldPwd + "\r\n\r\n");
	}
		return result;
	}
	//修改名称
	@Override
	public Result updateNm(String userName) {
		Result result = new Result();
		result.setMsg("名称修改成功");
		Map<String, Object> param = new HashMap<>();
		HttpSession session=SessionUtil.getSession();
		User user=(User)session.getAttribute("user");
		try {
			param.put("userNm", userName);
			param.put("userId", user.getAtNo());
			userMapper.updateNm(param);
			//将信息放入session
			user.setUserNm(userName);
			session.setAttribute("user", user);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 修改名称：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "userName:" + userName + "\r\n\r\n");
		}
		return result;
	}

	/* 
	 * Todo : [获取user列表]
	 * @时间:2016年11月18日下午12:55:39
	 */
	@Override
	public Result getUserListToTag() {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		try {
			List<ListUser> userList=userMapper.getUserListToTag();
			Map<String, Object> data = new HashMap<>();
			data.put("userList", userList);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}
	
}
