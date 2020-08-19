package com.milkit.test;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

public class TempTest {

	
	@Test
	public void 전화번호_테스트() {
//		String phoneNumber = "01086626053";
		String phoneNumber = "025166776";
		boolean isPhoneNumber =  isPhoneNumber(phoneNumber);
	
		assertTrue(isPhoneNumber);
	}
	
	private boolean isPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) return false;
        
        phoneNumber= phoneNumber.replaceAll("\\D", "");
        
        if( Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", phoneNumber) ) {
        	return true;
        }
        if( Pattern.matches("^\\d{2,3}\\d{3,4}\\d{4}$",phoneNumber) ) {
        	return true;
        }
        
        return false;
    }
	
}
