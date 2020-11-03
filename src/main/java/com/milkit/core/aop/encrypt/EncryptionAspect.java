package com.milkit.core.aop.encrypt;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.milkit.core.annotations.encrypt.DoEncryption;
import com.milkit.core.annotations.encrypt.Encrypt;
import com.milkit.core.annotations.encrypt.Hash;
import com.milkit.core.annotations.encrypt.DoEncryption.EncryptType;
import com.milkit.core.annotations.encrypt.Encrypt.EncryptAlgorithm;
import com.milkit.core.reflect.ReflectUtil;
import com.milkit.core.security.BlowfishUtil;
import com.milkit.core.security.SecurityUtil;
import com.milkit.core.security.hash.HashAgent;
import com.milkit.core.security.method.DigestMethod;
import com.milkit.core.util.ArrayUtil;
import com.milkit.core.util.ObjectCloner;


/**
* <pre>
* 1. 패키지명 : com.milkit.core.aop.encrypt
* 2. 타입명 : EncryptionAspect.java
* 3. 작성일 : 2015. 5. 28. 오후 3:11:21
* 4. 작성자 : milkit
* 5. 설명    : AOP를 활용하여 해쉬 및 암복호화 수행
* </pre>
*/
@Aspect 
@Component 
public class EncryptionAspect {
	
    private static final Logger logger = Logger.getLogger(EncryptionAspect.class);
    
//    @Autowired
//    private CacheManager cacheManager;
    

    @Pointcut("@annotation(com.milkit.core.annotations.encrypt.DoEncryption)")
    public void doEncryption() {}

    @Around("@annotation( doEncryption )")
    public Object encryptionAround(ProceedingJoinPoint joinPoint, DoEncryption doEncryption) throws Throwable {
    	Object methodOutput = null;
    	Object[] copyOriginArgs = null;
    	
		EncryptType encryptType = doEncryption.type();
				
		copyOriginArgs = doEncryption(joinPoint, encryptType);
		methodOutput = doDecryption(joinPoint, encryptType);
			
		restoreEncryptArgumentField(joinPoint, copyOriginArgs);
				
		return methodOutput;
    }
    

	private Object[] doEncryption(ProceedingJoinPoint joinPoint, EncryptType encryptType) throws Throwable {
		Object[] copyArgs = encryptionObject(joinPoint, encryptType);

		return copyArgs;
	}

	private Object doDecryption(ProceedingJoinPoint joinPoint, EncryptType encryptType) throws Throwable {
		Object output = null;
		if(encryptType == EncryptType.Encrypt) {
	    	output = joinPoint.proceed();
		} else if(encryptType == EncryptType.Decrypt || encryptType == EncryptType.Both) {
			output = decryption( joinPoint.proceed() );
		}

		return output;
	}

	private void restoreEncryptArgumentField(ProceedingJoinPoint joinPoint, Object[] copyOriginArgs) throws Exception {
		restoreObject(joinPoint.getArgs(), copyOriginArgs);
	}
	
    private Object decryption(Object output) throws Throwable {
    	try {
        	if(output != null){
        		if(output instanceof Collection) {
        			for (@SuppressWarnings("unchecked")
						Iterator<Object> it = ((Collection<Object>) output).iterator(); it.hasNext();) {
        					decryptObject(it.next());
        			}
        		} else if(output instanceof Object[]) {
        			Object[] resultArray = (Object[])output;
        			for (int i=0; i<resultArray.length; i++) {
        				decryptObject(resultArray[i]);
        			}
        		} else {
        			decryptObject(output);
        		}
        	}
        	
            return output;
        } catch (IllegalArgumentException e) {
            throw e;
        } 
    }


	private Object[] encryptionObject(ProceedingJoinPoint joinPoint, EncryptType encryptType) throws Throwable {
		Object[] args = joinPoint.getArgs();
		Object[] copyArgs = ArrayUtil.getDeepCopyArray(Object.class, args);
		
		if(args != null && args.length > 0) {
    		encryptObject(args, encryptType);
    		hashObject(args);		//also do hashing !!
    	}

		return copyArgs;
	}
    
