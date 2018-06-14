package com.qxh.service;

import com.qxh.utils.Result;

public interface StockQueryService {
	
	/**
	 * 库存列表
	 * @param name
	 * @param kindId
	 * @param page
	 * @param structId
	 * @return
	 */
	Result getStockList(String name, String kindCode, String page, int structId,String goodsCode);
	
	/**
	 * 查询库存记录
	 * @param stockFlag
	 * @param stime
	 * @param etime
	 * @param page
	 * @param goodsId
	 * @param structId
	 * @return
	 */
	Result getStockRecord(String stockFlag, String stime, String etime, String page, 
			String goodsId, int structId);

	
}
