package com.milkit.core.security.method;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class MD5DigestMethod implements DigestMethod {

	MessageDigest md = null;
	
	static {
//		Security.addProvider(new InitechProvider());
//		Security.addProvider(new BouncyCastleProvider());
	}
	
	public MD5DigestMethod() throws Exception {
//		md = MessageDigest.getInstance("SHA1", "BC");
		md = MessageDigest.getInstance("MD5");
	}
	
	public void update(byte[] mddata) {
		md.update(mddata);
	}
	
	public void update(byte mddata) {
		md.update(mddata);
	}

	public byte[] digest() {
		return md.digest();
	}

	public byte[] digest(byte[] mddata) {
		return md.digest(mddata);
	}

	public void reset() {
		md.reset();
	}

	public void setKey(byte[] key) {
		
	}

}
