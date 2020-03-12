package com.milkit.core.security.method;

import java.security.InvalidKeyException;

public interface DigestMethod {
	public static final String HMAC_SHA1 = "HMAC-SHA1";
	public static final String SHA1 = "SHA1";
	public static final String SHA256 = "SHA256";
	public static final String SHA512 = "SHA512";
	public static final String MD5 = "MD5";
	
	
	void setKey(byte[] key) throws InvalidKeyException;
	void update(byte[] mddata);
	void update(byte mddata);
	byte[] digest();
	byte[] digest(byte[] mddata);
	void reset();
}
