package com.milkit.core.aop;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.milkit.core.annotations.encrypt.DoEncryption;
import com.milkit.core.annotations.encrypt.Encrypt;
import com.milkit.core.annotations.encrypt.DoEncryption.EncryptType;
import com.milkit.core.annotations.encrypt.Encrypt.Algorithm;
import com.milkit.core.common.AbstractBean;
import com.milkit.test.card.CreditCard;



@Repository
public class CreditCardEncryptDummyImpl implements CreditCardEncryptDummy {
	
	private static final Logger logger = Logger.getLogger(CreditCardEncryptDummyImpl.class);
	
	@Override
	@DoEncryption
	public void encryptFieldCreditCard(CreditCard creditCard) throws Exception {
		logger.debug("#########		Encrypt creditCard:"+creditCard);
	}

	@Override
	@DoEncryption(type=EncryptType.Both)
	public CreditCard encryptBothFieldCreditCard(CreditCard creditCard,
			CreditCard creditCard2, PlainCard plainCard) throws Exception {
		logger.debug("#########		Encrypt creditCard:"+creditCard);
		logger.debug("#########		Encrypt creditCard2:"+creditCard2);
		logger.debug("#########		Plain creditCard:"+plainCard);
		
		return new CreditCard(creditCard);
	}
	
	@Override
	@DoEncryption
	public void encryptFieldCreditCard(CreditCard creditCard, PlainCard plainCard) throws Exception {
		logger.debug("#########		Encrypt creditCard:"+creditCard);
		logger.debug("#########		Plain creditCard:"+plainCard);
	}

	@Override
	public void encryptFieldArgu(String encryptString) throws Exception {
		logger.debug(encryptString);
	}


	@Override
	public void encryptFieldArgu(String encryptString, String plainString)
			throws Exception {
		logger.debug("encryptString:"+encryptString+"	plainString:"+plainString);
	}


	@Override
	@DoEncryption
	public void encryptFieldArgu(String encryptString, String plainString, String encryptString2) throws Exception {
		logger.debug("encryptString:"+encryptString+"	plainString:"+plainString+"	encryptString2:"+encryptString2);
	}


	@Override
	public void encryptFieldArgu(String encryptString, String plainString, String plainString2, String encryptString2) throws Exception {
		logger.debug("encryptString:"+encryptString+"	plainString:"+plainString);
		logger.debug("plainString2:"+plainString2+"	encryptString2:"+encryptString2);
	}





	
	

}
