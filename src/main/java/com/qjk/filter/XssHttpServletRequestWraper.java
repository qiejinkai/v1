package com.qjk.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;



public class XssHttpServletRequestWraper extends HttpServletRequestWrapper{

	public XssHttpServletRequestWraper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		return super.getParameter(name);
	}
	
	@Override
	public String getHeader(String name) {
		return super.getHeader(name);
	}
	
	@Override
	public String[] getParameterValues(String name) {
		return super.getParameterValues(name);
	}
}
