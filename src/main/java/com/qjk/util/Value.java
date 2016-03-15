package com.qjk.util;

import com.qjk.exception.NullException;

public class Value {

	private Value() {
		
	}
	
	public static  boolean nullValueValidate(String ...args)throws NullException{
		
		if(args.length == 0){
			return false;
		}
		if(args.length%2 != 0){
			return false;
		}
		
		for (int i = 0; i < args.length; i+=2) {
			
			nullValueValidate(args[i], args[i+1]);	
		}
		
		return true;
		
	}
	public static void nullValueValidate(String title,String data) throws NullException{
		
		if(data == null){
			throw new NullException(title +" ÖµÎª¿Õ");
		}
		
	}
	
	public static long longValueOf(String data,long defalut){
		if(data == null){
			
			return defalut;
		}
		
		try {
			long value = Long.parseLong(data);
			
			return value;
		} catch (NumberFormatException e) {
			
			return defalut;
		}
	}
	
	public  static int  intValueOf(String data,int defalut){
		if(data == null){
			
			return defalut;
		}
		
		try {
			int value = Integer.parseInt(data);
			
			return value;
		} catch (NumberFormatException e) {
			
			return defalut;
		}
	}
	
	public  static boolean  isEmpty(String data){
		if(data == null){
			
			return true;
		}
		
		if("".equals(data)){
			
			return true;
		}
		
		return false;
	}
	
}
