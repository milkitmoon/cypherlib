package com.milkit.core.security.method;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.Cipher;

//import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class RSASecretMethod extends SecretMethodImpl {
	public static final String RSAOAEP = "RSA-OAEP";
	
	private Cipher rsa;
	private Key key;
	
	private String ciphermode;
	
	static {
//		Security.addProvider(new BouncyCastleProvider());
//		Security.addProvider(new InitechProvider());
	}


	public void setCipherMode(String ciphermode) throws NoSuchAlgorithmException {
		try {
			rsa = Cipher.getInstance(ciphermode); //, "Initech");
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		this.ciphermode = ciphermode;
	}
	
	@Override
	public String getCipherMode() throws Exception {
		return ciphermode;
	}

	public void setKey(Object key) {
		this.key = (Key)key;
	}

	public void setIv(Object iv) {
	}
	
	
	public byte[] decrypt(byte[] data) throws GeneralSecurityException {
		rsa.init(Cipher.DECRYPT_MODE, key);
		outputsize = rsa.getOutputSize(data.length);
		return rsa.doFinal(data);
	}

	public byte[] encrypt(byte[] data) throws GeneralSecurityException {
		rsa.init(Cipher.ENCRYPT_MODE, key);
		outputsize = rsa.getOutputSize(data.length);
		return rsa.doFinal(data);
	}

}
