package com.milkit.core.security;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

import com.milkit.core.security.random.RandomAgent;


/**
 * RSA 비대칭키 암호화 알고리즘 사용을 위한 유틸리티 클래스
 *
 */
/**
* <pre>
* 1. 패키지명 : com.milkit.core.security
* 2. 타입명 : RSAUtils.java
* 3. 작성일 : 2015. 5. 26. 오전 10:13:10
* 4. 작성자 : milkit
* 5. 설명    : RSA 비대칭키 암호화 알고리즘 사용을 위한 유틸리티 클래스
* </pre>
*/
public class RSAUtils {
//	public static final String RSA = "RSA";
	public static final String RSA = "RSA/ECB/PKCS1Padding";
//	public static final String RSA = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
	
	private static KeyPair keyPair = null;
	
	
	private static Map<Integer, KeyPair> keyPairMap = new HashMap<Integer, KeyPair>();
	
	
	public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
		return generateKeyPair(1024);
	}
	
	public static void generateKeyPairMap(int mapSize) throws NoSuchAlgorithmException {
		for(int i=0; i<mapSize; i++) {
			keyPairMap.put(i, generateKeyPair());
		}
	}
	
	public static KeyPair getKeyPair(int mapKey) {
		return keyPairMap.get(mapKey);
	}
	
	public static KeyPair getRandomKeyPair() {
		int mapSize = keyPairMap.size();
		
		int mapKey = RandomAgent.getRangeRandomNumber(0, mapSize-1);
		
		return keyPairMap.get(mapKey);
	}
	
	public static KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(keySize);
				
		keyPair = keyPairGenerator.generateKeyPair();
		
		return keyPair;
	}
	
	public static KeyPair getKeyPair() throws NoSuchAlgorithmException {
		if(keyPair == null) {
			generateKeyPair();
		}
		
		return keyPair;
	}
	
	/**
	 * Public Key로 암호화한 후 결과로 출력된 byte 배열을 Base64로 인코딩하여 String으로 변환하여 리턴함
	 * @param text 암호화할 텍스트
	 * @param publicKey RSA 공개키
	 * @return Base64로 인코딩된 암호화 문자열
	 */
	public static String encrypt(String text, PublicKey publicKey) throws Exception {
		byte[] bytes = text.getBytes();

		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			
		return new String(Base64.encodeBase64(cipher.doFinal(bytes)));
	}
	
	/**
	 * Base64로 인코딩된 문자열을 받아 decode 시킨 후 RSA 비밀키(Private Key)를 이용하여 암호화된 텍스트를 원문으로 복호화
	 * @param encryptedBase64Text Base64로 인코딩된 암호화 문자열
	 * @param privateKey RSA 비밀 키
	 * @return 복호화된 텍스트
	 */
	public static String decrypt(String encryptedBase64Text, PrivateKey privateKey) throws Exception {
		byte[] bytes = Base64.decodeBase64(encryptedBase64Text.getBytes());
		String decryptedText = null;
		try {
			Cipher cipher = Cipher.getInstance(RSA);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			decryptedText = new String(cipher.doFinal(bytes));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return decryptedText;
	}
	
	/**
	 * RSA 공개키로부터 RSAPublicKeySpec 객체를 생성함
	 * @param publicKey 공개키
	 * @return RSAPublicKeySpec
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 */
	public static RSAPublicKeySpec getRSAPublicKeySpec(PublicKey publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
		return KeyFactory.getInstance("RSA").getKeySpec(publicKey, RSAPublicKeySpec.class);
	}
	
	/**
	 * RSA 비밀키로부터 RSAPrivateKeySpec 객체를 생성함
	 * @param privateKey 비밀키
	 * @return RSAPrivateKeySpec
	 */
	public static RSAPrivateKeySpec getRSAPrivateKeySpec(PrivateKey privateKey) {
		RSAPrivateKeySpec spec = null;
		
		try {
			spec = KeyFactory.getInstance("RSA").getKeySpec(privateKey, RSAPrivateKeySpec.class);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return spec;
	}
	
	public static PublicKey getPublicKey() throws NoSuchAlgorithmException {
		return getKeyPair().getPublic();
	}
	
	public static PrivateKey getPrivateKey() throws NoSuchAlgorithmException {
		return getKeyPair().getPrivate();
	}
	
	public static String getPublicKeyModuleString(PublicKey publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
		return getPublicKeyModuleString(publicKey, 16);
	}
	
	public static String getPublicKeyModuleString(PublicKey publicKey, int radix) throws InvalidKeySpecException, NoSuchAlgorithmException {
		return getRSAPublicKeySpec(publicKey).getModulus().toString(radix);
	}
	
	public static String getPublicKeyExponentString(PublicKey publicKey, int radix) throws InvalidKeySpecException, NoSuchAlgorithmException {
		return getRSAPublicKeySpec(publicKey).getPublicExponent().toString(radix);
	}
	
	public static String getPublicKeyExponentString(PublicKey publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
		return getPublicKeyExponentString(publicKey, 16);
	}
	
	public static String getPrivateKeyModuleString(PrivateKey privateKey, int radix) {
		return getRSAPrivateKeySpec(privateKey).getModulus().toString(radix);
	}
	
	public static String getPrivateKeyModuleString(PrivateKey privateKey) {
		return getPrivateKeyModuleString(privateKey, 16);
	}
	
	public static String getPrivateKeyExponentString(PrivateKey privateKey, int radix) {
		return getRSAPrivateKeySpec(privateKey).getPrivateExponent().toString(radix);
	}
	
	public static String getPrivateKeyExponentString(PrivateKey privateKey) {
		return getPrivateKeyExponentString(privateKey, 16);
	}
	
	public static byte[] getPublicKeyBase64() throws NoSuchAlgorithmException {
		return Base64.encodeBase64( getKeyPair().getPublic().getEncoded() );
	}
	
	public static byte[] getPrivateKeyBase64() throws NoSuchAlgorithmException {
		return Base64.encodeBase64( getKeyPair().getPrivate().getEncoded() );
	}
	
	/**
	 * Moduls, Exponent 값을 이용하여 PublicKey 객체를 생성함
	 * @param modulus RSA Public Key Modulus
	 * @param exponent RSA Public Key exponent
	 * @return PublicKey 객체
	 */
	public static PublicKey getPublicKey(String modulus, String exponent) {
		BigInteger modulus_ = new BigInteger(modulus, 16);
		BigInteger exponent_ = new BigInteger(exponent, 16);
		PublicKey publicKey = null;
		
		try {
			publicKey = KeyFactory
					.getInstance("RSA")
					.generatePublic(new RSAPublicKeySpec(modulus_, exponent_));
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return publicKey;
	}
	
	public static PublicKey getPublicKey(byte[] keyBytes) {
		PublicKey publicKey = null;
		
		try {
			publicKey = KeyFactory
					.getInstance("RSA")
					.generatePublic(new X509EncodedKeySpec(keyBytes));
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return publicKey;
	}
	
	/**
	 * Modulus, Exponent 값을 이용하여 PrivateKey 객체를 생성함
	 * @param modulus RSA private key Modulus
	 * @param privateExponent RSA private key exponent
	 * @return PrivateKey 객체
	 */
	public static PrivateKey getPrivateKey(String modulus, String privateExponent) {
		BigInteger modulus_ = new BigInteger(modulus, 16);
		BigInteger privateExponent_ = new BigInteger(privateExponent, 16);
		PrivateKey privateKey = null;
		
		try {
			privateKey = KeyFactory
					.getInstance("RSA")
					.generatePrivate(new RSAPrivateKeySpec(modulus_, privateExponent_));
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return privateKey;
	}
}