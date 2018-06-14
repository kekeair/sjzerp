package com.qxh.impl.tuihuo;

import java.util.List;
import java.util.Map;

import com.qxh.exmodel.BaseGoods;
import com.qxh.exmodel.BaseStockGoods;
import com.qxh.exmodel.EditInOutD;
import com.qxh.exmodel.ExportTuihuoModel;
import com.qxh.model.TuihuoH;

public interface TuihuoMapper {
	
	/**
	 * 退库单列表
	 * @param param
	 * @return
	 */
	List<TuihuoH> getTuihuoListH(Map<String, Object> param);
	/**
	 * 退库单数量
	 * @param param
	 * @return
	 */
	int getTuihuoListHNum(Map<String, Object> param);
	/**
	 * 根据头表id查询退库物料
	 * @param param
	 * @return
	 */
	List<EditInOutD> getTuihuoGoodsByHeadAtNo(Map<String, Object> param);
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
	void addTuihuoH(Map<String, Object> param);
	/**
	 * 更新退库单客户
	 * @param param
	 */
	void updateTuihuoSupplier(Map<String, Object> param);
	/**
	 * 根据单据id删除退库单详情
	 * @param param
	 */
	void delTuihuoDByBillId(Map<String, Object> param);
	/**
	 * 删除退库单
	 * @param param
	 */
	void delTuihuoBill(Map<String, Object> param);
	/**
	 * 查询退库单状态
	 * @param param
	 * @return
	 */
	Integer getTuihuoHState(Map<String, Object> param);
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
	void updateTuihuoHState(Map<String, Object> param);
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
	List<BaseGoods> viewTuihuoD(Map<String, Object> param);
	/**
	 * 根据id查询退库表头
	 * @param param
	 * @return
	 */
	TuihuoH getTuihuoHById(Map<String, Object> param);
	/**
	 * 导出退库单汇总
	 * @param param
	 * @return
	 */
	List<ExportTuihuoModel> exportTuihuoList(Map<String, Object> param);
	/**
	 * 批量添加tuihuoD
	 * @param param
	 */
	void batchAddTuihuoD(Map<String, Object> param);
	/**
	 * 批量更新tuihuoD
	 * @param param
	 */
	void batchUpdateTuihuoD(Map<String, Object> param);
	/**
	 * 批量删除tuihuoD
	 * @param param
	 */
	void batchDelTuihuoD(Map<String, Object> param);
}
