package com.qxh.model;

public class Structure {
	
	private int atNo;
	private int pId=-1;
	private String code;
	private String name;
	private int levelOrder;
	private int isCenter;//是否是餐饮中心：0不是1是
	private String createTime;
	private String updateTime;
	
	//扩展属性
	private int provinceId;
	private int cityId;
	private int countyId;
	private String address;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getCountyId() {
		return countyId;
	}
	public void setCountyId(int countyId) {
		this.countyId = countyId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public int getAtNo() {
		return atNo;
	}
	public void setAtNo(int atNo) {
		this.atNo = atNo;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevelOrder() {
		return levelOrder;
	}
	public void setLevelOrder(int levelOrder) {
		this.levelOrder = levelOrder;
	}
	public int getIsCenter() {
		return isCenter;
	}
	public void setIsCenter(int isCenter) {
		this.isCenter = isCenter;
	}
	
}
