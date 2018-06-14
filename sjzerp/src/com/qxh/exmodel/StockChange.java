package com.qxh.exmodel;

import com.qxh.utils.NumUtil;

public class StockChange {
	private int goodsId;
	private String goodsCode;
	private String goodsNm;
	private String spec;
	private String unitNm;
	private double sNum;
	private double sMoney;
	private double sPrice;
	private double rkNum;
	private double rkMoney;
	private double rkPrice;
	private double ckNum;
	private double ckMoney;
	private double ckPrice;
	private double eNum;
	private double eMoney;
	private double ePrice;
	private int kindId;
	private String kindNm;
	private int maxAtNo;
	private int kindPId=-1;
	private String createTime;
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public double getRkMoney() {
		return rkMoney;
	}
	public void setRkMoney(double rkMoney) {
		double newRkMoney = NumUtil.trim0(rkMoney);
		this.rkMoney = newRkMoney;
	}
	public int getKindPId() {
		return kindPId;
	}
	public void setKindPId(int kindPId) {
		this.kindPId = kindPId;
	}
	public int getMaxAtNo() {
		return maxAtNo;
	}
	public void setMaxAtNo(int maxAtNo) {
		this.maxAtNo = maxAtNo;
	}
	public String getKindNm() {
		return kindNm;
	}
	public void setKindNm(String kindNm) {
		this.kindNm = kindNm;
	}
	public int getKindId() {
		return kindId;
	}
	public void setKindId(int kindId) {
		this.kindId = kindId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
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
	public double getsNum() {
		return NumUtil.trim0(sNum);
	}
	public void setsNum(double sNum) {
		this.sNum = NumUtil.trim0(sNum);
	}
	public double getsMoney() {
		return NumUtil.trim0(sMoney);
	}
	public void setsMoney(double sMoney) {
		this.sMoney = NumUtil.trim0(sMoney);
	}
	public double getsPrice() {
		return sPrice;
	}
	public void setsPrice(double sPrice) {
		this.sPrice = sPrice;
	}
	public double getRkNum() {
		return NumUtil.trim0(rkNum);
	}
	public void setRkNum(double rkNum) {
		this.rkNum = NumUtil.trim0(rkNum);
	}
	public double getRkPrice() {
		return rkPrice;
	}
	public void setRkPrice(double rkPrice) {
		this.rkPrice = rkPrice;
	}
	public double getCkNum() {
		return NumUtil.trim0(ckNum);
	}
	public void setCkNum(double ckNum) {
		this.ckNum = NumUtil.trim0(ckNum);
	}
	public double getCkMoney() {
		return NumUtil.trim0(ckMoney);
	}
	public void setCkMoney(double ckMoney) {
		this.ckMoney = NumUtil.trim0(ckMoney);
	}
	public double getCkPrice() {
		return ckPrice;
	}
	public void setCkPrice(double ckPrice) {
		this.ckPrice = ckPrice;
	}
	public double geteNum() {
		return NumUtil.trim0(eNum);
	}
	public void seteNum(double eNum) {
		this.eNum = NumUtil.trim0(eNum);
	}
	public double geteMoney() {
		return NumUtil.trim0(eMoney);
	}
	public void seteMoney(double eMoney) {
		this.eMoney = NumUtil.trim0(eMoney);
	}
	public double getePrice() {
		return ePrice;
	}
	public void setePrice(double ePrice) {
		this.ePrice = ePrice;
	}
	
	
	
}
