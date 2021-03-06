package com.qxh.impl.purchaseSumm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.Units;

import com.alibaba.fastjson.JSON;
import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.DemandGoods;
import com.qxh.exmodel.DemandSummaryD;
import com.qxh.exmodel.EditInOutD;
import com.qxh.impl.demandSumm.DemandSummMapper;
import com.qxh.model.BigKind;
import com.qxh.model.GoodsClass;
import com.qxh.model.GoodsKind;
import com.qxh.model.ProfitSumm;
import com.qxh.model.PurchaseSumm;
import com.qxh.model.PurchaseSummD;
import com.qxh.service.DemandSummService;
import com.qxh.service.PurchaseSummService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.NumUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : PurchaseSummServiceImpl
 * @todo : [采购汇总实现类]
 * 
 *       创建时间 ： 2016年12月2日上午11:27:31
 */
public class PurchaseSummServiceImpl implements PurchaseSummService {

	private PurchaseSummMapper purchaseSummMapper;

	public void setPurchaseSummMapper(PurchaseSummMapper purchaseSummMapper) {
		this.purchaseSummMapper = purchaseSummMapper;
	}

	private DemandSummMapper demandSummMapper;

	public void setDemandSummMapper(DemandSummMapper demandSummMapper) {
		this.demandSummMapper = demandSummMapper;
	}

