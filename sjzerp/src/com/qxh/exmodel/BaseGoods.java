package com.qxh.exmodel;

import com.qxh.utils.NumUtil;

public class BaseGoods {

	private int atNo;
	private int orderIndex;
	private int goodsId;
	private String goodsNm;
	private String goodsCode;
	private String spec;
	private String unitNm;
	private double price;
	private double num;
	private double money;
	private int supplierId;
	private String supplierNm;
	private String goodsType;
	private String remark;
	private String customerNm;
	private String customerId;
	private String code;
	private String BillDate;

	public String getBillDate() {
		return BillDate;
	}

	public void setBillDate(String billDate) {
		BillDate = billDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerNm() {
		return customerNm;
	}

	public void setCustomerNm(String customerNm) {
		this.customerNm = customerNm;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public int getAtNo() {
		return atNo;
	}

	public void setAtNo(int atNo) {
		this.atNo = atNo;
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
		double trim0 = NumUtil.trim0(price);
		this.price = trim0;
	}

	public double getNum() {
		return num;
	}

	public void setNum(double num) {
		double trim0 = NumUtil.trim0(num);
		this.num = trim0;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		double trim0 = NumUtil.trim0(money);
		this.money = trim0;
	}

}
