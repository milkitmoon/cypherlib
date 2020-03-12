package com.milkit.core.security.method;

import java.security.GeneralSecurityException;

public interface SecretMethod {
	void setCipherMode(String ciphermode) throws Exception;
	String getCipherMode() throws Exception;
	void setKey(Object key) throws Exception;
	void setIv(Object iv);
	byte[] encrypt(byte[] data) throws GeneralSecurityException;
	byte[] decrypt(byte[] data) throws GeneralSecurityException;
	byte[] decryptWithoutPadding(byte[] data) throws GeneralSecurityException;
	int getOutputsize();
	public boolean isEnabeBase64();
	public void setEnabeBase64(boolean enabeBase64);
}