	/*
	 * Todo : [获取列表]
	 * 
	 * @时间:2016年12月2日上午11:34:04
	 */
	@Override
	public Result getPurchaseSummList(String stime, String etime, int structId, int roleId, String customerId,
			String goodsNm, String kindCode, String supplierId) {

		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("structId", structId);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("customerId", customerId);
		param.put("goodsNm", goodsNm);
		param.put("kindCode", kindCode);
		param.put("supplierId", supplierId);

		try {
			// 集合
			Double totalMoney = 0.00;// 总计
			Integer orderIndex = 1;
			List<PurchaseSumm> list = purchaseSummMapper.getPurchaseSummList(param);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setOrderIndex(orderIndex);
				orderIndex++;
				Double totalMn = list.get(i).getTotalMn();
				totalMoney = totalMoney + totalMn;
			}
			PurchaseSumm purchaseSumm = new PurchaseSumm();
			purchaseSumm.setGoodsNm("合计");
			purchaseSumm.setTotalMn(totalMoney);
			list.add(purchaseSumm);
			Map<String, Object> data = new HashMap<>();
			data.put("list", list);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<PurchaseSumm> getOutExcelPurchaseSummList(String stime, String etime, int structId, int roleId,
			String customerId, String goodsNm, String goodskind, String supplierId) {

		Map<String, Object> param = new HashMap<>();
		param.put("structId", structId);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("customerId", customerId);
		param.put("goodsNm", goodsNm);
		param.put("kindCode", goodskind);
		param.put("supplierId", supplierId);
		try {
			// 集合
			List<PurchaseSumm> list = purchaseSummMapper.getPurchaseSummList(param);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Todo : [详情页面]
	 * 
	 * @时间:2016年12月2日下午2:18:01
	 */
	@Override
	public Result getPurchaseSummDList(String goodsId, String stime, String etime, String goodsType, int structId,
			String purchaselistDId, String customerId) {

		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsId", goodsId);
		param.put("structId", structId);
		param.put("goodsType", goodsType);
		param.put("purchaselistDId", purchaselistDId);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("customerId", customerId);
		try {
			PurchaseSumm purchaseSumm = purchaseSummMapper.getGoodsNmByPurchaseListD(param);
			String goodsNm = purchaseSumm.getGoodsNm();
			param.put("godosNm", goodsNm);
			// 集合
			List<PurchaseSummD> list = purchaseSummMapper.getPurchaseSummDList(param);
			Map<String, Object> data = new HashMap<>();
			data.put("list", list);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * Todo : [单品毛利汇总]
	 * 
	 * @时间:2017年3月6日上午11:34:04
	 */
	@Override
	public Result getProfitSummDList(String customerIds, String stime, String etime, int structId, String goodsNm,
			String kindCode) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("structId", structId);
		// param.put("customerIds", customerIds);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("goodsNm", goodsNm);
		param.put("customerIdsStr", customerIds);
		param.put("kindCode", kindCode);
		try {
			// 创建单品毛利信息集合
			List<ProfitSumm> profitList = new ArrayList<>();
			Double totalProMoney = 0.00;// 毛利总计
			Double totalRate = 0.00;// 毛利率总计
			// 获取订单(销售)汇总信息
			List<DemandGoods> demandSummList = demandSummMapper.getDemandSummaryForProfit(param);
			Integer orderIndex = 1;
			Double totaldMoney = 0.00;// 销售合计
			for (int i = 0; i < demandSummList.size(); i++) {
				DemandGoods demandGoods = demandSummList.get(i);
				ProfitSumm profitSumm = new ProfitSumm();
				profitSumm.setGoodsId(demandGoods.getGoodsId());// 物料Id
				profitSumm.setGoodsNm(demandGoods.getGoodsNm());// 物料名称
				profitSumm.setBillDate(demandGoods.getBillDate().substring(0, 10));// 时间
				profitSumm.setGoodsType(demandGoods.getGoodsType());// 类型
				profitSumm.setOrderNum(demandGoods.getDemandNum());// 数量
				profitSumm.setdPirce(demandGoods.getPrice());// 销售价
				profitSumm.setdMoney(demandGoods.getMoney());// 销售收入
				totaldMoney += demandGoods.getMoney();
				profitSumm.setTcode(demandGoods.getTcode());// 标注临时物料
				profitSumm.setUnitNm(demandGoods.getUnitNm());// 单位
				profitSumm.setDemandListDId(demandGoods.getDemandListDId());// 需求订单物料Id
				profitSumm.setOrderIndex(orderIndex);
				orderIndex++;
				if (demandGoods.getCode().length() > 4) {
					String code = demandGoods.getCode().substring(0, 4);
					param.put("code", code);
					GoodsClass kindNmByCode = purchaseSummMapper.getKindNmByCode(param);
					if (kindNmByCode != null) {
						profitSumm.setKindNm(kindNmByCode.getKindNm());
					}
				} else {
					param.put("code", demandGoods.getCode());
					GoodsClass kindNmByCode = purchaseSummMapper.getKindNmByCode(param);
					if (kindNmByCode != null) {
						profitSumm.setKindNm(kindNmByCode.getKindNm());
					}
				}

				profitList.add(profitSumm);
			}
			// 获取采购汇总信息
			List<PurchaseSumm> PurchaseSummList = purchaseSummMapper.getProfitSummList(param);
			Double totalPMoney = 0.00;// 成本合计
			for (int i = 0; i < PurchaseSummList.size(); i++) {
				PurchaseSumm purchaseSumm = PurchaseSummList.get(i);
				for (int j = 0; j < profitList.size(); j++) {
					ProfitSumm profitSumm = profitList.get(j);
					if (!StringUtils.isEmpty(profitSumm.getTcode()) && !StringUtils.isEmpty(purchaseSumm.getTcode())) {
						// 修改因为:按条件查到purchaselistd中有两条符合条件的临时物料 取tcode出了问题
						if (purchaseSumm.getGoodsNm().equals(profitSumm.getGoodsNm())) {
							profitSumm.setpPrice(purchaseSumm.getPrice());// 成本价
							profitSumm.setpMoney(purchaseSumm.getPrice() * profitSumm.getOrderNum());// 成本
							profitSumm.setPurchaseListDId(purchaseSumm.getPurchaselistDId());// 采购订单物料Id
							totalPMoney += profitSumm.getpMoney();
							Double profitMoney = profitSumm.getdMoney() - profitSumm.getpMoney();
							profitSumm.setProfitMoney(profitMoney);
							totalProMoney += profitMoney;
							Double rate = profitMoney / profitSumm.getdMoney();
							profitSumm.setRate(String.valueOf(NumUtil.trim0(rate * 100)));
						}
					} else {
						// 添加常规物料的采购信息
						if (purchaseSumm.getGoodsId().equals(profitSumm.getGoodsId().toString())
								&& profitSumm.getGoodsId() != -1) {
							profitSumm.setpPrice(purchaseSumm.getPrice());// 成本价
							profitSumm.setpMoney(purchaseSumm.getPrice() * profitSumm.getOrderNum());// 成本
							profitSumm.setPurchaseListDId(purchaseSumm.getPurchaselistDId());// 采购订单物料Id
							totalPMoney += profitSumm.getpMoney();
							Double profitMoney = profitSumm.getdMoney() - profitSumm.getpMoney();
							profitSumm.setProfitMoney(profitMoney);
							totalProMoney += profitMoney;
							Double rate = profitMoney / profitSumm.getdMoney();
							profitSumm.setRate(String.valueOf(NumUtil.trim0(rate * 100)));
						}
					}
				}
			}
			ProfitSumm profitSumm = new ProfitSumm();
			profitSumm.setGoodsNm("合计");
			profitSumm.setdMoney(totaldMoney);
			profitSumm.setpMoney(totalPMoney);
			profitSumm.setProfitMoney(totalProMoney);
			if (totaldMoney > 0) {
				totalRate = totalProMoney / totaldMoney;
				profitSumm.setRate(String.valueOf(NumUtil.trim0(totalRate * 100)));
			} else {
				profitSumm.setRate("0.0");
			}
			// 总计集合
			ArrayList<Object> totalList = new ArrayList<>();
			totalList.add(profitSumm);
			Map<String, Object> data = new HashMap<>();
			data.put("list", profitList);
			data.put("totalList", totalList);
			data.put("profitSumm", profitSumm);
			result.setData(data);

		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据物料查询毛利汇总
	 */
	public Result getProfitSummDListByGoods(String goodsId, String goodsType, String stime, String etime, int structId,
			String demandListDId, String purchaseListDId, String customerIds) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsId", goodsId);
		param.put("goodsType", goodsType);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("demandListDId", demandListDId);
		param.put("purchaselistDId", purchaseListDId);
		param.put("customerIdsStr", customerIds);
		try {
			// 创建单品毛利信息集合
			List<ProfitSumm> profitList = new ArrayList<>();
			Double totalProMoney = 0.00;// 毛利总计
			Double totalRate = 0.00;// 毛利率总计
			// 获取订单(销售)汇总信息
			DemandSummaryD demandSummaryDforGoodsNm = demandSummMapper.getGoodsNmByDemandListDId(param);
			String goodsNm = demandSummaryDforGoodsNm.getGoodsNm();
			param.put("goodsNm", goodsNm);
			List<DemandGoods> demandSummList = demandSummMapper.getDemandSummByGoodsForProfit(param);
			Integer orderIndex = 1;
			Double totaldNum = 0.00;
			Double totaldMoney = 0.00;// 销售合计
			for (int i = 0; i < demandSummList.size(); i++) {
				DemandGoods demandGoods = demandSummList.get(i);
				ProfitSumm profitSumm = new ProfitSumm();
				profitSumm.setGoodsId(demandGoods.getGoodsId());// 物料Id
				profitSumm.setGoodsNm(demandGoods.getGoodsNm());// 物料名称
				profitSumm.setBillDate(demandGoods.getBillDate().substring(0, 10));// 时间
				profitSumm.setGoodsType(demandGoods.getGoodsType());// 类型
				profitSumm.setOrderNum(demandGoods.getDemandNum());// 数量
				totaldNum = totaldNum + demandGoods.getDemandNum();// 获取总数量
				profitSumm.setdPirce(demandGoods.getPrice());// 销售价
				profitSumm.setdMoney(demandGoods.getMoney());// 销售收入
				totaldMoney += demandGoods.getMoney();
				profitSumm.setTcode(demandGoods.getTcode());// 标注临时物料
				profitSumm.setUnitNm(demandGoods.getUnitNm());// 单位
				profitSumm.setPurchaseListHId(demandGoods.getPurchaseListHId());
				profitSumm.setOrderIndex(orderIndex);
				orderIndex++;
				profitList.add(profitSumm);
			}
			// 获取采购汇总信息
			PurchaseSumm goodsNmByPurchaseListD = purchaseSummMapper.getGoodsNmByPurchaseListD(param);
			String goodsNm2 = goodsNmByPurchaseListD.getGoodsNm();
			param.put("goodsNm", goodsNm2);
			List<PurchaseSumm> PurchaseSummList = purchaseSummMapper.getPurchaseSummDListForProfit(param);
			Double totalPMoney = 0.00;// 成本合计
			for (int i = 0; i < PurchaseSummList.size(); i++) {
				PurchaseSumm purchaseSumm = PurchaseSummList.get(i);
				for (int j = 0; j < profitList.size(); j++) {
					ProfitSumm profitSumm = profitList.get(j);
					if (!StringUtils.isEmpty(purchaseSumm.getTcode())) {
						if (purchaseSumm.getTcode().equals(profitSumm.getTcode())) {
							profitSumm.setpPrice(purchaseSumm.getPrice());// 成本价
							profitSumm.setpMoney(purchaseSumm.getPrice() * profitSumm.getOrderNum());// 成本
							totalPMoney += purchaseSumm.getTotalMn();
							Double profitMoney = profitSumm.getdMoney() - profitSumm.getpMoney();
							profitSumm.setProfitMoney(profitMoney);
							totalProMoney += profitMoney;
							Double rate = profitMoney / profitSumm.getdMoney();
							profitSumm.setRate(String.valueOf(NumUtil.trim0(rate * 100)) + "%");
						}
					} else {
						// 添加常规物料的采购信息
						if (purchaseSumm.getPurchaseListHId().equals(profitSumm.getPurchaseListHId())) {
							profitSumm.setpPrice(purchaseSumm.getPrice());// 成本价
							profitSumm.setpMoney(purchaseSumm.getTotalMn());// 成本
							totalPMoney += purchaseSumm.getTotalMn();
							Double profitMoney = profitSumm.getdMoney() - profitSumm.getpMoney();
							profitSumm.setProfitMoney(profitMoney);
							totalProMoney += profitMoney;
							Double rate = profitMoney / profitSumm.getdMoney();
							profitSumm.setRate(String.valueOf(NumUtil.trim0(rate * 100)) + "%");
						}
					}
				}
			}
			ProfitSumm profitSumm = new ProfitSumm();
			profitSumm.setGoodsNm("合计");
			profitSumm.setOrderNum(totaldNum);
			profitSumm.setdMoney(totaldMoney);
			profitSumm.setpMoney(totalPMoney);
			profitSumm.setProfitMoney(totalProMoney);
			totalRate = totalProMoney / totaldMoney;
			profitSumm.setRate(String.valueOf(NumUtil.trim0(totalRate * 100)) + "%");
			profitList.add(profitSumm);
			result.setData(profitList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/***
	 * 为数量统计图表查询当月每日此物料的数量
	 */
	@Override
	public Result getOrderNum(String goodsId, String goodsType, String stime, String etime, int structId,
			String demandListDId, String purchaseListDId, String customerIds) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsId", goodsId);
		param.put("goodsType", goodsType);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("demandListDId", demandListDId);
		param.put("purchaselistDId", purchaseListDId);
		param.put("customerIdsStr", customerIds);
		try {
			// 创建集合存储单品每月数量
			List<Double> orderNumList = new ArrayList<>();

			// 获取订单(销售)汇总信息
			DemandSummaryD demandSummaryDforGoodsNm = demandSummMapper.getGoodsNmByDemandListDId(param);
			String goodsNm = demandSummaryDforGoodsNm.getGoodsNm();
			param.put("goodsNm", goodsNm);
			List<DemandGoods> demandSummList = demandSummMapper.getDemandNumInDay(param);
			// 日期数组
			String[] day = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
					"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
			// 记录添加位置
			int t = 0;
			for (int j = 0; j < day.length; j++) {
				for (int i = 0; i < demandSummList.size(); i++) {
					DemandGoods demandGoods = demandSummList.get(i);
					String cday = demandGoods.getBillDate().substring(8, 10);
					if (day[j].equals(cday)) {
						orderNumList.add(demandGoods.getDemandNum());
						t++;
					}
				}
				if (t != 0) {
					t = 0;
				} else {
					orderNumList.add(0.00);
				}
			}
			result.setData(orderNumList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/**
	 * 为统计图表查询当月每日此物料的售价
	 */
	@Override
	public Result getDPrice(String goodsId, String goodsType, String stime, String etime, int structId,
			String demandListDId, String purchaseListDId, String customerIds) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsId", goodsId);
		param.put("goodsType", goodsType);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("demandListDId", demandListDId);
		param.put("purchaselistDId", purchaseListDId);
		param.put("customerIdsStr", customerIds);
		try {
			// 创建集合存储单品每月售价
			List<Double> dPriceList = new ArrayList<>();

			// 获取订单(销售)汇总信息
			DemandSummaryD demandSummaryDforGoodsNm = demandSummMapper.getGoodsNmByDemandListDId(param);
			String goodsNm = demandSummaryDforGoodsNm.getGoodsNm();
			param.put("goodsNm", goodsNm);
			List<DemandGoods> demandSummList = demandSummMapper.getDemandNumInDay(param);
			// 日期数组
			String[] day = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
					"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
			// 记录添加位置
			int t = 0;
			for (int j = 0; j < day.length; j++) {
				for (int i = 0; i < demandSummList.size(); i++) {
					DemandGoods demandGoods = demandSummList.get(i);
					String cday = demandGoods.getBillDate().substring(8, 10);
					if (day[j].equals(cday)) {
						dPriceList.add(demandGoods.getPrice());
						t++;
					}
				}
				if (t != 0) {
					t = 0;
				} else {
					dPriceList.add(0.00);
				}
			}
			result.setData(dPriceList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/**
	 * 为统计图表查询当月每日此物料的进价
	 */
	@Override
	public Result getPPrice(String goodsId, String goodsType, String stime, String etime, int structId,
			String demandListDId, String purchaseListDId, String customerIds) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsId", goodsId);
		param.put("goodsType", goodsType);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("demandListDId", demandListDId);
		param.put("purchaselistDId", purchaseListDId);
		param.put("customerIdsStr", customerIds);
		try {
			// 创建集合存储单品每月进价
			List<Double> pPriceList = new ArrayList<>();
			// 获取订单(销售)汇总信息
			PurchaseSumm purchaseListD = purchaseSummMapper.getGoodsNmByPurchaseListD(param);
			String goodsNm = purchaseListD.getGoodsNm();
			param.put("goodsNm", goodsNm);
			List<PurchaseSumm> pPriceInDay = purchaseSummMapper.getPPriceInDay(param);
			// 日期数组
			String[] day = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
					"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
			// 记录添加位置
			int t = 0;
			for (int j = 0; j < day.length; j++) {
				for (int i = 0; i < pPriceInDay.size(); i++) {
					PurchaseSumm purchaseSumm = pPriceInDay.get(i);
					String cday = purchaseSumm.getBillDate().substring(8, 10);
					if (day[j].equals(cday)) {
						pPriceList.add(purchaseSumm.getPrice());
						t++;
					}
				}
				if (t != 0) {
					t = 0;
				} else {
					pPriceList.add(0.00);
				}
			}
			result.setData(pPriceList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/**
	 * 为汇总图表获取每月毛利率
	 */
	@Override
	public Result getRateInDay(String goodsId, String goodsType, String stime, String etime, int structId,
			String demandListDId, String purchaseListDId, String customerIds) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsId", goodsId);
		param.put("goodsType", goodsType);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("demandListDId", demandListDId);
		param.put("purchaselistDId", purchaseListDId);
		param.put("customerIdsStr", customerIds);
		try {
			// 创建集合存储单品每月毛利率
			List<String> rateList = new ArrayList<>();
			// 创建单品毛利信息集合
			List<ProfitSumm> profitList = new ArrayList<>();
			// 获取订单(销售)汇总信息
			DemandSummaryD demandSummaryDforGoodsNm = demandSummMapper.getGoodsNmByDemandListDId(param);
			String goodsNm = demandSummaryDforGoodsNm.getGoodsNm();
			param.put("goodsNm", goodsNm);
			List<DemandGoods> demandSummList = demandSummMapper.getDemandNumInDay(param);
			for (int i = 0; i < demandSummList.size(); i++) {
				DemandGoods demandGoods = demandSummList.get(i);
				ProfitSumm profitSumm = new ProfitSumm();
				profitSumm.setBillDate(demandGoods.getBillDate());// 日期
				profitSumm.setGoodsId(demandGoods.getGoodsId());// 物料名称
				profitSumm.setTcode(demandGoods.getTcode());// 标注临时物料
				profitSumm.setdMoney(demandGoods.getMoney());// 销售收入
				profitList.add(profitSumm);
			}
			// 获取订单(销售)汇总信息
			PurchaseSumm purchaseListD = purchaseSummMapper.getGoodsNmByPurchaseListD(param);
			String goodsNm2 = purchaseListD.getGoodsNm();
			param.put("goodsNm", goodsNm2);
			List<PurchaseSumm> pPriceInDay = purchaseSummMapper.getPPriceInDay(param);
			for (int i = 0; i < pPriceInDay.size(); i++) {
				PurchaseSumm purchaseSumm = pPriceInDay.get(i);
				for (int j = 0; j < profitList.size(); j++) {
					ProfitSumm profitSumm = profitList.get(j);
					if (!StringUtils.isEmpty(profitSumm.getTcode())) {
						if (purchaseSumm.getBillDate().equals(profitSumm.getBillDate())) {

							Double totalMn = purchaseSumm.getTotalMn();// 成本
							Double dMoney = profitSumm.getdMoney();// 销售收入
							Double profit = dMoney - totalMn;// 毛利
							Double rate = NumUtil.trim0(profit / dMoney * 100);// 毛利率
							profitSumm.setRate(String.valueOf(rate));
						}
					} else {
						if (purchaseSumm.getBillDate().equals(profitSumm.getBillDate())) {
							Double totalMn = purchaseSumm.getTotalMn();// 成本
							Double dMoney = profitSumm.getdMoney();// 销售收入
							Double profit = dMoney - totalMn;// 毛利
							Double rate = NumUtil.trim0(profit / dMoney);// 毛利率
							profitSumm.setRate(String.valueOf(rate));
						}
					}
				}
			}
			// 日期数组
			String[] day = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
					"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
			// 记录添加位置
			int t = 0;
			for (int j = 0; j < day.length; j++) {
				for (int i = 0; i < profitList.size(); i++) {
					ProfitSumm profitSumm = profitList.get(i);
					String cday = profitSumm.getBillDate().substring(8, 10);
					if (day[j].equals(cday)) {
						if (StringUtils.isEmpty(profitSumm.getRate())) {
							rateList.add("0.00");
						} else {
							rateList.add(profitSumm.getRate());
						}
						t++;
					}
				}
				if (t != 0) {
					t = 0;
				} else {
					rateList.add("0.00");
				}
			}
			result.setData(rateList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/**
	 * 为统计图表获取当年每月单物料的数量
	 */
	@Override
	public Result getOrderNumInMonth(String goodsId, String goodsType, String stime, String etime, int structId,
			String demandListDId, String purchaseListDId, String customerIds) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsId", goodsId);
		param.put("goodsType", goodsType);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("demandListDId", demandListDId);
		param.put("purchaselistDId", purchaseListDId);
		param.put("customerIdsStr", customerIds);
		try {
			// 创建集合存储单品每月数量
			List<Double> orderNumList = new ArrayList<>();

			// 获取订单(销售)汇总信息
			DemandSummaryD demandSummaryDforGoodsNm = demandSummMapper.getGoodsNmByDemandListDId(param);
			String goodsNm = demandSummaryDforGoodsNm.getGoodsNm();
			param.put("goodsNm", goodsNm);
			List<DemandGoods> demandSummList = demandSummMapper.getDemandNumInMonth(param);
			String[] month = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
			// 记录添加位置
			int t = 0;
			for (int j = 0; j < month.length; j++) {
				for (int i = 0; i < demandSummList.size(); i++) {
					DemandGoods demandGoods = demandSummList.get(i);
					String cMonth = demandGoods.getBillDate().substring(5, 7);
					if (cMonth.equals(month[j])) {
						orderNumList.add(demandGoods.getDemandNum());
						t++;
					}
				}
				if (t != 0) {
					t = 0;
				} else {
					orderNumList.add(0.00);
				}
			}
			result.setData(orderNumList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/**
	 * 为汇总图表获取当年每月此物料的毛利
	 */
	@Override
	public Result getPPriceInMonth(String goodsId, String goodsType, String stime, String etime, int structId,
			String demandListDId, String purchaseListDId, String customerIds) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsId", goodsId);
		param.put("goodsType", goodsType);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("demandListDId", demandListDId);
		param.put("purchaselistDId", purchaseListDId);
		param.put("customerIdsStr", customerIds);
		try {
			// 创建集合存储单品每月进价
			List<Double> pPriceList = new ArrayList<>();
			// 获取订单(销售)汇总信息
			PurchaseSumm purchaseListD = purchaseSummMapper.getGoodsNmByPurchaseListD(param);
			String goodsNm = purchaseListD.getGoodsNm();
			param.put("goodsNm", goodsNm);
			List<PurchaseSumm> pPriceInMonth = purchaseSummMapper.getPPriceInMonth(param);
			// 日期数组
			String[] month = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
			// 记录添加位置
			int t = 0;
			for (int j = 0; j < month.length; j++) {
				for (int i = 0; i < pPriceInMonth.size(); i++) {
					PurchaseSumm purchaseSumm = pPriceInMonth.get(i);
					String cMonth = purchaseSumm.getBillDate().substring(5, 7);
					if (month[j].equals(cMonth)) {
						pPriceList.add(purchaseSumm.getPrice());
						t++;
					}
				}
				if (t != 0) {
					t = 0;
				} else {
					pPriceList.add(0.00);
				}
			}
			result.setData(pPriceList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/**
	 * 为统计图表获取当年每月此物料的售价
	 */
	@Override
	public Result getDPriceInMonth(String goodsId, String goodsType, String stime, String etime, int structId,
			String demandListDId, String purchaseListDId, String customerIds) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsId", goodsId);
		param.put("goodsType", goodsType);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("demandListDId", demandListDId);
		param.put("purchaselistDId", purchaseListDId);
		param.put("customerIdsStr", customerIds);
		try {
			// 创建集合存储单品每月售价
			List<Double> dPriceList = new ArrayList<>();

			// 获取订单(销售)汇总信息
			DemandSummaryD demandSummaryDforGoodsNm = demandSummMapper.getGoodsNmByDemandListDId(param);
			String goodsNm = demandSummaryDforGoodsNm.getGoodsNm();
			param.put("goodsNm", goodsNm);
			List<DemandGoods> demandSummList = demandSummMapper.getDemandNumInMonth(param);
			// 日期数组
			String[] month = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
			// 记录添加位置
			int t = 0;
			for (int j = 0; j < month.length; j++) {
				for (int i = 0; i < demandSummList.size(); i++) {
					DemandGoods demandGoods = demandSummList.get(i);
					String cMonth = demandGoods.getBillDate().substring(5, 7);
					if (month[j].equals(cMonth)) {
						dPriceList.add(demandGoods.getPrice());
						t++;
					}
				}
				if (t != 0) {
					t = 0;
				} else {
					dPriceList.add(0.00);
				}
			}
			result.setData(dPriceList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/**
	 * 为汇总图表获取当年每月的毛利率
	 */
	@Override
	public Result getRateInMonth(String goodsId, String goodsType, String stime, String etime, int structId,
			String demandListDId, String purchaseListDId, String customerIds) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsId", goodsId);
		param.put("goodsType", goodsType);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("demandListDId", demandListDId);
		param.put("purchaselistDId", purchaseListDId);
		param.put("customerIdsStr", customerIds);
		try {
			// 创建集合存储单品每月毛利率
			List<String> rateList = new ArrayList<>();
			// 创建单品毛利信息集合
			List<ProfitSumm> profitList = new ArrayList<>();
			// 获取订单(销售)汇总信息
			DemandSummaryD demandSummaryDforGoodsNm = demandSummMapper.getGoodsNmByDemandListDId(param);
			String goodsNm = demandSummaryDforGoodsNm.getGoodsNm();
			param.put("goodsNm", goodsNm);
			List<DemandGoods> demandSummList = demandSummMapper.getDemandNumInMonth(param);
			for (int i = 0; i < demandSummList.size(); i++) {
				DemandGoods demandGoods = demandSummList.get(i);
				ProfitSumm profitSumm = new ProfitSumm();
				profitSumm.setBillDate(demandGoods.getBillDate());// 日期
				profitSumm.setGoodsId(demandGoods.getGoodsId());// 物料名称
				profitSumm.setTcode(demandGoods.getTcode());// 标注临时物料
				profitSumm.setdMoney(demandGoods.getMoney());// 销售收入
				profitList.add(profitSumm);
			}
			// 获取订单(销售)汇总信息
			PurchaseSumm purchaseListD = purchaseSummMapper.getGoodsNmByPurchaseListD(param);
			String goodsNm2 = purchaseListD.getGoodsNm();
			param.put("goodsNm", goodsNm2);
			List<PurchaseSumm> pPriceInMonth = purchaseSummMapper.getPPriceInMonth(param);
			for (int i = 0; i < pPriceInMonth.size(); i++) {
				PurchaseSumm purchaseSumm = pPriceInMonth.get(i);
				for (int j = 0; j < profitList.size(); j++) {
					ProfitSumm profitSumm = profitList.get(j);
					if (!StringUtils.isEmpty(purchaseSumm.getTcode())) {
						if (purchaseSumm.getBillDate().equals(profitSumm.getBillDate())) {

							Double totalMn = purchaseSumm.getTotalMn();// 成本
							Double dMoney = profitSumm.getdMoney();// 销售收入
							Double profit = dMoney - totalMn;// 毛利
							Double rate = NumUtil.trim0(profit / dMoney);// 毛利率
							profitSumm.setRate(String.valueOf(rate));
						}
					} else {
						if (purchaseSumm.getBillDate().equals(profitSumm.getBillDate())) {
							Double totalMn = purchaseSumm.getTotalMn();// 成本
							Double dMoney = profitSumm.getdMoney();// 销售收入
							Double profit = dMoney - totalMn;// 毛利
							Double rate = NumUtil.trim0(profit / dMoney * 100);// 毛利率
							profitSumm.setRate(String.valueOf(rate));
						}
					}
				}
			}
			// 日期数组
			String[] month = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
			// 记录添加位置
			int t = 0;
			for (int j = 0; j < month.length; j++) {
				for (int i = 0; i < profitList.size(); i++) {
					ProfitSumm profitSumm = profitList.get(i);
					String cMonth = profitSumm.getBillDate().substring(5, 7);
					if (month[j].equals(cMonth)) {
						rateList.add(profitSumm.getRate());
						t++;
					}
				}
				if (t != 0) {
					t = 0;
				} else {
					rateList.add("0.00");
				}
			}
			result.setData(rateList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/**
	 * 分类占比 收入占比
	 */
	@Override
	public Result getSelectBigKindForIncome(String goodsNm, String stime, String etime, int structId,
			String customerIds) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsNm", goodsNm);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("customerIdsStr", customerIds);
		try {
			// 保存收入占比
			ArrayList<Map> incomelist = new ArrayList<>();
			// 分类
			String[] kindNmStr = { "蔬菜类", "水果类", "猪肉鲜品", "牛羊肉鲜品", "禽肉鲜品", "水产鲜品", "肉类冻品", "调料类", "蛋奶类", "热食类", "物耗",
					"小商品" };
			param.put("code", "0101");
			List<DemandGoods> kindForIncome1 = demandSummMapper.getBigKindForIncome(param);
			double money1 = 0.00;
			double money11 = 0.00;
			if (kindForIncome1.get(0) != null) {
				money1 = kindForIncome1.get(0).getMoney();
			}
			if (kindForIncome1.get(1) != null) {
				money11 = kindForIncome1.get(1).getMoney();
			}
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("value", money1 + money11);
			map1.put("name", "蔬菜类");
			incomelist.add(map1);

			param.put("code", "0102");
			List<DemandGoods> kindForIncome2 = demandSummMapper.getBigKindForIncome(param);
			double money2 = 0.00;
			double money21 = 0.00;
			if (kindForIncome2.get(0) != null) {
				money2 = kindForIncome2.get(0).getMoney();
			}
			if (kindForIncome2.get(1) != null) {
				money21 = kindForIncome2.get(1).getMoney();
			}
			HashMap<String, Object> map2 = new HashMap<>();
			map2.put("value", money2 + money21);
			map2.put("name", "水果类");
			incomelist.add(map2);

			param.put("code", "010301");
			List<DemandGoods> kindForIncome3 = demandSummMapper.getBigKindForIncome(param);
			double money3 = 0.00;
			double money31 = 0.00;
			if (kindForIncome3.get(0) != null) {
				money3 = kindForIncome3.get(0).getMoney();
			}
			if (kindForIncome3.get(1) != null) {
				money31 = kindForIncome3.get(1).getMoney();
			}
			HashMap<Object, Object> map3 = new HashMap<>();
			map3.put("value", money3 + money31);
			map3.put("name", "猪肉鲜品");
			incomelist.add(map3);

			param.put("code", "010302");
			List<DemandGoods> kindForIncome4 = demandSummMapper.getBigKindForIncome(param);
			double money4 = 0.00;
			double money41 = 0.00;
			if (kindForIncome4.get(0) != null) {
				money4 = kindForIncome4.get(0).getMoney();
			}
			if (kindForIncome4.get(1) != null) {
				money41 = kindForIncome4.get(1).getMoney();
			}
			param.put("code", "010303");
			List<DemandGoods> kindForIncome41 = demandSummMapper.getBigKindForIncome(param);
			double money42 = 0.00;
			double money43 = 0.00;
			if (kindForIncome41.get(0) != null) {
				money42 = kindForIncome41.get(0).getMoney();
			}
			if (kindForIncome41.get(1) != null) {
				money43 = kindForIncome41.get(1).getMoney();
			}
			HashMap<String, Object> map4 = new HashMap<>();
			map4.put("value", money4 + money41 + money42 + money43);
			map4.put("name", "牛羊肉鲜品");
			incomelist.add(map4);

			param.put("code", "010304");
			List<DemandGoods> kindForIncome5 = demandSummMapper.getBigKindForIncome(param);
			double money5 = 0.00;
			double money51 = 0.00;
			if (kindForIncome5.get(0) != null) {
				money5 = kindForIncome5.get(0).getMoney();
			}
			if (kindForIncome5.get(1) != null) {
				money51 = kindForIncome5.get(1).getMoney();
			}
			HashMap<String, Object> map5 = new HashMap<>();
			map5.put("value", money5 + money51);
			map5.put("name", "禽肉鲜品");
			incomelist.add(map5);

			param.put("code", "010305");
			List<DemandGoods> kindForIncome6 = demandSummMapper.getBigKindForIncome(param);
			double money6 = 0.00;
			double money61 = 0.00;
			if (kindForIncome6.get(0) != null) {
				money6 = kindForIncome6.get(0).getMoney();
			}
			if (kindForIncome6.get(1) != null) {
				money61 = kindForIncome6.get(1).getMoney();
			}
			HashMap<String, Object> map6 = new HashMap<>();
			map6.put("value", money6 + money61);
			map6.put("name", "水产鲜品");
			incomelist.add(map6);
			param.put("code", "0106");
			List<DemandGoods> kindForIncome7 = demandSummMapper.getBigKindForIncome(param);
			double money7 = 0.00;
			double money71 = 0.00;
			if (kindForIncome7.get(0) != null) {
				money7 = kindForIncome7.get(0).getMoney();
			}
			if (kindForIncome7.get(1) != null) {
				money71 = kindForIncome7.get(1).getMoney();
			}
			HashMap<String, Object> map7 = new HashMap<>();
			map7.put("value", money7 + money71);
			map7.put("name", "肉类冻品");
			incomelist.add(map7);

			param.put("code", "0105");
			List<DemandGoods> kindForIncome8 = demandSummMapper.getBigKindForIncome(param);
			double money8 = 0.00;
			double money81 = 0.00;
			if (kindForIncome8.get(0) != null) {
				money8 = kindForIncome8.get(0).getMoney();
			}
			if (kindForIncome8.get(1) != null) {
				money81 = kindForIncome8.get(1).getMoney();
			}
			param.put("code", "0108");
			List<DemandGoods> kindForIncome81 = demandSummMapper.getBigKindForIncome(param);
			double money82 = 0.00;
			double money83 = 0.00;
			if (kindForIncome81.get(0) != null) {
				money82 = kindForIncome81.get(0).getMoney();
			}
			if (kindForIncome81.get(1) != null) {
				money83 = kindForIncome81.get(1).getMoney();
			}
			HashMap<String, Object> map8 = new HashMap<>();
			map8.put("value", money8 + money81 + money82 + money83);
			map8.put("name", "调料类");
			incomelist.add(map8);

			param.put("code", "0109");
			List<DemandGoods> kindForIncome9 = demandSummMapper.getBigKindForIncome(param);
			double money9 = 0.00;
			double money91 = 0.00;
			if (kindForIncome9.get(0) != null) {
				money9 = kindForIncome9.get(0).getMoney();
			}
			if (kindForIncome9.get(1) != null) {
				money91 = kindForIncome9.get(1).getMoney();
			}
			param.put("code", "0110");
			List<DemandGoods> kindForIncome91 = demandSummMapper.getBigKindForIncome(param);
			double money92 = 0.00;
			double money93 = 0.00;
			if (kindForIncome91.get(0) != null) {
				money92 = kindForIncome91.get(0).getMoney();
			}
			if (kindForIncome91.get(1) != null) {
				money93 = kindForIncome91.get(1).getMoney();
			}
			HashMap<String, Object> map9 = new HashMap<>();
			map9.put("value", money9 + money91 + money92 + money93);
			map9.put("name", "蛋奶类");
			incomelist.add(map9);

			param.put("code", "0111");
			List<DemandGoods> kindForIncome10 = demandSummMapper.getBigKindForIncome(param);
			double money10 = 0.00;
			double money101 = 0.00;
			if (kindForIncome10.get(0) != null) {
				money10 = kindForIncome10.get(0).getMoney();
			}
			if (kindForIncome10.get(1) != null) {
				money101 = kindForIncome10.get(1).getMoney();
			}
			HashMap<String, Object> map10 = new HashMap<>();
			map10.put("value", money10 + money101);
			map10.put("name", "热食类");
			incomelist.add(map10);

			param.put("code", "02");
			List<DemandGoods> kindForIncome11 = demandSummMapper.getBigKindForIncome(param);
			double money111 = 0.00;
			double money112 = 0.00;
			if (kindForIncome11.get(0) != null) {
				money111 = kindForIncome11.get(0).getMoney();
			}
			if (kindForIncome11.get(1) != null) {
				money112 = kindForIncome11.get(1).getMoney();
			}
			HashMap<String, Object> map11 = new HashMap<>();
			map11.put("value", money111 + money112);
			map11.put("name", "物耗");
			incomelist.add(map11);

			param.put("code", "03");
			List<DemandGoods> kindForIncome12 = demandSummMapper.getBigKindForIncome(param);
			double money121 = 0.00;
			double money122 = 0.00;
			if (kindForIncome12.get(0) != null) {
				money121 = kindForIncome12.get(0).getMoney();
			}
			if (kindForIncome12.get(1) != null) {
				money122 = kindForIncome12.get(1).getMoney();
			}
			HashMap<String, Object> map12 = new HashMap<>();
			map12.put("value", money121 + money122);
			map12.put("name", "小商品");
			incomelist.add(map12);

			result.setData(incomelist);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/**
	 * 分类占比 查询成本
	 */
	@Override
	public Result getSelectBigKindForCost(String goodsNm, String stime, String etime, int structId,
			String customerIds) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsNm", goodsNm);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("customerIdsStr", customerIds);
		try {
			// 保存收入占比
			ArrayList<Map> incomelist = new ArrayList<>();
			// 分类
			String[] kindNmStr = { "蔬菜类", "水果类", "猪肉鲜品", "牛羊肉鲜品", "禽肉鲜品", "水产鲜品", "肉类冻品", "调料类", "蛋奶类", "热食类", "物耗",
					"小商品" };
			param.put("code", "0101");
			List<PurchaseSumm> KindForCost1 = purchaseSummMapper.getBigKindForCost(param);
			double money1 = 0.00;
			double money11 = 0.00;
			if (KindForCost1.get(0) != null) {
				money1 = KindForCost1.get(0).getTotalMn();
			}
			if (KindForCost1.get(1) != null) {
				money11 = KindForCost1.get(1).getTotalMn();
			}
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("value", money1 + money11);
			map1.put("name", "蔬菜类");
			incomelist.add(map1);

			param.put("code", "0102");
			List<PurchaseSumm> kindForCost2 = purchaseSummMapper.getBigKindForCost(param);
			double money2 = 0.00;
			double money21 = 0.00;
			if (kindForCost2.get(0) != null) {
				money2 = kindForCost2.get(0).getTotalMn();
			}
			if (kindForCost2.get(1) != null) {
				money21 = kindForCost2.get(1).getTotalMn();
			}
			HashMap<String, Object> map2 = new HashMap<>();
			map2.put("value", money2 + money21);
			map2.put("name", "水果类");
			incomelist.add(map2);

			param.put("code", "010301");
			List<PurchaseSumm> kindForCost3 = purchaseSummMapper.getBigKindForCost(param);
			double money3 = 0.00;
			double money31 = 0.00;
			if (kindForCost3.get(0) != null) {
				money3 = kindForCost3.get(0).getTotalMn();
			}
			if (kindForCost3.get(1) != null) {
				money31 = kindForCost3.get(1).getTotalMn();
			}
			HashMap<Object, Object> map3 = new HashMap<>();
			map3.put("value", money3 + money31);
			map3.put("name", "猪肉鲜品");
			incomelist.add(map3);

			param.put("code", "010302");
			List<PurchaseSumm> kindForCost4 = purchaseSummMapper.getBigKindForCost(param);
			double money4 = 0.00;
			double money41 = 0.00;
			if (kindForCost4.get(0) != null) {
				money4 = kindForCost4.get(0).getTotalMn();
			}
			if (kindForCost4.get(1) != null) {
				money41 = kindForCost4.get(1).getTotalMn();
			}
			param.put("code", "010303");
			List<PurchaseSumm> kindForCost41 = purchaseSummMapper.getBigKindForCost(param);
			double money42 = 0.00;
			double money43 = 0.00;
			if (kindForCost41.get(0) != null) {
				money42 = kindForCost41.get(0).getTotalMn();
			}
			if (kindForCost41.get(1) != null) {
				money43 = kindForCost41.get(1).getTotalMn();
			}
			HashMap<String, Object> map4 = new HashMap<>();
			map4.put("value", money4 + money41 + money42 + money43);
			map4.put("name", "牛羊肉鲜品");
			incomelist.add(map4);

			param.put("code", "010304");
			List<PurchaseSumm> kindForCost5 = purchaseSummMapper.getBigKindForCost(param);
			double money5 = 0.00;
			double money51 = 0.00;
			if (kindForCost5.get(0) != null) {
				money5 = kindForCost5.get(0).getTotalMn();
			}
			if (kindForCost5.get(1) != null) {
				money51 = kindForCost5.get(1).getTotalMn();
			}
			HashMap<String, Object> map5 = new HashMap<>();
			map5.put("value", money5 + money51);
			map5.put("name", "禽肉鲜品");
			incomelist.add(map5);

			param.put("code", "010305");
			List<PurchaseSumm> kindForCost6 = purchaseSummMapper.getBigKindForCost(param);
			double money6 = 0.00;
			double money61 = 0.00;
			if (kindForCost6.get(0) != null) {
				money6 = kindForCost6.get(0).getTotalMn();
			}
			if (kindForCost6.get(1) != null) {
				money61 = kindForCost6.get(1).getTotalMn();
			}
			HashMap<String, Object> map6 = new HashMap<>();
			map6.put("value", money6 + money61);
			map6.put("name", "水产鲜品");
			incomelist.add(map6);
			param.put("code", "0106");
			List<PurchaseSumm> kindForCost7 = purchaseSummMapper.getBigKindForCost(param);
			double money7 = 0.00;
			double money71 = 0.00;
			if (kindForCost7.get(0) != null) {
				money7 = kindForCost7.get(0).getTotalMn();
			}
			if (kindForCost7.get(1) != null) {
				money71 = kindForCost7.get(1).getTotalMn();
			}
			HashMap<String, Object> map7 = new HashMap<>();
			map7.put("value", money7 + money71);
			map7.put("name", "肉类冻品");
			incomelist.add(map7);

			param.put("code", "0105");
			List<PurchaseSumm> kindForCost8 = purchaseSummMapper.getBigKindForCost(param);
			double money8 = 0.00;
			double money81 = 0.00;
			if (kindForCost8.get(0) != null) {
				money8 = kindForCost8.get(0).getTotalMn();
			}
			if (kindForCost8.get(1) != null) {
				money81 = kindForCost8.get(1).getTotalMn();
			}
			param.put("code", "0108");
			List<PurchaseSumm> kindForCost81 = purchaseSummMapper.getBigKindForCost(param);
			double money82 = 0.00;
			double money83 = 0.00;
			if (kindForCost81.get(0) != null) {
				money82 = kindForCost81.get(0).getTotalMn();
			}
			if (kindForCost81.get(1) != null) {
				money83 = kindForCost81.get(1).getTotalMn();
			}
			HashMap<String, Object> map8 = new HashMap<>();
			map8.put("value", money8 + money81 + money82 + money83);
			map8.put("name", "调料类");
			incomelist.add(map8);

			param.put("code", "0109");
			List<PurchaseSumm> kindForCost9 = purchaseSummMapper.getBigKindForCost(param);
			double money9 = 0.00;
			double money91 = 0.00;
			if (kindForCost9.get(0) != null) {
				money9 = kindForCost9.get(0).getTotalMn();
			}
			if (kindForCost9.get(1) != null) {
				money91 = kindForCost9.get(1).getTotalMn();
			}
			param.put("code", "0110");
			List<PurchaseSumm> kindForCost91 = purchaseSummMapper.getBigKindForCost(param);
			double money92 = 0.00;
			double money93 = 0.00;
			if (kindForCost91.get(0) != null) {
				money92 = kindForCost91.get(0).getTotalMn();
			}
			if (kindForCost91.get(1) != null) {
				money93 = kindForCost91.get(1).getTotalMn();
			}
			HashMap<String, Object> map9 = new HashMap<>();
			map9.put("value", money9 + money91 + money92 + money93);
			map9.put("name", "蛋奶类");
			incomelist.add(map9);

			param.put("code", "0111");
			List<PurchaseSumm> kindForCost10 = purchaseSummMapper.getBigKindForCost(param);
			double money10 = 0.00;
			double money101 = 0.00;
			if (kindForCost10.get(0) != null) {
				money10 = kindForCost10.get(0).getTotalMn();
			}
			if (kindForCost10.get(1) != null) {
				money101 = kindForCost10.get(1).getTotalMn();
			}
			HashMap<String, Object> map10 = new HashMap<>();
			map10.put("value", money10 + money101);
			map10.put("name", "热食类");
			incomelist.add(map10);

			param.put("code", "02");
			List<PurchaseSumm> kindForCost11 = purchaseSummMapper.getBigKindForCost(param);
			double money111 = 0.00;
			double money112 = 0.00;
			if (kindForCost11.get(0) != null) {
				money111 = kindForCost11.get(0).getTotalMn();
			}
			if (kindForCost11.get(1) != null) {
				money112 = kindForCost11.get(1).getTotalMn();
			}
			HashMap<String, Object> map11 = new HashMap<>();
			map11.put("value", money111 + money112);
			map11.put("name", "物耗");
			incomelist.add(map11);

			param.put("code", "03");
			List<PurchaseSumm> kindForCost12 = purchaseSummMapper.getBigKindForCost(param);
			double money121 = 0.00;
			double money122 = 0.00;
			if (kindForCost12.get(0) != null) {
				money121 = kindForCost12.get(0).getTotalMn();
			}
			if (kindForCost12.get(1) != null) {
				money122 = kindForCost12.get(1).getTotalMn();
			}
			HashMap<String, Object> map12 = new HashMap<>();
			map12.put("value", money121 + money122);
			map12.put("name", "小商品");
			incomelist.add(map12);

			result.setData(incomelist);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/**
	 * 
	 * 分类占比 毛利率
	 */
	@Override
	public Result getSelectBigKindForProfit(String goodsNm, String stime, String etime, int structId,
			String customerIds) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsNm", goodsNm);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("customerIdsStr", customerIds);
		try {
			// 保存收入占比
			ArrayList<Map> incomelist = new ArrayList<>();
			// 分类
			String[] kindNmStr = { "蔬菜类", "水果类", "猪肉鲜品", "牛羊肉鲜品", "禽肉鲜品", "水产鲜品", "肉类冻品", "调料类", "蛋奶类", "热食类", "物耗",
					"小商品" };
			param.put("code", "0101");
			List<DemandGoods> kindForIncome1 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost1 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney1 = 0.00;
			double pMoney1 = 0.00;
			double dMoney11 = 0.00;
			double pMoney11 = 0.00;
			if (kindForIncome1.get(0) != null) {
				if (kindForCost1.get(0) != null) {
					dMoney1 = kindForIncome1.get(0).getMoney();
					pMoney1 = kindForCost1.get(0).getTotalMn();
				}
			}
			if (kindForIncome1.get(1) != null) {
				if (kindForCost1.get(1) != null) {
					dMoney11 = kindForIncome1.get(1).getMoney();
					pMoney11 = kindForCost1.get(0).getTotalMn();
				}
			}
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("value", Math.round((dMoney1 - pMoney1 + dMoney11 - pMoney11) / (dMoney1 + dMoney11) * 100));
			map1.put("name", "蔬菜类");
			incomelist.add(map1);

			param.put("code", "0102");
			List<DemandGoods> kindForIncome2 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost2 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney2 = 0.00;
			double pMoney2 = 0.00;
			double dMoney21 = 0.00;
			double pMoney21 = 0.00;
			if (kindForIncome2.get(0) != null) {
				if (kindForCost2.get(0) != null) {
					dMoney2 = kindForIncome2.get(0).getMoney();
					pMoney2 = kindForCost2.get(0).getTotalMn();
				}
			}
			if (kindForIncome2.get(1) != null) {
				if (kindForCost2.get(1) != null) {
					dMoney21 = kindForIncome2.get(1).getMoney();
					pMoney21 = kindForCost2.get(1).getTotalMn();
				}
			}
			HashMap<String, Object> map2 = new HashMap<>();
			map2.put("value", Math.round((dMoney2 - pMoney2 + dMoney21 - pMoney21) / (dMoney2 + dMoney21) * 100));
			map2.put("name", "水果类");
			incomelist.add(map2);

			param.put("code", "010301");
			List<DemandGoods> kindForIncome3 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost3 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney3 = 0.00;
			double pMoney3 = 0.00;
			double dMoney31 = 0.00;
			double pMoney31 = 0.00;
			if (kindForIncome3.get(0) != null) {
				if (kindForCost3.get(0) != null) {
					dMoney3 = kindForIncome3.get(0).getMoney();
					pMoney3 = kindForCost3.get(0).getTotalMn();
				}
			}
			if (kindForIncome3.get(1) != null) {
				if (kindForCost3.get(1) != null) {
					dMoney31 = kindForIncome3.get(1).getMoney();
					pMoney31 = kindForCost3.get(1).getTotalMn();
				}
			}
			HashMap<Object, Object> map3 = new HashMap<>();
			map3.put("value", Math.round((dMoney3 + dMoney31 - pMoney3 - pMoney31) / (dMoney3 + dMoney31) * 100));
			map3.put("name", "猪肉鲜品");
			incomelist.add(map3);

			param.put("code", "010302");
			List<DemandGoods> kindForIncome4 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost4 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney4 = 0.00;
			double pMoney4 = 0.00;
			double dMoney41 = 0.00;
			double pMoney41 = 0.00;
			if (kindForIncome4.get(0) != null) {
				if (kindForCost4.get(0) != null) {
					dMoney4 = kindForIncome4.get(0).getMoney();
					pMoney4 = kindForCost4.get(0).getTotalMn();
				}
			}
			if (kindForIncome4.get(1) != null) {
				if (kindForCost4.get(1) != null) {
					dMoney41 = kindForIncome4.get(1).getMoney();
					pMoney41 = kindForCost4.get(1).getTotalMn();
				}
			}
			param.put("code", "010303");
			List<DemandGoods> kindForIncome41 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost41 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney42 = 0.00;
			double pMoney42 = 0.00;
			double dMoney43 = 0.00;
			double pMoney43 = 0.00;
			if (kindForIncome41.get(0) != null) {
				if (kindForCost41.get(0) != null) {
					dMoney42 = kindForIncome41.get(0).getMoney();
					pMoney42 = kindForCost41.get(0).getTotalMn();
				}
			}
			if (kindForIncome41.get(1) != null) {
				if (kindForCost41.get(1) != null) {
					dMoney43 = kindForIncome41.get(1).getMoney();
					pMoney43 = kindForCost41.get(1).getTotalMn();
				}
			}
			HashMap<String, Object> map4 = new HashMap<>();
			map4.put("value",
					Math.round((dMoney4 + dMoney41 + dMoney42 + dMoney43 - pMoney4 - pMoney41 - pMoney42 - pMoney43)
							/ (dMoney4 + dMoney41 + dMoney42 + dMoney43) * 100));
			map4.put("name", "牛羊肉鲜品");
			incomelist.add(map4);

			param.put("code", "010304");
			List<DemandGoods> kindForIncome5 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost5 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney5 = 0.00;
			double pMoney5 = 0.00;
			double dMoney51 = 0.00;
			double pMoney51 = 0.00;
			if (kindForIncome5.get(0) != null) {
				if (kindForCost5.get(0) != null) {
					dMoney5 = kindForIncome5.get(0).getMoney();
					pMoney5 = kindForCost5.get(0).getTotalMn();
				}
			}
			if (kindForIncome5.get(1) != null) {
				if (kindForCost5.get(1) != null) {
					dMoney51 = kindForIncome5.get(1).getMoney();
					pMoney51 = kindForCost5.get(1).getTotalMn();
				}
			}
			HashMap<String, Object> map5 = new HashMap<>();
			map5.put("value", Math.round((dMoney5 + dMoney51 - pMoney5 - pMoney51) / (dMoney5 + dMoney51) * 100));
			map5.put("name", "禽肉鲜品");
			incomelist.add(map5);

			param.put("code", "010305");
			List<DemandGoods> kindForIncome6 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost6 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney6 = 0.00;
			double pMoney6 = 0.00;
			double dMoney61 = 0.00;
			double pMoney61 = 0.00;
			if (kindForIncome6.get(0) != null) {
				if (kindForCost6.get(0) != null) {
					dMoney6 = kindForIncome6.get(0).getMoney();
					pMoney6 = kindForCost6.get(0).getTotalMn();
				}
			}
			if (kindForIncome6.get(1) != null) {
				if (kindForCost6.get(1) != null) {
					dMoney61 = kindForIncome6.get(1).getMoney();
					pMoney61 = kindForCost6.get(1).getTotalMn();
				}
			}
			HashMap<String, Object> map6 = new HashMap<>();
			map6.put("value", Math.round((dMoney6 + dMoney61 - pMoney6 - pMoney61) / (dMoney6 + dMoney61) * 100));
			map6.put("name", "水产鲜品");
			incomelist.add(map6);
			param.put("code", "0106");
			List<DemandGoods> kindForIncome7 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost7 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney7 = 0.00;
			double pMoney7 = 0.00;
			double dMoney71 = 0.00;
			double pMoney71 = 0.00;
			if (kindForIncome7.get(0) != null) {
				if (kindForCost7.get(0) != null) {
					dMoney7 = kindForIncome7.get(0).getMoney();
					pMoney7 = kindForCost7.get(0).getTotalMn();
				}
			}
			if (kindForIncome7.get(1) != null) {
				if (kindForCost7.get(1) != null) {
					dMoney71 = kindForIncome7.get(1).getMoney();
					pMoney71 = kindForCost7.get(1).getTotalMn();
				}
			}
			HashMap<String, Object> map7 = new HashMap<>();
			map7.put("value", Math.round((dMoney7 + dMoney71 - pMoney7 - pMoney71) / (dMoney7 + dMoney71) * 100));
			map7.put("name", "肉类冻品");
			incomelist.add(map7);

			param.put("code", "0105");
			List<DemandGoods> kindForIncome8 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost8 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney8 = 0.00;
			double pMoney8 = 0.00;
			double dMoney81 = 0.00;
			double pMoney81 = 0.00;
			if (kindForIncome8.get(0) != null) {
				if (kindForCost8.get(0) != null) {
					dMoney8 = kindForIncome8.get(0).getMoney();
					pMoney8 = kindForCost8.get(0).getTotalMn();
				}
			}
			if (kindForIncome8.get(1) != null) {
				if (kindForCost8.get(1) != null) {
					dMoney81 = kindForIncome8.get(1).getMoney();
					pMoney81 = kindForCost8.get(1).getTotalMn();
				}
			}
			param.put("code", "0108");
			List<DemandGoods> kindForIncome81 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost81 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney82 = 0.00;
			double pMoney82 = 0.00;
			double dMoney83 = 0.00;
			double pMoney83 = 0.00;
			if (kindForIncome81.get(0) != null) {
				if (kindForCost81.get(0) != null) {
					dMoney82 = kindForIncome81.get(0).getMoney();
					pMoney82 = kindForCost81.get(0).getTotalMn();
				}
			}
			if (kindForIncome81.get(1) != null) {
				if (kindForCost81.get(1) != null) {
					dMoney83 = kindForIncome81.get(1).getMoney();
					pMoney83 = kindForCost81.get(1).getTotalMn();
				}
			}
			HashMap<String, Object> map8 = new HashMap<>();
			map8.put("value",
					Math.round((dMoney8 + dMoney81 + dMoney82 + dMoney83 - pMoney8 - pMoney81 - pMoney82 - pMoney83)
							/ (dMoney8 + dMoney81 + dMoney82 + dMoney83) * 100));
			map8.put("name", "调料类");
			incomelist.add(map8);

			param.put("code", "0109");
			List<DemandGoods> kindForIncome9 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost9 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney9 = 0.00;
			double pMoney9 = 0.00;
			double dMoney91 = 0.00;
			double pMoney91 = 0.00;
			if (kindForIncome9.get(0) != null) {
				if (kindForCost9.get(0) != null) {
					dMoney9 = kindForIncome9.get(0).getMoney();
					pMoney9 = kindForCost9.get(0).getTotalMn();
				}
			}
			if (kindForIncome9.get(1) != null) {
				if (kindForCost9.get(1) != null) {
					dMoney91 = kindForIncome9.get(1).getMoney();
					pMoney91 = kindForCost9.get(1).getTotalMn();
				}
			}
			param.put("code", "0110");
			List<DemandGoods> kindForIncome91 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost91 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney92 = 0.00;
			double pMoney92 = 0.00;
			double dMoney93 = 0.00;
			double pMoney93 = 0.00;
			if (kindForIncome91.get(0) != null) {
				if (kindForCost91.get(0) != null) {
					dMoney92 = kindForIncome91.get(0).getMoney();
					pMoney92 = kindForCost91.get(0).getTotalMn();
				}
			}
			if (kindForIncome91.get(1) != null) {
				if (kindForCost91.get(1) != null) {
					dMoney93 = kindForIncome91.get(1).getMoney();
					pMoney93 = kindForCost91.get(1).getTotalMn();
				}
			}
			HashMap<String, Object> map9 = new HashMap<>();
			map9.put("value",
					Math.round((dMoney9 + dMoney91 + dMoney92 + dMoney93 - pMoney9 - pMoney91 - pMoney92 - pMoney93)
							/ (dMoney9 + dMoney91 + dMoney92 + dMoney93) * 100));
			map9.put("name", "蛋奶类");
			incomelist.add(map9);

			param.put("code", "0111");
			List<DemandGoods> kindForIncome10 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost10 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney10 = 0.00;
			double pMoney10 = 0.00;
			double dMoney101 = 0.00;
			double pMoney101 = 0.00;
			if (kindForIncome10.get(0) != null) {
				if (kindForCost10.get(0) != null) {
					dMoney10 = kindForIncome10.get(0).getMoney();
					pMoney10 = kindForCost10.get(0).getTotalMn();
				}
			}
			if (kindForIncome10.get(1) != null) {
				if (kindForCost10.get(0) != null) {
					dMoney101 = kindForIncome10.get(1).getMoney();
					pMoney101 = kindForCost10.get(1).getTotalMn();
				}
			}
			HashMap<String, Object> map10 = new HashMap<>();
			map10.put("value",
					Math.round((dMoney10 + dMoney101 - pMoney10 - pMoney101) / (dMoney10 + dMoney101) * 100));
			map10.put("name", "热食类");
			incomelist.add(map10);

			param.put("code", "02");
			List<DemandGoods> kindForIncome11 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost11 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney111 = 0.00;
			double pMoney111 = 0.00;
			double dMoney112 = 0.00;
			double pMoney112 = 0.00;
			if (kindForIncome11.get(0) != null) {
				if (kindForCost11.get(0) != null) {
					dMoney111 = kindForIncome11.get(0).getMoney();
					pMoney111 = kindForCost11.get(0).getTotalMn();
				}
			}
			if (kindForIncome11.get(1) != null) {
				if (kindForCost11.get(1) != null) {
					dMoney112 = kindForIncome11.get(1).getMoney();
					pMoney112 = kindForCost11.get(1).getTotalMn();
				}
			}
			HashMap<String, Object> map11 = new HashMap<>();
			map11.put("value",
					Math.round((dMoney111 + dMoney112 - pMoney111 - pMoney112) / (dMoney111 + dMoney112) * 100));
			map11.put("name", "物耗");
			incomelist.add(map11);

			param.put("code", "03");
			List<DemandGoods> kindForIncome12 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost12 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney121 = 0.00;
			double pMoney121 = 0.00;
			double dMoney122 = 0.00;
			double pMoney122 = 0.00;
			if (kindForIncome12.get(0) != null) {
				if (kindForCost12.get(0) != null) {
					dMoney121 = kindForIncome12.get(0).getMoney();
					pMoney121 = kindForCost12.get(0).getTotalMn();
				}
			}
			if (kindForIncome12.get(1) != null) {
				if (kindForCost12.get(1) != null) {
					dMoney122 = kindForIncome12.get(1).getMoney();
					pMoney122 = kindForCost12.get(1).getTotalMn();
				}
			}
			HashMap<String, Object> map12 = new HashMap<>();
			map12.put("value",
					Math.round((dMoney121 + dMoney122 - pMoney121 - pMoney122) / (dMoney121 + dMoney122) * 100));
			map12.put("name", "小商品");
			incomelist.add(map12);

			result.setData(incomelist);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/**
	 * 
	 * 导出分类占比汇总
	 */
	@Override
	public Result excelOutBigKind(String goodsNm, String stime, String etime, int structId, String customerIds) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsNm", goodsNm);
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("customerIdsStr", customerIds);
		try {
			// 保存收入占比
			List<BigKind> bigKindlist = new ArrayList<>();
			param.put("code", "0101");
			List<DemandGoods> kindForIncome1 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost1 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney1 = 0.00;
			double pMoney1 = 0.00;
			double dMoney11 = 0.00;
			double pMoney11 = 0.00;
			if (kindForIncome1.get(0) != null) {
				if (kindForCost1.get(0) != null) {
					dMoney1 = kindForIncome1.get(0).getMoney();
					pMoney1 = kindForCost1.get(0).getTotalMn();
				}
			}
			if (kindForIncome1.get(1) != null) {
				if (kindForCost1.get(1) != null) {
					dMoney11 = kindForIncome1.get(1).getMoney();
					pMoney11 = kindForCost1.get(0).getTotalMn();
				}
			}
			BigKind bigKind1 = new BigKind();
			bigKind1.setKindNm("蔬菜类");
			bigKind1.setTotalDMoney(dMoney1 + dMoney11);
			bigKind1.setTotalPMoney(pMoney1 + pMoney11);
			Double profit1 = new Double(
					Math.round((dMoney1 - pMoney1 + dMoney11 - pMoney11) / (dMoney1 + dMoney11) * 10000));
			bigKind1.setTotalProfit(profit1 / 100);
			bigKindlist.add(bigKind1);
			param.put("code", "0102");
			List<DemandGoods> kindForIncome2 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost2 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney2 = 0.00;
			double pMoney2 = 0.00;
			double dMoney21 = 0.00;
			double pMoney21 = 0.00;
			if (kindForIncome2.get(0) != null) {
				if (kindForCost2.get(0) != null) {
					dMoney2 = kindForIncome2.get(0).getMoney();
					pMoney2 = kindForCost2.get(0).getTotalMn();
				}
			}
			if (kindForIncome2.get(1) != null) {
				if (kindForCost2.get(1) != null) {
					dMoney21 = kindForIncome2.get(1).getMoney();
					pMoney21 = kindForCost2.get(1).getTotalMn();
				}
			}
			BigKind bigKind2 = new BigKind();
			bigKind2.setKindNm("水果类");
			bigKind2.setTotalDMoney(dMoney2 + dMoney21);
			bigKind2.setTotalPMoney(pMoney2 + pMoney21);
			Double profit2 = new Double(
					Math.round((dMoney2 - pMoney2 + dMoney21 - pMoney21) / (dMoney2 + dMoney21) * 10000));
			bigKind2.setTotalProfit(profit2 / 100);
			bigKindlist.add(bigKind2);
			param.put("code", "010301");
			List<DemandGoods> kindForIncome3 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost3 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney3 = 0.00;
			double pMoney3 = 0.00;
			double dMoney31 = 0.00;
			double pMoney31 = 0.00;
			if (kindForIncome3.get(0) != null) {
				if (kindForCost3.get(0) != null) {
					dMoney3 = kindForIncome3.get(0).getMoney();
					pMoney3 = kindForCost3.get(0).getTotalMn();
				}
			}
			if (kindForIncome3.get(1) != null) {
				if (kindForCost3.get(1) != null) {
					dMoney31 = kindForIncome3.get(1).getMoney();
					pMoney31 = kindForCost3.get(1).getTotalMn();
				}
			}
			BigKind bigKind3 = new BigKind();
			bigKind3.setKindNm("猪肉鲜品");
			bigKind3.setTotalDMoney(dMoney3 + dMoney31);
			bigKind3.setTotalPMoney(pMoney3 + pMoney31);
			Double profit3 = new Double(
					Math.round((dMoney3 + dMoney31 - pMoney3 - pMoney31) / (dMoney3 + dMoney31) * 10000));
			bigKind3.setTotalProfit(profit3 / 100);
			bigKindlist.add(bigKind3);
			param.put("code", "010302");
			List<DemandGoods> kindForIncome4 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost4 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney4 = 0.00;
			double pMoney4 = 0.00;
			double dMoney41 = 0.00;
			double pMoney41 = 0.00;
			if (kindForIncome4.get(0) != null) {
				if (kindForCost4.get(0) != null) {
					dMoney4 = kindForIncome4.get(0).getMoney();
					pMoney4 = kindForCost4.get(0).getTotalMn();
				}
			}
			if (kindForIncome4.get(1) != null) {
				if (kindForCost4.get(1) != null) {
					dMoney41 = kindForIncome4.get(1).getMoney();
					pMoney41 = kindForCost4.get(1).getTotalMn();
				}
			}
			param.put("code", "010303");
			List<DemandGoods> kindForIncome41 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost41 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney42 = 0.00;
			double pMoney42 = 0.00;
			double dMoney43 = 0.00;
			double pMoney43 = 0.00;
			if (kindForIncome41.get(0) != null) {
				if (kindForCost41.get(0) != null) {
					dMoney42 = kindForIncome41.get(0).getMoney();
					pMoney42 = kindForCost41.get(0).getTotalMn();
				}
			}
			if (kindForIncome41.get(1) != null) {
				if (kindForCost41.get(1) != null) {
					dMoney43 = kindForIncome41.get(1).getMoney();
					pMoney43 = kindForCost41.get(1).getTotalMn();
				}
			}
			BigKind bigKind4 = new BigKind();
			bigKind4.setKindNm("牛羊肉鲜品");
			bigKind4.setTotalDMoney(dMoney4 + dMoney41 + dMoney42 + dMoney43);
			bigKind4.setTotalPMoney(pMoney4 + pMoney41 + pMoney42 + pMoney43);
			Double profit4 = new Double(
					Math.round((dMoney4 + dMoney41 + dMoney42 + dMoney43 - pMoney4 - pMoney41 - pMoney42 - pMoney43)
							/ (dMoney4 + dMoney41 + dMoney42 + dMoney43) * 10000));
			bigKind4.setTotalProfit(profit4 / 100);
			bigKindlist.add(bigKind4);
			param.put("code", "010304");
			List<DemandGoods> kindForIncome5 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost5 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney5 = 0.00;
			double pMoney5 = 0.00;
			double dMoney51 = 0.00;
			double pMoney51 = 0.00;
			if (kindForIncome5.get(0) != null) {
				if (kindForCost5.get(0) != null) {
					dMoney5 = kindForIncome5.get(0).getMoney();
					pMoney5 = kindForCost5.get(0).getTotalMn();
				}
			}
			if (kindForIncome5.get(1) != null) {
				if (kindForCost5.get(1) != null) {
					dMoney51 = kindForIncome5.get(1).getMoney();
					pMoney51 = kindForCost5.get(1).getTotalMn();
				}
			}
			BigKind bigKind5 = new BigKind();
			bigKind5.setKindNm("禽肉鲜品");
			bigKind5.setTotalDMoney(dMoney5 + dMoney51);
			bigKind5.setTotalPMoney(pMoney5 + pMoney51);
			Double profit5 = new Double(
					Math.round((dMoney5 + dMoney51 - pMoney5 - pMoney51) / (dMoney5 + dMoney51) * 10000));
			bigKind5.setTotalProfit(profit5 / 100);
			bigKindlist.add(bigKind5);
			param.put("code", "010305");
			List<DemandGoods> kindForIncome6 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost6 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney6 = 0.00;
			double pMoney6 = 0.00;
			double dMoney61 = 0.00;
			double pMoney61 = 0.00;
			if (kindForIncome6.get(0) != null) {
				if (kindForCost6.get(0) != null) {
					dMoney6 = kindForIncome6.get(0).getMoney();
					pMoney6 = kindForCost6.get(0).getTotalMn();
				}
			}
			if (kindForIncome6.get(1) != null) {
				if (kindForCost6.get(1) != null) {
					dMoney61 = kindForIncome6.get(1).getMoney();
					pMoney61 = kindForCost6.get(1).getTotalMn();
				}
			}
			BigKind bigKind6 = new BigKind();
			bigKind6.setKindNm("水产鲜品");
			bigKind6.setTotalDMoney(dMoney6 + dMoney61);
			bigKind6.setTotalPMoney(pMoney6 + pMoney61);
			Double profit6 = new Double(
					Math.round((dMoney6 + dMoney61 - pMoney6 - pMoney61) / (dMoney6 + dMoney61) * 10000));
			bigKind6.setTotalProfit(profit6 / 100);
			bigKindlist.add(bigKind6);
			param.put("code", "0106");
			List<DemandGoods> kindForIncome7 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost7 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney7 = 0.00;
			double pMoney7 = 0.00;
			double dMoney71 = 0.00;
			double pMoney71 = 0.00;
			if (kindForIncome7.get(0) != null) {
				if (kindForCost7.get(0) != null) {
					dMoney7 = kindForIncome7.get(0).getMoney();
					pMoney7 = kindForCost7.get(0).getTotalMn();
				}
			}
			if (kindForIncome7.get(1) != null) {
				if (kindForCost7.get(1) != null) {
					dMoney71 = kindForIncome7.get(1).getMoney();
					pMoney71 = kindForCost7.get(1).getTotalMn();
				}
			}
			BigKind bigKind7 = new BigKind();
			bigKind7.setKindNm("肉类冻品");
			bigKind7.setTotalDMoney(dMoney7 + dMoney71);
			bigKind7.setTotalPMoney(pMoney7 + pMoney71);
			Double profit7 = new Double(
					Math.round((dMoney7 + dMoney71 - pMoney7 - pMoney71) / (dMoney7 + dMoney71) * 10000));
			bigKind7.setTotalProfit(profit7 / 100);
			bigKindlist.add(bigKind7);
			param.put("code", "0105");
			List<DemandGoods> kindForIncome8 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost8 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney8 = 0.00;
			double pMoney8 = 0.00;
			double dMoney81 = 0.00;
			double pMoney81 = 0.00;
			if (kindForIncome8.get(0) != null) {
				if (kindForCost8.get(0) != null) {
					dMoney8 = kindForIncome8.get(0).getMoney();
					pMoney8 = kindForCost8.get(0).getTotalMn();
				}
			}
			if (kindForIncome8.get(1) != null) {
				if (kindForCost8.get(1) != null) {
					dMoney81 = kindForIncome8.get(1).getMoney();
					pMoney81 = kindForCost8.get(1).getTotalMn();
				}
			}
			param.put("code", "0108");
			List<DemandGoods> kindForIncome81 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost81 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney82 = 0.00;
			double pMoney82 = 0.00;
			double dMoney83 = 0.00;
			double pMoney83 = 0.00;
			if (kindForIncome81.get(0) != null) {
				if (kindForCost81.get(0) != null) {
					dMoney82 = kindForIncome81.get(0).getMoney();
					pMoney82 = kindForCost81.get(0).getTotalMn();
				}
			}
			if (kindForIncome81.get(1) != null) {
				if (kindForCost81.get(1) != null) {
					dMoney83 = kindForIncome81.get(1).getMoney();
					pMoney83 = kindForCost81.get(1).getTotalMn();
				}
			}
			BigKind bigKind8 = new BigKind();
			bigKind8.setKindNm("调料类");
			bigKind8.setTotalDMoney(dMoney8 + dMoney81 + dMoney82 + dMoney83);
			bigKind8.setTotalPMoney(pMoney8 + pMoney81 + pMoney82 + pMoney83);
			Double profit8 = new Double(
					Math.round((dMoney8 + dMoney81 + dMoney82 + dMoney83 - pMoney8 - pMoney81 - pMoney82 - pMoney83)
							/ (dMoney8 + dMoney81 + dMoney82 + dMoney83) * 10000));
			bigKind8.setTotalProfit(profit8 / 100);
			bigKindlist.add(bigKind8);
			param.put("code", "0109");
			List<DemandGoods> kindForIncome9 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost9 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney9 = 0.00;
			double pMoney9 = 0.00;
			double dMoney91 = 0.00;
			double pMoney91 = 0.00;
			if (kindForIncome9.get(0) != null) {
				if (kindForCost9.get(0) != null) {
					dMoney9 = kindForIncome9.get(0).getMoney();
					pMoney9 = kindForCost9.get(0).getTotalMn();
				}
			}
			if (kindForIncome9.get(1) != null) {
				if (kindForCost9.get(1) != null) {
					dMoney91 = kindForIncome9.get(1).getMoney();
					pMoney91 = kindForCost9.get(1).getTotalMn();
				}
			}
			param.put("code", "0110");
			List<DemandGoods> kindForIncome91 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost91 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney92 = 0.00;
			double pMoney92 = 0.00;
			double dMoney93 = 0.00;
			double pMoney93 = 0.00;
			if (kindForIncome91.get(0) != null) {
				if (kindForCost91.get(0) != null) {
					dMoney92 = kindForIncome91.get(0).getMoney();
					pMoney92 = kindForCost91.get(0).getTotalMn();
				}
			}
			if (kindForIncome91.get(1) != null) {
				if (kindForCost91.get(1) != null) {
					dMoney93 = kindForIncome91.get(1).getMoney();
					pMoney93 = kindForCost91.get(1).getTotalMn();
				}
			}
			BigKind bigKind9 = new BigKind();
			bigKind9.setKindNm("蛋奶类");
			bigKind9.setTotalDMoney(dMoney9 + dMoney91 + dMoney92 + dMoney93);
			bigKind9.setTotalPMoney(pMoney9 + pMoney91 + pMoney92 + pMoney93);
			Double profit9 = new Double(
					Math.round((dMoney9 + dMoney91 + dMoney92 + dMoney93 - pMoney9 - pMoney91 - pMoney92 - pMoney93)
							/ (dMoney9 + dMoney91 + dMoney92 + dMoney93) * 10000));
			bigKind9.setTotalProfit(profit9 / 100);
			bigKindlist.add(bigKind9);
			param.put("code", "0111");
			List<DemandGoods> kindForIncome10 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost10 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney10 = 0.00;
			double pMoney10 = 0.00;
			double dMoney101 = 0.00;
			double pMoney101 = 0.00;
			if (kindForIncome10.get(0) != null) {
				if (kindForCost10.get(0) != null) {
					dMoney10 = kindForIncome10.get(0).getMoney();
					pMoney10 = kindForCost10.get(0).getTotalMn();
				}
			}
			if (kindForIncome10.get(1) != null) {
				if (kindForCost10.get(0) != null) {
					dMoney101 = kindForIncome10.get(1).getMoney();
					pMoney101 = kindForCost10.get(1).getTotalMn();
				}
			}
			BigKind bigKind10 = new BigKind();
			bigKind10.setKindNm("热食类");
			bigKind10.setTotalDMoney(dMoney10 + dMoney101);
			bigKind10.setTotalPMoney(pMoney10 + pMoney101);
			Double profit10 = new Double(
					Math.round((dMoney10 + dMoney101 - pMoney10 - pMoney101) / (dMoney10 + dMoney101) * 10000));
			bigKind10.setTotalProfit(profit10 / 100);
			bigKindlist.add(bigKind10);
			param.put("code", "02");
			List<DemandGoods> kindForIncome11 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost11 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney111 = 0.00;
			double pMoney111 = 0.00;
			double dMoney112 = 0.00;
			double pMoney112 = 0.00;
			if (kindForIncome11.get(0) != null) {
				if (kindForCost11.get(0) != null) {
					dMoney111 = kindForIncome11.get(0).getMoney();
					pMoney111 = kindForCost11.get(0).getTotalMn();
				}
			}
			if (kindForIncome11.get(1) != null) {
				if (kindForCost11.get(1) != null) {
					dMoney112 = kindForIncome11.get(1).getMoney();
					pMoney112 = kindForCost11.get(1).getTotalMn();
				}
			}
			BigKind bigKind11 = new BigKind();
			bigKind11.setKindNm("物耗");
			bigKind11.setTotalDMoney(dMoney111 + dMoney112);
			bigKind11.setTotalPMoney(pMoney111 + pMoney112);
			Double profit11 = new Double(
					Math.round((dMoney111 + dMoney112 - pMoney111 - pMoney112) / (dMoney111 + dMoney112) * 10000));
			bigKind11.setTotalProfit(profit11 / 100);
			bigKindlist.add(bigKind11);
			param.put("code", "03");
			List<DemandGoods> kindForIncome12 = demandSummMapper.getBigKindForIncome(param);
			List<PurchaseSumm> kindForCost12 = purchaseSummMapper.getBigKindForCost(param);
			double dMoney121 = 0.00;
			double pMoney121 = 0.00;
			double dMoney122 = 0.00;
			double pMoney122 = 0.00;
			if (kindForIncome12.get(0) != null) {
				if (kindForCost12.get(0) != null) {
					dMoney121 = kindForIncome12.get(0).getMoney();
					pMoney121 = kindForCost12.get(0).getTotalMn();
				}
			}
			if (kindForIncome12.get(1) != null) {
				if (kindForCost12.get(1) != null) {
					dMoney122 = kindForIncome12.get(1).getMoney();
					pMoney122 = kindForCost12.get(1).getTotalMn();
				}
			}
			BigKind bigKind12 = new BigKind();
			bigKind12.setKindNm("小商品");
			bigKind12.setTotalDMoney(dMoney121 + dMoney122);
			bigKind12.setTotalPMoney(pMoney121 + pMoney122);
			Double profit12 = new Double(
					Math.round((dMoney121 + dMoney122 - pMoney121 - pMoney122) / (dMoney121 + dMoney122) * 10000));
			bigKind12.setTotalProfit(profit12 / 100);
			bigKindlist.add(bigKind12);
			// --------------统计占比----------------------------
			Double totalP = bigKind1.getTotalPMoney() + bigKind2.getTotalPMoney() + bigKind3.getTotalPMoney()
					+ bigKind4.getTotalPMoney() + bigKind5.getTotalPMoney() + bigKind6.getTotalPMoney()
					+ bigKind7.getTotalPMoney() + bigKind8.getTotalPMoney() + bigKind9.getTotalPMoney()
					+ bigKind10.getTotalPMoney() + bigKind11.getTotalPMoney() + bigKind12.getTotalPMoney();

			Double totalD = bigKind1.getTotalDMoney() + bigKind2.getTotalDMoney() + bigKind3.getTotalDMoney()
					+ bigKind4.getTotalDMoney() + bigKind5.getTotalDMoney() + bigKind6.getTotalDMoney()
					+ bigKind7.getTotalDMoney() + bigKind8.getTotalDMoney() + bigKind9.getTotalDMoney()
					+ bigKind10.getTotalDMoney() + bigKind11.getTotalDMoney() + bigKind12.getTotalDMoney();
			for (int i = 0; i < bigKindlist.size(); i++) {
				BigKind bigKind = bigKindlist.get(i);
				Double totalPMoney = bigKind.getTotalPMoney();
				Double double1 = new Double(Math.round(totalPMoney / totalP * 10000));
				bigKind.setPPercent(double1 / 100);
				Double totalDMoney = bigKind.getTotalDMoney();
				Double double2 = new Double(Math.round(totalDMoney / totalD * 10000));
				bigKind.setDPercent(double2 / 100);
			}
			result.setData(bigKindlist);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/**
	 * 通过客户Id查询客户
	 */
	@Override
	public List<String> getCustomerNmByCustomerIds(String customerIds) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("customerIdsStr", customerIds);
		List<String> list = null;
		try {
			list = purchaseSummMapper.getCustomerNmByCustomerId(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 为统计图表按天查看单个客户的物料数量
	 */
	@Override
	public Result getOrderNumByCustomerInDay(String stime, String etime, int structId, String customerIds,
			String kindCode) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("customerIdsStr", customerIds);
		param.put("kindCode", kindCode);
		try {
			// 创建集合存储单个客户当月每天的数量
			List<Double> orderNumList = new ArrayList<>();

			List<DemandGoods> demandSummList = demandSummMapper.getOrderNumBycustomerInDay(param);
			// 日期数组
			String[] day = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
					"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
			for (int j = 0; j < day.length; j++) {
				Double orderNum = 0.00;// 统计同一天的数量
				for (int i = 0; i < demandSummList.size(); i++) {
					DemandGoods demandGoods = demandSummList.get(i);
					String cday = demandGoods.getBillDate().substring(8, 10);
					if (day[j].equals(cday)) {
						orderNum += demandGoods.getDemandNum();
					}
				}
				orderNumList.add(orderNum);
			}
			result.setData(orderNumList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/**
	 * 按日查看单个客户的成本
	 */
	@Override
	public Result getToTalPmoneyByCustomerInDay(String stime, String etime, int structId, String customerIds,
			String kindCode) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("customerIdsStr", customerIds);
		param.put("kindCode", kindCode);
		try {
			// 存储单个客户单天的成本
			List<Double> totalPMoneyList = new ArrayList<>();
			List<PurchaseSumm> purchaseSummList = purchaseSummMapper.getToTalPMoneyByCustomerInDay(param);
			// 日期数组
			String[] day = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
					"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
			for (int j = 0; j < day.length; j++) {
				Double totalPMoney = 0.00;// 统计同一天的数量
				for (int i = 0; i < purchaseSummList.size(); i++) {
					PurchaseSumm purchaseSumm = purchaseSummList.get(i);
					String cday = purchaseSumm.getBillDate().substring(8, 10);
					if (day[j].equals(cday)) {
						totalPMoney += purchaseSumm.getTotalMn();
					}
				}
				totalPMoneyList.add(NumUtil.trim0(totalPMoney));
			}
			result.setData(totalPMoneyList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	@Override
	public Result getToTalDmoneyByCustomerInDay(String stime, String etime, int structId, String customerIds,
			String kindCode) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("customerIdsStr", customerIds);
		param.put("kindCode", kindCode);
		try {
			// 存储单个客户单天的成本
			List<Double> totalDMoneyList = new ArrayList<>();
			List<DemandGoods> demandGoodsList = demandSummMapper.getOrderNumBycustomerInDay(param);
			// 日期数组
			String[] day = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
					"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
			for (int j = 0; j < day.length; j++) {
				Double totalDMoney = 0.00;// 统计同一天的数量
				for (int i = 0; i < demandGoodsList.size(); i++) {
					DemandGoods demandGoods = demandGoodsList.get(i);
					String cday = demandGoods.getBillDate().substring(8, 10);
					if (day[j].equals(cday)) {
						totalDMoney += demandGoods.getMoney();
					}
				}
				totalDMoneyList.add(NumUtil.trim0(totalDMoney));
			}
			result.setData(totalDMoneyList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/**
	 * 获取每月的物料数量(按单个客户)
	 */
	@Override
	public Result getOrderNumByCustomerInMonth(String stime, String etime, int structId, String customerIds,
			String kindCode) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("customerIdsStr", customerIds);
		param.put("kindCode", kindCode);
		try {
			// 创建集合存储单个客户当月每天的数量
			List<Double> orderNumList = new ArrayList<>();

			List<DemandGoods> demandGoodsList = demandSummMapper.getOrderNumBycustomerInMonth(param);
			// 月数组
			String[] month = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
			for (int j = 0; j < month.length; j++) {
				Double orderNum = 0.00;// 统计同一月的数量
				for (int i = 0; i < demandGoodsList.size(); i++) {
					DemandGoods demandGoods = demandGoodsList.get(i);
					String cMonth = demandGoods.getBillDate().substring(5, 7);
					if (month[j].equals(cMonth)) {
						orderNum += demandGoods.getDemandNum();
					}
				}
				orderNumList.add(NumUtil.trim0(orderNum));
			}
			result.setData(orderNumList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	/**
	 * 按月查看单个客户的成本
	 */
	@Override
	public Result getToTalPmoneyByCustomerInMonth(String stime, String etime, int structId, String customerIds,
			String kindCode) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("customerIdsStr", customerIds);
		param.put("kindCode", kindCode);
		try {
			// 存储单个客户单天的成本
			List<Double> totalPMoneyList = new ArrayList<>();
			List<PurchaseSumm> purchaseSummList = purchaseSummMapper.getToTalPMoneyByCustomerInMonth(param);
			// 月数组
			String[] month = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
			for (int j = 0; j < month.length; j++) {
				Double totalPMoney = 0.00;// 统计同一天的数量
				for (int i = 0; i < purchaseSummList.size(); i++) {
					PurchaseSumm purchaseSumm = purchaseSummList.get(i);
					String cMonth = purchaseSumm.getBillDate().substring(5, 7);
					if (month[j].equals(cMonth)) {
						totalPMoney += purchaseSumm.getTotalMn();
					}
				}
				totalPMoneyList.add(NumUtil.trim0(totalPMoney));
			}
			result.setData(totalPMoneyList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	@Override
	public Result getToTalDmoneyByCustomerInMonth(String stime, String etime, int structId, String customerIds,
			String kindCode) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("customerIdsStr", customerIds);
		param.put("kindCode", kindCode);
		try {
			// 存储单个客户单天的成本
			List<Double> totalDMoneyList = new ArrayList<>();
			List<DemandGoods> demandGoodsList = demandSummMapper.getOrderNumBycustomerInMonth(param);
			// 月数组
			String[] month = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
			for (int j = 0; j < month.length; j++) {
				Double totalDMoney = 0.00;// 统计同一天的数量
				for (int i = 0; i < demandGoodsList.size(); i++) {
					DemandGoods demandGoods = demandGoodsList.get(i);
					String cmonth = demandGoods.getBillDate().substring(5, 7);
					if (month[j].equals(cmonth)) {
						totalDMoney += demandGoods.getMoney();
					}
				}
				totalDMoneyList.add(NumUtil.trim0(totalDMoney));
			}
			result.setData(totalDMoneyList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	@Override
	public Result getOrderNumByCustomersInDay(String stime, String etime, int structId, String customerIds,
			String kindCode) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("customerIdsStr", customerIds);
		param.put("kindCode", kindCode);
		try {
			// 创建集合存储单个客户当月每天的数量
			List<Double> orderNumList = new ArrayList<>();

			List<DemandGoods> demandSummList = demandSummMapper.getOrderNumBycustomersInDay(param);
			// 客户数组
			String[] customers = { "机关一食堂", "机关二食堂", "招待所", "汽车队", "78分队", "正定新校", "正定老校", "库损" };
			for (int j = 0; j < customers.length; j++) {
				Double orderNum = 0.00;// 统计同一天的数量
				for (int i = 0; i < demandSummList.size(); i++) {
					DemandGoods demandGoods = demandSummList.get(i);
					String cCustomNm = demandGoods.getCustomNm();
					if (customers[j].equals(cCustomNm)) {
						orderNum += demandGoods.getDemandNum();
					}
				}
				orderNumList.add(NumUtil.trim0(orderNum));
			}
			result.setData(orderNumList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	@Override
	public Result getToTalPmoneyByCustomersInDay(String stime, String etime, int structId, String customerIds,
			String kindCode) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("customerIdsStr", customerIds);
		param.put("kindCode", kindCode);
		try {
			// 存储单个客户单天的成本
			List<Double> totalPMoneyList = new ArrayList<>();
			List<PurchaseSumm> purchaseSummList = purchaseSummMapper.getToTalPMoneyByCustomersInDay(param);
			// 客户数组
			String[] customers = { "机关一食堂", "机关二食堂", "招待所", "汽车队", "78分队", "正定新校", "正定老校", "库损" };
			for (int j = 0; j < customers.length; j++) {
				Double totalPMoney = 0.00;// 统计同一天的数量
				for (int i = 0; i < purchaseSummList.size(); i++) {
					PurchaseSumm purchaseSumm = purchaseSummList.get(i);
					String cCustomNm = purchaseSumm.getCustomNm();
					if (customers[j].equals(cCustomNm)) {
						totalPMoney += purchaseSumm.getTotalMn();
					}
				}
				totalPMoneyList.add(NumUtil.trim0(totalPMoney));
			}
			result.setData(totalPMoneyList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}

	@Override
	public Result getToTalDmoneyByCustomersInDay(String stime, String etime, int structId, String customerIds,
			String kindCode) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("customerIdsStr", customerIds);
		param.put("kindCode", kindCode);
		try {
			// 存储单个客户单天的成本
			List<Double> totalPMoneyList = new ArrayList<>();
			List<DemandGoods> demandGoodsList = demandSummMapper.getOrderNumBycustomersInDay(param);
			// 客户数组
			String[] customers = { "机关一食堂", "机关二食堂", "招待所", "汽车队", "78分队", "正定新校", "正定老校", "库损" };
			for (int j = 0; j < customers.length; j++) {
				Double totalPMoney = 0.00;// 统计同一天的数量
				for (int i = 0; i < demandGoodsList.size(); i++) {
					DemandGoods demandGoods = demandGoodsList.get(i);
					String cCustomNm = demandGoods.getCustomNm();
					if (customers[j].equals(cCustomNm)) {
						totalPMoney += demandGoods.getMoney();
					}
				}
				totalPMoneyList.add(NumUtil.trim0(totalPMoney));
			}
			result.setData(totalPMoneyList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
		}
		return result;
	}
}