	/*
	 * @Deprecated by milkit, Method로 넘어오는 String argument의 경우 변경 시 Immutable 특성으로 인해 다른 String 객체도 영향을 받음
	 */
	@Deprecated
	private void encryptionMethodArguments(JoinPoint joinPoint, EncryptType encryptType, Object[] args) throws Exception {
		if (joinPoint.getSignature() instanceof MethodSignature) {
			MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
			Method method = methodSignature.getMethod();
			
			Class<?>[] paramClasses = method.getParameterTypes();
			Annotation[][] annotations = method.getParameterAnnotations();
				
			for(int j=0; j < annotations.length; j++) {
				for(int k=0; k < annotations[j].length; k++) {
					if(annotations[j][k].annotationType().equals(Encrypt.class) && paramClasses[j].getName().equalsIgnoreCase("java.lang.String")) {
						
						Encrypt encryptField = annotations[j][k].getClass().getAnnotation(Encrypt.class);
						
						Method toString = paramClasses[j].getMethod("toString");
						String clearText = (String)toString.invoke((String)args[j], null);
						String cipherText = encryptValue(encryptField.algorithm(), encryptField.secureKey(), encryptField.secureIV(), clearText);
						
						ReflectUtil.setStringValue(args[j], cipherText);
					}
				}
	
			}
		}
	}

	private void encryptObject(Object[] args, EncryptType encryptType) throws Exception {
		for(int i=0; i<args.length; i++) {
			Object argObj = args[i];

			if(argObj instanceof Collection) {
				Collection collection = (Collection)argObj;
				for(Object innerObj : collection) {
					encryptObjectElement(innerObj, encryptType);
				}
				
			} else {
				encryptObjectElement(argObj, encryptType);
			}
		}
	}
	
	private void encryptObjectElement(Object argObj, EncryptType encryptType) throws Exception {
		if(argObj != null) {
			Class<? extends Object> argClass = argObj.getClass();
			
			for (Field f : argClass.getDeclaredFields()) {
				if ( f.isAnnotationPresent(Encrypt.class) && f.getType().getName().equalsIgnoreCase("java.lang.String")
					&& (encryptType == EncryptType.Encrypt || encryptType == EncryptType.Both) ) {
					
					Encrypt encryptField = f.getAnnotation(Encrypt.class);
					
		        	String clearText = (String) ReflectUtil.getFieldValue(argObj, f.getName());
		        	if(clearText != null && !clearText.equals("")) {
						String cipherText = encryptValue(encryptField.algorithm(), encryptField.secureKey(), encryptField.secureIV(), clearText);
			        	ReflectUtil.setFieldValue(argObj, f.getName(), cipherText);
		        	}
				}
			}
		}
	}

	
	private String encryptValue(EncryptAlgorithm algorithm, String secureKey, String secureIV, String clearText) throws Exception {
		String cipherText = null;
		if(algorithm == null || algorithm.equals("")) {
			throw new IllegalArgumentException("algorithm 값이 존재하지 않습니다.");
		}
		
		if(secureKey == null || secureKey.equals("")) {
			throw new IllegalArgumentException("암호화 키값이 존재하지 않습니다.");
		}
		
		if(secureIV == null || secureIV.equals("")) {
			throw new IllegalArgumentException("암호화 IV값이 존재하지 않습니다.");
		}
		
		if(algorithm == EncryptAlgorithm.BlowfishECB) {
			cipherText = SecurityUtil.encrypt(algorithm.getValue(), secureKey, null, clearText);
		} else if(algorithm == EncryptAlgorithm.BlowfishCBC) {
			cipherText = SecurityUtil.encrypt(algorithm.getValue(), secureKey, secureIV, clearText);
		} else if(algorithm == EncryptAlgorithm.AES128CBC) {
			cipherText = SecurityUtil.encrypt(algorithm.getValue(), secureKey, secureIV, clearText);
		} else if(algorithm == EncryptAlgorithm.AES128ECB) {
			cipherText = SecurityUtil.encrypt(algorithm.getValue(), secureKey, secureIV, clearText);
		}
		
		return cipherText;
	}

	
	private String decryptValue(EncryptAlgorithm algorithm, String secureKey, String secureIV, String cipherText) throws Exception {
		String clearText = null;
		if(algorithm == null || algorithm.equals("")) {
			throw new IllegalArgumentException("algorithm 값이 존재하지 않습니다.");
		}
		
		if(secureKey == null || secureKey.equals("")) {
			throw new IllegalArgumentException("암호화 키값이 존재하지 않습니다.");
		}
		
		if(secureIV == null || secureIV.equals("")) {
			throw new IllegalArgumentException("암호화 IV값이 존재하지 않습니다.");
		}
		
		if(algorithm == EncryptAlgorithm.BlowfishECB) {
			clearText = SecurityUtil.decrypt(algorithm.getValue(), secureKey, null, cipherText);
		} else if(algorithm == EncryptAlgorithm.BlowfishCBC) {
			clearText = SecurityUtil.decrypt(algorithm.getValue(), secureKey, secureIV, cipherText);
		} else if(algorithm == EncryptAlgorithm.AES128CBC) {
			clearText = SecurityUtil.decrypt(algorithm.getValue(), secureKey, secureIV, cipherText);
		} else if(algorithm == EncryptAlgorithm.AES128ECB) {
			clearText = SecurityUtil.decrypt(algorithm.getValue(), secureKey, secureIV, cipherText);
		}
		
		return clearText;
		
	}
	
