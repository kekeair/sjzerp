package com.qxh.exmodel;

import com.qxh.utils.NumUtil;

public class DemandGoods {

	private int orderIndex;
	private int goodsId;
	private String goodsNm;
	private Double stock;
	private Double demandNum = 0.00;
	private int state;// 0未添加到购物车1已添加到购物车
	private Double price = 0.00;
	private double money = 0.00;
	private int demandListDId;
	private int teamId;
	private String unitNm;
	private int tempGoodsType;
	private String spec;
	private int teamDemandId;
	private int goodsType;
	private String kindNm;// 物料类型名
	private Integer pId;// 类型父类Id
	private double stockNum;
	private String code; // 物料编码
	private String tagGoodsPrice;
	private String remark;
	private String billDate;
	private String tcode;// 临时物料标注(为毛利表)
	private Integer purchaseListHId;
	private String customNm;

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getKindNm() {
		return kindNm;
	}

	public void setKindNm(String kindNm) {
		this.kindNm = kindNm;
	}

	public String getTcode() {
		return tcode;
	}

	public void setTcode(String tcode) {
		this.tcode = tcode;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
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

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		double trim0_2 = NumUtil.trim0(stock);
		this.stock = trim0_2;
	}

	public Double getDemandNum() {
		return demandNum;
	}

	public void setDemandNum(Double demandNum) {
		double trim0_2 = NumUtil.trim0(demandNum);
		this.demandNum = trim0_2;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		double trim0_2 = NumUtil.trim0(price);
		this.price = trim0_2;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		double trim0_2 = NumUtil.trim0(money);
		this.money = trim0_2;
	}

	public int getDemandListDId() {
		return demandListDId;
	}

	public void setDemandListDId(int demandListDId) {
		this.demandListDId = demandListDId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getUnitNm() {
		return unitNm;
	}

	public void setUnitNm(String unitNm) {
		this.unitNm = unitNm;
	}

	public int getTempGoodsType() {
		return tempGoodsType;
	}

	public void setTempGoodsType(int tempGoodsType) {
		this.tempGoodsType = tempGoodsType;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public int getTeamDemandId() {
		return teamDemandId;
	}

	public void setTeamDemandId(int teamDemandId) {
		this.teamDemandId = teamDemandId;
	}

	public int getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(int goodsType) {
		this.goodsType = goodsType;
	}

	public double getStockNum() {
		return stockNum;
	}

	public void setStockNum(double stockNum) {
		double trim0_2 = NumUtil.trim0(stockNum);
		this.stockNum = trim0_2;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTagGoodsPrice() {
		return tagGoodsPrice;
	}

	public void setTagGoodsPrice(String tagGoodsPrice) {
		this.tagGoodsPrice = tagGoodsPrice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
