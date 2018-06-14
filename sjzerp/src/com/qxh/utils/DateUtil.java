package com.qxh.utils;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 类说明
 * 
 * @author chenyang 创建时间：2014-4-26 下午3:37:20
 */
public class DateUtil {

	private static Logger log = Logger.getLogger(DateUtil.class);

	private static String dateFormat = "yyyy-MM-dd";
	private static SimpleDateFormat format = new SimpleDateFormat(dateFormat);

	/**
	 * 获取当前年度字符串。
	 * 
	 * <pre>
	 *  日期字符串格式： yyyy
	 *  其中：
	 *      yyyy   表示4位年。
	 * </pre>
	 * 
	 * @return String "yyyy"格式的当前年度字符串。
	 */
	public static String getNowYear() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(new Date());
	}

	/**
	 * 方法描述 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 * @变更记录 2014-6-21 下午2:31:40 陈阳 创建
	 */
	public static String getNow() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new Date());
	}

	public static String getFormatTime(Date datetime, String forma) {
		SimpleDateFormat formatter = new SimpleDateFormat(forma);
		return formatter.format(datetime);
	}

	/**
	 * 获取当前月度字符串。
	 * 
	 * <pre>
	 *  日期字符串格式： MM
	 *  其中：
	 *      MM   表示4位年。
	 * </pre>
	 * 
	 * @return String "yyyy"格式的当前月度字符串。
	 */
	public static String getNowMonth() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		return formatter.format(new Date());
	}

	/**
	 * 获取当前月度字符串。
	 * 
	 * <pre>
	 *  日期字符串格式： dd
	 *  其中：
	 *      dd   表示4位年。
	 * </pre>
	 * 
	 * @return String "yyyy"格式的当前月度字符串。
	 */
	public static String getNowDay() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		return formatter.format(new Date());
	}

	/**
	 * 获取日期字符串。
	 * 
	 * <pre>
	 *  日期字符串格式： yyyy-MM-dd
	 *  其中：
	 *      yyyy   表示4位年。
	 *      MM     表示2位月。
	 *      dd     表示2位日。
	 * </pre>
	 * 
	 * @param date
	 *            需要转化的日期。
	 * @return String "yyyy-MM-dd"格式的日期字符串。
	 */
	public static String getDate(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	public static String getMonthDate(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		return formatter.format(date);
	}

	/**
	 * 将"yyyyMMdd"格式的日期字符串转换为日期对象。
	 * 
	 * @param source
	 *            日期字符串。
	 * @return Date 日期对象。
	 */
	public static Date parsePlainDate(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

		return sdf.parse(source, new ParsePosition(0));
	}

	/**
	 * 将“yyyy-MM-dd”格式的日期字符串转换为日期对象。
	 * 
	 * @param source
	 *            日期字符串。
	 * @return Date 日期对象。
	 */
	public static Date parseHyphenDate(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		return sdf.parse(source, new ParsePosition(0));
	}

	/**
	 * 将“yyyy-MM-dd HH:mm:ss”格式的日期字符串转换为日期对象。
	 * 
	 * @param source
	 *            日期字符串。
	 * @return Date 日期对象。
	 */
	public static Date parseHyphenDateTime(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(source, new ParsePosition(0));
	}

	/**
	 * 将指定格式的日期字符串转换为日期对象。
	 * 
	 * @param source
	 *            日期字符串。
	 * @param pattern
	 *            模式。
	 * @return Date 日期对象。
	 */
	public static Date parseDate(String source, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(source, new ParsePosition(0));
	}

	/**
	 * 将“yyyy-MM-dd”格式的日期字符串转换为“yyyy-MM”格式的日期字符串。
	 * 
	 * @param source
	 *            日期字符串。
	 * @return String "yyyy-MM-dd"格式的日期字符串。
	 */
	public static String toHyphenDate(String source) {
		Date date = parsePlainDate(source);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		return formatter.format(date);
	}

	/**
	 * 获得年度周期 <br>
	 * Example:<br>
	 * XJPDateUtil.getPeriodYear("20040229" , -1);<br>
	 * XJPDateUtil.getPeriodYear("20040228" , -1);<br>
	 * XJPDateUtil.getPeriodYear("20020230" , 2);<br>
	 * 
	 * @param source
	 *            时间串
	 * @param yearPeriod
	 *            年度周期 -1代表本时间的上一年度,以次类推。
	 * @return String 时间。
	 */
	public static String getPeriodYear(String source, int yearPeriod) {
		int p = Integer.parseInt(source.substring(0, 4)) + yearPeriod;
		String newYear = String.valueOf(p)
				+ source.substring(4, source.length());
		Date date = parsePlainDate(newYear);
		String s = getDate(date);
		int ny = Integer.parseInt(s);
		int sy = Integer.parseInt(newYear);

		while (ny > sy) {
			sy--;
			ny = Integer.parseInt(getDate(parsePlainDate(String.valueOf(sy))));
		}

		return String.valueOf(sy);
	}

	/**
	 * 日期相加
	 * 
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static String addDate(Date date, int day) {
		if (date == null)
			return null;
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, day);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(c.getTime());
	}

	public static String addDate(Date date, int day,String format ) {
		if (date == null)
			return null;
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, day);
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(c.getTime());
	}
	public static String addMonthDate(Date date, int month) {
		if (date == null)
			return null;
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(c.getTime());
	}
	
	/**
	 * 日期相加
	 * 
	 * @param hour
	 *            小时
	 * @return 返回相加后的日期
	 */
	public static String addHour(Date date, int hour) {
		if (date == null)
			return null;
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, hour);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(c.getTime());
	}

	/**
	 * 日期相加
	 * 
	 * @param hour
	 *            小时
	 * @return 返回相加后的日期
	 */
	public static String addMinite(Date date, int minite) {
		if (date == null)
			return null;
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minite);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(c.getTime());
	}

	/**
	 * 方法描述 判断是否是数字
	 * 
	 * @param str
	 * @return
	 * @变更记录 2014-6-9 上午11:27:27 陈阳 创建
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 获取当前日期和时间
	 * 
	 * @param format
	 *            日期格式 例：yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String getDate(Date date, String format) {
		if (date == null)
			return null;
		String str = null;
		SimpleDateFormat df = new SimpleDateFormat(format);
		str = df.format(date);
		return str;
	}
	
	public static String getTimeStr(Date date) {
		String str = "";
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		str = df.format(date);
		return str;
	}

	public static String GetTimeHourMinute(Date date) {
		String str = "";
		SimpleDateFormat df = new SimpleDateFormat("HH时mm分");
		str = df.format(date);
		return str;
	}

	/**
	 * 方法描述 比较第一个时间是否在第二个时间之前
	 * 
	 * @param date
	 * @param date2
	 * @return 在之前 返回true 否则返回false
	 * @变更记录 2014-5-8 上午11:25:44 陈阳 创建
	 */
	public static boolean compareDate(Date date, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		if (cal.before(cal2))
			return true;
		return false;
	}
	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	public static List<String> getDateList(String date1, String date2) {

		List<String> result = new ArrayList<>();
		if (date1.equals(date2)) {
			result.add(date1);
			return result;
		}

		String tmp;
		if (date1.compareTo(date2) > 0) { // 确保 date1的日期不晚于date2
			tmp = date1;
			date1 = date2;
			date2 = tmp;
		}
		result.add(date1);
		tmp = format.format(str2Date(date1).getTime() + 3600 * 24 * 1000);

		int num = 0;
		while (tmp.compareTo(date2) < 0) {
			result.add(tmp);
			num++;
			tmp = format.format(str2Date(tmp).getTime() + 3600 * 24 * 1000);
		}
		result.add(date2);
		return result;
	}

	private static Date str2Date(String str) {
		if (str == null)
			return null;

		try {
			return format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> getMonthList(String beginTime, String endTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
		List<String> monthList = new ArrayList<String>();
		try {
			Date begin = format.parse(beginTime);
			Date end = format.parse(endTime);
			int months = (end.getYear() - begin.getYear()) * 12
					+ (end.getMonth() - begin.getMonth());

			for (int i = 0; i <= months; i++) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(begin);
				calendar.add(Calendar.MONTH, i);
				monthList.add(monthFormat.format(calendar.getTime()));
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return monthList;
	}

	public static String getNowString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		return formatter.format(new Date());
	
	}
	
	/**
	  * 得到本周周一
	  * 
	  * @return yyyy-MM-dd
	  */
	 public static String getMondayOfThisWeek() {
	  Calendar c = Calendar.getInstance();
	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (day_of_week == 0)
	   day_of_week = 7;
	  c.add(Calendar.DATE, -day_of_week + 1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  return formatter.format(c.getTime());
	 }

	 

	 /**
	  * 得到本周周日
	  * 
	  * @return yyyy-MM-dd
	  */
	 public static String getSundayOfThisWeek() {
	  Calendar c = Calendar.getInstance();
	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (day_of_week == 0)
	   day_of_week = 7;
	  c.add(Calendar.DATE, -day_of_week + 7);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		  return formatter.format(c.getTime());
	 }
	//根据时间段(一个星期,半个月一个月,),返回 开始时间,结束时间
	public static List<String> getPartStartToEnd( String type){
		//时间段类型(0:一周内、1:半个月内、2:一个月内)
		List<String> partList=new ArrayList<String>();
		 String day1="", day2="";
		if (type.equals("0")) {
			  day1=getMondayOfThisWeek() +" 00:00:00";
			  day2=getSundayOfThisWeek()+"  23:59:59";
	
		}
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	   String nowDay=formatter.format(new Date());
		if (type.equals("1")) {
			   Integer tem=Integer.parseInt(getNowDay());
			   if (tem>=15) {
				   day1=getNowYear()+"-"+getNowMonth()+"-01 00:00:00";
				   day2=getNowYear()+"-"+getNowMonth()+"-15 23:59:59";
			    }else {
			    	   day1=getNowYear()+"-"+getNowMonth()+"-15   00:00:00";
					   day2=getNowYear()+"-"+getNowMonth()+"-31   23:59:59";
				}
		}
		if (type.equals("2")) {
				   day1=getNowYear()+"-"+getNowMonth()+"-01   00:00:00";
				   day2=getNowYear()+"-"+getNowMonth()+"-31  23:59:59";   
		}
		  partList.add(day1);
		  partList.add(day2);
		  return partList;
	
	}

	//获取日期字符串 yyyy-MM-dd HH:mm:ss
	public static String getNowStrL(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}

}
