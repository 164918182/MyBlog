package com.java.blog.utils;

import java.util.Properties;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {

	private static JedisPool jedisPool;//连接池
	private static final String password;
	private static final Integer timeout;

	static {
		Properties p = PropertiesUtil.getProperties("common.properties");
		password = p.getProperty("redis.pass");
		timeout = Integer.parseInt(p.get("redis.timeout").toString());

		JedisPoolConfig config = new JedisPoolConfig();
		config.setTestOnBorrow(false);
		config.setBlockWhenExhausted(true);
		jedisPool = new JedisPool(config, p.getProperty("redis.host"), Integer.valueOf(p.getProperty("redis.port")));
	}

	public static String get(String key) {
		if (key != null) {
			Jedis jedis = null;
			String value = null;
			try {
				jedis = jedisPool.getResource();
				jedis.auth(password);
				value = jedis.get(key);
				jedis.expire(key, timeout);
			} catch (Exception e) {
				// Log
				throw e;
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
			return value;
		}
		return null;
	}

	public static String set(String key, String value) {
		if (key != null) {
			Jedis jedis = null;
			try {
				jedis = jedisPool.getResource();
				jedis.auth(password);
				jedis.set(key, value);
				jedis.expire(key, timeout);
			} catch (Exception e) {
				// Log
				throw e;
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
			return value;
		}
		return null;
	}

	public static String hget(String key, String field) {
		if (key != null) {
			Jedis jedis = null;
			String value = null;
			try {
				jedis = jedisPool.getResource();
				jedis.auth(password);
				value = jedis.hget(key, field);
			} catch (Exception e) {
				// Log
				throw e;
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
			return value;
		}
		return null;
	}

	public static Long del(String key) {
		if (key != null) {
			Jedis jedis = null;
			try {
				jedis = jedisPool.getResource();
				jedis.auth(password);
				return jedis.del(key);
			} catch (Exception e) {
				// Log
				throw e;
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		}
		return null;
	}

	public static void sadd(String key, final String... members) {
		if (key != null) {
			Jedis jedis = null;
			try {
				jedis = jedisPool.getResource();
				jedis.auth(password);
				jedis.sadd(key, members);
			} catch (Exception e) {
				// Log
				throw e;
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		}
	}

	public static Set<String> smembers(String key) {
		if (key != null) {
			Jedis jedis = null;
			try {
				jedis = jedisPool.getResource();
				jedis.auth(password);
				return jedis.smembers(key);
			} catch (Exception e) {
				// Log
				throw e;
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		}
		return null;
	}

	public static void hset(String key, String field, String value) {
		if (key != null) {
			Jedis jedis = null;
			try {
				jedis = jedisPool.getResource();
				jedis.auth(password);
				jedis.hset(key, field, value);
			} catch (Exception e) {
				// Log
				throw e;
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		}
	}

	public static Long hdel(String key, String field) {
		if (key != null) {
			Jedis jedis = null;
			try {
				jedis = jedisPool.getResource();
				jedis.auth(password);
				return jedis.hdel(key, field);
			} catch (Exception e) {
				// Log
				throw e;
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		}
		return null;
	}
}
