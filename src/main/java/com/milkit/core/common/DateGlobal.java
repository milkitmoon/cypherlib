package com.milkit.core.common;

import com.milkit.core.util.DateUtil;

/**
* <pre>
* 1. 패키지명 : com.milkit.core.common
* 2. 타입명 : DateGlobal.java
* 3. 작성일 : 2015. 5. 28. 오후 3:22:09
* 4. 작성자 : milkit
* 5. 설명    : 일반적인 Data Format 정의
* </pre>
*/
public class DateGlobal {

	//	public static String DATE_FORMAT = "yyyyMMddHHmmss";
	
	public static String SIMPLE_DATE_FORMAT = "yyyyMMddHH";
	
	public static String STAT_YEAR_FORMAT = "yyyy-MM";
	public static String STAT_MONTH_FORMAT = "yyyy-MM-dd";
	public static String STAT_DATE_FORMAT = "yyyy-MM-ddHH";
	
	public static int MONTHLY_PERIOD = 1;
	public static int DAILY_PERIOD = 2;
	public static int STAT_PERIOD = MONTHLY_PERIOD;
	
	public static int LOG_COUNT = 5;
	
	public static String FRIENDLY_DATE_FORMAT = "yyyy-MM-dd HH:mm";
	
	public static String CURRENT_YEAR = DateUtil.getCurrentYear();
	public static String CURRENT_MONTH = DateUtil.getCurrentMonth();

}
