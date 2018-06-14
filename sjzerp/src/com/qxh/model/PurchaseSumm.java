package com.qxh.model;

import com.qxh.utils.NumUtil;

/**
 * @author Mr chen
 * @name : PurchaseSumm
 * @todo : [采购汇总实体对象]
 * 
 *       创建时间 ： 2016年12月2日上午11:41:46
 */
public class PurchaseSumm {
	private Integer purchaseListHId;// id
	private String code;
	private String billDate;
	private String tcode;// 临时物料标注(为毛利汇总)
	private String goodsId;
	private String purchaselistDId;
	private String goodsNm;
	private String orderNum;
	private Double totalNum;
	private String unitNm;
	private Double price;
	private Double totalMn;// 合计
	private String goodsType;// 0常规物料 1非常规物料
	private String kindNm;// 物料类型名称
	private String demandlisth;
	private String supplierNm;
	private Integer orderIndex;
	private String customNm;
	// ============G/S=====================

	public String getCode() {
		return code;
	}

	public String getKindNm() {
		return kindNm;
	}

	public void setKindNm(String kindNm) {
		this.kindNm = kindNm;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getTcode() {
		return tcode;
	}

	public void setTcode(String tcode) {
		this.tcode = tcode;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getPurchaselistDId() {
		return purchaselistDId;
	}

	public void setPurchaselistDId(String purchaselistDId) {
		this.purchaselistDId = purchaselistDId;
	}

	public String getGoodsNm() {
		return goodsNm;
	}

	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}

	public Double getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Double totalNum) {
		double trim0 = NumUtil.trim0(totalNum);
		this.totalNum = trim0;
	}

	public String getUnitNm() {
		return unitNm;
	}

	public void setUnitNm(String unitNm) {
		this.unitNm = unitNm;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		double trim0 = NumUtil.trim0(price);
		this.price = trim0;
	}

	public Double getTotalMn() {
		return totalMn;
	}

	public void setTotalMn(Double totalMn) {
		double trim0 = NumUtil.trim0(totalMn);
		this.totalMn = trim0;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getDemandlisth() {
		return demandlisth;
	}

	public void setDemandlisth(String demandlisth) {
		this.demandlisth = demandlisth;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getSupplierNm() {
		return supplierNm;
	}

	public void setSupplierNm(String supplierNm) {
		this.supplierNm = supplierNm;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getPurchaseListHId() {
		return purchaseListHId;
	}

	public void setPurchaseListHId(Integer purchaseListHId) {
		this.purchaseListHId = purchaseListHId;
	}

	public String getCustomNm() {
		return customNm;
	}

	public void setCustomNm(String customNm) {
		this.customNm = customNm;
	}

}
