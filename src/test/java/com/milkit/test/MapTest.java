package com.milkit.test;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import org.junit.Test;

public class MapTest {
	
	@Test
	public void LinkedHashMap_테스트() {
		LinkedHashMap<String, Object> resultMap = new LinkedHashMap<String, Object>();
		resultMap.put("ITEM_CD","ITEM_CD");
		resultMap.put("ITEM_NM", "ITEM_NM");
		resultMap.put("ITEM_LANG_NM", "ITEM_LANG_NM");
		resultMap.put("L_CLASS_CD", "L_CLASS_CD");
		resultMap.put("M_CLASS_CD", "M_CLASS_CD");
		resultMap.put("S_CLASS_CD", "S_CLASS_CD");
		resultMap.put("ITEM_DIV", "ITEM_DIV");
		resultMap.put("CAPACITY", "CAPACITY");
		resultMap.put("STANDARD", "STANDARD");
		
		Set<String> keys = resultMap.keySet();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			System.out.println(it.next());
		}
		
	}
	
	
	@Test
	public void HashTable_테스트() {
		Hashtable<String, Object> resultMap = new Hashtable<String, Object>();
		resultMap.put("ITEM_CD","ITEM_CD");
		resultMap.put("ITEM_NM", "ITEM_NM");
		resultMap.put("ITEM_LANG_NM", "ITEM_LANG_NM");
		resultMap.put("L_CLASS_CD", "L_CLASS_CD");
		resultMap.put("M_CLASS_CD", "M_CLASS_CD");
		resultMap.put("S_CLASS_CD", "S_CLASS_CD");
		resultMap.put("ITEM_DIV", "ITEM_DIV");
		resultMap.put("CAPACITY", "CAPACITY");
		resultMap.put("STANDARD", "STANDARD");
		
		for (Enumeration<String> it = resultMap.keys(); it.hasMoreElements();) {
			System.out.println(it.nextElement());
		}
	}
}
