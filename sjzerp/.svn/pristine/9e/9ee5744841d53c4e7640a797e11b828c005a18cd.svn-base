package com.qxh.impl.tuiku;

import java.util.List;
import java.util.Map;

import com.qxh.exmodel.BaseGoods;
import com.qxh.exmodel.BaseStockGoods;
import com.qxh.exmodel.EditInOutD;
import com.qxh.exmodel.ExportTuikuModel;
import com.qxh.model.TuikuH;

public interface TuikuMapper {
	
	/**
	 * 退库单列表
	 * @param param
	 * @return
	 */
	List<TuikuH> getTuikuListH(Map<String, Object> param);
	/**
	 * 退库单数量
	 * @param param
	 * @return
	 */
	int getTuikuListHNum(Map<String, Object> param);
	/**
	 * 根据头表id查询退库物料
	 * @param param
	 * @return
	 */
	List<EditInOutD> getTuikuGoodsByHeadAtNo(Map<String, Object> param);
	/**
	 * 查询最大单据号
	 * @param param
	 * @return
	 */
	Integer getMaxCodeOrder(Map<String, Object> param);
	/**
	 * 添加退库头表
	 * @param param
	 */
	void addTuikuH(Map<String, Object> param);
	/**
	 * 更新退库单客户
	 * @param param
	 */
	void updateTuikuCustomer(Map<String, Object> param);
	/**
	 * 根据单据id删除退库单详情
	 * @param param
	 */
	void delTuikuDByBillId(Map<String, Object> param);
	/**
	 * 删除退库单
	 * @param param
	 */
	void delTuikuBill(Map<String, Object> param);
	/**
	 * 查询退库单状态
	 * @param param
	 * @return
	 */
	Integer getTuikuHState(Map<String, Object> param);
	/**
	 * 根据单据id查询详单数量
	 * @param param
	 * @return
	 */
	int getDetailNumByBillId(Map<String, Object> param);
	/**
	 * 根据id查询单据号
	 * @param param
	 * @return
	 */
	String getBillCodeById(Map<String, Object> param);
	/**
	 * 更新退库单状态
	 * @param param
	 */
	void updateTuikuHState(Map<String, Object> param);
	/**
	 * 影响库存
	 * @param param
	 */
	void effectStock(Map<String, Object> param);
	/**
	 * 影响库存前查询退库物料详情
	 * @param param
	 * @return
	 */
	List<BaseStockGoods> getGoodsBeforeEffectStock(Map<String, Object> param);
	/**
	 * 添加库存记录
	 * @param param
	 */
	void addStockRecord(Map<String, Object> param);
	/**
	 * 查看退库详情
	 * @param param
	 * @return
	 */
	List<BaseGoods> viewTuikuD(Map<String, Object> param);
	/**
	 * 根据id查询退库表头
	 * @param param
	 * @return
	 */
	TuikuH getTuikuHById(Map<String, Object> param);
	/**
	 * 导出退库单汇总
	 * @param param
	 * @return
	 */
	List<ExportTuikuModel> exportTuikuList(Map<String, Object> param);
	/**
	 * @description : [查询退库列表]
	 * @param param
	 * @return
	 * @时间:2017年1月6日下午3:29:08
	 */
	List<BaseGoods> getTuikuGoodsListByHeadAtNo(Map<String, Object> param);
	
	/**
	 * @description : [批量保存]
	 * @param param
	 * @时间:2017年1月6日下午5:59:03
	 */
	void batchAddTuikuD(Map<String, Object> param);
	/**
	 * @description : [批量更新 tuikuD]
	 * @param param
	 * @时间:2017年1月6日下午6:00:40
	 */
	void batchUpdateTuikuD(Map<String, Object> param);
	/**
	 * @description : [批量退库]
	 * @param param
	 * @时间:2017年1月6日下午6:01:45
	 */
	void batchTuikuD(Map<String, Object> param);
}
