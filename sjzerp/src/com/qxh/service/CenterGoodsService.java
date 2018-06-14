package com.qxh.service;

import com.qxh.utils.Result;

public interface CenterGoodsService {
	
	/**
	 * 餐饮中心物料总数据
	 * @param name
	 * @param leftKindId
	 * @param leftPage
	 * @param rightKindId
	 * @param rightPage
	 * @return
	 */
	Result getCenterGoodsData(String name, String lKindCode, String leftPage, 
			String rKindCode, String rightPage,int structId, String goodsCode);
	
	/**
	 * 查询物料库
	 * @param name
	 * @param leftKindId
	 * @param leftPage
	 * @param structId
	 * @return
	 */
	Result getLeftGoodsList(String name, String lKindCode, String leftPage, int structId,String goodsCode);
	
	/**
	 * 查询餐饮中心物料
	 * @param rightKindId
	 * @param rightPage
	 * @param structId
	 * @return
	 */
	Result getCenterGoodsList(String rKindCode, String rightPage, int structId);
	/**
	 * 餐饮中心添加物料
	 * @param rightKindId
	 * @param rightPage
	 * @param goodsId
	 * @param structId
	 * @return
	 */
	Result addCenterGoods(String rightKindId, String rightPage, String goodsId, int structId);
	/**
	 * 餐饮中心删除物料
	 * @param rightKindId
	 * @param rightPage
	 * @param goodsId
	 * @param structId
	 * @return
	 */
	Result delCenterGoods(String rightKindId, String rightPage, 
			String centerGoodsId, int structId,String goodsId);

	/**
	 * @description : [批量添加]
	 * @param name
	 * @param lKindCode
	 * @param structId
	 * @param goodsCode
	 * @return
	 * @时间:2016年12月1日下午3:28:58
	 */
	Result addAllGoodsList(String name, String lKindCode, int structId, String goodsCode);


}
