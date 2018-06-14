package com.qxh.service;

import com.qxh.utils.Result;

public interface StockChangeService {

	/**
	 * 库存变动记录
	 * @param stime
	 * @param etime
	 * @return
	 */
	Result getStockChangeRec(String stime, String etime,int structId,
			String name,String kindCode,String goodsCode);
	
	
}
