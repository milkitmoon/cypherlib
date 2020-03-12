package com.milkit.core.aop;

import com.milkit.core.annotations.encrypt.Encrypt;
import com.milkit.core.annotations.encrypt.Encrypt.Algorithm;
import com.milkit.core.annotations.encrypt.Encrypt.EncryptType;
import com.milkit.test.card.CreditCard;

public interface CreditCardEncryptDummy {

	public void encryptFieldCreditCard(CreditCard creditCard) throws Exception;
	
	public void encryptFieldCreditCard(CreditCard creditCard, PlainCard plainCard) throws Exception;
	
	public CreditCard encryptBothFieldCreditCard(CreditCard creditCard, CreditCard creditCard2, PlainCard plainCard) throws Exception;
	
	public void encryptFieldArgu(@Encrypt(algorithm=Algorithm.BlowfishECB, type=EncryptType.Symmetric) String plainString) throws Exception;
	
	public void encryptFieldArgu(@Encrypt String encryptString, String plainString) throws Exception;
	
	public void encryptFieldArgu(@Encrypt(algorithm=Algorithm.BlowfishECB, type=EncryptType.Symmetric) String encryptString, String plainString, @Encrypt String encryptString2) throws Exception;
	
	public void encryptFieldArgu(@Encrypt(algorithm=Algorithm.BlowfishECB, type=EncryptType.Symmetric) String encryptString, String plainString, String plainString2, @Encrypt(algorithm=Algorithm.BlowfishECB, type=EncryptType.Symmetric) String encryptString2) throws Exception;
	
}
