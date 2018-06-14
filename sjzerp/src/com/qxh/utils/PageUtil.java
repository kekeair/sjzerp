package com.qxh.utils;

public class PageUtil {
	
	public static int getTotalPage(int totalRow,int pageSize){
		int result;
		if (totalRow%pageSize==0) {
			 result = totalRow/pageSize;
		}
		else {
			 result = (totalRow/pageSize)+1;
		}
		if(result == 0)
		{
			result = 1;
		}
		return result;
	}
}
