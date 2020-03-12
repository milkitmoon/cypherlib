package com.milkit.core.annotations.encrypt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.milkit.core.enumeration.IntValueEnum;
import com.milkit.core.enumeration.ObjValueEnum;
import com.milkit.core.security.SecurityGlobal;
import com.milkit.core.security.method.AESSecretMethod;
import com.milkit.core.security.method.BlowfishSecretMethod;


/**
* <pre>
* 1. 패키지명 : com.milkit.core.annotations.encrypt
* 2. 타입명 : Encrypt.java
* 3. 작성일 : 2015. 5. 28. 오후 2:45:45
* 4. 작성자 : milkit
* 5. 설명    : Method 입출력 구간암호화의 암호화정보 정의
* </pre>
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Encrypt {
//	public static String Blowfish = "Blowfish";
	
	Algorithm algorithm() default Algorithm.BlowfishECB;
	EncryptType type() default EncryptType.Symmetric;
	
	String secureKey() default SecurityGlobal.SecurityKey;
	String secureIV() default SecurityGlobal.SecurityKeyIV;
	
	
	/**
	* <pre>
	* 1. 패키지명 : com.milkit.core.annotations.encrypt
	* 2. 타입명 : Encrypt.java
	* 3. 작성일 : 2015. 5. 28. 오후 3:09:58
	* 4. 작성자 : milkit
	* 5. 설명    : 암호화 알고리즘(Blowfish)
	* </pre>
	*/
	public static enum Algorithm implements ObjValueEnum {
		BlowfishCBC(BlowfishSecretMethod.BLOWFISHCBC),
		BlowfishECB(BlowfishSecretMethod.BLOWFISHECB),
		AES128CBC(AESSecretMethod.AESCBC),
		AES128ECB(AESSecretMethod.AESECB);

		private final String value;
		
		Algorithm(String value) {
			this.value = value;
		}

		public String getValue() {		
			return value;
		}
		

		
		/**
		 * @Method Name  : getName
		 * @작성일   : 2015. 11. 19. 
		 * @작성자   : milkit
		 * @변경이력  :
		 * @Method 설명 :
		 * <pre>
		 * Parameter :
		 * Return    :
		 * </pre>
		 * @param argValue
		 * @return
		 */
		public static String getName(Object argValue) {
			String name = null;
			ObjValueEnum[] enums = values();
			
			for(int i=0; i<enums.length; i++) {
				Object value = enums[i].getValue();
				if(value.equals(argValue)) {
					name = enums[i].toString();
					break;
				}
			}
			
			return name;
		}
		
		/**
		 * @Method Name  : getValue
		 * @작성일   : 2015. 11. 19. 
		 * @작성자   : milkit
		 * @변경이력  : 
		 * @Method 설명 :
		 * <pre>
		 * Parameter :
		 * Return    :
		 * </pre>
		 * @param enumNameArg
		 * @return
		 */
		public static Object getValue(String enumNameArg) {
			Object value = null;
			ObjValueEnum[] enums = values();
			
			for(int i=0; i<enums.length; i++) {
				String name = enums[i].toString();
				if(name.equals(enumNameArg)) {
					value = enums[i].getValue();
					break;
				}
			}
			
			return value;
		}
	}
	
	/**
	* <pre>
	* 1. 패키지명 : com.milkit.core.annotations.encrypt
	* 2. 타입명 : Encrypt.java
	* 3. 작성일 : 2015. 5. 28. 오후 3:10:21
	* 4. 작성자 : milkit
	* 5. 설명    : 암호화 유형(Hash:단방향Hash, Symmetric:대칭키, Asymmetric:비대칭키)
	* </pre>
	*/
	public static enum EncryptType implements IntValueEnum {
		Hash(0), Symmetric(1), Asymmetric(2);
		
		private final int seq;
		
		EncryptType(int seq) {
			this.seq = seq;
		}

		@Override
		public int getValue() {
			return seq;
		}
		
		public static EncryptType valueOf(int seq) {
			for(EncryptType t:EncryptType.values()) {
				if( t.getValue() == seq ) {
					return t;
				}
			}
			
			return null;
		}
		
		public static String getName(Object argValue) {
			String name = null;
			IntValueEnum[] enums = values();
			
			for(int i=0; i<enums.length; i++) {
				Object value = enums[i].getValue();
				if(value.equals(argValue)) {
					name = enums[i].toString();
					break;
				}
			}
			
			return name;
		}

	}
}
