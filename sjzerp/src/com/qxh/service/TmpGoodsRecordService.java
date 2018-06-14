package com.qxh.service;

import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : TmpGoodsRecordService
 * @todo : [临时库存接口]
 * 
 * 创建时间 ： 2016年11月23日上午9:45:25
 */
public interface TmpGoodsRecordService {

	/**
	 * @description : [获取列表]
	 * @param stime
	 * @param etime
	 * @param page
	 * @param structId
	 * @param roleId
	 * @return
	 * @时间:2016年11月23日上午9:54:50
	 */
	Result getTmpGoodsRecordList(String stime, String etime, String page, int structId, int roleId);

}
