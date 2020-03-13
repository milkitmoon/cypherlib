package com.milkit.core.aop;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:com/milkit/core/spring/context-*.xml",
		"classpath*:com/milkit/test/spring/context-*.xml"
})
public class EncryptionAspectTest {
	
	private static final Logger logger = Logger.getLogger(EncryptionAspectTest.class);
	
	@Autowired
    private CreditCardEncryptDummy creditCardEncryptDummy;
	
	
	
	@Test
	public void AOP암호화테스트1() throws Exception {
		String fourDigits = "3456";
		String cardNumber = "1234567890123456";
		
		CreditCard creditCard = new CreditCard();
		creditCard.setFourDigits(fourDigits);
		creditCard.setCreditCardNumber(cardNumber);
		
		creditCardEncryptDummy.encryptFieldCreditCard(creditCard);
		
		assertNotSame( fourDigits, creditCard.getFourDigits() );
		assertNotSame( cardNumber, creditCard.getCreditCardNumber() );
		
		logger.debug("#########		Origin creditCard:"+creditCard);
	
	}
	
	@Test
	public void AOP암호화테스트2() throws Exception {
		String fourDigits = "3456";
		String cardNumber = "1234567890123456";
		
		CreditCard creditCard = new CreditCard();
		creditCard.setFourDigits(fourDigits);
		creditCard.setCreditCardNumber(cardNumber);
		
		
		PlainCard plainCard = new PlainCard();
		plainCard.setFourDigits("3456");
		plainCard.setCreditCardNumber("1234567890123456");
		
		creditCardEncryptDummy.encryptFieldCreditCard(creditCard, plainCard);
		
		assertTrue( fourDigits.equals(creditCard.getFourDigits()) );
		assertTrue( cardNumber.equals( creditCard.getCreditCardNumber()) );
		
		assertTrue( fourDigits.equals(plainCard.getFourDigits()) );
		assertTrue( cardNumber.equals(plainCard.getCreditCardNumber()) );
		
		logger.debug("creditCard:"+creditCard);
		logger.debug("plainCard:"+plainCard);
	}
	
