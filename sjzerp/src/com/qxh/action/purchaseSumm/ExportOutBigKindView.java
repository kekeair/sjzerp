package com.qxh.action.purchaseSumm;

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

import com.qxh.model.BigKind;
import com.qxh.model.ProfitSumm;
import com.qxh.model.PurchaseSumm;
import com.qxh.model.User;
import com.qxh.service.PurchaseSummService;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * @Description:[分大类汇总导出]
 * @author:kekeair
 * @time:2017年4月14日 上午9:03:12
 */
public class ExportOutBigKindView extends AbstractExcelView {

	private PurchaseSummService purchaseSummService;

	public void setPurchaseSummService(PurchaseSummService purchaseSummService) {
		this.purchaseSummService = purchaseSummService;
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> arg0, HSSFWorkbook workbook, HttpServletRequest req,
			HttpServletResponse response) throws Exception {
		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String customerIds = req.getParameter("customerIds");
		String goodsNm = req.getParameter("goodsNm");

		String typeNm = "大类物料占比";
		String fname = typeNm + ".xls";
		response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fname, "UTF-8"));
		response.setContentType("application/msexcel");
		HSSFSheet sheet = workbook.createSheet(typeNm);
		// sheet.setDefaultRowHeight((short)400);

		// 设置列宽
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 4000);
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
		style3.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		User user = (User) SessionUtil.getSession().getAttribute("user");
		Result result = purchaseSummService.excelOutBigKind(goodsNm, stime, etime, user.getStructId(), customerIds);
		List<BigKind> list = (List<BigKind>)result.getData();
		List<String> customerNmList = purchaseSummService.getCustomerNmByCustomerIds(customerIds);
		String customerNms="";
		for (int i = 0; i < customerNmList.size(); i++) {
			customerNms = customerNms+" "+customerNmList.get(i);
		
		}
		// =============start-title=============================
		HSSFCell cell0 = getCell(sheet, 0, 0);
		cell0.setCellStyle(style3);
		HSSFCell cell1 = getCell(sheet, 0, 1);
		cell1.setCellStyle(style3);
		HSSFCell cell2 = getCell(sheet, 0, 2);
		cell2.setCellStyle(style3);
		HSSFCell cell3 = getCell(sheet, 0, 3);
		cell3.setCellStyle(style3);
		cell3.setCellValue("大类物料占比");
		HSSFCell cell4 = getCell(sheet, 0, 4);
		cell4.setCellStyle(style3);
		HSSFCell cell5 = getCell(sheet, 0, 5);
		cell5.setCellStyle(style3);
		// =============end-title===============================

		//==============title2-start============================
		HSSFCell cell10 = getCell(sheet, 1, 0);
		cell10.setCellStyle(style3);
		cell10.setCellValue("客户:");
		HSSFCell cell11 = getCell(sheet, 1, 1);
		cell11.setCellStyle(style3);
		cell11.setCellValue(customerNms);
		HSSFCell cell12 = getCell(sheet, 1, 2);
		cell12.setCellStyle(style3);
		HSSFCell cell13 = getCell(sheet, 1, 3);
		cell13.setCellStyle(style3);
		HSSFCell cell14 = getCell(sheet, 1, 4);
		cell14.setCellStyle(style3);
		HSSFCell cell15 = getCell(sheet, 1, 5);
		cell15.setCellStyle(style3);
		//==============title2-end==============================
		// =============列名称start===============================
		HSSFCell cell20 = getCell(sheet, 2, 0);
		cell20.setCellStyle(style);
		cell20.setCellValue("大类");
		HSSFCell cell21 = getCell(sheet, 2, 1);
		cell21.setCellStyle(style);
		cell21.setCellValue("占总成本");
		HSSFCell cell22 = getCell(sheet, 2, 2);
		cell22.setCellStyle(style);
		cell22.setCellValue("占比");
		HSSFCell cell23 = getCell(sheet, 2, 3);
		cell23.setCellStyle(style);
		cell23.setCellValue("占总收入");
		HSSFCell cell24 = getCell(sheet, 2, 4);
		cell24.setCellStyle(style);
		cell24.setCellValue("占比");
		HSSFCell cell25 = getCell(sheet, 2, 5);
		cell25.setCellStyle(style);
		cell25.setCellValue("单项毛利率");
		
		// =============列名称end===============================

		if (list == null || list.size() == 0) {
			return;
		}
		int l = list.size();
		for (int i = 0; i < l; i++) {
			 BigKind bigKind = list.get(i);
			HSSFCell cell30 = getCell(sheet, i + 3, 0);
			cell30.setCellStyle(style1);
			cell30.setCellValue(bigKind.getKindNm());
			HSSFCell cell31 = getCell(sheet, i + 3, 1);
			cell31.setCellStyle(style1);
			cell31.setCellValue(bigKind.getTotalPMoney());
			HSSFCell cell32 = getCell(sheet, i + 3, 2);
			cell32.setCellStyle(style1);
			cell32.setCellValue(bigKind.getPPercent());
			HSSFCell cell33 = getCell(sheet, i + 3, 3);
			cell33.setCellStyle(style1);
			cell33.setCellValue(bigKind.getTotalDMoney());
			HSSFCell cell34 = getCell(sheet, i + 3, 4);
			cell34.setCellStyle(style1);
			cell34.setCellValue(bigKind.getDPercent());
			HSSFCell cell35 = getCell(sheet, i + 3, 5);
			cell35.setCellStyle(style1);
			cell35.setCellValue(bigKind.getTotalProfit());
		}
	}
}
