package com.qxh.impl.demandSumm;

import java.util.List;
import java.util.Map;

import com.qxh.exmodel.DemandGoods;
import com.qxh.exmodel.DemandSummaryD;
import com.qxh.model.GoodsKind;

public interface DemandSummMapper {

	/**
	 * 查询销售汇总
	 * @param param
	 * @return
	 */
	List<DemandGoods> getDemandSummary(Map<String, Object> param);
	/**
	 * 根据物料查询销售汇总
	 * @param param
	 * @return
	 */
	List<DemandSummaryD> getDemandSummByGoods(Map<String, Object> param);
	/**
	 * 根据需求详情单获取物料名称
	 * @param param
	 * @return
	 */
	DemandSummaryD getGoodsNmByDemandListDId(Map<String, Object> param);

	/**
	 * 为毛利汇总根据物料查询销售汇总
	 * @param param
	 * @return
	 */
	List<DemandGoods> getDemandSummByGoodsForProfit(Map<String, Object> param);
	
	/**
	 * 为毛利汇总查询销售汇总
	 * @param param
	 * @return
	 */
	List<DemandGoods> getDemandSummaryForProfit(Map<String, Object> param);
	
	/**
	 * 为汇总图表获取数量
	 * @param param
	 * @return
	 */
	List<DemandGoods> getDemandNumInDay(Map<String, Object> param);
	/**
	 * 为汇总图表获取当年每月单物料数量
	 * @param param
	 * @return
	 */
	List<DemandGoods> getDemandNumInMonth(Map<String, Object> param);
	/**
	 * 为分类汇总图表   成本占比
	 * @param param
	 * @return
	 */
	List<DemandGoods> getBigKindForIncome(Map<String, Object> param);
	/**
	 * 为分类汇总图表   成本占比
	 * @param param
	 * @return
	 */
	GoodsKind getKindNmByCode(Map<String, Object> param);
	/**
	 * 为汇总图表按天获取单个客户的数量 
	 * @param param
	 * @return
	 */
	List<DemandGoods> getOrderNumBycustomerInDay(Map<String, Object> param);
	/**
	 * 为汇总图表按月获取单个客户的数量 
	 * @param param
	 * @return
	 */
	List<DemandGoods> getOrderNumBycustomerInMonth(Map<String, Object> param);
	/**
	 * 为汇总图表按日期获取多个客户的数量 
	 * @param param
	 * @return
	 */
	List<DemandGoods> getOrderNumBycustomersInDay(Map<String, Object> param);
}
