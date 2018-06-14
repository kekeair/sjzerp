package com.qxh.impl.list;

import java.util.List;

import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.model.Menu;
import com.qxh.service.MenuService;
import com.qxh.utils.Result;

public class MenuServiceImpl implements MenuService {

	Logger log = Logger.getLogger(this.getClass());
	private MenuMapper menuMapper;

	public void setMenuMapper(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}

	/*
	 * Todo : [获取菜单]
	 * 
	 * @时间:2016年11月15日下午2:15:01
	 */
	@Override
	public Result getMenu() {
		Result result = new Result();
		try {
			List<Menu> powerNodes = menuMapper.getMenu();
			if (powerNodes == null) {
				result.setCode(CodeConstant.CODE211);
				result.setMsg("查找集合为空");
				return result;
			}
			result.setData(powerNodes);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取信息失败");
			e.printStackTrace();
		}
		return result;

	}
}
