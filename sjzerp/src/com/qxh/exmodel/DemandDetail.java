package com.qxh.exmodel;

public class DemandDetail {
	
	private int goodsId;
	private double num;
	private double price;
	private double preStockNum;
	private double preStockPrice;
	private double afterStockNum;
	private double afterStockPrice;
	
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPreStockNum() {
		return preStockNum;
	}
	public void setPreStockNum(double preStockNum) {
		this.preStockNum = preStockNum;
	}
	public double getPreStockPrice() {
		return preStockPrice;
	}
	public void setPreStockPrice(double preStockPrice) {
		this.preStockPrice = preStockPrice;
	}
	public double getAfterStockNum() {
		return afterStockNum;
	}
	public void setAfterStockNum(double afterStockNum) {
		this.afterStockNum = afterStockNum;
	}
	public double getAfterStockPrice() {
		return afterStockPrice;
	}
	public void setAfterStockPrice(double afterStockPrice) {
		this.afterStockPrice = afterStockPrice;
	}
}
