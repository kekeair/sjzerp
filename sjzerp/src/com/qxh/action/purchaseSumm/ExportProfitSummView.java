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

import com.qxh.model.ProfitSumm;
import com.qxh.model.PurchaseSumm;
import com.qxh.model.User;
import com.qxh.service.PurchaseSummService;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * 
 * @Description:[毛利表汇总导出]
 * 
 * @author:kekeair
 * 
 * @time:2017年3月7日 上午11:03:35
 * 
 */
public class ExportProfitSummView extends AbstractExcelView {

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
		String kindCode = req.getParameter("kindCode");

		String typeNm = "毛利汇总单";
		String fname = typeNm + ".xls";
		response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fname, "UTF-8"));
		response.setContentType("application/msexcel");
		HSSFSheet sheet = workbook.createSheet(typeNm);
		// sheet.setDefaultRowHeight((short)400);

		// 设置列宽
		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 3081);
		sheet.setColumnWidth(2, 3762);
		sheet.setColumnWidth(3, 3081);
		sheet.setColumnWidth(4, 3081);
		sheet.setColumnWidth(5, 3081);
		sheet.setColumnWidth(6, 3081);
		sheet.setColumnWidth(7, 3081);
		sheet.setColumnWidth(8, 3081);
		sheet.setColumnWidth(9, 3081);
		//sheet.setColumnWidth(10, 3081);
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

		User user = (User) SessionUtil.getSession().getAttribute("user");
		Result result = purchaseSummService.getProfitSummDList(customerIds, stime, etime, user.getStructId(), goodsNm,kindCode);
		Map data = (Map) result.getData();
		List<ProfitSumm> list = (List<ProfitSumm>) data.get("list");
		ProfitSumm profitSummT = (ProfitSumm) data.get("profitSumm");
		// =============start-title=============================
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
		HSSFCell cell5 = getCell(sheet, 0, 5);
		cell5.setCellStyle(style3);
		cell5.setCellValue("毛利汇总单");
		HSSFCell cell6 = getCell(sheet, 0, 6);
		cell6.setCellStyle(style3);
		HSSFCell cell7 = getCell(sheet, 0, 7);
		cell7.setCellStyle(style3);
		HSSFCell cell8 = getCell(sheet, 0, 8);
		cell8.setCellStyle(style3);
		HSSFCell cell9 = getCell(sheet, 0, 9);
		cell9.setCellStyle(style3);
		/*HSSFCell cell110 = getCell(sheet, 0, 10);
		cell110.setCellStyle(style3);*/
		// =============end-title===============================

		// =============列名称start===============================
		HSSFCell cell10 = getCell(sheet, 1, 0);
		cell10.setCellStyle(style);
		cell10.setCellValue("物料名称");
		HSSFCell cell11 = getCell(sheet, 1, 1);
		cell11.setCellStyle(style);
		cell11.setCellValue("单位");
		HSSFCell cell12 = getCell(sheet, 1, 2);
		cell12.setCellStyle(style);
		cell12.setCellValue("类型");
		HSSFCell cell13 = getCell(sheet, 1, 3);
		cell13.setCellStyle(style);
		cell13.setCellValue("数量");
		HSSFCell cell14 = getCell(sheet, 1, 4);
		cell14.setCellStyle(style);
		cell14.setCellValue("成本价");
		HSSFCell cell15 = getCell(sheet, 1, 5);
		cell15.setCellStyle(style);
		cell15.setCellValue("成本");
		HSSFCell cell16 = getCell(sheet, 1, 6);
		cell16.setCellStyle(style);
		cell16.setCellValue("售价");
		HSSFCell cell17 = getCell(sheet, 1, 7);
		cell17.setCellStyle(style);
		cell17.setCellValue("销售收入");
		HSSFCell cell18 = getCell(sheet, 1, 8);
		cell18.setCellStyle(style);
		cell18.setCellValue("毛利");
		HSSFCell cell19 = getCell(sheet, 1, 9);
		cell19.setCellStyle(style);
		cell19.setCellValue("毛利率");
		/*HSSFCell cellx110 = getCell(sheet, 1, 10);
		cellx110.setCellStyle(style);
		cellx110.setCellValue("毛利率");*/
		// =============列名称end===============================

		if (list == null || list.size() == 0) {
			return;
		}
		int l = list.size();
		for (int i = 0; i < l; i++) {
			ProfitSumm profitSumm = list.get(i);
			HSSFCell cell20 = getCell(sheet, i + 2, 0);
			cell20.setCellStyle(style1);
			cell20.setCellValue(profitSumm.getGoodsNm());
			HSSFCell cell21 = getCell(sheet, i + 2, 1);
			cell21.setCellStyle(style1);
			cell21.setCellValue(profitSumm.getUnitNm());
			HSSFCell cell22 = getCell(sheet, i + 2, 2);
			cell22.setCellStyle(style1);
			cell22.setCellValue(profitSumm.getKindNm());
			HSSFCell cell23 = getCell(sheet, i + 2, 3);
			cell23.setCellStyle(style1);
			cell23.setCellValue(profitSumm.getOrderNum());
			HSSFCell cell24 = getCell(sheet, i + 2, 4);
			cell24.setCellStyle(style1);
			cell24.setCellValue(profitSumm.getpPrice());
			HSSFCell cell25 = getCell(sheet, i + 2, 5);
			cell25.setCellStyle(style1);
			cell25.setCellValue(profitSumm.getpMoney());
			HSSFCell cell26 = getCell(sheet, i + 2, 6);
			cell26.setCellStyle(style1);
			cell26.setCellValue(profitSumm.getdPirce());
			HSSFCell cell27 = getCell(sheet, i + 2, 7);
			cell27.setCellStyle(style1);
			cell27.setCellValue(profitSumm.getdMoney());
			HSSFCell cell28 = getCell(sheet, i + 2, 8);
			cell28.setCellStyle(style1);
			cell28.setCellValue(profitSumm.getProfitMoney());
			HSSFCell cell29 = getCell(sheet, i + 2, 9);
			cell29.setCellStyle(style1);
			cell29.setCellValue(profitSumm.getRate());
			/*HSSFCell cell210 = getCell(sheet, i + 2, 10);
			cell210.setCellStyle(style1);
			cell210.setCellValue();*/
		}
		HSSFCell cell20 = getCell(sheet, l + 2, 0);
		cell20.setCellStyle(style1);
		cell20.setCellValue(profitSummT.getGoodsNm());
		HSSFCell cell21 = getCell(sheet, l + 2, 1);
		cell21.setCellStyle(style1);
		HSSFCell cell22 = getCell(sheet, l + 2, 2);
		cell22.setCellStyle(style1);
		HSSFCell cell23 = getCell(sheet, l + 2, 3);
		cell23.setCellStyle(style1);
		HSSFCell cell24 = getCell(sheet, l + 2, 4);
		cell24.setCellStyle(style1);
		HSSFCell cell25 = getCell(sheet, l + 2, 5);
		cell25.setCellStyle(style1);
		cell25.setCellValue(profitSummT.getpMoney());
		HSSFCell cell26 = getCell(sheet, l + 2, 6);
		cell26.setCellStyle(style1);
		HSSFCell cell27 = getCell(sheet, l + 2, 7);
		cell27.setCellStyle(style1);
		cell27.setCellValue(profitSummT.getdMoney());
		HSSFCell cell28 = getCell(sheet, l + 2, 8);
		cell28.setCellStyle(style1);
		cell28.setCellValue(profitSummT.getProfitMoney());
		HSSFCell cell29 = getCell(sheet, l + 2, 9);
		cell29.setCellStyle(style1);
		cell29.setCellValue(profitSummT.getRate());
		/*HSSFCell cell210 = getCell(sheet, l + 2, 10);
		cell210.setCellStyle(style1);*/
		
	}
}
