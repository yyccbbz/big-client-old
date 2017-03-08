package com.evergrande.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SHORT_DATE_FORMAT_NO_ = "yyyyMMdd";

    public static final String DATE_FORMAT_YEAR_WEEK = "yyyyww";
    public static final String DATE_FORMAT_YEAR_MONTH = "yyyyMM";

    public static final long WEEK_TIME = 7 * 24 * 3600 * 1000l;
    public static final long DATE_TIME = 24 * 3600 * 1000l;

    /**
     * 返回两日期之间的天数
     * 
     * @param sdate
     * @param edate
     * @return
     */
    public static int computeDayOffset(Date sdate, Date edate) {
        long sMillis = sdate.getTime();
        long eMillis = edate.getTime();

        long millis = Math.abs(eMillis - sMillis);

        if (millis != 0l) {
            millis = millis / DATE_TIME;
        }
        return (int) millis;
    }

    /**
     * 返回两日期之间的天数
     * 
     * @param sdateStr
     * @param edateStr
     * @return
     */
    public static int computeDayOffset(String sdateStr, String edateStr) {
        Date sdate = praseDate(sdateStr, SHORT_DATE_FORMAT);
        Date edate = praseDate(edateStr, SHORT_DATE_FORMAT);

        return computeDayOffset(sdate, edate);
    }

    /**
     * 返回一个格式化的日期
     * 
     * @param dateStr
     * @param format
     * @return
     */
    public static Date praseDate(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;

        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
    
    public static String getFormatDate(Date date, String format) {
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	return sdf.format(date);
    }

    /**
     * 获得两个日期间的日期集合数组(包含这两个日期)
     * 
     * @param sdateStr
     * @param edateStr
     * @return
     */
    public static String[] getDays(String sdateStr, String edateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int initSize = computeDayOffset(sdateStr, edateStr) + 1;
        String[] dateArr = new String[initSize];

        try {
            // 默认不包含sdate，现在手动将sdate放在第一个，故循环从1开始
            dateArr[0] = sdateStr;
            Date sdated = sdf.parse(sdateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdated);
            for (int i = 1; i < initSize; i++) {
                calendar.add(Calendar.DATE, 1);
                dateArr[i] = sdf.format(calendar.getTime());
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateArr;
    }
    
    
    /**
     *  获取两个日期间的日期集合，开始和结束都变化间隔天数
     * @param sdateStr 开始日期
     * @param edateStr 结束日期
     * @param interval 间隔天数
     * @return  返回
     */
    public static String[] getDays(String sdateStr, String edateStr, int interval) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int initSize = computeDayOffset(sdateStr, edateStr) + 1;
        String[] dateArr = new String[initSize];

        try {
            // 默认不包含sdate，现在手动将sdate放在第一个，故循环从1开始
//            dateArr[0] = sdateStr;
            Date sdated = sdf.parse(sdateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdated);
            //开始日期上减去相隔天数
            calendar.add(Calendar.DATE, interval);
            
            for (int i = 0; i <initSize; i++) {
                dateArr[i] = sdf.format(calendar.getTime());
                calendar.add(Calendar.DATE, 1);
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateArr;
    }
    
    /**
     * 获取N天的时间
     * @param dateStr
     * @param format
     * @param days
     * @return
     */
    public static String getNDaysDiff(String dateStr, String format, Integer days){
    	SimpleDateFormat sdf = new SimpleDateFormat(format); 
    	Date date = null;
    	try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.DATE, days);
    	String yesterday = new SimpleDateFormat(format).format(cal.getTime());
    	
    	return yesterday;
    }


    /**
     * 获取 当前实际 加上 N天 (或减, 如果为负数)的时间 字符
     * @param format   日期格式
     * @param daysDiff 正数,+天数; 负数,-天数
     * @return 最终时间的 格式化字符
     */
    public static String getNDaysDiff(String format, Integer daysDiff){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, daysDiff);
        String resultDateStr = new SimpleDateFormat(format).format(now.getTime());

        return resultDateStr;
    }
    
    /**
     * 取得当前日期所在周的第一天(Monday)
     * @param date
     * @param format
     * @return
     */
    public static String getFirstDayOfWeek(Date date, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                      calendar.getFirstDayOfWeek()); 
//        calendar.add(Calendar.DATE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        
        return sdf.format(calendar.getTime());
    }

    /**
     * 取得当前日期所在周的最后一天(Sunday)
     * @param date
     * @param format
     * @return
     */
    public static String getLastDayOfWeek(Date date, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                     calendar.getFirstDayOfWeek() + 6); 
//        calendar.add(Calendar.DATE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        
        return sdf.format(calendar.getTime());
    }
    
    /**
	 * 得到本月的第一天  
	 * @return  
	 */  
	public static String getMonthFirstDay(Date date, String format) {   
		SimpleDateFormat sdf = new SimpleDateFormat(format);
	    Calendar calendar = Calendar.getInstance();   
	    calendar.setTime(date);
	    calendar.set(Calendar.DAY_OF_MONTH, calendar   
	            .getActualMinimum(Calendar.DAY_OF_MONTH));   
	  
	    return sdf.format(calendar.getTime());   
	}   
	  
	/**  
	 * 得到本月的最后一天  
	 *   
	 * @return  
	 */  
	public static String getMonthLastDay(Date date, String format) {   
		SimpleDateFormat sdf = new SimpleDateFormat(format);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.DAY_OF_MONTH, calendar   
	            .getActualMaximum(Calendar.DAY_OF_MONTH));   
	    return sdf.format(calendar.getTime());  
	}   
    
}
