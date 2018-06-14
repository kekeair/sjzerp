package com.qxh.exmodel;

public class PurchaseInModel {
	
	private int goodsId;
	private double totalInNum;
	private double price;
	private double money;
	private int stockId;
	private double preStockNum;
	private double preStockPrice;
	private double afterStockNum;//当前库存数量
	private double afterStockPrice;//当前库存物料单价
	
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public double getTotalInNum() {
		return totalInNum;
	}
	public void setTotalInNum(double totalInNum) {
		this.totalInNum = totalInNum;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	
}
