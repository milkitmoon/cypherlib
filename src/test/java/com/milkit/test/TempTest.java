package com.milkit.test;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

public class TempTest {

	
	@Test
	public void 문자변환_테스트() {
		String phoneNummber = "010-8662-6053";
		phoneNummber = phoneNummber.replaceAll("-", "");
		
		assertTrue(phoneNummber.equals("01086626053"));
	}
	
	
	@Test
	public void 맞는전화번호검증_테스트() {
		String phoneNummber = "010-8662-6053";
		
		assertTrue(verifyPhoneNumber(phoneNummber));
	}
	
	@Test
	public void 틀린전화번호검증_테스트() {
		String phoneNummber = "10-8662-60531";
		
		assertTrue(!verifyPhoneNumber(phoneNummber));
	}
	
	private boolean verifyPhoneNumber(String phoneNummber) {
		boolean isOk = false;

		phoneNummber = phoneNummber.replaceAll("-", "");
		
		if(phoneNummber.length() > 9 && phoneNummber.length() < 12) {
			if(phoneNummber.startsWith("01")) {
				isOk = true;				
			}
		}

		return isOk;
	}
	
	@Test
	public void 그냥() {
		Object i = new Object();
		Object t = new Object();
		System.out.println("hashCode : " + i.hashCode());
		System.out.println("hashCode : " + t.hashCode());
		String s = i.toString();
		String k = t.toString();
		System.out.println("s : " + s);
		System.out.println("k : " + k);
	}
	
	@Test
	public void 전화번호_테스트() {
//		String phoneNumber = "01086626053";
		String phoneNumber = "025166776";
		boolean isPhoneNumber =  isPhoneNumber(phoneNumber);
		System.out.println("isPhoneNumber : " + isPhoneNumber);
	}
	
	public static boolean isPhoneNumber(String phoneNumber) {
        if (phoneNumber==null) return false;
        
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
