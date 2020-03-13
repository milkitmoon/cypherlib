package com.milkit.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.milkit.core.common.StandardGlobal;



public class DateUtil {
	
	public static SimpleDateFormat getMotpDateFormat() {
		return new SimpleDateFormat(StandardGlobal.STAT_DATE_FORMAT);
	}
	
	public static SimpleDateFormat getMotpDateFormat(String format) {
		return new SimpleDateFormat(format);
	}


	public static int compareDate(String src, String compare) throws ParseException {
		SimpleDateFormat motpDateFormat = getMotpDateFormat();

		Date srcDate = motpDateFormat.parse(src);
		Date compareDate = motpDateFormat.parse(compare);

		if (srcDate.equals(compareDate)) return 0;

		return !srcDate.after(compareDate) ? 1 : -1;
	}
	
	public static int compareDate(Date srcDate, Date compareDate) throws ParseException {
		if (srcDate.equals(compareDate)) return 0;

		return !srcDate.after(compareDate) ? 1 : -1;
	}
	
    
    public static int getDaysDiff(Date srcDate, Date diffDate) {
        int days1 = (int)((srcDate.getTime()/3600000)/24);
        int days2 = (int)((diffDate.getTime()/3600000)/24);
        
        return days2 - days1;
    }
    
    public static int getMinDiff(String sDate1, String sDate2) {
        return getMinDiff(sDate1, sDate2, "yyyyMMddHHmm");
    }
    
    public static int getMinDiff(String sDate1, String sDate2, String argFormat) {
//  	String dateStr1 = validChkDate(sDate1);
//    	String dateStr2 = validChkDate(sDate2);
    	
//        if (!checkDate(sDate1) || !checkDate(sDate2)) {
//            throw new IllegalArgumentException("Invalid date format: args[0]=" + sDate1 + " args[1]=" + sDate2);
//        }
        SimpleDateFormat sdf = new SimpleDateFormat(argFormat, Locale.getDefault());
        
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = sdf.parse(sDate1);
            date2 = sdf.parse(sDate2);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: args[0]=" + sDate1 + " args[1]=" + sDate2);
        }
        int minuite  = (1000 * 3600) / 60; //1분
        
        int days1 = (int)((date1.getTime()/minuite));
        int days2 = (int)((date2.getTime()/minuite));
        
