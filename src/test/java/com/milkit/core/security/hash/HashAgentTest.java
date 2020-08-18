package com.milkit.core.security.hash;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.milkit.core.security.hash.HashAgent;
import com.milkit.core.security.method.DigestMethod;

public class HashAgentTest {
	
	private static final Logger logger = Logger.getLogger(HashAgentTest.class);
	
	@Test
	public void 해시테스트() throws Exception {
		String rawPass = "password";
//		Object salt = "00000001";
		Object salt = null;
		
		String digest = HashAgent.hash(DigestMethod.SHA256, rawPass, null, false);
//		String digest = HashAgent.hash(DigestMethod.SHA256, Long.toString(new Date().getTime()), salt, false);
		
		logger.debug("digest:"+digest);

		assertNotNull(digest);
		
	}
	
	@Test
	public void 해시값_확인테스트() throws Exception {
		String rawPass = "1111";
		Object salt = null;
		
		String digest = HashAgent.hash(rawPass, salt);
		
		boolean isValied = HashAgent.isHashValid(DigestMethod.SHA256, digest, rawPass, null, false);

		logger.debug("digest:"+digest);
		
		assertTrue(isValied);
		
	}

}

