package com.qxh.exmodel;

import com.qxh.utils.NumUtil;
import com.sun.swing.internal.plaf.metal.resources.metal;

/**
 * 
 * @author Mr chen
 * @name : BillAndCustomTeam
 * @todo : [导出实体类]
 * 
 *       创建时间 ： 2016年11月24日上午10:11:49
 */
public class BillAndCustomTeam {

	private int billId;
	private String code; // 物料编号
	private String teamNm;
	private String goodsNm; // 物料名称
	private String unitNm; // 单位
	private String demandNum;
	private String spec; // 规格
	private Double price;
	private String billDate;
	private Double totalMonay = 0.00;
	private int orderIndex; // 序号
	private String rewmark; // 备注
	private String orderNum; // 数量
	private String codeNm; // 账单编号
	private String customNm;
	private Integer atNo;
	private String userNm;

	private String goodsType; // 物料类型 -1:临时,0:常规
	private String remark;
	private String goodsId;
	private String goodsCode;
	private String num;
	private Double money;
	private String customerNm;
	

	// ================G/S===================

	public String getCodeNm() {
		return codeNm;
	}

	public String getCustomerNm() {
		return customerNm;
	}

	public void setCustomerNm(String customerNm) {
		this.customerNm = customerNm;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		double trim0 = NumUtil.trim0(money);
		this.money = trim0;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		double trim0 = NumUtil.trim0(Double.parseDouble(num));
		String sNum = String.valueOf(trim0);
		this.num = sNum;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getAtNo() {
		return atNo;
	}

	public void setAtNo(Integer atNo) {
		this.atNo = atNo;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getCustomNm() {
		return customNm;
	}

	public void setCustomNm(String customNm) {
		this.customNm = customNm;
	}

	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		double trim0 = NumUtil.trim0(Double.parseDouble(orderNum));
		String sOrderNum = String.valueOf(trim0);
		this.orderNum = sOrderNum;
	}

	public String getRewmark() {
		return rewmark;
	}

	public void setRewmark(String rewmark) {
		this.rewmark = rewmark;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Double getTotalMonay() {
		totalMonay = Double.parseDouble(demandNum) * price;
		double trim0 = NumUtil.trim0(totalMonay);
		return trim0;
	}

	public void setTotalMonay(Double totalMonay) {
		double trim0 = NumUtil.trim0(totalMonay);
		this.totalMonay = trim0;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTeamNm() {
		return teamNm;
	}

	public void setTeamNm(String teamNm) {
		this.teamNm = teamNm;
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

	public String getDemandNum() {
		return demandNum;
	}

	public void setDemandNum(String demandNum) {
		double trim0 = NumUtil.trim0(Double.parseDouble(demandNum));
		String sDemandNum = String.valueOf(trim0);
		this.demandNum = sDemandNum;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		double trim0 = NumUtil.trim0(price);
		this.price = trim0;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

}
