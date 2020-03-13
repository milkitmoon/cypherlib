package com.milkit.core.common;

import com.milkit.core.util.DateUtil;

public class StandardGlobal {
	
	public static final String ENCODING = "UTF-8";
	
	public static String STAT_MONTH_FORMAT = "yyyy-MM-dd";
	public static String STAT_DATE_FORMAT = "yyyy-MM-ddHH";
	public static String FRIENDLY_DATE_FORMAT = "yyyy-MM-dd HH:mm";

	public static String CURRENT_YEAR = DateUtil.getCurrentYear();
	public static String CURRENT_MONTH = DateUtil.getCurrentMonth();
	
	public static int MONTHLY_PERIOD = 1;
	public static int DAILY_PERIOD = 2;
	public static int STAT_PERIOD = MONTHLY_PERIOD;
	
}
