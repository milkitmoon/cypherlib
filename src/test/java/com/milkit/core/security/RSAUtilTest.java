package com.milkit.core.security;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAKeyGenParameterSpec;

import org.junit.Test;

import com.milkit.core.security.BlowfishUtil;
import com.milkit.core.security.RSAUtils;

public class RSAUtilTest {
	

	@Test
	public void RSA암호화_테스트() throws Exception {
		String plainText = "1234567890123456";
		System.out.println("Plain Text = " + plainText);
		
		String modulus = "B31F9097CA38B4548D7300A0768F262C58D92B190355E382DACA4B79DA52226758FD8EB23CE29F404433411AC90308BEEED093BA157E03982E587496A15BB47A371C32C6B665FF75D6E900CF28CF679E871FB06FF0E90431E829DBE8EBDF7D5024A2D32F8B0D50C0D592E5D31046DE275F81B106088EBECA434493458DBF2B804BC46EC973E8F47408CC454F03F24933A5B100001B47239B44A52CF6A40670CED35060EB84BD6D0829DF8EC84EC49D784CA8583F353086071604DB2F43FA243A05F031033FFB179D8CB281A814B01F8CCC2EC29196BB136905104E23832FA923185743E18924C4E9772ED094D98643C677DE99EF1E598452728A7AC3DF5A1AB9";
		String exponent = "010001";
		
//		PublicKey publicKey= RSAUtils.getPublicKey(modulus, exponent);
//		PrivateKey privateKey= RSAUtils.getPrivateKey(modulus, exponent);
		
		RSAUtils.generateKeyPair();
		PublicKey publicKey= RSAUtils.getPublicKey();
		PrivateKey privateKey= RSAUtils.getPrivateKey();
		
		PublicKey sndPublicKey = RSAUtils.getPublicKey(RSAUtils.getPublicKeyModuleString(publicKey), RSAUtils.getPublicKeyExponentString(publicKey));
		
		System.out.println( "public_key modulus:\n" + RSAUtils.getPublicKeyModuleString(publicKey) + "\nexponent:\n" + RSAUtils.getPublicKeyExponentString(publicKey));
		System.out.println( "private_key modulus:\n" + RSAUtils.getPrivateKeyModuleString(privateKey) + "\nexponent:\n" + RSAUtils.getPrivateKeyExponentString(privateKey));
		
		String encryptText = RSAUtils.encrypt(plainText, sndPublicKey);
		
		System.out.println("Ecrypt Text = " + encryptText + " : " + encryptText.length());
		
		String decryptText = RSAUtils.decrypt(encryptText, privateKey);
		
		System.out.println("Decrypt Text = " + decryptText );
	}
	
	
	@Test
	public void RSA암호화_테스트2() throws Exception {
		String plainText = "1234567890123456";
		System.out.println("Plain Text = " + plainText);
		
		String modulus = "B31F9097CA38B4548D7300A0768F262C58D92B190355E382DACA4B79DA52226758FD8EB23CE29F404433411AC90308BEEED093BA157E03982E587496A15BB47A371C32C6B665FF75D6E900CF28CF679E871FB06FF0E90431E829DBE8EBDF7D5024A2D32F8B0D50C0D592E5D31046DE275F81B106088EBECA434493458DBF2B804BC46EC973E8F47408CC454F03F24933A5B100001B47239B44A52CF6A40670CED35060EB84BD6D0829DF8EC84EC49D784CA8583F353086071604DB2F43FA243A05F031033FFB179D8CB281A814B01F8CCC2EC29196BB136905104E23832FA923185743E18924C4E9772ED094D98643C677DE99EF1E598452728A7AC3DF5A1AB9";
		String exponent = "010001";
		
//		PublicKey publicKey= RSAUtils.getPublicKey(modulus, exponent);
//		PrivateKey privateKey= RSAUtils.getPrivateKey(modulus, exponent);
		
		RSAUtils.generateKeyPair();
		PublicKey publicKey= RSAUtils.getPublicKey();
		PrivateKey privateKey= RSAUtils.getPrivateKey();
		
		
//		PublicKey sndPublicKey = RSAUtils.getPublicKey(RSAUtils.getPublicKeyModuleString(publicKey), RSAUtils.getPublicKeyExponentString(publicKey));
		PublicKey sndPublicKey = RSAUtils.getPublicKey( publicKey.getEncoded() );
		
		System.out.println( "public_key :\n" + new String( RSAUtils.getPublicKeyBase64() ));
		System.out.println( "public_key length:\n" + publicKey.getEncoded().length);
		System.out.println( "private_key :\n" + new String( RSAUtils.getPrivateKeyBase64() ));
		
		String encryptText = RSAUtils.encrypt(plainText, sndPublicKey);
		
		System.out.println("Ecrypt Text = " + encryptText + " : " + encryptText.length());
		
		String decryptText = RSAUtils.decrypt(encryptText, privateKey);
		
		System.out.println("Decrypt Text = " + decryptText );
	}
	
	
	@Test
	public void RSA고정키암호화_테스트() throws Exception {
		String plainText = "1234567890123456";
		System.out.println("Plain Text = " + plainText);
		
//		String modulus = "B31F9097CA38B4548D7300A0768F262C58D92B190355E382DACA4B79DA52226758FD8EB23CE29F404433411AC90308BEEED093BA157E03982E587496A15BB47A371C32C6B665FF75D6E900CF28CF679E871FB06FF0E90431E829DBE8EBDF7D5024A2D32F8B0D50C0D592E5D31046DE275F81B106088EBECA434493458DBF2B804BC46EC973E8F47408CC454F03F24933A5B100001B47239B44A52CF6A40670CED35060EB84BD6D0829DF8EC84EC49D784CA8583F353086071604DB2F43FA243A05F031033FFB179D8CB281A814B01F8CCC2EC29196BB136905104E23832FA923185743E18924C4E9772ED094D98643C677DE99EF1E598452728A7AC3DF5A1AB9";
//		String exponent = "10001";
		
		String modulus = "c520aa9920b0b1813ba4fe1cbc72ef59a451184a5d7ee9dc23806eb0c570db16d5596f10ae6aee8e5f0d1281cf0117719af8e604960fcd5eecc3376a748420d48a9776e1340347c2efcc1c825f7ee8866d3587488f5f0ef9ce2a84a9d51dc8b0bf3753a719fa58d04c8b9087d4022ad3526d671673409da61b144554e08c96d5";
		String exponent = "10001";
		
		KeyPair keyPair = getRsaKeyPair(1024, exponent);
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		
//		PublicKey publicKey = RSAUtils.getPublicKey(modulus, exponent);
//		PrivateKey privateKey = RSAUtils.getPrivateKey(modulus, exponent);
		
		try {
		
			System.out.println( "public_key modulus:\n" + RSAUtils.getPublicKeyModuleString(publicKey) + "\nexponent:\n" + RSAUtils.getPublicKeyExponentString(publicKey));
			System.out.println( "private_key modulus:\n" + RSAUtils.getPrivateKeyModuleString(privateKey) + "\nexponent:\n" + RSAUtils.getPrivateKeyExponentString(privateKey));
			
			String encryptText = RSAUtils.encrypt(plainText, publicKey);
			
			System.out.println("Ecrypt Text = " + encryptText + " : " + encryptText.length());
			
			String decryptText = RSAUtils.decrypt(encryptText, privateKey);
			
			System.out.println("Decrypt Text = " + decryptText );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		

	}
	
	private KeyPair getRsaKeyPair(int keySize, String publicExponent) throws Exception {
		BigInteger exponent = new BigInteger(publicExponent, 16);
			
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		RSAKeyGenParameterSpec keySpec = new RSAKeyGenParameterSpec(keySize, exponent);
		keyPairGenerator.initialize(keySpec);
				
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
			
		return keyPair;
	}
	
	

}
