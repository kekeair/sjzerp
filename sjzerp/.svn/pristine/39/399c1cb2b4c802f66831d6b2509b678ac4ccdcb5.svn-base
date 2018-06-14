package com.qxh.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author Mr chen
 * @name : ToBracket
 * @todo : [去掉物料名称中括号]
 * 
 * 创建时间 ： 2017年1月17日上午8:11:14
 */
public class Bracket {

	
	/**
	 * @description : [去掉物料名称中括号]
	 * @param name
	 * @return
	 * @时间:2017年1月17日上午8:10:03
	 */
	public static String ToBrackets(String name) {
		StringBuilder builder = new StringBuilder();
		StringBuilder builderLast = new StringBuilder();
		if (StringUtils.isNotEmpty(name)) {
			String[] split = name.split("[(]");
			for (String string : split) {
				builder.append(string);
			}

			String[] lastArray = builder.toString().split("[)]");
			for (String string : lastArray) {
				builderLast.append(string);
			}
			name = builderLast.toString();
		}
		return name;
	}
	
}
