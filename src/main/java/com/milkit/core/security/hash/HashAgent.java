package com.milkit.core.security.hash;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import com.milkit.core.security.factory.MethodFactory;
import com.milkit.core.security.method.DigestMethod;
import com.milkit.core.util.UTF8Codec;

public class HashAgent {
	
	public static String hash(String algname, String rawPass, Object salt, String secretKey, boolean encodeHashAsBase64) throws Exception {
		String hashString = null;
		if(rawPass != null) {
			DigestMethod digestMethod = MethodFactory.getDigestMethod(algname, secretKey);
			digestMethod.update(rawPass.getBytes());
			
			if(salt != null) {
				digestMethod.update(salt.toString().getBytes());
			}
			
			byte[] digest = digestMethod.digest();
			if(encodeHashAsBase64 == true) {
//				hashString = new String(Base64.encodeBase64(digest));
				hashString = Base64.encodeBase64String(digest);
			} else {
				hashString = Hex.encodeHexString(digest);
			}
		}

		return hashString;
	}
	
	public static String hash(String algname, String rawPass, Object salt, boolean encodeHashAsBase64) throws Exception {
		return hash(algname, rawPass, salt, null, encodeHashAsBase64);
	}
	
	public static String hash(String algname, String rawPass, Object salt) throws Exception {
		return hash(algname, rawPass, salt, false);
	}
	
	public static String hash(String rawPass, Object salt, boolean encodeHashAsBase64) throws Exception {
		return hash(DigestMethod.SHA256, rawPass, salt, encodeHashAsBase64);
	}

	
	public static String hash(String rawPass, Object salt) throws Exception {
		return hash(DigestMethod.SHA256, rawPass, salt, false);
	}
	
	public static String hmacHash(String rawPass, String secretKey, boolean encodeHashAsBase64) throws Exception {
		return hash(DigestMethod.HMAC_SHA1, rawPass, null, secretKey, encodeHashAsBase64);
	}
	
	public static boolean isHashValid(String algname, String encPass, String rawPass, Object salt, boolean encodeHashAsBase64) throws Exception {
        String pass1 = (new StringBuilder()).append("").append(encPass).toString();
        String pass2 = hash(algname, rawPass, salt, encodeHashAsBase64);
        
        return paramEquals(pass1, pass2);
	}
	

	
	private static boolean paramEquals(String expected, String actual) {
        byte expectedBytes[] = bytesUtf8(expected);
        byte actualBytes[] = bytesUtf8(actual);
        int expectedLength = expectedBytes != null ? expectedBytes.length : -1;
        int actualLength = actualBytes != null ? actualBytes.length : -1;
        if(expectedLength != actualLength)
            return false;
        int result = 0;
        for(int i = 0; i < expectedLength; i++)
            result |= expectedBytes[i] ^ actualBytes[i];

        return result == 0;
    }
	
	private static byte[] bytesUtf8(String s) {
        if(s == null) {
            return null;
        } else {
            return UTF8Codec.encode(s);
        }
    }
}
