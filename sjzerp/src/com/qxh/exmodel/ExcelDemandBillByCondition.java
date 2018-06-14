package com.qxh.exmodel;

/**
 * @author Mr chen
 * @name : ExcelDemandBillByCondition
 * @todo : [客户申报按条件导出]
 * 
 *       创建时间 ： 2016年12月24日上午8:57:23
 */
public class ExcelDemandBillByCondition {

	private String time;
	private String billCode;
	private String customNm;
	private String goodsNm;
	private String code;
	private String spec;
	private String unitNm;
	private String demandNum;
	private Double price;
	private Double monay;
	private Integer flat;
	private String name;// 附件名字 ----去重*
	private String bill;// 附加账单 ----去重*
	private String remark;
	private String billDate;

	// ==============GS=====================
	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getTime() {
		return time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBill() {
		return bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
	}

	public Integer getFlat() {
		return flat;
	}

	public void setFlat(Integer flat) {
		this.flat = flat;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getCustomNm() {
		return customNm;
	}

	public void setCustomNm(String customNm) {
		this.customNm = customNm;
	}

	public String getGoodsNm() {
		return goodsNm;
	}

	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getDemandNum() {
		return demandNum;
	}

	public void setDemandNum(String demandNum) {
		this.demandNum = demandNum;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMonay() {
		return monay;
	}

	public void setMonay(Double monay) {
		this.monay = monay;
	}

}
