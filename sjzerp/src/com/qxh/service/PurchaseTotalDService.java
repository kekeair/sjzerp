package com.qxh.service;

import com.qxh.utils.Result;

public interface PurchaseTotalDService {

	/**
	 * 采购汇总明细
	 * @param stime
	 * @param etime
	 * @param structId
	 * @param customerId 
	 * @return
	 */
	Result getPurchaseTotalD(String stime, String etime, int structId,
			String supplierId, String customerId);

}
