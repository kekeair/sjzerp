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

public class ExportPurchaseTotallListView extends AbstractExcelView {

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

		String billId = req.getParameter("billId");
		String supplierId = req.getParameter("supplierId");
		String fname = "采购汇总表.xls";
		response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fname, "UTF-8"));
		response.setContentType("application/msexcel");
		HSSFSheet sheet = workbook.createSheet("采购汇总表");
		// 设置列宽
		sheet.setColumnWidth(0, 2762);
		sheet.setColumnWidth(1, 4201);
		sheet.setColumnWidth(2, 5322);
		sheet.setColumnWidth(3, 4021);
		sheet.setColumnWidth(4, 4021);
		sheet.setColumnWidth(5, 4021);
		sheet.setColumnWidth(6, 4021);
		sheet.setColumnWidth(7, 4021);
		sheet.setColumnWidth(8, 4021);
		sheet.setColumnWidth(9, 4021);
		sheet.setColumnWidth(10, 4021);

		// 格式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style1.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style1.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
		style4.setAlignment(HSSFCellStyle.ALIGN_LEFT);

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
		style6.setAlignment(HSSFCellStyle.ALIGN_LEFT);

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
		if (size <= 20) {
			len = 1;
		} else if (size % 20 != 0) {
			len = size / 20 + 1;
		} else {
			len = size / 20;
		}

		// --------设置行高-------------
		for (int i = 0; i < 26 * len; i++) {
			HSSFRow createRow = sheet.createRow(i);
			createRow.setHeightInPoints(17);
		}
		// ---------行高设置-end------------------
		int count = -26; // 每页行数
		int countSize = -20; // 控制集合循环的下标
		Double nAllMoney = (Double) map.get("allMoney");// 获取总钱数
		// 将钱数转为大写
		String sAllMoney = nAllMoney.toString();
		String allMoney = MoneyUtil.toChinese(sAllMoney);

		for (int i = 0; i < len; i++) {
			count = count + 26;
			countSize = countSize + 20;
			// =============start-title=============

			HSSFCell cell0 = getCell(sheet, count , 0);
			cell0.setCellStyle(style8);
			HSSFCell cell1 = getCell(sheet, count , 1);
			cell1.setCellStyle(style8);
			HSSFCell cell2 = getCell(sheet, count , 2);
			cell2.setCellStyle(style8);
			HSSFCell cell3 = getCell(sheet, count , 3);
			cell3.setCellStyle(style8);
			HSSFCell cell4 = getCell(sheet, count , 4);
			cell4.setCellValue(" 采购汇总单 ");
			cell4.setCellStyle(style8);
			HSSFCell cell5 = getCell(sheet, count , 5);
			cell5.setCellStyle(style8);
			HSSFCell cell6 = getCell(sheet, count , 6);
			cell6.setCellStyle(style8);
			HSSFCell cell7 = getCell(sheet, count , 7);
			cell7.setCellStyle(style8);
			HSSFCell cell8 = getCell(sheet, count , 8);
			cell8.setCellStyle(style8);
			HSSFCell cell9 = getCell(sheet, count , 9);
			cell9.setCellStyle(style8);
			HSSFCell cell910 = getCell(sheet, count , 10);
			cell910.setCellStyle(style8);
			// =============end-title=================================

			// =============类型属性start===============================
			// String teamNm = list.get(0).getTeamNm();
			HSSFCell cell40 = getCell(sheet, count + 1, 0);
			cell40.setCellStyle(style3);
			cell40.setCellValue("供应商:");
			HSSFCell cell41 = getCell(sheet, count + 1, 1);
			cell41.setCellValue(supplierName);
			cell41.setCellStyle(style3);
			HSSFCell cell42 = getCell(sheet, count + 1, 2);
			cell42.setCellStyle(style3);
			HSSFCell cell43 = getCell(sheet, count + 1, 3);
			cell43.setCellStyle(style3);
			cell43.setCellValue("订单号:");
			HSSFCell cell44 = getCell(sheet, count + 1, 4);
			cell44.setCellStyle(style3);
			cell44.setCellValue(list.get(i).getBillCode());
			HSSFCell cell45 = getCell(sheet, count + 1, 5);
			cell45.setCellStyle(style3);
			HSSFCell cell46 = getCell(sheet, count + 1, 6);
			cell46.setCellStyle(style3);
			HSSFCell cell47 = getCell(sheet, count + 1, 7);
			cell47.setCellValue("日期:");
			cell47.setCellStyle(style3);
			HSSFCell cell48 = getCell(sheet, count + 1, 8);
			cell48.setCellValue(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
			cell48.setCellStyle(style3);
			HSSFCell cell49 = getCell(sheet, count + 1, 9);
			cell49.setCellStyle(style3);
			HSSFCell cell4910 = getCell(sheet, count + 1, 10);
			cell4910.setCellStyle(style3);
			// =============类型属性end===============================

			// =============列名称start===============================
			HSSFCell cell101 = getCell(sheet, count + 2, 0);
			cell101.setCellStyle(style);
			cell101.setCellValue("序号");
			HSSFCell cell10 = getCell(sheet, count + 2, 1);
			cell10.setCellStyle(style);
			cell10.setCellValue("物料编号");
			HSSFCell cell11 = getCell(sheet, count + 2, 2);
			cell11.setCellStyle(style);
			cell11.setCellValue("物料名称");
			HSSFCell cell12 = getCell(sheet, count + 2, 3);
			cell12.setCellStyle(style);
			cell12.setCellValue("规格");
			HSSFCell cell13 = getCell(sheet, count + 2, 4);
			cell13.setCellStyle(style);
			cell13.setCellValue("库存数量");
			HSSFCell cell14 = getCell(sheet, count + 2, 5);
			cell14.setCellStyle(style);
			cell14.setCellValue("申购量");
			HSSFCell cell15 = getCell(sheet, count + 2, 6);
			cell15.setCellStyle(style);
			cell15.setCellValue("实收量");
			HSSFCell cell16 = getCell(sheet, count + 2, 7);
			cell16.setCellStyle(style);
			cell16.setCellValue("单位");
			HSSFCell cell17 = getCell(sheet, count + 2, 8);
			cell17.setCellStyle(style);
			cell17.setCellValue("单价");
			HSSFCell cell18 = getCell(sheet, count + 2, 9);
			cell18.setCellStyle(style);
			cell18.setCellValue("金额");
			HSSFCell cell19 = getCell(sheet, count + 2, 10);
			cell19.setCellStyle(style);
			cell19.setCellValue("备注");
			// =============列名称end===============================

			// 小计
			double smillCount = 0;
			if (size < 20) {
				// 获取的集合进行遍历
				for (int j = 0; j < size; j++) {
					PurchaseTotallModel model = list.get(countSize + j);
					HSSFCell cell201 = getCell(sheet, count + j + 3, 0);
					cell201.setCellStyle(style4);
					cell201.setCellValue(j + 1);
					HSSFCell cell20 = getCell(sheet, count + j + 3, 1);
					cell20.setCellStyle(style4);
					cell20.setCellValue(model.getCode());
					HSSFCell cell21 = getCell(sheet, count + j + 3, 2);
					cell21.setCellStyle(style4);
					cell21.setCellValue(model.getGoodsNm());
					HSSFCell cell22 = getCell(sheet, count + j + 3, 3);
					cell22.setCellStyle(style4);
					cell22.setCellValue("");
					HSSFCell cell23 = getCell(sheet, count + j + 3, 4);
					cell23.setCellStyle(style4);
					cell23.setCellValue(model.getStockNum());
					HSSFCell cell24 = getCell(sheet, count + j + 3, 5);
					cell24.setCellStyle(style4);
					cell24.setCellValue(model.getOrderNum());
					HSSFCell cell25 = getCell(sheet, count + j + 3, 6);
					cell25.setCellStyle(style4);
					cell25.setCellValue("");
					HSSFCell cell26 = getCell(sheet, count + j + 3, 7);
					cell26.setCellStyle(style4);
					cell26.setCellValue(model.getUnitNm());
					HSSFCell cell27 = getCell(sheet, count + j + 3, 8);
					cell27.setCellStyle(style4);
					cell27.setCellValue(model.getPrice());
					HSSFCell cell28 = getCell(sheet, count + j + 3, 9);
					cell28.setCellStyle(style4);
					cell28.setCellValue(model.getMoney());
					HSSFCell cell29 = getCell(sheet, count + j + 3, 10);
					cell29.setCellStyle(style4);
					cell29.setCellValue(model.getRemark());
					smillCount += model.getMoney();
				}

				// ================合計start=========================
				// 小计:
				// 将小结金额转为大写
				String sSmillCount = String.valueOf(smillCount);
				String cSmillCount = MoneyUtil.toChinese(sSmillCount);
				HSSFCell cell70 = getCell(sheet, count + size + 3, 0);
				cell70.setCellStyle(style);
				cell70.setCellValue("本页小计:");
				HSSFCell cell71 = getCell(sheet, count + size + 3, 1);
				cell71.setCellStyle(style);
				HSSFCell cell72 = getCell(sheet, count + size + 3, 2);
				cell72.setCellStyle(style5);
				HSSFCell cell73 = getCell(sheet, count + size + 3, 3);
				cell73.setCellStyle(style5);
				HSSFCell cell74 = getCell(sheet, count + size + 3, 4);
				cell74.setCellStyle(style5);
				cell74.setCellValue(cSmillCount);
				HSSFCell cell75 = getCell(sheet, count + size + 3, 5);
				cell75.setCellStyle(style5);
				HSSFCell cell76 = getCell(sheet, count + size + 3, 6);
				cell76.setCellStyle(style5);
				HSSFCell cell77 = getCell(sheet, count + size + 3, 7);
				cell77.setCellStyle(style);
				cell77.setCellValue("￥");
				HSSFCell cell78 = getCell(sheet, count + size + 3, 8);
				cell78.setCellStyle(style5);
				cell78.setCellValue(smillCount);
				HSSFCell cell79 = getCell(sheet, count + size + 3, 9);
				cell79.setCellStyle(style5);
				HSSFCell cell790 = getCell(sheet, count + size + 3, 10);
				cell790.setCellStyle(style5);

				// 总计:
				HSSFCell cell80 = getCell(sheet, count + size + 4, 0);
				cell80.setCellStyle(style);
				cell80.setCellValue("总计金额:");
				HSSFCell cell81 = getCell(sheet, count + size + 4, 1);
				cell81.setCellStyle(style);
				HSSFCell cell82 = getCell(sheet, count + size + 4, 2);
				cell82.setCellStyle(style5);
				HSSFCell cell83 = getCell(sheet, count + size + 4, 3);
				cell83.setCellStyle(style5);
				HSSFCell cell84 = getCell(sheet, count + size + 4, 4);
				cell84.setCellStyle(style5);
				cell84.setCellValue(allMoney);
				HSSFCell cell85 = getCell(sheet, count + size + 4, 5);
				cell85.setCellStyle(style5);
				HSSFCell cell86 = getCell(sheet, count + size + 4, 6);
				cell86.setCellStyle(style5);
				HSSFCell cell87 = getCell(sheet, count + size + 4, 7);
				cell87.setCellStyle(style);
				cell87.setCellValue("￥");
				HSSFCell cell88 = getCell(sheet, count + size + 4, 8);
				cell88.setCellStyle(style5);
				cell88.setCellValue(nAllMoney);
				HSSFCell cell89 = getCell(sheet, count + size + 4, 9);
				cell89.setCellStyle(style5);
				HSSFCell cell890 = getCell(sheet, count + size + 4, 10);
				cell890.setCellStyle(style5);
				// ================合計end==============================

				// ================签字start===========================
				HSSFCell cell90 = getCell(sheet, count + size + 5, 0);
				cell90.setCellStyle(style3);
				cell90.setCellValue("经理:");
				HSSFCell cell91 = getCell(sheet, count + size + 5, 1);
				cell91.setCellStyle(style3);
				HSSFCell cell92 = getCell(sheet, count + size + 5, 2);
				cell92.setCellStyle(style3);
				cell92.setCellValue("库管:");
				HSSFCell cell93 = getCell(sheet, count + size + 5, 3);
				cell93.setCellStyle(style3);
				HSSFCell cell94 = getCell(sheet, count + size + 5, 4);
				cell94.setCellStyle(style3);
				cell94.setCellValue("采购:");
				HSSFCell cell95 = getCell(sheet, count + size + 5, 5);
				cell95.setCellStyle(style3);
				HSSFCell cell96 = getCell(sheet, count + size + 5, 6);
				cell96.setCellStyle(style3);
				HSSFCell cell97 = getCell(sheet, count + size + 5, 7);
				cell97.setCellValue("财务:");
				cell97.setCellStyle(style3);
				HSSFCell cell98 = getCell(sheet, count + size + 5, 8);
				cell98.setCellStyle(style3);
				HSSFCell cell99 = getCell(sheet, count + size + 5, 9);
				cell99.setCellStyle(style3);
				HSSFCell cell990 = getCell(sheet, count + size + 5, 10);
				cell990.setCellStyle(style3);
				// ================签字end=========================

			} else {
				for (int j = 0; j < 20; j++) {
					PurchaseTotallModel model = list.get(countSize + j);
					HSSFCell cell201 = getCell(sheet, count + j + 3, 0);
					cell201.setCellStyle(style4);
					cell201.setCellValue(j + 1);
					HSSFCell cell20 = getCell(sheet, count + j + 3, 1);
					cell20.setCellStyle(style4);
					cell20.setCellValue(model.getCode());
					HSSFCell cell21 = getCell(sheet, count + j + 3, 2);
					cell21.setCellStyle(style4);
					cell21.setCellValue(model.getGoodsNm());
					HSSFCell cell22 = getCell(sheet, count + j + 3, 3);
					cell22.setCellStyle(style4);
					cell22.setCellValue("");
					HSSFCell cell23 = getCell(sheet, count + j + 3, 4);
					cell23.setCellStyle(style4);
					cell23.setCellValue(model.getStockNum());
					HSSFCell cell24 = getCell(sheet, count + j + 3, 5);
					cell24.setCellStyle(style4);
					cell24.setCellValue(model.getOrderNum());
					HSSFCell cell25 = getCell(sheet, count + j + 3, 6);
					cell25.setCellStyle(style4);
					cell25.setCellValue("");
					HSSFCell cell26 = getCell(sheet, count + j + 3, 7);
					cell26.setCellStyle(style4);
					cell26.setCellValue(model.getUnitNm());
					HSSFCell cell27 = getCell(sheet, count + j + 3, 8);
					cell27.setCellStyle(style4);
					cell27.setCellValue(model.getPrice());
					HSSFCell cell28 = getCell(sheet, count + j + 3, 9);
					cell28.setCellStyle(style4);
					cell28.setCellValue(model.getMoney());
					HSSFCell cell29 = getCell(sheet, count + j + 3, 10);
					cell29.setCellStyle(style4);
					cell29.setCellValue("");
					smillCount += model.getMoney();
				}
				size = size - 20;
				// ================合計start=========================
				// 小计:
				// 将小结金额转为大写
				String sSmillCount = String.valueOf(smillCount);
				String cSmillCount = MoneyUtil.toChinese(sSmillCount);
				HSSFCell cell70 = getCell(sheet, count + 20 + 3, 0);
				cell70.setCellStyle(style);
				cell70.setCellValue("本页小计:");
				HSSFCell cell71 = getCell(sheet, count + 20 + 3, 1);
				cell71.setCellStyle(style);
				HSSFCell cell72 = getCell(sheet, count + 20 + 3, 2);
				cell72.setCellStyle(style5);
				HSSFCell cell73 = getCell(sheet, count + 20 + 3, 3);
				cell73.setCellStyle(style5);
				HSSFCell cell74 = getCell(sheet, count + 20 + 3, 4);
				cell74.setCellStyle(style5);
				cell74.setCellValue(cSmillCount);
				HSSFCell cell75 = getCell(sheet, count + 20 + 3, 5);
				cell75.setCellStyle(style5);
				HSSFCell cell76 = getCell(sheet, count + 20 + 3, 6);
				cell76.setCellStyle(style5);
				HSSFCell cell77 = getCell(sheet, count + 20 + 3, 7);
				cell77.setCellStyle(style);
				cell77.setCellValue("￥");
				HSSFCell cell78 = getCell(sheet, count + 20 + 3, 8);
				cell78.setCellStyle(style5);
				cell78.setCellValue(smillCount);
				HSSFCell cell79 = getCell(sheet, count + 20 + 3, 9);
				cell79.setCellStyle(style5);
				HSSFCell cell790 = getCell(sheet, count + 20 + 3, 10);
				cell790.setCellStyle(style5);
				// 总计:
				HSSFCell cell80 = getCell(sheet, count + 20 + 4, 0);
				cell80.setCellStyle(style);
				cell80.setCellValue("总计金额:");
				HSSFCell cell81 = getCell(sheet, count + 20 + 4, 1);
				cell81.setCellStyle(style);
				HSSFCell cell82 = getCell(sheet, count + 20 + 4, 2);
				cell82.setCellStyle(style5);
				HSSFCell cell83 = getCell(sheet, count + 20 + 4, 3);
				cell83.setCellStyle(style5);
				HSSFCell cell84 = getCell(sheet, count + 20 + 4, 4);
				cell84.setCellStyle(style5);
				cell84.setCellValue(allMoney);
				HSSFCell cell85 = getCell(sheet, count + 20 + 4, 5);
				cell85.setCellStyle(style5);
				HSSFCell cell86 = getCell(sheet, count + 20 + 4, 6);
				cell86.setCellStyle(style5);
				HSSFCell cell87 = getCell(sheet, count + 20 + 4, 7);
				cell87.setCellStyle(style);
				cell87.setCellValue("￥");
				HSSFCell cell88 = getCell(sheet, count + 20 + 4, 8);
				cell88.setCellStyle(style5);
				cell88.setCellValue(nAllMoney);
				HSSFCell cell89 = getCell(sheet, count + 20 + 4, 9);
				cell89.setCellStyle(style5);
				HSSFCell cell890 = getCell(sheet, count + 20 + 4, 10);
				cell890.setCellStyle(style5);
				// ================合計end=========================

				// ================签字start===========================
				HSSFCell cell90 = getCell(sheet, count + 20 + 5, 0);
				cell90.setCellStyle(style3);
				cell90.setCellValue("经理:");
				HSSFCell cell91 = getCell(sheet, count + 20 + 5, 1);
				cell91.setCellStyle(style3);
				HSSFCell cell92 = getCell(sheet, count + 20 + 5, 2);
				cell92.setCellStyle(style3);
				cell92.setCellValue("库管:");
				HSSFCell cell93 = getCell(sheet, count + 20 + 5, 3);
				cell93.setCellStyle(style3);
				HSSFCell cell94 = getCell(sheet, count + 20 + 5, 4);
				cell94.setCellStyle(style3);
				cell94.setCellValue("采购:");
				HSSFCell cell95 = getCell(sheet, count + 20 + 5, 5);
				cell95.setCellStyle(style3);
				HSSFCell cell96 = getCell(sheet, count + 20 + 5, 6);
				cell96.setCellStyle(style3);
				HSSFCell cell97 = getCell(sheet, count + 20 + 5, 7);
				cell97.setCellValue("财务:");
				cell97.setCellStyle(style3);
				HSSFCell cell98 = getCell(sheet, count + 20 + 5, 8);
				cell98.setCellStyle(style3);
				HSSFCell cell99 = getCell(sheet, count + 20 + 5, 9);
				cell99.setCellStyle(style3);
				HSSFCell cell990 = getCell(sheet, count + 20 + 5, 10);
				cell990.setCellStyle(style3);
				// ================签字end=========================
			}

		}
	}

}
