package com.qjk.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisUtil {

	private static JedisPool _pool;
	
	
	public static int maxActive = 500;
	
	public static String host = "127.0.0.1";
	
	public static int port = 6379;
	
	public static JedisPool pool(){
		
		if (_pool == null || _pool.isClosed()) {

			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(maxActive);
			config.setMaxIdle(5);
			config.setMaxWaitMillis(1000 * 100);
			config.setTestOnBorrow(true);

			_pool = new JedisPool(config, host, port);

		}

		return _pool;
	}
	
}
