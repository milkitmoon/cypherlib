package com.milkit.core.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ArrayUtil {
	
	@SuppressWarnings("unchecked")
	public static List getListFromArray(Object[] array) {
		List arrayList = new ArrayList<Object>();
		Collections.addAll( arrayList, array);
		
		return arrayList;
	}
	
	public static <T> List<T> getListFromArray(Class<T> componentType, T[] array) {
		List<T> arrayList = new ArrayList<T>();
		if(array != null) {
			Collections.addAll( arrayList, array);
		}
		
		return arrayList;
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] getArrayFormList(Class<T> componentType, Collection<T> list) {
		 T[] objects = (T[])Array.newInstance(componentType, 0);
		
		return (T[]) list.toArray( objects );
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] addArray( T[] srcArray, Class<T> componentType, T addingObject ) {
		T[] destArray = null;
		
		if(srcArray != null) {
			destArray = (T[])Array.newInstance(componentType, srcArray.length+1);
			System.arraycopy(srcArray, 0, destArray, 0, srcArray.length);
			
			destArray[srcArray.length] = (T) addingObject;
		}
		
		return destArray;
	}
	
	public static int[] addArray( int[] srcArray, int addingObject ) {
		int[] destArray = null;
		
		if(srcArray != null) {
			destArray = new int[srcArray.length+1];
			System.arraycopy(srcArray, 0, destArray, 0, srcArray.length);
			
			destArray[srcArray.length] = addingObject;
		}
		
		return destArray;
	}
	
	public static byte[] addArray( byte[] srcArray, byte addingObject ) {
		byte[] destArray = null;
		
		if(srcArray != null) {
			destArray = new byte[srcArray.length+1];
			System.arraycopy(srcArray, 0, destArray, 0, srcArray.length);
			
			destArray[srcArray.length] = addingObject;
		}
		
		return destArray;
	}

	public static <T> T[] addArray(T[] srcArray, Class<T> componentType, T[] addingArray) {
		T[] destArray = null;
		
		if(srcArray != null && addingArray != null) {
			destArray = (T[])Array.newInstance(componentType, srcArray.length+addingArray.length);
			System.arraycopy(srcArray, 0, destArray, 0, srcArray.length);
			System.arraycopy(addingArray, 0, destArray, srcArray.length, addingArray.length);
		}
		
		return destArray;
	}
	
	public static int[] addArray(int[] srcArray, int[] addingArray) {
		int[] destArray = null;
		
		if(srcArray != null && addingArray != null) {
			destArray = new int[srcArray.length+addingArray.length];
			System.arraycopy(srcArray, 0, destArray, 0, srcArray.length);
			System.arraycopy(addingArray, 0, destArray, srcArray.length, addingArray.length);
		}
		
		return destArray;
	}

	public static byte[] addArray(byte[] srcArray, byte[] addingArray) {
		byte[] destArray = null;
		
		if(srcArray != null && addingArray != null) {
			destArray = new byte[srcArray.length+addingArray.length];
			System.arraycopy(srcArray, 0, destArray, 0, srcArray.length);
			System.arraycopy(addingArray, 0, destArray, srcArray.length, addingArray.length);
		}
		
		return destArray;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] getCuttingArray(Class<T> componentType, T[] sourceArray, int startIndex, int endIndex) {
		T[] objects = null;
		
        if(sourceArray != null) {
            if(sourceArray.length < startIndex || (endIndex<startIndex)) {
                return objects;
            }
            
            int targetLength = endIndex-startIndex;
            if(sourceArray.length < (endIndex-startIndex)) {
            	targetLength = sourceArray.length%targetLength;
            } else {
            	if(sourceArray.length < endIndex) {
            		targetLength = sourceArray.length%targetLength;
            	}
            }
            objects = (T[])Array.newInstance(componentType, targetLength);
   
            System.arraycopy(sourceArray, startIndex, objects, 0, objects.length);
        }
        
        return objects;
	}

	
	@SuppressWarnings("unchecked")
	public static <T> T[] getCopyArray(Class<T> componentType, T[] sourceArray) {
		T[] objects = null;
		
        if(sourceArray != null) {
            int targetLength = sourceArray.length;
            objects = (T[])Array.newInstance(componentType, targetLength);
   
            System.arraycopy(sourceArray, 0, objects, 0, objects.length);
        }
        
        return objects;
	}
	
	
	/*
	public static <T> T[] getDeepCopyArray(Class<T> componentType, T[] sourceArray) throws Exception {
		T[] objects = null;
		
        if(sourceArray != null) {
            int targetLength = sourceArray.length;
            objects = (T[])Array.newInstance(componentType, targetLength);
   
            for(int i=0; i<sourceArray.length; i++) {
            	objects[i] = (T) ObjectCloner.deepCopy(sourceArray[i]);
            }
        }
        
		return objects;
	}
	*/


	@SuppressWarnings("unchecked")
	public static <T> T[] getDeepCopyArray(Class<T> componentType, T[] sourceArray) throws Exception {
        return (T[])ObjectCloner.deepCopy(sourceArray);
	}

	public static <T> List<T> getDeepCopyList(List<T> source) {
	    List<T> dest = new ArrayList<T>();
	    for (T item : source) { dest.add(item); }
	    
	    return dest;
	}

}
