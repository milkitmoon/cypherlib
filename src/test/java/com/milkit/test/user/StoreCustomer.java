package com.milkit.test.user;

import java.io.Serializable;
import java.util.Date;

import com.milkit.core.annotations.encrypt.Encrypt;
import com.milkit.core.common.AbstractBean;
import com.milkit.core.common.lang.Containable;
import com.milkit.core.security.SecurityGlobal;
import com.milkit.core.util.DateUtil;


public class StoreCustomer extends AbstractBean implements Serializable, Containable<String>  {

	private long seq = -1;

	private String cmpCD;
	private String cmpNM;
	private String brandCD;
	private String brandNM;
	private String agencyId;
	private String membNam;
	private String storeCD;
	private String storeNM;
	private String userNO;
	private String cardNO;
	private String userNM;
	
	@Encrypt(algorithm=Encrypt.Algorithm.AES128CBC, secureKey="O2POSSECURITYKEY", secureIV="O2POSSECURITYIV1")
	private String telNO;
	
	@Encrypt(algorithm=Encrypt.Algorithm.AES128CBC, secureKey="O2POSSECURITYKEY", secureIV="O2POSSECURITYIV1")
	private String mobileNO;
	private String email;
	private String zipCD;
	private String sidoCD;
	private String sidoNM;
	private String regionCD;
	private String regionNM;
	
	private String addr1;
	private String addr2;
	private String birthday;
	private String sex;
	private String age;
	private String localYN;
	private String jobCD;
	private String smsRcvYN;
	private String emailRcvYN;
	private String creditYN;
	private String custDiv;
	private String useYN;
	
	private Date instTime;
	private Date updTime;
	private String instUser;
	private String updUser;
	
	private String mannum;
	private String dealer;
	
	private String advanceYN;
	private long avlPoint;
	private long savePoint;
	private long usePoint;
	private long creditPay;
	
	public StoreCustomer() {
		this.localYN = "Y";
		this.useYN = "Y";
	}
	
	public StoreCustomer(long seq) {
		this.seq = seq;
	}
	
	public StoreCustomer(String cmpCD, String brandCD, String storeCD, String userNO, String cardNO, String userNM, String sex, String mobileNO, String sidoCD, String regionCD) {
		this();
		this.cmpCD    = cmpCD;
		this.brandCD  = brandCD;
		this.storeCD  = storeCD;
		this.userNO   = userNO;
		this.cardNO   = cardNO;
		this.userNM   = userNM;
		this.sex      = sex;
		this.mobileNO = mobileNO;
		this.sidoCD   = sidoCD;
		this.regionCD = regionCD;
	}
	
