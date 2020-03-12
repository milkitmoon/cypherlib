package com.milkit.core.security.random;



import java.security.MessageDigest;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

import com.milkit.core.security.factory.MethodFactory;
import com.milkit.core.security.method.DigestMethod;
import com.milkit.core.security.random.RandomGenerator;
import com.milkit.core.util.DateUtil;

public class RandomAgent {

	public static String getRandomMessage(int length) throws Exception {
		String message = null;
		
		byte[] salt = new byte[20];
		RandomGenerator.randomBytes(salt);
		
//		DigestMethod md = MethodFactory.getDigestMethod(DigestMethod.MD5);
		DigestMethod md = MethodFactory.getDigestMethod(DigestMethod.SHA1);
		md.update(DateUtil.getCurrentTime().getBytes());
        md.update(salt);
        byte[] digest = md.digest();
		
//        StringBuffer buffer = new StringBuffer(new String( digest ));
		StringBuffer buffer = new StringBuffer(new String( Base64.encodeBase64(digest) ));
		
		if(buffer.length() > length) {
			message = buffer.substring(0, length);
		} else {
			while (buffer.length() < length) {
				buffer.insert(0, '0');
			}
			message = buffer.toString();
		}

		return message;
	}
	
	public static String getRandomNumber(int length) throws Exception {
		String currTime = DateUtil.getCurrentTime();
		byte[] dummy = new byte[20];
		RandomGenerator.randomBytes(dummy);

		DigestMethod md = MethodFactory.getDigestMethod(DigestMethod.SHA1);
		
		md.update(currTime.getBytes());
		md.update(dummy);
		
		byte mdBytes[] = md.digest();		
			
		return dynamicTruncate(mdBytes, length, true);
	}
	
	
	private static String dynamicTruncate(byte[] secret, int codeDigits, boolean addChecksum) {
		StringBuffer ret = new StringBuffer();

		if (codeDigits < 2)
			codeDigits = 2;
		if (codeDigits > 18)
			codeDigits = 18;

		for (int i = 0; i <= codeDigits / 4; i++) {
			int s;
			s = (0xff000000 & (secret[i * 4    ] << 24)) |
				(0x00ff0000 & (secret[i * 4 + 1] << 16)) |
				(0x0000ff00 & (secret[i * 4 + 2] << 8 )) |
				(0x000000ff & (secret[i * 4 + 3]));
			s = s & 0x7fffffff;
			String str = Integer.toString(s + 10000);
			str = str.substring(str.length() - 4, str.length());
			ret.append(str);
		}

		long otp;

		if (addChecksum) {
			otp = Long.parseLong(ret.toString().substring(0, codeDigits - 1));
			int otpchecksum = calcChecksum(otp, codeDigits - 1);
			otp = (otp * 10) + otpchecksum;
		} else {
			otp = Integer.parseInt(ret.toString().substring(0, codeDigits));
		}
		
		StringBuffer sb = new StringBuffer(Long.toString(otp));

		while (sb.length() < codeDigits) {
			sb.insert(0, '0');
		}
		
		return sb.toString();
	}
	
	
	private static final int[] doubleDigits = { 0, 2, 4, 6, 8, 1, 3, 5, 7, 9 };
	public static int calcChecksum(long num, int digits) {
		boolean doubleDigit = true;
		int     total = 0;
		while (0 < digits--) {
			int digit = (int) (num % 10);
			num /= 10;
			if (doubleDigit) {
				digit = doubleDigits[digit];
			}
			total += digit;
			doubleDigit = !doubleDigit;
		}
		int result = total % 10;
		if (result > 0) {
			result = 10 - result;
		}
		return result;
	}
	
	public static String getPrefixRandomNumber(String prefix, int length) throws Exception {
		String randomString = null;
		if(prefix != null) {
			randomString = prefix+getRandomNumber(length-prefix.length() );
		}
		
		return randomString;
	}
	
	public static String getTimeFormatRandomNumber(String timeFormat, int length) throws Exception {
		String randomString = null;
		if(timeFormat != null) {
			randomString = getPrefixRandomNumber(DateUtil.getCurrectTimeString(timeFormat), length);
//			randomString = DateUtil.getCurrectTimeString(timeFormat)+getRandomNumber(length-timeFormat.length() );
		}
		
		return randomString;
	}
	
	
	public static int getRangeRandomNumber(int min, int max) {
	    Random rand = new Random();
	    
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
}

