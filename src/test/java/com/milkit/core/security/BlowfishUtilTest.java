package com.milkit.core.security;

import org.junit.Test;

import com.milkit.core.security.BlowfishUtil;

public class BlowfishUtilTest {
	

	@Test
	public void 암호화_테스트() {
		String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEF";
		System.out.println("Plain Text = " + plainText);
		String encryptText = BlowfishUtil.encrypt(plainText);
		System.out.println("Ecrypt Text = " + encryptText + " : " + encryptText.length());
		String decryptText = BlowfishUtil.decrypt(encryptText);
		System.out.println("Decrypt Text = " + decryptText );
	}
	
	
	@Test
	public void 암호화_테스트2() {
		String securityKey = "asdqwezxc";
		
		String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEF";
		System.out.println("Plain Text = " + plainText);
		String encryptText = BlowfishUtil.encrypt(securityKey, plainText);
		System.out.println("Ecrypt Text = " + encryptText + ":" + encryptText.length());
		String decryptText = BlowfishUtil.decrypt(securityKey, encryptText);
		System.out.println("Decrypt Text = " + decryptText );
	}

}
