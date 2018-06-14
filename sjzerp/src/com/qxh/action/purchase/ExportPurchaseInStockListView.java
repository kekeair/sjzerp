package com.qxh.action.purchase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.qxh.exmodel.CommonModel;
import com.qxh.model.PurchaseTotallModel;
import com.qxh.model.User;
import com.qxh.service.CommonService;
import com.qxh.service.PurchaseService;
import com.qxh.utils.MoneyUtil;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**

 * @Description:[采购入库单导出]

 * @author:kekeair

 * @time:2017年2月27日 下午8:00:39

 */
public class ExportPurchaseInStockListView extends AbstractExcelView {

	private PurchaseService purchaseService;

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	private CommonService commonService;

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> arg0, HSSFWorkbook workbook, HttpServletRequest req,
			HttpServletResponse response) throws Exception {
		/*
		 * String kindId = req.getParameter("kindId"); String goodsNm =
		 * req.getParameter("goodsNm"); String goodsType =
		 * req.getParameter("goodsType");
		 */
		String billId = req.getParameter("billId");
		String supplierId = req.getParameter("supplierId");
		String fname = "采购入库表.xls";
		response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fname, "UTF-8"));
		response.setContentType("application/msexcel");
		HSSFSheet sheet = workbook.createSheet("采购入库表");
		// 设置列宽
		sheet.setColumnWidth(0, 2280);
		sheet.setColumnWidth(1, 3882);
		sheet.setColumnWidth(2, 5202);
		sheet.setColumnWidth(3, 1560);
		sheet.setColumnWidth(4, 1762);
		sheet.setColumnWidth(5, 1521);
		sheet.setColumnWidth(6, 2802);
		sheet.setColumnWidth(7, 3602);

		// 格式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style1.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.RED.index);
		style2.setFont(font);

		HSSFCellStyle style3 = workbook.createCellStyle();
		style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style3.setBottomBorderColor(HSSFColor.WHITE.index);
		style3.setLeftBorderColor(HSSFColor.WHITE.index);
		style3.setRightBorderColor(HSSFColor.WHITE.index);
		style3.setTopBorderColor(HSSFColor.WHITE.index);
		style3.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle style4 = workbook.createCellStyle();
		style4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style4.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style4.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style4.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 左右无边框设置,字体居中
		HSSFCellStyle style5 = workbook.createCellStyle();
		style5.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style5.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style5.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style5.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style5.setLeftBorderColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		style5.setRightBorderColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		style5.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		style5.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style5.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//// 无边框设置,字体靠左
		HSSFCellStyle style6 = workbook.createCellStyle();
		style6.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style6.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style6.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style6.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style6.setBottomBorderColor(HSSFColor.WHITE.index);
		style6.setLeftBorderColor(HSSFColor.WHITE.index);
		style6.setRightBorderColor(HSSFColor.WHITE.index);
		style6.setTopBorderColor(HSSFColor.WHITE.index);
		style6.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style6.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style6.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle style7 = workbook.createCellStyle();
		style7.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style7.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style7.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style7.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style7.setBottomBorderColor(HSSFColor.WHITE.index);
		style7.setLeftBorderColor(HSSFColor.WHITE.index);
		style7.setRightBorderColor(HSSFColor.WHITE.index);
		style7.setTopBorderColor(HSSFColor.WHITE.index);
		style7.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style7.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style7.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font7 = workbook.createFont();
		font7.setFontHeightInPoints((short) 15);// 设置字体大小
		font7.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体增粗
		// 把字体应用到当前的样式
		style7.setFont(font7);

		HSSFCellStyle style8 = workbook.createCellStyle();
		style8.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style8.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style8.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style8.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style8.setBottomBorderColor(HSSFColor.WHITE.index);
		style8.setLeftBorderColor(HSSFColor.WHITE.index);
		style8.setRightBorderColor(HSSFColor.WHITE.index);
		style8.setTopBorderColor(HSSFColor.WHITE.index);
		style8.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style8.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style8.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font8 = workbook.createFont();
		font8.setFontHeightInPoints((short) 14);// 设置字体大小
		// font8.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体增粗
		// 把字体应用到当前的样式
		style8.setFont(font8);

		// 获取供应商
		String supplierName = null;
		Result result = purchaseService.getExportSupplier(billId);
		List<CommonModel> commonModel = (List<CommonModel>) result.getData();
		for (int i = 0; i < commonModel.size(); i++) {
			int atNo = commonModel.get(i).getAtNo();
			int supplierId1 = Integer.parseInt(supplierId);
			if (atNo == supplierId1) {
				supplierName = commonModel.get(i).getName();
			}
		}
		// 获取数据列表
		User user = (User) SessionUtil.getSession().getAttribute("user");
		Map map = purchaseService.exportPurchaseTotallList(user.getStructId(), billId, supplierId);
		List<PurchaseTotallModel> list = (List<PurchaseTotallModel>) map.get("list");

		if (list == null || list.size() == 0) {
			return;
		}
		int size = list.size();
		int len = 0;// len控制大循环的次数
		if (size <= 30) {
			len = 1;
		} else if (size % 30 != 0) {
			len = size / 30 + 1;
		} else {
			len = size / 30;
		}

		// 设置行高-------------------
		for (int i = 0; i < 37 * len; i++) {
			HSSFRow createRow = sheet.createRow(i);
			createRow.setHeightInPoints(21);
		}
		// 设置行高结束-----------------------
		int count = -37;
		int countSize = -30; // 控制集合循环的下标
		Double nAllMoney = (Double) map.get("allMoney");// 获取总钱数

		// 将钱数转为大写
		String sAllMoney = nAllMoney.toString();
		String allMoney = MoneyUtil.toChinese(sAllMoney);
		for (int i = 0; i < len; i++) {
			count = count + 37;
			countSize = countSize + 30;
			// =============start-title=============
			HSSFCell cell000 = getCell(sheet, count, 0);
			cell000.setCellStyle(style3);
			HSSFCell cell100 = getCell(sheet, count, 1);
			cell100.setCellStyle(style3);
			HSSFCell cell200 = getCell(sheet, count, 2);
			cell200.setCellStyle(style3);
			HSSFCell cell300 = getCell(sheet, count, 3);
			cell300.setCellStyle(style7);
			cell300.setCellValue(" 北 京 祥 鹤 物 流 股 份 有 限 公 司 ");
			HSSFCell cell400 = getCell(sheet, count, 4);
			cell400.setCellStyle(style3);
			HSSFCell cell500 = getCell(sheet, count, 5);
			cell500.setCellStyle(style3);
			HSSFCell cell600 = getCell(sheet, count, 6);
			cell600.setCellStyle(style3);
			HSSFCell cell700 = getCell(sheet, count, 7);
			cell700.setCellStyle(style3);

			HSSFCell cell0 = getCell(sheet, count + 1, 0);
			cell0.setCellStyle(style3);
			cell0.setCellValue("订单号:");
			HSSFCell cell1 = getCell(sheet, count + 1, 1);
			cell1.setCellStyle(style3);
			cell1.setCellValue(list.get(i).getBillCode());
			HSSFCell cell2 = getCell(sheet, count + 1, 2);
			cell2.setCellStyle(style3);
			HSSFCell cell3 = getCell(sheet, count + 1, 3);
			cell3.setCellStyle(style3);
			HSSFCell cell4 = getCell(sheet, count + 1, 4);
			cell4.setCellStyle(style8);
			cell4.setCellValue(" 采购入库单 ");
			HSSFCell cell5 = getCell(sheet, count + 1, 5);
			cell5.setCellStyle(style3);
			HSSFCell cell6 = getCell(sheet, count + 1, 6);
			cell6.setCellStyle(style3);
			HSSFCell cell7 = getCell(sheet, count + 1, 7);
			cell7.setCellStyle(style3);
			// =============end-title=================================

			// =============类型属性start===============================
			// String teamNm = list.get(0).getTeamNm();
			HSSFCell cell40 = getCell(sheet, count + 2, 0);
			cell40.setCellStyle(style3);
			cell40.setCellValue("供应商:");
			HSSFCell cell41 = getCell(sheet, count + 2, 1);
			cell41.setCellValue(supplierName);
			cell41.setCellStyle(style3);
			HSSFCell cell42 = getCell(sheet, count + 2, 2);
			cell42.setCellStyle(style3);
			HSSFCell cell43 = getCell(sheet, count + 2, 3);
			cell43.setCellStyle(style3);

			HSSFCell cell44 = getCell(sheet, count + 2, 4);
			cell44.setCellStyle(style3);

			HSSFCell cell45 = getCell(sheet, count + 2, 5);
			cell45.setCellStyle(style3);

			HSSFCell cell47 = getCell(sheet, count + 2, 6);
			cell47.setCellValue("日期:");
			cell47.setCellStyle(style3);
			HSSFCell cell48 = getCell(sheet, count + 2, 7);
			cell48.setCellValue(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
			cell48.setCellStyle(style3);
			// =============类型属性end===============================

			// =============列名称start===============================
			HSSFCell cell10 = getCell(sheet, count + 3, 0);
			cell10.setCellStyle(style);
			cell10.setCellValue("序号");
			HSSFCell cell11 = getCell(sheet, count + 3, 1);
			cell11.setCellStyle(style);
			cell11.setCellValue("物料代码");
			HSSFCell cell12 = getCell(sheet, count + 3, 2);
			cell12.setCellStyle(style);
			cell12.setCellValue("产品名称");
			HSSFCell cell13 = getCell(sheet, count + 3, 3);
			cell13.setCellStyle(style);
			cell13.setCellValue("规格");
			HSSFCell cell14 = getCell(sheet, count + 3, 4);
			cell14.setCellStyle(style);
			cell14.setCellValue("数量");
			HSSFCell cell30 = getCell(sheet, count + 3, 5);
			cell30.setCellStyle(style);
			cell30.setCellValue("单位");
			HSSFCell cell16 = getCell(sheet, count + 3, 6);
			cell16.setCellStyle(style);
			cell16.setCellValue("单价");
			HSSFCell cell17 = getCell(sheet, count + 3, 7);
			cell17.setCellStyle(style);
			cell17.setCellValue("金额");

			// =============列名称end===============================

			// 小计
			double smillCount = 0;
			if (size < 30) {
				// 获取的集合进行遍历
				for (int j = 0; j < size; j++) {
					PurchaseTotallModel model = list.get(countSize + j);
					HSSFCell cell20 = getCell(sheet, count + j + 4, 0);
					cell20.setCellStyle(style4);
					cell20.setCellValue(j + 1);
					HSSFCell cell21 = getCell(sheet, count + j + 4, 1);
					cell21.setCellStyle(style4);
					cell21.setCellValue(model.getCode());
					HSSFCell cell22 = getCell(sheet, count + j + 4, 2);
					cell22.setCellStyle(style4);
					cell22.setCellValue(model.getGoodsNm());
					HSSFCell cell23 = getCell(sheet, count + j + 4, 3);
					cell23.setCellStyle(style4);
					cell23.setCellValue("");
					HSSFCell cell24 = getCell(sheet, count + j + 4, 4);
					cell24.setCellStyle(style4);
					cell24.setCellValue(model.getOrderNum());
					HSSFCell cell25 = getCell(sheet, count + j + 4, 5);
					cell25.setCellStyle(style4);
					cell25.setCellValue(model.getUnitNm());
					HSSFCell cell26 = getCell(sheet, count + j + 4, 6);
					cell26.setCellStyle(style4);
					cell26.setCellValue(model.getPrice());
					HSSFCell cell27 = getCell(sheet, count + j + 4, 7);
					cell27.setCellStyle(style4);
					cell27.setCellValue(model.getMoney());

					smillCount += model.getMoney();
				}

				// ================合計start=========================
				// 小计:
				// 将本页小计转为大写
				String sSmillCount = String.valueOf(smillCount);
				String cSmillCount = MoneyUtil.toChinese(sSmillCount);
				HSSFCell cell70 = getCell(sheet, count + size + 4, 0);
				cell70.setCellStyle(style);
				cell70.setCellValue("本页小计:");
				HSSFCell cell71 = getCell(sheet, count + size + 4, 1);
				cell71.setCellStyle(style);
				HSSFCell cell72 = getCell(sheet, count + size + 4, 2);
				cell72.setCellStyle(style5);

				HSSFCell cell73 = getCell(sheet, count + size + 4, 3);
				cell73.setCellStyle(style5);
				HSSFCell cell74 = getCell(sheet, count + size + 4, 4);
				cell74.setCellStyle(style5);
				cell74.setCellValue(cSmillCount);
				HSSFCell cell75 = getCell(sheet, count + size + 4, 5);
				cell75.setCellStyle(style5);

				HSSFCell cell77 = getCell(sheet, count + size + 4, 6);
				cell77.setCellStyle(style);
				cell77.setCellValue("￥");
				HSSFCell cell78 = getCell(sheet, count + size + 4, 7);
				cell78.setCellStyle(style);
				cell78.setCellValue(smillCount);

				// 总计:
				HSSFCell cell80 = getCell(sheet, count + size + 5, 0);
				cell80.setCellStyle(style);
				cell80.setCellValue("总计金额:");
				HSSFCell cell81 = getCell(sheet, count + size + 5, 1);
				cell81.setCellStyle(style);
				HSSFCell cell82 = getCell(sheet, count + size + 5, 2);
				cell82.setCellStyle(style5);
				HSSFCell cell83 = getCell(sheet, count + size + 5, 3);
				cell83.setCellStyle(style5);
				HSSFCell cell84 = getCell(sheet, count + size + 5, 4);
				cell84.setCellStyle(style5);
				cell84.setCellValue(allMoney);
				HSSFCell cell85 = getCell(sheet, count + size + 5, 5);
				cell85.setCellStyle(style5);
				HSSFCell cell86 = getCell(sheet, count + size + 5, 6);
				cell86.setCellStyle(style);
				cell86.setCellValue("￥");
				HSSFCell cell87 = getCell(sheet, count + size + 5, 7);
				cell87.setCellStyle(style);
				cell87.setCellValue(nAllMoney);

				// ================合計end==============================

				// ================签字start===========================
				HSSFCell cell90 = getCell(sheet, count + size + 6, 0);
				cell90.setCellStyle(style3);
				cell90.setCellValue("经理:");
				HSSFCell cell91 = getCell(sheet, count + size + 6, 1);
				cell91.setCellStyle(style3);
				HSSFCell cell92 = getCell(sheet, count + size + 6, 2);
				cell92.setCellStyle(style3);
				cell92.setCellValue("库管:");
				HSSFCell cell93 = getCell(sheet, count + size + 6, 3);
				cell93.setCellStyle(style3);
				HSSFCell cell94 = getCell(sheet, count + size + 6, 4);
				cell94.setCellStyle(style3);
				cell94.setCellValue("采购:");
				HSSFCell cell95 = getCell(sheet, count + size + 6, 5);
				cell95.setCellStyle(style3);

				HSSFCell cell97 = getCell(sheet, count + size + 6, 6);
				cell97.setCellValue("财务:");
				cell97.setCellStyle(style3);
				HSSFCell cell98 = getCell(sheet, count + size + 6, 7);
				cell98.setCellStyle(style3);
				// ================签字end=========================

			} else {
				for (int j = 0; j < 30; j++) {
					PurchaseTotallModel model = list.get(countSize + j);
					HSSFCell cell20 = getCell(sheet, count + j + 4, 0);
					cell20.setCellStyle(style4);
					cell20.setCellValue(j + 1);
					HSSFCell cell36 = getCell(sheet, count + j + 4, 1);
					cell36.setCellStyle(style4);
					cell36.setCellValue(model.getCode());
					HSSFCell cell22 = getCell(sheet, count + j + 4, 2);
					cell22.setCellStyle(style4);
					cell22.setCellValue(model.getGoodsNm());
					HSSFCell cell23 = getCell(sheet, count + j + 4, 3);
					cell23.setCellStyle(style4);
					cell23.setCellValue("");
					HSSFCell cell24 = getCell(sheet, count + j + 4, 4);
					cell24.setCellStyle(style4);
					cell24.setCellValue(model.getOrderNum());
					HSSFCell cell25 = getCell(sheet, count + j + 4, 5);
					cell25.setCellStyle(style4);
					cell25.setCellValue(model.getUnitNm());
					HSSFCell cell26 = getCell(sheet, count + j + 4, 6);
					cell26.setCellStyle(style4);
					cell26.setCellValue(model.getPrice());
					HSSFCell cell27 = getCell(sheet, count + j + 4, 7);
					cell27.setCellStyle(style4);
					cell27.setCellValue(model.getMoney());

					smillCount += model.getMoney();
				}
				size = size - 30;
				// ================合計start=========================
				// 小计:
				// 将本页小计转为大写
				String sSmillCount = String.valueOf(smillCount);
				String cSmillCount = MoneyUtil.toChinese(sSmillCount);

				HSSFCell cell70 = getCell(sheet, count + 30 + 4, 0);
				cell70.setCellStyle(style);
				cell70.setCellValue("本页小计:");
				HSSFCell cell71 = getCell(sheet, count + 30 + 4, 1);
				cell71.setCellStyle(style);
				HSSFCell cell72 = getCell(sheet, count + 30 + 4, 2);
				cell72.setCellStyle(style5);
				HSSFCell cell73 = getCell(sheet, count + 30 + 4, 3);
				cell73.setCellStyle(style5);
				HSSFCell cell74 = getCell(sheet, count + 30 + 4, 4);
				cell74.setCellStyle(style5);
				cell74.setCellValue(cSmillCount);
				HSSFCell cell75 = getCell(sheet, count + 30 + 4, 5);
				cell75.setCellStyle(style5);
				HSSFCell cell76 = getCell(sheet, count + 30 + 4, 6);
				cell76.setCellStyle(style);
				cell76.setCellValue("￥");
				HSSFCell cell77 = getCell(sheet, count + 30 + 4, 7);
				cell77.setCellStyle(style);
				cell77.setCellValue(smillCount);

				// 总计:
				HSSFCell cell80 = getCell(sheet, count + 30 + 5, 0);
				cell80.setCellStyle(style);
				cell80.setCellValue("总计金额:");
				HSSFCell cell81 = getCell(sheet, count + 30 + 5, 1);
				cell81.setCellStyle(style);
				HSSFCell cell82 = getCell(sheet, count + 30 + 5, 2);
				cell82.setCellStyle(style5);
				HSSFCell cell83 = getCell(sheet, count + 30 + 5, 3);
				cell83.setCellStyle(style5);
				HSSFCell cell84 = getCell(sheet, count + 30 + 5, 4);
				cell84.setCellStyle(style5);
				cell84.setCellValue(allMoney);
				HSSFCell cell85 = getCell(sheet, count + 30 + 5, 5);
				cell85.setCellStyle(style5);
				HSSFCell cell86 = getCell(sheet, count + 30 + 5, 6);
				cell86.setCellStyle(style);
				cell86.setCellValue("￥");
				HSSFCell cell87 = getCell(sheet, count + 30 + 5, 7);
				cell87.setCellStyle(style);
				cell87.setCellValue(nAllMoney);

				// ================合計end=========================

				// ================签字start===========================
				HSSFCell cell90 = getCell(sheet, count + 30 + 6, 0);
				cell90.setCellStyle(style3);
				cell90.setCellValue("经理:");
				HSSFCell cell91 = getCell(sheet, count + 30 + 6, 1);
				cell91.setCellStyle(style3);
				HSSFCell cell92 = getCell(sheet, count + 30 + 6, 2);
				cell92.setCellStyle(style3);
				cell92.setCellValue("库管:");
				HSSFCell cell93 = getCell(sheet, count + 30 + 6, 3);
				cell93.setCellStyle(style3);
				HSSFCell cell94 = getCell(sheet, count + 30 + 6, 4);
				cell94.setCellStyle(style3);
				cell94.setCellValue("采购:");
				HSSFCell cell95 = getCell(sheet, count + 30 + 6, 5);
				cell95.setCellStyle(style3);

				HSSFCell cell97 = getCell(sheet, count + 30 + 6, 6);
				cell97.setCellValue("财务:");
				cell97.setCellStyle(style3);
				HSSFCell cell98 = getCell(sheet, count + 30 + 6, 7);
				cell98.setCellStyle(style3);
				// ================签字end=========================
			}

		}
	}

}
