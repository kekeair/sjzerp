package com.qxh.impl.stockQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.model.Stock;
import com.qxh.model.StockRecord;
import com.qxh.service.StockQueryService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.IPageConstants;
import com.qxh.utils.PageUtil;
import com.qxh.utils.Result;

public class StockQueryServiceImpl implements StockQueryService {

	Logger log = Logger.getLogger(this.getClass());
	private StockQueryMapper stockQueryMapper;

	public void setStockQueryMapper(StockQueryMapper stockQueryMapper) {
		this.stockQueryMapper = stockQueryMapper;
	}

	/**
	 * 库存列表
	 */
	@Override
	public Result getStockList(String name, String kindCode, String page, int structId,String goodsCode) {
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
		Map<String, Object> param = new HashMap<>();
		param.put("name", name);
		param.put("goodsCode", goodsCode);
		param.put("kindCode", kindCode);
		param.put("page", (Integer.parseInt(page) - 1) * IPageConstants.PageSize);
		param.put("structId", structId);
		param.put("pageSize", IPageConstants.PageSize);
		try {
			List<Stock> list = stockQueryMapper.getStockList(param);
			if (list != null && list.size() > 0) {
				int l = list.size();
				int orderIndex = (Integer.parseInt(page) - 1) * IPageConstants.PageSize + 1;
				for (int i = 0; i < l; i++) {
					list.get(i).setOrderIndex(orderIndex);
					orderIndex++;
				}
			}
			int count = stockQueryMapper.getStockListNum(param);
			Map<String, Object> data = new HashMap<>();
			data.put("list", list);
			data.put("totalCount", count);
			data.put("totalPage", PageUtil.getTotalPage(count, IPageConstants.PageSize));
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询库存列表：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",name:" + name
					+ ",kindId:" + kindCode + ",page:" + page + ",structId:" + structId + "\r\n\r\n");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询库存记录
	 */
	@Override
	public Result getStockRecord(String stockFlag, String stime, String etime, String page, String goodsId,
			int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("stockFlag", stockFlag);
		param.put("goodsId", goodsId);
		param.put("page", (Integer.parseInt(page) - 1) * IPageConstants.PageSize);
		param.put("structId", structId);
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
			List<StockRecord> list = stockQueryMapper.getStockRecord(param);
			int count = stockQueryMapper.getStockRecordNum(param);
			Map<String, Object> data = new HashMap<>();
			data.put("list", list);
			data.put("totalCount", count);
			data.put("totalPage", PageUtil.getTotalPage(count, IPageConstants.PageSize));
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询库存记录：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",stockFlag:"
					+ stockFlag + ",stime:" + stime + ",etime:" + etime + ",page:" + page + ",structId:" + structId
					+ ",goodsId:" + goodsId + "\r\n\r\n");
		}
		return result;
	}
}
