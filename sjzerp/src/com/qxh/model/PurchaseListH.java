package com.qxh.model;
/**
 * 
 * @author Mr chen
 * @name : PurchaseListH
 * @todo : [添加一列客户]
 * 
 * 修改时间 ： 2016年12月19日上午9:54:41
 */
public class PurchaseListH {
	
	private int billId;
	private String code="";
	private int codeOrder;
	private int centerId;
	private String createTime;
	private int reviewState;//0等待库管确认1等待经理确认2等待采购员确认3等待库管质检4等待经理二次确认5已结束
	private int isCheck;
	private int purchaseType;
	private String billDate;
	private String customNm;
	
	public String getCustomNm() {
		return customNm;
	}
	public void setCustomNm(String customNm) {
		this.customNm = customNm;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	private int orderIndex;
	
	public int getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
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
	public int getCodeOrder() {
		return codeOrder;
	}
	public void setCodeOrder(int codeOrder) {
		this.codeOrder = codeOrder;
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
	public int getReviewState() {
		return reviewState;
	}
	public void setReviewState(int reviewState) {
		this.reviewState = reviewState;
	}
	public int getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}
	public int getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(int purchaseType) {
		this.purchaseType = purchaseType;
	}
}
