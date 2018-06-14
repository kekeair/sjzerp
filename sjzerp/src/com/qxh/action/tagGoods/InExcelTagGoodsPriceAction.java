package com.qxh.action.tagGoods;

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
import com.qxh.constant.CodeConstant;
import com.qxh.model.TagGoods;
import com.qxh.service.TagGoodsService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;
/**
 * 
 * @author Mr chen
 * @name : InExcelGoodsPriceAction
 * @todo : [标签价格的导入功能]
 * 
 * 创建时间 ： 2017年1月12日上午8:17:27
 */
public class InExcelTagGoodsPriceAction extends MainAction implements Controller{
	
	private TagGoodsService tagGoodsService; 
	public void setTagGoodsService(TagGoodsService tagGoodsService) {
		this.tagGoodsService = tagGoodsService;
	}
	
	@SuppressWarnings("resource")
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
		String atNo = "";            //标签id
		String goodsCode = "";      //标签名称
		String tagGoodsNm = "";      //标签名称
		String tagGoodsPrice = "";   //标枪价格
		
		
		Result result=new Result();
		ArrayList<TagGoods> list=null;
		try {
			// 存入传入的集合
			list = new ArrayList<TagGoods>();
			for (int i = 1; i <= totalRow; i++) {
				row = sheet.getRow(i);
				
				cell = row.getCell(0);
				row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
				atNo = cell.getRichStringCellValue().toString();
				
				cell = row.getCell(1);
				//row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				goodsCode = cell.getRichStringCellValue().toString(); 
				
				cell = row.getCell(2);
				//row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				tagGoodsNm = cell.getRichStringCellValue().toString();
				
				cell = row.getCell(3);
				row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
				tagGoodsPrice = cell.getRichStringCellValue().toString();
				
				// 将读到的数据放入goodsParam
				TagGoods tagGoods = new TagGoods();
				tagGoods.setAtNo(Integer.parseInt(atNo));
				tagGoods.setTagGoodsNm(tagGoodsNm);
				tagGoods.setTagGoodsPrice(Double.parseDouble(tagGoodsPrice));
				list.add(tagGoods);
			}
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("导入的格式不正确,请检查你导入的格式!!!");
			e.printStackTrace();
			return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
					result.getData());
		}
		
		workbook.close();
		result=tagGoodsService.editTagGoodsPriceByAtNO(list);
		
		return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(),
				result.getData());
	}
}