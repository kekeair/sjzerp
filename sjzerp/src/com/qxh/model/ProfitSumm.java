package com.qxh.model;

import com.qxh.utils.NumUtil;

public class ProfitSumm {
	private String tcode;// 临时物料标注
	private Integer goodsId;
	private String goodsCode;
	private String goodsNm;
	private String billDate;
	private Integer goodsType;// 0常规物料 1非常规物料
	private String kindNm;// 类型
	private Double orderNum = 0.00;
	private Double pPrice = 0.00; // 成本价--也是采购表中价格
	private Double pMoney = 0.00;// 成本
	private Double dPirce = 0.00;// 售价
	private Double dMoney = 0.00;// 销售收入
	private Double profitMoney = 0.00;// 毛利
	private String rate;// 毛利率
	private Integer orderIndex;// 序号
	private String unitNm;
	private Integer demandListDId;
	private String purchaseListDId;
	private Integer purchaseListHId;

	public String getUnitNm() {
		return unitNm;
	}

	public void setUnitNm(String unitNm) {
		this.unitNm = unitNm;
	}

	public String getKindNm() {
		return kindNm;
	}

	public void setKindNm(String kindNm) {
		this.kindNm = kindNm;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getTcode() {
		return tcode;
	}

	public void setTcode(String tcode) {
		this.tcode = tcode;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsNm() {
		return goodsNm;
	}

	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	public Double getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Double orderNum) {
		double trim0 = NumUtil.trim0(orderNum);
		this.orderNum = trim0;
	}

	public Double getpPrice() {
		return pPrice;
	}

	public void setpPrice(Double pPrice) {
		double trim0 = NumUtil.trim0(pPrice);
		this.pPrice = trim0;
	}

	public Double getpMoney() {
		return pMoney;
	}

	public void setpMoney(Double pMoney) {
		double trim0 = NumUtil.trim0(pMoney);
		this.pMoney = trim0;
	}

	public Double getdPirce() {
		return dPirce;
	}

	public void setdPirce(Double dPirce) {
		double trim0 = NumUtil.trim0(dPirce);
		this.dPirce = trim0;
	}

	public Double getdMoney() {
		return dMoney;
	}

	public void setdMoney(Double dMoney) {
		double trim0 = NumUtil.trim0(dMoney);
		this.dMoney = trim0;
	}

	public Double getProfitMoney() {
		return profitMoney;
	}

	public void setProfitMoney(Double profitMoney) {
		double trim0 = NumUtil.trim0(profitMoney);
		this.profitMoney = trim0;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Integer getDemandListDId() {
		return demandListDId;
	}

	public void setDemandListDId(Integer demandListDId) {
		this.demandListDId = demandListDId;
	}

	public String getPurchaseListDId() {
		return purchaseListDId;
	}

	public void setPurchaseListDId(String purchaseListDId) {
		this.purchaseListDId = purchaseListDId;
	}

	public Integer getPurchaseListHId() {
		return purchaseListHId;
	}

	public void setPurchaseListHId(Integer purchaseListHId) {
		this.purchaseListHId = purchaseListHId;
	}
}
