package com.qxh.impl.stockChange;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.StockChange;
import com.qxh.model.GoodsKind;
import com.qxh.service.StockChangeService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.Result;

public class StockChangeServiceImpl implements StockChangeService {

	Logger log = Logger.getLogger(this.getClass());
	private StockChangeMapper stockChangeMapper;

	public void setStockChangeMapper(StockChangeMapper stockChangeMapper) {
		this.stockChangeMapper = stockChangeMapper;
	}

	/**
	 * 库存变动记录
	 */
	@Override
	public Result getStockChangeRec(String stime, String etime, int structId, String name, String kindCode,String goodsCode) {
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
		param.put("structId", structId);
		param.put("name", name);
		param.put("kindCode", kindCode);
		param.put("goodsCode", goodsCode);
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
			List<StockChange> stockList = stockChangeMapper.getStockList(param);
			if (stockList == null || stockList.size() == 0) {
				result.setMsg("无库存记录");
				result.setCode(CodeConstant.CODE200);
				return result;
			}
			List<StockChange> changeList = stockChangeMapper.getStockChangeRec(param);
			String unChangeStr = "";
			String eAtNo = "";
			double totalSMoney = 0, totalRkMoney = 0, totalCkMoney = 0, totalEMoney = 0;
			if (changeList != null && changeList.size() > 0) {
				int cLength = changeList.size();
				for (int i = 0; i < cLength; i++) {
					contains(stockList, changeList.get(i).getGoodsId(), unChangeStr);
					eAtNo += changeList.get(i).getMaxAtNo() + ",";
				}
				if (eAtNo.length() > 0) {
					eAtNo = eAtNo.substring(0, eAtNo.length() - 1);
					param.put("eAtNo", eAtNo);
					List<StockChange> eChangeList = stockChangeMapper.getEChangeList(param);
					for (int i = 0; i < cLength; i++) {
						StockChange change = changeList.get(i);
						StockChange eChange = eChangeList.get(i);
						change.seteMoney(eChange.geteMoney());
						change.seteNum(eChange.geteNum());
						totalSMoney += change.getsMoney();
						totalRkMoney += change.getRkMoney();
						totalCkMoney += change.getCkMoney();
						totalEMoney += change.geteMoney();
					}
				}
			} else {
				changeList = new ArrayList<>();
			}
			int sLength = stockList.size();
			for (int i = 0; i < sLength; i++) {
				unChangeStr += stockList.get(i).getGoodsId() + ",";
			}
			if (unChangeStr != "") {
				unChangeStr = unChangeStr.substring(0, unChangeStr.length() - 1);
				param.put("unChangeStr", unChangeStr);
				List<Integer> unChangeAtNoList = stockChangeMapper.getUnChangeAtNoList(param);
				if (unChangeAtNoList != null && unChangeAtNoList.size() > 0) {
					int uaLength = unChangeAtNoList.size();
					String unChangeAtNo = "";
					for (int i = 0; i < uaLength; i++) {
						unChangeAtNo += unChangeAtNoList.get(i) + ",";
					}
					unChangeAtNo = unChangeAtNo.substring(0, unChangeAtNo.length() - 1);
					param.put("unChangeAtNo", unChangeAtNo);
					List<StockChange> unChangeList = stockChangeMapper.getUnChangeList(param);
					if (unChangeList != null && unChangeList.size() > 0) {
						int uLength = unChangeList.size();
						for (int i = 0; i < uLength; i++) {
							checkStock(stockList, unChangeList.get(i).getGoodsId());
							StockChange change = unChangeList.get(i);
							totalSMoney += change.getsMoney();
							totalRkMoney += change.getRkMoney();
							totalCkMoney += change.getCkMoney();
							totalEMoney += change.geteMoney();
						}
						changeList.addAll(unChangeList);
					}
				}

			}
			changeList.addAll(stockList);
			StockChange total = new StockChange();
			total.setGoodsNm("合计");
			total.setsMoney(totalSMoney);
			total.setRkMoney(totalRkMoney);
			total.setCkMoney(totalCkMoney);
			total.seteMoney(totalEMoney);
			changeList.add(total);
			// List<GoodsKind> kindList=commonMapper.getGoodsKind();
			// int kindLen=kindList.size();
			// Map<Integer, String> kindNmMap=new HashMap<>();
			// for (int i = 0; i < kindLen; i++) {
			// GoodsKind kind=kindList.get(i);
			// kindNmMap.put(kind.getAtNo(), kind.getName());
			// kindNmMap.put(-kind.getAtNo(), kind.getpId()+"");
			// }
			// int l=changeList.size();
			// Map<Integer, StockChange> map=new HashMap<>();
			// for (int i = 0; i < l; i++) {
			// StockChange change=changeList.get(i);
			// StockChange kindChange;
			// if(map.get(change.getKindId())!=null){
			// kindChange=(StockChange)map.get(change.getKindId());
			// }else{
			// kindChange=new StockChange();
			// }
			// kindChange.setCkMoney(kindChange.getCkMoney()+change.getCkMoney());
			// kindChange.setCkNum(kindChange.getCkNum()+change.getCkNum());
			// kindChange.seteMoney(kindChange.geteMoney()+change.geteMoney());
			// kindChange.seteNum(kindChange.geteNum()+change.geteMoney());
			// kindChange.setRkMoney(kindChange.getRkMoney()+change.getRkMoney());
			// kindChange.setRkNum(kindChange.getRkNum()+change.getRkNum());
			// kindChange.setsMoney(kindChange.getsMoney()+change.getsMoney());
			// kindChange.setsNum(kindChange.getsNum()+change.getsNum());
			// kindChange.setGoodsNm(change.getKindNm());
			// kindChange.setKindId(change.getKindId());
			// kindChange.setGoodsId(0);
			// kindChange.setKindPId(kindNmMap.get(-change.getKindId())==null?0:
			// Integer.parseInt(kindNmMap.get(-change.getKindId())));
			// map.put(change.getKindId(), kindChange);
			// }
			// List<StockChange> list1 = new
			// ArrayList<StockChange>(map.values());
			// changeList.addAll(list1);
			// while(!map.isEmpty()){
			// map=setKindId(map,kindList,kindNmMap);
			// map.remove(0);
			// List<StockChange> list2 = new
			// ArrayList<StockChange>(map.values());
			// changeList.addAll(list2);
			// }
			// Collections.sort(changeList, new SortByKind());
			result.setData(changeList);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 库存变动记录：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",stime:"
					+ stime + ",etime:" + etime + ",structId:" + structId + "\r\n\r\n");
		}
		return result;
	}

