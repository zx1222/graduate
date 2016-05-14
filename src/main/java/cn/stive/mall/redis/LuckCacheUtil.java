package cn.stive.mall.redis;

import cn.stive.mall.exception.RedisException;
import cn.stive.mall.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.List;


public class LuckCacheUtil {


	/*
	 * 用户缓存结构 HSET
	 */
	public final static String KEY_HSET_USER = "hset:user:";
	public final static String KEY_HSET_USER_FIELD_USERINFO = "userinfo";
	public final static String KEY_HSET_USER_FIELD_TOKEN = "token";

	// 防止刷验证码
	public final static String KEY_HSET_PHONE_LAST_VERFIY_CODE_TIME = "hset:phone:sms:lasttime";

	private static String getRootUserKey(long user_id) {
		return KEY_HSET_USER + user_id;
	}

	private final static Logger LOG = LoggerFactory.getLogger(JedisUtil.class);

	public static long incr(String key) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			return jedis.incr(key);
		} catch (Exception e) {
			LOG.error("", e);
			throw new RedisException();
		} finally {
			if (jedis != null) {
				JedisFactory.getInstance().release(jedis);
			}
		}
	}

	public static void sadd(String key, String item) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			jedis.sadd(key, item);
		} catch (Exception e) {
			LOG.error("", e);
			throw new RedisException();
		} finally {
			if (jedis != null) {
				JedisFactory.getInstance().release(jedis);
			}
		}
	}

	public static String get(String key) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			return jedis.get(key);
		} catch (Exception e) {
			LOG.error("", e);
			throw new RedisException();
		} finally {
			if (jedis != null) {
				JedisFactory.getInstance().release(jedis);
			}
		}
	}

	public static void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			jedis.set(key, value);
		} catch (Exception e) {
			LOG.error("", e);
			throw new RedisException();
		} finally {
			if (jedis != null) {
				JedisFactory.getInstance().release(jedis);
			}
		}
	}

	public static void setex(String key, int expr, String value) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			jedis.setex(key, expr, value);
		} catch (Exception e) {
			LOG.error("", e);
			throw new RedisException();
		} finally {
			if (jedis != null) {
				JedisFactory.getInstance().release(jedis);
			}
		}
	}

	public static List<String> lrange(String key, int start, int end) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			return jedis.lrange(key, start, end);
		} catch (Exception e) {
			LOG.error("", e);
			throw new RedisException();
		} finally {
			if (jedis != null) {
				JedisFactory.getInstance().release(jedis);
			}
		}
	}

	public static void lpush(String key, Object obj) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			jedis.lpush(key, JsonUtil.toJson(obj));
		} catch (Exception e) {
			LOG.error("", e);
			throw new RedisException();
		} finally {
			if (jedis != null) {
				JedisFactory.getInstance().release(jedis);
			}
		}
	}

	public static String lpop(String key) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			return jedis.lpop(key);
		} catch (Exception e) {
			LOG.error("", e);
			throw new RedisException();
		} finally {
			if (jedis != null) {
				JedisFactory.getInstance().release(jedis);
			}
		}
	}

	public static String hget(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			return jedis.hget(key, field);
		} catch (Exception e) {
			LOG.error("", e);
			throw new RedisException();
		} finally {
			if (jedis != null) {
				JedisFactory.getInstance().release(jedis);
			}
		}
	}

	public static void hset(String key, String field, String val) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			jedis.hset(key, field, val);
		} catch (Exception e) {
			LOG.error("", e);
			throw new RedisException();
		} finally {
			if (jedis != null) {
				JedisFactory.getInstance().release(jedis);
			}
		}
	}

	public static void hdel(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			jedis.hdel(key, field);
		} catch (Exception e) {
			LOG.error("", e);
			throw new RedisException();
		} finally {
			if (jedis != null) {
				JedisFactory.getInstance().release(jedis);
			}
		}
	}

	public static boolean sismember(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			return jedis.sismember(key, member);
		} catch (Exception e) {
			LOG.error("", e);
			throw new RedisException();
		} finally {
			if (jedis != null) {
				JedisFactory.getInstance().release(jedis);
			}
		}
	}

	public static long scard(String key) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			return jedis.scard(key);
		} catch (Exception e) {
			LOG.error("", e);
			throw new RedisException();
		} finally {
			if (jedis != null) {
				JedisFactory.getInstance().release(jedis);
			}
		}
	}

	public static String getToken(long user_id) {
		String key = KEY_HSET_USER + user_id;
		String field = KEY_HSET_USER_FIELD_TOKEN;
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getUserSource();
			return jedis.hget(key, field);
		} catch (Exception e) {
			LOG.error("", e);
			throw new RuntimeException();
		} finally {
			if (jedis != null) {
				JedisFactory.getInstance().releaseUser(jedis);
			}
		}
	}



}
