package com.qxh.action.tagGoods;

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

import com.qxh.model.TagGoods;
import com.qxh.service.TagGoodsService;
/**
 * 
 * @author Mr chen
 * @name : ExportOutBillView
 * @todo : [标签价格导出]
 * 
 * 创建时间 ： 2017年1月11日下午6:01:55
 */
public class ExportOutTageGoodsBillView extends AbstractExcelView{
	
	private TagGoodsService tagGoodsService;
	
	public void setTagGoodsService(TagGoodsService tagGoodsService) {
		this.tagGoodsService = tagGoodsService;
	}


	@Override
	protected void buildExcelDocument(Map<String, Object> arg0, HSSFWorkbook workbook, HttpServletRequest req,
			HttpServletResponse response) throws Exception {
		
		String tagId = req.getParameter("tagId");          
		String tagNm = req.getParameter("tagNm");          
		
		List<TagGoods> list=tagGoodsService.getTagGoodsPriceListByTagId(tagId);
		
		String typeNm = tagNm+"的物料标签价格";
		String fname=typeNm+".xls";
		response.setHeader("Content-disposition", "attachment; filename="+ java.net.URLEncoder.encode(fname, "UTF-8"));
		response.setContentType("application/msexcel");
		HSSFSheet sheet = workbook.createSheet(typeNm);
		
		//设置列宽
		sheet.setColumnWidth(0, 3000); 
		sheet.setColumnWidth(1, 5000);   
		sheet.setColumnWidth(1, 8000);   
		sheet.setColumnWidth(2, 3000);   
	   
		//格式
		HSSFCellStyle style=workbook.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCellStyle style1=workbook.createCellStyle();
		style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style1.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCellStyle style2=workbook.createCellStyle();
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font=workbook.createFont();
		font.setColor(HSSFColor.RED.index);
		style2.setFont(font);
		
		 	HSSFCell cell10=getCell(sheet, 0, 0);
		    cell10.setCellStyle(style);
		    cell10.setCellValue("atNo"); 
		    HSSFCell cell11=getCell(sheet, 0, 1);
		    cell11.setCellStyle(style);
		    cell11.setCellValue("标签物料编码");  
		    HSSFCell cell12=getCell(sheet, 0, 2);
		    cell12.setCellStyle(style);
		    cell12.setCellValue("标签物料名称");  
		    HSSFCell cell13=getCell(sheet, 0, 3);
		    cell13.setCellStyle(style);
		    cell13.setCellValue("标签物料价格"); 
		    
		int count  = 1;
		for(int i=0;i<list.size();i++){
				TagGoods tagGoods = list.get(i);          //当前元素
				HSSFCell cell20=getCell(sheet, count, 0);
				cell20.setCellValue(tagGoods.getAtNo());
				cell20.setCellStyle(style1);
				HSSFCell cell21=getCell(sheet, count, 1);
				cell21.setCellValue(tagGoods.getGoodsCode());
				cell21.setCellStyle(style1);
				HSSFCell cell22=getCell(sheet, count, 2);
				cell22.setCellValue(tagGoods.getTagGoodsNm());
				cell22.setCellStyle(style1);
				HSSFCell cell23=getCell(sheet,count, 3);
				cell23.setCellValue(tagGoods.getTagGoodsPrice());
				cell23.setCellStyle(style1);
				
				count++;
			}
				
	}

}
