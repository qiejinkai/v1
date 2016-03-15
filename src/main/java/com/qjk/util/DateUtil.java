package com.qjk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间工具类
 * @author qiejinkai
 *
 */
public class DateUtil {

	/**
	 * 2015-05-31 09:15:20 星期日
	 */
	public static final String DEFAULTFORMATE="yyyy-MM-dd HH:mm:ss E";
	

	/**
	 * 2015-05-31 09:15:20
	 */
	public static final String FORMATE_1="yyyy-MM-dd HH:mm:ss";
	
	private DateUtil(){
		
	}
	
	/**
	 * 获取当前时间
	 * @return long 11位长整形 精确到秒
	 */
	public static long now(){
		
		return System.currentTimeMillis()/1000;
	}
	
	/**
	 * 格式化日期时间 
	 * 默认格式
	 * @param time
	 * @return
	 */
	public static String formateDate(long time){
		
		return formateDate(time, DEFAULTFORMATE);
	}
	
	/**
	 * 格式化日期时间
	 * @param time
	 * @param formate
	 * @return
	 */
	public static String formateDate(long time,String formate){
		
		SimpleDateFormat sdf =new SimpleDateFormat(formate);
		
		Date date=new Date(time);
		
		return sdf.format(date);
	}
	
}
