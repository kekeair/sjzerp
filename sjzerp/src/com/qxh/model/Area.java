package com.qxh.model;

public class Area {
	
	private int atNo;
	private int pId=-1;
	private String name;
	private int levelOrder;//0省份1城市3县（区）
	
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
	
}
