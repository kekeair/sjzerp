package com.qxh.model;

public class StockFixH {
	
	private int billId;
	private String code="";
	private int centerId;
	private int launcher;
	private String createTime;
	private int reviewState;//0等待发出1等待经理确认2结束
	
	private int orderIndex;

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	public int getLauncher() {
		return launcher;
	}

	public void setLauncher(int launcher) {
		this.launcher = launcher;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getReviewState() {
		return reviewState;
	}

	public void setReviewState(int reviewState) {
		this.reviewState = reviewState;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
	
}
