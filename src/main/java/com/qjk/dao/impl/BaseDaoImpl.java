package com.qjk.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import com.qjk.dao.BaseDao;

/**
 * 基本的curd
 * 
 * @author qiejinkai
 *
 * @param <T>
 */
@SuppressWarnings("rawtypes")
@Repository
public class BaseDaoImpl<T> implements BaseDao<T>{
	
	
	@Resource
	protected SqlSessionTemplate sqlSessionTemplate;
	
	@Resource
	protected RedisTemplate<String, Object> redisTemplate;


	/**
	 * 设置redisTemplate
	 * 
	 * @param redisTemplate
	 *            the redisTemplate to set
	 */
	public void setRedisTemplate(RedisTemplate<String,Object>redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 获取 RedisSerializer <br>
	 * ------------------------------<br>
	 */
	public RedisSerializer<String> getRedisSerializer() {
		return redisTemplate.getStringSerializer();
	}

	@Override
	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}
	
	
	
	

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

	@Override
	public String getCache(final String key) {
		 String result = redisTemplate.execute(new RedisCallback<String>() {  
	            public String doInRedis(RedisConnection connection)  
	                    throws DataAccessException {  
	                RedisSerializer<String> serializer = getRedisSerializer();  
	                byte[] keyBates = serializer.serialize(key);  
	                byte[] valueBates = connection.get(keyBates);  
	                if (valueBates == null) {  
	                    return null;  
	                }  
	                String result = serializer.deserialize(valueBates);  
	                return result;  
	            }  
	        });  
	        return result;  
	}

	@Override
	public void deleteCache(String key) {
		List<String> list = new ArrayList<String>();  
		list.add(key);  
		delete(list);  
	}

	@Override
	public boolean setCache(final String key, final String value) {
		
		redisTemplate.delete(key);
		boolean result = redisTemplate.
								execute(new RedisCallback<Boolean>() {  
		    public Boolean doInRedis(RedisConnection connection)  
		            throws DataAccessException {  
		        RedisSerializer<String> serializer = getRedisSerializer();
		            byte[] keyBates  = serializer.serialize(key);
		            byte[] nameBates = serializer.serialize(value);  
		            connection.setNX(keyBates, nameBates);  
		        return true;  
		    }  
		}, false, true); 

		return result;
		
	}	
	
	private void delete(List<String> keys) {  
        redisTemplate.delete(keys);  
    }  
}
