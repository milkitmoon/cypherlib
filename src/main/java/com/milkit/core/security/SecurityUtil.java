package com.milkit.core.security;

import com.milkit.core.security.factory.MethodFactory;
import com.milkit.core.security.method.SecretMethod;


public class SecurityUtil {
	
	
	public static String encrypt(String algname, String securityKey, String painStr) throws Exception {
		return encrypt(algname, securityKey, null, painStr, true);
	}
	
	public static String encrypt(String algname, String securityKey, String securityKeyIV, String painStr) throws Exception {
		return encrypt(algname, securityKey, securityKeyIV, painStr, true);
	}
	
	public static String encrypt(String algname, String securityKey, String securityKeyIV, String painStr, boolean enabeBase64) throws Exception {
		
		if(algname == null || securityKey == null || painStr == null) {
			return null;
		}
		
		SecretMethod secretMethod = MethodFactory.getSecretMethod(algname, enabeBase64);
		secretMethod.setKey(securityKey.getBytes());
		
		if(securityKeyIV != null && !securityKeyIV.equals("")) {
			secretMethod.setIv(securityKeyIV.getBytes());
		}
		byte[] encBytes = secretMethod.encrypt(painStr.getBytes());
		
		return new String(encBytes);
	}

	public static String decrypt(String algname, String securityKey, String encStr) throws Exception {
		return decrypt(algname, securityKey, null, encStr, true);
	}
	
	public static String decrypt(String algname, String securityKey, String securityKeyIV, String encStr) throws Exception {
		return decrypt(algname, securityKey, securityKeyIV, encStr, true);
	}

	public static String decrypt(String algname, String securityKey, String securityKeyIV, String encStr, boolean enabeBase64) throws Exception {
		if(algname == null || securityKey == null || encStr == null) {
			return null;
		}
		
		SecretMethod secretMethod = MethodFactory.getSecretMethod(algname, enabeBase64);
		secretMethod.setKey(securityKey.getBytes());
		
		if(securityKeyIV != null && !securityKeyIV.equals("")) {
			secretMethod.setIv(securityKeyIV.getBytes());
		}
		byte[] decBytes = secretMethod.decrypt(encStr.getBytes());
		
		return new String(decBytes);
	}
}
