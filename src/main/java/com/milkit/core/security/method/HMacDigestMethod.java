package com.milkit.core.security.method;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

//import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class HMacDigestMethod implements DigestMethod {
	private Mac hmac = null;
	
	static {
//		Security.addProvider(new InitechProvider());
//		Security.addProvider(new BouncyCastleProvider());
	}
	
	public HMacDigestMethod() throws NoSuchAlgorithmException, NoSuchProviderException {
//		hmac = Mac.getInstance("HmacSHA1", "BC");
		hmac = Mac.getInstance("HmacSHA1");
	}
	
	public HMacDigestMethod(String initKey) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
		this();
		if(initKey == null) {
			throw new InvalidKeyException();
		}
		
		hmac.init(new SecretKeySpec(initKey.getBytes(), "HmacSHA1"));
	}
	
	public void setKey(byte[] key) throws InvalidKeyException {
//		hmac.init(new SecretKeySpec(key, "HmacHSA1"));
		hmac.init(new SecretKeySpec(key, "HmacSHA1"));
	}

	public void update(byte[] mddata) {
		hmac.update(mddata);
	}

	public void update(byte mddata) {
		hmac.update(mddata);
	}
	
	public byte[] digest() {
		return hmac.doFinal();
	}
	

	public byte[] digest(byte[] mddata) {
		return hmac.doFinal(mddata);
	}

	public void reset() {
		hmac.reset();
	}

}