	@Test
	public void AOP암호화테스트3() throws Exception {
		String fourDigits = "3456";
		String cardNumber = "1234567890123456";
		String password = "test";
		
		CreditCard creditCard = new CreditCard();
		creditCard.setFourDigits(fourDigits);
		creditCard.setCreditCardNumber(cardNumber);
		creditCard.setPassword(password);
		
		CreditCard creditCard2 = new CreditCard();
		creditCard2.setFourDigits("6543");
		creditCard2.setCreditCardNumber("6543210987654321");
		
		PlainCard plainCard = new PlainCard();
		plainCard.setFourDigits("3456");
		plainCard.setCreditCardNumber("1234567890123456");
		plainCard.setPassword(password);
		
		CreditCard returnCreditCard = creditCardEncryptDummy.encryptBothFieldCreditCard(creditCard, creditCard2, plainCard);
		
		assertTrue( fourDigits.equals(creditCard.getFourDigits()) );
		assertTrue( cardNumber.equals(creditCard.getCreditCardNumber()) );
		assertTrue( password.equals(creditCard.getPassword()) );
		
		assertTrue( fourDigits.equals(plainCard.getFourDigits()) );
		assertTrue( cardNumber.equals(plainCard.getCreditCardNumber()) );
		assertTrue( password.equals(plainCard.getPassword()) );

logger.debug("arg creditCard:"+creditCard);
logger.debug("arg creditCard2:"+creditCard2);
logger.debug("arg plainCard:"+plainCard);
logger.debug("return Encrypt CreditCard:"+returnCreditCard);
//logger.debug("creditCard FourDigits:"+creditCard.getFourDigits()+"	returnCreditCard FourDigits:"+returnCreditCard.getFourDigits());
		
		assertTrue( creditCard.getFourDigits().equals(returnCreditCard.getFourDigits()) );
		assertTrue( creditCard.getCreditCardNumber().equals(returnCreditCard.getCreditCardNumber()) );
		assertFalse( creditCard.getPassword().equals(returnCreditCard.getPassword()) );
	}

/*	
	
	@Test
	public void AOP암호화테스트1() throws Exception {
		String fourDigits = "3456";
		String cardNumber = "1234567890123456";
		
		CreditCard creditCard = new CreditCard();
		creditCard.setFourDigits(fourDigits);
		creditCard.setCreditCardNumber(cardNumber);
		
		creditCardEncryptDummy.encryptFieldCreditCard(creditCard);
		
		assertNotSame( fourDigits, creditCard.getFourDigits() );
		assertNotSame( cardNumber, creditCard.getCreditCardNumber() );
		
		logger.debug("CreditCardNumber:"+creditCard.getCreditCardNumber());
	
	}
	
	@Test
	public void AOP암호화테스트2() throws Exception {
		String fourDigits = "3456";
		String cardNumber = "1234567890123456";
		
		CreditCard creditCard = new CreditCard();
		creditCard.setFourDigits(fourDigits);
		creditCard.setCreditCardNumber(cardNumber);
		
		
		PlainCard plainCard = new PlainCard();
		plainCard.setFourDigits(fourDigits);
		plainCard.setCreditCardNumber(cardNumber);
		
		creditCardEncryptDummy.encryptFieldCreditCard(creditCard, plainCard);
		
		assertNotSame( fourDigits, creditCard.getFourDigits() );
		assertNotSame( cardNumber, creditCard.getCreditCardNumber() );
		
		assertSame( fourDigits, plainCard.getFourDigits() );
		assertSame( cardNumber, plainCard.getCreditCardNumber() );

	
	}
	
	@Test
	public void AOP인자값암호화테스트1() throws Exception {
		String cardNumber = "1234567890123456";
		
		creditCardEncryptDummy.encryptFieldArgu(cardNumber);
		
		logger.debug("CreditCardNumber:"+cardNumber);
	}
	
	@Test
	public void AOP인자값암호화테스트2() throws Exception {
		String cardNumber = "1234567890123456";
		String fourDigits = "3456";
		
		creditCardEncryptDummy.encryptFieldArgu(cardNumber, fourDigits);
	}
	
	
	@Test
	public void AOP인자값암호화테스트3() throws Exception {
		String cardNumber = "1234567890123456";
		String fourDigits = "3456";
		String cardNumber2 = "9876543219876543";
		
		creditCardEncryptDummy.encryptFieldArgu(cardNumber, fourDigits, cardNumber2);
	}
	
	@Test
	public void AOP인자값암호화테스트4() throws Exception {
		String cardNumber = "1234567890123456";
		String fourDigits = "3456";
		String cardNumber2 = "9876543219876543";
		String fourDigits2 = "6543";
		
//		logger.debug("cardNumber2:"+cardNumber2);
		
		creditCardEncryptDummy.encryptFieldArgu(cardNumber, fourDigits, cardNumber2, fourDigits2);
	}
	
	
	@Test
	public void AOP인자값암호화테스트5() throws Exception {
		String cardNumber = "1234567890123456";
		String fourDigits = "3456";
		String cardNumber2 = "9876543219876543";
		String fourDigits2 = "6543";
		
//		logger.debug("cardNumber2:"+cardNumber2);
		
		creditCardEncryptDummy.encryptFieldArgu(cardNumber, fourDigits, cardNumber2, fourDigits2);

		cardNumber = "1234567890123456";
		fourDigits = "3456";
		cardNumber2 = "9876543219876543";
		fourDigits2 = "6543";
		
		creditCardEncryptDummy.encryptFieldArgu(cardNumber, fourDigits, cardNumber2, fourDigits2);
	}
*/
	
	@Test
	public void AOP인자값암호화테스트3() throws Exception {
		String cardNumber = "1234567890123456";
		String fourDigits = "3456";
		String cardNumber2 = "9876543219876543";
		
		creditCardEncryptDummy.encryptFieldArgu(cardNumber, fourDigits, cardNumber2);
		
logger.debug("cardNumber:"+cardNumber);
logger.debug("1234567890123456");		//String Pool 이 변경됨으로써 "1234567890123456" 값도 암호화된 값으로 바뀌어 버림, 이슈해결 필요

		assertTrue( cardNumber.equals("1234567890123456") );
	}
	
	@Test
	public void AOP인자값암호화테스트4() throws Exception {
		String cardNumber = "1234567890123456";
		String fourDigits = "3456";
		String cardNumber2 = new String("9876543219876543");
		String fourDigits2 = "6543";
		
		logger.debug("cardNumber:"+cardNumber);
		logger.debug("fourDigits:"+fourDigits);
		logger.debug("cardNumber2:"+cardNumber2);
		logger.debug("fourDigits2:"+fourDigits2);
		
		creditCardEncryptDummy.encryptFieldArgu(cardNumber, fourDigits, cardNumber2, fourDigits2);
	}
	
	


}
