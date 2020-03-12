package com.milkit.core.aop;

import java.io.Serializable;

import com.milkit.core.common.AbstractBean;

public class PlainCard extends AbstractBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private String creditCardNumber;

	private String fourDigits;
	
	private String password;

	
	
	private String cardType;
	private String nameOnCard;
	
	private String expirationDate;
	private int version;
	
	
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
}