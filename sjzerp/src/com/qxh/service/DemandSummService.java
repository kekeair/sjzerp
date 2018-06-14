package com.qxh.service;

import com.qxh.utils.Result;

public interface DemandSummService {

	/**
	 * 查询销售汇总
	 * @param stime
	 * @param etime
	 * @param customerId 
	 * @param goodsNm
	 * @param kindCode
	 * @return
	 */
	Result getDemandSummary(String stime, String etime,int structId, String customerId,String goodsNm
			,String kindCode);

	/**
	 * @description : [根据物料查询销售汇总]
	 * @param goodsId
	 * @param goodsType
	 * @param stime
	 * @param etime
	 * @param structId
	 * @param demandListDId
	 * @param customerId
	 * @return
	 * @时间:2016年12月8日下午7:33:26
	 */
	Result getDemandSummByGoods(String goodsId, String goodsType, String stime, String etime, int structId,
			String demandListDId, String customerId);

	
}
