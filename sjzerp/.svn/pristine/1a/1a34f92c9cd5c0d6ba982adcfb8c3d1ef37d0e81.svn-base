package com.qxh.action.purchase;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.qxh.exmodel.ExcelDemandBillByCondition;
import com.qxh.model.ExcelPurchaseBillByCondition;
import com.qxh.model.User;
import com.qxh.service.DemandService;
import com.qxh.service.PurchaseService;
import com.qxh.utils.SessionUtil;

/**
 * 
 * @Description:[采购单汇总导出]
 * 
 * @author:kekeair
 * 
 * @time:2017年3月4日 下午3:10:18
 * 
 */
public class ExportOutPurchaseBillByStateAndTimeView extends AbstractExcelView {
	private PurchaseService purchaseService;

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> arg0, HSSFWorkbook workbook, HttpServletRequest req,
			HttpServletResponse response) throws Exception {
		String state = req.getParameter("state");
		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String customerId = req.getParameter("customerId");

		String typeNm = "采购入库单";
		String fname = typeNm + ".xls";
		response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fname, "UTF-8"));
		response.setContentType("application/msexcel");
		HSSFSheet sheet = workbook.createSheet(typeNm);
		// sheet.setDefaultRowHeight((short)400);

		// 设置列宽
		sheet.setColumnWidth(0, 4000);// 日期
		sheet.setColumnWidth(1, 5000);// 单据编号
		sheet.setColumnWidth(2, 5000);// 编号
		sheet.setColumnWidth(3, 5000);// 物料名称
		sheet.setColumnWidth(4, 6000);// 客户
		sheet.setColumnWidth(5, 3081);// 规格
		sheet.setColumnWidth(6, 3081);// 单位
		sheet.setColumnWidth(7, 3081);// 数量
		sheet.setColumnWidth(8, 3081);// 单价
		sheet.setColumnWidth(9, 3081);// 金额
		sheet.setColumnWidth(10, 3081);// 备注

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

		User user = (User) SessionUtil.getSession().getAttribute("user");
        //
		List<ExcelPurchaseBillByCondition> list = purchaseService.getExcelOutPurchaseListHBill(state, stime, etime, user.getStructId(),
				user.getRoleId(), customerId);
		if (list == null || list.size() == 0) {
			return;
		}
		// =============start-title=================================
		HSSFCell cell0 = getCell(sheet, 0, 0);
		cell0.setCellStyle(style3);
		HSSFCell cell1 = getCell(sheet, 0, 1);
		cell1.setCellStyle(style3);
		HSSFCell cell2 = getCell(sheet, 0, 2);
		cell2.setCellStyle(style3);
		HSSFCell cell3 = getCell(sheet, 0, 3);
		cell3.setCellStyle(style3);
		HSSFCell cell4 = getCell(sheet, 0, 4);
		cell4.setCellStyle(style3);
		cell4.setCellValue("客户申报汇总单");
		HSSFCell cell5 = getCell(sheet, 0, 5);
		cell5.setCellStyle(style3);
		HSSFCell cell6 = getCell(sheet, 0, 6);
		cell6.setCellStyle(style3);
		HSSFCell cell7 = getCell(sheet, 0, 7);
		cell7.setCellStyle(style3);
		HSSFCell cell8 = getCell(sheet, 0, 8);
		cell8.setCellStyle(style3);
		HSSFCell cell9 = getCell(sheet, 0, 9);
		cell9.setCellStyle(style3);
		HSSFCell cell110 = getCell(sheet, 0, 10);
		cell110.setCellStyle(style3);
		// =============end-title=================================

		// =============列名称start===============================
		HSSFCell cell10 = getCell(sheet, 1, 0);
		cell10.setCellStyle(style);
		cell10.setCellValue("日期");
		HSSFCell cell11 = getCell(sheet, 1, 1);
		cell11.setCellStyle(style);
		cell11.setCellValue("单据编号");
		HSSFCell cell12 = getCell(sheet, 1, 2);
		cell12.setCellStyle(style);
		cell12.setCellValue("客户");
		HSSFCell cell13 = getCell(sheet, 1, 3);
		cell13.setCellStyle(style);
		cell13.setCellValue("物料编码");
		HSSFCell cell14 = getCell(sheet, 1, 4);
		cell14.setCellStyle(style);
		cell14.setCellValue("物料名称");
		HSSFCell cell15 = getCell(sheet, 1, 5);
		cell15.setCellStyle(style);
		cell15.setCellValue("规格");
		HSSFCell cell16 = getCell(sheet, 1, 6);
		cell16.setCellStyle(style);
		cell16.setCellValue("单位");
		HSSFCell cell17 = getCell(sheet, 1, 7);
		cell17.setCellStyle(style);
		cell17.setCellValue("数量");
		HSSFCell cell18 = getCell(sheet, 1, 8);
		cell18.setCellStyle(style);
		cell18.setCellValue("单价");
		HSSFCell cell19 = getCell(sheet, 1, 9);
		cell19.setCellStyle(style);
		cell19.setCellValue("备注");
		HSSFCell cell20 = getCell(sheet, 1, 10);
		cell20.setCellStyle(style);
		cell20.setCellValue("金额");
		// =============列名称end===============================

		int count = 2;// 换行控制
		Double totalMoney = 0.00;// 总金额

		// 客户循环
		for (int i = 0; i < list.size(); i++) {
			ExcelPurchaseBillByCondition excel = list.get(i);
			// 日期
			HSSFCell cell220 = getCell(sheet, count, 0);
			cell220.setCellStyle(style1);
			cell220.setCellValue(excel.getTime());
			// 单据编号
			HSSFCell cell21 = getCell(sheet, count, 1);
			cell21.setCellStyle(style1);
			cell21.setCellValue(excel.getBill());
			// 客户
			HSSFCell cell22 = getCell(sheet, count, 2);
			cell22.setCellStyle(style1);
			cell22.setCellValue(excel.getName());
			// 物料编码
			HSSFCell cell23 = getCell(sheet, count, 3);
			cell23.setCellStyle(style1);
			cell23.setCellValue(excel.getCode());
			// 物料名称
			HSSFCell cell24 = getCell(sheet, count, 4);
			cell24.setCellStyle(style1);
			cell24.setCellValue(excel.getGoodsNm());
			// 规格
			HSSFCell cell25 = getCell(sheet, count, 5);
			cell25.setCellStyle(style1);
			cell25.setCellValue(excel.getSpec());
			// 单位
			HSSFCell cell26 = getCell(sheet, count, 6);
			cell26.setCellStyle(style1);
			cell26.setCellValue(excel.getUnitNm());
			// 数量
			HSSFCell cell27 = getCell(sheet, count, 7);
			cell27.setCellStyle(style1);
			cell27.setCellValue(excel.getOrderNum());
			// 单价
			HSSFCell cell28 = getCell(sheet, count, 8);
			cell28.setCellStyle(style1);
			cell28.setCellValue(excel.getPrice());
			// 备注
			HSSFCell cell29 = getCell(sheet, count, 9);
			cell29.setCellStyle(style1);
			cell29.setCellValue(excel.getRemark());
			// 金额
			HSSFCell cell300 = getCell(sheet, count, 10);
			cell300.setCellStyle(style1);
			cell300.setCellValue(excel.getMoney());

			totalMoney += excel.getMoney();
			count = count + 1;
		}

		// =============总金额=======================
		HSSFCell cell30 = getCell(sheet, count, 0);
		cell30.setCellStyle(style);
		cell30.setCellValue("合计:");
		HSSFCell cell31 = getCell(sheet, count, 1);
		cell31.setCellStyle(style);
		HSSFCell cell32 = getCell(sheet, count, 2);
		cell32.setCellStyle(style);
		HSSFCell cell33 = getCell(sheet, count, 3);
		cell33.setCellStyle(style);
		HSSFCell cell34 = getCell(sheet, count, 4);
		cell34.setCellStyle(style);
		HSSFCell cell35 = getCell(sheet, count, 5);
		cell35.setCellStyle(style);
		HSSFCell cell36 = getCell(sheet, count, 6);
		cell36.setCellStyle(style);
		HSSFCell cell37 = getCell(sheet, count, 7);
		cell37.setCellStyle(style);
		HSSFCell cell38 = getCell(sheet, count, 8);
		cell38.setCellStyle(style);
		HSSFCell cell39 = getCell(sheet, count, 9);
		cell39.setCellStyle(style);
		HSSFCell cell400 = getCell(sheet, count, 10);
		cell400.setCellStyle(style);
		cell400.setCellValue(totalMoney);

	}
}
