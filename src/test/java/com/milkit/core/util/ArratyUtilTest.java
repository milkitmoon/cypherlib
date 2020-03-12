package com.milkit.core.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milkit.core.util.ArrayUtil;
import com.milkit.core.util.PrintUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:com/milkit/core/spring/context-*.xml",
		"classpath*:com/milkit/test/spring/context-*.xml"
})
public class ArratyUtilTest {
	
	private static final Logger logger = Logger.getLogger(ArratyUtilTest.class);

	
	@Test
	public void List를배열로변환_테스트() {
		List<String> values = new ArrayList<String>();
		values.add("test1");
		values.add("test2");
		values.add("test3");
		
		String[] arrayValues = null;
		if(values != null) {
			arrayValues = (String[])ArrayUtil.getArrayFormList(String.class, values);
		}
		
		PrintUtil.print(logger, arrayValues);
	}
	
	@Test
	public void int를배열로변환_테스트() {
		List<Integer> values = new ArrayList<Integer>();
		values.add(1);
		values.add(1);
		values.add(1);
		
		Integer[] arrayValues = null;
		if(values != null) {
			arrayValues = (Integer[])ArrayUtil.getArrayFormList(Integer.class, values);
		}
		
		PrintUtil.print(logger, arrayValues);
	}
	
	@Test
	public void 배열추가_테스트() {
		String[] beanContextPath = {"classpath*:/com/milkit/pos/spring/context-*.xml"};
		String contextPath = "TESTTESET";
		
		beanContextPath = ArrayUtil.addArray(beanContextPath, String.class, contextPath);
		
		PrintUtil.print(logger, beanContextPath);
	}


	@Test
	public void 배열추가_테스트2() {
		int[] intArr = {0};
		int[] contextPath = {1};
		
//		String[] intArr = {"0"};
//		String[] contextPath = {"1"};
		intArr = ArrayUtil.addArray(intArr, contextPath);
		
		PrintUtil.print(logger, intArr);
	}

}
