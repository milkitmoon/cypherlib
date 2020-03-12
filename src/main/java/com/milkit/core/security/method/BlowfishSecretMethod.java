package com.milkit.core.security.method;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


public class BlowfishSecretMethod extends SecretMethodImpl {
	
	public static final String BLOWFISHECB = "BLOWFISH-ECB";
	public static final String BLOWFISHCBC = "BLOWFISH-CBC";
	
	private String ciphermode;
	
	private Cipher cipher = null;
	private SecretKeySpec seedkey = null;
//	private SecretKey seedkey = null;
	private IvParameterSpec iv = null;

	@Override
	public void setCipherMode(String ciphermode) throws GeneralSecurityException {
		if (ciphermode.indexOf("CBC") >= 0) {
			cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
		} else if (ciphermode.indexOf("ECB") >= 0) {
			cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
		} else {
			throw new NoSuchAlgorithmException();
		}
		
		this.ciphermode = ciphermode;
	}

	@Override
	public String getCipherMode() throws Exception {
		return ciphermode;
	}
	
	@Override
	public void setKey(Object key) throws GeneralSecurityException {
//		SecretKeyFactory skf = SecretKeyFactory.getInstance("Blowfish");
//		seedkey = skf.generateSecret(new SecretKeySpec((byte[])key, "Blowfish"));
		seedkey = new SecretKeySpec((byte[])key, "Blowfish");
	}

	@Override
	public void setIv(Object iv) {
		if(ciphermode != null && ciphermode.indexOf("CBC") >= 0) {
			this.iv = new IvParameterSpec((byte[])iv);
		}
	}

	@Override
	public byte[] encrypt(byte[] data) throws GeneralSecurityException {
		if (this.iv != null) {
			cipher.init(Cipher.ENCRYPT_MODE, seedkey, iv);
		} else {
			cipher.init(Cipher.ENCRYPT_MODE, seedkey);
		}
		
		byte[] retbuf = cipher.doFinal(	data );

		if(isEnabeBase64() == true) {
			return Base64.encodeBase64(retbuf);
		} else {
			return retbuf;
		}
	}

	@Override
	public byte[] decrypt(byte[] data) throws GeneralSecurityException {
		if (this.iv != null) {
			cipher.init(Cipher.DECRYPT_MODE, seedkey, iv);
		} else {
			cipher.init(Cipher.DECRYPT_MODE, seedkey);
		}

		if(isEnabeBase64() == true) {
			return cipher.doFinal( Base64.decodeBase64(data) );
		} else {
			return cipher.doFinal( data );
		}
	}
	
	
    /**
     * @Method Name : hexToBytes
     * 문자열의 각 글자들의 ascii 값으로 byte 배열로 
     * @param str
     * @return
     */
    private static byte[] hexToBytes(String str) {
        if (str == null) {
            return null;
        } else if (str.length() < 2) {
            return null;
        } else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i = 0; i < len; i++) {
                buffer[i] = (byte) Integer.parseInt(
                        str.substring(i * 2, i * 2 + 2), 16);
            }
            return buffer;
        }
 
    }
 
    /**
     * @Method Name : bytesToHex
     * byte 배열의 각각의 값을 해당하는 ascii 문자로 변환해서 문자열로 반환
     * @param data
     * @return
     */
    private static String bytesToHex(byte[] data) {
        if (data == null) {
            return null;
        } else {
            int len = data.length;
            String str = "";
            for (int i = 0; i < len; i++) {
                if ((data[i] & 0xFF) < 16)
                    str = str + "0"
                            + java.lang.Integer.toHexString(data[i] & 0xFF);
                else
                    str = str + java.lang.Integer.toHexString(data[i] & 0xFF);
            }
            return str.toUpperCase();
        }
    }

}
