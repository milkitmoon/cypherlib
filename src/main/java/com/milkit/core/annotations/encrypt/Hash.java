package com.milkit.core.annotations.encrypt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.milkit.core.enumeration.IntValueEnum;
import com.milkit.core.enumeration.ObjValueEnum;


/**
* <pre>
* 1. 패키지명 : com.milkit.core.annotations.encrypt
* 2. 타입명 : Hash.java
* 3. 작성일 : 2015. 5. 28. 오후 3:11:20
* 4. 작성자 : milkit
* 5. 설명    : Method 입출력 구간암호화의 단방향 Hash정보 정의
* </pre>
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Hash {
	
	Algorithm algorithm() default Algorithm.SHA256;
	
	/**
	* <pre>
	* 1. 패키지명 : com.milkit.core.annotations.encrypt
	* 2. 타입명 : Hash.java
	* 3. 작성일 : 2015. 5. 28. 오후 3:11:52
	* 4. 작성자 : milkit
	* 5. 설명    : 단방향Hash 알고리즘 (MD5, SHA1, SHA256, SHA512)
	* </pre>
	*/
	public static enum Algorithm implements ObjValueEnum {
		MD5("MD5"), SHA1("SHA1"), SHA256("SHA256"), SHA512("SHA512");

		private final String value;
		
		Algorithm(String value) {
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
