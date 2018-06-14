package com.qxh.action.home;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.qxh.constant.CodeConstant;
import com.qxh.model.User;

public class MainAction {

	public ModelAndView getPowerRight(ModelAndView mav, User user,
			String navString,String parentString) {
		if (user != null) {
			String userPower=user.getPower();
			if(!StringUtils.isEmpty(userPower)){
				String[] powerArray = userPower.split(",");
				List<String> baseInfoRight = new ArrayList<String>();
				List<String> centerConfigRight=new ArrayList<>();
				List<String> inoutRight=new ArrayList<>();
				List<String> stockRight=new ArrayList<>();
				List<String> inoutSummRight=new ArrayList<>();
				for (String power : powerArray) {
					switch (power.substring(0, 1)) {
					case "1":
						// 基础信息
						baseInfoRight.add(power);
						break;
					case "2":
						//餐饮中心设置
						centerConfigRight.add(power);
						break;
					case "3":
						//出入库设置
						inoutRight.add(power);
						break;
					case "4":
						//库存
						stockRight.add(power);
						break;
					case "5":
						//库存
						inoutSummRight.add(power);
						break;
					}
				}
				mav.addObject("baseInfoRight",baseInfoRight);
				mav.addObject("centerConfigRight",centerConfigRight);
				mav.addObject("inoutRight",inoutRight);
				mav.addObject("stockRight",stockRight);
				mav.addObject("inoutSummRight",inoutSummRight);
			}
			
		}
		// 目录点亮
		choseSelected(mav, navString,parentString);
		return mav;
	}

	private void choseSelected(ModelAndView modelAndView, 
			String navString,String parentString) {
		// 待办任务
		if (CodeConstant.NAV_1001.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_1001, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_1001, 0);
		}
		//基础信息
		if (CodeConstant.NAV_1.equals(parentString)) {
			modelAndView.addObject(CodeConstant.NAV_1, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_1, 0);
		}
		// 基础信息-角色
		if (CodeConstant.NAV_101.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_101, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_101, 0);
		}
		// 基础信息-组织架构
		if (CodeConstant.NAV_102.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_102, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_102, 0);
		}
		// 基础信息-人员管理
		if (CodeConstant.NAV_103.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_103, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_103, 0);
		}
		// 基础信息-物料分类
		if (CodeConstant.NAV_104.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_104, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_104, 0);
		}
		// 基础信息-物料单位
		if (CodeConstant.NAV_105.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_105, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_105, 0);
		}
		// 基础信息-物料
		if (CodeConstant.NAV_106.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_106, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_106, 0);
		}
		
		//餐饮中心设置
		if (CodeConstant.NAV_2.equals(parentString)) {
			modelAndView.addObject(CodeConstant.NAV_2, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_2, 0);
		}
		// 餐饮中心设置-餐饮中心物料
		if (CodeConstant.NAV_201.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_201, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_201, 0);
		}
		// 餐饮中心设置-供应商物料
		if (CodeConstant.NAV_202.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_202, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_202, 0);
		}
		// 餐饮中心设置-物料价格设置
		if (CodeConstant.NAV_203.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_203, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_203, 0);
		}
		// 餐饮中心设置-供应商管理
		if (CodeConstant.NAV_204.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_204, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_204, 0);
		}
		// 餐饮中心设置-客户管理
		if (CodeConstant.NAV_205.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_205, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_205, 0);
		}
		// 餐饮中心设置-标签管理
		if (CodeConstant.NAV_207.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_207, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_207, 0);
		}
		
		//出入库
		if (CodeConstant.NAV_3.equals(parentString)) {
			modelAndView.addObject(CodeConstant.NAV_3, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_3, 0);
		}
		// 出入库-需求出库
		if (CodeConstant.NAV_301.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_301, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_301, 0);
		}
		// 出入库-采购入库
		if (CodeConstant.NAV_302.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_302, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_302, 0);
		}
		// 出入库-退库
		if (CodeConstant.NAV_303.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_303, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_303, 0);
		}
		// 出入库-退货
		if (CodeConstant.NAV_304.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_304, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_304, 0);
		}
		
		//库存
		if (CodeConstant.NAV_4.equals(parentString)) {
			modelAndView.addObject(CodeConstant.NAV_4, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_4, 0);
		}
		// 库存-库存修正
		if (CodeConstant.NAV_401.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_401, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_401, 0);
		}
		// 库存-库存修正
		if (CodeConstant.NAV_402.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_402, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_402, 0);
		}
		// 库存-库存修正
		if (CodeConstant.NAV_403.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_403, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_403, 0);
		}
		// 库存-库存变动记录
		if (CodeConstant.NAV_404.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_404, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_404, 0);
		}
		
		//出入库汇总
		if (CodeConstant.NAV_5.equals(parentString)) {
			modelAndView.addObject(CodeConstant.NAV_5, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_5, 0);
		}
		// 出入库汇总-销售汇总
		if (CodeConstant.NAV_501.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_501, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_501, 0);
		}
		// 出入库-采购汇总
		if (CodeConstant.NAV_502.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_502, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_502, 0);
		}
		// 出入库-采购汇总
		if (CodeConstant.NAV_503.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_503, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_503, 0);
		}
		// 出入库-单品毛利汇总
		if (CodeConstant.NAV_504.equals(navString)) {
			modelAndView.addObject(CodeConstant.NAV_504, 1);
		} else {
			modelAndView.addObject(CodeConstant.NAV_504, 0);
		}
	}
}
