package com.qxh.exmodel;

import com.qxh.utils.NumUtil;

/**
 * @author Mr chen
 * @name : DemandGoodsByGoods
 * @todo : [物料查询实体对象]
 * 
 *       创建时间 ： 2016年12月22日上午10:17:20
 */
public class DemandGoodsByGoods {

	private String goodsId;
	private String goodsNm;
	private String spec;
	private String unitNm;
	private String customNm;
	private Double price;
	private String demandNum;
	private Integer flat;
	private Double totalMonay;
	private String remark;
	private double money;
	private int tempGoodsType;

	private Integer orderIndex;// 序号

	// ===============G/S=================

	public String getGoodsId() {
		return goodsId;
	}

	public int getTempGoodsType() {
		return tempGoodsType;
	}

	public void setTempGoodsType(int tempGoodsType) {
		this.tempGoodsType = tempGoodsType;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		double trim0_2 = NumUtil.trim0(money);
		this.money = trim0_2;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getTotalMonay() {
		return totalMonay;
	}

	public void setTotalMonay(Double totalMonay) {
		double trim0_2 = NumUtil.trim0(totalMonay);
		this.totalMonay = trim0_2;
	}

	public Integer getFlat() {
		return flat;
	}

	public void setFlat(Integer flat) {
		this.flat = flat;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsNm() {
		return goodsNm;
	}

	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
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

	public String getCustomNm() {
		return customNm;
	}

	public void setCustomNm(String customNm) {
		this.customNm = customNm;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDemandNum() {
		return demandNum;
	}

	public void setDemandNum(String demandNum) {
		double num = NumUtil.trim0(Double.parseDouble(demandNum));
		this.demandNum = String.valueOf(num);
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

}
