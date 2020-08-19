package com.milkit.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.util.StopWatch;

import com.milkit.core.util.PrintUtil;

public class StringTest {
	
	private static final Logger logger = Logger.getLogger(StringTest.class);
	
	private String srcStr = "String 기본객체가 빨라보이지만, 특성상";

	
	@Test
	public void 문자열테스트() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		for(int i=0; i<10; i++) {
			srcStr += srcStr;
		}
		stopWatch.stop();
		logger.debug(stopWatch.shortSummary());
	}

	
	@Test
	public void 문자열테스트_Buffer() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		StringBuffer strBuff = new StringBuffer();
		for(int i=0; i<100000; i++) {
			strBuff.append(srcStr);
		}
		stopWatch.stop();
		PrintUtil.print(logger, stopWatch.prettyPrint());
	}
}
