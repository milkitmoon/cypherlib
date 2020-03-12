package com.milkit.core.annotations.encrypt;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import com.milkit.core.enumeration.ObjValueEnum;
import com.milkit.core.security.SecurityGlobal;

/**
* <pre>
* 1. 패키지명 : com.milkit.core.annotations.encrypt
* 2. 타입명 : DoEncryption.java
* 3. 작성일 : 2015. 5. 28. 오후 2:40:07
* 4. 작성자 : milkit
* 5. 설명    : Method 입출력 구간암호화를 위한 Annotation정의
* </pre>
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoEncryption {
	
	String securityKey() default SecurityGlobal.SecurityKey;	//기본 암호키 미지정 시 기본상수 값 설정
	
	EncryptType type() default EncryptType.Encrypt;		//기본모드 : 암호화
	
	
	/**
	* <pre>
	* 1. 패키지명 : com.milkit.core.annotations.encrypt
	* 2. 타입명 : DoEncryption.java
	* 3. 작성일 : 2015. 5. 28. 오후 2:44:17
	* 4. 작성자 : milkit
	* 5. 설명    : Method 입출력 구간암호화 유형구분(Encrypt:암호화, Decrypt:복호화, Both:암/복호화)
	* </pre>
	*/
	public static enum EncryptType implements ObjValueEnum {
		Encrypt("encrypt"), Decrypt("decrypt"), Both("both");


		private final String value;
		
		EncryptType(String value) {
			this.value = value;
		}

		public String getValue() {		
			return value;
		}
		

		
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

}
