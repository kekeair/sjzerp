package com.qxh.action.demand;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.constant.CodeConstant;
import com.qxh.model.DemandListD;
import com.qxh.model.User;
import com.qxh.service.DemandService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.MoneyUtil;
import com.qxh.utils.NumUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * 
 * @author Mr chen
 * @name : printSureBillAction
 * @todo : [打印销售单]
 * 
 * 创建时间 ： 2017年1月14日下午2:01:54
 */
public class printSureBillAction extends MainAction implements Controller{

	private DemandService demandService;
	
	public void setDemandService(DemandService demandService) {
		this.demandService = demandService;
	}

	/* 
	 * Todo : [打印销售单]
	 * @时间:2016年12月10日上午11:48:16
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		//获取参数值
		String customerId = request.getParameter("customerId");   
		String stimeM = request.getParameter("stimeM");
		String etimeM = request.getParameter("etimeM");
		User user = (User)SessionUtil.getSession().getAttribute("user");
		Result result =new Result();
		//通过条件查询集合
		List<DemandListD> list=demandService.getDemandBillByCustomerId(customerId,user.getStructId(),stimeM,etimeM);
		Double tatolCounts=0.00;                        //总钱数
		
		if(list==null || list.size()==0){
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			mav.setViewName("excelOut/printDemandBill");
			return CheckUtil.returnResult(mav, result.getCode(),result.getMsg(),result.getData());
		}
		
		String customNm = list.get(0).getCustomerNm();  //客户名称
		for (DemandListD demandListD : list) {
			tatolCounts += NumUtil.trim0(demandListD.getTotalMoney());
		}
		tatolCounts = NumUtil.trim0(tatolCounts);
		
		//==========定义集合前的死数据======================================
		DemandListD andCustomTeamTitle= new DemandListD();//大标题
		andCustomTeamTitle.setCode("-1"); 
		
		DemandListD andCustomTeamBill= new DemandListD();//出库单
		andCustomTeamBill.setCode("-2");  
		
		DemandListD andCustomTeamDate= new DemandListD(); //客户/日期
		andCustomTeamDate.setCode("-3"); 
		andCustomTeamDate.setCustomerNm(customNm);
		andCustomTeamDate.setBillDate(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
		
		DemandListD andCustomTeamTotalList= new DemandListD();//列标
		andCustomTeamTotalList.setCode("-4"); 
		
		DemandListD andCustomTeamTotalAllMoney= new DemandListD();//总计
		andCustomTeamTotalAllMoney.setCode("-5"); 
		andCustomTeamTotalAllMoney.setPrice(tatolCounts);
		andCustomTeamTotalAllMoney.setGoodsNm(MoneyUtil.toChinese(tatolCounts+""));//大写钱数
		
		DemandListD andCustomTeamSmallQianzi= new DemandListD();//签字
		andCustomTeamSmallQianzi.setCode("-7");
		//==========定义集合前的死数据======================================
		
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
		
		ArrayList<DemandListD> newList= new ArrayList<>();//定义一个新集合,用来装排列的数据
		int count=0;
		for (int i = 0; i < len; i++) {

			
			DemandListD andCustomTeamSmallMoney= new DemandListD();//小计
			andCustomTeamSmallMoney.setCode("-6");
			newList.add(andCustomTeamTitle);      //标题
			newList.add(andCustomTeamBill);	      //账单名称
			newList.add(andCustomTeamDate);	      //日期等格式
			newList.add(andCustomTeamTotalList);  //列的标题

			if(size<25){

				//小计
				double smillCount=0;
				for (int j = 0; j < size; j++) {
					DemandListD billAndCustomTeam = controlLen(list, count);//控制物料名称长度
					newList.add(billAndCustomTeam);
					smillCount += billAndCustomTeam.getTotalMoney();
					count++;
				}
				andCustomTeamSmallMoney.setPrice(smillCount);
			}else{
				//小计
				double smillCount=0;
				for (int j = 0; j < 25; j++) {
					DemandListD billAndCustomTeam = controlLen(list, count);//控制物料名称长度
					newList.add(billAndCustomTeam);//从list中获取每个元素添加到newlist集合中
					smillCount += billAndCustomTeam.getTotalMoney();
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
		
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("excelOut/printDemandBill");
		return CheckUtil.returnResult(mav, result.getCode(),result.getMsg(),result.getData());
	}

	
	
	/**
	 * @description : [控制物料名称的长度]
	 * @param list
	 * @param count
	 * @return
	 * @时间:2016年12月13日下午2:14:41
	 */
	private DemandListD controlLen(List<DemandListD> list, int count) {
		DemandListD demandListD = list.get(count);
		String goodsNm = demandListD.getGoodsNm();
		if(StringUtils.isNotEmpty(goodsNm)){
			int length = goodsNm.length();
			if(length>5){
				goodsNm = goodsNm.substring(0,5);
				demandListD.setGoodsNm(goodsNm);
			}
		}
		return demandListD;
	}
}
