package com.qxh.exmodel;

/**
 * 
 * @author Mr chen
 * @name : PurchaseGoodsByGoods
 * @todo : [采购中查看的实体类]
 * 
 * 创建时间 ： 2016年12月23日上午11:36:32
 */
public class PurchaseGoodsBySupplier {

	private String goodsId;
	private String goodsNm;
	private String spec;
	private String unitNm;
	private String teamNm;
	private Double price;
	private String orderNum ;
	private Integer flat;
	private Double totalMonay;
	
	
	//===============G/S=================
	
	
	public String getGoodsId() {
		return goodsId;
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
		this.totalMonay = totalMonay;
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
	
	
}
