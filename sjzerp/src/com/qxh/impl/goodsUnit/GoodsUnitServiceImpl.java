package com.qxh.impl.goodsUnit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.model.UnitSet;
import com.qxh.service.GoodsUnitService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.IPageConstants;
import com.qxh.utils.PageUtil;
import com.qxh.utils.Result;

public class GoodsUnitServiceImpl implements GoodsUnitService {
	
	Logger log = Logger.getLogger(this.getClass());
	private GoodsUnitMapper goodsUnitMapper;
	
	public void setGoodsUnitMapper(GoodsUnitMapper goodsUnitMapper) {
		this.goodsUnitMapper = goodsUnitMapper;
	}

	/**
	 * 查询单位列表
	 */
	@Override
	public Result getUnitList(String page) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("page", (Integer.parseInt(page)-1)*IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		try {
			List<UnitSet> unitList=goodsUnitMapper.getUnitList(param);
			if(unitList!=null&&unitList.size()>0){
				int orderIndex=(Integer.parseInt(page)-1)*IPageConstants.PageSize+1;
				int l=unitList.size();
				for (int i = 0; i < l; i++) {
					unitList.get(i).setOrderIndex(orderIndex);
					orderIndex++;
				}
			}
			int totalCount=goodsUnitMapper.getUnitCount(param);
			Map<String, Object> data = new HashMap<>();
			data.put("unitList", unitList);
			data.put("totalPage", PageUtil.getTotalPage(totalCount, IPageConstants.PageSize));
			data.put("totalCount", totalCount);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询单位列表：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "page:" + page + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 编辑物料单位
	 */
	@Override
	public Result editUnit(String atNo, String name) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		param.put("name", name);
		try {
			//首先判断单位名是否重复
			int count=goodsUnitMapper.checkUnitNm(param);
			if(count>0){
				result.setCode(CodeConstant.CODE207);
				result.setMsg("该物料单位已有，请重新填写");
				return result;
			}
			if(atNo.equals("-1")){//新增单位
				goodsUnitMapper.addGoodsUnit(param);
			}else{//修改单位
				goodsUnitMapper.updateGoodsUnit(param);
				goodsUnitMapper.updateRelGoods(param);
			}
		} catch (Exception e) {
			log.error("\r\n 编辑物料单位：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "atNo:" + atNo +",name:"+name + "\r\n\r\n");
			throw new RuntimeException();
		}
		return result;
	}

}