        return days2 - days1;
    }
    
    public static int getMinDiff(Date date1, Date date2) {
//  	String dateStr1 = validChkDate(sDate1);
//    	String dateStr2 = validChkDate(sDate2);
    	
//        if (!checkDate(sDate1) || !checkDate(sDate2)) {
//            throw new IllegalArgumentException("Invalid date format: args[0]=" + sDate1 + " args[1]=" + sDate2);
//        }
        
        int minuite  = (1000 * 3600) / 60; //1분
        
        int days1 = (int)((date1.getTime()/minuite));
        int days2 = (int)((date2.getTime()/minuite));
        
        return days2 - days1;
    }

    public static int getSecondDiff(Date date1, Date date2) {
//  	String dateStr1 = validChkDate(sDate1);
//    	String dateStr2 = validChkDate(sDate2);
    	
//        if (!checkDate(sDate1) || !checkDate(sDate2)) {
//            throw new IllegalArgumentException("Invalid date format: args[0]=" + sDate1 + " args[1]=" + sDate2);
//        }
        
        int second  = ((1000 * 3600) / 60) / 60; //1분
        
        int days1 = (int)(date1.getTime()/second);
        int days2 = (int)(date2.getTime()/second);
        
        return days2 - days1;
    }
    
    public static int getMiliSecondDiff(Date date1, Date date2) {
//  	String dateStr1 = validChkDate(sDate1);
//    	String dateStr2 = validChkDate(sDate2);
    	
//        if (!checkDate(sDate1) || !checkDate(sDate2)) {
//            throw new IllegalArgumentException("Invalid date format: args[0]=" + sDate1 + " args[1]=" + sDate2);
//        }
        
        
        int days1 = (int)date1.getTime();
        int days2 = (int)date2.getTime();
        
        return days2 - days1;
    }
    
	public static String plusMin(String srcdate, int addMin) throws ParseException {
		return plusDay("yyyyMMddHHmm", srcdate, addMin);
	}
	
	public static String plusMin(Date srcdate, String targetdateFormat, int addMin) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(targetdateFormat);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(srcdate);
		cal.add(Calendar.MINUTE, addMin);
		srcdate = cal.getTime();
		
		return dateFormat.format(srcdate);
	}
	
	public static String plusMin(String srcdateFormat, String srcdate, int addMin) throws ParseException {
		SimpleDateFormat motpDateFormat = new SimpleDateFormat(srcdateFormat);
		Date src = motpDateFormat.parse(srcdate);
		
		return plusMin(src, srcdateFormat, addMin);
	}
    
	public static String plusHour(String srcdate, int addHour) throws ParseException {
		return plusDay(StandardGlobal.STAT_DATE_FORMAT, srcdate, addHour);
	}
	
	public static String plusHour(Date srcdate, String targetdateFormat, int addHour) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(targetdateFormat);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(srcdate);
		cal.add(Calendar.HOUR_OF_DAY, addHour);
		srcdate = cal.getTime();
		
		return dateFormat.format(srcdate);
	}
	
	public static String plusHour(String srcdateFormat, String srcdate, int addHour) throws ParseException {
		SimpleDateFormat motpDateFormat = new SimpleDateFormat(srcdateFormat);
		Date src = motpDateFormat.parse(srcdate);
		
		return plusHour(src, srcdateFormat, addHour);
	}
	
	public static String plusDay(String srcdate, int addday) throws ParseException {
		return plusDay(StandardGlobal.STAT_DATE_FORMAT, srcdate, addday);
	}
	
	public static String plusDay(String srcdateFormat, String srcdate, int addday) throws ParseException {
		SimpleDateFormat motpDateFormat = new SimpleDateFormat(srcdateFormat);

		Date src = motpDateFormat.parse(srcdate);
		Calendar cal = new GregorianCalendar();
		cal.setTime(src);
//		cal.add(Calendar.DATE, addday);
		cal.add(Calendar.DAY_OF_YEAR, addday);
	
		src = cal.getTime();
		return motpDateFormat.format(src);
	}
	
	public static String plusDay(Date srcdate, String targetdateFormat, int addday) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(targetdateFormat);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(srcdate);
		cal.add(Calendar.DAY_OF_MONTH, addday);
		srcdate = cal.getTime();
		
		return dateFormat.format(srcdate);
	}
	
	public static String plusMonth(String srcdate, int month) throws ParseException {
		return plusMonth(StandardGlobal.STAT_DATE_FORMAT, srcdate, month);
	}
	
	public static String plusMonth(Date srcdate, String targetdateFormat, int month) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(targetdateFormat);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(srcdate);
		cal.add(Calendar.MONTH, month);
		srcdate = cal.getTime();
		
		return dateFormat.format(srcdate);
	}
	
	public static String plusMonth(String srcdateFormat, String srcdate, int month) throws ParseException {
		SimpleDateFormat motpDateFormat = new SimpleDateFormat(srcdateFormat);

		Date src = motpDateFormat.parse(srcdate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(src);
		cal.add(Calendar.MONTH, month);
		src = cal.getTime();
		return motpDateFormat.format(src);
	}

	public static String toFriendlyDateString(String userdate) throws ParseException {
		String DATEFORMAT = "yyyyMMddHHmmss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT);
		SimpleDateFormat viewFormat = new SimpleDateFormat(StandardGlobal.FRIENDLY_DATE_FORMAT);
		
		return viewFormat.format(dateFormat.parse(userdate));
	}
	
	public static String toFriendlyDateString(Date curDate) {
		SimpleDateFormat motpDateFormatMs = new SimpleDateFormat(StandardGlobal.FRIENDLY_DATE_FORMAT);
		
		return motpDateFormatMs.format(curDate);
	}

	public static String getCurrentTime() {
		SimpleDateFormat motpDateFormat = getMotpDateFormat();
		return motpDateFormat.format(new Date(System.currentTimeMillis()));
	}

	public static String getCurrentTimeMs() {
		SimpleDateFormat motpDateFormatMs = new SimpleDateFormat("yyyyMMddHHmmssSS");
		
		return motpDateFormatMs.format(new Date(System.currentTimeMillis()));
	}
	
	public static Date parseDate(String time) throws ParseException {
		SimpleDateFormat motpDateFormat = getMotpDateFormat();
		
		return motpDateFormat.parse(time);
	}
	
	public static Date parseDate(String time, String format) throws ParseException {
		SimpleDateFormat motpDateFormat = getMotpDateFormat(format);
		
		return motpDateFormat.parse(time);
	}
	
	public static String parseDateString(long time, String outputFormat) throws ParseException {
		Date date = new Date(time);
		SimpleDateFormat format = new SimpleDateFormat(outputFormat);
		
	    return format.format(date).toString();
	}
	
	public static String parseDateString(String time, String inputFormat, String outputFormat) throws ParseException {
		SimpleDateFormat motpDateFormat = getMotpDateFormat(inputFormat);
		Date parseDate = motpDateFormat.parse(time);
		
		return getTimeString(parseDate, outputFormat);
	}
	
	public static String parseDateString(Date curDate, String formatStr) {
		SimpleDateFormat motpDateFormatMs = new SimpleDateFormat(formatStr);
			
		return motpDateFormatMs.format(curDate);
	}
	
	public static String getCurrectTimeString(String format) {
		SimpleDateFormat motpDateFormatMs = new SimpleDateFormat(format);
		
		return motpDateFormatMs.format(new Date());
	}
	
	public static String getTimeString(Date date, String format) {
		SimpleDateFormat motpDateFormatMs = new SimpleDateFormat(format);
		
		return motpDateFormatMs.format(date);
	}
	
	public static String getCurrentYear() {
		SimpleDateFormat motpDateFormat = new SimpleDateFormat("yyyy");
		return motpDateFormat.format(new Date());
	}
	
	public static String getCurrentMonth() {
		SimpleDateFormat motpDateFormat = new SimpleDateFormat("MM");
		return motpDateFormat.format(new Date());
	}
	
	public static String getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return String.valueOf(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	}
	
	    
    
    public static int getLastDayOfMonth(int year, int month) throws Exception {
    	Calendar calendar = Calendar.getInstance();
       	calendar.set(year,month-1,1);
       	
       	return calendar.getActualMaximum(Calendar.DATE);
    }
	
	public static String getWeekDay(Date date) {
		String []weeks = {"일","월","화","수","목","금","토"};
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return weeks[calendar.get(Calendar.DAY_OF_WEEK)-1];
	}
	
	public static String getWeekDay(String dateString, String dateFormat) throws ParseException {
		Date parseDate = DateUtil.parseDate(dateString, dateFormat);
		
		return getWeekDay(parseDate);
	}
	
    
    public static boolean checkDate(String sDate) {
//    	String dateStr = validChkDate(sDate);

        String year  = sDate.substring(0,4);
        String month = sDate.substring(4,6);
        String day   = sDate.substring(6);
   
        return checkDate(year, month, day);
    }   

    public static boolean checkDate(String year, String month, String day) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
            
            Date result = formatter.parse(year + "." + month + "." + day);
            String resultStr = formatter.format(result);
            if (resultStr.equalsIgnoreCase(year + "." + month + "." + day))
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }
    

}
