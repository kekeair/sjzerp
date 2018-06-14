package com.qxh.action.goodsPrice;

import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.action.home.MainAction;
import com.qxh.model.SupplierGoods;
import com.qxh.service.GoodsPriceService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * 物料参数导出
 */
public class InExcelGoodsPriceAction extends MainAction implements Controller{
	
	private GoodsPriceService goodsPriceService;
	
	public void setGoodsPriceService(GoodsPriceService goodsPriceService) {
		this.goodsPriceService = goodsPriceService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		MultipartFile file = multipartRequest.getFile("inFile");
		InputStream fin = file.getInputStream();
		HSSFWorkbook workbook = new HSSFWorkbook(fin);// 创建工作薄 ;
		HSSFSheet sheet = workbook.getSheetAt(0);// 得到工作表
		HSSFRow row = null;// 对应excel的行
		HSSFCell cell = null;// 对应excel的列
		int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数
		// 定义对应数据库表的字段
		String atNo = "";
		String goodsCode = "";
		String goodsNm = "";
		String frontPrice = "";
		String newPrice = "";
		// 存入传入的集合
		ArrayList<SupplierGoods> list = new ArrayList<SupplierGoods>();
		for (int i = 1; i <= totalRow; i++) {
			row = sheet.getRow(i);
			cell = row.getCell(0);
			row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
			atNo = cell.getRichStringCellValue().toString();
			cell = row.getCell(1);
			goodsCode = cell.getRichStringCellValue().toString();
			cell = row.getCell(2);
			goodsNm = cell.getRichStringCellValue().toString();
			cell = row.getCell(3);
			row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
			frontPrice = cell.getRichStringCellValue().toString();
			cell = row.getCell(4);
			row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
			newPrice = cell.getRichStringCellValue().toString();
			// 将读到的数据放入goodsParam
			SupplierGoods supplierGoods = new SupplierGoods();
			supplierGoods.setAtNo(Integer.parseInt(atNo));
			supplierGoods.setGoodsCode(goodsCode);
			supplierGoods.setGoodsNm(goodsNm);
			supplierGoods.setFrontPrice(Double.parseDouble(frontPrice));
			supplierGoods.setNewPrice(Double.parseDouble(newPrice));
			list.add(supplierGoods);
		}
		workbook.close();
		Result result=goodsPriceService.editGoodsPriceByIn(list);
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}