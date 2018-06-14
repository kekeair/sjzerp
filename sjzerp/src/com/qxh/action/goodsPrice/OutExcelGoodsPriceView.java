package com.qxh.action.goodsPrice;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.qxh.model.SupplierGoods;
import com.qxh.service.GoodsPriceService;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * 物料参数列表导出
 */
public class OutExcelGoodsPriceView extends AbstractExcelView {
	private GoodsPriceService goodsPriceService;
	
	public void setGoodsPriceService(GoodsPriceService goodsPriceService) {
		this.goodsPriceService = goodsPriceService;
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> arg0, HSSFWorkbook workbook, HttpServletRequest req,
			HttpServletResponse response) throws Exception {
		//获取查询条件
		String name = req.getParameter("name");
		String code = req.getParameter("code");
		String kindCode = req.getParameter("kindCode");
		String supplierId =req.getParameter("supplierId");
		if (name==null)
			name="";
		if(code==null)
			code="";
		if(kindCode.equals("-1"))
			kindCode="";
		String typeNm = "物料价格列表导出";
		String fname = typeNm + ".xls";
		response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fname, "UTF-8"));
		response.setContentType("application/msexcel");
		HSSFSheet sheet = workbook.createSheet(typeNm);
		sheet.setDefaultRowHeight((short)400);
		// 设置列宽
		sheet.setColumnWidth(0, 3641);
		sheet.setColumnWidth(1, 3641);
		sheet.setColumnWidth(2, 3641);
		sheet.setColumnWidth(3, 3641);
		sheet.setColumnWidth(4, 3641);
		//short a =400;
		//sheet.setDefaultRowHeight(a);

		
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

		//通过查询条件获取物料参数列表
		int structId=(Integer)SessionUtil.getSession().getAttribute("structId");
		Result result = goodsPriceService.getGoodsPriceOutExcel(name, code, kindCode, supplierId, structId);
		 List<SupplierGoods> list = (List<SupplierGoods>)result.getData();
		if (list == null || list.size() == 0) {
			return;
		}
		
		//----------------列定义开始----------------------------
		HSSFCell cell0 = getCell(sheet,0, 0);
		cell0.setCellStyle(style);
		cell0.setCellValue("atNo");
		HSSFCell cell1 = getCell(sheet,0, 1);
		cell1.setCellStyle(style);
		cell1.setCellValue("物料编码");
		HSSFCell cell2 = getCell(sheet,0, 2);
		cell2.setCellStyle(style);
		cell2.setCellValue("物料名称");
		HSSFCell cell3 = getCell(sheet,0, 3);
		cell3.setCellStyle(style);
		cell3.setCellValue("原始价格");
		HSSFCell cell4 = getCell(sheet,0, 4);
		cell4.setCellStyle(style);
		cell4.setCellValue("新置价格");

		// =============列名称end===============================
	  for (int i = 0; i < list.size(); i++) {
		  
		    SupplierGoods supplierGoods = list.get(i);
		    HSSFCell cell10 = getCell(sheet,i+1, 0);
			cell10.setCellStyle(style1);
			cell10.setCellValue(supplierGoods.getAtNo());
			HSSFCell cell14 = getCell(sheet,i+1, 1);
			cell14.setCellStyle(style1);
			cell14.setCellValue(supplierGoods.getGoodsCode());
			HSSFCell cell11 = getCell(sheet,i+1, 2);
			cell11.setCellStyle(style1);
			cell11.setCellValue(supplierGoods.getGoodsNm());
			HSSFCell cell12 = getCell(sheet, i+1, 3);
			cell12.setCellStyle(style1);
			cell12.setCellValue(supplierGoods.getFrontPrice());
			HSSFCell cell13 = getCell(sheet, i+1, 4);
			cell13.setCellStyle(style1);
			cell13.setCellValue(supplierGoods.getFrontPrice());
		}
	}
}
