package com.qxh.impl.goodsKind;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.service.GoodsKindService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.Result;

public class GoodsKindServiceImpl implements GoodsKindService {
	
	Logger log = Logger.getLogger(this.getClass());
	private GoodsKindMapper goodsKindMapper;
	
	public void setGoodsKindMapper(GoodsKindMapper goodsKindMapper) {
		this.goodsKindMapper = goodsKindMapper;
	}

	/**
	 * 添加物料分类
	 */
	@Override
	public Result addGoodsKind(String pId, String code, String name) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("pId", pId);
		param.put("name", name);
		param.put("atNo", 0);
		try {
			String maxCode=goodsKindMapper.getMaxGoodsKindCode(param);
			if(StringUtils.isEmpty(maxCode))
				maxCode="00";
			else
				maxCode=maxCode.substring(maxCode.length()-2);
			int codeInt=Integer.parseInt(maxCode)+1;
			if(codeInt>100){
				result.setCode(CodeConstant.CODE200);
				result.setMsg("该分类已满，无法再添加子分类");
				return result;
			}
			String newCode=String.valueOf(codeInt);
			if(String.valueOf(codeInt).length()==1)
				newCode="0"+codeInt;
			newCode=code+newCode;
			param.put("code", newCode);
			goodsKindMapper.addGoodsKind(param);
			Map<String, Object> data = new HashMap<>();
			data.put("atNo", param.get("atNo"));
			data.put("code", newCode);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 添加物料分类：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "pId:" + pId
			+",code:"+code +",name:"+name + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 编辑物料分类名称
	 */
	@Override
	public Result editGoodsKindNm(String atNo, String name) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		param.put("name", name);
		try {
			goodsKindMapper.editGoodsKindNm(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 编辑物料分类名称：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "atNo:" + atNo +",name:"+name + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 删除物料分类
	 */
	@Override
	public Result delGoodsKind(String atNo) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		try {
			int childCount=goodsKindMapper.getChildCount(param);
			if(childCount>0){
				result.setCode(CodeConstant.CODE200);
				result.setMsg("该分类下还有子分类，不能删除");
				return result;
			}
			int goodsCount=goodsKindMapper.getGoodsNumByKindId(param);
			if(goodsCount>0){
				result.setCode(CodeConstant.CODE200);
				result.setMsg("有物料属于该分类下，不能删除");
				return result;
			}
			goodsKindMapper.delGoodsKind(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("操作失败");
			log.error("\r\n 删除物料分类：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "atNo:" + atNo  + "\r\n\r\n");
		}
		return result;
	}

	
}
