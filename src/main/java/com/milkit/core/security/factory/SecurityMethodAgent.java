package com.milkit.core.security.factory;

import com.milkit.core.annotations.encrypt.Encrypt.EncryptAlgorithm;
import com.milkit.core.security.SecurityUtil;
import com.milkit.core.security.method.BlowfishSecretMethod;
import com.milkit.core.security.method.SecretMethod;

import org.apache.log4j.Logger;

public class SecurityMethodAgent {
	
	private static final Logger logger = Logger.getLogger(SecurityMethodAgent.class);
	
	public static String encrypt(EncryptAlgorithm algorithm, String securityKey, String securityKeyIV, String painStr, boolean encodeHashAsBase64) throws Exception {
		String cipherText = null;
		
		if(algorithm == null || algorithm.equals("")) {
			algorithm = EncryptAlgorithm.BlowfishECB;
		}
		
		if(securityKey == null || securityKey.equals("")) {
			throw new IllegalArgumentException("암호화 키값이 존재하지 않습니다.");
		}
		
		if(algorithm != EncryptAlgorithm.BlowfishECB && (securityKeyIV == null || securityKeyIV.equals(""))) {
			throw new IllegalArgumentException("암호화 IV값이 존재하지 않습니다.");
		}
		
		if(algorithm == EncryptAlgorithm.BlowfishECB) {
			cipherText = SecurityUtil.encrypt(algorithm.getValue(), securityKey, null, painStr, encodeHashAsBase64);
		} else {
			cipherText = SecurityUtil.encrypt(algorithm.getValue(), securityKey, securityKeyIV, painStr, encodeHashAsBase64);
		}
		
		return cipherText;
	}

	
	public static String encrypt(EncryptAlgorithm algorithm, String securityKey, String securityKeyIV, String painStr) throws Exception {
		return encrypt(algorithm, securityKey, securityKeyIV, painStr, true);
	}
	
	public static String encrypt(String securityKey, String securityKeyIV,  String painStr, boolean encodeHashAsBase64) throws Exception {
		return encrypt(EncryptAlgorithm.BlowfishECB, securityKey, securityKeyIV, painStr, encodeHashAsBase64);
	}

	
	public static String encrypt(String securityKey, String securityKeyIV, String painStr) throws Exception {
		return encrypt(EncryptAlgorithm.BlowfishECB, securityKey, securityKeyIV, painStr, true);
	}
	

	
	
	public static String decrypt(EncryptAlgorithm algorithm, String securityKey, String securityKeyIV, String cipherText, boolean encodeHashAsBase64) throws Exception {
		String clearText = null;
		if(algorithm == null || algorithm.equals("")) {
			algorithm = EncryptAlgorithm.BlowfishECB;
		}
		
		if(securityKey == null || securityKey.equals("")) {
			throw new IllegalArgumentException("암호화 키값이 존재하지 않습니다.");
		}
		
		if(algorithm != EncryptAlgorithm.BlowfishECB && (securityKeyIV == null || securityKeyIV.equals(""))) {
			throw new IllegalArgumentException("암호화 IV값이 존재하지 않습니다.");
		}
		
		if(algorithm == EncryptAlgorithm.BlowfishECB) {
			clearText = SecurityUtil.decrypt(algorithm.getValue(), securityKey, null, cipherText, encodeHashAsBase64);
		} else {
			clearText = SecurityUtil.decrypt(algorithm.getValue(), securityKey, securityKeyIV, cipherText, encodeHashAsBase64);
		}
		
		return clearText;
	}
	
	public static String decrypt(EncryptAlgorithm algorithm, String securityKey, String securityKeyIV, String cipherText) throws Exception {
		return decrypt(algorithm, securityKey, securityKeyIV, cipherText, true);
	}
	
	public static String decrypt(String securityKey, String securityKeyIV, String cipherText, boolean encodeHashAsBase64) throws Exception {
		return decrypt(EncryptAlgorithm.BlowfishECB, securityKey, securityKeyIV, cipherText, encodeHashAsBase64);
	}

	
	public static String decrypt(String securityKey, String securityKeyIV, String cipherText) throws Exception {
		return decrypt(EncryptAlgorithm.BlowfishECB, securityKey, securityKeyIV, cipherText, true);
	}

}
