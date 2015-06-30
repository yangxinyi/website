/*
 * @(#)DateUtils.java
 *
 * Copyright 2008 Xinhua Online, Inc. All rights reserved.
 */

package com.support.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具类
 * @author  liuan
 * @version 1.0,2008-7-17
 */
public class DateUtils {
	
	public static final String SHORT_DATE_FORMAT_STR="yyyy-MM-dd";
	public static final String LONG_DATE_FORMAT_STR="yyyy-MM-dd HH:mm:ss";
	private static final String EARLY_TIME="00:00:00";
	private static final String LATE_TIME="23:59:59";
	
	/**
	 * 得到某个日期在这一天中时间最早的日期对象
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getEarlyInTheDay(Date date) throws ParseException{
		String dateString=new SimpleDateFormat(SHORT_DATE_FORMAT_STR).format(date)+" "+EARLY_TIME;
		return new SimpleDateFormat(LONG_DATE_FORMAT_STR).parse(dateString);
	}
	
	/**
	 * 得到某个日期在这一天中时间最晚的日期对象
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getLateInTheDay(Date date) throws ParseException{
		String dateString=new SimpleDateFormat(SHORT_DATE_FORMAT_STR).format(date)+" "+LATE_TIME;
		return new SimpleDateFormat(LONG_DATE_FORMAT_STR).parse(dateString);
	}
	
	/**
	*增加时间的秒数
	* @param date 要增加的日期
	* @param second 增加的时间（以秒为单位）
	* @return 增加时间后的日期
	*/

	public static Date addSecond(Date date, int second) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, second);
		return cal.getTime();
	}
	/**
	 * 根据传入日期计算和当前日期的相差天数
	 * @param date
	 * @return
	 */
	public static long subtractNowDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long dateTimeInMillis = calendar.getTimeInMillis();
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(new Date());
		long nowTimeInMillis = nowCalendar.getTimeInMillis();
		return (nowTimeInMillis-dateTimeInMillis)/(24 *60*60*1000);
	}
	
	/**
	 * 获取结束日期与开始日期相差的秒数
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @return
	 */
	public static long subtractSecond(Date startDate, Date endDate){
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		long startTimeInMillis = startCalendar.getTimeInMillis();
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		long endTimeInMillis = endCalendar.getTimeInMillis();
		return (endTimeInMillis - startTimeInMillis)/1000;
		
	}
	
	/**
	 * 字符串按自定格式更新
	 * @param dateString
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date parserStringToDate(String dateString,String format) throws ParseException {
		DateFormat dateFormat=new SimpleDateFormat(format);
		return dateFormat.parse(dateString);
	}

}
