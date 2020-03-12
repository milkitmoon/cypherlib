package com.milkit.test.card;

import java.util.List;

import com.milkit.test.card.CreditCard;

public interface CreditCardTestService {
	
	public void insertCreditCard(CreditCard creditCard) throws Exception;
	
	public CreditCard selectCreditCard(CreditCard creditCard) throws Exception;

	public List<CreditCard> selectCreditCardList(CreditCard selectCreditCard) throws Exception;
	
	public List<CreditCard> selectCreditCardList() throws Exception;
	
	public void deleteCreditCard(CreditCard creditCard) throws Exception;
	
	public void updateCreditCard(CreditCard creditCard) throws Exception;

}
