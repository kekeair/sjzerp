package com.qxh.impl.stockQuery;

import java.util.List;
import java.util.Map;

import com.qxh.model.Stock;
import com.qxh.model.StockRecord;

public interface StockQueryMapper {

	/**
	 * 查询库存列表
	 * @param param
	 * @return
	 */
	List<Stock> getStockList(Map<String, Object> param);
	/**
	 * 查询库存列表数量
	 * @param param
	 * @return
	 */
	int getStockListNum(Map<String, Object> param);
	/**
	 * 查询库存记录
	 * @param param
	 * @return
	 */
	List<StockRecord> getStockRecord(Map<String, Object> param);
	/**
	 * 查询库存记录数量
	 * @param param
	 * @return
	 */
	int getStockRecordNum(Map<String, Object> param);

	
}
