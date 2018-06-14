package com.qxh.model;

public class UserRel {
	
	private int atNo;
	private int userId;
	private int structId;
	private int roleId;
	
	//扩展
	private String structNm;
	private String roleNm;
	private String power;
	
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public int getAtNo() {
		return atNo;
	}
	public void setAtNo(int atNo) {
		this.atNo = atNo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStructId() {
		return structId;
	}
	public void setStructId(int structId) {
		this.structId = structId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getStructNm() {
		return structNm;
	}
	public void setStructNm(String structNm) {
		this.structNm = structNm;
	}
	public String getRoleNm() {
		return roleNm;
	}
	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}
	
}
