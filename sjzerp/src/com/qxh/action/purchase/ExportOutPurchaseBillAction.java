package com.qxh.action.purchase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.exmodel.BillAndCustomTeam;
import com.qxh.service.PurchaseService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * 
 * @author Mr chen
 * @name : ExportOutPurchaseBillAction
 * @todo : [js打印采购明细单]
 * 
 * 创建时间 ： 2016年12月13日下午5:33:53
 */
public class ExportOutPurchaseBillAction extends MainAction implements Controller{

	private PurchaseService purchaseService;
	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	
	/* 
	 * Todo : [js导出打印]
	 * @时间:2016年12月10日上午11:48:16
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		//获取参数值
		String teamBillId = request.getParameter("teamBillId");   
		String billId = request.getParameter("bill_Id");
	
		//通过条件查询集合
		List<BillAndCustomTeam> list = purchaseService.getExportOutPurchaseBill(teamBillId,billId);
		
		int n = 1;//为addList集合定义序号标记
		ArrayList<BillAndCustomTeam> addList= new ArrayList<BillAndCustomTeam>();//该集合用来存取通过条件查询的供应商的各个集合
		
		separateList(list, n, addList);//将集合中不同的供应商分别装进map集合中
		
		String customNm = list.get(0).getCustomNm();	//客户名称
		if(StringUtils.isEmpty(customNm)){
			customNm="自采";
		}
		String teamNm = list.get(0).getTeamNm();    	//工作组名称
		String code = list.get(0).getCodeNm();			//单号
		
		//==========定义集合前的死数据======================================
		BillAndCustomTeam andCustomTeamTitle= new BillAndCustomTeam();//大标题
		andCustomTeamTitle.setCode("-1"); 
		BillAndCustomTeam andCustomTeamDate= new BillAndCustomTeam(); //客户/账单/日期
		andCustomTeamDate.setCode("-3"); 
		andCustomTeamDate.setCustomNm(customNm);
		andCustomTeamDate.setCodeNm(code);
		andCustomTeamDate.setBillDate(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
		BillAndCustomTeam andCustomTeamTotalList= new BillAndCustomTeam();//列标
		andCustomTeamTotalList.setCode("-4"); 
		//==========定义集合前的死数据======================================
		
		Result result =new Result();
		int size = addList.size();
		int len=0;//定义循环次数
		if(size<25){
			len=1;
		}else{
			if(size%25 !=0){
				len=size/25+1;
			}else{
				len=size/25;
			}
		}
		try {
			ArrayList<BillAndCustomTeam> newList= new ArrayList<>();//定义一个新集合,用来装排列的数据
			int count=0;
			for (int i = 0; i < len; i++) {
				
				BillAndCustomTeam andCustomTeamBill= new BillAndCustomTeam();//采购明细单
				andCustomTeamBill.setCode("-2"); 
				if(addList.size()>0 && addList != null){
					andCustomTeamBill.setUserNm(addList.get(count).getUserNm());
					
				}else{
					andCustomTeamBill.setUserNm("自采");
				}
				newList.add(andCustomTeamTitle);      //标题
				newList.add(andCustomTeamBill);	      //账单名称
				newList.add(andCustomTeamDate);	      //日期等格式
				newList.add(andCustomTeamTotalList);  //列的标题
				if(size<25){
					for (int j = 0; j < size; j++) {
						BillAndCustomTeam billAndCustomTeam = controlLen(addList, count);//控制物料名称长度
					
						newList.add(billAndCustomTeam);
						count++;
					}
				}else{
					for (int j = 0; j < 25; j++) {
						BillAndCustomTeam billAndCustomTeam = controlLen(addList, count);//控制物料名称长度
						newList.add(billAndCustomTeam);//从list中获取每个元素添加到newlist集合中
						count++;
					}
					size=size-25;
				}
			}
			result.setData(newList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.setViewName("excelOut/excelOutPurchase");
		return CheckUtil.returnResult(mav, result.getCode(),result.getMsg(),result.getData());
	}


	//========================调用方法================================================================
	
	
	/**
	 * @description : [//将集合中不同的供应商分别装进map集合中]
	 * @param list
	 * @param n
	 * @param addList
	 * @时间:2016年12月14日下午2:14:49
	 */
	private void separateList(List<BillAndCustomTeam> list, int n, ArrayList<BillAndCustomTeam> addList) {
		int l=list.size();
		int preSupplierId=0;
		int nowSupplierId=0;
		Map<Integer, List<BillAndCustomTeam>> listMap=new HashMap<>();
		for (int i = 0; i < l; i++) {
			
			BillAndCustomTeam goods=list.get(i);
			if(i==0){
				preSupplierId=goods.getAtNo();
				nowSupplierId=preSupplierId;
				List<BillAndCustomTeam> supplierList=new ArrayList<>();
				supplierList.add(goods);
				listMap.put(preSupplierId, supplierList);
			}else{
				nowSupplierId=goods.getAtNo();
				if(nowSupplierId==preSupplierId){
					List<BillAndCustomTeam> supplierList=listMap.get(nowSupplierId);
					supplierList.add(goods);
				}else{
					int len=0;
					List<BillAndCustomTeam> BillAndCustomTeamList = listMap.get(preSupplierId);
					int size = BillAndCustomTeamList.size();
					if(size<=25){
						len= 25-size;
					}else if(size%25 != 0){
						len = (size/25+1)*25-size;
					}
					if(len !=0){
						for (int j = 0; j < len; j++) {
							BillAndCustomTeamList.add(new BillAndCustomTeam());
						}
					}
					for (int j = 0; j < BillAndCustomTeamList.size(); j++) {
						BillAndCustomTeam billAndCustomTeam = BillAndCustomTeamList.get(j);
						billAndCustomTeam.setOrderIndex(n);
						n++;
						addList.add(billAndCustomTeam);
					}
					//重置preSupplierId
					preSupplierId=nowSupplierId;
					List<BillAndCustomTeam> supplierList=new ArrayList<>();
					supplierList.add(goods);
					listMap.put(nowSupplierId, supplierList);
				}
			}
			if(i==l-1){
				int len=0;
				List<BillAndCustomTeam> BillAndCustomTeamList = listMap.get(nowSupplierId);
				int size =0;
				if(BillAndCustomTeamList!=null){
					size = BillAndCustomTeamList.size();
				}
				
				
				if(size<=25){
					len= 25-size;
				}else if(size%25 != 0){
					len = (size/25+1)*25-size;
				}
				if(len !=0){
					for (int j = 0; j < len; j++) {
						BillAndCustomTeamList.add(new BillAndCustomTeam());
					}
				}
				
				for (int j = 0; j < BillAndCustomTeamList.size(); j++) {
					BillAndCustomTeam billAndCustomTeam = BillAndCustomTeamList.get(j);
					billAndCustomTeam.setOrderIndex(n);
					n++;
					addList.add(billAndCustomTeam);
				}
			}
		}
	}

	
	
	/**
	 * @description : [控制物料名称的长度]
	 * @param list
	 * @param count
	 * @return
	 * @时间:2016年12月13日下午2:14:41
	 */
	private BillAndCustomTeam controlLen(List<BillAndCustomTeam> list, int count) {
		BillAndCustomTeam billAndCustomTeam = list.get(count);
		
		if(billAndCustomTeam!=null){
			String goodsNm = billAndCustomTeam.getGoodsNm();
			if(StringUtils.isNotEmpty(goodsNm)){
				int length = goodsNm.length();
				if(length>5){
					goodsNm = goodsNm.substring(0,5);
					billAndCustomTeam.setGoodsNm(goodsNm);
				}
			}
			
		}
		return billAndCustomTeam;
	}
}
