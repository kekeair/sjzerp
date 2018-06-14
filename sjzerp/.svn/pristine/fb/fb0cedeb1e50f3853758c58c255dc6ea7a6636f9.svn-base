package com.qxh.impl.tmpGoodsRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.qxh.constant.CodeConstant;
import com.qxh.model.TmpGoodsRecord;
import com.qxh.service.TmpGoodsRecordService;
import com.qxh.utils.IPageConstants;
import com.qxh.utils.PageUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : TmpGoodsRecordServiceImpl
 * @todo : [临时库存实现类]
 * 
 * 创建时间 ： 2016年11月23日上午9:47:43
 */
public class TmpGoodsRecordServiceImpl implements TmpGoodsRecordService {

	private TmpGoodsRecordMapper tmpGoodsRecordMapper;
	
	public void setTmpGoodsRecordMapper(TmpGoodsRecordMapper tmpGoodsRecordMapper) {
		this.tmpGoodsRecordMapper = tmpGoodsRecordMapper;
	}

	/* 
	 * Todo : [获取列表]
	 * @时间:2016年11月23日上午9:55:11
	 */
	@Override
	public Result getTmpGoodsRecordList(String stime, String etime, String page, int structId, int roleId) {
		
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("page", (Integer.parseInt(page)-1)*IPageConstants.PageSize);
		param.put("structId", structId);
		param.put("pageSize", IPageConstants.PageSize);
		try {
			if(!StringUtils.isEmpty(stime)){
				stime=stime+" 00:00:00";
			}else{
				stime="";
			}
			if(!StringUtils.isEmpty(etime)){
				etime=etime+" 23:59:59";
			}else{
				etime="";
			}
			param.put("stime", stime);
			param.put("etime", etime);
			switch (roleId) {
			case 5://库管
				param.put("viewPower", 0);
				break;
			case 4://经理
				param.put("viewPower", 1);
				break;
			}
			List<TmpGoodsRecord> list=tmpGoodsRecordMapper.getTmpGoodsRecordList(param);
			if(list!=null&&list.size()>0){
				int l=list.size();
				int orderIndex=(Integer.parseInt(page)-1)*IPageConstants.PageSize+1;
				for (int i = 0; i < l; i++) {
					list.get(i).setOrderIndex(orderIndex);
					orderIndex++;
				}
			}
			int count=tmpGoodsRecordMapper.getTmpGoodsRecordCount(param);
			Map<String, Object> data=new HashMap<>();
			data.put("list", list);
			data.put("totalCount", count);
			data.put("totalPage", PageUtil.getTotalPage(count, 
					IPageConstants.PageSize));
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}

}
