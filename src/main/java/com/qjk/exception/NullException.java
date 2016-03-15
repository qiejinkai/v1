package com.qjk.exception;

public class NullException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullException() {
		super();
	}
	public NullException(String msg) {
		super(msg);
	}
	
	public NullException(String msg,Throwable e){
		super(msg,e);
	}
}
