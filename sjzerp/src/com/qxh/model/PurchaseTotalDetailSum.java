package com.qxh.model;

public class PurchaseTotalDetailSum {
	private String code;
	private Double sOrderNum;

	public PurchaseTotalDetailSum() {
		super();
	}

	public PurchaseTotalDetailSum(String code, Double sOrderNum) {
		super();
		this.code = code;
		this.sOrderNum = sOrderNum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getsOrderNum() {
		return sOrderNum;
	}

	public void setsOrderNum(Double sOrderNum) {
		this.sOrderNum = sOrderNum;
	}

	@Override
	public String toString() {
		return "PurchaseTotalDetailSum [code=" + code + ", sOrderNum=" + sOrderNum + "]";
	}

}
