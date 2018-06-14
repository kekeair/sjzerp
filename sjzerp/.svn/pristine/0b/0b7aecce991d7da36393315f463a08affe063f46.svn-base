package com.qxh.impl.demandSumm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.DemandGoods;
import com.qxh.exmodel.DemandSummaryD;
import com.qxh.service.DemandSummService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.NumUtil;
import com.qxh.utils.Result;

public class DemandSummServiceImpl implements DemandSummService {

	Logger log = Logger.getLogger(this.getClass());
	private DemandSummMapper demandSummMapper;

	public void setDemandSummMapper(DemandSummMapper demandSummMapper) {
		this.demandSummMapper = demandSummMapper;
	}

	/**
	 * 查询销售汇总
	 */
	@Override
	public Result getDemandSummary(String stime, String etime,int structId,String customerId,String goodsNm,String kindCode) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("customerId", customerId);
		param.put("goodsNm", goodsNm);
		param.put("kindCode", kindCode);
		try {
			List<DemandGoods> list = demandSummMapper.getDemandSummary(param);
			Double totalMoney=0.00;
			for (int i = 0; i < list.size(); i++) {
				double money = list.get(i).getMoney();
				totalMoney=totalMoney+money;
			}
			double trim0_2 = NumUtil.trim0(totalMoney);
			DemandGoods demandGoods = new DemandGoods();
			demandGoods.setGoodsNm("合计");
			demandGoods.setMoney(trim0_2);
			list.add(demandGoods);
			result.setData(list);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询销售汇总：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" 
					+ ",stime:" + stime + ",etime:" + etime + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 根据物料查询销售汇总
	 */
	@Override
	public Result getDemandSummByGoods(String goodsId, String goodsType,String stime,
			String etime,int structId,String demandListDId,String customerId) {
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
		param.put("customerId", customerId);
		try {
			DemandSummaryD demandSummaryD = demandSummMapper.getGoodsNmByDemandListDId(param);
			String goodsNm = demandSummaryD.getGoodsNm();
			param.put("goodsNm", goodsNm);
			List<DemandSummaryD> list = demandSummMapper.getDemandSummByGoods(param);
			result.setData(list);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 根据物料查询销售汇总：  errorcode=" + ErrorCode.geterrocode(this) 
			+ "  \r\n" + e + "\r\n" + ",goodsId:" + goodsId 
			+ ",goodsType:" + goodsType + "\r\n\r\n");
		}
		return result;
	}

	
}
