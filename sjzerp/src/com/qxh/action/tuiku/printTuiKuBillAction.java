package com.qxh.action.tuiku;

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
import com.qxh.exmodel.BaseGoods;
import com.qxh.exmodel.BillAndCustomTeam;
import com.qxh.model.TuikuH;
import com.qxh.model.User;
import com.qxh.service.DemandService;
import com.qxh.service.TuikuService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.MoneyUtil;
import com.qxh.utils.NumUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * 
 * @Description:[退库打印]
 * 
 * @author:kekeair
 * 
 * @time:2017年3月2日 下午4:32:54
 * 
 */
public class printTuiKuBillAction extends MainAction implements Controller {

	private TuikuService tuikuService;

	public void setTuikuService(TuikuService tuikuService) {
		this.tuikuService = tuikuService;
	}

	/*
	 * Todo : [js导出打印]
	 * 
	 * @时间:2016年12月10日上午11:48:16
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		// 获取参数值
		String billId = request.getParameter("billId"); // 订单Id
		// 通过条件查询集合List<BaseGoods>
		Result result = tuikuService.viewTuikuD(billId);
		Map<String,Object> map = (HashMap<String, Object>) result.getData();
		List<BaseGoods> list = (List<BaseGoods>)map.get("list");// 获取退库单中物料列表 
		TuikuH head = (TuikuH)map.get("head");//获取退库单信息


		String customNm = head.getCustomerNm();// 客户名称
		String code = head.getCode(); // 单号
		String billDate = head.getBillDate().substring(0, 10); // 日期
		Double tatolCounts = 0.00; // 总钱数
		tatolCounts = NumUtil.trim0(tatolCounts);

		// ==========定义集合前的死数据======================================
		BaseGoods token = new BaseGoods();// 品牌
		token.setSupplierNm("-1");
		BaseGoods title = new BaseGoods();// 退库单
		title.setSupplierNm("-2");
		BaseGoods cNameAndBillCodeAndDate = new BaseGoods();
		cNameAndBillCodeAndDate.setSupplierNm("-3");
		cNameAndBillCodeAndDate.setCustomerNm(customNm);
		cNameAndBillCodeAndDate.setCode(code);
		cNameAndBillCodeAndDate.setBillDate(billDate);
		BaseGoods totalList = new BaseGoods();
		totalList.setSupplierNm("-4");

		/*
		 * BillAndCustomTeam andCustomTeamTotalAllMoney = new
		 * BillAndCustomTeam();// 总计
		 * 
		 * andCustomTeamTotalAllMoney.setCode("-5");
		 * andCustomTeamTotalAllMoney.setPrice(tatolCounts);
		 * andCustomTeamTotalAllMoney.setGoodsNm(MoneyUtil.toChinese(tatolCounts
		 * + ""));
		 */
		BaseGoods totalmoney = new BaseGoods();
		totalmoney.setSupplierNm("-5");
		totalmoney.setPrice(tatolCounts);// ??
		/*
		 * BillAndCustomTeam andCustomTeamSmallQianzi = new
		 * BillAndCustomTeam();// 签字 andCustomTeamSmallQianzi.setCode("-7");
		 */
		BaseGoods qianzi = new BaseGoods();
		qianzi.setSupplierNm("-7");
		// ==========定义集合前的死数据======================================

		Result result1 = new Result();
		int size = list.size();
		int len = 0;// 定义循环次数
		if (size < 25) {
			len = 1;
		} else {
			if (size % 25 != 0) {
				len = size / 25 + 1;
			} else {
				len = size / 25;
			}
		}

		ArrayList<BaseGoods> newList = new ArrayList<>();// 定义一个新集合,用来装排列的数据
		int count = 0;
		for (int i = 0; i < len; i++) {

			/*
			 * BillAndCustomTeam andCustomTeamSmallMoney = new
			 * BillAndCustomTeam();// 小计 andCustomTeamSmallMoney.setCode("-6");
			 * newList.add(andCustomTeamTitle); // 标题
			 * newList.add(andCustomTeamBill); // 账单名称
			 * newList.add(andCustomTeamDate); // 日期等格式
			 * newList.add(andCustomTeamTotalList); // 列的标题
			 */
			BaseGoods smallMoney = new BaseGoods();
			smallMoney.setSupplierNm("-6");
			newList.add(token);
			newList.add(title);
			newList.add(cNameAndBillCodeAndDate);
			newList.add(totalList);
			if (size < 25) {

				// 小计
				double smillCount = 0;
				double smillNum = 0;
				for (int j = 0; j < size; j++) {
					BaseGoods baseGoods = controlLen(list, count);// 控制物料名称长度
					newList.add(baseGoods);
					smillCount += baseGoods.getMoney();
					smillNum += baseGoods.getNum();
					count++;
				}
				smallMoney.setPrice(smillCount);
				smallMoney.setNum(smillNum);
				smallMoney.setGoodsNm(MoneyUtil.toChinese(NumUtil.trim0(smillCount) + ""));
			} else {
				// 小计
				double smillCount = 0;
				double smillNum = 0;
				for (int j = 0; j < 25; j++) {
					BaseGoods baseGoods = controlLen(list, count);// 控制物料名称长度
					newList.add(baseGoods);// 从list中获取每个元素添加到newlist集合中
					smillCount += baseGoods.getMoney();
					smillNum += baseGoods.getNum();
					count++;
				}
				smallMoney.setPrice(smillCount);
				smallMoney.setNum(smillNum);
				smallMoney.setGoodsNm(MoneyUtil.toChinese(NumUtil.trim0(smillCount) + ""));
				size = size - 25;
			}
			newList.add(smallMoney);
			newList.add(totalmoney);
			newList.add(qianzi);
		}

		result.setData(newList);
		User user = (User) SessionUtil.getSession().getAttribute("user");
		mav.addObject("roleId", user.getRoleId());
		mav.setViewName("tuiku/printTuiKuView");
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(), result.getData());
	}

	/**
	 * @description : [控制物料名称的长度]
	 * @param list
	 * @param count
	 * @return
	 * @时间:2016年12月13日下午2:14:41
	 */
	private BaseGoods controlLen(List<BaseGoods> list, int count) {
		BaseGoods baseGoods = list.get(count);
		// 截取金额
		/*
		 * double totalMoney = NumUtil.trim0(baseGoods.getMoney();
		 * billAndCustomTeam.setTotalMonay(totalMoney);
		 */
		
		// 截取物料名称
		String goodsNm = baseGoods.getGoodsNm();
		if (StringUtils.isNotEmpty(goodsNm)) {
			int length = goodsNm.length();
			if (length > 4) {
				goodsNm = goodsNm.substring(0, 4);
				baseGoods.setGoodsNm(goodsNm);
			}
		}
		// 截取规格
		String spec = baseGoods.getSpec();
		if (StringUtils.isNotEmpty(spec)) {
			if (spec.length() > 4) {
				spec = spec.substring(0, 4);
				baseGoods.setSpec(spec);
			}
		}
		// 单位截取
		String unitNm = baseGoods.getUnitNm();
		if (StringUtils.isNotEmpty(unitNm)) {
			if (unitNm.length() > 3) {
				unitNm = unitNm.substring(0, 3);
				baseGoods.setUnitNm(unitNm);
			}
		}
		return baseGoods;
	}
}
