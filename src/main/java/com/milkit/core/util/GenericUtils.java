package com.milkit.core.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class GenericUtils {
    public static Class getClassOfGenericTypeIn(Class clazz, int index){
        ParameterizedType genericSuperclass = (ParameterizedType) clazz.getGenericSuperclass();
        Type wantedClassType = genericSuperclass.getActualTypeArguments()[index];
        
        if (wantedClassType instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) wantedClassType).getRawType();
        } else {
            return (Class) wantedClassType;
        }
    }
}