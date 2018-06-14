package com.qxh.action.purchaseTotalD;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.qxh.exmodel.PurchaseTotalCustomer;
import com.qxh.exmodel.PurchaseTotalGoods;
import com.qxh.model.User;
import com.qxh.service.PurchaseTotalDService;
import com.qxh.service.UserService;
import com.qxh.utils.Result;
import com.qxh.utils.SessionUtil;

/**
 * 
 * @author Mr chen
 * @name : ExportOutPurchaseTotalDView
 * @todo : [采购明细汇总导出单]
 * 
 *       创建时间 ： 2016年12月3日上午8:53:34
 */
public class ExportOutPurchaseTotalDView extends AbstractExcelView {
	private PurchaseTotalDService purchaseTotalDService;

	public void setPurchaseTotalDService(PurchaseTotalDService purchaseTotalDService) {
		this.purchaseTotalDService = purchaseTotalDService;
	}

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> arg0, HSSFWorkbook workbook, HttpServletRequest req,
			HttpServletResponse response) throws Exception {

		String stime = req.getParameter("stime");
		String etime = req.getParameter("etime");
		String supplierId = req.getParameter("supplierId");
		String customerId = req.getParameter("customerId");
		if(StringUtils.isEmpty(customerId)){
			customerId="";
		}
		User user = (User) SessionUtil.getSession().getAttribute("user");
		Result purchaseTotalD = purchaseTotalDService.getPurchaseTotalD(stime, etime, user.getStructId(), supplierId,customerId);
		Map<String, Object> data = (Map<String, Object>) purchaseTotalD.getData();

		List<PurchaseTotalGoods> goodsList = (List<PurchaseTotalGoods>) data.get("goodsList");
		List<PurchaseTotalCustomer> customerList = (List<PurchaseTotalCustomer>) data.get("customerList");
		if(customerList == null || customerList.size()<=0){
			return;
		}
		
		if(goodsList == null || goodsList.size()<=0){
			return;
		}
		
		String typeNm = "采购汇总明细单";
		String fname = typeNm + ".xls";
		response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fname, "UTF-8"));
		response.setContentType("application/msexcel");
		HSSFSheet sheet = workbook.createSheet(typeNm);
		// 设置列宽
		sheet.setColumnWidth(0, 4602);
		sheet.setColumnWidth(1, 6600);
		int widthNum = customerList.size();
		// 列的个数
		for (int i = 2; i < widthNum + 2; i++) {
			sheet.setColumnWidth(i, 6500);
		}

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
		
		//字体大小加粗
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
		//字体大小不加粗
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
		// 把字体应用到当前的样式
		style8.setFont(font8);
		
		String userNm ="自采购";
		User spu = (User) userService.getUserById(supplierId).getData();
		if (spu != null){
			 userNm = spu.getUserNm();
		}
		
		String[] split = stime.split(":");
		String startTime = split[0];
		String[] split2 = etime.split(":");
		String endTime = split2[0];
		int centerNum = (widthNum + 2) / 2;
		int HeightNum = goodsList.size();// 行个数
		// 设置行高
		for (int i = 0; i < HeightNum + 4; i++) {
			HSSFRow createRow = sheet.createRow(i);
			createRow.setHeightInPoints(20);
		}
		// 控制行循环个数
		for (int i = 0; i < HeightNum + 4; i++) {
			// 控制列的循环个数
			for (int j = 0; j < widthNum + 2; j++) {
			if (i == 0) {// 日期
					if (j == centerNum) {
						HSSFCell cell = getCell(sheet, i, j);
						cell.setCellStyle(style8);
						cell.setCellValue("采 购 汇 总 明 细 单");
					} else {
						HSSFCell cell = getCell(sheet, i, j);
						cell.setCellStyle(style3);
					}
				} else if (i == 1) {// 日期
					if (j == 0) {
						HSSFCell cell = getCell(sheet, i, j);
						cell.setCellStyle(style3);
						cell.setCellValue("供应商:    ");
					} else if (j == 1) {
						HSSFCell cell = getCell(sheet, i, j);
						cell.setCellStyle(style3);
						cell.setCellValue(userNm);
					} else {
						HSSFCell cell = getCell(sheet, i, j);
						cell.setCellStyle(style3);
					}
				}else if (i == 2){
					if (j == 0) {
						HSSFCell cell = getCell(sheet, i, j);
						cell.setCellStyle(style3);
						cell.setCellValue("日    期:");
					} else if (j == 1) {
						HSSFCell cell = getCell(sheet, i, j);
						cell.setCellStyle(style3);
						cell.setCellValue(startTime + "至" + endTime);
					} else {
						HSSFCell cell = getCell(sheet, i, j);
						cell.setCellStyle(style3);
					}
				} else if (i == 3) {// 列头名称
					if (j == 0) {// 物料名称
						HSSFCell cell1 = getCell(sheet, i, j);
						cell1.setCellStyle(style);
						cell1.setCellValue("物料名称");
					} else if (j == 1) {// 合计
						HSSFCell cell1 = getCell(sheet, i, j);
						cell1.setCellStyle(style);
						cell1.setCellValue("合计");
					} else {

						HSSFCell cell1 = getCell(sheet, i, j);
						cell1.setCellStyle(style);
						cell1.setCellValue(customerList.get(j - 2).getCustomerNm());

					}
				} else {// 数据
					if (j == 0) {// 物料名称
						HSSFCell cell1 = getCell(sheet, i, j);
						cell1.setCellStyle(style4);
						cell1.setCellValue(goodsList.get(i - 4).getGoodsNm());
					} else if (j == 1) {// 合计数量
						HSSFCell cell1 = getCell(sheet, i, j);
						cell1.setCellStyle(style4);
						cell1.setCellValue(goodsList.get(i - 4).getTotalNum());
					} else {// 客户对应的数量
						HSSFCell cell1 = getCell(sheet, i, j);
						cell1.setCellStyle(style4);
						cell1.setCellValue(goodsList.get(i - 4).getcList().get(j - 2));

					}
				}
			}
		}
	}
}
