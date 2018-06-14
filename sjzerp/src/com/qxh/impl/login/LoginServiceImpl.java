package com.qxh.impl.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.model.User;
import com.qxh.model.UserRel;
import com.qxh.service.LoginService;
import com.qxh.utils.AESCoder;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class LoginServiceImpl implements LoginService {
	
	Logger log = Logger.getLogger(this.getClass());
	private LoginMapper loginMapper;
	
	public void setLoginMapper(LoginMapper loginMapper) {
		this.loginMapper = loginMapper;
	}

	/**
	 * 登陆
	 */
	@Override
	public Result login(String userAccount, String pwd) {
		Result result = new Result();
		result.setMsg("获取数据成功");
		Map<String, Object> param = new HashMap<>();
		param.put("userAccount", userAccount);
		param.put("pwd", pwd);
		try {
			User user=loginMapper.getLoginUser(param);
			if(user==null){
				result.setCode(CodeConstant.CODE201);
				result.setMsg("用户不存在");
				return result;
			}
			if(!new String(AESCoder.decode(user.getPwd())).equals(pwd)){
				result.setCode(CodeConstant.CODE202);
				result.setMsg("账户密码不匹配，请重新输入");
				return result;
			}
			param.put("userId", user.getAtNo());
			List<UserRel> relList=loginMapper.getUserRel(param);
			if(relList==null||relList.size()==0){
				result.setCode(CodeConstant.CODE203);
				result.setMsg("该账户尚未设置权限，请通知管理员进行设置");
				return result;
			}
			if(relList.size()==1){//如果用户是单角色
				UserRel rel=relList.get(0);
				user.setRoleId(rel.getRoleId());
				user.setStructId(rel.getStructId());
				user.setPower(rel.getPower());
				HttpSession session=SessionUtil.getSession();
				session.setAttribute("userAccount", userAccount);
				session.setAttribute("pwd", pwd);
				session.setAttribute("user", user);
				session.setAttribute("structId", rel.getStructId());
				result.setCode(CodeConstant.CODE204);
			}else{
				result.setCode(CodeConstant.CODE205);
				result.setData(relList);
			}
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取信息失败");
			log.error("\r\n  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "userAccount:" + userAccount
			+",pwd:"+pwd + "\r\n\r\n");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 选择职务
	 */
	@Override
	public Result selectJob(String relId, String userId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("获取数据成功");
		Map<String, Object> param = new HashMap<>();
		param.put("relId", relId);
		param.put("userId", userId);
		try {
			User user=loginMapper.getUserByIdAndRel(param);
			HttpSession session=SessionUtil.getSession();
			session.setAttribute("userAccount", user.getUserAccount());
			String pwd=new String(AESCoder.decode(user.getPwd()));
			session.setAttribute("pwd", pwd);
			session.setAttribute("user", user);
			session.setAttribute("structId", user.getStructId());
			Map<String, Object> data = new HashMap<>();
			data.put("userAccount", user.getUserAccount());
			data.put("pwd", pwd);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取信息失败");
			log.error("\r\n  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "relId:" + relId
			+",userId:"+ userId + "\r\n\r\n");
		}
		return result;
	}
}
