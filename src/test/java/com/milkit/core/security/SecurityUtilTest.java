package com.milkit.core.security;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.milkit.core.security.BlowfishUtil;
import com.milkit.core.security.SecurityUtil;
import com.milkit.core.security.method.AESSecretMethod;
import com.milkit.core.security.method.BlowfishSecretMethod;

public class SecurityUtilTest {
	

	@Test
	public void BLOWFISHECB암호화_테스트() throws Exception {
		String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEF";
		System.out.println("Plain Text = " + plainText);
		String encryptText = SecurityUtil.encrypt(BlowfishSecretMethod.BLOWFISHECB, "MILKSECURETESTKEY", plainText);
		System.out.println("Ecrypt Text = " + encryptText + " : " + encryptText.length());
		String decryptText = SecurityUtil.decrypt(BlowfishSecretMethod.BLOWFISHECB, "MILKSECURETESTKEY", encryptText);
		System.out.println("Decrypt Text = " + decryptText );
		
		assertTrue(plainText.equals(decryptText));
	}
	
	@Test
	public void BLOWFISHCBC암호화_테스트() throws Exception {
		String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEF";
		System.out.println("Plain Text = " + plainText);
		String encryptText = SecurityUtil.encrypt(BlowfishSecretMethod.BLOWFISHCBC, "MILKSECURETESTKEY", "MILKSECU", plainText);
		System.out.println("Ecrypt Text = " + encryptText + " : " + encryptText.length());
		String decryptText = SecurityUtil.decrypt(BlowfishSecretMethod.BLOWFISHCBC, "MILKSECURETESTKEY", "MILKSECU", encryptText);
		System.out.println("Decrypt Text = " + decryptText );
		
		assertTrue(plainText.equals(decryptText));
	}
	
	
	@Test
	public void AES128CBC암호화_테스트() throws Exception {
		String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEF";
		System.out.println("Plain Text = " + plainText);
		String encryptText = SecurityUtil.encrypt(AESSecretMethod.AESCBC, "1234567890123456", "9878543210123456", plainText);
		System.out.println("Ecrypt Text = " + encryptText + " : " + encryptText.length());
		String decryptText = SecurityUtil.decrypt(AESSecretMethod.AESCBC, "1234567890123456", "9878543210123456", encryptText);
		System.out.println("Decrypt Text = " + decryptText );
		
		assertTrue(plainText.equals(decryptText));
	}
	
	@Test
	public void AES128ECB암호화_테스트() throws Exception {
		String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEF";
		System.out.println("Plain Text = " + plainText);
		String encryptText = SecurityUtil.encrypt(AESSecretMethod.AESECB, "1234567890123456", "9878543210123456", plainText);
		System.out.println("Ecrypt Text = " + encryptText + " : " + encryptText.length());
		String decryptText = SecurityUtil.decrypt(AESSecretMethod.AESECB, "1234567890123456", "9878543210123456", encryptText);
		System.out.println("Decrypt Text = " + decryptText );
		
		assertTrue(plainText.equals(decryptText));
	}
	
	
	@Test
	public void 암호화_테스트2() throws Exception {
		String securityKey = "MILKSECURETESTKEY";
		
		String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEF";
		System.out.println("Plain Text = " + plainText);
		String encryptText = BlowfishUtil.encrypt(securityKey, plainText);
		System.out.println("Ecrypt Text = " + encryptText + ":" + encryptText.length());
		String decryptText = BlowfishUtil.decrypt(securityKey, encryptText);
		System.out.println("Decrypt Text = " + decryptText );
		
		assertTrue(plainText.equals(decryptText));
	}

}
