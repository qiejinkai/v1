package com.qjk.dao;

import java.util.List;

/**
 * 
 * @author qiejinkai
 *
 * @param <T>
 */

@SuppressWarnings("rawtypes")
public interface BaseDao <T> {
	
	public void add(String statement,T t);
	
	public void update(String statement, T t);
	
	public void delete(String statement, T t);
	
	public T queryById(String statement,long id);
	
	public List<T> queryList(String statement);
	
	public void add(T t);
	
	public void update(T t);

	public void delete(T t);
	
	public void deleteById(Class c,long id);
	
	public T queryById(Class c,long id);
	
	public List<T> queryList(Class c);
	
	public T query(T t);
	
	public List<T> queryList(T t);
	
	

}
