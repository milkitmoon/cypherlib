package com.milkit.core.security.method;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

//import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class SHA256DigestMethod implements DigestMethod {
	MessageDigest md = null;
//	SHA256 md256 = null;
	
	static {
//		Security.addProvider(new InitechProvider());
//		Security.addProvider(new BouncyCastleProvider());
	}
	
	public SHA256DigestMethod() throws NoSuchAlgorithmException, NoSuchProviderException {
//		md = MessageDigest.getInstance("SHA256", "Initech");
		md = MessageDigest.getInstance("SHA-256");
//		md256 = new SHA256();
	}
	
	public void update(byte[] mddata) {
		md.update(mddata);
//		md256.update(mddata);
	}
	
	public void update(byte mddata) {
		md.update(mddata);
//		md256.update(mddata);
	}

	public byte[] digest() {
		return md.digest();
//		return md256.digest();
	}

	public byte[] digest(byte[] mddata) {
		return md.digest(mddata);
//		return md256.digest(mddata);
	}

	public void reset() {
		md.reset();
//		md256.reset();
	}

	public void setKey(byte[] key) {
		
	}
}
