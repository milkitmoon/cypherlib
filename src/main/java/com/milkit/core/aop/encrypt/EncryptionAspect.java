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
import com.milkit.core.annotations.encrypt.Encrypt.Algorithm;
import com.milkit.core.reflect.ReflectUtil;
import com.milkit.core.security.BlowfishUtil;
import com.milkit.core.security.SecurityGlobal;
import com.milkit.core.security.SecurityUtil;
import com.milkit.core.security.hash.HashAgent;
import com.milkit.core.security.method.DigestMethod;
import com.milkit.core.util.ArrayUtil;
import com.milkit.core.util.ObjectCloner;


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
//    	log.debug("encryptBeforeSaving...The method " + joinPoint.getSignature().getName()+ "() begins with " + Arrays.toString(joinPoint.getArgs()));
//    	log.debug("encryptBeforeSaving...Target class : "+ joinPoint.getTarget().getClass().getName());
    	
    	Object methodOutput = null;
    	Object[] copyOriginArgs = null;
    	
	EncryptType encryptType = doEncryption.type();
			
	copyOriginArgs = doEncryption(joinPoint, encryptType);
	methodOutput = doDecryption(joinPoint, encryptType);
		
//log.debug("methodOutput : "+ methodOutput);
		
	restoreEncryptArgumentField(joinPoint, copyOriginArgs);
		
//log.debug("methodOutput after: "+ methodOutput);
			
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
        	
