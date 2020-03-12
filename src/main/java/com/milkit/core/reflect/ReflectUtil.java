package com.milkit.core.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectUtil {
	
	
	public static Object getFieldValue(Object object, String fieldName) throws NoSuchFieldException,
								IllegalArgumentException,IllegalAccessException,SecurityException {
		Object fieldVale = null;
		
		if(object == null || fieldName == null) {
		   throw new IllegalArgumentException("argument can not be null");
		}

		Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);

		if (field != null) {
			fieldVale = field.get(object);
		}
		
		return fieldVale;
	}
	
	public static void setFieldValue(Object object, String replaceFieldName, Object replaceFieldVal) throws NoSuchFieldException, IllegalAccessException {
		if(object == null || replaceFieldName == null) {
			throw new IllegalArgumentException("argument can not be null");
		}
		
		Field field = object.getClass().getDeclaredField(replaceFieldName);

		if (field != null) {
			field.setAccessible(true);
			field.set(object, replaceFieldVal);
		}
	}

	
	/**
	 * String 의 값을 변경 시킴.
	 *
	 * @Deprecated  Not for public use.
	 *    주의!! String의 값을 변경하기 위해 이 메소드를 쓰면 
	 *    (실제 pool의 값을 조작함으로) 같은 String pool을 공유하는 다른 Stirng 객체 값들도 변경됨. 
	 */
	@Deprecated
	public static void setStringValue(Object object, String original) throws NoSuchFieldException, IllegalAccessException {
		char value[] = null;
		int size = 0;
		
		if(object != null && original != null) {
			char[] charValue = original.toCharArray();
			size = charValue.length;
			value = Arrays.copyOf(charValue, size);
			
//			setFieldValue(object, "offset", 0);
//			setFieldValue(object, "count", size);
			setFieldValue(object, "value", value);
		}
	}
	
	public static Object invokeMethod(Object object, String methodName, Object[] args, Class[] argTypes) throws SecurityException, NoSuchMethodException, 
									IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Object methodValue = null;
		if(object == null || methodName == null) {
			throw new IllegalArgumentException("argument can not be null");
		}
		
    	Method method = object.getClass().getMethod(methodName, argTypes);
    	if(method != null) {
    		methodValue = method.invoke(object, args);
    	}
    	
    	return methodValue;
	}

}
