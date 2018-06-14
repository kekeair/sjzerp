package com.qxh.impl.demand;

import java.util.List;
import java.util.Map;

import com.qxh.exmodel.BillAndCustomTeam;
import com.qxh.exmodel.DemandDetail;
import com.qxh.exmodel.DemandGoods;
import com.qxh.exmodel.EditInOutD;
import com.qxh.exmodel.ExcelDemandBillByCondition;
import com.qxh.exmodel.GetDemandListByCustomerId;
import com.qxh.exmodel.GroupDemand;
import com.qxh.model.Customer;
import com.qxh.model.DemandListD;
import com.qxh.model.DemandListH;

public interface DemandMapper {
	/**
	 * 生成需求上报单头表
	 * @param param
	 */
	void addDemandListH(Map<String, Object> param);
	/**
	 * 添加作业组需求
	 * @param param
	 */
	Integer addTeamDemand(Map<String, Object> param);
	/**
	 * 查询需求上报列表
	 * @param param
	 * @return
	 */
	List<DemandListH> getDemandListH(Map<String, Object> param);
	/**
	 * 查询需求上报列表
	 * @param param
	 * @return
	 */
	int getDemandListHNum(Map<String, Object> param);
	/**
	 * 按作业组查看需求上报
	 * @param param
	 * @return
	 */
	List<GroupDemand> getDemandByGroup(Map<String, Object> param);
	/**
	 * 查询各作业组需求的物料
	 * @param param
	 * @return
	 */
	List<DemandGoods> getGoodsByGroupDemand(Map<String, Object> param);
	/**
	 * 查询需求上报单状态
	 * @param param
	 * @return
	 */
	Integer getDemandListHState(Map<String, Object> param);
	/**
	 * 根据id串删除需求物料
	 * @param param
	 */
	void delDemandListDById(Map<String, Object> param);
	/**
	 * 批量更新需求物料
	 * @param param
	 */
	void batchUpdateDemandListD(Map<String, Object> param);
	/**
	 * 更新需求单据状态
	 * @param param
	 */
	void updateDemandListHState(Map<String, Object> param);
	/**
	 * 检查库存前查询需求详情
	 * @param param
	 * @return
	 */
	List<DemandListD> getDemandListDBeforeCheckStock(Map<String, Object> param);
	/**
	 * 更新库存
	 * @param param
	 * @return
	 */
	int reduceStock(Map<String, Object> param);
	/**
	 * 增加库存记录
	 * @param param
	 */
	void addOutStockRecord(Map<String, Object> param);
	/**
	 * 查询待采购单id
	 * @param param
	 * @return
	 */
	Integer getUnPurchaseHId(Map<String, Object> param);
	/**
	 * 更新需求头表的采购单id
	 * @param param
	 */
	void updateDemandHPurchaseId(Map<String, Object> param);
	/**
	 * 查询最大单据号
	 * @param param
	 * @return
	 */
	Integer getMaxCodeOrder(Map<String, Object> param);
	/**
	 * 查询采购单最大单据号
	 * @param param
	 * @return
	 */
	Integer getPurchaseMaxCodeOrder(Map<String, Object> param);
	/**
	 * 根据id查询单据编号
	 * @param param
	 * @return
	 */
	String getBillCodeById(Map<String, Object> param);
	/**
	 * 清空作业组上报的物料
	 * @param param
	 */
	void clearTeamDemand(Map<String, Object> param);
	/**
	 * 删除订单
	 * @param param
	 */
	void delDemandBill(Map<String, Object> param);
	/**
	 * 获取中间表id
	 * @param param
	 * @return
	 */
	List<Integer> getTeamDIdList(Map<String, Object> param);
	/**
	 * 删除订单物料
	 * @param param
	 */
	void delDemandDByParent(Map<String, Object> param);
	/**
	 * 删除中间表
	 * @param param
	 */
	void delTeamDemandByParent(Map<String, Object> param);
	/**
	 * 客户列表
	 * @return
	 */
	List<Customer> getCustomerList(Map<String, Object> param);
	/**
	 * @description : [通过采购单id获取作业组id]
	 * @param param
	 * @return
	 * @时间:2016年11月23日下午6:00:53
	 */
	List<DemandListD> getTeamIdListByBillId(Map<String, Object> param);
	/**
	 * @description : [获取导出集合]
	 * @param param
	 * @return
	 * @时间:2016年11月24日上午9:44:31
	 */
	List<BillAndCustomTeam> getExclList(Map<String, Object> param);
	/**
	 * 影响库存前查询申报详细
	 * @param param
	 * @return
	 */
	List<DemandDetail> getDemandListDBeforeEffectStock(Map<String, Object> param);
	/**
	 * 查询标签价格
	 * @param param
	 * @return
	 */
	Double getTagPrice(Map<String, Object> param);
	/**
	 * @description : [按物料查]
	 * @param param
	 * @return
	 * @时间:2016年12月22日上午10:12:56
	 */
	List selectDemandGoodsByGoods(Map<String, Object> param);
	/**
	 * @description : [通过条件导出客户申报单]
	 * @param param
	 * @return
	 * @时间:2016年12月24日上午9:03:05
	 */
	List<ExcelDemandBillByCondition> getExcelOutDemandListHBill(Map<String, Object> param);
	/**
	 * 查询入库单详情
	 * @param param
	 * @return
	 */
	List<EditInOutD> getDemandD(Map<String, Object> param);
	/**
	 * 批量增加demandListD
	 * @param param
	 */
	void batchAddDemandD(Map<String, Object> param);
	/**
	 * 批量更新demandListD
	 * @param param
	 */
	void batchUpdateDemandD(Map<String, Object> param);
	/**
	 * 批量删除demandListD
	 * @param param
	 */
	void batchDelDemandD(Map<String, Object> param);
	/**
	 * @description : [获取申请物料的列表]
	 * @param param
	 * @return
	 * @时间:2017年1月5日下午3:40:07
	 */
	List<EditInOutD> getAllDemandGoodsListByTeamId(Map<String, Object> param);
	/**
	 * @description : [获取出库详情列表]
	 * @param param
	 * @return
	 * @时间:2017年1月9日上午9:34:27
	 */
	List<GetDemandListByCustomerId> getDemandListByCustomerId(Map<String, Object> param);
	/**
	 * @description : [通过时间和客户id查找账单]
	 * @param param
	 * @return
	 * @时间:2017年1月15日上午9:41:49
	 */
	List<DemandListD> getDemandBillByCustomerId(Map<String, Object> param);
	/**
	 * @description : [通过条件查询物料]
	 * @param param
	 * @return
	 * @时间:2017年1月15日上午9:41:49
	 */
	List<BillAndCustomTeam> getGoodsByTerm(Map<String, Object> param);
	/**
	 * @description : [批量添加做账单]
	 * @param param
	 * @return
	 * @时间:2017年1月15日上午9:41:49
	 */
	void saveMakeBill(Map<String, Object> param);
	/**
	 * @description : [通过订单Id查询是否已存在做账单]
	 * @param param
	 * @return
	 * @时间:2017年3月20日上午9:41:49
	 */
	Integer checkMakeBillByBillid(Map<String, Object> param);
	/**
	 * @description : [通过billId查询对账单]
	 * @param param
	 * @return
	 * @时间:2017年1月9日上午9:34:27
	 */
	List<GetDemandListByCustomerId> getMakeBillListByBillId(Map<String, Object> param);
	/**
	 * 批量删除makeBill
	 * @param param
	 */
	void batchDelMakeBill(Map<String, Object> param);
	/**
	 * 批量更新makeBill
	 * @param param
	 */
	void batchUpdateMakeBill(Map<String, Object> param);
	/**
	 * @description : [通过billId获取做账单信息]
	 * @param param
	 * @return
	 * @时间:2017年1月9日上午9:34:27
	 */
	List<GetDemandListByCustomerId> getMakeBillMessageByBillId(Map<String, Object> param);
}
