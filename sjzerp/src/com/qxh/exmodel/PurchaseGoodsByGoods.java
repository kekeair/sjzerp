package com.qxh.exmodel;

import com.qxh.utils.NumUtil;

/**
 * 
 * @author Mr chen
 * @name : PurchaseGoodsByGoods
 * @todo : [采购中查看的实体类]
 * 
 *       创建时间 ： 2016年12月23日上午11:36:32
 */
public class PurchaseGoodsByGoods {

	private String goodsId;
	private String goodsNm;
	private String spec;
	private String unitNm;
	private String teamNm;
	private Double price;
	private String orderNum;
	private Integer flat;
	private Double money;
	private Double totalMonay;
	private String userNm;
	private String customNm;
	private String remark;

	private Integer orderIndex;// 添加序号

	// ===============G/S=================

	public String getGoodsId() {
		return goodsId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCustomNm() {
		return customNm;
	}

	public void setCustomNm(String customNm) {
		this.customNm = customNm;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		double trim0_2 = NumUtil.trim0(money);
		this.money = trim0_2;
	}

	public String getTeamNm() {
		return teamNm;
	}

	public void setTeamNm(String teamNm) {
		this.teamNm = teamNm;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

}
