package com.milkit.core.security;


/**
 * SecurityUtil
 * @author jsmarch
 * @date 2010.10.21
 */
public class BlowfishUtil {
	
	/**
	 * bolwfishEncoder
	 * Blowfish 암호화
	 * @param str
	 * @return
	 */
	public static String encrypt(String str) {
		return encrypt(SecurityGlobal.SecurityKey, str);
	}
	
	/**
	 * bolwfishEncoder
	 * Blowfish 암호화
	 * @param str
	 * @return
	 */
	public static String encrypt(String securityKey, String str) {
		CryptoSunJCE crypt = CryptoSunJCE.getInstance();
		crypt.setKeyEncode(securityKey.getBytes());
		String result = null;
		try {
			result = crypt.encode(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * blowfishDecoder
	 * Blowfish 복호화
	 * @param str
	 * @return
	 */
	public static String decrypt(String str) {
		return decrypt(SecurityGlobal.SecurityKey, str);
	}
	
	public static String decrypt(String securityKey, String str) {
		String result = null;
		try {
			CryptoSunJCE crypt = CryptoSunJCE.getInstance();
			crypt.setKeyEncode(securityKey.getBytes());
			result = crypt.decode(str);
		} catch (java.lang.IllegalArgumentException e) {
			result = str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
