package com.qxh.impl.demand;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.BillAndCustomTeam;
import com.qxh.exmodel.CommonModel;
import com.qxh.exmodel.DemandDetail;
import com.qxh.exmodel.DemandGoods;
import com.qxh.exmodel.DemandGoodsByGoods;
import com.qxh.exmodel.EditInOutD;
import com.qxh.exmodel.ExcelDemandBillByCondition;
import com.qxh.exmodel.GetDemandListByCustomerId;
import com.qxh.exmodel.GroupDemand;
import com.qxh.impl.common.CommonMapper;
import com.qxh.model.Customer;
import com.qxh.model.DemandListD;
import com.qxh.model.DemandListH;
import com.qxh.model.MakeBill;
import com.qxh.service.DemandService;
import com.qxh.utils.DateUtil;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.IPageConstants;
import com.qxh.utils.IdWorker;
import com.qxh.utils.MoneyUtil;
import com.qxh.utils.NumUtil;
import com.qxh.utils.PageUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

import redis.clients.jedis.Jedis;
import sun.security.util.UntrustedCertificates;

public class DemandServiceImpl implements DemandService {

	Logger log = Logger.getLogger(this.getClass());
	private DemandMapper demandMapper;
	private CommonMapper commonMapper;
	private Jedis jedis;

	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}

	public void setDemandMapper(DemandMapper demandMapper) {
		this.demandMapper = demandMapper;
	}

	public void setCommonMapper(CommonMapper commonMapper) {
		this.commonMapper = commonMapper;
	}

	/**
	 * 查询作业组列表
	 */
	@Override
	public Result getTeamList(String customerId, String teamId, String type) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		try {
			List<CommonModel> teamList = new ArrayList<>();
			if (teamId.equals("-1")) {
				// 添加
				Map<String, Object> param = new HashMap<>();
				param.put("customerId", customerId);
				teamList = commonMapper.getTeamList(param);
			} else {
				// 编辑
				String teamNm = commonMapper.getTeamNmById(teamId);
				CommonModel team = new CommonModel();
				team.setAtNo(Integer.parseInt(teamId));
				team.setName(teamNm);
				teamList.add(team);
			}
			result.setData(teamList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询作业组列表：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",customerId:"
					+ customerId + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 查询需求上报列表
	 */
	@Override
	public Result getDemandListH(String state, String stime, String etime, String page, int structId, int roleId,
			String customerId) {
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
			case 9:// 业务员
				param.put("viewPower", 0);
				break;
			case 4:// 经理
			case 10:// 财务
			case 5:// 库管
				param.put("viewPower", 1);
				break;
			}
			List<DemandListH> list = demandMapper.getDemandListH(param);
			if (list != null && list.size() > 0) {
				int l = list.size();
				int orderIndex = (Integer.parseInt(page) - 1) * IPageConstants.PageSize + 1;
				for (int i = 0; i < l; i++) {
					list.get(i).setOrderIndex(orderIndex);
					orderIndex++;
				}
			}
			int count = demandMapper.getDemandListHNum(param);
			Map<String, Object> data = new HashMap<>();
			data.put("list", list);
			data.put("totalCount", count);
			data.put("totalPage", PageUtil.getTotalPage(count, IPageConstants.PageSize));
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询需求上报列表：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",state:"
					+ state + ",stime:" + stime + ",etime:" + etime + ",page:" + page + ",structId:" + structId
					+ "\r\n\r\n");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 按作业组查看需求上报
	 */
	@Override
	public Result getDemandByGroup(String billId, int structId, String customerId, String teamDemandId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		try {
			Map<String, Object> data = new HashMap<>();
			Map<String, Object> param = new HashMap<>();
			param.put("billId", billId);
			param.put("structId", structId);
			param.put("customerId", customerId);
			param.put("teamDemandId", teamDemandId);
			Integer billState = demandMapper.getDemandListHState(param);
			param.put("billState", billState);

			List<GroupDemand> teamList = demandMapper.getDemandByGroup(param);
			double totalMoney = 0;
			if (teamList != null && teamList.size() > 0) {
				int l = teamList.size();
				Map<Integer, GroupDemand> keyMap = new HashMap<>();
				String teamIdStr = "";
				for (int i = 0; i < l; i++) {
					GroupDemand d = teamList.get(i);
					if (d.getState() > 0) {
						keyMap.put(d.getTeamId(), d);
						teamIdStr += d.getTeamId() + ",";
					}
				}
				if (teamIdStr.length() > 0) {
					teamIdStr = teamIdStr.substring(0, teamIdStr.length() - 1);
					param.put("teamIdStr", teamIdStr);
					List<DemandGoods> goodsList = demandMapper.getGoodsByGroupDemand(param);
					if (goodsList != null && goodsList.size() > 0) {
						int goodsLen = goodsList.size();
						for (int i = 0; i < goodsLen; i++) {
							DemandGoods goods = goodsList.get(i);
							goods.setOrderIndex(i + 1);
							GroupDemand d = keyMap.get(goods.getTeamId());
							if (d != null) {
								d.getGoodsList().add(goods);
								totalMoney += goods.getMoney();
							}
						}
					}
				}
			}
			data.put("teamList", teamList);
			data.put("totalMoney", totalMoney);
			data.put("billState", billState);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询需求上报列表：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",billId:"
					+ billId + ",structId:" + structId + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 经理审核订单
	 */
	@Override
	public Result dealDemandBill(String billId, String reviewState, int structId, int userId, String operType,
			String remark, String userNm, String billDate, String newList, String editList, String delAtNo,
			String headAtNo, String customerId, String teamId) {
		// 0：通过
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billDate", billDate);
		param.put("billId", billId);
		param.put("reviewState", reviewState);
		param.put("structId", structId);
		try {
			if (!StringUtils.isEmpty(billId) && !billId.equals("-1")) {
				// 检查是否已办理过
				Integer nowState = demandMapper.getDemandListHState(param);
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
			Map<String, Object> saveData = saveEditDemand(billId, newList, editList, delAtNo, headAtNo, customerId,
					teamId, structId, userId);
			param.put("billId", saveData.get("billId"));
			// 判断库存是否充足
			if (reviewState.equals("2") && operType.equals("0")) {
				List<DemandListD> detailList = demandMapper.getDemandListDBeforeCheckStock(param);
				if (detailList != null && detailList.size() > 0) {
					int l = detailList.size();
					boolean stockEnough = true;
					// String purchaseGoodsId = "";
					for (int i = 0; i < l; i++) {
						DemandListD goods = detailList.get(i);
						if (goods.getStockNum() < goods.getDemandNum() && goods.getGoodsType() == 0
								|| goods.getIsPurchase() == 0 && goods.getGoodsType() == 1) {
							stockEnough = false;
							// purchaseGoodsId += goods.getGoodsId() + ",";
						}
					}
					if (stockEnough == false) {
						// 库存不足，生成待采购单
						// 判断系统中是否有对应的待采购单
						Integer purchaseListHId = demandMapper.getUnPurchaseHId(param);
						if (purchaseListHId == null || purchaseListHId == -1) {
							// 生成采购单头表
							// 生成单据号
							Integer maxCodeOrder = demandMapper.getPurchaseMaxCodeOrder(param);
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
							String code = "cg-" + centerCode + "-" + DateUtil.getDate(new Date(), "yyMMdd") + "-"
									+ codeStr;
							param.put("code", code);
							param.put("codeOrder", maxCodeOrder);
							param.put("purchaseListHId", 0);
							commonMapper.addPurchaseListH(param);
							demandMapper.updateDemandHPurchaseId(param);
							// 生成采购单详细表
							// purchaseGoodsId = purchaseGoodsId.substring(0,
							// purchaseGoodsId.length() - 1);
							// param.put("purchaseGoodsId", purchaseGoodsId);
							commonMapper.addPurchaseListD(param);
						}
						result.setCode(CodeConstant.CODE209);
						result.setMsg("库存不足，已生成待采购单，即将跳往采购入库模块...");
						return result;
					} else {
						synchronized (this) {
							// 影响库存
							List<DemandDetail> goodsList = demandMapper.getDemandListDBeforeEffectStock(param);
							if (goodsList != null && goodsList.size() > 0) {
								int gLength = goodsList.size();
								String goodsIdStr = "";
								for (int i = 0; i < gLength; i++) {
									DemandDetail goods = goodsList.get(i);
									goodsIdStr += goods.getGoodsId() + ",";
									goods.setAfterStockNum(goods.getNum() <= goods.getPreStockNum()
											? goods.getPreStockNum() - goods.getNum() : 0);
								}
								goodsIdStr = goodsIdStr.substring(0, goodsIdStr.length() - 1);
								param.put("goodsIdStr", goodsIdStr);
								param.put("goodsList", goodsList);
								demandMapper.reduceStock(param);
								String billCode = demandMapper.getBillCodeById(param);
								param.put("billCode", billCode);
								demandMapper.addOutStockRecord(param);
							}
						}
					}
				}
			}
			demandMapper.updateDemandListHState(param);
			param.put("billType", 0);
			switch (reviewState) {
			case "0":
				param.put("nodeNm", "库管确认");
				break;
			case "1":
				param.put("nodeNm", "业务员提交");
				break;
			case "2":
				param.put("nodeNm", "库管确认");
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
			commonMapper.addReviewProcess(param);
			if (reviewState.equals("2")) {
				param.put("nodeNm", "结束");
				param.put("operNm", "");
				param.put("operatorId", -1);
				param.put("operatorNm", "");
				param.put("remark", "");
				commonMapper.addReviewProcess(param);
			}
		} catch (Exception e) {
			log.error("\r\n 经理审核订单：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",billId:"
					+ billId + "\r\n\r\n");
			e.printStackTrace();
			throw new RuntimeException("库存不足");
		}
		return result;
	}

	/**
	 * 清空作业组上报的物料
	 */
	@Override
	public Result clearTeamDemand(String teamDemandId, String teamId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("teamDemandId", teamDemandId);
		param.put("teamId", teamId);
		try {
			demandMapper.clearTeamDemand(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 清空作业组上报的物料：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n"
					+ ",teamDemandId:" + teamDemandId + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 删除订单
	 */
	@Override
	public Result delDemandBill(String billId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		try {
			List<Integer> teamDList = demandMapper.getTeamDIdList(param);
			if (teamDList != null && teamDList.size() > 0) {
				int l = teamDList.size();
				String teamIdStr = "";
				for (int i = 0; i < l; i++) {
					teamIdStr += teamDList.get(i) + ",";
				}
				teamIdStr = teamIdStr.substring(0, teamIdStr.length() - 1);
				param.put("teamIdStr", teamIdStr);
				demandMapper.delDemandDByParent(param);
				demandMapper.delTeamDemandByParent(param);
			}
			demandMapper.delDemandBill(param);
		} catch (Exception e) {
			log.error("\r\n 删除订单：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",billId:"
					+ billId + "\r\n\r\n");
			throw new RuntimeException();
		}
		return result;
	}

	/**
	 * 客户列表
	 */
	@Override
	public Result getCustomerList(int structId, String billId, int roleId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("structId", structId);
		param.put("billId", billId);
		param.put("countermanId", roleId);
		try {
			Integer billState = demandMapper.getDemandListHState(param);
			param.put("billState", billState);
			List<Customer> customerList = demandMapper.getCustomerList(param);
			Map<String, Object> data = new HashMap<>();
			data.put("list", customerList);
			data.put("billState", billState);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 客户列表：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "  \r\n" + "structId:"
					+ structId + "\r\n\r\n");
		}
		return result;
	}

	/*
	 * Todo : [获取导出的集合]
	 * 
	 * @时间:2016年11月24日上午9:33:13
	 */
	@Override
	public Result getExclList(String billId, String teamId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		param.put("teamId", teamId);
		try {
			List<BillAndCustomTeam> billAndCustomTeamList = demandMapper.getExclList(param);

			String page = "1";
			if (billAndCustomTeamList != null && billAndCustomTeamList.size() > 0) {
				int l = billAndCustomTeamList.size();
				int orderIndex = (Integer.parseInt(page) - 1) * IPageConstants.PageSize + 1;
				for (int i = 0; i < l; i++) {
					billAndCustomTeamList.get(i).setOrderIndex(orderIndex);
					orderIndex++;
				}
			}
			result.setData(billAndCustomTeamList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return result;
	}

	/*
	 * @时间:2016年12月10日下午1:22:35
	 */
	@Override
	public List<BillAndCustomTeam> getExportOutDemandBill(String billId, String teamId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		param.put("teamId", teamId);
		try {
			List<BillAndCustomTeam> billAndCustomTeamList = demandMapper.getExclList(param);

			String page = "1";
			if (billAndCustomTeamList != null && billAndCustomTeamList.size() > 0) {
				int l = billAndCustomTeamList.size();
				int orderIndex = (Integer.parseInt(page) - 1) * IPageConstants.PageSize + 1;
				for (int i = 0; i < l; i++) {
					billAndCustomTeamList.get(i).setOrderIndex(orderIndex);
					orderIndex++;
				}
			}

			return billAndCustomTeamList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/*
	 * Todo : [客户申报汇总单]
	 * 
	 * @时间:2016年12月20日上午11:37:29
	 */
	@Override
	public List<DemandListH> getDemandListHForExcel(String state, String stime, String etime, String page, int structId,
			int roleId, String customerId) {
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
			case 9:// 业务员
				param.put("viewPower", 0);
				break;
			case 4:// 经理
			case 10:// 财务
			case 5:// 库管
				param.put("viewPower", 1);
				break;
			}
			List<DemandListH> list = demandMapper.getDemandListH(param);
			return list;
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Todo : [按物料查]
	 * 
	 * @时间:2016年12月22日上午10:10:08
	 */
	@Override
	public Result selectDemandGoodsByGoods(String billId, int structId, String type, String goodsNm,
			String customerId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		param.put("structId", structId);
		param.put("selectType", type);
		param.put("goodsNm", goodsNm);
		param.put("customerId", customerId);
		try {
			List<DemandGoodsByGoods> DemandGoodsList = demandMapper.selectDemandGoodsByGoods(param);
			// 设置标志位
			if (DemandGoodsList != null && DemandGoodsList.size() > 0) {
				int n = 0;
				double totalMoney = 0;
				Double totalCount = 0.00;
				int orderIndex = 1;// 序号标志位
				for (int i = 0; i < DemandGoodsList.size(); i++) {
					DemandGoodsList.get(i).setOrderIndex(orderIndex);
					orderIndex++;
					DemandGoodsByGoods demandGoodsByGoods = DemandGoodsList.get(i);
					if (i > 0) {
						if (DemandGoodsList.get(i - 1).getGoodsNm().equals(demandGoodsByGoods.getGoodsNm())) {
							if (i == DemandGoodsList.size() - 1) {
								DemandGoodsList.get(i - (n + 1)).setFlat(n + 2);
								demandGoodsByGoods.setFlat(0);
								n = 0;
							} else {
								demandGoodsByGoods.setFlat(0);
								n = n + 1;
							}
						} else {
							DemandGoodsList.get(i - (n + 1)).setFlat(n + 1);
							demandGoodsByGoods.setFlat(1);
							n = 0;
						}
					} else {
						demandGoodsByGoods.setFlat(1);
					}
					totalCount += Double.parseDouble(demandGoodsByGoods.getDemandNum());
					totalMoney += demandGoodsByGoods.getMoney();
				}
				DemandGoodsByGoods total = new DemandGoodsByGoods();
				total.setGoodsNm("合计");
				total.setMoney(totalMoney);
				total.setDemandNum(String.valueOf(totalCount));
				total.setTotalMonay(totalMoney);
				DemandGoodsList.add(total);
			}
			result.setData(DemandGoodsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/*
	 * Todo : [按客户查看]
	 * 
	 * @时间:2016年12月22日下午5:17:08
	 */
	@Override
	public Result selectDemandGoodsByCustomer(String billId, int structId, String type, String customerId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		param.put("structId", structId);
		param.put("selectType", type);
		param.put("customerId", customerId);
		try {
			List<DemandGoodsByGoods> DemandGoodsList = demandMapper.selectDemandGoodsByGoods(param);
			// 设置标志位
			if (DemandGoodsList != null && DemandGoodsList.size() > 0) {
				int n = 0;
				Double totalMonay = 0.00;
				int orderIndex = 1;
				for (int i = 0; i < DemandGoodsList.size(); i++) {
					DemandGoodsList.get(i).setOrderIndex(orderIndex);
					orderIndex++;
					DemandGoodsByGoods demandGoodsByGoods = DemandGoodsList.get(i);
					if (i > 0) {
						if (DemandGoodsList.get(i - 1).getCustomNm().equals(demandGoodsByGoods.getCustomNm())) {
							if (i == DemandGoodsList.size() - 1) {
								DemandGoodsList.get(i - (n + 1)).setFlat(n + 2);
								DemandGoodsList.get(i - (n + 1))
										.setTotalMonay(totalMonay + demandGoodsByGoods.getPrice()
												* Double.parseDouble(demandGoodsByGoods.getDemandNum()));
								demandGoodsByGoods.setFlat(0);
								n = 0;
							} else {
								demandGoodsByGoods.setFlat(0);
								totalMonay += demandGoodsByGoods.getPrice()
										* Double.parseDouble(demandGoodsByGoods.getDemandNum());
								n = n + 1;
							}
						} else {
							DemandGoodsList.get(i - (n + 1)).setFlat(n + 1);
							DemandGoodsList.get(i - (n + 1)).setTotalMonay(totalMonay);
							demandGoodsByGoods.setFlat(1);
							demandGoodsByGoods.setTotalMonay(demandGoodsByGoods.getPrice()
									* Double.parseDouble(demandGoodsByGoods.getDemandNum()));
							n = 0;
							totalMonay = demandGoodsByGoods.getPrice()
									* Double.parseDouble(demandGoodsByGoods.getDemandNum());
						}
					} else {
						demandGoodsByGoods.setFlat(1);
						demandGoodsByGoods.setTotalMonay(
								demandGoodsByGoods.getPrice() * Double.parseDouble(demandGoodsByGoods.getDemandNum()));
						totalMonay = demandGoodsByGoods.getPrice()
								* Double.parseDouble(demandGoodsByGoods.getDemandNum());
					}
				}
			}
			result.setData(DemandGoodsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/*
	 * Todo : [客户汇总导出]
	 * 
	 * @时间:2016年12月24日上午8:52:04
	 */
	@Override
	public List<ExcelDemandBillByCondition> getExcelOutDemandListHBill(String state, String stime, String etime,
			int structId, int roleId, String customerId) {
		Map<String, Object> param = new HashMap<>();
		param.put("state", state);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("customerId", customerId);
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
			case 9:// 业务员
				param.put("viewPower", 0);
				break;
			case 4:// 经理
			case 10:// 财务
			case 5:// 库管
				param.put("viewPower", 1);
				break;
			}
			List<ExcelDemandBillByCondition> list = demandMapper.getExcelOutDemandListHBill(param);
			// int n = 0;
			for (int i = 0; i < list.size(); i++) {
				ExcelDemandBillByCondition demandBillByCondition = list.get(i);
				if (i > 0) {
					if (list.get(i - 1).getBillCode().equals(demandBillByCondition.getBillCode())) {// 账单号相同的情况
						if (!list.get(i - 1).getCustomNm().equals(demandBillByCondition.getCustomNm())) {// 客户名相同情况
							demandBillByCondition.setName(demandBillByCondition.getCustomNm());
						}
					} else {
						demandBillByCondition.setName(demandBillByCondition.getCustomNm());
						demandBillByCondition.setBill(demandBillByCondition.getBillCode());
						demandBillByCondition.setTime(demandBillByCondition.getBillDate().substring(0, 10));

					}

				} else {
					demandBillByCondition.setName(demandBillByCondition.getCustomNm());
					demandBillByCondition.setBill(demandBillByCondition.getBillCode());
					demandBillByCondition.setTime(demandBillByCondition.getBillDate().substring(0, 10));
				}

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询入库单详情
	 */
	@Override
	public Result getDemandD(String billId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		try {
			Integer billState = demandMapper.getDemandListHState(param);
			List<EditInOutD> list = demandMapper.getDemandD(param);
			int orderIndex = 1;
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setOrderIndex(orderIndex);
				orderIndex++;
			}
			Map<String, Object> data = new HashMap<>();
			data.put("billState", billState);
			data.put("list", list);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询入库单详情：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",billId:"
					+ billId + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 获取物料售价
	 */
	@Override
	public Result getGoodsPrice(String customerId, String goodsId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("customerId", customerId);
		param.put("goodsId", goodsId);
		try {
			Double price = demandMapper.getTagPrice(param);
			if (price == null) {
				price = 0d;
			}
			result.setData(price);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 获取物料售价：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",customerId:"
					+ customerId + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 保存需求单详情
	 */
	@Override
	public Result saveDemandD(String billId, String newList, String editList, String delAtNo, String headAtNo,
			String customerId, String teamId, int structId, int userId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		try {
			Map<String, Object> data = saveEditDemand(billId, newList, editList, delAtNo, headAtNo, customerId, teamId,
					structId, userId);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 保存需求单详情：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",newList:"
					+ newList + ",editList:" + editList + ",delAtNo:" + delAtNo + "\r\n\r\n");
		}
		return result;
	}

	private Map<String, Object> saveEditDemand(String billId, String newListStr, String editListStr, String delAtNo,
			String headAtNo, String customerId, String teamId, int structId, int userId) {
		Map<String, Object> param = new HashMap<>();
		param.put("teamId", teamId);
		param.put("customerId", customerId);
		param.put("teamDemandId", headAtNo);
		param.put("demandListHId", billId);
		param.put("structId", structId);
		param.put("userId", userId);
		param.put("customerId", customerId);
		if (StringUtils.isEmpty(billId) || billId.equals("-1")) {
			synchronized (this) {
				// 生成单据号
				Integer maxCodeOrder = demandMapper.getMaxCodeOrder(param);
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
				String code = "xq-" + centerCode + "-" + DateUtil.getDate(new Date(), "yyMMdd") + "-" + codeStr;
				param.put("code", code);
				param.put("codeOrder", maxCodeOrder);
				param.put("structId", structId);
				param.put("userId", userId);
				demandMapper.addDemandListH(param);
			}
		}
		if ((StringUtils.isEmpty(headAtNo) || Integer.parseInt(headAtNo) <= 0) && Integer.parseInt(billId) < 0) {
			demandMapper.addTeamDemand(param);
			headAtNo = param.get("teamDemandId") + "";
		}
		billId = param.get("demandListHId").toString();
		List<EditInOutD> newList = JSON.parseArray(newListStr, EditInOutD.class);
		// 获取临时物料 goodsId=-1
		if (newList != null && newList.size() > 0) {
			for (int i = 0; i < newList.size(); i++) {
				EditInOutD editInOutD = newList.get(i);
				if (editInOutD.getGoodsId() == -1) {
					String code = IdWorker.createId(10);
					editInOutD.setCode(code);
				} else {
					editInOutD.setCode("");
				}
			}
		}
		if (newList != null && newList.size() > 0) {
			try {
				// 缓存在redis中
				for (int i = 0; i < newList.size(); i++) {
					int goodsId = newList.get(i).getGoodsId();
					String redisGoodsPriceKey = structId + "" + customerId + "" + goodsId + "";
					jedis.lpush(redisGoodsPriceKey, newList.get(i).getPrice() + "");
				}
			} catch (Exception e) {
			}
			Map<String, Object> param1 = new HashMap<>();
			param1.put("newList", newList);
			param1.put("headAtNo", headAtNo);
			param1.put("customerId", customerId);
			param1.put("teamId", teamId);
			demandMapper.batchAddDemandD(param1);
		}
		List<EditInOutD> editList = JSON.parseArray(editListStr, EditInOutD.class);
		if (editList != null && editList.size() > 0) {
			try {
				// 缓存在redis中
				for (int i = 0; i < editList.size(); i++) {
					int goodsId = editList.get(i).getGoodsId();
					String redisGoodsPriceKey = structId + "" + customerId + "" + goodsId + "";
					jedis.lpush(redisGoodsPriceKey, editList.get(i).getPrice() + "");
				}
			} catch (Exception e) {
			}
			int l = editList.size();
			String idStr = "";
			for (int i = 0; i < l; i++) {
				idStr += editList.get(i).getAtNo() + ",";
			}
			if (idStr.length() > 0)
				idStr = idStr.substring(0, idStr.length() - 1);
			Map<String, Object> param1 = new HashMap<>();
			param1.put("idStr", idStr);
			param1.put("editList", editList);
			demandMapper.batchUpdateDemandD(param1);
		}
		if (!StringUtils.isEmpty(delAtNo)) {
			Map<String, Object> param1 = new HashMap<>();
			param1.put("delAtNo", delAtNo);
			demandMapper.batchDelDemandD(param1);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("teamDemandId", headAtNo);
		data.put("billId", billId);
		return data;
	}

	/*
	 * Todo : [获取申请物料的列表]
	 * 
	 * @时间:2017年1月5日下午3:31:39
	 */
	@Override
	public Result getAllDemandGoodsListByTeamId(String teamDemandId, String teamId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("teamId", teamId);
		param.put("teamDemandId", teamDemandId);
		try {
			List<EditInOutD> list = demandMapper.getAllDemandGoodsListByTeamId(param);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setOrderIndex(i + 1);
			}
			result.setData(list);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 根据作业组id查询物料详情：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n"
					+ ",teamId:" + teamId + ",teamDemandId:" + teamDemandId + "\r\n\r\n");
		}
		return result;
	}

	/*
	 * Todo : [获取出库详情列表]
	 * 
	 * @时间:2017年1月9日上午9:27:00
	 */
	@Override
	public List<GetDemandListByCustomerId> getDemandListByCustomerId(String billId, String teamId) {
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		param.put("teamId", teamId);
		List<GetDemandListByCustomerId> list = null;
		try {
			// 通过billId查看是否已存在makeBilll
			Integer count = demandMapper.checkMakeBillByBillid(param);
			if (count > 0) {
				// 查询对账单
				list = demandMapper.getMakeBillListByBillId(param);
				String page = "1";
				if (list != null && list.size() > 0) {
					int l = list.size();
					int orderIndex = (Integer.parseInt(page) - 1) * IPageConstants.PageSize + 1;
					for (int i = 0; i < l; i++) {
						list.get(i).setOrderIndex(orderIndex);
						orderIndex++;
					}
				}
			} else {
				list = demandMapper.getDemandListByCustomerId(param);
				String page = "1";
				if (list != null && list.size() > 0) {
					int l = list.size();
					int orderIndex = (Integer.parseInt(page) - 1) * IPageConstants.PageSize + 1;
					for (int i = 0; i < l; i++) {
						list.get(i).setOrderIndex(orderIndex);
						orderIndex++;
					}
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/*
	 * Todo : [获取物料价格的集合]
	 * 
	 * @时间:2017年1月10日上午10:26:43
	 */
	@Override
	public Result getRedisGoodsPriceListBykey(int structId, String customerId, String redisGoodsIds) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		String key = structId + "" + customerId + redisGoodsIds;
		try {
			List<String> list = jedis.lrange(key, 0, 2);
			result.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * Todo : [将出单的数据存入redis中]
	 * 
	 * @时间:2017年1月10日下午8:43:41
	 */
	@Override
	public Result storageDataAction(String billId, String customerTeamId, String editListStr, String newListStr) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("teamId", customerTeamId);
		param.put("billId", billId);
		try {
			// 通过billId获取makeBill信息
			List<GetDemandListByCustomerId> makeBillList = demandMapper.getMakeBillMessageByBillId(param);
			String customNm = makeBillList.get(0).getCustomerNm(); // 客户名称
			// String teamNm = makeBillList.get(0).getTeamNm(); // 工作组名称
			String billCode = makeBillList.get(0).getBillCode(); // 单号
			String billDate = makeBillList.get(0).getBillDate();// 订单日期
			Double tatolCounts = 0.00; // 总钱数
			if (makeBillList != null && makeBillList.size() > 0) {
				for (int i = 0; i < makeBillList.size(); i++) {
					tatolCounts += NumUtil.trim0(Double.parseDouble(makeBillList.get(i).getMoney()));
				}
				tatolCounts = NumUtil.trim0(tatolCounts);
			}
			// ==========定义集合前的死数据======================================
			GetDemandListByCustomerId title = new GetDemandListByCustomerId();// 大标题
			title.setCode("-1");

			GetDemandListByCustomerId chuKuDan = new GetDemandListByCustomerId();// 出库单
			chuKuDan.setCode("-2");

			GetDemandListByCustomerId customerAndBillDate = new GetDemandListByCustomerId();// 客户/账单/日期
			customerAndBillDate.setCode("-3");
			customerAndBillDate.setCustomerNm(customNm);
			customerAndBillDate.setBillCode(billCode);
			customerAndBillDate.setBillDate(billDate.substring(0, 10));

			GetDemandListByCustomerId andCustomTeamTotalList = new GetDemandListByCustomerId();// 列标
			andCustomTeamTotalList.setCode("-4");

			GetDemandListByCustomerId andCustomTeamTotalAllMoney = new GetDemandListByCustomerId();// 总计
			andCustomTeamTotalAllMoney.setCode("-5");
			andCustomTeamTotalAllMoney.setPrice(tatolCounts);
			andCustomTeamTotalAllMoney.setGoodsNm(MoneyUtil.toChinese(tatolCounts + ""));

			GetDemandListByCustomerId andCustomTeamSmallQianzi = new GetDemandListByCustomerId();// 签字
			andCustomTeamSmallQianzi.setCode("-7");
			// ==========定义集合前的死数据======================================

			int size = makeBillList.size();
			int len = 0;// 定义循环次数
			if (size < 25) {
				len = 1;
			} else {
				if (size % 25 != 0) {
					len = size / 25 + 1;
				} else {
					len = size / 25;
				}
			}

			ArrayList<GetDemandListByCustomerId> newList = new ArrayList<>();// 定义一个新集合,用来装排列的数据
			int count = 0;
			for (int i = 0; i < len; i++) {
				GetDemandListByCustomerId andEditInOutDSmallMoney = new GetDemandListByCustomerId();// 小计
				andEditInOutDSmallMoney.setCode("-6");
				newList.add(title); // 标题
				newList.add(chuKuDan); // 账单名称
				newList.add(customerAndBillDate); // 日期等格式
				newList.add(andCustomTeamTotalList); // 列的标题

				if (size < 25) {
					// 小计
					double smillCount = 0;
					// 数量
					double smillNum = 0;
					for (int j = 0; j < size; j++) {
						GetDemandListByCustomerId makeBill = controlLen(makeBillList, count);// 控制物料名称长度
						newList.add(makeBill);
						smillCount += Double.parseDouble(makeBill.getMoney());
						smillNum += Double.parseDouble(makeBill.getNum());
						count++;
					}
					andEditInOutDSmallMoney.setGoodsCode(MoneyUtil.toChinese(smillCount + ""));
					andEditInOutDSmallMoney.setPrice(smillCount);
					andEditInOutDSmallMoney.setNum(String.valueOf(smillNum));
				} else {
					// 小计
					double smillCount = 0;
					// 数量
					double smillNum = 0;
					for (int j = 0; j < 25; j++) {
						GetDemandListByCustomerId controlLen = controlLen(makeBillList, count);// 控制物料名称长度
						newList.add(controlLen);// 从list中获取每个元素添加到newlist集合中
						smillCount += Double.parseDouble(controlLen.getMoney());
						smillNum += Double.parseDouble(controlLen.getNum());
						count++;
					}
					andEditInOutDSmallMoney.setPrice(smillCount);
					andEditInOutDSmallMoney.setNum(String.valueOf(smillNum));
					andEditInOutDSmallMoney.setGoodsCode(MoneyUtil.toChinese(smillCount + ""));
					size = size - 25;
				}
				newList.add(andEditInOutDSmallMoney);
				newList.add(andCustomTeamTotalAllMoney);
				newList.add(andCustomTeamSmallQianzi);
			}
			UUID uuid = UUID.randomUUID();
			String key = uuid.toString();
			for (int i = 0; i < newList.size(); i++) {
				String goodsNm = newList.get(i).getGoodsNm();
				if (goodsNm != null && goodsNm.length() > 4) {
					String substr = goodsNm.substring(0, 4);
					newList.get(i).setGoodsNm(substr);
				}
			}
			String string = JSON.toJSONString(newList);
			SessionUtil.getSession().setAttribute(key, string);
			// jedis.set(key, string);
			result.setData(key);
			// result.setData(newList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("请先执行保存!");
			e.printStackTrace();
		}
		return result;
	}

	private GetDemandListByCustomerId controlLen(List<GetDemandListByCustomerId> list, int count) {
		GetDemandListByCustomerId makeBill = list.get(count);
		// 为临时物料添加编码
		int t = 1;
		if ("1".equals(makeBill.getGoodsType())) {
			makeBill.setGoodsCode("010107060" + count + t);
			t++;
		}
		// 截取物料名称
		String goodsNm = makeBill.getGoodsNm();
		if (StringUtils.isNotEmpty(goodsNm)) {
			int length = goodsNm.length();
			if (length > 5) {
				goodsNm = goodsNm.substring(0, 5);
				makeBill.setGoodsNm(goodsNm);
			}
		}
		// 截取 规格
		String spec = makeBill.getSpec();
		if (StringUtils.isNotEmpty(spec)) {
			if (spec.length() > 3) {
				spec = spec.substring(0, 3);
				makeBill.setSpec(spec);
			}
		}
		// 截取单位
		String unitNm = makeBill.getUnitNm();
		if (StringUtils.isNotEmpty(unitNm)) {
			if (unitNm.length() > 3) {
				unitNm = unitNm.substring(0, 3);
				makeBill.setUnitNm(unitNm);
			}
		}
		return makeBill;
	}

	/*
	 * Todo : [打印出库单详情]
	 * 
	 * @时间:2017年1月10日下午9:01:30
	 */
	@Override
	public ArrayList<GetDemandListByCustomerId> getDataListByKey(String key) {

		ArrayList<GetDemandListByCustomerId> list = null;
		try {

			// String string = jedis.get(key);
			String string = (String) SessionUtil.getSession().getAttribute(key);
			list = (ArrayList<GetDemandListByCustomerId>) JSON.parseArray(string, GetDemandListByCustomerId.class);

			// jedis.expireAt(key, 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * Todo : [通过时间和客户id查找账单]
	 * 
	 * @时间:2017年1月15日上午9:38:04
	 */
	@Override
	public List<DemandListD> getDemandBillByCustomerId(String customerId, int structId, String stimeM, String etimeM) {
		Map<String, Object> param = new HashMap<>();
		param.put("customerId", customerId);
		param.put("structId", structId);
		param.put("stimeM", stimeM);
		param.put("etimeM", etimeM);
		List<DemandListD> list = null;
		try {
			list = demandMapper.getDemandBillByCustomerId(param);
			if (list != null && list.size() > 0) {
				int l = list.size();
				int orderIndex = 1;
				for (int i = 0; i < l; i++) {
					list.get(i).setOrderIndex(orderIndex);
					orderIndex++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BillAndCustomTeam> getGoodsByTerm(String billId, String teamId, String codes) {
		Result result = new Result();
		// result.setCode(CodeConstant.CODE1000);
		// result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		param.put("teamId", teamId);
		List<BillAndCustomTeam> goodsByTerm = null;
		try {
			// 物料分类数组
			ArrayList<String> codeList = new ArrayList<String>();
			String[] goodsKindCodes = codes.split(",");
			for (int i = 0; i < goodsKindCodes.length; i++) {
				codeList.add(goodsKindCodes[i]);
			}
			param.put("codeList", codeList);
			goodsByTerm = demandMapper.getGoodsByTerm(param);
			// 添加序号
			int orderIndex = 1;
			for (int i = 0; i < goodsByTerm.size(); i++) {
				goodsByTerm.get(i).setOrderIndex(orderIndex);
				orderIndex++;
			}

		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询入库单详情：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",billId:"
					+ billId + "\r\n\r\n");
		}
		return goodsByTerm;
	}

	/**
	 * 保存出库修改的做账信息
	 */
	@Override
	public Result saveMakeBill(String billId, Integer structId, String customerId, String customerTeamId,
			String delAtNo, String editListStr, String newListStr) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		param.put("structId", structId);
		param.put("customerId", customerId);
		param.put("customerTeamId", customerTeamId);
		try {
			List<MakeBill> editList = JSON.parseArray(editListStr, MakeBill.class);
			List<MakeBill> newStrList = JSON.parseArray(newListStr, MakeBill.class);
			// 检查是否生成对账单
			Integer count = demandMapper.checkMakeBillByBillid(param);
			if (count > 0) {
				// 批量添加
				if (newStrList.size() > 0) {
					param.put("list", newStrList);
					demandMapper.saveMakeBill(param);
				}
				// 批量更新
				int l = editList.size();
				String idStr = "";
				for (int i = 0; i < l; i++) {
					idStr += editList.get(i).getAtNo() + ",";
				}
				if (idStr.length() > 0) {
					idStr = idStr.substring(0, idStr.length() - 1);
					Map<String, Object> param1 = new HashMap<>();
					param1.put("idStr", idStr);
					param1.put("editList", editList);
					demandMapper.batchUpdateMakeBill(param1);
				}
				if (!StringUtils.isEmpty(delAtNo)) {
					Map<String, Object> param1 = new HashMap<>();
					param1.put("delAtNo", delAtNo);
					demandMapper.batchDelMakeBill(param1);
				}
			} else {
				// 用于批量添加makeBill信息
				List<MakeBill> addlist = new ArrayList<>();
				if (editList != null && editList.size() > 0) {
					for (int i = 0; i < editList.size(); i++) {
						MakeBill makeBill = editList.get(i);
						if (makeBill.getGoodsType() == 0) {
							addlist.add(makeBill);
							makeBill.setGoodsNm("");
							makeBill.setUnitNm("");
							makeBill.setSpec("");
						} else {
							addlist.add(makeBill);
						}
					}
				}
				if (newStrList != null && newStrList.size() > 0) {
					for (int i = 0; i < newStrList.size(); i++) {
						MakeBill makeBill = newStrList.get(i);
						if (makeBill.getGoodsType() == 0) {
							addlist.add(makeBill);
							makeBill.setGoodsNm("");
							makeBill.setUnitNm("");
							makeBill.setSpec("");
						} else {
							addlist.add(makeBill);
						}
					}
				}
				param.put("list", addlist);
				// 批量添加MakeBill
				demandMapper.saveMakeBill(param);
			}
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("添加数据失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 导出makeBill
	 */
	@Override
	public List<GetDemandListByCustomerId> getMakeBillByBIllIdAndTeanId(String billId, String teamId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		param.put("teamId", teamId);
		List<GetDemandListByCustomerId> makeBillList = null;
		try {
			Integer orderIndex = 1;
			makeBillList = demandMapper.getMakeBillMessageByBillId(param);
			for (int i = 0; i < makeBillList.size(); i++) {
				makeBillList.get(i).setOrderIndex(orderIndex);
				orderIndex++;
			}
			result.setData(makeBillList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return makeBillList;
	}

}
