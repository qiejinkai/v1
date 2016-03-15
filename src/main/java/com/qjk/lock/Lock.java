package com.qjk.lock;

/**
 * Ëø
 * @author qiejinkai
 *
 */
public class Lock {
	
	private static final Lock lock=new Lock();
	
	private Lock() {
		
	}
	
	public static Lock getLock(){
		
		return lock;
	}
	
}
