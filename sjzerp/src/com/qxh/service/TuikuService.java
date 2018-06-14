package com.qxh.service;

import java.util.List;

import com.qxh.exmodel.ExportTuikuModel;
import com.qxh.utils.Result;

public interface TuikuService {

	/**
	 * 退库列表
	 * @param state
	 * @param stime
	 * @param etime
	 * @param page
	 * @param structId
	 * @param roleId
	 * @return
	 */
	Result getTuikuListH(String state, String stime, String etime, String page, 
			int structId, int roleId,String customerId);
	/**
	 * 查询退库物料
	 * @param billId
	 * @return
	 */
	Result getTuikuGoodsByHeadAtNo(String billId);
	/**
	 * 删除订单
	 * @param billId
	 * @return
	 */
	Result delTuikuBill(String billId);
	/**
	 * 处理订单
	 * @param billId
	 * @param reviewState
	 * @param structId
	 * @param atNo
	 * @param operType
	 * @param remark
	 * @param userNm
	 * @param billDate
	 * @return
	 */
	Result dealTuikuBill(String billId, String reviewState, int structId, int userId, String operType, String remark,
			String userNm, String billDate,String newList,String editList,
			String delAtNo,String customerId,String customerNm);
	/**
	 * 查看退库详情
	 * @param billId
	 * @return
	 */
	Result viewTuikuD(String billId);
	/**
	 * 导出退库单汇总
	 * @param state
	 * @param stime
	 * @param etime
	 * @param customerId
	 * @param structId
	 * @return
	 */
	List<ExportTuikuModel> exportTuikuList(String state, String stime, 
			String etime, String customerId, int structId);
	/**
	 * @description : [查询退库列表]
	 * @param billId
	 * @param customerId 
	 * @return
	 * @时间:2017年1月6日下午3:17:18
	 */
	Result getTuikuGoodsListByHeadAtNo(String billId, String customerId);
	/**
	 * @description : [保存退库列表]
	 * @param structId
	 * @param atNo
	 * @param newList
	 * @param editList
	 * @param delAtNo
	 * @param customerId
	 * @param billId
	 * @param customerNm
	 * @return
	 * @时间:2017年1月6日下午5:39:32
	 */
	Result saveTuikuD(int structId, int atNo, String newList, String editList, String delAtNo, String customerId,
			String billId, String customerNm);

	
}
