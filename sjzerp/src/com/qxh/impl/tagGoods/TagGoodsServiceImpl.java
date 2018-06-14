package com.qxh.impl.tagGoods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.model.CenterGoods;
import com.qxh.model.TagGoods;
import com.qxh.service.TagGoodsService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.IPageConstants;
import com.qxh.utils.PageUtil;
import com.qxh.utils.Result;

public class TagGoodsServiceImpl implements TagGoodsService {

	Logger log = Logger.getLogger(this.getClass());
	private TagGoodsMapper tagGoodsMapper;

	public void setTagGoodsMapper(TagGoodsMapper tagGoodsMapper) {
		this.tagGoodsMapper = tagGoodsMapper;
	}

	/**
	 * 餐饮中心物料总数据
	 */
	@Override
	public Result getTagGoodsData(String name, String lkindCode, String leftPage, String rkindCode, String rightPage,
			int structId, String tagId,String goodsCode) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("name", name);
		// 左
		param.put("lkindCode", lkindCode);
		param.put("leftPage", (Integer.parseInt(leftPage) - 1) * IPageConstants.PageSize);
		// 右
		param.put("rkindCode", rkindCode);
		param.put("rightPage", (Integer.parseInt(rightPage) - 1) * IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("structId", structId);
		param.put("tagId", tagId);
		param.put("goodsCode", goodsCode);
		try {

			// 查询餐饮中心物料
			List<CenterGoods> leftGoodsList = tagGoodsMapper.getCenterGoodsList(param);
			int leftTotalCount = 0, leftTotalPage = 1;
			if (leftGoodsList != null && leftGoodsList.size() > 0) {
				leftTotalCount = tagGoodsMapper.getCenterGoodsCount(param);
				leftTotalPage = PageUtil.getTotalPage(leftTotalCount, IPageConstants.PageSize);
			}

			// 查询标签物料库
			List<TagGoods> rightGoodsList = tagGoodsMapper.getLeftGoodsList(param);
			int rightTotalCount = 0, rightTotalPage = 1;
			if (rightGoodsList != null && rightGoodsList.size() > 0) {
				rightTotalCount = tagGoodsMapper.getLeftGoodsCount(param);
				rightTotalPage = PageUtil.getTotalPage(rightTotalCount, IPageConstants.PageSize);
			}
			Map<String, Object> data = new HashMap<>();
			data.put("leftGoodsList", leftGoodsList);
			data.put("leftTotalPage", leftTotalPage);
			data.put("leftTotalCount", leftTotalCount);

			data.put("rightGoodsList", rightGoodsList);
			data.put("rightTotalPage", rightTotalPage);
			data.put("rightTotalCount", rightTotalCount);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * Todo : [向标签物料库中添加商品]
	 * 
	 * @时间:2016年11月18日下午9:06:19
	 */
	@Override
	public Result addTagGoods(String rightKindId, String rightPage, String goodsId, String tagId,
			String tagGoodsPrice) {

		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");

		Map<String, Object> param = new HashMap<>();
		param.put("rightKindId", rightKindId);
		param.put("rightPage", (Integer.parseInt(rightPage) - 1) * IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("tagId", tagId);
		param.put("goodsId", goodsId);
		param.put("tagGoodsPrice", tagGoodsPrice);
		try {
			tagGoodsMapper.addTagGoods(param);
			Map<String, Object> data = getTagGoods(param, rightPage);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 餐饮中心添加物料：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n"
					+ ",rightKindId:" + rightKindId + ",rightPage:" + rightPage + "\r\n\r\n");
			e.printStackTrace();
		}
		return result;
	}

	private Map<String, Object> getTagGoods(Map<String, Object> param, String rightPage) {
		// 查询标签物料库
		List<TagGoods> rightGoodsList = tagGoodsMapper.getLeftGoodsList(param);
		int rightTotalCount = 0, rightTotalPage = 1;
		if (rightGoodsList != null && rightGoodsList.size() > 0) {
			rightTotalCount = tagGoodsMapper.getLeftGoodsCount(param);
			rightTotalPage = PageUtil.getTotalPage(rightTotalCount, IPageConstants.PageSize);
		}

		Map<String, Object> data = new HashMap<>();
		data.put("rightGoodsList", rightGoodsList);
		data.put("rightTotalPage", rightTotalPage);
		data.put("rightTotalCount", rightTotalCount);
		return data;
	}

	/*
	 * Todo : [删除右侧的物品项]
	 * 
	 * @时间:2016年11月19日上午9:02:52
	 */
	@Override
	public Result delTagGoods(String rightKindId, String rightPage, String atNo, String tagId, String goodsId) {

		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("rightKindId", rightKindId);
		param.put("rightPage", (Integer.parseInt(rightPage) - 1) * IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("atNo", atNo);
		param.put("tagId", tagId);
		param.put("goodsId", goodsId);
		try {
			tagGoodsMapper.delTagGoods(param);
			Map<String, Object> data = getTagGoods(param, rightPage);
			result.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * Todo : [修改标签库中的价格]
	 * 
	 * @时间:2016年11月19日下午3:39:47
	 */
	@Override
	public void editTagGoods(String atNo, String priceVal) {
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		param.put("tagGoodsPrice", priceVal);
		try {
			tagGoodsMapper.editTagGoods(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Todo : [商品种类查询]
	 * 
	 * @时间:2016年11月19日下午5:15:40
	 */
	@Override
	public Result getTagGoodsList(String rkindCode, String rightPage, String tagId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("rkindCode", rkindCode);
		param.put("rightPage", (Integer.parseInt(rightPage) - 1) * IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("tagId", tagId);
		try {
			Map<String, Object> data = getTagGoods(param, rightPage);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询餐饮中心物料：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",kindCode:"
					+ rkindCode + ",rightPage:" + rightPage + "\r\n\r\n");
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * Todo : [左搜索]
	 * 
	 * @时间:2016年11月20日下午9:42:59
	 */
	@Override
	public Result getLeftGoodsList(String name, String lkindCode, String leftPage, int structId, String tagId,String goodsCode) {

		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");

		// ======去掉name中是否"(")"=======================
		StringBuilder builder = new StringBuilder();
		StringBuilder builderLast = new StringBuilder();
		if (StringUtils.isNotEmpty(name)) {
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
		// ======去掉name中是否"(")"=======================
		Map<String, Object> param = new HashMap<>();
		param.put("name", name);
		param.put("lkindCode", lkindCode);
		param.put("leftPage", (Integer.parseInt(leftPage) - 1) * IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("structId", structId);
		param.put("tagId", tagId);
		param.put("goodsCode", goodsCode);
		try {
			// 查询物料库
			List<CenterGoods> leftGoodsList = tagGoodsMapper.getCenterGoodsList(param);
			int leftTotalCount = 0, leftTotalPage = 1;
			if (leftGoodsList != null && leftGoodsList.size() > 0) {
				leftTotalCount = tagGoodsMapper.getCenterGoodsCount(param);
				leftTotalPage = PageUtil.getTotalPage(leftTotalCount, IPageConstants.PageSize);
			}

			Map<String, Object> data = new HashMap<>();
			data.put("leftGoodsList", leftGoodsList);
			data.put("leftTotalPage", leftTotalPage);
			data.put("leftTotalCount", leftTotalCount);

			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}

	/* 
	 * Todo : [批量添加]
	 * @时间:2016年12月1日下午8:31:28
	 */
	@Override
	public Result addAllGoodsList(String name, String lkindCode, int structId, String goodsCode,String tagId) {
		
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
		param.put("lkindCode", lkindCode);
		param.put("structId", structId);
		param.put("goodsCode", goodsCode);
		param.put("tagId", tagId);
		try {
			ArrayList<TagGoods> tagGoodsList = new ArrayList<>();
			//查询物料库
			List<TagGoods> leftGoodsList=tagGoodsMapper.addAllGoodsList(param);
			if(leftGoodsList.size()==0 || leftGoodsList == null){
				result.setMsg("没有要添加的物料!");
				return result;
			}
			for (TagGoods listGoods : leftGoodsList) {
				tagGoodsList.add(listGoods);
			}
			param.put("tagGoodsList", tagGoodsList);
			//批量添加
			tagGoodsMapper.addAllTagGoods(param);
			result.setMsg("批量添加成功!");
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}

	/* 
	 * Todo : [通过标签id查找标签价格集合]
	 * @时间:2017年1月11日下午6:13:51
	 */
	@Override
	public List<TagGoods> getTagGoodsPriceListByTagId(String tagId) {
		Map<String, Object> param = new HashMap<>();
		param.put("tagId", tagId);
		List<TagGoods>  list=null;
		try {
			  list = tagGoodsMapper.getTagGoodsPriceListByTagId(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* 
	 * Todo : [标签价格的导入功能]
	 * @时间:2017年1月12日上午8:38:49
	 */
	@Override
	public Result editTagGoodsPriceByAtNO(ArrayList<TagGoods> list) {
		
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		try {
			//拼接插入的條件
			StringBuilder idStr = new StringBuilder();
			for (int i = 0; i < list.size(); i++) {
				int atNo = list.get(i).getAtNo();
				if(i<list.size()-1){
					idStr.append(atNo).append(",");
				}else{
					idStr.append(atNo);
				}
			}
				param.put("idStr", idStr);
				param.put("priceList", list);
				
				tagGoodsMapper.updateTagGoodsPriceAtNo(param);

		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("导入的格式不正确,请检查你导入的格式!!!");
			e.printStackTrace();
			
			System.err.println(e.getMessage());
			log.error("\r\n 修改物料价格：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + "priceJson:"
					+ list + "\r\n\r\n");
			throw new RuntimeException();
		}
		return result;
	}

}
