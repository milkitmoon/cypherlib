package com.milkit.core.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;



/**
 * The Class CryptoSunJCE.
 */
public class CryptoSunJCE {
	
	/** The key. */
	private SecretKey key = null;

	/** The default key. */
	private static SecretKey defaultKey = null;

	/** The algorithm. */
	private static String algorithm = "Blowfish";

	/** The Constant defaultkeys. */
	private final static byte[] defaultkeys = "J8vx62BPwaEZUl+I".getBytes();

	static {
		try {
			defaultKey = (SecretKey) new SecretKeySpec(defaultkeys, algorithm);
		} catch (Exception e) {
			e.printStackTrace();
			defaultKey = null;
		}
	};

	/**
	 * Instantiates a new crypto sun jce.
	 */
	private CryptoSunJCE() {
	}

	/**
	 * Gets the single instance of CryptoSunJCE.
	 * 
	 * @return single instance of CryptoSunJCE
	 */
	public static CryptoSunJCE getInstance() {
		// return instance;
		return new CryptoSunJCE();
	}

	/**
	 * Gets the key.
	 * 
	 * @return the key
	 */
	public SecretKey getKey() {
		return this.key;
	}

	/**
	 * Sets the key.
	 * 
	 * @param key the new key
	 */
	public void setKey(SecretKey key) {
		this.key = key;
	}

	/**
	 * Sets the key encode.
	 * 
	 * @param keys the new key encode
	 */
	public void setKeyEncode(byte[] keys) {
		try {
			this.key = (SecretKey) new SecretKeySpec(keys, algorithm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the algorithm.
	 * 
	 * @return the algorithm
	 */
	public String getAlgorithm() {
		return algorithm;
	}

	/**
	 * Task.
	 * 
	 * @param mode the mode
	 * @param bytes the bytes
	 * 
	 * @return the byte[]
	 * 
	 * @throws Exception the exception
	 */
	public byte[] task(int mode, byte[] bytes) throws Exception {
		Cipher cipher = Cipher.getInstance(algorithm);
		SecretKey ky = this.key == null ? defaultKey : this.key;
		cipher.init(mode, ky);

		return cipher.doFinal(bytes);
	}

	/**
	 * Encrypt.
	 * 
	 * @param bytes the bytes
	 * 
	 * @return the byte[]
	 * 
	 * @throws Exception the exception
	 */
	public byte[] encrypt(byte[] bytes) throws Exception {
		return task(Cipher.ENCRYPT_MODE, bytes);
	}

	/**
	 * Decrypt.
	 * 
	 * @param bytes the bytes
	 * 
	 * @return the byte[]
	 * 
	 * @throws Exception the exception
	 */
	public byte[] decrypt(byte[] bytes) throws Exception {
		return task(Cipher.DECRYPT_MODE, bytes);
	}

	/**
	 * Encode.
	 * 
	 * @param src the src
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
	 */
	public String encode(String src) throws Exception {
		if (src == null)
			return null;
		byte[] srcBytes = src.getBytes();
		byte[] encBytes = this.encrypt(srcBytes);
		return Base64.encode(encBytes);
	}
	
	/**
	 * Encode.
	 * 
	 * @param src the src
	 * @param charset
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
	 */
	public String encode(String src, String charset) throws Exception {
		if (src == null)
			return null;
		byte[] srcBytes = src.getBytes(charset);
		byte[] encBytes = this.encrypt(srcBytes);
		return Base64.encode(encBytes);
	}

	/**
	 * Decode.
	 * 
	 * @param encrypted the encrypted
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
	 */
	public String decode(String encrypted) throws Exception {
		if (encrypted == null)
			return null;
		byte[] encBytes = Base64.decode(encrypted);
		byte[] orgBytes = this.decrypt(encBytes);

		return new String(orgBytes);
	}
	
	/**
	 * Decode.
	 * 
	 * @param encrypted the encrypted
	 * @param charset
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
	 */
	public String decode(String encrypted, String charset) throws Exception {
		if (encrypted == null)
			return null;
		byte[] encBytes = Base64.decode(encrypted);
		byte[] orgBytes = this.decrypt(encBytes);
		
		return new String(orgBytes, charset);
	}
}