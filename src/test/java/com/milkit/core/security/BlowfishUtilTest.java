package com.milkit.core.security;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.milkit.core.security.BlowfishUtil;

public class BlowfishUtilTest {
	

	@Test
	public void 암호화_테스트() throws Exception {
		String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEF";
		System.out.println("Plain Text = " + plainText);
		String encryptText = BlowfishUtil.encrypt("1234", plainText);
		System.out.println("Ecrypt Text = " + encryptText + " : " + encryptText.length());
		String decryptText = BlowfishUtil.decrypt("1234", encryptText);
		System.out.println("Decrypt Text = " + decryptText );
		
		assertTrue(plainText.equals(decryptText));
	}
	
	
	@Test
	public void 암호화_테스트2() throws Exception {
		String securityKey = "asdqwezxc";
		
		String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEF";
		System.out.println("Plain Text = " + plainText);
		String encryptText = BlowfishUtil.encrypt(securityKey, plainText);
		System.out.println("Ecrypt Text = " + encryptText + ":" + encryptText.length());
		String decryptText = BlowfishUtil.decrypt(securityKey, encryptText);
		System.out.println("Decrypt Text = " + decryptText );
		
		assertTrue(plainText.equals(decryptText));
	}

}
