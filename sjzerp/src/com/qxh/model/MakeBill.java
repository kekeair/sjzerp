package com.qxh.model;

public class MakeBill {
	private int atNo;
	private int headAtNo;// 订单Id
	private int orderIndex;
	private int goodsId;// 物料Id
	private String goodsNm;// 临时物料名称
	private String goodsCode;
	private String spec;// 品牌
	private String unitNm;// 单位
	private double price;// 售价
	private double num;// 数量
	private double money;
	private int goodsType;// 是否是临时物料
	private int customerId;// 客户Id
	private String customerNm;
	private int teamId;// 作业组Id
	private String teamNm;
	private int supplierId;// 供应商Id
	private String supplierNm;
	private String remark;// 备注
	private int tempGoodsType;// 临时物料类型
	private int demandListDId;

	public int getAtNo() {
		return atNo;
	}

	public void setAtNo(int atNo) {
		this.atNo = atNo;
	}

	public int getHeadAtNo() {
		return headAtNo;
	}

	public void setHeadAtNo(int headAtNo) {
		this.headAtNo = headAtNo;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsNm() {
		return goodsNm;
	}

	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getUnitNm() {
		return unitNm;
	}

	public void setUnitNm(String unitNm) {
		this.unitNm = unitNm;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getNum() {
		return num;
	}

	public void setNum(double num) {
		this.num = num;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(int goodsType) {
		this.goodsType = goodsType;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerNm() {
		return customerNm;
	}

	public void setCustomerNm(String customerNm) {
		this.customerNm = customerNm;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getTeamNm() {
		return teamNm;
	}

	public void setTeamNm(String teamNm) {
		this.teamNm = teamNm;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierNm() {
		return supplierNm;
	}

	public void setSupplierNm(String supplierNm) {
		this.supplierNm = supplierNm;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getTempGoodsType() {
		return tempGoodsType;
	}

	public void setTempGoodsType(int tempGoodsType) {
		this.tempGoodsType = tempGoodsType;
	}

	public int getDemandListDId() {
		return demandListDId;
	}

	public void setDemandListDId(int demandListDId) {
		this.demandListDId = demandListDId;
	}

}