	private void hashObject(Object[] args) throws Exception {
		for(int i=0; i<args.length; i++) {
			Object argObj = args[i];
			if(argObj != null) {
				Class<? extends Object> argClass = argObj.getClass();
				

				for (Field f : argClass.getDeclaredFields()) {
					Hash hashField = f.getAnnotation(Hash.class);
					if(hashField != null && f.getType().getName().equalsIgnoreCase("java.lang.String")) {
						String clearText = (String) ReflectUtil.getFieldValue(args[i], f.getName());
						String digestText = HashAgent.hash(hashField.algorithm().getValue(), clearText, null);
						
						ReflectUtil.setFieldValue(args[i], f.getName(), digestText);
					}
				}
			}
		}
	}
	

	private void restoreObject(Object[] changeArgs, Object[] copyArgs) throws Exception {
		
		if(changeArgs.length != copyArgs.length ) {
			throw new IllegalArgumentException("changeArgs does not match copyArgs !!");
		}
		
		for(int i=0; i<changeArgs.length; i++) {
			Object changeArgObj = changeArgs[i];
			
			if(changeArgObj != null) {
				Class<? extends Object> changeArgClass = changeArgObj.getClass();
			
				for (Field changeArgfield : changeArgClass.getDeclaredFields()) {
					if (( changeArgfield.isAnnotationPresent(Encrypt.class) || changeArgfield.isAnnotationPresent(Hash.class) ) && changeArgfield.getType().getName().equalsIgnoreCase("java.lang.String")) {
			        	String clearText = (String) ReflectUtil.getFieldValue(copyArgs[i], changeArgfield.getName());
				        	
			        	ReflectUtil.setFieldValue(changeArgs[i], changeArgfield.getName(), clearText);
					}
				}
			}
		}

	}

	private void decryptObject(Object result) throws Exception {
		for (Field f : result.getClass().getDeclaredFields()) {
			if (f.isAnnotationPresent(Encrypt.class) && f.getType().getName().equalsIgnoreCase("java.lang.String")) {
				Encrypt encryptField = f.getAnnotation(Encrypt.class);
	        	String cipherText = (String) ReflectUtil.getFieldValue(result, f.getName());
				String decryptedText = decryptValue(encryptField.algorithm(), encryptField.secureKey(), encryptField.secureIV(), cipherText);

	        	ReflectUtil.setFieldValue(result, f.getName(), decryptedText);
		     }
		}
	}
	
	
	
/*	
	private boolean isPrimitive(Class<? extends Object> argClass) throws InstantiationException, IllegalAccessException {
		boolean isPrimitive = false;

		if(argClass.isPrimitive()) {
			isPrimitive = true;
		} else if(argClass.getName().equalsIgnoreCase("java.lang.String")) {
			isPrimitive = true;
		} else if(argClass.getSuperclass() == java.lang.Number.class) {
			isPrimitive = true;
		}
	
log.debug("isPrimitive:" + isPrimitive);
		return isPrimitive;
	}
*/
	
	private static <T extends Annotation> T getAnnotation(Class<?> clazz, Class<T> annotationType) {
	    T result = clazz.getAnnotation(annotationType);
	    if (result == null) {
	        Class<?> superclass = clazz.getSuperclass();
	        if (superclass != null) {
	            return getAnnotation(superclass, annotationType);
	        } else {
	            return null;
	        }
	    } else {
	        return result;
	    }
	}
	
	@SuppressWarnings("unused")
	private class EncryptionObjectResult {
		private Object[] changeArgs = null;
		private Object[] copyArgs = null;
		
		public EncryptionObjectResult(Object[] changeArgs, Object[] copyArgs) {
			this.changeArgs = changeArgs;
			this.copyArgs = copyArgs;
		}
		
		public Object[] getChangeArgs() {
			return changeArgs;
		}
		public void setChangeArgs(Object[] changeArgs) {
			this.changeArgs = changeArgs;
		}
		public Object[] getCopyArgs() {
			return copyArgs;
		}
		public void setCopyArgs(Object[] copyArgs) {
			this.copyArgs = copyArgs;
		}
		
	}

}
