package com.qxh.model;

/**
 * @author Mr chen
 * @name : TmpGoodsRecord
 * @todo : [临时库存的实体类]
 * 
 * 创建时间 ： 2016年11月23日上午10:15:33
 */
public class TmpGoodsRecord {
	private int atNo;	
	private int centerId;
	private String goodsNm;	
	private String unitNm;	
	private String spec;	
	private Double num;	
	private Double price;	
	private String createTime;
	private int orderIndex;// 控制当当前索引
	
	//===========G/S======================
	
	public int getAtNo() {
		return atNo;
	}
	public void setAtNo(int atNo) {
		this.atNo = atNo;
	}
	public int getCenterId() {
		return centerId;
	}
	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}
	public String getGoodsNm() {
		return goodsNm;
	}
	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}
	public String getUnitNm() {
		return unitNm;
	}
	public void setUnitNm(String unitNm) {
		this.unitNm = unitNm;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
	
	
	
}

