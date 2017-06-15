package com.ds365.commons.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
	
	private static final String  dateFormatForyyyyMMdd="yyyy-MM-dd";
	
	private static final String  dateFormatForyyyyMMddHHmmss="yyyy-MM-dd HH:mm:ss";
	
	private static final String defaultDateFormat=dateFormatForyyyyMMddHHmmss;
	
	public static String dateToStringForyyyyMMddHHmmss(Date data) {
        return dateToString(data,defaultDateFormat);
    }
	
	public static String dateToStringForyyyyMMdd(Date data) {
        return dateToString(data,dateFormatForyyyyMMdd);
    }
	
	public static String dateToString(Date data,String format) {
		if(data==null)
			return null;
        return new SimpleDateFormat(format).format(data);
    }
	
	public static Date stringToDateForyyyyMMdd(String time){
		return stringToDate(time,dateFormatForyyyyMMdd);
	}
	
	public static Date stringToDateForyyyyMMddHHmmss(String time){
		return stringToDate(time,dateFormatForyyyyMMddHHmmss);
	}
	
	public static Date stringToDate(String time,String format){
		if(time==null||"".equals(time))
			return null;
		DateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			throw new RuntimeException("string  to date  pase exception", e);
		}
	}
	
	public static String getCurrentTimeForyyyyMMddHHmmss() {
		return getCurrentTime(defaultDateFormat);
	}
	
	public static String getCurrentTime(String dateFormat) {
		DateFormat formatter = new SimpleDateFormat(dateFormat);
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}
}
