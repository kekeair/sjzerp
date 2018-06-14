package com.qxh.impl.supplierGoods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.AvailableGoods;
import com.qxh.model.SupplierGoods;
import com.qxh.service.SupplierGoodsService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.IPageConstants;
import com.qxh.utils.PageUtil;
import com.qxh.utils.Result;

public class SupplierGoodsServiceImpl implements SupplierGoodsService {

	Logger log = Logger.getLogger(this.getClass());
	private SupplierGoodsMapper supplierGoodsMapper;

	public void setSupplierGoodsMapper(SupplierGoodsMapper supplierGoodsMapper) {
		this.supplierGoodsMapper = supplierGoodsMapper;
	}

	/**
	 * 查询供应商物料画面总数据
	 */
	@Override
	public Result getSupplierGoodsData(String name, String kindCode, String leftPage, String supplierId,
			String rightPage, int structId,String goodsCode) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("name", name);
		param.put("kindCode", kindCode);
		param.put("leftPage", (Integer.parseInt(leftPage) - 1) * IPageConstants.PageSize);
		param.put("supplierId", supplierId);
		param.put("rightPage", (Integer.parseInt(rightPage) - 1) * IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("structId", structId);
		param.put("goodsCode", goodsCode);
		try {
			Map<String, Object> data = new HashMap<>();
			// 查询可用物料
			setLeftData(data, supplierId, leftPage, param);
			// 查询供应商物料
			setRightData(data, rightPage, param);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 餐饮中心物料总数据：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + "name:"
					+ name + ",kindCode:" + kindCode + ",leftPage:" + leftPage + ",supplierId:" + supplierId
					+ ",rightPage:" + rightPage + ",structId:" + structId + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 查询餐饮中心可用物料
	 */
	@Override
	public Result getCenterAvaiGoods(String name, String kindCode, String leftPage, int structId, String supplierId,String goodsCode) {
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
		param.put("kindCode", kindCode);
		param.put("leftPage", (Integer.parseInt(leftPage) - 1) * IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("structId", structId);
		param.put("supplierId", supplierId);
		param.put("goodsCode", goodsCode);
		
		try {
			Map<String, Object> data = new HashMap<>();
			setLeftData(data, supplierId, leftPage, param);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询餐饮中心可用物料：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + "name:"
					+ name + ",kindCode:" + kindCode + ",leftPage:" + leftPage + ",structId:" + structId + "\r\n\r\n");
		}
		return result;
	}

	// 设置左列表数据
	private void setLeftData(Map<String, Object> data, String supplierId, String leftPage, Map<String, Object> param) {
		// 查询可用物料
		List<AvailableGoods> leftGoodsList = supplierGoodsMapper.getCenterAvaiGoods(param);
		int leftTotalCount = 0, leftTotalPage = 1;
		if (leftGoodsList != null && leftGoodsList.size() > 0) {
			int l = leftGoodsList.size();
			// int
			// orderIndex=(Integer.parseInt(leftPage)-1)*IPageConstants.PageSize+1;
			for (int i = 0; i < l; i++) {
				AvailableGoods g = leftGoodsList.get(i);
				String s = g.getSupplierId();
				if (!StringUtils.isEmpty(s)) {
					s = s + ",";
					if (s.contains(supplierId + ","))
						g.setState(1);
				}
				// g.setOrderIndex(orderIndex);
				// orderIndex++;
			}
			leftTotalCount = supplierGoodsMapper.getCenterAvaiGoodsNum(param);
			leftTotalPage = PageUtil.getTotalPage(leftTotalCount, IPageConstants.PageSize);
		}

		data.put("leftGoodsList", leftGoodsList);
		data.put("leftTotalPage", leftTotalPage);
		data.put("leftTotalCount", leftTotalCount);
	}

	// 设置右列表数据
	private void setRightData(Map<String, Object> data, String rightPage, Map<String, Object> param) {
		List<SupplierGoods> rightGoodsList = supplierGoodsMapper.getSupplierGoods(param);
		int rightTotalCount = 0, rightTotalPage = 1;
		if (rightGoodsList != null && rightGoodsList.size() > 0) {
			// int l=rightGoodsList.size();
			// int
			// orderIndex=(Integer.parseInt(rightPage)-1)*IPageConstants.PageSize+1;
			// for (int i = 0; i < l; i++) {
			// rightGoodsList.get(i).setOrderIndex(orderIndex);
			// orderIndex++;
			// }
			rightTotalCount = supplierGoodsMapper.getSupplierGoodsNum(param);
			rightTotalPage = PageUtil.getTotalPage(rightTotalCount, IPageConstants.PageSize);
		}
		data.put("rightGoodsList", rightGoodsList);
		data.put("rightTotalPage", rightTotalPage);
		data.put("rightTotalCount", rightTotalCount);
	}

	/**
	 * 供应商提供物料列表
	 */
	@Override
	public Result getSupplierGoods(String supplierId, String rightPage, int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("supplierId", supplierId);
		param.put("rightPage", (Integer.parseInt(rightPage) - 1) * IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("structId", structId);
		try {
			Map<String, Object> data = new HashMap<>();
			// 查询供应商物料
			setRightData(data, rightPage, param);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 供应商提供物料列表：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n"
					+ ",supplierId:" + supplierId + ",rightPage:" + rightPage + ",structId:" + structId + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 添加供应商提供的物料
	 */
	@Override
	public Result addSupplierGoods(String supplierId, String rightPage, String goodsId, int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("supplierId", supplierId);
		param.put("rightPage", (Integer.parseInt(rightPage) - 1) * IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("structId", structId);
		param.put("goodsId", goodsId);
		try {
			supplierGoodsMapper.addSupplierGoods(param);
			Map<String, Object> data = new HashMap<>();
			setRightData(data, rightPage, param);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 餐饮中心添加物料：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",goodsId:"
					+ goodsId + ",rightPage:" + rightPage + ",rightPage:" + rightPage + ",structId:" + structId
					+ "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 删除供应商提供的物料
	 */
	@Override
	public Result delSupplierGoods(String supplierId, String rightPage, String supplierGoodsId, int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("supplierId", supplierId);
		param.put("rightPage", (Integer.parseInt(rightPage) - 1) * IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("supplierGoodsId", supplierGoodsId);
		param.put("structId", structId);
		try {
			Map<String, Object> data = new HashMap<>();
			supplierGoodsMapper.delSupplierGoods(param);
			setRightData(data, rightPage, param);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 删除供应商提供的物料：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n"
					+ ",supplierId:" + supplierId + ",structId:" + structId + ",rightPage:" + rightPage
					+ ",supplierGoodsId:" + supplierGoodsId + "\r\n\r\n");
		}
		return result;
	}

	/* 
	 * Todo : [批量添加]
	 * @时间:2016年12月1日下午5:18:48
	 */
	@Override
	public Result addAllGoodsList(String name, String kindCode, int structId, String goodsCode,String supplierId) {
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
		param.put("kindCode", kindCode);
		param.put("structId", structId);
		param.put("goodsCode", goodsCode);
		param.put("supplierId", supplierId);
		try {
			ArrayList<Integer> ids = new ArrayList<>();
			//查询物料库
			List<AvailableGoods> leftGoodsList=supplierGoodsMapper.addAllGoodsList(param);
			if(leftGoodsList.size()==0 || leftGoodsList == null){
				result.setMsg("没有要添加的物料!");
				return result;
			}
			for (AvailableGoods listGoods : leftGoodsList) {
				ids.add(listGoods.getGoodsId());
			}
			param.put("ids", ids);
			//批量添加
			supplierGoodsMapper.addAllCenterGoods(param);
			result.setMsg("批量添加成功!");
			return result;
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}
}
