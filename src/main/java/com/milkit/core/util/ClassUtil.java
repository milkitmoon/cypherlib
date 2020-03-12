package com.milkit.core.util;

import java.util.Collection;

public class ClassUtil {
	
    @SuppressWarnings("unchecked")
	public static <T> Class<T> castClass(Class<?> aClass) {
        return (Class<T>)aClass;
    }
    
    
	public static <T, C extends Collection<T>> Class<C> castClass2(Class<C> aClass) {
        return (Class<C>)aClass;
    }
}
