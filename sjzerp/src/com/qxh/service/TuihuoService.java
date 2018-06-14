package com.qxh.service;

import java.util.List;

import com.qxh.exmodel.ExportTuihuoModel;
import com.qxh.utils.Result;

public interface TuihuoService {

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
	Result getTuihuoListH(String state, String stime, String etime, String page, 
			int structId, int roleId,String supplierId);
	/**
	 * 查询退库物料
	 * @param billId
	 * @return
	 */
	Result getTuihuoGoodsByHeadAtNo(String billId);
	/**
	 * 删除订单
	 * @param billId
	 * @return
	 */
	Result delTuihuoBill(String billId);
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
	Result dealTuihuoBill(String billId, String reviewState, int structId, int userId, String operType, String remark,
			String userNm, String billDate,String newList,String editList,
			String delAtNo,String supplierId,String supplierNm);
	/**
	 * 查看退库详情
	 * @param billId
	 * @return
	 */
	Result viewTuihuoD(String billId);
	/**
	 * 导出退库单汇总
	 * @param state
	 * @param stime
	 * @param etime
	 * @param supplierId
	 * @param structId
	 * @return
	 */
	List<ExportTuihuoModel> exportTuihuoList(String state, String stime, 
			String etime, String supplierId, int structId);
	/**
	 * 保存退货单详情
	 * @param newList
	 * @param editList
	 * @param delAtNo
	 * @param billId
	 * @param supplierId
	 * @return
	 */
	Result saveTuihuoD(String newList, String editList, String delAtNo, 
			String billId, String supplierId,String supplierNm,
			int structId,int userId);
	
}
