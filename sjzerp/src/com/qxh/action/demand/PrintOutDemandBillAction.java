package com.qxh.action.demand;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.exmodel.BillAndCustomTeam;
import com.qxh.service.DemandService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.MoneyUtil;
import com.qxh.utils.NumUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : PrintOutDemandBillAction
 * @todo : [出库单详情]
 * 
 * 创建时间 ： 2016年12月27日下午2:43:52
 */
public class PrintOutDemandBillAction extends MainAction implements Controller{
	
	private DemandService demandService;
	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}
	/* 
	 * Todo : [出库单详情]
	 * @时间:2016年12月27日下午2:45:18
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		//获取参数值
		String billId = request.getParameter("billId");     //账单id
		String customerTeamId = request.getParameter("customerTeamId");			//作业组ID
		String numList = request.getParameter("numList");
		String[] numArray = numList.split(",");//物料的数量
		
		//通过条件查询集合
		List<BillAndCustomTeam> list=demandService.getExportOutDemandBill(billId,customerTeamId);
		String customNm = list.get(0).getCustomNm();	//客户名称
		String teamNm = list.get(0).getTeamNm();    	//工作组名称
		String code = list.get(0).getCodeNm();			//单号
		Double tatolCounts=0.00;                        //总钱数
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setDemandNum(numArray[i]);
			tatolCounts +=NumUtil.trim0(list.get(i).getTotalMonay());
		}
		tatolCounts = NumUtil.trim0(tatolCounts);
		
		//==========定义集合前的死数据======================================
		BillAndCustomTeam andCustomTeamTitle= new BillAndCustomTeam();//大标题
		andCustomTeamTitle.setCode("-1"); 
		
		BillAndCustomTeam andCustomTeamBill= new BillAndCustomTeam();//出库单
		andCustomTeamBill.setCode("-2");  
		
		BillAndCustomTeam andCustomTeamDate= new BillAndCustomTeam(); //客户/账单/日期
		andCustomTeamDate.setCode("-3"); 
		andCustomTeamDate.setCustomNm(customNm+"/"+teamNm);
		andCustomTeamDate.setCodeNm(code);
		andCustomTeamDate.setBillDate(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
		
		BillAndCustomTeam andCustomTeamTotalList= new BillAndCustomTeam();//列标
		andCustomTeamTotalList.setCode("-4"); 
		BillAndCustomTeam andCustomTeamTotalAllMoney= new BillAndCustomTeam();//总计
		
		andCustomTeamTotalAllMoney.setCode("-5"); 
		andCustomTeamTotalAllMoney.setPrice(tatolCounts);
		andCustomTeamTotalAllMoney.setGoodsNm(MoneyUtil.toChinese(tatolCounts+""));
		
		BillAndCustomTeam andCustomTeamSmallQianzi= new BillAndCustomTeam();//签字
		andCustomTeamSmallQianzi.setCode("-7");
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
		
		ArrayList<BillAndCustomTeam> newList= new ArrayList<>();//定义一个新集合,用来装排列的数据
		int count=0;
		for (int i = 0; i < len; i++) {
			
			BillAndCustomTeam andCustomTeamSmallMoney= new BillAndCustomTeam();//小计
			andCustomTeamSmallMoney.setCode("-6");
			newList.add(andCustomTeamTitle);      //标题
			newList.add(andCustomTeamBill);	      //账单名称
			newList.add(andCustomTeamDate);	      //日期等格式
			newList.add(andCustomTeamTotalList);  //列的标题

			if(size<25){

				//小计
				double smillCount=0;
				for (int j = 0; j < size; j++) {
					BillAndCustomTeam billAndCustomTeam = controlLen(list, count);//控制物料名称长度
					newList.add(billAndCustomTeam);
					smillCount += billAndCustomTeam.getTotalMonay();
					count++;
				}
				andCustomTeamSmallMoney.setPrice(smillCount);
			}else{
				//小计
				double smillCount=0;
				for (int j = 0; j < 25; j++) {
					BillAndCustomTeam billAndCustomTeam = controlLen(list, count);//控制物料名称长度
					newList.add(billAndCustomTeam);//从list中获取每个元素添加到newlist集合中
					smillCount += billAndCustomTeam.getTotalMonay();
					count++;
				}
				andCustomTeamSmallMoney.setPrice(smillCount);
				size=size-25;
			}
			newList.add(andCustomTeamSmallMoney);
			newList.add(andCustomTeamTotalAllMoney);
			newList.add(andCustomTeamSmallQianzi);
		}
		
		result.setData(newList);
		mav.setViewName("excelOut/excelOutDemand");
		return CheckUtil.returnResult(mav, result.getCode(),result.getMsg(),result.getData());
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
		String goodsNm = billAndCustomTeam.getGoodsNm();
		int length = goodsNm.length();
		if(length>5){
			goodsNm = goodsNm.substring(0,5);
			billAndCustomTeam.setGoodsNm(goodsNm);
		}
		return billAndCustomTeam;
	}

}
