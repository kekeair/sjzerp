package com.qxh.service;

import java.util.ArrayList;
import java.util.List;

import com.qxh.exmodel.BillAndCustomTeam;
import com.qxh.exmodel.EditInOutD;
import com.qxh.exmodel.ExcelDemandBillByCondition;
import com.qxh.exmodel.GetDemandListByCustomerId;
import com.qxh.model.DemandListD;
import com.qxh.model.DemandListH;
import com.qxh.utils.Result;

public interface DemandService {

	/**
	 * 查询作业组列表
	 * 
	 * @param atNo
	 * @return
	 */
	Result getTeamList(String customerId, String teamId, String type);

	/**
	 * 查询需求上报列表
	 * 
	 * @param state
	 * @param stime
	 * @param etime
	 * @param page
	 * @param structId
	 * @return
	 */
	Result getDemandListH(String state, String stime, String etime, String page, int structId, int roleId,
			String customerId);

	/**
	 * 按作业组查看需求上报
	 * 
	 * @param billId
	 * @return
	 */
	Result getDemandByGroup(String billId, int structId, String customerId, String teamDemandId);

	/**
	 * 经理审核订单
	 * 
	 * @param changeJson
	 * @param delDId
	 * @param billId
	 * @return
	 */
	Result dealDemandBill(String billId, String reviewState, int structId, int userId, String operType, String remark,
			String userNm, String billDate, String newList, String editList, String delAtNo, String headAtNo,
			String customerId, String teamId);

	/**
	 * 清空作业组上报的物料
	 * 
	 * @param teamDemandId
	 * @return
	 */
	Result clearTeamDemand(String teamDemandId, String teamId);

	/**
	 * 删除订单
	 * 
	 * @param billId
	 * @return
	 */
	Result delDemandBill(String billId);

	/**
	 * 客户列表
	 * 
	 * @param structId
	 * @return
	 */
	Result getCustomerList(int structId, String billId, int roleId);

	/**
	 * @description : [获取导出的集合]
	 * @param teamBillId
	 * @param billId
	 * @return
	 * @时间:2016年11月24日上午9:32:45
	 */
	Result getExclList(String teamBillId, String billId);

	/**
	 * @description : []
	 * @param teamBillId
	 * @param billId
	 * @return
	 * @时间:2016年12月10日下午1:22:30
	 */
	List<BillAndCustomTeam> getExportOutDemandBill(String billId, String customerTeamId);

	/**
	 * @description : [客户申报汇总但]
	 * @param state
	 * @param stime
	 * @param etime
	 * @param page
	 * @param structId
	 * @param roleId
	 * @return
	 * @时间:2016年12月20日上午11:36:57
	 */
	List<DemandListH> getDemandListHForExcel(String state, String stime, String etime, String page, int structId,
			int roleId, String customerId);

	/**
	 * 
	 * @description : [按物料查询]
	 * @param billId
	 * @param structId
	 * @param type
	 * @param goodsNm
	 * @param customerId
	 * @return
	 * @修改时间:2016年12月22日下午5:18:10
	 */
	Result selectDemandGoodsByGoods(String billId, int structId, String type, String goodsNm, String customerId);

	/**
	 * @description : [按客户查看]
	 * @param billId
	 * @param structId
	 * @param type
	 * @param customerId
	 * @return
	 * @时间:2016年12月22日下午5:16:54
	 */
	Result selectDemandGoodsByCustomer(String billId, int structId, String type, String customerId);

	/**
	 * @description : [客户汇总导出]
	 * @param state
	 * @param stime
	 * @param etime
	 * @param structId
	 * @param roleId
	 * @param customerId
	 * @return
	 * @时间:2016年12月24日上午8:51:07
	 */
	List<ExcelDemandBillByCondition> getExcelOutDemandListHBill(String state, String stime, String etime, int structId,
			int roleId, String customerId);

	/**
	 * 入库单详情
	 * 
	 * @param billId
	 * @return
	 */
	Result getDemandD(String billId);

	/**
	 * 获取物料售价
	 * 
	 * @param customerId
	 * @param goodsId
	 * @param structId
	 * @return
	 */
	Result getGoodsPrice(String customerId, String goodsId);

	/**
	 * 保存需求单详情
	 * 
	 * @param changeJson
	 * @return
	 */
	Result saveDemandD(String billId, String newList, String editList, String delAtNo, String headAtNo,
			String customerId, String teamId, int structId, int userId);

	/**
	 * @description : [获取申请物料的列表]
	 * @param teamDemandId
	 * @param teamId
	 * @return
	 * @时间:2017年1月5日下午3:31:23
	 */
	Result getAllDemandGoodsListByTeamId(String teamDemandId, String teamId);

	/**
	 * @description : [获取出库详情列表]
	 * @param billId
	 * @param customerTeamId
	 * @return
	 * @时间:2017年1月9日上午9:26:46
	 */
	List<GetDemandListByCustomerId> getDemandListByCustomerId(String billId, String customerTeamId);

	/**
	 * @description : [获取物料价格的集合]
	 * @param structId
	 * @param customerId
	 * @param redisGoodsIds
	 * @return
	 * @时间:2017年1月10日上午10:26:28
	 */
	Result getRedisGoodsPriceListBykey(int structId, String customerId, String redisGoodsIds);

	/**
	 * @description : [将出单的数据存入redis中]
	 * @param billId
	 * @param customerTeamId
	 * @param editListStr
	 * @param newListStr
	 * @return
	 * @时间:2017年1月10日下午8:43:25
	 */
	Result storageDataAction(String billId, String customerTeamId, String editListStr, String newListStr);

	/**
	 * @description : [打印出库单详情]
	 * @param key
	 * @return
	 * @时间:2017年1月10日下午9:01:00
	 */
	ArrayList<GetDemandListByCustomerId> getDataListByKey(String key);

	/**
	 * @description : [通过时间和客户id查找账单]
	 * @param customerId
	 * @param structId
	 * @param etimeM
	 * @param stimeM
	 * @return
	 * @时间:2017年1月15日上午9:18:09
	 */
	List<DemandListD> getDemandBillByCustomerId(String customerId, int structId, String stimeM, String etimeM);

	/**
	 * @description : [按条件查询物料]
	 * @param billId
	 * @param teamId
	 * @param codes
	 *            物料分类编码拼接的字符串
	 * @return
	 * @时间:2016年12月10日下午1:22:30
	 */
	List<BillAndCustomTeam> getGoodsByTerm(String billId, String teamId, String codes);

	/**
	 * @description : [保存出库修改的做账信息]
	 * @param billId
	 * @param structId
	 * @param customerId
	 * @param customerTeamId
	 * @param editListStr
	 * @param newListStr
	 * @return
	 * @时间:2017年1月10日下午8:43:25
	 */
	Result saveMakeBill(String billId, Integer structId, String customerId, String customerTeamId, String delAtNo,
			String editListStr, String newListStr);

	/**
	 * @description : [导出makeBill]
	 * @param billId
	 * @param teamId
	 * @return
	 * @时间:2016年12月10日下午1:22:30
	 */
	List<GetDemandListByCustomerId> getMakeBillByBIllIdAndTeanId(String billId, String teamId);
}
