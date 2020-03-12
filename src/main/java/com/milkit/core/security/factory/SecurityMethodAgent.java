package com.milkit.core.security.factory;

import com.milkit.core.annotations.encrypt.Encrypt.Algorithm;
import com.milkit.core.security.SecurityGlobal;
import com.milkit.core.security.SecurityUtil;
import com.milkit.core.security.method.BlowfishSecretMethod;
import com.milkit.core.security.method.SecretMethod;

import org.apache.log4j.Logger;

public class SecurityMethodAgent {
	
	private static final Logger logger = Logger.getLogger(SecurityMethodAgent.class);
	
	public static String encrypt(Algorithm algorithm, String securityKey, String securityKeyIV, String painStr, boolean encodeHashAsBase64) throws Exception {
		String cipherText = null;
		
		if(algorithm == null || algorithm.equals("")) {
			algorithm = Algorithm.BlowfishECB;
		}
		
		if(securityKey == null || securityKey.equals("")) {
			securityKey = SecurityGlobal.SecurityKey;
		}
		
		if(securityKeyIV == null || securityKeyIV.equals("")) {
			securityKeyIV = SecurityGlobal.SecurityKeyIV;
		}
		
		if(algorithm == Algorithm.BlowfishECB) {
			cipherText = SecurityUtil.encrypt(algorithm.getValue(), securityKey, null, painStr, encodeHashAsBase64);
		} else {
			cipherText = SecurityUtil.encrypt(algorithm.getValue(), securityKey, securityKeyIV, painStr, encodeHashAsBase64);
		}
		
		return cipherText;
	}

	
	public static String encrypt(Algorithm algorithm, String securityKey, String securityKeyIV, String painStr) throws Exception {
		return encrypt(algorithm, securityKey, securityKeyIV, painStr, true);
	}
	
	public static String encrypt(String securityKey, String securityKeyIV,  String painStr, boolean encodeHashAsBase64) throws Exception {
		return encrypt(Algorithm.BlowfishECB, securityKey, securityKeyIV, painStr, encodeHashAsBase64);
	}

	
	public static String encrypt(String securityKey, String securityKeyIV, String painStr) throws Exception {
		return encrypt(Algorithm.BlowfishECB, securityKey, securityKeyIV, painStr, true);
	}
	

	
	
	public static String decrypt(Algorithm algorithm, String securityKey, String securityKeyIV, String cipherText, boolean encodeHashAsBase64) throws Exception {
		String clearText = null;
		if(algorithm == null || algorithm.equals("")) {
			algorithm = Algorithm.BlowfishECB;
		}
		
		if(securityKey == null || securityKey.equals("")) {
			securityKey = SecurityGlobal.SecurityKey;
		}
		
		if(securityKeyIV == null || securityKeyIV.equals("")) {
			securityKeyIV = SecurityGlobal.SecurityKeyIV;
		}
		
		try {
			if(algorithm == Algorithm.BlowfishECB) {
				clearText = SecurityUtil.decrypt(algorithm.getValue(), securityKey, null, cipherText, encodeHashAsBase64);
			} else {
				clearText = SecurityUtil.decrypt(algorithm.getValue(), securityKey, securityKeyIV, cipherText, encodeHashAsBase64);
			}
		} catch(Exception ex) {
			logger.error("복호화오류", ex);
		}
		
		return clearText;
	}
	
	public static String decrypt(Algorithm algorithm, String securityKey, String securityKeyIV, String cipherText) throws Exception {
		return decrypt(algorithm, securityKey, securityKeyIV, cipherText, true);
	}
	
	public static String decrypt(String securityKey, String securityKeyIV, String cipherText, boolean encodeHashAsBase64) throws Exception {
		return decrypt(Algorithm.BlowfishECB, securityKey, securityKeyIV, cipherText, encodeHashAsBase64);
	}

	
	public static String decrypt(String securityKey, String securityKeyIV, String cipherText) throws Exception {
		return decrypt(Algorithm.BlowfishECB, securityKey, securityKeyIV, cipherText, true);
	}

}
