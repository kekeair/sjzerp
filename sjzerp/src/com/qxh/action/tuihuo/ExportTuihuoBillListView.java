package com.qxh.action.tuihuo;

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

import com.qxh.exmodel.ExportTuihuoModel;
import com.qxh.model.User;
import com.qxh.service.TuihuoService;
import com.qxh.utils.SessionUtil;

public class ExportTuihuoBillListView extends AbstractExcelView {

	private TuihuoService tuihuoService;
	
	public void setTuihuoService(TuihuoService tuihuoService) {
		this.tuihuoService = tuihuoService;
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> arg0, HSSFWorkbook workbook, HttpServletRequest req,HttpServletResponse response) throws Exception {
		String state = req.getParameter("state");
		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String supplierId = req.getParameter("supplierId");
		String typeNm = "退库汇总单";
		String fname = typeNm + ".xls";
		response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fname, "UTF-8"));
		response.setContentType("application/msexcel");
		HSSFSheet sheet = workbook.createSheet(typeNm);
		// sheet.setDefaultRowHeight((short)400);

		// 设置列宽
		sheet.setColumnWidth(0, 3000);//日期
		sheet.setColumnWidth(1, 5000);//单据编号
		sheet.setColumnWidth(2, 5000);//客户
		sheet.setColumnWidth(3, 5000);//物料编码
		sheet.setColumnWidth(4, 6000);//物料名称
		sheet.setColumnWidth(5, 3081);//规格
		sheet.setColumnWidth(6, 3081);//单位
		sheet.setColumnWidth(7, 3081);//数量
		sheet.setColumnWidth(8, 3081);//单价
		sheet.setColumnWidth(9, 3081);//金额

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
		cell4.setCellValue("退库汇总单");
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
		cell19.setCellValue("金额");
		// =============列名称end===============================
		List<ExportTuihuoModel> list=tuihuoService.exportTuihuoList(state,stime,etime,
				supplierId,user.getStructId());
		if (list==null||list.size()==0) {
			return;
		}
		int l=list.size();
		int preBillId=0;
		double totalMoney=0;
		int count=2;
		for (int i = 0; i < l; i++) {
			ExportTuihuoModel model=list.get(i);
			totalMoney+=model.getMoney();
			if(i==0){
				preBillId=model.getBillId();
			}else{
				int nowBillId=model.getBillId();
				if(nowBillId==preBillId){
					model.setBillDate("");
					model.setBillCode("");
					model.setSupplierNm("");
				}else{
					preBillId=model.getBillId();
				}
			}
			//日期
			HSSFCell cell20 = getCell(sheet, count, 0);
			cell20.setCellStyle(style1);
			cell20.setCellValue(model.getBillDate());
			//单据编号
			HSSFCell cell21 = getCell(sheet, count, 1);
			cell21.setCellStyle(style1);
			cell21.setCellValue(model.getBillCode());
			//客户
			HSSFCell cell22 = getCell(sheet, count, 2);
			cell22.setCellStyle(style1);
			cell22.setCellValue(model.getSupplierNm());
			//物料编码
			HSSFCell cell23 = getCell(sheet, count, 3);
			cell23.setCellStyle(style1);
			cell23.setCellValue(model.getGoodsCode());
			//物料名称
			HSSFCell cell24 = getCell(sheet, count, 4);
			cell24.setCellStyle(style1);
			cell24.setCellValue(model.getGoodsNm());
			//规格
			HSSFCell cell25 = getCell(sheet, count,5);
			cell25.setCellStyle(style1);
			cell25.setCellValue(model.getSpec());
			//单位
			HSSFCell cell26 = getCell(sheet, count,6);
			cell26.setCellStyle(style1);
			cell26.setCellValue(model.getUnitNm());
			//数量
			HSSFCell cell27 = getCell(sheet, count,7);
			cell27.setCellStyle(style1);
			cell27.setCellValue(model.getNum());
			//单价
			HSSFCell cell28 = getCell(sheet, count,8);
			cell28.setCellStyle(style1);
			cell28.setCellValue(model.getPrice());
			//金额
			HSSFCell cell29 = getCell(sheet, count,9);
			cell29.setCellStyle(style1);
			cell29.setCellValue(model.getMoney());
			
			count++;
			if(i==l-1){
				//日期
				HSSFCell cell30 = getCell(sheet, count, 0);
				cell30.setCellStyle(style);
				cell30.setCellValue("合计");
				//单据编号
				HSSFCell cell31 = getCell(sheet, count, 1);
				cell31.setCellStyle(style);
				cell31.setCellValue("");
				//客户
				HSSFCell cell32 = getCell(sheet, count, 2);
				cell32.setCellStyle(style);
				cell32.setCellValue("");
				//物料编码
				HSSFCell cell33 = getCell(sheet, count, 3);
				cell33.setCellStyle(style);
				cell33.setCellValue("");
				//物料名称
				HSSFCell cell34 = getCell(sheet, count, 4);
				cell34.setCellStyle(style);
				cell34.setCellValue("");
				//规格
				HSSFCell cell35 = getCell(sheet, count,5);
				cell35.setCellStyle(style);
				cell35.setCellValue("");
				//单位
				HSSFCell cell36 = getCell(sheet, count,6);
				cell36.setCellStyle(style);
				cell36.setCellValue("");
				//数量
				HSSFCell cell37 = getCell(sheet, count,7);
				cell37.setCellStyle(style);
				cell37.setCellValue("");
				//单价
				HSSFCell cell38 = getCell(sheet, count,8);
				cell38.setCellStyle(style);
				cell38.setCellValue("");
				//金额
				HSSFCell cell39 = getCell(sheet, count,9);
				cell39.setCellStyle(style);
				cell39.setCellValue(totalMoney);
			}
		}
	}
}
