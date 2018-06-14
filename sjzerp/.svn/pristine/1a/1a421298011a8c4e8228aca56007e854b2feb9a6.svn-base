package com.qxh.impl.centerGoods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.ListGoods;
import com.qxh.model.CenterGoods;
import com.qxh.service.CenterGoodsService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.IPageConstants;
import com.qxh.utils.PageUtil;
import com.qxh.utils.Result;

public class CenterGoodsServiceImpl implements CenterGoodsService {
	
	Logger log = Logger.getLogger(this.getClass());
	private CenterGoodsMapper centerGoodsMapper;
	
	public void setCenterGoodsMapper(CenterGoodsMapper centerGoodsMapper) {
		this.centerGoodsMapper = centerGoodsMapper;
	}
	
	/**
	 * 餐饮中心物料总数据
	 */
	@Override
	public Result getCenterGoodsData(String name, String lKindCode, String leftPage, String rKindCode,
			String rightPage,int structId,String goodsCode) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("name", name);
		param.put("lKindCode", lKindCode);
		param.put("leftPage", (Integer.parseInt(leftPage)-1)*IPageConstants.PageSize);
		param.put("rKindCode", rKindCode);
		param.put("rightPage", (Integer.parseInt(rightPage)-1)*IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("structId", structId);
		param.put("goodsCode", goodsCode);
		try {
			
			//查询物料库
			List<ListGoods> leftGoodsList=centerGoodsMapper.getLeftGoodsList(param);
			/*ArrayList<Integer> ids = new ArrayList<>();
			for (ListGoods listGoods : leftGoodsList) {
				ids.add(listGoods.getAtNo());
			}*/
			int leftTotalCount=0,leftTotalPage=1;
			if(leftGoodsList!=null&&leftGoodsList.size()>0){

				leftTotalCount=centerGoodsMapper.getLeftGoodsCount(param);
				leftTotalPage=PageUtil.getTotalPage(leftTotalCount, IPageConstants.PageSize);
			}
			//查询餐饮中心物料
			List<CenterGoods> rightGoodsList=centerGoodsMapper.getCenterGoodsList(param);
			int rightTotalCount=0,rightTotalPage=1;
			if(rightGoodsList!=null&&rightGoodsList.size()>0){

				rightTotalCount=centerGoodsMapper.getCenterGoodsCount(param);
				rightTotalPage=PageUtil.getTotalPage(rightTotalCount, IPageConstants.PageSize);
			}
			Map<String, Object> data = new HashMap<>();
			data.put("leftGoodsList", leftGoodsList);
			data.put("leftTotalPage", leftTotalPage);
			data.put("leftTotalCount", leftTotalCount);
			data.put("rightGoodsList", rightGoodsList);
			data.put("rightTotalPage", rightTotalPage);
			data.put("rightTotalCount", rightTotalCount);
			//data.put("ids", ids);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/**
	 * 查询物料库
	 */
	@Override
	public Result getLeftGoodsList(String name, String lKindCode, String leftPage, int structId,String goodsCode) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		//======去掉name中是否"(")"=======================
		StringBuilder builder = new StringBuilder();
		StringBuilder builderLast = new StringBuilder();
		if(StringUtils.isNotEmpty(name)){
			String[] split = name.split("[(]");
			for (String string : split) {
				builder.append(string);
			}
			
			String[] lastArray = builder.toString().split("[)]");
			for (String string : lastArray) {
				builderLast.append(string);
			}
			 name = builderLast.toString();
		}
		//======去掉name中是否"(")"=======================
		param.put("name", name);
		param.put("lKindCode", lKindCode);
		param.put("leftPage", (Integer.parseInt(leftPage)-1)*IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("structId", structId);
		param.put("goodsCode", goodsCode);
		try {
			//查询物料库
			List<ListGoods> leftGoodsList=centerGoodsMapper.getLeftGoodsList(param);
			/*ArrayList<Integer> ids = new ArrayList<>();
			for (ListGoods listGoods : leftGoodsList) {
				ids.add(listGoods.getAtNo());
			}*/
			int leftTotalCount=0,leftTotalPage=1;
			if(leftGoodsList!=null&&leftGoodsList.size()>0){
				leftTotalCount=centerGoodsMapper.getLeftGoodsCount(param);
				leftTotalPage=PageUtil.getTotalPage(leftTotalCount, IPageConstants.PageSize);
			}
			Map<String, Object> data = new HashMap<>();
			data.put("leftGoodsList", leftGoodsList);
			data.put("leftTotalPage", leftTotalPage);
			data.put("leftTotalCount", leftTotalCount);
			//data.put("ids", ids);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询物料库：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "name:" + name +",lKindCode:"+lKindCode
			+",leftPage:"+leftPage + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 查询餐饮中心物料
	 */
	@Override
	public Result getCenterGoodsList(String rKindCode, String rightPage, int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("rKindCode", rKindCode);
		param.put("rightPage", (Integer.parseInt(rightPage)-1)*IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("structId", structId);
		try {
			Map<String, Object> data = getCenterGoods(param, rightPage);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询餐饮中心物料：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" +",rKindCode:"+rKindCode
			+",rightPage:"+rightPage + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 餐饮中心添加物料
	 */
	@Override
	public Result addCenterGoods(String rightKindId, String rightPage, 
			String goodsId, int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("rightKindId", rightKindId);
		param.put("rightPage", (Integer.parseInt(rightPage)-1)*IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("structId", structId);
		param.put("goodsId", goodsId);
		try {
			centerGoodsMapper.addCenterGoods(param);
			Map<String, Object> data = getCenterGoods(param, rightPage);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 餐饮中心添加物料：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" +",rightKindId:"+rightKindId
			+",rightPage:"+rightPage + "\r\n\r\n");
		}
		return result;
	}
	
	private Map<String, Object> getCenterGoods(Map<String, Object> param,
			String rightPage){
		List<CenterGoods> rightGoodsList=centerGoodsMapper.getCenterGoodsList(param);
		int rightTotalCount=0,rightTotalPage=1;
		if(rightGoodsList!=null&&rightGoodsList.size()>0){
//			int l=rightGoodsList.size();
//			int orderIndex=(Integer.parseInt(rightPage)-1)*IPageConstants.PageSize+1;
//			for (int i = 0; i < l; i++) {
//				rightGoodsList.get(i).setOrderIndex(orderIndex);
//				orderIndex++;
//			}
			rightTotalCount=centerGoodsMapper.getCenterGoodsCount(param);
			rightTotalPage=PageUtil.getTotalPage(rightTotalCount, IPageConstants.PageSize);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("rightGoodsList", rightGoodsList);
		data.put("rightTotalPage", rightTotalPage);
		data.put("rightTotalCount", rightTotalCount);
		return data;
	}

	/**
	 * 餐饮中心删除物料
	 */
	@Override
	public Result delCenterGoods(String rightKindId, String rightPage, 
			String centerGoodsId, int structId,String goodsId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("rightKindId", rightKindId);
		param.put("rightPage", (Integer.parseInt(rightPage)-1)*IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("centerGoodsId", centerGoodsId);
		param.put("structId", structId);
		param.put("goodsId", goodsId);
		try {
			Double stockNum=centerGoodsMapper.getGoodsStock(param);
			if(stockNum!=null&&stockNum>0){
				result.setCode(CodeConstant.CODE200);
				result.setMsg("该商品库存不为0，无法删除");
				return result;
			}
			centerGoodsMapper.delCenterGoods(param);
			centerGoodsMapper.delSuppGoodsFromCenterGoods(param);
			Map<String, Object> data = getCenterGoods(param, rightPage);
			result.setData(data);
		} catch (Exception e) {
			log.error("\r\n 餐饮中心删除物料：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" +",rightKindId:"+rightKindId
			+",rightPage:"+rightPage + "\r\n\r\n");
			throw new RuntimeException("库存不足");
		}
		return result;
	}

	/* 
	 * Todo : [批量添加]
	 * @时间:2016年12月1日下午3:29:38
	 */
	@Override
	public Result addAllGoodsList(String name, String lKindCode, int structId, String goodsCode) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		//======去掉name中是否"(")"=======================
		StringBuilder builder = new StringBuilder();
		StringBuilder builderLast = new StringBuilder();
		if(StringUtils.isNotEmpty(name)){
			String[] split = name.split("[(]");
			for (String string : split) {
				builder.append(string);
			}
			
			String[] lastArray = builder.toString().split("[)]");
			for (String string : lastArray) {
				builderLast.append(string);
			}
			 name = builderLast.toString();
		}
		//======去掉name中是否"(")"=======================
		param.put("name", name);
		param.put("lKindCode", lKindCode);
		param.put("structId", structId);
		param.put("goodsCode", goodsCode);
		try {
			ArrayList<Integer> ids = new ArrayList<>();
			//查询物料库
			List<ListGoods> leftGoodsList=centerGoodsMapper.addAllGoodsList(param);
			if(leftGoodsList.size()==0 || leftGoodsList == null){
				result.setMsg("没有要添加的物料!");
				return result;
			}
			for (ListGoods listGoods : leftGoodsList) {
				ids.add(listGoods.getAtNo());
			}
			param.put("ids", ids);
			//批量添加
			centerGoodsMapper.addAllCenterGoods(param);
			result.setMsg("批量添加成功!");
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}
	
}
