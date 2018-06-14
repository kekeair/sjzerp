package com.qxh.utils;

import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;

public class NumUtil {

	public static double trim0(double num){
		try {
			DecimalFormat df=new DecimalFormat("#.00");
			String str=df.format(num);
			if(str.endsWith(".00")){
				str=str.substring(0, str.length()-3);
			}else if(str.endsWith("0")){
				str=str.substring(0, str.length()-1);
			}
			if(StringUtils.isEmpty(str))
				return 0;
			return Double.parseDouble(str);
		} catch (Exception e) {
			System.out.println(num);
			return -1;
		}
		
	}
	
	public static double trim0_4(double num){
		try {
			DecimalFormat df=new DecimalFormat("#.0000");
			String str=df.format(num);
			if(str.endsWith(".0000")){
				str=str.substring(0, str.length()-5);
			}else if(str.endsWith("000")){
				str=str.substring(0, str.length()-2);
			}else if(str.endsWith(".00")){
				str=str.substring(0, str.length()-2);
			}else if(str.endsWith("0")){
				str=str.substring(0, str.length()-1);
			}
			if(StringUtils.isEmpty(str))
				return 0;
			return Double.parseDouble(str);
		} catch (Exception e) {
			System.out.println(num);
			return -1;
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(trim0(0.1));
	}                            
}
