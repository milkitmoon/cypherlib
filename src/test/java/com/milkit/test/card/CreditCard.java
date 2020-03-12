package com.milkit.test.card;

import java.io.Serializable;

import com.milkit.core.annotations.encrypt.Encrypt;
import com.milkit.core.annotations.encrypt.Hash;
import com.milkit.core.annotations.encrypt.Encrypt.EncryptType;
import com.milkit.core.common.AbstractBean;


public class CreditCard extends AbstractBean implements Serializable {

	private long id;

	@Encrypt(algorithm=com.milkit.core.annotations.encrypt.Encrypt.Algorithm.AES128CBC, secureKey="1234567890123456", secureIV="9878543210123456")
	private String creditCardNumber;

	@Encrypt
	private String fourDigits;

	@Hash(algorithm=com.milkit.core.annotations.encrypt.Hash.Algorithm.SHA256)
	private String password;
	
	private String cardType;
	private String nameOnCard;
	
	private String expirationDate;
	private int version;
	
	public CreditCard() {}
	public CreditCard(CreditCard creditCard) {
		this.id = creditCard.getId();
		this.creditCardNumber = creditCard.getCreditCardNumber();
		this.fourDigits = creditCard.getFourDigits();
		this.password = creditCard.getPassword();
		this.cardType = creditCard.getCardType();
		this.nameOnCard = creditCard.getNameOnCard();
		this.expirationDate = creditCard.getExpirationDate();
		this.version = creditCard.getVersion();
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}


	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	public String getCardType() {
		return cardType;
	}
	
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}
	
	public void setFourDigits(String fourDigits) {
		this.fourDigits = fourDigits;
	}

	public String getFourDigits() {
		return fourDigits;
	}
	
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public int getVersion() {
		return version;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}


	
/*
	@Override
	public boolean equals(Object o){
		boolean result = false;
		if (o instanceof CreditCard){
			CreditCard cc = (CreditCard)o;
			if(getCardType().equalsIgnoreCase(cc.getCardType()) &&
					getNameOnCard().equalsIgnoreCase(cc.getNameOnCard()) &&
					getFourDigits().equalsIgnoreCase(cc.getFourDigits())) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public int hashCode(){
		return getCardType().hashCode() +
			getNameOnCard().hashCode() + 
			getFourDigits().hashCode();
	}
*/
}
