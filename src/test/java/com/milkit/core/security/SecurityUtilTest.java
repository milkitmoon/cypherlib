package com.milkit.core.security;

import org.junit.Test;

import com.milkit.core.security.BlowfishUtil;
import com.milkit.core.security.SecurityGlobal;
import com.milkit.core.security.SecurityUtil;
import com.milkit.core.security.method.AESSecretMethod;
import com.milkit.core.security.method.BlowfishSecretMethod;

public class SecurityUtilTest {
	

	@Test
	public void BLOWFISHECB암호화_테스트() throws Exception {
		String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEF";
		System.out.println("Plain Text = " + plainText);
		String encryptText = SecurityUtil.encrypt(BlowfishSecretMethod.BLOWFISHECB, SecurityGlobal.SecurityKey, plainText);
		System.out.println("Ecrypt Text = " + encryptText + " : " + encryptText.length());
		String decryptText = SecurityUtil.decrypt(BlowfishSecretMethod.BLOWFISHECB, SecurityGlobal.SecurityKey, encryptText);
		System.out.println("Decrypt Text = " + decryptText );
	}
	
	@Test
	public void BLOWFISHCBC암호화_테스트() throws Exception {
		String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEF";
		System.out.println("Plain Text = " + plainText);
		String encryptText = SecurityUtil.encrypt(BlowfishSecretMethod.BLOWFISHCBC, SecurityGlobal.AES128SecurityKey, SecurityGlobal.SecurityKeyIV, plainText);
		System.out.println("Ecrypt Text = " + encryptText + " : " + encryptText.length());
		String decryptText = SecurityUtil.decrypt(BlowfishSecretMethod.BLOWFISHCBC, SecurityGlobal.AES128SecurityKey, SecurityGlobal.SecurityKeyIV, encryptText);
		System.out.println("Decrypt Text = " + decryptText );
	}
	
	
	@Test
	public void AES128CBC암호화_테스트() throws Exception {
		String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEF";
		System.out.println("Plain Text = " + plainText);
		String encryptText = SecurityUtil.encrypt(AESSecretMethod.AESCBC, SecurityGlobal.AES128SecurityKey, SecurityGlobal.AES128SecurityKeyIV, plainText);
		System.out.println("Ecrypt Text = " + encryptText + " : " + encryptText.length());
		String decryptText = SecurityUtil.decrypt(AESSecretMethod.AESCBC, SecurityGlobal.AES128SecurityKey, SecurityGlobal.AES128SecurityKeyIV, encryptText);
		System.out.println("Decrypt Text = " + decryptText );
	}
	
	@Test
	public void AES128ECB암호화_테스트() throws Exception {
		String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEF";
		System.out.println("Plain Text = " + plainText);
		String encryptText = SecurityUtil.encrypt(AESSecretMethod.AESECB, SecurityGlobal.AES128SecurityKey, SecurityGlobal.AES128SecurityKeyIV, plainText);
		System.out.println("Ecrypt Text = " + encryptText + " : " + encryptText.length());
		String decryptText = SecurityUtil.decrypt(AESSecretMethod.AESECB, SecurityGlobal.AES128SecurityKey, SecurityGlobal.AES128SecurityKeyIV, encryptText);
		System.out.println("Decrypt Text = " + decryptText );
	}
	
	
	@Test
	public void 암호화_테스트2() {
		String securityKey = SecurityGlobal.SecurityKey;
		
		String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEF";
		System.out.println("Plain Text = " + plainText);
		String encryptText = BlowfishUtil.encrypt(securityKey, plainText);
		System.out.println("Ecrypt Text = " + encryptText + ":" + encryptText.length());
		String decryptText = BlowfishUtil.decrypt(securityKey, encryptText);
		System.out.println("Decrypt Text = " + decryptText );
	}

}
