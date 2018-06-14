package com.qxh.impl.suppliManage;

import java.util.List;
import java.util.Map;

import com.qxh.exmodel.ListUser;

public interface SuppliManageMapper {

	/**
	 * 供应商列表
	 * @param param
	 * @return
	 */
	List<ListUser> getSupplierList(Map<String, Object> param);

	
}
