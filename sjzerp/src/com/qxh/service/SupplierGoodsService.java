package com.qxh.service;

import com.qxh.utils.Result;

public interface SupplierGoodsService {
	
	/**
	 * 查询供应商物料画面总数据
	 * @param name
	 * @param leftKindId
	 * @param leftPage
	 * @param supplierId
	 * @param rightPage
	 * @param structId
	 * @return
	 */
	Result getSupplierGoodsData(String name, String kindCode, String leftPage, String supplierId, String rightPage,
			int structId,String goodsCode);
	
	/**
	 * 查询餐饮中心可用物料
	 * @param name
	 * @param leftKindId
	 * @param leftPage
	 * @param structId
	 * @return
	 */
	Result getCenterAvaiGoods(String name, String kindCode, 
			String leftPage, int structId,String supplierId,String goodsCode);
	
	/**
	 * 查询供应商提供物料列表
	 * @param supplierId
	 * @param rightPage
	 * @param structId
	 * @return
	 */
	Result getSupplierGoods(String supplierId, String rightPage, int structId);
	/**
	 * 添加供应商提供的物料
	 * @param supplierId
	 * @param rightPage
	 * @param goodsId
	 * @param structId
	 * @return
	 */
	Result addSupplierGoods(String supplierId, String rightPage, String goodsId, int structId);
	/**
	 * 删除供应商提供的物料
	 * @param supplierId
	 * @param rightPage
	 * @param supplierGoodsId
	 * @param structId
	 * @return
	 */
	Result delSupplierGoods(String supplierId, String rightPage, String supplierGoodsId, int structId);

	/**
	 * @description : [批量添加]
	 * @param name
	 * @param kindCode
	 * @param structId
	 * @param goodsCode
	 * @return
	 * @时间:2016年12月1日下午5:18:29
	 */
	Result addAllGoodsList(String name, String kindCode, int structId, String goodsCode,String supplierId);
	
	
}
