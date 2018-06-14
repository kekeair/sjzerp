package com.qxh.service;

import java.util.ArrayList;
import java.util.List;

import com.qxh.model.TagGoods;
import com.qxh.utils.Result;

public interface TagGoodsService {
	
	/**
	 * 餐饮中心物料总数据
	 * @param name
	 * @param leftKindId
	 * @param leftPage
	 * @param rightKindId
	 * @param rightPage
	 * @param tagId 
	 * @return  name,lkindCode,leftPage, rkindCode,rightPage,structId,tagId
	 */
	Result getTagGoodsData(String name, String lkindCode, String leftPage, 
			String rkindCode, String rightPage,int structId, String tagId,String goodsCode);

	/**
	 * @description : [向标签物料库中添加商品]
	 * @param rightKindId
	 * @param rightPage
	 * @param goodsId
	 * @param structId
	 * @return
	 * @时间:2016年11月18日下午9:06:00
	 */
	Result addTagGoods(String rightKindId, String rightPage, String goodsId, String tagId, String tagGoodsPrice);

	/**
	 * @description : [删除右侧的物品项]
	 * @param rightKindId
	 * @param rightPage
	 * @param centerGoodsId
	 * @param structId
	 * @param goodsId
	 * @return
	 * @时间:2016年11月19日上午9:00:00
	 */
	Result delTagGoods(String rightKindId, String rightPage, String atNo, String tagId, String goodsId);

	/**
	 * @description : [修改标签库中的价格]
	 * @param atNo
	 * @param priceVal
	 * @return
	 * @时间:2016年11月19日下午3:39:17
	 */
	public void editTagGoods(String atNo, String priceVal);

	/**
	 * @description : [商品种类查询]
	 * @param rightKindId
	 * @param rightPage
	 * @param tagId
	 * @return
	 * @时间:2016年11月19日下午5:15:19
	 */
	Result getTagGoodsList(String rkindCode, String rightPage, String tagId);

	/**
	 * @description : [左搜索]
	 * @param name
	 * @param leftKindId
	 * @param leftPage
	 * @param structId
	 * @return
	 * @时间:2016年11月20日下午9:41:14
	 */
	Result getLeftGoodsList(String name, String kindCode, String leftPage, int structId,String tagId,String goodsCode);

	/**
	 * @description : [批量添加]
	 * @param name
	 * @param lKindCode
	 * @param structId
	 * @param goodsCode
	 * @return
	 * @时间:2016年12月1日下午8:31:17
	 */
	Result addAllGoodsList(String name, String lKindCode, int structId, String goodsCode,String tagId);

	/**
	 * @description : [通过标签id查找标签价格集合]
	 * @param tagId
	 * @return
	 * @时间:2017年1月11日下午6:13:06
	 */
	List<TagGoods> getTagGoodsPriceListByTagId(String tagId);

	/**
	 * @description : [标签价格的导入功能]
	 * @param list
	 * @return
	 * @时间:2017年1月12日上午8:38:30
	 */
	Result editTagGoodsPriceByAtNO(ArrayList<TagGoods> list);
	

}
