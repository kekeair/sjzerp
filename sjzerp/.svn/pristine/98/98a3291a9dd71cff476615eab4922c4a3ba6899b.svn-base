package com.qxh.impl.stockFix;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.ListGoods;
import com.qxh.impl.common.CommonMapper;
import com.qxh.model.CenterGoods;
import com.qxh.model.Stock;
import com.qxh.model.StockFixD;
import com.qxh.model.StockFixH;
import com.qxh.service.StockFixService;
import com.qxh.utils.DateUtil;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.IPageConstants;
import com.qxh.utils.PageUtil;
import com.qxh.utils.Result;

public class StockFixServiceImpl implements StockFixService {
	
	Logger log = Logger.getLogger(this.getClass());
	private StockFixMapper stockFixMapper;
	private CommonMapper commonMapper;
	
	public void setStockFixMapper(StockFixMapper stockFixMapper) {
		this.stockFixMapper = stockFixMapper;
	}
	
	public void setCommonMapper(CommonMapper commonMapper) {
		this.commonMapper = commonMapper;
	}

	/**
	 * 获取餐饮中心物料
	 */
	@Override
	public Result getCenterGoods(String name, String kindCode, String page,
			int structId,String billId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("name", name);
		param.put("page", (Integer.parseInt(page)-1)*IPageConstants.PageSize);
		param.put("kindCode", kindCode);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("structId", structId);
		param.put("billId", billId);
		try {
			List<CenterGoods> goodsList=stockFixMapper.getCenterGoods(param);
			if(goodsList!=null&&goodsList.size()>0){
				int l=goodsList.size();
				int orderIndex=(Integer.parseInt(page)-1)*IPageConstants.PageSize+1;
				for (int i = 0; i < l; i++) {
					goodsList.get(i).setOrderIndex(orderIndex);
					orderIndex++;
				}
			}
			int count=stockFixMapper.getCenterGoodsNum(param);
			Map<String, Object> data=new HashMap<>();
			data.put("goodsList", goodsList);
			data.put("totalCount", count);
			data.put("totalPage", PageUtil.getTotalPage(count, 
					IPageConstants.PageSize));
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 获取餐饮中心物料：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" +",name:"+name +",kindCode:"+kindCode
			+",page:"+page+",structId:"+structId+ "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 查询库存修正详细
	 */
	@Override
	public Result getStockFixD(String name, String kindCode, String page, String billId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		try {
			Map<String, Object> data=new HashMap<>();
			getStockFixDList(name, kindCode, billId, page, data);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询库存修正详细：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" +",name:"+name +",kindCode:"+kindCode
			+",page:"+page+",billId:"+billId+ "\r\n\r\n");
			e.printStackTrace();
		}
		return result;
	}
	
	private void getStockFixDList(String name,String kindCode,String billId,
			String page,Map<String, Object> data){
		Map<String, Object> param = new HashMap<>();
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
		param.put("name", name);
		param.put("page", (Integer.parseInt(page)-1)*IPageConstants.PageSize);
		param.put("kindCode", kindCode);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("billId", billId);
		Integer billState=stockFixMapper.getStockFixHState(param);
		List<StockFixD> goodsList=stockFixMapper.getStockFixD(param);
		if(goodsList!=null&&goodsList.size()>0){
			int l=goodsList.size();
			int orderIndex=(Integer.parseInt(page)-1)*IPageConstants.PageSize+1;
			for (int i = 0; i < l; i++) {
				goodsList.get(i).setOrderIndex(orderIndex);
				orderIndex++;
			}
		}
		int count=stockFixMapper.getStockFixDNum(param);
		data.put("billState", billState);
		data.put("goodsList", goodsList);
		data.put("totalCount", count);
		data.put("totalPage", PageUtil.getTotalPage(count, 
				IPageConstants.PageSize));
	}

	/**
	 * 查询库存修正单列表
	 */
	@Override
	public Result getStockFixH(String state, String stime, String etime, String page, 
			int structId,int roleId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("state", state);
		param.put("page", (Integer.parseInt(page)-1)*IPageConstants.PageSize);
		param.put("structId", structId);
		param.put("pageSize", IPageConstants.PageSize);
		try {
			if(!StringUtils.isEmpty(stime)){
				stime=stime+" 00:00:00";
			}else{
				stime="";
			}
			if(!StringUtils.isEmpty(etime)){
				etime=etime+" 23:59:59";
			}else{
				etime="";
			}
			param.put("stime", stime);
			param.put("etime", etime);
			switch (roleId) {
			case 5://库管
				param.put("viewPower", 0);
				break;
			case 4://经理
			case 10://财务
				param.put("viewPower", 1);
				break;
			}
			List<StockFixH> list=stockFixMapper.getStockFixH(param);
			if(list!=null&&list.size()>0){
				int l=list.size();
				int orderIndex=(Integer.parseInt(page)-1)*IPageConstants.PageSize+1;
				for (int i = 0; i < l; i++) {
					list.get(i).setOrderIndex(orderIndex);
					orderIndex++;
				}
			}
			int count=stockFixMapper.getStockFixHNum(param);
			Map<String, Object> data=new HashMap<>();
			data.put("list", list);
			data.put("totalCount", count);
			data.put("totalPage", PageUtil.getTotalPage(count, 
					IPageConstants.PageSize));
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询库存修正单列表：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" +",state:"+state +",stime:"+stime
			+",etime:"+etime+",page:"+page+",structId:"+structId+ "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 删除库存修正单
	 */
	@Override
	public Result delStockFix(String billId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		try {
			stockFixMapper.delStockFixH(param);
			stockFixMapper.delStockFixD(param);
		} catch (Exception e) {
			log.error("\r\n 删除库存修正单：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" +",billId:"+billId + "\r\n\r\n");
			throw new RuntimeException();
		}
		return result;
	}

	/**
	 * 添加库存修正详细
	 */
	@Override
	public Result addStockFixD(String name, String kindCode, String page,String goodsId, 
			int structId, String billId,int userId,String userNm) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsId", goodsId);
		param.put("billId", billId);
		param.put("structId", structId);
		param.put("userId", userId);
		param.put("userNm", userNm);
		try {
			if(billId.equals("-1")){
				synchronized (this) {
					//生成单据号
					Integer maxCodeOrder=stockFixMapper.getMaxCodeOrder(param);
					if(maxCodeOrder==null){
						maxCodeOrder=1;
					}else{
						maxCodeOrder++;
					}
					String codeStr=String.valueOf(maxCodeOrder);
					if(codeStr.length()==1){
						codeStr="00"+codeStr;
					}else if(codeStr.length()==2){
						codeStr+="0"+codeStr;
					}
					String centerCode=commonMapper.getStructCodeById(param);
					String code="xz-"+centerCode+"-"+
							DateUtil.getDate(new Date(),"yyMMdd")+"-"+codeStr;
					param.put("code", code);
					param.put("codeOrder", maxCodeOrder);
					stockFixMapper.addStockFixH(param);
				}
			}
			Stock stock=stockFixMapper.getStock(param);
			if(stock!=null){
				param.put("goodsNum",stock.getStockNum());
				param.put("goodsPrice", stock.getPrice());
			}else{
				param.put("goodsNum",0);
				param.put("goodsPrice", 0);
			}
			stockFixMapper.addStockFixD(param);
			Map<String, Object> data=new HashMap<>();
			getStockFixDList(name, kindCode, param.get("billId").toString(), page, data);
			data.put("billId", param.get("billId"));
			result.setData(data);
		} catch (Exception e) {
			log.error("\r\n 添加库存修正详细：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" +",name:"+name+",kindCode:"+kindCode
			+",page:"+page+",goodsId:"+goodsId+",structId:"+structId
			+",billId:"+billId + "\r\n\r\n");
			throw new RuntimeException();
		}
		return result;
	}

	/**
	 * 删除库存修正详细
	 */
	@Override
	public Result delStockFixD(String name,String kindCode,String page,String stockFixDId,
			String billId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("stockFixDId", stockFixDId);
		try {
			stockFixMapper.delStockFixDById(param);
			Map<String, Object> data=new HashMap<>();
			getStockFixDList(name, kindCode, billId, page, data);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 删除库存修正详细：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" +",name:"+name+",kindCode:"+kindCode
			+",page:"+page+",stockFixDId:"+stockFixDId + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 添加自定义物料
	 */
	@Override
	public Result addExtraGoods(String goodsNm, String goodsCode,String goodsUnit,
			int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsNm", goodsNm);
		param.put("goodsCode", goodsCode);
		param.put("goodsUnit", goodsUnit);
		param.put("structId", structId);
		try {
			//检查自定义物料是否重复
			int tmpGoodsNum=stockFixMapper.checkTmpGoodsNum(param);
			if(tmpGoodsNum>0){
				result.setCode(CodeConstant.CODE200);
				result.setMsg("该物料已存在");
				return result;
			}
			//添加自定义物料
			String unitNm=commonMapper.getUnitNmById(param);
			param.put("unitNm", unitNm);
			stockFixMapper.addExtraGoods(param);
			List<ListGoods> list=stockFixMapper.getTmpGoodsList(param);
			result.setData(list);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 添加自定义物料：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" +",goodsNm:"+goodsNm
			+",goodsCode:"+goodsCode+",goodsUnit:"+goodsUnit + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 查询自定义物料列表
	 */
	@Override
	public Result getTmpGoodsList(int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("structId", structId);
		try {
			List<ListGoods> list=stockFixMapper.getTmpGoodsList(param);
			result.setData(list);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询自定义物料列表：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" +",structId:"+structId+ "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 选择自定义物料
	 */
	@Override
	public Result selTmpGoods(String name, String kindCode, String page, String goodsIdStr, int structId, String billId,
			int userId, String userNm) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		param.put("structId", structId);
		param.put("userId", userId);
		param.put("userNm", userNm);
		try {
			if(billId.equals("-1")){//添加库存修正头表
				synchronized (this) {
					//生成单据号
					Integer maxCodeOrder=stockFixMapper.getMaxCodeOrder(param);
					if(maxCodeOrder==null){
						maxCodeOrder=1;
					}else{
						maxCodeOrder++;
					}
					String codeStr=String.valueOf(maxCodeOrder);
					if(codeStr.length()==1){
						codeStr="00"+codeStr;
					}else if(codeStr.length()==2){
						codeStr+="0"+codeStr;
					}
					String centerCode=commonMapper.getStructCodeById(param);
					String code="xz-"+centerCode+"-"+
							DateUtil.getDate(new Date(),"yyMMdd")+"-"+codeStr;
					param.put("code", code);
					param.put("codeOrder", maxCodeOrder);
					stockFixMapper.addStockFixH(param);
				}
			}
			String[] goodsIdArray=goodsIdStr.split(",");
			param.put("goodsIdArray",goodsIdArray);
			stockFixMapper.batchAddStockFixD(param);
			Map<String, Object> data=new HashMap<>();
			getStockFixDList(name, kindCode, param.get("billId").toString(), page, data);
			data.put("billId", param.get("billId"));
			result.setData(data);
		} catch (Exception e) {
			log.error("\r\n 选择自定义物料：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" +",name:"+name+",kindCode:"+kindCode
			+",page:"+page+",goodsIdStr:"+goodsIdStr+",structId:"+structId
			+",billId:"+billId + "\r\n\r\n");
			throw new RuntimeException();
		}
		return result;
	}

	/**
	 * 更新库存修正详细
	 */
	@Override
	public Result updateStockFixD(String goodsNum, String price, String stockFixDId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsNum", goodsNum);
		param.put("price", price);
		param.put("stockFixDId", stockFixDId);
		try {
			stockFixMapper.updateStockFixD(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 更新库存修正详细：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" +",goodsNum:"+goodsNum+",price:"+price
			+",stockFixDId:"+stockFixDId + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 处理库存修正单
	 */
	@Override
	public Result dealStockFix(String billId, int roleId, String reviewState,
			int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("billId", billId);
		param.put("reviewState", reviewState);
		param.put("structId", structId);
		try {
			stockFixMapper.dealStockFix(param);
			if(roleId==4&&reviewState.equals("2")){
				String billCode=stockFixMapper.getBillCodeById(param);
				param.put("billCode", billCode);
				stockFixMapper.addStockRecord(param);
				//影响库存
				stockFixMapper.updateStock(param);
			}
		} catch (Exception e) {
			log.error("\r\n 处理库存修正单：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" +",billId:"+billId+",roleId:"+roleId
			+",reviewState:"+reviewState + "\r\n\r\n");
			throw new RuntimeException();
		}
		return result;
	}
}
