package com.milkit.core.security.factory;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import com.milkit.core.security.method.AESSecretMethod;
import com.milkit.core.security.method.BlowfishSecretMethod;
import com.milkit.core.security.method.DigestMethod;
import com.milkit.core.security.method.HMacDigestMethod;
import com.milkit.core.security.method.MD5DigestMethod;
import com.milkit.core.security.method.RSASecretMethod;
import com.milkit.core.security.method.SHA1DigestMethod;
import com.milkit.core.security.method.SHA256DigestMethod;
import com.milkit.core.security.method.SHA512DigestMethod;
import com.milkit.core.security.method.SecretMethod;
import com.milkit.core.security.method.SeedSecretMethod;


public class MethodFactory {
	public static SecretMethod getSecretMethod(String algname) throws Exception {
		return getSecretMethod(algname, true);
	}
	
	public static SecretMethod getSecretMethod(String algname, boolean enabeBase64) throws Exception {
		SecretMethod secretMethod = null;
		
		if (algname.equals(AESSecretMethod.AESCBC)) {
			secretMethod = new AESSecretMethod();
			secretMethod.setCipherMode("AES/CBC/PKCS5Padding");
		} else if (algname.equals(AESSecretMethod.AESECB)) {
			secretMethod = new AESSecretMethod();
			secretMethod.setCipherMode("AES/ECB/PKCS5Padding");
		} else if (algname.equals(SeedSecretMethod.SEEDCBC)) {
			secretMethod = new SeedSecretMethod();
			secretMethod.setCipherMode("SEED/CBC/PKCS5Padding");
		} else if (algname.equals(SeedSecretMethod.SEEDECB)) {
			secretMethod = new SeedSecretMethod();
			secretMethod.setCipherMode("SEED/ECB/PKCS5Padding");
		} else if (algname.equals(RSASecretMethod.RSAOAEP)){
			secretMethod = new RSASecretMethod();
//			secretMethod.setCipherMode("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
			secretMethod.setCipherMode("RSA/ECB/OAEPPadding");
		} else if (algname.equals(BlowfishSecretMethod.BLOWFISHCBC)) {
			secretMethod = new BlowfishSecretMethod();
			secretMethod.setCipherMode("Blowfish/CBC/PKCS5Padding");
		} else if (algname.equals(BlowfishSecretMethod.BLOWFISHECB)) {
			secretMethod= new BlowfishSecretMethod();
			secretMethod.setCipherMode("Blowfish/ECB/PKCS5Padding");
		} else {
			throw new NoSuchAlgorithmException("Cannot find " + algname);
		}
		
		secretMethod.setEnabeBase64(enabeBase64);
		
		return secretMethod;
	}
	
	
	public static DigestMethod getDigestMethod(String algname) throws Exception {
		return getDigestMethod(algname, null);
	}
	
	public static DigestMethod getDigestMethod(String algname, String initKey) throws Exception {
		if (algname == null || algname.length() == 0 ||algname.equals("None") ) {
			throw new NoSuchAlgorithmException("Cannot find " + algname);
		}

		if (algname.equals(DigestMethod.HMAC_SHA1)) {
			return new HMacDigestMethod(initKey);
		} else if (algname.equals(DigestMethod.SHA1)) {
			return new SHA1DigestMethod();
		} else if (algname.equals(DigestMethod.SHA256)) {
			return new SHA256DigestMethod();
		} else if (algname.equals(DigestMethod.SHA512)) {
			return new SHA512DigestMethod();
		} else if (algname.equals(DigestMethod.MD5)) {
			return new MD5DigestMethod();
		} else {
			throw new NoSuchAlgorithmException("Cannot find " + algname);
		}
	}
}
