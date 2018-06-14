package com.qxh.exmodel;

import java.util.ArrayList;
import java.util.List;

public class GroupDemand {
	
	private int state;//0未申报1已申报
	private int teamId;
	private String teamNm;
	private List<DemandGoods> goodsList=new ArrayList<>();
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeamNm() {
		return teamNm;
	}
	public void setTeamNm(String teamNm) {
		this.teamNm = teamNm;
	}
	public List<DemandGoods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<DemandGoods> goodsList) {
		this.goodsList = goodsList;
	}
}
