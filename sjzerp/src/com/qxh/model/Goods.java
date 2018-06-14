package com.qxh.model;

public class Goods {
	
	private int atNo=-1;
	private String code="";
	private String goodsNm="";
	private String alias="";
	private int kindId=-1;
	private int baseUnit=-1;
	private String unitNm="";
	private String spec="";
	private int illegal=0;
	private String createTime;
	private String updateTime;
	private String tradiNm="";
	private String groupStandardCode="";
	private String shortNm="";
	private int assistUnit=-1;
	private int sequenceUnit=-1;
	private String helpCode="";
	private String barCode="";
	private String approvalNumber="";
	private String picNumber="";
	private String assistAttr="";
	private double grossWeight=0;
	private double netWeight=0;
	private int weightUnit=-1;
	private double length=0;
	private double width=0;
	private double height=0;
	private int lengthUnit=-1;
	private double volume=0;
	private int volumeUnit=-1;
	private String equipAttr="";
	private String tradeMark="";
	private String brand="";
	private String pic="";
	private String depict="";
	private String keyword="";
	private String remark="";
	private String summary="";
	private String firstLetter="";
	private String englishNm="";
	private String foreignNm="";
	private String groupId="";
	private String criteria="";
	private String spell="";
	private double minUnit=0;
	private int minUnitNm=-1;
	
	//扩展
	private String kindNm;
	
	public String getUnitNm() {
		return unitNm;
	}
	public void setUnitNm(String unitNm) {
		this.unitNm = unitNm;
	}
	public String getKindNm() {
		return kindNm;
	}
	public void setKindNm(String kindNm) {
		this.kindNm = kindNm;
	}
	public int getAtNo() {
		return atNo;
	}
	public void setAtNo(int atNo) {
		this.atNo = atNo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getGoodsNm() {
		return goodsNm;
	}
	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getKindId() {
		return kindId;
	}
	public void setKindId(int kindId) {
		this.kindId = kindId;
	}
	public int getBaseUnit() {
		return baseUnit;
	}
	public void setBaseUnit(int baseUnit) {
		this.baseUnit = baseUnit;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public int getIllegal() {
		return illegal;
	}
	public void setIllegal(int illegal) {
		this.illegal = illegal;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getTradiNm() {
		return tradiNm;
	}
	public void setTradiNm(String tradiNm) {
		this.tradiNm = tradiNm;
	}
	public String getGroupStandardCode() {
		return groupStandardCode;
	}
	public void setGroupStandardCode(String groupStandardCode) {
		this.groupStandardCode = groupStandardCode;
	}
	public String getShortNm() {
		return shortNm;
	}
	public void setShortNm(String shortNm) {
		this.shortNm = shortNm;
	}
	public int getAssistUnit() {
		return assistUnit;
	}
	public void setAssistUnit(int assistUnit) {
		this.assistUnit = assistUnit;
	}
	public int getSequenceUnit() {
		return sequenceUnit;
	}
	public void setSequenceUnit(int sequenceUnit) {
		this.sequenceUnit = sequenceUnit;
	}
	public String getHelpCode() {
		return helpCode;
	}
	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getApprovalNumber() {
		return approvalNumber;
	}
	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}
	public String getPicNumber() {
		return picNumber;
	}
	public void setPicNumber(String picNumber) {
		this.picNumber = picNumber;
	}
	public String getAssistAttr() {
		return assistAttr;
	}
	public void setAssistAttr(String assistAttr) {
		this.assistAttr = assistAttr;
	}
	public double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public double getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(double netWeight) {
		this.netWeight = netWeight;
	}
	public int getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(int weightUnit) {
		this.weightUnit = weightUnit;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public int getLengthUnit() {
		return lengthUnit;
	}
	public void setLengthUnit(int lengthUnit) {
		this.lengthUnit = lengthUnit;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public int getVolumeUnit() {
		return volumeUnit;
	}
	public void setVolumeUnit(int volumeUnit) {
		this.volumeUnit = volumeUnit;
	}
	public String getEquipAttr() {
		return equipAttr;
	}
	public void setEquipAttr(String equipAttr) {
		this.equipAttr = equipAttr;
	}
	public String getTradeMark() {
		return tradeMark;
	}
	public void setTradeMark(String tradeMark) {
		this.tradeMark = tradeMark;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getDepict() {
		return depict;
	}
	public void setDepict(String depict) {
		this.depict = depict;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getFirstLetter() {
		return firstLetter;
	}
	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}
	public String getEnglishNm() {
		return englishNm;
	}
	public void setEnglishNm(String englishNm) {
		this.englishNm = englishNm;
	}
	public String getForeignNm() {
		return foreignNm;
	}
	public void setForeignNm(String foreignNm) {
		this.foreignNm = foreignNm;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public String getSpell() {
		return spell;
	}
	public void setSpell(String spell) {
		this.spell = spell;
	}
	public double getMinUnit() {
		return minUnit;
	}
	public void setMinUnit(double minUnit) {
		this.minUnit = minUnit;
	}
	public int getMinUnitNm() {
		return minUnitNm;
	}
	public void setMinUnitNm(int minUnitNm) {
		this.minUnitNm = minUnitNm;
	}
	
}
