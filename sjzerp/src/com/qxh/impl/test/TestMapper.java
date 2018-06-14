package com.qxh.impl.test;

import java.util.Map;

public interface TestMapper {

	void insertStructure(Map<String, Object> param);

	void insertDiningCenter(Map<String, Object> param);

	void insertUser(Map<String, Object> param);

	void insertUserRel(Map<String, Object> param);

	void addGoods(Map<String, Object> param);

	void addGoodsDetail(Map<String, Object> param);

	void addCenterGoods(Map<String, Object> param);

	void insertSupplierAttr(Map<String, Object> param);

	void addSupplierGoods(Map<String, Object> param);

	void addStock(Map<String, Object> param);

	void addDemandListH(Map<String, Object> param);

	void addTeamDemand(Map<String, Object> param);

	void addDemandListD(Map<String, Object> param);

	void addPurchaseListH(Map<String, Object> param);

	void addPurchaseListD(Map<String, Object> param);

	void addStockFixH(Map<String, Object> param);

	void addTmpGoods(Map<String, Object> param);

	void addStockFixD(Map<String, Object> param);

	void addStock1(Map<String, Object> param);

	void addStockRecord(Map<String, Object> param);
	
}
