package com.qjk.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import com.qjk.dao.BaseDao;

/**
 * »ù±¾µÄcurd
 * 
 * @author qiejinkai
 *
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public class BaseDaoImpl<T> implements BaseDao<T>{
	
	
	@Resource
	protected SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void add(String statement, T t) {
		
		this.sqlSessionTemplate.insert(statement, t);
		
	}

	@Override
	public void update(String statement, T t) {
		
		this.sqlSessionTemplate.update(statement, t);
		
	}

	@Override
	public void delete(String statement, T t) {
		
		this.sqlSessionTemplate.delete(statement, t);
		
	}


	@Override
	public T queryById(String statement, long id) {
		
		return sqlSessionTemplate.selectOne(statement, id);
	}

	@Override
	public List<T> queryList(String statement) {
		
		return sqlSessionTemplate.selectList(statement);
	}

	@Override
	public void add(T t) {
		
		if(null != t){
			
			String statement="add"+t.getClass().getSimpleName();
			
			this.add(statement,t);
			
		}
		
	}

	@Override
	public void update(T t) {
		
		if(null != t){
			
			String statement="update"+t.getClass().getSimpleName();
			
			this.update(statement,t);
			
		}
		
	}

	@Override
	public void delete(T t) {
		
		if(null != t){
			
			String statement="delete"+t.getClass().getSimpleName();
			
			this.delete(statement,t);
			
		}
		
	}
	
	@Override
	public void deleteById(Class c, long id) {
		if(0 != id){
			
			String statement="delete"+c.getSimpleName();
			
			this.sqlSessionTemplate.delete(statement,id);
			
		}
		
	}

	
	@Override
	public T queryById(Class c, long id) {
		
		if(null != c){
			
			String statement="query"+c.getSimpleName()+"ById";
			
			return this.queryById(statement,id);
			
		}
		
		return null;
	}

	@Override
	public List<T> queryList(Class c) {
		
		if(null != c){
			
			String statement="query"+c.getSimpleName()+"List";
			
			return this.queryList(statement);
			
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T query(T t) {
		
		if(null != t){
			
			String statement="query"+t.getClass().getSimpleName();
			
			return (T)this.sqlSessionTemplate.selectOne(statement,t);
			
		}
		return null;
	}
	
	@Override
	public List<T> queryList(T t) {
		if(null != t){
			
			String statement="query"+t.getClass().getSimpleName();
			
			return this.sqlSessionTemplate.selectList(statement,t);
			
		}
		return null;
	}	
}
