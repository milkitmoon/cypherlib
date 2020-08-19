package com.milkit.core.security.method;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milkit.core.security.factory.MethodFactory;
import com.milkit.core.security.hash.HashAgent;
import com.milkit.core.security.method.AESSecretMethod;
import com.milkit.core.security.method.BlowfishSecretMethod;
import com.milkit.core.security.method.RSASecretMethod;
import com.milkit.core.security.method.SecretMethod;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:com/milkit/core/spring/context-*.xml",
		"classpath*:com/milkit/test/spring/context-*.xml"
})
public class SecretMethodTest {
	
	private static final Logger logger = Logger.getLogger(SecretMethodTest.class);
	

	
	@Test
	public void Blowfish테스트() throws Exception {
		String algname = BlowfishSecretMethod.BLOWFISHECB;
//		String algname = BlowfishSecretMethod.BLOWFISHCBC;
		String keyData = "asdqwezxc";
		String painStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEF";
		String keyIV = "01234567";
		
		logger.debug("plain str:"+painStr);
		
		SecretMethod secretMethod = MethodFactory.getSecretMethod(algname);
		secretMethod.setKey(keyData.getBytes());
		secretMethod.setIv(keyIV.getBytes());
//		secretMethod.setEnabeBase64(false);
		
		byte[] encBytes = secretMethod.encrypt(painStr.getBytes());
		
		logger.debug("enc str:"+new String(encBytes));
		
		byte[] decBytes = secretMethod.decrypt( encBytes );
		
		String decString = new String(decBytes);
		logger.debug("dec str:"+decString);
		
		assertTrue(painStr.equals(decString));
	}
	
	@Test
	public void AES테스트() throws Exception {
		String algname = AESSecretMethod.AESCBC;
		String keyData = "O2POSSECURITYKEY";
		String ivData = "O2POSSECURITYIV1";
		String painStr = "010****1234";
		Object salt = null;
		
		logger.debug("plain str:"+painStr);
		logger.debug("keyData:"+keyData+"	ivData:"+ivData);
		
		SecretMethod secretMethod = MethodFactory.getSecretMethod(algname);
		secretMethod.setKey(keyData.getBytes());
		secretMethod.setIv(ivData.getBytes());
		byte[] encBytes = secretMethod.encrypt(painStr.getBytes());
		
		logger.debug("enc str:"+new String(encBytes));
		
		byte[] decBytes = secretMethod.decrypt(encBytes);
		
		String decString = new String(decBytes);
		logger.debug("dec str:"+decString);
		
		assertTrue(painStr.equals(decString));
	}
	
	@Test
	public void AES복호화테스트() throws Exception {
		String algname = AESSecretMethod.AESCBC;
		String keyData = "O2POSSECURITYKEY";
		String ivData = "O2POSSECURITYIV1";
		String encStr = "q3B8tb84mcx8FhHh/XAP2A==";
		Object salt = null;
		
		logger.debug("encStr str:"+encStr);
		logger.debug("keyData:"+keyData);
		
		SecretMethod secretMethod = MethodFactory.getSecretMethod(algname);
		secretMethod.setKey(keyData.getBytes());
		secretMethod.setIv(ivData.getBytes());
		byte[] decBytes = secretMethod.decrypt(encStr.getBytes());
		
		String decString = new String(decBytes);
		logger.debug("dec str:"+decString);
		
//		assertTrue(painStr.equals(decString));
	}
	
	@Test
	public void AES테스트256() throws Exception {
		String algname = AESSecretMethod.AESCBC;
		String keyData = "12345678901234561234567890123456";
		String painStr = "2000";
		Object salt = null;
		
		logger.debug("plain str:"+painStr);
		logger.debug("keyData:"+keyData+"	length:"+keyData.getBytes().length);
		
		SecretMethod secretMethod = MethodFactory.getSecretMethod(algname);
		secretMethod.setKey(keyData.getBytes());
		secretMethod.setIv("0987654321098765".getBytes());
		byte[] encBytes = secretMethod.encrypt(painStr.getBytes());
		
		logger.debug("enc str:"+new String(encBytes));
		
		byte[] decBytes = secretMethod.decrypt(encBytes);
		
		String decString = new String(decBytes);
		logger.debug("dec str:"+decString);
		
		assertTrue(painStr.equals(decString));
	}
	
	

	
}
