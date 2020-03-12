package com.milkit.core.security.method;

import java.security.GeneralSecurityException;

public abstract class SecretMethodImpl implements SecretMethod {
	protected int outputsize;
	private boolean enabeBase64 = true;
	
	public byte[] decryptWithoutPadding(byte[] data) throws GeneralSecurityException {
		byte[] bret = decrypt(data);		
		// 덧붙힌 패딩정보가 없으면 원본 리턴
		if (bret.length == getOutputsize()) 
			return bret;
		
		byte[] sret = new byte[getOutputsize()];
		System.arraycopy(bret, 0, sret, 0, sret.length);
		
		return sret;
	}
	
	public int getOutputsize() {
		return outputsize;
	}

	public boolean isEnabeBase64() {
		return enabeBase64;
	}

	public void setEnabeBase64(boolean enabeBase64) {
		this.enabeBase64 = enabeBase64;
	}
}