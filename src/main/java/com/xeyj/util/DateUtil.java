package com.xeyj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 时间工具类
 * 
 * @author LENOVO
 * 
 */
public class DateUtil {
	private static final String STR_SPACE = " ";
	private static final String STR_GANG = "-";
	private static final String STR_MAOHAO = ":";

	private static final String STR_DATE = "yyyy-MM-dd";
	private static final String STR_TIME = "yyyy-MM-dd HH:mm";
	private static final String STR_FULL_TIME = "yyyy-MM-dd HH:mm:ss";

	public static SimpleDateFormat getDateFormat() {
		return new SimpleDateFormat(STR_DATE);
	}

	public static SimpleDateFormat getTimeFormat() {
		return new SimpleDateFormat(STR_TIME);
	}

	public static SimpleDateFormat getFullTimeFormat() {
		return new SimpleDateFormat(STR_FULL_TIME);
	}

	/**
	 * 获取现在时间
	 * 
	 * @return Date
	 */
	public static Date getNowTime() {
		return new Date();
	}

	/**
	 * 把date日期格式化（yyyy-MM-dd）
	 * 
	 * @param date
	 * @return
	 */
	public static String parseDateToString(Date date) {
		String rtn = getDateFormat().format(date);
		return rtn;
	}

	/**
	 * 把date日期格式化（yyyy-MM-dd HH:mm）
	 * 
	 * @param date
	 * @return
	 */
	public static String parseTimeToString(Date date) {
		if (date == null)
			return "";
		else
			return getTimeFormat().format(date);
	}

	/**
	 * 把date日期格式化（yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param date
	 * @return
	 */
	public static String parseFullTimeToString(Date date) {
		if (date == null)
			return "";
		else
			return getFullTimeFormat().format(date);
	}

	/**
	 * 获取当前日期（格式化后的 yyyy-MM-dd）
	 * 
	 * @return
	 */
	public static String getNowDateString1() {
		return parseDateToString(getNowTime());
	}

	/**
	 * 获取当前日期（格式化后的 yyyy-MM-dd）
	 * 
	 * @return
	 */
	public static String getYesterdayString() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
		return yesterday;
	}

	/**
	 * 获取当前时间（格式化后的 yyyy-MM-dd HH:mm）
	 * 
	 * @return
	 */
	public static String getNowTimeString() {
		return parseTimeToString(getNowTime());
	}

	/**
	 * 获取当前时间（格式化后的 yyyy-MM-dd HH:mm:ss）
	 * 
	 * @return
	 */
	public static String getNowFullTimeString() {
		return parseFullTimeToString(getNowTime());
	}

	public static Date getNextDay(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Date getNextMonth(Date date, int mons) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, mons);
		return cal.getTime();
	}

	/**
	 * 得到当前系统时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return string
	 */
	public static String getCurrentTime() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strTime = "";
		try {
			strTime = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strTime;
	}

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	static int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	/**
	 * 检查时间是否有效
	 * 
	 * @param date
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static boolean isValidDateAndTime(String dateAndTime) {
		try {
			int year = Integer.parseInt(dateAndTime.substring(0, 4));
			if (year <= 0)
				return false;
			int month = Integer.parseInt(dateAndTime.substring(5, 7));
			if (month <= 0 || month > 12)
				return false;
			int day = Integer.parseInt(dateAndTime.substring(8, 10));
			if (day <= 0 || day > DAYS[month])
				return false;
			if (month == 2 && day == 29 && !isGregorianLeapYear(year)) {
				return false;
			}
			int hour = Integer.parseInt(dateAndTime.substring(11, 13));
			if (hour < 0 || hour > 23)
				return false;
			int minute = Integer.parseInt(dateAndTime.substring(14, 16));
			if (minute < 0 || minute > 59)
				return false;
			int second = Integer.parseInt(dateAndTime.substring(17, 19));
			if (second < 0 || second > 59)
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 是否是闰年
	 * 
	 * @param year
	 * @return
	 */
	public static final boolean isGregorianLeapYear(int year) {
		return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
	}

	/**
	 * 得到全时间的毫秒数 yyyy-MM-dd HH:mm:ss
	 */
	public static long getNowFullTimeForLong(String str) {
		try {
			long time = getFullTimeFormat().parse(str).getTime();
			return time;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 得到时间毫秒数：到分 yyyy-MM-dd HH:mm
	 * 
	 * @param str
	 * @return
	 */
	public static long getNowTimeToMinutesForLong(String str) {
		try {
			long time = getTimeFormat().parse(str).getTime();
			return time;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 得到时间毫秒数：到天 yyyy-MM-dd
	 * 
	 * @param str
	 * @return
	 */
	public static long getNowTimeToDaysForLong(String str) {
		try {
			long time = getDateFormat().parse(str).getTime();
			return time;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	// public static String getCurrentTimeForOss(){
	// Calendar calendar = Calendar.getInstance();
	// int year = calendar.get(Calendar.YEAR);
	// int month = calendar.get(Calendar.MONTH);
	// int date = calendar.get(Calendar.DATE);
	// month = month+1;
	// String datePath = "";
	// if(month<10){
	// datePath = year + "0" + month + "" + date;
	// }else{
	// datePath = year + "" + month + "" + date;
	// }
	// return datePath;
	// }

	public static String getYearMonthDayStr() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String date = df.format(new Date());
		return date;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getNowFullTimeForLong("2014-10-24 11:23:28"));
	}
}
