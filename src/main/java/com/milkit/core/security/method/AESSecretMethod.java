package com.milkit.core.security.method;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


public class AESSecretMethod extends SecretMethodImpl {
	
	public static final String AESECB = "AES-ECB";
	public static final String AESCBC = "AES-CBC";

	private String ciphermode;
	
	Cipher cipher = null;
//	private SecretKeySpec seedkey = null;
	private SecretKey seedkey = null;
	private IvParameterSpec iv = null;
	
	public static String SECRET_KEY_TYPE = "AES";
    public static int KEYSIZE = 256;
	

	@Override
	public void setCipherMode(String ciphermode) throws GeneralSecurityException {
		if (ciphermode.indexOf("CBC") >= 0) {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} else if (ciphermode.indexOf("ECB") >= 0) {
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		} else {
			throw new NoSuchAlgorithmException();
		}
		
		this.ciphermode = ciphermode;
	}
	

	@Override
	public String getCipherMode() throws Exception {
		return ciphermode;
	}

	@Override
	public void setKey(Object key) throws Exception {
		
		byte[] keyBytes = (byte[])key;
//ByteUtil.printBytesHexa(keyBytes, 0, keyBytes.length);
        seedkey = new SecretKeySpec((byte[])key, "AES");
        
//ByteUtil.printBytesHexa(seedkey.getEncoded(), 0, seedkey.getEncoded().length);

//        seedkey = (SecretKey) new SecretKeySpec((byte[])key, "AES");
        
//		seedkey = new SecretKeySpec((byte[])key, "AES");
	}

	@Override
	public void setIv(Object iv) {
		if(ciphermode != null && ciphermode.indexOf("CBC") >= 0) {
			this.iv = new IvParameterSpec((byte[])iv);
		}
	}

	@Override
	public byte[] encrypt(byte[] data) throws GeneralSecurityException {
/*
		if (this.iv == null) {
			this.iv = new IvParameterSpec(seedkey.getEncoded());
		}
*/
		cipher.init(Cipher.ENCRYPT_MODE, seedkey, iv);
		
		byte[] retbuf = cipher.doFinal(data);

		if(isEnabeBase64() == true) {
			return Base64.encodeBase64(retbuf);
		} else {
			return retbuf;
		}
	}

	@Override
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
