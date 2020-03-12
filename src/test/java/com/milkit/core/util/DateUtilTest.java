package com.milkit.core.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milkit.core.util.DateUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:com/milkit/core/spring/context-*.xml",
		"classpath*:com/milkit/test/spring/context-*.xml"
})
public class DateUtilTest {
	
	//private static final Logger logger = LogUtil.getLoggerForThisClass();
	private static Log logger = LogFactory.getLog(DateUtilTest.class);
	
	
	@Test
	public void 시간차_테스트_String() {
		String srcDate = "201110101010";
		String comDate = "201110101410";
		int diffMin = DateUtil.getMinDiff(srcDate, comDate);
		
		logger.debug("diffMin : "+ diffMin);
	}
	
	@Test
	public void 시간차_테스트_Date() throws ParseException {
		String srcDateStr = "201110101010";
		String comDateStr = "201110101012";
		Date srcDate = DateUtil.parseDate(srcDateStr, "yyyyMMddHHmm");
		Date comDate = DateUtil.parseDate(comDateStr, "yyyyMMddHHmm");
			
		int diffMin = DateUtil.getMinDiff(srcDate, comDate);
		
		logger.debug("diffMin : "+ diffMin);
	}
	
	@Test
	public void 시간차_테스트_String2() {
		String srcDate = "1010";
		String comDate = "1410";
		int diffMin = DateUtil.getMinDiff(srcDate, comDate, "HHmm");
		
		logger.debug("diffMin : "+ diffMin);
	}
	
	
	
	@Test
	public void 달의마지막날_테스트() throws Exception {
		int lastDay = DateUtil.getLastDayOfMonth(2011, 11);		//	2011년 11월
		
		logger.debug("lastDay : "+ lastDay);
	}
	
	@Test
	public void 어제일자조회_테스트() throws Exception {
		String yesterDay = DateUtil.plusDay(new Date(), "yyyyMMdd", -1);
		
		logger.debug("yesterDay : "+ yesterDay);
	}

	
	@Test
	public void 다음날조회_테스트() throws Exception {
		String nextDay = DateUtil.plusDay("yyyyMMdd", "20131231", 1);
		
		logger.debug("nextDay : "+ nextDay);
	}
	
	@Test
	public void 일자비교_테스트() throws Exception {
		Date srcDate = DateUtil.parseDate("20181019", "yyyyMMdd");
		Date cmpDate = DateUtil.parseDate("20181018", "yyyyMMdd");
		int compareVar = DateUtil.compareDate(srcDate, cmpDate);
		
		logger.debug("compareVar : "+ compareVar);
	}
	
	@Test
	public void 다음날조회_테스트2() throws Exception {

		Date currDate = DateUtil.parseDate("20131231", "yyyyMMdd");
logger.debug("currDate : "+ currDate);
		GregorianCalendar cal = new GregorianCalendar();
//		    cal.setTime();
		    cal.setGregorianChange(currDate);
		    cal.add(Calendar.DATE, 1);

		    System.out.println("내일 년: " +  cal.get(Calendar.YEAR));
		    System.out.println("내일 월: " + (cal.get(Calendar.MONTH) + 1));
		    System.out.println("내일 일: " +  cal.get(Calendar.DAY_OF_MONTH));

		    // 24시간 후의 날짜, 시간, 시간대를
		    // Sun Dec 10 13:50:52 KST 2006 이런 식으로 한 줄로 출력
		    Date date = cal.getTime();
		    System.out.println(date.toString());

	  }
}