	public StoreCustomer(String cmpCD, String brandCD, String agencyId, String mannum, String dealer, String storeCD, String userNO, String cardNO, String userNM, String sex, String mobileNO, String sidoCD, String regionCD, String useYN) {
		this();
		this.cmpCD    = cmpCD;
		this.brandCD  = brandCD;
		this.agencyId = agencyId;
		this.mannum = mannum;
		this.dealer = dealer;
		this.storeCD  = storeCD;
		this.userNO   = userNO;
		this.cardNO   = cardNO;
		this.userNM   = userNM;
		this.sex      = sex;
		this.mobileNO = mobileNO;
		this.sidoCD   = sidoCD;
		this.regionCD = regionCD;
		this.useYN    = useYN;
	}
	

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}


	public String getCmpCD() {
		return cmpCD;
	}

	public void setCmpCD(String cmpCD) {
		this.cmpCD = cmpCD;
	}

	public String getCmpNM() {
		return cmpNM;
	}

	public void setCmpNM(String cmpNM) {
		this.cmpNM = cmpNM;
	}
	
	public String getBrandCD() {
		return brandCD;
	}

	public void setBrandCD(String brandCD) {
		this.brandCD = brandCD;
	}

	public String getBrandNM() {
		return brandNM;
	}

	public void setBrandNM(String brandNM) {
		this.brandNM = brandNM;
	}
	
	public String getStoreCD() {
		return storeCD;
	}

	public void setStoreCD(String storeCD) {
		this.storeCD = storeCD;
	}

	public String getStoreNM() {
		return storeNM;
	}

	public void setStoreNM(String storeNM) {
		this.storeNM = storeNM;
	}
	
	public String getUserNO() {
		return userNO;
	}

	public void setUserNO(String userNO) {
		this.userNO = userNO;
	}

	public String getCardNO() {
		return cardNO;
	}

	public void setCardNO(String cardNO) {
		this.cardNO = cardNO;
	}
	public String getUserNM() {
		return userNM;
	}

	public void setUserNM(String userNM) {
		this.userNM = userNM;
	}

	public String getTelNO() {
		return telNO;
	}

	public void setTelNO(String telNO) {
		this.telNO = telNO;
	}

	public String getMobileNO() {
		return mobileNO;
	}

	public void setMobileNO(String mobileNO) {
		this.mobileNO = mobileNO;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipCD() {
		return zipCD;
	}

	public void setZipCD(String zipCD) {
		this.zipCD = zipCD;
	}

	public String getSidoCD() {
		return sidoCD;
	}

	public void setSidoCD(String sidoCD) {
		this.sidoCD = sidoCD;
	}
	public String getSidoNM() {
		return sidoNM;
	}
	public void setSidoNM(String sidoNM) {
		this.sidoNM = sidoNM;
	}
	
	public String getRegionCD() {
		return regionCD;
	}

	public void setRegionCD(String regionCD) {
		this.regionCD = regionCD;
	}
	public String getRegionNM() {
		return regionNM;
	}

	public void setRegionNM(String regionNM) {
		this.regionNM = regionNM;
	}
	
	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	
/*
	public String getBirthday() {
		String formatDateStr = "";
		if(birthday != null) {
			try {
				formatDateStr = DateUtil.parseDateString(birthday, "yyyyMMdd", StatGlobals.STAT_MONTHLY_FORMAT);
			} catch (Exception e) {
				formatDateStr = "";
			} 
		} 
		return formatDateStr;
	}
*/
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}
	public String getSexMark() {
		String sexMark = "-";
		if(sex != null && sex.equals("M")) {
			sexMark = "남성";
		} else if(sex != null && sex.equals("W")) {
			sexMark = "여성";
		}
		
		return sexMark;
	}	

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getLocalYN() {
		return localYN;
	}
	public String getLocalYNMark() {
		String localYNMark = "-";
		if(localYN != null && localYN.equals("Y")) {
			localYNMark = "내국인";
		} else if(localYN != null && localYN.equals("N")) {
			localYNMark = "외국인";
		}
		
		return localYNMark;
	}
	

	public void setLocalYN(String localYN) {
		this.localYN = localYN;
	}

	public String getJobCD() {
		return jobCD;
	}

	public void setJobCD(String jobCD) {
		this.jobCD = jobCD;
	}

	public String getSmsRcvYN() {
		return smsRcvYN;
	}

	public void setSmsRcvYN(String smsRcvYN) {
		this.smsRcvYN = smsRcvYN;
	}

	public String getEmailRcvYN() {
		return emailRcvYN;
	}

	public void setEmailRcvYN(String emailRcvYN) {
		this.emailRcvYN = emailRcvYN;
	}
	
	public String getCreditYN() {
		return creditYN;
	}

	public void setCreditYN(String creditYN) {
		this.creditYN = creditYN;
	}

	public String getCustDiv() {
		return custDiv;
	}

	public void setCustDiv(String custDiv) {
		this.custDiv = custDiv;
	}
	
	public String getUseYN() {
		return useYN;
	}

	public void setUseYN(String useYN) {
		this.useYN = useYN;
	}

	public Date getInstTime() {
		return instTime;
	}

	public void setInstTime(Date instTime) {
		this.instTime = instTime;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getInstUser() {
		return instUser;
	}

	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}

	public String getUpdUser() {
		return updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getMembNam() {
		return membNam;
	}

	public void setMembNam(String membNam) {
		this.membNam = membNam;
	}

	public String getMannum() {
		return mannum;
	}

	public void setMannum(String mannum) {
		this.mannum = mannum;
	}

	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	public String getAdvanceYN() {
		return advanceYN;
	}

	public void setAdvanceYN(String advanceYN) {
		this.advanceYN = advanceYN;
	}

	public long getAvlPoint() {
		return avlPoint;
	}

	public void setAvlPoint(long avlPoint) {
		this.avlPoint = avlPoint;
	}

	public long getSavePoint() {
		return savePoint;
	}

	public void setSavePoint(long savePoint) {
		this.savePoint = savePoint;
	}

	public long getUsePoint() {
		return usePoint;
	}

	public void setUsePoint(long usePoint) {
		this.usePoint = usePoint;
	}

	public long getCreditPay() {
		return creditPay;
	}

	public void setCreditPay(long creditPay) {
		this.creditPay = creditPay;
	}
	
	public boolean contains(String phoneNumber) throws Exception {
		if(phoneNumber != null) {
			if(telNO != null) {
				return telNO.trim().replaceAll("-", "").contains( phoneNumber );
			}
			if(mobileNO != null) {
				return mobileNO.trim().replaceAll("-", "").contains( phoneNumber );
			}
		}
		
		return false;
	}

}
