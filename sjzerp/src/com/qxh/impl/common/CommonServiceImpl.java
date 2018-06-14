package com.qxh.impl.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.AppyDemandGoods;
import com.qxh.exmodel.BaseGoods;
import com.qxh.exmodel.CommonModel;
import com.qxh.model.Area;
import com.qxh.model.GoodsKind;
import com.qxh.model.ReviewProcess;
import com.qxh.model.UnitSet;
import com.qxh.service.CommonService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.Result;

public class CommonServiceImpl implements CommonService {
	
	Logger log = Logger.getLogger(this.getClass());
	
	private CommonMapper commonMapper;

	public void setCommonMapper(CommonMapper commonMapper) {
		this.commonMapper = commonMapper;
	}

	/**
	 * 城市列表
	 */
	@Override
	public Result getCityList(String provinceId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("provinceId", provinceId);
		try {
			List<Area> cityList=commonMapper.getCityList(param);
			Map<String, Object> data = new HashMap<>();
			data.put("cityList", cityList);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询城市列表：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" +",provinceId:"+provinceId + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 获取县列表
	 */
	@Override
	public Result getCountyList(String cityId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("cityId", cityId);
		try {
			List<Area> countyList=commonMapper.getCountyList(param);
			Map<String, Object> data = new HashMap<>();
			data.put("countyList", countyList);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询县列表：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" +",cityId:"+cityId + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 省列表
	 */
	@Override
	public Result getProvinceList() {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		try {
			List<Area> provinceList=commonMapper.getProvinceList();
			Map<String, Object> data = new HashMap<>();
			data.put("provinceList", provinceList);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询省列表：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" );
		}
		return result;
	}

	/**
	 * 查询组织架构和角色
	 */
	@Override
	public Result getStructAndRole() {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		try {
			List<CommonModel> structList=commonMapper.getStructList();
			List<CommonModel> roleList=commonMapper.getRoleList();
			if(structList==null)
				structList=new ArrayList<CommonModel>();
			if(roleList==null)
				roleList=new ArrayList<CommonModel>();
			Map<String, Object> data = new HashMap<>();
			data.put("structList", structList);
			data.put("roleList", roleList);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询组织架构和角色：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" );
		}
		return result;
	}

	/**
	 * 查询市和县
	 */
	@Override
	public Result getCityAndCounty(String provinceId, String cityId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("provinceId", provinceId);
		param.put("cityId", cityId);
		try {
			List<Area> cityList=commonMapper.getCityList(param);
			List<Area> countyList=commonMapper.getCountyList(param);
			Map<String, Object> data = new HashMap<>();
			data.put("cityList", cityList);
			data.put("countyList", countyList);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询市和县：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "privinceId:" + provinceId
			+",cityId:"+cityId + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 查询物料分类
	 */
	@Override
	public Result getGoodsKind() {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		try {
			List<GoodsKind> kindList=commonMapper.getGoodsKind();
			if(kindList==null)
				kindList=new ArrayList<>();
			result.setData(kindList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询物料分类：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" );
		}
		return result;
	}

	/**
	 * 查询单位
	 */
	@Override
	public Result getGoodsUnit() {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		try {
			List<UnitSet> unitList=commonMapper.getUnitList();
			result.setData(unitList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询物料单位：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" );
		}
		return result;
	}

	/**
	 * 供应商列表
	 */
	@Override
	public Result getSupplier(int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("structId", structId);
		try {
			List<CommonModel> supplierList=commonMapper.getSupplier(param);
			result.setData(supplierList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 供应商列表：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "structId:" + structId + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 查询审批流程
	 */
	@Override
	public Result getReviewProcess(String billId, String billType) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		param.put("billType", billType);
		try {
			List<ReviewProcess> pList=commonMapper.getReviewProcessList(param);
			if(pList!=null&&pList.size()>0){
				int l=pList.size();
				for (int i = 0; i < l; i++) {
					pList.get(i).setOrderIndex(i+1);
				}
			}
			result.setData(pList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询审批流程：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "billId:" + billId
			+ "billType:" + billType+ "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 查询客户列表
	 */
	@Override
	public Result getCustomerList(int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("structId", structId);
		try {
			List<CommonModel> list=commonMapper.getCustomerList(param);
			result.setData(list);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询客户列表：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "structId:" + structId+ "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 退库退货审批流程
	 */
	@Override
	public Result getReturnProcess(String billId, String billType) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		param.put("billType", billType);
		try {
			List<ReviewProcess> pList=commonMapper.getReturnProcessList(param);
			if(pList!=null&&pList.size()>0){
				int l=pList.size();
				for (int i = 0; i < l; i++) {
					pList.get(i).setOrderIndex(i+1);
				}
			}
			result.setData(pList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 退库退货审批流程：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "billId:" + billId
			+ "billType:" + billType+ "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 查询所有配送中心物料
	 */
	@Override
	public Result getAllCenterGoods(int structId,String billId,String billType,
			String customerId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("structId", structId);
		param.put("billId", billId);
		try {
			if(billType.equals("demand")){
				if(StringUtils.isEmpty(customerId)){
					Integer customerId1=commonMapper.getCustomerIdByBillId(param);
					param.put("customerId", customerId1);
				}else{
					param.put("customerId", customerId);
				}
				Integer tagId=commonMapper.getCustomerTagId(param);
				param.put("tagId", tagId);
				List<BaseGoods> list=commonMapper.getCenterGoods_demand(param);
				result.setData(list);
			}else if(billType.equals("purchase")){
				List<BaseGoods> list=commonMapper.getCenterGoods_purchase(param);
				result.setData(list);
			}
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询所有配送中心物料：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "structId:" + structId+ "\r\n\r\n");
		}
		return result;
	}

	/* 
	 * Todo : [获取物料列表]
	 * @时间:2016年12月26日下午2:38:31
	 */
	@Override
	public Result getCenterGoodsList() {
		Result result = new Result();
		try {
		List<AppyDemandGoods> goodsNameList =	commonMapper.getCenterGoodsList();
		result.setData(goodsNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
