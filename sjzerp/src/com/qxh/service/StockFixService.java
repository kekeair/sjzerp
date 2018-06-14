package com.qxh.service;

import com.qxh.utils.Result;

public interface StockFixService {

	/**
	 * 获取餐饮中心物料
	 * @param name
	 * @param kindId
	 * @param page
	 * @return
	 */
	Result getCenterGoods(String name, String kindCode, String page,int structId,
			String billId);
	/**
	 * 查询库存修正单详细
	 * @param name
	 * @param kindId
	 * @param page
	 * @param structId
	 * @return
	 */
	Result getStockFixD(String name, String kindCode, String page, String billId);
	/**
	 * 查询库存修正单列表
	 * @param state
	 * @param stime
	 * @param etime
	 * @param page
	 * @param structId
	 * @return
	 */
	Result getStockFixH(String state, String stime, String etime, String page, 
			int structId,int roleId);
	/**
	 * 删除库存修正单
	 * @param billId
	 * @return
	 */
	Result delStockFix(String billId);
	/**
	 * 添加库存修正详细
	 * @param name
	 * @param kindId
	 * @param page
	 * @param goodsId
	 * @param structId
	 * @param billId
	 * @return
	 */
	Result addStockFixD(String name, String kindCode, String page, String goodsId, 
			int structId, String billId,int userId,String userNm);
	/**
	 * 删除库存修正详细
	 * @param name
	 * @param kindId
	 * @param page
	 * @param goodsId
	 * @param stockFixDId
	 * @return
	 */
	Result delStockFixD(String name, String kindCode, String page, String stockFixDId,
			String billId);
	/**
	 * 添加自定义物料
	 * @param name
	 * @param kindId
	 * @param page
	 * @param goodsNm
	 * @param goodsKind
	 * @param goodsUnit
	 * @param goodsPrice
	 * @param goodsNum
	 * @param structId
	 * @param billId
	 * @param atNo
	 * @param userNm
	 * @return
	 */
	Result addExtraGoods(String goodsNm, String goodsCode, String goodsUnit,
			int structId);
	/**
	 * 查询自定义物料列表
	 * @param structId
	 * @return
	 */
	Result getTmpGoodsList(int structId);
	/**
	 * 选择自定义物料
	 * @param name
	 * @param kindId
	 * @param page
	 * @param goodsIdStr
	 * @param structId
	 * @param billId
	 * @param atNo
	 * @param userNm
	 * @return
	 */
	Result selTmpGoods(String name, String kindCode, String page, String goodsIdStr, int structId, String billId,
			int atNo, String userNm);
	/**
	 * 更新库存修正详细
	 * @param goodsNum
	 * @param price
	 * @param stockFixDId
	 * @return
	 */
	Result updateStockFixD(String goodsNum, String price, String stockFixDId);
	/**
	 * 处理库存修正单
	 * @param billId
	 * @param roleId
	 * @param reviewState
	 * @return
	 */
	Result dealStockFix(String billId, int roleId, String reviewState,int structId);
}
