package com.milkit.core.security.random;

import java.util.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milkit.core.security.random.RandomAgent;
import com.milkit.core.util.DateUtil;
import com.milkit.core.util.PrintUtil;



public class RandomTest {
	
	private static final Logger logger = Logger.getLogger(RandomTest.class);
	
	@Test
	public void 난수테스트_NEW() throws Exception {
		Set set = new HashSet();
		for(int i=0; i<100; i++) {
			String saltStr = RandomAgent.getRandomMessage(16);
			set.add(saltStr);
		}
		
		PrintUtil.print(logger, set);
	}
	
	
	
	@Test
	public void 난수테스트_NUMBER() throws Exception {
		Set set = new HashSet();
		for(int i=0; i<100; i++) {
			String saltStr = RandomAgent.getRandomNumber(6);
			set.add(saltStr);
			
logger.debug(saltStr);
		}
		
		PrintUtil.print(logger, set);
	}
	
	
	@Test
	public void 구간_RandomNumber() throws Exception {
		for(int i=0; i<10; i++) {
			int randomInt = RandomAgent.getRangeRandomNumber(0, 9);
logger.debug("randomInt["+1+"]:"+randomInt);
		}
	}
	
	
	@Test
	public void 시간_RandomNumber() throws Exception {
		
		String currectTime = DateUtil.getCurrectTimeString("yyyyMMddhhmmss");
		
		String random = RandomAgent.getTimeFormatRandomNumber("yyyyMMddhhmmssSSS", 22);
		
logger.debug(currectTime+"\n"+random);
	}
	
}