//	private Map<Integer, StockChange> setKindId(Map<Integer, StockChange> map, List<GoodsKind> kindList,
//			Map<Integer, String> kindNmMap) {
//		Map<Integer, StockChange> newMap = new HashMap<>();
//		Iterator it = map.keySet().iterator();
//		int l = kindList.size();
//		while (it.hasNext()) {
//			Integer key = (Integer) it.next();
//			StockChange kindChange = map.get(key);
//			for (int i = 0; i < l; i++) {
//				GoodsKind kind = kindList.get(i);
//				if (kind.getAtNo() == kindChange.getKindId()) {
//					StockChange newKind = new StockChange();
//					if (newMap.get(kind.getpId()) != null) {
//						newKind = (StockChange) newMap.get(kind.getpId());
//					} else {
//						newKind = new StockChange();
//					}
//					newKind.setCkMoney(newKind.getCkMoney() + kindChange.getCkMoney());
//					newKind.setCkNum(newKind.getCkNum() + kindChange.getCkNum());
//					newKind.seteMoney(newKind.geteMoney() + kindChange.geteMoney());
//					newKind.seteNum(newKind.geteNum() + kindChange.geteMoney());
//					newKind.setRkMoney(newKind.getRkMoney() + kindChange.getRkMoney());
//					newKind.setRkNum(newKind.getRkNum() + kindChange.getRkNum());
//					newKind.setsMoney(newKind.getsMoney() + kindChange.getsMoney());
//					newKind.setsNum(newKind.getsNum() + kindChange.getsNum());
//					newKind.setKindId(kind.getpId());
//					newKind.setGoodsNm(kindNmMap.get(kind.getpId()));
//					newKind.setGoodsId(kindChange.getGoodsId() - 1);
//					newKind.setKindPId(kindNmMap.get(-kind.getpId()) == null ? 0
//							: Integer.parseInt(kindNmMap.get(-kind.getpId())));
//					newMap.put(newKind.getKindId(), newKind);
//					break;
//				}
//			}
//
//		}
//		return newMap;
//	}

	private void contains(List<StockChange> stockList, int goodsId, String unChangeStr) {
		int l = stockList.size();
		int containIndex = -1;
		for (int i = 0; i < l; i++) {
			if (stockList.get(i).getGoodsId() == goodsId) {
				containIndex = i;
			}
		}
		stockList.remove(containIndex);
	}

	private void checkStock(List<StockChange> stockList, int goodsId) {
		int l = stockList.size();
		for (int i = 0; i < l; i++) {
			if (stockList.get(i).getGoodsId() == goodsId) {
				stockList.remove(i);
				break;
			}
		}
	}

	class SortByKind implements Comparator {
		public int compare(Object o1, Object o2) {
			StockChange s1 = (StockChange) o1;
			StockChange s2 = (StockChange) o2;
			if (s1.getKindId() > s2.getKindId())
				return 1;
			else if (s1.getKindId() < s2.getKindId())
				return -1;
			else if (s1.getGoodsId() > s2.getGoodsId())
				return 1;
			else if (s1.getGoodsId() < s2.getGoodsId())
				return -1;
			return 0;
		}
	}

}
