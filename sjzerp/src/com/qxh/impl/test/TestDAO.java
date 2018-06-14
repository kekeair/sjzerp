package com.qxh.impl.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qxh.impl.purchase.PurchaseMapper;
import com.qxh.model.PurchaseTotallModel;
import com.qxh.service.PurchaseService;

public class TestDAO {
	private PurchaseService purchaseService;
	
	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
@Test
public void test1(){
	ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:config/applicationContext.xml");
	PurchaseMapper bean = (PurchaseMapper)ctx.getBean("PurchaseMapper");
	Map<String, Object> param = new HashMap<>();
	param.put("centerId", 2);
	param.put("code","cg-0101-161122-001");
	param.put("atNo",32);
	
	/*param.put("page", 1);
	param.put("pageSize", 1);*/
	List<PurchaseTotallModel> purchaseTotalList = bean.selectPurchaseTotalList(param);
  System.out.println(purchaseTotalList);
}
@Test
public void test2(){
	ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:config/applicationContext.xml");
	PurchaseMapper bean = (PurchaseMapper)ctx.getBean("PurchaseMapper");
	Map<String, Object> param = new HashMap<>();
	param.put("centerId", 2);
	param.put("code","cg-0101-161122-001");
	param.put("atNo", 32);
	//param.put("page", 1);
	//param.put("pageSize", 7);
	
	Integer selectPurchaseTotalListCount = bean.selectPurchaseTotalListCount(param);
  System.out.println(selectPurchaseTotalListCount);
}
}
