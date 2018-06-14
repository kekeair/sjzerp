package com.qxh.action.purchase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.qxh.exmodel.BillAndCustomTeam;
import com.qxh.service.PurchaseService;
import com.qxh.utils.Result;

/**
 * 
 * @author Mr chen
 * @name : ExportOutBillView
 * @todo : [采购明细导出]
 * 
 *       创建时间 ： 2016年11月25日上午9:43:44
 */
public class ExportOutBillParticularsView extends AbstractExcelView {
	private PurchaseService purchaseService;

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
	
	@Override
	protected void buildExcelDocument(Map<String, Object> arg0, HSSFWorkbook workbook, HttpServletRequest req,
			HttpServletResponse response) throws Exception {
		String teamBillId = req.getParameter("teamBillId");
		String billId = req.getParameter("bill_Id");

		String typeNm = "采购明细单";
		String fname = typeNm + ".xls";
		response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fname, "UTF-8"));
		response.setContentType("application/msexcel");
		HSSFSheet sheet = workbook.createSheet(typeNm);
		sheet.setDefaultRowHeight((short)400);
		// 设置列宽
		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 3081);
		sheet.setColumnWidth(2, 3762);
		sheet.setColumnWidth(3, 3081);
		sheet.setColumnWidth(4, 3081);
		sheet.setColumnWidth(5, 3081);
		sheet.setColumnWidth(6, 2681);
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
		style7.setFont(font7);// 把字体应用到当前的样式

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
		style8.setFont(font8);
		
		
		
		Result result = purchaseService.getExclList(teamBillId, billId);
		List<BillAndCustomTeam> list = (List<BillAndCustomTeam>) result.getData();
		if (list == null || list.size() == 0) {
			return;
		}
		
		int l=list.size();
		int preSupplierId=0;
		
		int count = 0;//控制循环s
		Map<Integer, List<BillAndCustomTeam>> listMap=new HashMap<>();
		for (int i = 0; i < l; i++) {
			
			BillAndCustomTeam goods=list.get(i);
			if(i==0){
				preSupplierId=goods.getAtNo();
				List<BillAndCustomTeam> supplierList=new ArrayList<>();
				supplierList.add(goods);
				listMap.put(preSupplierId, supplierList);
			}else{
				int nowSupplierId=goods.getAtNo();
				if(nowSupplierId==preSupplierId){
					List<BillAndCustomTeam> supplierList=listMap.get(nowSupplierId);
					supplierList.add(goods);
				}else{
					//设置excel
					count=setExcel(listMap.get(preSupplierId), sheet, style1, style2, 
							style3, style4, style5, style6,style7,style8,count);
					count = count + 34;
					//重置preSupplierId
					preSupplierId=nowSupplierId;
					List<BillAndCustomTeam> supplierList=new ArrayList<>();
					supplierList.add(goods);
					listMap.put(nowSupplierId, supplierList);
				}
				if(i==l-1){
					count=setExcel(listMap.get(nowSupplierId), sheet, style1, style2, 
							style3, style4, style5, style6,style7,style8,count);
					count = count + 34;
				}
			}
		}
	}

	private int setExcel(List<BillAndCustomTeam> list,HSSFSheet sheet,
			HSSFCellStyle style,HSSFCellStyle style2,HSSFCellStyle style3,
			HSSFCellStyle style4,HSSFCellStyle style5,HSSFCellStyle style6,
			HSSFCellStyle style7,HSSFCellStyle style8,int count){
		int size = list.size();
		int len = 0;// len控制大循环的次数
		if (size <= 30) {
			len = 1;
		} else if (size % 30 != 0) {
			len = size / 30 + 1;
		} else {
			len = size / 30;
		}

		int countSize = -30; // 控制集合循环的下标

		for (int i = 0; i < len; i++) {

			//count = count + 34;
			countSize = countSize + 30;
			// =============start-title=============
			HSSFRow row = sheet.createRow(count);
			row.setHeight((short)400);
			HSSFCell cell00 = getCell(sheet, count, 0);
			cell00.setCellStyle(style3);
			HSSFCell cell01 = getCell(sheet, count, 1);
			cell01.setCellStyle(style3);
			HSSFCell cell02 = getCell(sheet, count, 2);
			cell02.setCellStyle(style3);
			HSSFCell cell03 = getCell(sheet, count, 3);
			cell03.setCellStyle(style7);
			cell03.setCellValue(" 北 京 祥 鹤 物 流 股 份 有 限 公 司");
			HSSFCell cell04 = getCell(sheet, count, 4);
			cell04.setCellStyle(style3);
			HSSFCell cell05 = getCell(sheet, count, 5);
			cell05.setCellStyle(style3);
			HSSFCell cell06 = getCell(sheet, count, 6);
			cell06.setCellStyle(style3);
			
			HSSFRow row0 = sheet.createRow(count+1);
			row0.setHeight((short)400);
			HSSFCell cell0 = getCell(sheet, count+1, 0);
			cell0.setCellStyle(style3);
			HSSFCell cell1 = getCell(sheet, count+1, 1);
			cell1.setCellStyle(style3);
			HSSFCell cell2 = getCell(sheet, count+1, 2);
			cell2.setCellStyle(style3);
			HSSFCell cell3 = getCell(sheet, count+1, 3);
			cell3.setCellStyle(style8);
			cell3.setCellValue(" 采购明细单 " + "(" + list.get(i).getUserNm() + ")");
			HSSFCell cell4 = getCell(sheet, count+1, 4);
			cell4.setCellStyle(style3);
			HSSFCell cell5 = getCell(sheet, count+1, 5);
			cell5.setCellStyle(style3);
			HSSFCell cell6 = getCell(sheet, count+1, 6);
			cell6.setCellStyle(style3);
			// =============end-title=================================

			// =============类型属性start===============================
			String teamNm = list.get(0).getTeamNm();
			String code = list.get(0).getCodeNm();
			String customNm = list.get(0).getCustomNm();
			HSSFRow row1 = sheet.createRow(count+2);
			row1.setHeight((short)400);
			HSSFCell cell40 = getCell(sheet, count + 2, 0);
			cell40.setCellStyle(style3);
			cell40.setCellValue("客戶:");
			HSSFCell cell41 = getCell(sheet, count + 2, 1);
			cell41.setCellValue("   " + customNm + "/" + teamNm);
			cell41.setCellStyle(style3);
			HSSFCell cell42 = getCell(sheet, count + 2, 2);
			cell42.setCellStyle(style3);
			HSSFCell cell43 = getCell(sheet, count + 2, 3);
			cell43.setCellValue("账单:" + code);
			cell43.setCellStyle(style3);
			HSSFCell cell44 = getCell(sheet, count + 2, 4);
			cell44.setCellStyle(style3);
			HSSFCell cell45 = getCell(sheet, count + 2, 5);
			cell45.setCellValue("日期:");
			cell45.setCellStyle(style3);
			HSSFCell cell46 = getCell(sheet, count + 2, 6);
			cell46.setCellStyle(style3);
			cell46.setCellValue(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
			// =============类型属性end===============================

			// =============列名称start===============================
			HSSFRow row2 = sheet.createRow(count+3);
			row2.setHeight((short)400);
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
			HSSFCell cell15 = getCell(sheet, count + 3, 5);
			cell15.setCellStyle(style);
			cell15.setCellValue("单位");
			HSSFCell cell16 = getCell(sheet, count + 3, 6);
			cell16.setCellStyle(style);
			cell16.setCellValue("备注");
			// =============列名称end===============================

			if (size < 30) {
				// 获取的集合进行遍历
				for (int j = 0; j < size; j++) {
					BillAndCustomTeam billAndCustomTeam = list.get(countSize + j);
					HSSFRow row3 = sheet.createRow(count+j+4);
					row3.setHeight((short)400);
					HSSFCell cell20 = getCell(sheet, count + j + 4, 0);
					cell20.setCellStyle(style4);
					cell20.setCellValue(billAndCustomTeam.getOrderIndex());
					HSSFCell cell21 = getCell(sheet, count + j + 4, 1);
					cell21.setCellStyle(style4);
					cell21.setCellValue(billAndCustomTeam.getCode());
					HSSFCell cell22 = getCell(sheet, count + j + 4, 2);
					cell22.setCellStyle(style4);
					cell22.setCellValue(billAndCustomTeam.getGoodsNm());
					HSSFCell cell23 = getCell(sheet, count + j + 4, 3);
					cell23.setCellStyle(style4);
					cell23.setCellValue(billAndCustomTeam.getSpec());
					HSSFCell cell24 = getCell(sheet, count + j + 4, 4);
					cell24.setCellStyle(style4);
					cell24.setCellValue(billAndCustomTeam.getOrderNum());
					HSSFCell cell25 = getCell(sheet, count + j + 4, 5);
					cell25.setCellStyle(style4);
					cell25.setCellValue(billAndCustomTeam.getUnitNm());
					HSSFCell cell26 = getCell(sheet, count + j + 4, 6);
					cell26.setCellStyle(style4);
					cell26.setCellValue(billAndCustomTeam.getRewmark());

				}
			} else {
				for (int j = 0; j < 30; j++) {
					BillAndCustomTeam billAndCustomTeam = list.get(count + j);
					HSSFRow row4 = sheet.createRow(count+j+4);
					row4.setHeight((short)400);
					HSSFCell cell20 = getCell(sheet, count + j + 4, 0);
					cell20.setCellStyle(style4);
					cell20.setCellValue(billAndCustomTeam.getOrderIndex());
					HSSFCell cell21 = getCell(sheet, count + j + 4, 1);
					cell21.setCellStyle(style4);
					cell21.setCellValue(billAndCustomTeam.getCode());
					HSSFCell cell22 = getCell(sheet, count + j + 4, 2);
					cell22.setCellStyle(style4);
					cell22.setCellValue(billAndCustomTeam.getGoodsNm());
					HSSFCell cell23 = getCell(sheet, count + j + 4, 3);
					cell23.setCellStyle(style4);
					cell23.setCellValue(billAndCustomTeam.getSpec());
					HSSFCell cell24 = getCell(sheet, count + j + 4, 4);
					cell24.setCellStyle(style4);
					cell24.setCellValue(billAndCustomTeam.getOrderNum());
					HSSFCell cell25 = getCell(sheet, count + j + 4, 5);
					cell25.setCellStyle(style4);
					cell25.setCellValue(billAndCustomTeam.getUnitNm());
					HSSFCell cell26 = getCell(sheet, count + j + 4, 6);
					cell26.setCellStyle(style4);
					cell26.setCellValue(billAndCustomTeam.getRewmark());
				}
				size = size - 30;
				count =count +34;
			}

		}
		return count;
	}
}
