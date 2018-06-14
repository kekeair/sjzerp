package com.qxh.impl.stockFix;

import java.util.List;
import java.util.Map;

import com.qxh.exmodel.ListGoods;
import com.qxh.model.CenterGoods;
import com.qxh.model.Stock;
import com.qxh.model.StockFixD;
import com.qxh.model.StockFixH;

public interface StockFixMapper {

	/**
	 * 查询餐饮中心物料
	 * @param param
	 * @return
	 */
	List<CenterGoods> getCenterGoods(Map<String, Object> param);
	/**
	 * 查询餐饮中心物料数量
	 * @param param
	 * @return
	 */
	int getCenterGoodsNum(Map<String, Object> param);
	/**
	 * 查询库存修正单详细
	 * @param param
	 * @return
	 */
	List<StockFixD> getStockFixD(Map<String, Object> param);
	/**
	 * 查询库存修正单列表
	 * @param param
	 * @return
	 */
	List<StockFixH> getStockFixH(Map<String, Object> param);
	/**
	 * 查询库存修正单数量
	 * @param param
	 * @return
	 */
	int getStockFixHNum(Map<String, Object> param);
	/**
	 * 查询库存修正详细数量
	 * @param param
	 * @return
	 */
	int getStockFixDNum(Map<String, Object> param);
	/**
	 * 删除库存修正头表
	 * @param param
	 */
	void delStockFixH(Map<String, Object> param);
	/**
	 * 删除库存修正详细表
	 * @param param
	 */
	void delStockFixD(Map<String, Object> param);
	/**
	 * 查询最大单据号
	 * @param param
	 * @return
	 */
	Integer getMaxCodeOrder(Map<String, Object> param);
	/**
	 * 添加库存修正头表
	 * @param param
	 */
	void addStockFixH(Map<String, Object> param);
	/**
	 * 添加库存修正详细
	 * @param param
	 */
	void addStockFixD(Map<String, Object> param);
	/**
	 * 根据id删除库存修正详细
	 * @param param
	 */
	void delStockFixDById(Map<String, Object> param);
	/**
	 * 检查自定义物料是否重复
	 * @param param
	 * @return
	 */
	int checkTmpGoodsNum(Map<String, Object> param);
	/**
	 * 查询自定义物料列表
	 * @param param
	 * @return
	 */
	List<ListGoods> getTmpGoodsList(Map<String, Object> param);
	/**
	 * 批量添加库存修正详单
	 * @param param
	 */
	void batchAddStockFixD(Map<String, Object> param);
	/**
	 * 添加自定义物料
	 * @param param
	 */
	void addExtraGoods(Map<String, Object> param);
	/**
	 * 更新库存修正详细
	 * @param param
	 */
	void updateStockFixD(Map<String, Object> param);
	/**
	 * 处理库存修正单
	 * @param param
	 */
	void dealStockFix(Map<String, Object> param);
	/**
	 * 查询库存修正单状态
	 * @param param
	 * @return
	 */
	Integer getStockFixHState(Map<String, Object> param);
	/**
	 * 查询库存物料
	 * @param param
	 * @return
	 */
	Stock getStock(Map<String, Object> param);
	/**
	 * 影响库存
	 * @param param
	 */
	void updateStock(Map<String, Object> param);
	/**
	 * 插入库存记录表
	 */
	void addStockRecord(Map<String, Object> param);
	/**
	 * 根据id查询单据号
	 * @param param
	 * @return
	 */
	String getBillCodeById(Map<String, Object> param);
}
