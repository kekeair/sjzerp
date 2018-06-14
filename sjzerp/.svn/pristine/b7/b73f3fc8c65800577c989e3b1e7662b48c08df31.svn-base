package com.qxh.impl.tuiku;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.BaseGoods;
import com.qxh.exmodel.BaseStockGoods;
import com.qxh.exmodel.EditInOutD;
import com.qxh.exmodel.ExportTuikuModel;
import com.qxh.impl.common.CommonMapper;
import com.qxh.model.TuikuH;
import com.qxh.service.TuikuService;
import com.qxh.utils.DateUtil;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.IPageConstants;
import com.qxh.utils.PageUtil;
import com.qxh.utils.Result;

public class TuikuServiceImpl implements TuikuService {

	Logger log = Logger.getLogger(this.getClass());
	private TuikuMapper tuikuMapper;
	private CommonMapper commonMapper;
	
	public void setTuikuMapper(TuikuMapper tuikuMapper) {
		this.tuikuMapper = tuikuMapper;
	}
	
	public void setCommonMapper(CommonMapper commonMapper) {
		this.commonMapper = commonMapper;
	}

	/**
	 * 退库单列表
	 */
	@Override
	public Result getTuikuListH(String state, String stime, String etime, 
			String page, int structId, int roleId,String customerId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("state", state);
		param.put("page", (Integer.parseInt(page) - 1) * IPageConstants.PageSize);
		param.put("structId", structId);
		param.put("customerId", customerId);
		param.put("pageSize", IPageConstants.PageSize);
		try {
			if (!StringUtils.isEmpty(stime)) {
				stime = stime + " 00:00:00";
			} else {
				stime = "";
			}
			if (!StringUtils.isEmpty(etime)) {
				etime = etime + " 23:59:59";
			} else {
				etime = "";
			}
			param.put("stime", stime);
			param.put("etime", etime);
			switch (roleId) {
			case 5:// 业务员
				param.put("viewPower", 0);
				break;
			case 4:// 经理
				param.put("viewPower", 1);
				break;
			}
			List<TuikuH> list = tuikuMapper.getTuikuListH(param);
			if (list != null && list.size() > 0) {
				int l = list.size();
				int orderIndex = (Integer.parseInt(page) - 1) * 
						IPageConstants.PageSize + 1;
				for (int i = 0; i < l; i++) {
					list.get(i).setOrderIndex(orderIndex);
					orderIndex++;
				}
			}
			int count = tuikuMapper.getTuikuListHNum(param);
			Map<String, Object> data = new HashMap<>();
			data.put("list", list);
			data.put("totalCount", count);
			data.put("totalPage", PageUtil.getTotalPage(count, IPageConstants.PageSize));
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 退库单列表：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",state:"
					+ state + ",stime:" + stime + ",etime:" + etime + ",page:" + page + ",structId:" + structId
					+ "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 根据头表id查询退库物料
	 */
	@Override
	public Result getTuikuGoodsByHeadAtNo(String billId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param=new HashMap<>();
		param.put("billId", billId);
		try {
			Integer billState=tuikuMapper.getTuikuHState(param);
			List<EditInOutD> list=tuikuMapper.getTuikuGoodsByHeadAtNo(param);
			Map<String, Object> data=new HashMap<>();
			data.put("billState", billState);
			data.put("list", list);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 根据头表id查询退库物料：  errorcode=" + ErrorCode.geterrocode(this) 
			+ "  \r\n" + e + "\r\n" + ",billId:"+ billId + "\r\n\r\n");
		}
		return result;
	}
	
	/**
	 * 删除订单
	 */
	@Override
	public Result delTuikuBill(String billId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		try {
			tuikuMapper.delTuikuDByBillId(param);
			tuikuMapper.delTuikuBill(param);
		} catch (Exception e) {
			log.error("\r\n 删除订单：  errorcode=" + ErrorCode.geterrocode(this) 
			+ "  \r\n" + e + "\r\n" + ",billId:"+ billId + "\r\n\r\n");
			throw new RuntimeException();
		}
		return result;
	}

	/**
	 * 处理订单
	 */
	@Override
	public Result dealTuikuBill(String billId, String reviewState, int structId, 
			int userId, String operType,String remark, String userNm, 
			String billDate,String newList,String editList,
			String delAtNo,String customerId,String customerNm) {
			Result result = new Result();
			result.setCode(CodeConstant.CODE1000);
			result.setMsg("成功");
			Map<String, Object> param = new HashMap<>();
			param.put("billDate", billDate);
			param.put("billId", billId);
			param.put("reviewState", reviewState);
			param.put("structId", structId);
			try {
				if(!StringUtils.isEmpty(billId)&&!billId.equals("-1")){
					// 检查是否已办理过
					Integer nowState = tuikuMapper.getTuikuHState(param);
					if (nowState == null) {
						result.setCode(CodeConstant.CODE200);
						result.setMsg("单据不存在");
						return result;
					}
					if (operType.equals("0") && nowState >= Integer.parseInt(reviewState)
							|| operType.equals("1") && nowState <= Integer.parseInt(reviewState)) {
						result.setCode(CodeConstant.CODE200);
						result.setMsg("此单据已被办理过");
						return result;
					}
				}
				//保存修改
				billId=saveEditTuiku(newList, editList, delAtNo, billId, 
						customerId, customerNm, structId, userId, reviewState);
				param.put("billId", billId);
				if (reviewState.equals("2") && operType.equals("0")) {
					//影响库存
					synchronized (this) {
						List<BaseStockGoods> goodsList=tuikuMapper.
								getGoodsBeforeEffectStock(param);
						if(goodsList!=null&&goodsList.size()>0){
							int l=goodsList.size();
							String goodsIdStr="";
							for (int i = 0; i < l; i++) {
								BaseStockGoods goods=goodsList.get(i);
								goodsIdStr+=goods.getGoodsId()+",";
								goods.setAfterStockNum(goods.getPreStockNum()
										+goods.getNum());
							}
							goodsIdStr=goodsIdStr.substring(0, goodsIdStr.length()-1);
							param.put("goodsList", goodsList);
							param.put("goodsIdStr", goodsIdStr);
							tuikuMapper.effectStock(param);
							String billCode = tuikuMapper.getBillCodeById(param);
							param.put("billCode", billCode);
							tuikuMapper.addStockRecord(param);
						}
					}
				}
				tuikuMapper.updateTuikuHState(param);
				//审批记录
				param.put("billType", 0);
				switch (reviewState) {
				case "0":
					param.put("nodeNm", "经理确认");
					break;
				case "1":
					param.put("nodeNm", "库管提交");
					break;
				case "2":
					param.put("nodeNm", "经理确认");
					break;
				}
				if (operType.equals("0")) {
					param.put("operNm", "通过");
				} else {
					param.put("operNm", "驳回");
				}
				param.put("operatorId", userId);
				param.put("operatorNm", userNm);
				param.put("remark", remark == null ? "" : remark);
				commonMapper.addReturnProcess(param);
				if (reviewState.equals("2")) {
					param.put("nodeNm", "结束");
					param.put("operNm", "");
					param.put("operatorId", -1);
					param.put("operatorNm", "");
					param.put("remark", "");
					commonMapper.addReturnProcess(param);
				}
			} catch (Exception e) {
				log.error("\r\n 审核订单：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",billId:"
						+ billId + "\r\n\r\n");
				e.printStackTrace();
				throw new RuntimeException("操作失败");
			}
			return result;
	}

	/**
	 * 查看退库详情
	 */
	@Override
	public Result viewTuikuD(String billId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		try {
			List<BaseGoods> list=tuikuMapper.viewTuikuD(param);
			TuikuH head=tuikuMapper.getTuikuHById(param);
			Map<String, Object> data=new HashMap<>();
			data.put("list", list);
			data.put("head", head);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查看退库详情：  errorcode=" + ErrorCode.geterrocode(this) 
			+ "  \r\n" + e + "\r\n" + ",billId:"+ billId + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 导出退库单汇总
	 */
	@Override
	public List<ExportTuikuModel> exportTuikuList(String state, String stime, 
			String etime, String customerId,int structId) {
		Map<String, Object> param = new HashMap<>();
		param.put("state", state);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("customerId", customerId);
		param.put("structId", structId);
		try {
			List<ExportTuikuModel> list=tuikuMapper.exportTuikuList(param);
			return list;
		} catch (Exception e) {
			log.error("\r\n 导出退库单汇总：  errorcode=" + ErrorCode.geterrocode(this) 
			+ "  \r\n" + e + "\r\n" + ",state:"+ state + "\r\n\r\n");
			return null;
		}
		
	}

	/* 
	 * Todo : [查询退库列表]
	 * @时间:2017年1月6日下午3:17:37
	 */
	@Override
	public Result getTuikuGoodsListByHeadAtNo(String billId,String customerId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		param.put("customerId", customerId);
		try {
		
			List<BaseGoods> list = tuikuMapper.getTuikuGoodsListByHeadAtNo(param);
			
			result.setData(list);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 根据头表id查询退库物料：  errorcode=" 
			+ ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n"
			+ "billId:" + billId + "\r\n\r\n");
		}
		return result;
	}

	
	/* 
	 * Todo : [保存退库列表]
	 * @时间:2017年1月6日下午5:39:47
	 */
	@Override
	public Result saveTuikuD(int structId, int userId, String newList, String editList, String delAtNo, String customerId,
			String billId, String customerNm) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		try {
			billId=saveEditTuiku(newList,editList,delAtNo,billId,customerId,
					customerNm,structId,userId,"0");
			result.setData(billId);
		} catch (Exception e) {
			log.error("\r\n 保存退库单详情：  errorcode=" + ErrorCode.geterrocode(this) 
			+ "  \r\n" + e + "\r\n" + ",newList:"+ newList+ ",editList:"+ editList
			+ ",delAtNo:"+ delAtNo+ ",billId:"+ billId+ ",customerId:"+customerId
			+ ",structId:"+ structId+ ",userId:"+ userId+ "\r\n\r\n");
			throw new RuntimeException();
		}
		return result;
	}
	
	
	private String saveEditTuiku(String newListStr,String editListStr,
			String delAtNo,String billId,String customerId,String customerNm,
			int structId,int userId,String reviewState){
		if (billId.equals("-1")) {
			synchronized (this) {
				// 生成单据号
				Map<String, Object> param=new HashMap<>();
				param.put("structId", structId);
				Integer maxCodeOrder = tuikuMapper.getMaxCodeOrder(param);
				if (maxCodeOrder == null) {
					maxCodeOrder = 1;
				} else {
					maxCodeOrder++;
				}
				String codeStr = String.valueOf(maxCodeOrder);
				if (codeStr.length() == 1) {
					codeStr = "00" + codeStr;
				} else if (codeStr.length() == 2) {
					codeStr = "0" + codeStr;
				}
				String centerCode = commonMapper.getStructCodeById(param);
				String code = "cg-" + centerCode + "-" + DateUtil.getDate(new Date(), "yyMMdd") + "-" + codeStr;
				param.put("code", code);
				param.put("codeOrder", maxCodeOrder);
				param.put("billId", billId);
				param.put("userId", userId);
				param.put("customerId", customerId);
				param.put("customerNm", customerNm);
				param.put("reviewState", reviewState);
				tuikuMapper.addTuikuH(param);
				billId=param.get("billId").toString();
			}
		}else{
			//更新客户
			Map<String, Object> param=new HashMap<>();
			param.put("billId", billId);
			param.put("customerId", customerId);
			param.put("customerNm", customerNm);
			tuikuMapper.updateTuikuCustomer(param);
		}
		//更新详情
		List<EditInOutD> newList=JSON.parseArray(newListStr, 
				EditInOutD.class);
		if(newList!=null&&newList.size()>0){
			Map<String, Object> param=new HashMap<>();
			param.put("newList", newList);
			param.put("billId", billId);
			tuikuMapper.batchAddTuikuD(param);
		}
		
		List<EditInOutD> editList=JSON.parseArray(editListStr, 
				EditInOutD.class);
		if(editList!=null&&editList.size()>0){
			int l=editList.size();
			String idStr="";
			for (int i = 0; i < l; i++) {
			 	idStr += editList.get(i).getAtNo() + ",";
			}
			if (idStr.length() > 0)
				idStr = idStr.substring(0, idStr.length() - 1);
			Map<String, Object> param=new HashMap<>();
			param.put("idStr", idStr);
			param.put("editList", editList);
			tuikuMapper.batchUpdateTuikuD(param);
		}
		if(!StringUtils.isEmpty(delAtNo)){
			Map<String, Object> param=new HashMap<>();
			param.put("delAtNo", delAtNo);
			tuikuMapper.batchTuikuD(param);
		}
		return billId;
	}
}
