package com.milkit.core.security.method;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

//import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class SHA1DigestMethod implements DigestMethod {
	MessageDigest md = null;
	
	static {
//		Security.addProvider(new InitechProvider());
//		Security.addProvider(new BouncyCastleProvider());
	}
	
	public SHA1DigestMethod() throws NoSuchAlgorithmException, NoSuchProviderException {
//		md = MessageDigest.getInstance("SHA1", "BC");
		md = MessageDigest.getInstance("SHA1");
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
