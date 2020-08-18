package com.milkit.core.util;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;


public class PrintUtil {
	
	
	public static void print(PrintInterface printer, String message) {
		printer.print(message);
	}
	
	public static void print(PrintInterface printer, Collection message) {
		if(printer != null && message != null) {
			printer.print("List size:["+message.size()+"]");
			for (Iterator e = message.iterator(); e.hasNext();) {
//				printer.print(e.next().getClass().getInterfaces()[0]);
				printer.print(e.next());
			}
		}
	}
	
	public static void print(PrintInterface printer, Map message) {
		printer.print("Map size:["+message.size()+"]");
		Set<Object> keySet = message.keySet();
		
		for (Iterator<Object> e = keySet.iterator(); e.hasNext();) {
			Object keyString = e.next();
			Object value = message.get(keyString);
			
			printer.print("[Key]:"+keyString);
			printer.print("[Value]:"+value+"\n");
		}
	}
	
	public static void print(PrintInterface printer, Object[] message) {
		if(printer != null && message != null) {
			printer.print("Array size:["+message.length+"]");
			for(int i=0; i<message.length; i++) {
				printer.print(message[i]);
			}
		}
	}
	
	public static void printHashCode(PrintInterface printer, Object[] message) {
		if(printer != null && message != null) {
			printer.print("Array size:["+message.length+"]");
			for(int i=0; i<message.length; i++) {
				printer.print(message[i].hashCode());
			}
		}
	}
	
	public static void print(Logger printer, String message) {
		if(printer != null) {
			printer.debug(message);
		}
	}
	
	public static void print(Logger printer, Collection message) {
		if(printer != null && message != null) {
			printer.debug("List size:["+message.size()+"]");
			for (Iterator e = message.iterator(); e.hasNext();) {
				printer.debug(e.next());
			}
		}
	}
	
	public static void print(Logger printer, Map message) {
		if(printer != null && message != null) {
			printer.debug("Map size:["+message.size()+"]");
			Set<Object> keySet = message.keySet();
			
			for (Iterator<Object> e = keySet.iterator(); e.hasNext();) {
				Object keyString = e.next();
				Object value = message.get(keyString);
				
				printer.debug("[Key]:"+keyString);
				printer.debug("[Value]:"+value+"\n");
			}
		}
	}
	
	
	public static void print(Logger printer, Object[] message) {
		if(printer != null && message != null) {
			printer.debug("Array size:["+message.length+"]");
			for(int i=0; i<message.length; i++) {
				printer.debug(message[i]);
			}
		}
	}
	
	public static void print(Logger printer, int[] message) {
		if(printer != null && message != null) {
			printer.debug("Array size:["+message.length+"]");
			for(int i=0; i<message.length; i++) {
				printer.debug(message[i]);
			}
		}
	}
	
	public static void print(Logger printer, byte[] message) {
		if(printer != null && message != null) {
			printer.debug("Array size:["+message.length+"]");
			for(int i=0; i<message.length; i++) {
				printer.debug(message[i]);
			}
		}
	}
	

	public static void print(PrintInterface printer, int[] intArr) {
		if(printer != null && intArr != null) {
			printer.print("Array size:["+intArr.length+"]");
			for(int i=0; i<intArr.length; i++) {
				printer.print(intArr[i]);
			}
		}
	}
	
	public static void print(PrintInterface printer, byte[] intArr) {
		if(printer != null && intArr != null) {
			printer.print("Array size:["+intArr.length+"]");
			for(int i=0; i<intArr.length; i++) {
				printer.print(intArr[i]);
			}
		}
	}
	
	public static void printHashCode(Logger printer, Object[] message) {
		if(printer != null && message != null) {
			printer.debug("Array size:["+message.length+"]");
			for(int i=0; i<message.length; i++) {
				printer.debug(message[i].hashCode());
			}
		}
	}



}
