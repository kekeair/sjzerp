package com.qxh.impl.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qxh.constant.CodeConstant;
import com.qxh.model.CenterGoods;
import com.qxh.model.DemandListD;
import com.qxh.model.Goods;
import com.qxh.model.PurchaseListD;
import com.qxh.model.PurchaseListH;
import com.qxh.model.Stock;
import com.qxh.model.StockRecord;
import com.qxh.model.Structure;
import com.qxh.model.SupplierGoods;
import com.qxh.model.TeamDemand;
import com.qxh.model.User;
import com.qxh.model.UserRel;
import com.qxh.service.TestService;
import com.qxh.utils.Result;

public class TestServiceImpl implements TestService{
	
	private static TestMapper testMapper;
	
	public void setTestMapper(TestMapper testMapper) {
		this.testMapper = testMapper;
	}
	

	private void insertStruct(){
		List<Structure> structureList=new ArrayList<>();
		List<DiningCenter> centerList=new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			Structure structure=new Structure();
			structure.setAtNo(i+2);
			structure.setpId(1);
			structure.setCode(String.valueOf(i+1));
			structure.setName("餐饮中心"+(i+1));
			structure.setLevelOrder(2);
			structure.setIsCenter(1);
			DiningCenter center=new DiningCenter();
			center.setAtNo(i+1);
			center.setStructId(i+2);
			center.setProvinceId(1);
			center.setCityId(3);
			center.setCountyId(6);
			center.setAddress("北京海淀");
			structureList.add(structure);
			centerList.add(center);
		}
		Map<String, Object> param=new HashMap<>();
		param.put("structureList", structureList);
		param.put("centerList", centerList);
		testMapper.insertStructure(param);
		testMapper.insertDiningCenter(param);
	}
	
	private void insertUser(){
		List<User> userList=new ArrayList<>();
		List<UserRel> relList=new ArrayList<>();
		List<SupplierAttr> supplierAttrList=new ArrayList<>();
		int count=30000;
		for (int i = 5000; i < 10000; i++) {
			User manager=new User();
			manager.setAtNo(i+count+2);
			manager.setUserAccount("manager"+(i+count+2));
			manager.setPwd("888888");
			manager.setUserNm("user"+(i+count+2));
			manager.setPhone("18255555555");
			manager.setSex(1);
			UserRel rel=new UserRel();
			rel.setAtNo(i+count+2);
			rel.setUserId(i+count+2);
			rel.setStructId(i+2);
			rel.setRoleId(4);
			
			User kuguan=new User();
			kuguan.setAtNo(i+count+3);
			kuguan.setUserAccount("kuguan"+(i+count+3));
			kuguan.setPwd("888888");
			kuguan.setUserNm("kuguan"+(i+count+3));
			kuguan.setPhone("15822000000");
			kuguan.setSex(1);
			UserRel rel1=new UserRel();
			rel1.setAtNo(i+count+3);
			rel1.setUserId(i+count+3);
			rel1.setStructId(i+2);
			rel1.setRoleId(5);
			
			User zuoyezu1=new User();
			zuoyezu1.setAtNo(i+count+4);
			zuoyezu1.setUserAccount("zuoyezu"+(i+count+4));
			zuoyezu1.setPwd("888888");
			zuoyezu1.setUserNm("zuoyezu"+(i+count+4));
			zuoyezu1.setPhone("15425852022");
			zuoyezu1.setSex(1);
			UserRel rel2=new UserRel();
			rel2.setAtNo(i+count+4);
			rel2.setUserId(i+count+4);
			rel2.setStructId(i+2);
			rel2.setRoleId(6);
			
			User zuoyezu2=new User();
			zuoyezu2.setAtNo(i+count+5);
			zuoyezu2.setUserAccount("zuoyezu"+(i+count+5));
			zuoyezu2.setPwd("888888");
			zuoyezu2.setUserNm("zuoyezu"+(i+count+5));
			zuoyezu2.setPhone("15425852022");
			zuoyezu2.setSex(1);
			UserRel rel3=new UserRel();
			rel3.setAtNo(i+count+5);
			rel3.setUserId(i+count+5);
			rel3.setStructId(i+2);
			rel3.setRoleId(6);
			
			User zuoyezu3=new User();
			zuoyezu3.setAtNo(i+count+6);
			zuoyezu3.setUserAccount("zuoyezu"+(i+count+6));
			zuoyezu3.setPwd("888888");
			zuoyezu3.setUserNm("zuoyezu"+(i+count+6));
			zuoyezu3.setPhone("15425852022");
			zuoyezu3.setSex(1);
			UserRel rel4=new UserRel();
			rel4.setAtNo(i+count+6);
			rel4.setUserId(i+count+6);
			rel4.setStructId(i+2);
			rel4.setRoleId(6);
			
			User caigouyuan=new User();
			caigouyuan.setAtNo(i+count+7);
			caigouyuan.setUserAccount("caigouyuan"+(i+count+7));
			caigouyuan.setPwd("888888");
			caigouyuan.setUserNm("caigouyuan"+(i+count+7));
			caigouyuan.setPhone("15425852022");
			caigouyuan.setSex(1);
			UserRel rel5=new UserRel();
			rel5.setAtNo(i+count+7);
			rel5.setUserId(i+count+7);
			rel5.setStructId(i+2);
			rel5.setRoleId(7);
			
			User gongyingshang=new User();
			caigouyuan.setAtNo(i+count+8);
			caigouyuan.setUserAccount("gongyingshang"+(i+count+8));
			caigouyuan.setPwd("888888");
			caigouyuan.setUserNm("gongyingshang"+(i+count+8));
			caigouyuan.setPhone("15425852022");
			caigouyuan.setSex(1);
			UserRel rel6=new UserRel();
			rel6.setAtNo(i+count+8);
			rel6.setUserId(i+count+8);
			rel6.setStructId(i+2);
			rel6.setRoleId(8);
			SupplierAttr supplierAttr=new SupplierAttr();
			supplierAttr.setUserId(i+count+8);
			supplierAttr.setProvinceId(1);
			supplierAttr.setCityId(3);
			supplierAttr.setCountyId(6);
			supplierAttr.setAddress("北京海淀");
			
			userList.add(manager);
			userList.add(kuguan);
			userList.add(zuoyezu1);
			userList.add(zuoyezu2);
			userList.add(zuoyezu3);
			userList.add(caigouyuan);
			userList.add(gongyingshang);
			
			relList.add(rel);
			relList.add(rel1);
			relList.add(rel2);
			relList.add(rel3);
			relList.add(rel4);
			relList.add(rel5);
			relList.add(rel6);
			
			supplierAttrList.add(supplierAttr);
			
			count=count+6;
		}
		Map<String, Object> param=new HashMap<>();
		param.put("userList", userList);
		param.put("relList", relList);
		param.put("supplierAttrList",supplierAttrList);
		testMapper.insertUser(param);
		testMapper.insertUserRel(param);
		testMapper.insertSupplierAttr(param);
	}
	
	private void addGoods(){
		List<Goods> goodsList=new ArrayList<>();
		List<GoodsDetail> detailList=new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			Goods goods=new Goods();
			goods.setAtNo(i+1);
			goods.setCode(String.valueOf(i+1));
			goods.setGoodsNm("物料"+(i+1));
			goods.setAlias("物料"+(i+1));
			goods.setKindId(15);
			goods.setBaseUnit(1);
			goods.setUnitNm("kg");
			GoodsDetail detail=new GoodsDetail();
			detail.setAtNo(i+1);
			detail.setGoodsId(i+1);
			goodsList.add(goods);
			detailList.add(detail);
		}
		Map<String, Object> param=new HashMap<>();
		param.put("goodsList", goodsList);
		param.put("detailList", detailList);
		testMapper.addGoods(param);
		testMapper.addGoodsDetail(param);
		goodsList.clear();
		detailList.clear();
	}
	
	private void addCenterGoods(){
		List<CenterGoods> centerGoodsList=new ArrayList<>();
		int count=0;
		int order=0;
		for (int i = 0; i < 10000; i++) {
			for (int j = 0; j < 100; j++) {
				CenterGoods goods=new CenterGoods();
				goods.setAtNo(order+1);
				goods.setCenterId(i+2);
				goods.setGoodsId(j+1);
				centerGoodsList.add(goods);
				count++;
				order++;
				if(count==10000){
					count=0;
				}
			}
			if(count%10000==0||i==9999){
				Map<String, Object> param=new HashMap<>();
				param.put("centerGoodsList", centerGoodsList);
				testMapper.addCenterGoods(param);
				centerGoodsList.clear();
				System.out.println(i);
			}
		}
	}

	private void addSupplierGoods(){
		List<SupplierGoods> list=new ArrayList<>();
		int count=0;
		int order=0;
		int supplierId=8;
		for (int i = 0; i < 10000; i++) {
			for (int j = 0; j < 100; j++) {
				SupplierGoods goods=new SupplierGoods();
				goods.setAtNo(order+1);
				goods.setCenterId(i+2);
				goods.setSupplierId(supplierId);
				goods.setGoodsId(j+1);
				list.add(goods);
				count++;
				order++;
				if(count==10000){
					count=0;
				}
			}
			supplierId+=7;
			if(count%10000==0||i==9999){
				Map<String, Object> param=new HashMap<>();
				param.put("list", list);
				testMapper.addSupplierGoods(param);
				list.clear();
				System.out.println(i);
			}
		}
	}
	
	private void addStock(){
		List<Stock> list=new ArrayList<>();
		int count=0;
		int order=0;
		for (int i = 0; i < 10000; i++) {
			for (int j = 0; j < 100; j++) {
				Stock stock=new Stock();
				stock.setAtNo(order+1);
				stock.setCenterId(i+2);
				stock.setGoodsId(j+1);
				stock.setStockNum(10);
				stock.setPrice(2);
				list.add(stock);
				count++;
				order++;
				if(count==10000){
					count=0;
				}
			}
			if(count%10000==0||i==9999){
				Map<String, Object> param=new HashMap<>();
				param.put("list", list);
				testMapper.addStock(param);
				list.clear();
				System.out.println(i);
			}
		}
	}
	
	private void addDemandListH(){
		List<DemandListH> list=new ArrayList<>();
		for (int i = 0; i < 4000; i++) {
			DemandListH bill=new DemandListH();
			bill.setAtNo(i+1);
			bill.setCode(String.valueOf(i+1));
			bill.setCodeOrder(i+1);
			bill.setCenterId(2);
			if(i<1000)
				bill.setReviewState(0);
			else if(i<2000)
				bill.setReviewState(1);
			else if(i<3000)
				bill.setReviewState(2);
			else if (i<4000) 
				bill.setReviewState(3);
			list.add(bill);
		}
		for (int i = 4000; i < 8000; i++) {
			DemandListH bill=new DemandListH();
			bill.setAtNo(i+1);
			bill.setCode(String.valueOf(i+1));
			bill.setCodeOrder(i+1);
			bill.setCenterId(3);
			if(i<5000)
				bill.setReviewState(0);
			else if(i<6000)
				bill.setReviewState(1);
			else if(i<7000)
				bill.setReviewState(2);
			else if (i<8000) 
				bill.setReviewState(3);
			list.add(bill);
		}
		Map<String, Object> param=new HashMap<>();
		param.put("list", list);
		testMapper.addDemandListH(param);
	}
	
	private void addTeamDemand(){
		List<TeamDemand> list=new ArrayList<>();
		int order=1;
		for (int i = 0; i < 8000; i++) {
			int teamId4=4,teamId5=5,teamId6=6;
			int teamId11=11,teamId12=12,teamId13=13;
			for (int j = 0; j < 3; j++) {
				TeamDemand demand=new TeamDemand();
				if(i<4000){
					demand.setAtNo(order);
					demand.setHeadAtNo(i+1);
					demand.setCenterId(2);
					if(j==0){
						demand.setCreatorId(teamId4);
						demand.setTeamId(teamId4);
					}else if(j==1){
						demand.setCreatorId(teamId5);
						demand.setTeamId(teamId5);
					}else{
						demand.setCreatorId(teamId6);
						demand.setTeamId(teamId6);
					}
					if(i<2000)
						demand.setReviewState(0);
					else
						demand.setReviewState(1);
				}else{
					demand.setAtNo(order);
					demand.setHeadAtNo(i+1);
					demand.setCenterId(3);
					if(j==0){
						demand.setCreatorId(teamId11);
						demand.setTeamId(teamId11);
					}else if(j==1){
						demand.setCreatorId(teamId12);
						demand.setTeamId(teamId12);
					}else{
						demand.setCreatorId(teamId13);
						demand.setTeamId(teamId13);
					}
					if(i<6000)
						demand.setReviewState(0);
					else
						demand.setReviewState(1);
				}
				order++;
				list.add(demand);
			}
		}
		Map<String, Object> param=new HashMap<>();
		param.put("list", list);
		testMapper.addTeamDemand(param);
	}
	
	private void addDemandListD(){
		List<DemandListD> list=new ArrayList<>();
		int order=1;
		int goodsOrder=1;
		for (int i = 0; i < 24000; i++) {
			for (int j = 0; j < 2; j++) {
				DemandListD detail=new DemandListD();
				detail.setDetailId(order);
				detail.setHeadAtNo(i+1);
				detail.setGoodsId(goodsOrder);
				detail.setDemandNum(2);
				goodsOrder++;
				order++;
				if(goodsOrder==100)
					goodsOrder=0;
				list.add(detail);
			}
		}
		Map<String, Object> param=new HashMap<>();
		param.put("list", list);
		testMapper.addDemandListD(param);
	}
	
	private void addPurchaseListH(){
		List<PurchaseListH> list=new ArrayList<>();
		for (int i = 0; i < 4000; i++) {
			PurchaseListH bill=new PurchaseListH();
			bill.setBillId(i+1);
			bill.setCode(String.valueOf(i+1));
			bill.setCodeOrder(i+1);
			bill.setCenterId(2);
			if(i<800)
				bill.setReviewState(0);
			else if(i<1400)
				bill.setReviewState(1);
			else if(i<2000)
				bill.setReviewState(2);
			else if (i<2600) 
				bill.setReviewState(3);
			else if(i<3300)
				bill.setReviewState(4);
			else if(i<4000)
				bill.setReviewState(5);
			list.add(bill);
		}
		for (int i = 4000; i < 8000; i++) {
			PurchaseListH bill=new PurchaseListH();
			bill.setBillId(i+1);
			bill.setCode(String.valueOf(i+1));
			bill.setCodeOrder(i+1);
			bill.setCenterId(3);
			if(i<4800)
				bill.setReviewState(0);
			else if(i<5400)
				bill.setReviewState(1);
			else if(i<6000)
				bill.setReviewState(2);
			else if (i<6600) 
				bill.setReviewState(3);
			else if(i<7300)
				bill.setReviewState(4);
			else if(i<8000)
				bill.setReviewState(5);
			list.add(bill);
		}
		Map<String, Object> param=new HashMap<>();
		param.put("list", list);
		testMapper.addPurchaseListH(param);
	}
	
	private void addPurchaseListD(){
		List<PurchaseListD> list=new ArrayList<>();
		int order=1;
		int teamId4=4,teamId5=5,teamId6=6;
		int teamId11=11,teamId12=12,teamId13=13;
		int supplierId8=8,supplierId15=15;
		int goodsOrder=1;
		for (int i = 0; i < 4000; i++) {//头表
			for (int j = 0; j < 3; j++) {//组
				for (int k = 0; k < 2; k++) {//物料
					PurchaseListD detail=new PurchaseListD();
					detail.setAtNo(order);
					detail.setHeadAtNo(i+1);
					detail.setCenterId(2);
					if(j==0)
						detail.setTeamId(teamId4);
					else if(j==1)
						detail.setTeamId(teamId5);
					else if(j==2)
						detail.setTeamId(teamId6);
					detail.setGoodsId(goodsOrder);
					detail.setSupplierId(supplierId8);
					detail.setOrderNum(2);
//					detail.setActualNum(3);
					detail.setPrice(5);
//					detail.setTotalInNum(10);
					list.add(detail);
					order++;
					goodsOrder++;
					if(goodsOrder==100)
						goodsOrder=0;
				}
			}
		}
		
		for (int i = 4000; i < 8000; i++) {//头表
			for (int j = 0; j < 3; j++) {//组
				for (int k = 0; k < 2; k++) {//物料
					PurchaseListD detail=new PurchaseListD();
					detail.setAtNo(order);
					detail.setHeadAtNo(i+1);
					detail.setCenterId(3);
					if(j==0)
						detail.setTeamId(teamId11);
					else if(j==1)
						detail.setTeamId(teamId12);
					else if(j==2)
						detail.setTeamId(teamId13);
					detail.setGoodsId(goodsOrder);
					detail.setSupplierId(supplierId15);
					detail.setOrderNum(2);
//					detail.setActualNum(3);
					detail.setPrice(5);
//					detail.setTotalInNum(10);
					list.add(detail);
					order++;
					goodsOrder++;
					if(goodsOrder==100)
						goodsOrder=0;
				}
			}
		}
		Map<String, Object> param=new HashMap<>();
		param.put("list", list);
		testMapper.addPurchaseListD(param);
	}
	
	private void addStockFixH(){
		List<StockFixH> list=new ArrayList<>();
		for (int i = 0; i < 4000; i++) {
			StockFixH bill=new StockFixH();
			bill.setAtNo(i+1);
			bill.setCode(String.valueOf(i+1));
			bill.setCodeOrder(i+1);
			bill.setCenterId(2);
			bill.setLauncher(3);
			bill.setLauncherNm("kuguan3");
			if(i<1300)
				bill.setReviewState(0);
			else if(i<2600)
				bill.setReviewState(1);
			else if(i<4000)
				bill.setReviewState(2);
			list.add(bill);
		}
		for (int i = 4000; i < 8000; i++) {
			StockFixH bill=new StockFixH();
			bill.setAtNo(i+1);
			bill.setCode(String.valueOf(i+1));
			bill.setCodeOrder(i+1);
			bill.setCenterId(3);
			bill.setLauncher(10);
			bill.setLauncherNm("kuguan10");
			if(i<5300)
				bill.setReviewState(0);
			else if(i<6600)
				bill.setReviewState(1);
			else if(i<8000)
				bill.setReviewState(2);
			list.add(bill);
		}
		Map<String, Object> param=new HashMap<>();
		param.put("list", list);
		testMapper.addStockFixH(param);
	}
	
	private void addStockFixD(){
		List<StockFixD> list=new ArrayList<>();
		int order=1;
		int goodsOrder=1;
		int tmpGoodsOrder1=1,tmpGoodsOrder2=1001;
		for (int i = 0; i < 8000; i++) {//头表
			for (int j = 0; j < 2; j++) {//物料
				StockFixD detail=new StockFixD();
				detail.setAtNo(order);
				detail.setHeadAtNo(i+1);
				if(i<4000){
					if(j%2==0){
						detail.setGoodsId(goodsOrder);
						detail.setGoodsType(0);
						goodsOrder++;
					}else{
						detail.setGoodsId(tmpGoodsOrder1);
						detail.setGoodsType(1);
						tmpGoodsOrder1++;
					}
				}else{
					if(j%2==0){
						detail.setGoodsId(goodsOrder);
						detail.setGoodsType(0);
						goodsOrder++;
					}else{
						detail.setGoodsId(tmpGoodsOrder2);
						detail.setGoodsType(1);
						tmpGoodsOrder2++;
					}
				}
				detail.setGoodsNum(8);
				detail.setPrice(24);
				list.add(detail);
				order++;
				if(goodsOrder==100)
					goodsOrder=0;
				if(tmpGoodsOrder1==1000)
					tmpGoodsOrder1=1;
				if(tmpGoodsOrder2==2000)
					tmpGoodsOrder2=1001;
			}
		}
		Map<String, Object> param=new HashMap<>();
		param.put("list", list);
		testMapper.addStockFixD(param);
	}
	
	private void addTmpGoods(){
		List<Goods> list=new ArrayList<>();
		for (int i = 0; i < 2000; i++) {
			Goods goods=new Goods();
			goods.setAtNo(i+1);
			if(i<1000)
				goods.setAssistUnit(2);
			else
				goods.setAssistUnit(3);
			goods.setCode(String.valueOf(i+1));
			goods.setGoodsNm("临时物料"+(i+1));
			goods.setBaseUnit(1);
			goods.setUnitNm("kg");
			list.add(goods);
		}
		Map<String, Object> param=new HashMap<>();
		param.put("list", list);
		testMapper.addTmpGoods(param);
	}
	
	private void addStock1(){
		List<Stock> list=new ArrayList<>();
		int order=1000001;
		for (int i = 0; i < 2000; i++) {
			Stock stock=new Stock();
			stock.setAtNo(order);
			if(i<1000)
				stock.setCenterId(2);
			else
				stock.setCenterId(3);
			stock.setGoodsId(i+1);
			stock.setStockNum(10);
			stock.setPrice(2);
			list.add(stock);
			order++;
		}
		Map<String, Object> param=new HashMap<>();
		param.put("list", list);
		testMapper.addStock1(param);
	}
	
	private void addStockRecord(){
		List<StockRecord> list=new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			StockRecord stock=new StockRecord();
			stock.setAtNo(i+1);
			stock.setCenterId(2);
			stock.setBillId(i+1);
			stock.setBillCode(String.valueOf(i+1));
			stock.setGoodsId(1);
			stock.setGoodsNm("物料1");
			stock.setGoodsType(0);
			stock.setAcrossNum(i+1);
			stock.setAcrossPrice(i+1);
			stock.setStockNum(i+1);
			stock.setStockPrice(i+1);
			list.add(stock);
			if(i%40000==0||i==100000-1){
				Map<String, Object> param=new HashMap<>();
				param.put("list", list);
				testMapper.addStockRecord(param);
				list.clear();
			}
		}
		
	}
	
	@Override
	public Result test() {
		Result result=new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		try {
			addStockRecord();
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("失败");
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
		return result;
	}

	/* 
	 * Todo : []
	 * @时间:2017年1月5日上午10:32:19
	 */
	@Override
	public Result testJedis() {
		return null;
	}

}
