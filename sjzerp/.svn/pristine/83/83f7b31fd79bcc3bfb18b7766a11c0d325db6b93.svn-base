package com.qxh.impl.structure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.model.Structure;
import com.qxh.service.StructureService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.Result;

public class StructureServiceImpl implements StructureService {
	
	Logger log = Logger.getLogger(this.getClass());
	private StructureMapper structureMapper;
	
	public void setStructureMapper(StructureMapper structureMapper) {
		this.structureMapper = structureMapper;
	}

	/**
	 * 查询组织架构
	 */
	@Override
	public Result getStructure() {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("获取数据成功");
		try {
			List<Structure> list=structureMapper.getStructure();
			result.setData(list);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询组织架构：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" );
		}
		return result;
	}

	/**
	 * 编辑组织架构
	 */
	@Override
	public Result editStructure(String atNo, String pId,String pCode,String levelOrder,String name, 
			String isCenter, String provinceId, 
			String cityId,String countyId,String address) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		param.put("pId", pId);
		param.put("levelOrder", levelOrder);
		param.put("name", name);
		param.put("isCenter", isCenter);
		param.put("provinceId", provinceId);
		param.put("cityId", cityId);
		param.put("countyId", countyId);
		param.put("address", address==null?"":address);
		try {
			if(!atNo.equals("-1")){//更新
				structureMapper.updateStructure(param);
				if(isCenter.equals("1")){//是餐饮中心
					//更新餐饮中心属性
					structureMapper.updateDiningCenter(param);
				}
			}else{//新增
				//生成编码
				String maxCode=structureMapper.getMaxStructCode(param);
				if(StringUtils.isEmpty(maxCode))
					maxCode="01";
				else
					maxCode=maxCode.substring(maxCode.length()-2);
				int codeInt=Integer.parseInt(maxCode)+1;
				if(codeInt>100){
					result.setCode(CodeConstant.CODE200);
					result.setMsg("该节点已满，无法再添加子节点");
					return result;
				}
				String newCode=String.valueOf(codeInt);
				if(String.valueOf(codeInt).length()==1)
					newCode="0"+codeInt;
				newCode=pCode+newCode;
				param.put("code", newCode);
				structureMapper.addStructure(param);
				if(isCenter.equals("1")){//非餐饮中心
					structureMapper.addDiningCenter(param);
				}
				Map<String, Object> data=new HashMap<>();
				data.put("structId", param.get("atNo"));
				data.put("code", newCode);
				result.setData(data);
			}
		} catch (Exception e) {
			log.error("\r\n 编辑组织架构：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "privinceId:" + provinceId
			+",cityId:"+cityId +",countyId:"+countyId +",atNo:"+atNo
			+",name:"+name +",isCenter:"+isCenter
			+",levelOrder:"+levelOrder +",pId:"+pId + "\r\n\r\n");
			throw new RuntimeException();
		}
		return result;
	}

	/**
	 * 更新架构顺序
	 */
	@Override
	public Result updateStructOrder(String atNo, String pId, String levelOrder) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		param.put("pId", pId);
		param.put("levelOrder", levelOrder);
		try {
			structureMapper.updateStructOrder(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 更新架构顺序：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "atNo:" + atNo
			+",levelOrder:"+levelOrder +",pId:"+pId + "\r\n\r\n");
		}
		return result;
	}
}
