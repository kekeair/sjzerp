package com.qxh.impl.purchaseTotalD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.PurchaseTotalCustomer;
import com.qxh.exmodel.PurchaseTotalD;
import com.qxh.exmodel.PurchaseTotalGoods;
import com.qxh.service.PurchaseTotalDService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.Result;

public class PurchaseTotalDServiceImpl implements PurchaseTotalDService {

	Logger log = Logger.getLogger(this.getClass());
	private PurchaseTotalDMapper purchaseTotalDMapper;
	
	public void setPurchaseTotalDMapper(PurchaseTotalDMapper purchaseTotalDMapper) {
		this.purchaseTotalDMapper = purchaseTotalDMapper;
	}

	/**
	 * 采购汇总明细
	 */
	@Override
	public Result getPurchaseTotalD(String stime, String etime, int structId,
			String supplierId,String customerId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("stime", stime);
		param.put("etime", etime);
		param.put("structId", structId);
		param.put("supplierId", supplierId);
		param.put("customerId", customerId);
		try {
			Map<String, Object> data=new HashMap<>();
			List<PurchaseTotalD> list = purchaseTotalDMapper.getPurchaseTotalD(param);
			if(list!=null&&list.size()>0){
				int l=list.size();
				List<PurchaseTotalCustomer> customerList=purchaseTotalDMapper.getCustomerList(param);
				List<PurchaseTotalGoods> goodsList=new ArrayList<>();
				int preGoodsId=0;
				double totalNum=0;
				LinkedHashMap<Integer, Double> customerMap=list2Map(customerList);
				for (int i = 0; i < l; i++) {
					PurchaseTotalD detail=list.get(i);
					if(i==0){
						//设置新物料并加入到物料列表中
						preGoodsId=detail.getGoodsId();
						PurchaseTotalGoods goods=new PurchaseTotalGoods();
						goods.setGoodsId(detail.getGoodsId());
						goods.setGoodsNm(detail.getGoodsNm());
						customerMap.put(detail.getCustomerId(),detail.getOrderNum());
						goodsList.add(goods);
						totalNum+=detail.getOrderNum();
					}else{
						int nowGoodsId=detail.getGoodsId();
						if(nowGoodsId==preGoodsId&&detail.getGoodsType()==0){
							totalNum+=detail.getOrderNum();
							//设置需给单位购买的物料数量
							customerMap.put(detail.getCustomerId(),detail.getOrderNum());
						}else{
							//先把之前的物料的客户列表转化
							goodsList.get(goodsList.size()-1).setcList(
									new ArrayList<>(customerMap.values()));
							goodsList.get(goodsList.size()-1).setTotalNum(totalNum);
							//初始化合计
							totalNum=0;
							totalNum+=detail.getOrderNum();
							//初始化map
							initCustomerMap(customerMap);
							//设置新物料并加入到物料列表中
							preGoodsId=detail.getGoodsId();
							PurchaseTotalGoods goods=new PurchaseTotalGoods();
							goods.setGoodsId(detail.getGoodsId());
							goods.setGoodsNm(detail.getGoodsNm());
							customerMap.put(detail.getCustomerId(),detail.getOrderNum());
							goodsList.add(goods);
						}
					}
					if(i==l-1){
						//最后一个物料
						goodsList.get(goodsList.size()-1).setcList(
								new ArrayList<>(customerMap.values()));
						goodsList.get(goodsList.size()-1).setTotalNum(totalNum);
					}
				}
				data.put("goodsList", goodsList);
				data.put("customerList", customerList);
			}
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 采购汇总明细：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" 
					+ ",stime:" + stime + ",etime:" + etime + "\r\n\r\n");
			e.printStackTrace();
		}
		return result;
	}

	private LinkedHashMap<Integer, Double> list2Map(
			List<PurchaseTotalCustomer> customerList){
		LinkedHashMap<Integer, Double> customerMap=new LinkedHashMap<>();
		int l=customerList.size();
		for (int i = 0; i < l; i++) {
			PurchaseTotalCustomer customer=customerList.get(i);
			customerMap.put(customer.getCustomerId(), customer.getOrderNum());
		}
		return customerMap;
	}
	
	private void initCustomerMap(Map<Integer, Double> customerMap){
		for(Map.Entry<Integer, Double> entry : customerMap.entrySet()) {
			customerMap.put(entry.getKey(), 0.0);
		} 
	}
	
}