//log.debug("decryptedObject:" + output);
            return output;
        } catch (IllegalArgumentException e){
            throw e;
        } 
    	
    }


	private Object[] encryptionObject(ProceedingJoinPoint joinPoint, EncryptType encryptType) throws Throwable {
		Object[] args = joinPoint.getArgs();
		Object[] copyArgs = ArrayUtil.getDeepCopyArray(Object.class, args);
		
		if(args != null && args.length > 0) {
    		encryptObject(args, encryptType);
    		hashObject(args);		//also do hashing !!
    		
//			encryptionMethodArguments(joinPoint, encryptType, args);
    	}

		return copyArgs;
	}
    
	private void encryptionMethodArguments(JoinPoint joinPoint, EncryptType encryptType, Object[] args) throws Exception {
		
		if (joinPoint.getSignature() instanceof MethodSignature) {
			MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
			Method method = methodSignature.getMethod();
			
			Class<?>[] paramClasses = method.getParameterTypes();
			Annotation[][] annotations = method.getParameterAnnotations();
				
			for(int j=0; j < annotations.length; j++) {
				for(int k=0; k < annotations[j].length; k++) {
					if(annotations[j][k].annotationType().equals(Encrypt.class) && paramClasses[j].getName().equalsIgnoreCase("java.lang.String")) {
						Method toString = paramClasses[j].getMethod("toString");
						String clearText = (String)toString.invoke((String)args[j], null);
	
						String cipherText = BlowfishUtil.encrypt(clearText);
logger.debug("Arguments clearText:" + clearText);
logger.debug("Arguments cipherText:" + cipherText);

//						Object copyArg = ObjectCloner.deepCopy(args[j]);
//						ReflectUtil.setStringValue(copyArg, cipherText);
						
						ReflectUtil.setStringValue(args[j], cipherText);
						
//						encryptObjectElement(clearText, EncryptType.Encrypt);
					}
				}
	
			}
		}
	}

	private void encryptObject(Object[] args, EncryptType encryptType) {
		for(int i=0; i<args.length; i++) {
			Object argObj = args[i];
			
//log.debug("@@@@@			instanceof Collection:"+(argObj instanceof Collection));
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
	
	private void encryptObjectElement(Object argObj, EncryptType encryptType) {
		if(argObj != null) {
			Class<? extends Object> argClass = argObj.getClass();
			
			try {
				for (Field f : argClass.getDeclaredFields()) {
					if ( f.isAnnotationPresent(Encrypt.class) && f.getType().getName().equalsIgnoreCase("java.lang.String")
						&& (encryptType == EncryptType.Encrypt || encryptType == EncryptType.Both) ) {
						
						Encrypt encryptField = f.getAnnotation(Encrypt.class);
						
//						Algorithm algorithm = encryptField.algorithm();
						
			        	String clearText = (String) ReflectUtil.getFieldValue(argObj, f.getName());
			        	if(clearText != null && !clearText.equals("")) {
//							String cipherText = SecurityUtil.encrypt(clearText);
							String cipherText = encryptValue(encryptField.algorithm(), encryptField.secureKey(), encryptField.secureIV(), clearText);
							
//log.debug("clearText:" + clearText);
//log.debug("cipherText:" + cipherText);
				        	
				        	ReflectUtil.setFieldValue(argObj, f.getName(), cipherText);
			        	}
					}
				}

			} catch (Throwable ex) {
				logger.error(ex);
			}
		}
	}

	
	private String encryptValue(Algorithm algorithm, String secureKey, String secureIV, String clearText) {
		String cipherText = null;
		if(algorithm == null || algorithm.equals("")) {
			algorithm = Algorithm.BlowfishECB;
		}
		
		if(secureKey == null || secureKey.equals("")) {
			secureKey = SecurityGlobal.SecurityKey;
		}
		
		if(secureIV == null || secureIV.equals("")) {
			secureIV = SecurityGlobal.SecurityKeyIV;
		}
		
		try {
			if(algorithm == Algorithm.BlowfishECB) {
				cipherText = SecurityUtil.encrypt(algorithm.getValue(), secureKey, null, clearText);
			} else if(algorithm == Algorithm.BlowfishCBC) {
				cipherText = SecurityUtil.encrypt(algorithm.getValue(), secureKey, secureIV, clearText);
			} else if(algorithm == Algorithm.AES128CBC) {
				cipherText = SecurityUtil.encrypt(algorithm.getValue(), secureKey, secureIV, clearText);
			} else if(algorithm == Algorithm.AES128ECB) {
				cipherText = SecurityUtil.encrypt(algorithm.getValue(), secureKey, secureIV, clearText);
			}
		} catch(Exception ex) {
			logger.error("암호화오류", ex);
		}
		
		return cipherText;
		
	}

	
	private String decryptValue(Algorithm algorithm, String secureKey, String secureIV, String cipherText) {
		String clearText = null;
		if(algorithm == null || algorithm.equals("")) {
			algorithm = Algorithm.BlowfishECB;
		}
		
		if(secureKey == null || secureKey.equals("")) {
			secureKey = SecurityGlobal.SecurityKey;
		}
		
		if(secureIV == null || secureIV.equals("")) {
			secureIV = SecurityGlobal.SecurityKeyIV;
		}
		
		try {
			if(algorithm == Algorithm.BlowfishECB) {
				clearText = SecurityUtil.decrypt(algorithm.getValue(), secureKey, null, cipherText);
			} else if(algorithm == Algorithm.BlowfishCBC) {
				clearText = SecurityUtil.decrypt(algorithm.getValue(), secureKey, secureIV, cipherText);
			} else if(algorithm == Algorithm.AES128CBC) {
				clearText = SecurityUtil.decrypt(algorithm.getValue(), secureKey, secureIV, cipherText);
			} else if(algorithm == Algorithm.AES128ECB) {
				clearText = SecurityUtil.decrypt(algorithm.getValue(), secureKey, secureIV, cipherText);
			}
		} catch(Exception ex) {
			logger.error("복호화오류", ex);
		}
		
		return clearText;
		
	}
	
	private void hashObject(Object[] args) {
		for(int i=0; i<args.length; i++) {
			Object argObj = args[i];
			if(argObj != null) {
				Class<? extends Object> argClass = argObj.getClass();
				
				try {
					for (Field f : argClass.getDeclaredFields()) {
						Hash hashField = f.getAnnotation(Hash.class);
//						if(f.isAnnotationPresent(Hash.class) && f.getType().getName().equalsIgnoreCase("java.lang.String")) {
						if(hashField != null && f.getType().getName().equalsIgnoreCase("java.lang.String")) {
							String clearText = (String) ReflectUtil.getFieldValue(args[i], f.getName());
							String digestText = HashAgent.hash(hashField.algorithm().getValue(), clearText, null);
//log.debug("clearText:" + clearText);
//log.debug("digestText:" + digestText);
							
							ReflectUtil.setFieldValue(args[i], f.getName(), digestText);
						}
					}

				} catch (Throwable ex) {
					logger.error(ex);
				}
			}
		}
	}
	

	private void restoreObject(Object[] changeArgs, Object[] copyArgs) throws Exception {
		
		if(changeArgs.length != copyArgs.length ) {
			throw new IllegalArgumentException("changeArgs does not match copyArgs");
		}
		
		for(int i=0; i<changeArgs.length; i++) {
			Object changeArgObj = changeArgs[i];
			
			if(changeArgObj != null) {
				Class<? extends Object> changeArgClass = changeArgObj.getClass();
			
				try {
					for (Field changeArgfield : changeArgClass.getDeclaredFields()) {
						if (( changeArgfield.isAnnotationPresent(Encrypt.class) || changeArgfield.isAnnotationPresent(Hash.class) ) && changeArgfield.getType().getName().equalsIgnoreCase("java.lang.String")) {
				        	String clearText = (String) ReflectUtil.getFieldValue(copyArgs[i], changeArgfield.getName());
							
//log.debug("restoreClearText:" + clearText);
				        	
				        	ReflectUtil.setFieldValue(changeArgs[i], changeArgfield.getName(), clearText);
						}
					}

				} catch (Throwable ex) {
					logger.error(ex);
				}
			}
		}

	}

	private void decryptObject(Object result) {
		for (Field f : result.getClass().getDeclaredFields()) {
			if (f.isAnnotationPresent(Encrypt.class) && f.getType().getName().equalsIgnoreCase("java.lang.String")) {
		        try {
//log.debug("encryptBeforeSaving...@Encrypt annotation field name: " + f.getName());

				Encrypt encryptField = f.getAnnotation(Encrypt.class);
		        	String cipherText = (String) ReflectUtil.getFieldValue(result, f.getName());
//log.debug("cipherText:" + cipherText);

					String decryptedText = decryptValue(encryptField.algorithm(), encryptField.secureKey(), encryptField.secureIV(), cipherText);
//log.debug("decryptedText:" + decryptedText);
					
//    		        String decryptedText = SecureCreditUtils.decrypt(encryptionKeys, cipherText);

		        	ReflectUtil.setFieldValue(result, f.getName(), decryptedText);
		        	
		        } 
		        catch (Throwable ex) {
		        	logger.error("decryptAfterFinding...ex:" + ex.getMessage());
		        }
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
