package com.qxh.impl.suppliManage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.ListUser;
import com.qxh.service.SuppliManageService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.Result;

public class SuppliManageServiceImpl implements SuppliManageService {
	
	Logger log = Logger.getLogger(this.getClass());
	private SuppliManageMapper suppliManageMapper;
	
	public void setSuppliManageMapper(SuppliManageMapper suppliManageMapper) {
		this.suppliManageMapper = suppliManageMapper;
	}

	/**
	 * 供应商列表
	 */
	@Override
	public Result getSupplierList(int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("structId", structId);
		try {
			List<ListUser> list=suppliManageMapper.getSupplierList(param);
			if(list!=null&&list.size()>0){
				int l=list.size();
				for (int i = 0; i < l; i++) {
					list.get(i).setOrderIndex(i+1);
				}
			}
			result.setData(list);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询供应商列表：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "structId:" + structId  + "\r\n\r\n");
		}
		return result;
	}

	
}
