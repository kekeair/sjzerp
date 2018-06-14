package com.qxh.service;

import java.util.List;
import java.util.Map;

import com.qxh.exmodel.BillAndCustomTeam;
import com.qxh.exmodel.ExcelDemandBillByCondition;
import com.qxh.model.ExcelPurchaseBillByCondition;
import com.qxh.model.User;
import com.qxh.utils.Result;

public interface PurchaseService {

	/**
	 * 查找采购单列表
	 * @param state
	 * @param stime
	 * @param etime
	 * @param page
	 * @param structId
	 * @return
	 */
	Result getPurchaseListH(String state, String stime, String etime, String page, 
			int structId,int roleId,String customeId);
	/**
	 * 处理采购单
	 * @param billId
	 * @param changeJson
	 * @param reviewState
	 * @param structId
	 * @return
	 */
	Result dealPurchaseBill(String billId,String reviewState, 
			int structId,int userId,String userNm,String operType,String remark,
			String billDate,String newList,String editList,
			String delAtNo,String customerId,String teamId);
	/**
	 * 获取作业组列表
	 * @param user
	 * @return
	 */
	Result getTeamList(User user,String teamId);
	/**
	 * 供应商选择数据列表
	 * @param billId
	 * @return
	 */
	Result getSuppliserSelData(String billId,int structId);
	/**
	 * 选择供应商
	 * @param billId
	 * @param supplierJson
	 * @param structId
	 * @return
	 */
	Result supplierSel(String billId, String supplierJson, int structId,
			int userId,String userNm,String remark);
	/**
	 * 入库保存
	 * @param billId
	 * @param supplierId
	 * @param changeJson
	 * @return
	 */
	Result inStorageSave(String billId, String supplierId, String changeJson);
	/**
	 * 删除采购单
	 * @param billId
	 * @return
	 */
	Result delPurchaseBill(String billId);
	/**
	 * 采购汇总单据列表打印
	 * @param centerId
	 * @param code
	 * @param userNm
	 * @param page
	 * */
	Result getPurchaseTotalList(String centerId,String code,String userNm,String page);

    /**
     * 采购汇总单据列表导出
     * */
	Map<String,Object> exportPurchaseTotallList(int centerId,String billId,String supplierId);

	/**
	 * 采购入库导出
	 * */
	Map<String,Object> exportPurchaseInStrockList(String centerId,String code,String supplierId);

	/**
	 * @description : [采购明细导出]
	 * @param teamBillId
	 * @param billId
	 * @return
	 * @时间:2016年11月25日上午9:38:49
	 */
	Result getExclList(String teamBillId, String billId);
	/**
	 * 查询供应商
	 * @param billId
	 * @return
	 */
	Result getExportSupplier(String billId);
	/**
	 * @description : []
	 * @param billId
	 * @param structId
	 * @return
	 * @时间:2016年11月29日上午8:48:26
	 */
	Result getSuppliserSelExist(String billId, int structId);
	/**
	 * @description : [获取集合]
	 * @param teamBillId
	 * @param billId
	 * @return
	 * @时间:2016年12月13日下午5:42:16
	 */
	List<BillAndCustomTeam> getExportOutPurchaseBill(String teamBillId, String billId);
	/**
	 * @description : [按物料查看]
	 * @param billId
	 * @param structId
	 * @param type
	 * @param goodsNm
	 * @return
	 * @时间:2016年12月23日上午11:32:35
	 */
	Result selectPurchaseGoodsByGoods(String billId, int structId, String type, String goodsNm);
	/**
	 * @description : [按客户查看]
	 * @param billId
	 * @param structId
	 * @param type
	 * @return
	 * @时间:2016年12月23日下午2:09:21
	 */
	Result selectPurchaseGoodsByGroup(String billId, int structId, String type);
	/**
	 * @description : [按照供应商进行查看]
	 * @param billId
	 * @param structId
	 * @param type
	 * @return
	 * @时间:2016年12月23日下午3:20:37
	 */
	Result selectPurchaseGoodsBySupplier(String billId, int structId, String type);
	/**
	 * 查询入库单详情
	 * @param billId
	 * @return
	 */
	Result getPurchaseD(String billId);
	/**
	 * 保存入库单详情
	 * @param changeJson
	 * @return
	 */
	Result savePurchaseD(String newList,String editList,String delAtNo,
			String billId,String customerId,String teamId,int structId,int userId);
	/**
	 * 采购入库汇总打印
	 * @param state
	 * @param etime
	 * @param stime
	 * @param structId
	 * @param role
	 * @param customerId
	 * @return
	 */
	public List<ExcelPurchaseBillByCondition> getExcelOutPurchaseListHBill(String state, String stime, String etime,
			int structId, int roleId, String customerId);
	
}
