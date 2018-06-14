package com.qxh.model;

public class BigKind {
	private String kindNm;
	private Double totalPMoney;// 占总成本
	private Double totalDMoney;// 占总收入
	private Double totalProfit;// 单项毛利率
	private Double PPercent;// 成本占比
	private Double DPercent;// 收入占比

	public String getKindNm() {
		return kindNm;
	}

	public void setKindNm(String kindNm) {
		this.kindNm = kindNm;
	}

	public Double getTotalPMoney() {
		return totalPMoney;
	}

	public void setTotalPMoney(Double totalPMoney) {
		this.totalPMoney = totalPMoney;
	}

	public Double getTotalDMoney() {
		return totalDMoney;
	}

	public void setTotalDMoney(Double totalDMoney) {
		this.totalDMoney = totalDMoney;
	}

	public Double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public Double getPPercent() {
		return PPercent;
	}

	public void setPPercent(Double pPercent) {
		PPercent = pPercent;
	}

	public Double getDPercent() {
		return DPercent;
	}

	public void setDPercent(Double dPercent) {
		DPercent = dPercent;
	}

}
