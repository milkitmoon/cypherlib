package com.milkit.core.security.method;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

//import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SeedSecretMethod extends SecretMethodImpl {
	public static final String SEEDECB = "SEED-ECB";
	public static final String SEEDCBC = "SEED-CBC";

	private String ciphermode;
	
	Cipher cipher = null;
//	private SecretKeySpec seedkey = null;
	private SecretKey seedkey = null;
	private IvParameterSpec iv = null;

	static {
//		Security.addProvider(new InitechProvider());
//		Security.addProvider(new BouncyCastleProvider());
	}
	
	public SeedSecretMethod() {
	}
	
	/*
	 Cipher cip = Cipher.getInstance("SEED/CBC/PKCS5Padding","Initech");
			SecretKeyFactory skf = SecretKeyFactory.getInstance("SEED");
			SecretKey sk = skf.generateSecret(new SecretKeySpec(Hex.decodeHex(key.toCharArray()), "SEED"));
			
			Cipher cip = Cipher.getInstance("SEED/CBC/PKCS5Padding","Initech");
			SecretKeyFactory skf = SecretKeyFactory.getInstance("SEED");
			SecretKey sk = skf.generateSecret(new SecretKeySpec(Hex.decodeHex(key.toCharArray()), "SEED"));
			// 암호화
			cip.init(Cipher.DECRYPT_MODE, sk, new IvParameterSpec(Hex.decodeHex(iv.toCharArray())));
	 */
	public void setCipherMode(String ciphermode) throws GeneralSecurityException {
		if (ciphermode.indexOf("CBC") >= 0) {
			cipher = Cipher.getInstance("SEED/CBC/PKCS5Padding", "Initech");
		} else if (ciphermode.indexOf("ECB") >= 0) {
			cipher = Cipher.getInstance("SEED/ECB/PKCS5Padding", "Initech");
		} else {
			throw new NoSuchAlgorithmException();
		}
		
		this.ciphermode = ciphermode;
	}
	
	@Override
	public String getCipherMode() throws Exception {
		return ciphermode;
	}
	
	public void setKey(Object key) throws GeneralSecurityException {
		SecretKeyFactory skf = SecretKeyFactory.getInstance("SEED");
		seedkey = skf.generateSecret(new SecretKeySpec((byte[])key, "SEED"));
//		seedkey = new SecretKeySpec((byte[])key, "SEED");
	}

	public void setIv(Object iv) {
		this.iv = new IvParameterSpec((byte[])iv);
	}

	public byte[] encrypt(byte[] data) throws GeneralSecurityException {
		if (this.iv != null)
			cipher.init(Cipher.ENCRYPT_MODE, seedkey, iv);
		else
			cipher.init(Cipher.ENCRYPT_MODE, seedkey);
		
		byte[] retbuf = cipher.doFinal(data);

		if(isEnabeBase64() == true) {
			return Base64.encodeBase64(retbuf);
		} else {
			return retbuf;
		}
	}

	public byte[] decrypt(byte[] data) throws GeneralSecurityException {
		if (this.iv != null)
			cipher.init(Cipher.DECRYPT_MODE, seedkey, iv);
		else
			cipher.init(Cipher.DECRYPT_MODE, seedkey);
		
		if(isEnabeBase64() == true) {
			return cipher.doFinal( Base64.decodeBase64(data) );
		} else {
			return cipher.doFinal( data );
		}
	}
}
