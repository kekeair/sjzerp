package com.qxh.model;

public class DemandListH {

	private int billId;
	private String code = "";
	private int centerId;
	private String createTime;
	private String outTime;
	private int reviewState;// 0等待经理确认1等待库管2等待经理再次确认3结束
	private String billDate;
	private int purchaseState;
	private String customerNm;
	private String userNm;
	
	
	//===添加 chen: 2016-12-29=======
	private String teamDemandId;
	private String customerId;
	
	
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getTeamDemandId() {
		return teamDemandId;
	}

	public void setTeamDemandId(String teamDemandId) {
		this.teamDemandId = teamDemandId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getCustomerNm() {
		return customerNm;
	}

	public void setCustomerNm(String customerNm) {
		this.customerNm = customerNm;
	}

	public int getPurchaseState() {
		return purchaseState;
	}

	public void setPurchaseState(int purchaseState) {
		this.purchaseState = purchaseState;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	private String totalMoney;
	private int orderIndex;

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public int getReviewState() {
		return reviewState;
	}

	public void setReviewState(int reviewState) {
		this.reviewState = reviewState;
	}
}
