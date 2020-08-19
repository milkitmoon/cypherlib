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
	 * @throws Exception 
	 */
	public static String encrypt(String securityKey, String str) throws Exception {
		CryptoSunJCE crypt = CryptoSunJCE.getInstance();
		crypt.setKeyEncode(securityKey.getBytes());
		String result = null;

		return crypt.encode(str);
	}

	/**
	 * blowfishDecoder
	 * Blowfish 복호화
	 * @param str
	 * @return
	 * @throws Exception 
	 */
	public static String decrypt(String securityKey, String str) throws Exception {
		CryptoSunJCE crypt = CryptoSunJCE.getInstance();
		crypt.setKeyEncode(securityKey.getBytes());
		
		return crypt.decode(str);
	}
}
