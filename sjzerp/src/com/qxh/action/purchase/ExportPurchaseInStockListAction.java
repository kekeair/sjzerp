package com.qxh.action.purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.exmodel.CommonModel;
import com.qxh.model.PurchaseTotallModel;
import com.qxh.model.User;
import com.qxh.service.CommonService;
import com.qxh.service.PurchaseService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.MoneyUtil;
import com.qxh.utils.NumUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

public class ExportPurchaseInStockListAction extends MainAction implements Controller{

	private PurchaseService purchaseService;
	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
	private CommonService commonService;
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

		/* 
		 * Todo : [js导出打印]
		 * @时间:2016年12月10日上午11:48:16
		 */
		@Override
		public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
			ModelAndView mav = new ModelAndView();
			String billId = request.getParameter("billId");
			String supplierId = request.getParameter("supplierId");
			
			// 获取供应商
			String supplierName = null;
			Result result1 = purchaseService.getExportSupplier(billId);
			List<CommonModel> commonModel = (List<CommonModel>) result1.getData();
			for (int i = 0; i < commonModel.size(); i++) {
				int atNo = commonModel.get(i).getAtNo();
				int supplierId1 = Integer.parseInt(supplierId);
				if (atNo == supplierId1) {
					supplierName = commonModel.get(i).getName();//供应商名称
				}
			}
			// 获取数据列表
			User user = (User) SessionUtil.getSession().getAttribute("user");
			Map map = purchaseService.exportPurchaseTotallList(user.getStructId(), billId, supplierId);
			List<PurchaseTotallModel> list = (List<PurchaseTotallModel>) map.get("list");
		
			String code = list.get(0).getBillCode();			//单号
			Double tatolCounts=0.00;                        //总钱数
			Double allMoney = (Double) map.get("allMoney");// 获取总钱数
			tatolCounts = NumUtil.trim0(allMoney);
			
			//==========定义集合前的死数据======================================
			PurchaseTotallModel title = new PurchaseTotallModel();//大标题
			title.setCode("-1"); 
			
			PurchaseTotallModel title2 = new PurchaseTotallModel();//采购入库单
			title2.setCode("-2");  
			
			PurchaseTotallModel supplierAndBillAndDate = new PurchaseTotallModel(); //供应商/账单/日期
			supplierAndBillAndDate.setCode("-3"); 
			supplierAndBillAndDate.setSupplierNm(supplierName);
			supplierAndBillAndDate.setBillCode(code);
			supplierAndBillAndDate.setBillDate(list.get(0).getBillDate());
			
			PurchaseTotallModel totalList = new PurchaseTotallModel();//列标
			totalList.setCode("-4"); 
			PurchaseTotallModel totallAllMoney = new PurchaseTotallModel();//总计
			
			totallAllMoney.setCode("-5"); 
			totallAllMoney.setPrice(tatolCounts);
			totallAllMoney.setGoodsNm(MoneyUtil.toChinese(tatolCounts+""));
			
			PurchaseTotallModel qianzi = new PurchaseTotallModel();//签字
		     qianzi.setCode("-7");
			//==========定义集合前的死数据======================================
			
			Result result =new Result();
			int size = list.size();
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
			
			ArrayList<PurchaseTotallModel> newList= new ArrayList<>();
			int count=0;
			for (int i = 0; i < len; i++) {

				
				PurchaseTotallModel pageMoney = new PurchaseTotallModel();//小计
				pageMoney.setCode("-6");
				newList.add(title);      //标题
				newList.add(title2);	      //采购入库单
				newList.add(supplierAndBillAndDate);	      //日期等格式
				newList.add(totalList);  //列的标题

				if(size<25){
					//小计
					double smillCount = 0;
					//数量
					double smillNum = 0;
					for (int j = 0; j < size; j++) {
						PurchaseTotallModel purchaseTotallModel = controlLen(list, count);//控制物料名称长度
						newList.add(purchaseTotallModel);
						smillCount +=purchaseTotallModel.getMoney();
						smillNum += purchaseTotallModel.getOrderNum();
						count++;
					}
					pageMoney.setPrice(smillCount);
					pageMoney.setOrderNum(smillNum);
					pageMoney.setGoodsNm(MoneyUtil.toChinese(NumUtil.trim0(smillCount)+""));
				}else{
					//小计
					double smillCount=0;
					//数量
					double smillNum = 0;
					for (int j = 0; j < 25; j++) {
						PurchaseTotallModel purchaseTotallModel = controlLen(list, count);//控制物料名称长度
						newList.add(purchaseTotallModel);//从list中获取每个元素添加到newlist集合中
						smillCount += purchaseTotallModel.getMoney();
						smillNum += purchaseTotallModel.getOrderNum();
						count++;
					}
					pageMoney.setPrice(smillCount);
					pageMoney.setOrderNum(smillNum);
					pageMoney.setGoodsNm(MoneyUtil.toChinese(NumUtil.trim0(smillCount)+""));
					size=size-25;
				}
				newList.add(pageMoney);
				newList.add(totallAllMoney);
				newList.add(qianzi);
			}
			
			result.setData(newList);
			mav.setViewName("printInStock/printInStock");
			return CheckUtil.returnResult(mav, result.getCode(),result.getMsg(),result.getData());
		}

		/**
		 * @description : 控制物料名称的长度
		 * @param list
		 * @param count
		 * @return
		 */
		private PurchaseTotallModel controlLen(List<PurchaseTotallModel> list, int count) {
			 PurchaseTotallModel purchaseTotallModel = list.get(count);
			String goodsNm = purchaseTotallModel.getGoodsNm();
			int length = goodsNm.length();
			if(length>5){
				goodsNm = goodsNm.substring(0,5);
				purchaseTotallModel.setGoodsNm(goodsNm);
			}
			return purchaseTotallModel;
		}
	}
