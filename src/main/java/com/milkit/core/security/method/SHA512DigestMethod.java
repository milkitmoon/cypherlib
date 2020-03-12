package com.milkit.core.security.method;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

//import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class SHA512DigestMethod implements DigestMethod {
	MessageDigest md = null;
//	SHA512 md512 = null;
	
	static {
//		Security.addProvider(new InitechProvider());
//		Security.addProvider(new BouncyCastleProvider());
	}
	
	public SHA512DigestMethod() throws NoSuchAlgorithmException, NoSuchProviderException {
//		md = MessageDigest.getInstance("SHA512", "BC");
		md = MessageDigest.getInstance("SHA-512");
//		md512 = new SHA512();
	}
	
	public void update(byte[] mddata) {
		md.update(mddata);
//		md512.update(mddata);
	}
	
	public void update(byte mddata) {
		md.update(mddata);
//		md512.update(mddata);
	}

	public byte[] digest() {
		return md.digest();
//		return md512.digest();
	}

	public byte[] digest(byte[] mddata) {
		return md.digest(mddata);
//		return md512.digest(mddata);
	}

	public void reset() {
		md.reset();
//		md512.reset();
	}

	public void setKey(byte[] key) {
		
	}
}
