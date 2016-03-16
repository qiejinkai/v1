package com.qjk.dao;

import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

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
	
	
	public void setRedisTemplate(RedisTemplate<String,Object>redisTemplate);
	
	
	public RedisTemplate<String,Object> getRedisTemplate();

	/**
	 * ªÒ»° RedisSerializer <br>
	 * ------------------------------<br>
	 */
	public RedisSerializer<String> getRedisSerializer();
	
	public String getCache(String key);
	
	public void deleteCache(String key);
	
	public boolean setCache(String key,String value);
	

}
