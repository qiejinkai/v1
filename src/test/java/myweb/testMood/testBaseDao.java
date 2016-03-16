package myweb.testMood;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qjk.dao.BaseDao;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class testBaseDao extends AbstractJUnit4SpringContextTests {

	@Resource
	public BaseDao baseDaoImpl;

	@Test
	public void testRedisSet(){
		
		boolean result = baseDaoImpl.setCache("1", "qiejinkai2");
		
		System.out.println(result);
		
	}
	
	@Test
	public void testRedisGet(){
		
		String result = baseDaoImpl.getCache("2");
		
		System.out.println(result);
		
	}
	
	
}
