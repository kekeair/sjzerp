package com.qxh.action.list;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.qxh.model.Menu;
import com.qxh.service.MenuService;
import com.qxh.utils.CheckUtil;
import com.qxh.utils.Result;

/**
 * 
 * @author Mr chen
 * @name : GetListAction
 * @todo : [获得菜单列表]
 * 
 * 创建时间 ： 2016年11月15日上午10:11:03
 */
public class GetListAction implements Controller {
	Logger log = Logger.getLogger(this.getClass());
	private MenuService menuService;
	
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 ModelAndView mav = new ModelAndView();
		 Result result = menuService.getMenu();
		 List<Menu> powerNodes = (List<Menu>) result.getData();
		 return CheckUtil.returnResult(mav, result.getCode(), result.getMsg(), result.getData());
	}

}
