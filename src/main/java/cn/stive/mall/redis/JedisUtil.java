package cn.stive.mall.redis;

import cn.stive.mall.Exceptions.RedisException;
import cn.stive.mall.util.JsonUtil;
import com.mysql.jdbc.StringUtils;
import com.mysql.jdbc.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class JedisUtil {

	public static long incr(String key) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			return jedis.incr(key);
		} finally {
			release(jedis);
		}
	}

	public static void sadd(String key, Object data) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			String json = JsonUtil.toJson(data);
			jedis.sadd(key, json);
		} finally {
			release(jedis);
		}
	}

	public static boolean sismember(String key, Object data) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			String json = JsonUtil.toJson(data);
			return jedis.sismember(key, json);
		} finally {
			release(jedis);
		}
	}

	public static void setex(String key, int expr, Object data) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			String json = JsonUtil.toJson(data);
			jedis.setex(key, expr, json);
		} finally {
			release(jedis);
		}
	}

	public static void lpush(String key, Object obj) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			jedis.lpush(key, JsonUtil.toJson(obj));
		} finally {
			release(jedis);
		}
	}

	public static void rpush(String key, Object obj) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			jedis.rpush(key, JsonUtil.toJson(obj));
		} finally {
			release(jedis);
		}
	}

	public static <T> T hget(String key, String field, Class<T> classOfT) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			String json = jedis.hget(key, field);
			if (StringUtils.isEmptyOrWhitespaceOnly(json)) {
				return null;
			}
			return JsonUtil.fromJson(json, classOfT);
		} finally {
			release(jedis);
		}
	}
	public static String hget(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			return jedis.hget(key, field);
		} catch (Exception e) {
			throw new RedisException();
		} finally {
			if (jedis != null) {
				JedisFactory.getInstance().release(jedis);
			}
		}
	}

	public static void hset(String key, String field, Object data) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			String json = JsonUtil.toJson(data);
			jedis.hset(key, field, json);
		} finally {
			release(jedis);
		}
	}

	public static void hdel(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			jedis.hdel(key, field);
		} finally {
			release(jedis);
		}
	}

	public static long scard(String key) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			return jedis.scard(key);
		} finally {
			release(jedis);
		}
	}

	public static void del(String key) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			jedis.del(key);
		} finally {
			release(jedis);
		}
	}

	public static void hincrByFloat(String key, String field, double score) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			jedis.hincrByFloat(key, field, score);
		} finally {
			release(jedis);
		}
	}

	public static void expire(String key, int second) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			jedis.expire(key, second);
		} finally {
			release(jedis);
		}
	}

	public static void set(String key, Object obj) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			jedis.set(key, JsonUtil.toJson(obj));
		} finally {
			release(jedis);
		}
	}

	public static <T> T get(String key, Class<T> classOfT) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			String json = jedis.get(key);
			if (StringUtils.isEmptyOrWhitespaceOnly(json)) {
				return null;
			}
			return JsonUtil.fromJson(json, classOfT);
		} finally {
			release(jedis);
		}
	}

	public static void rpush(String key, List<?> list) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			String[] strs = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				strs[i] = JsonUtil.toJson(list.get(i));
			}
			jedis.rpush(key, strs);
		} finally {
			release(jedis);
		}
	}

	public static void lpush(String key, List<?> list) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			String[] strs = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				strs[i] = JsonUtil.toJson(list.get(i));
			}
			jedis.lpush(key, strs);
		} finally {
			release(jedis);
		}
	}

	public static <T> T lpop(String key, Class<T> classOfT) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			String json = jedis.lpop(key);
			if (StringUtils.isEmptyOrWhitespaceOnly(json)) {
				return null;
			}
			return JsonUtil.fromJson(json, classOfT);
		} finally {
			release(jedis);
		}
	}

	public static long hincr(String key, String field, int incr) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			return jedis.hincrBy(key, field, incr);
		} finally {
			release(jedis);
		}
	}

	public static <T> List<T> lrange(String key, int start, int end,
			Class<T> classOfT) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			List<String> jsons = jedis.lrange(key, start, end);
			if (jsons == null || jsons.isEmpty()) {
				return null;
			}

			List<T> list = new ArrayList<T>();
			for (String json : jsons) {
				T t = JsonUtil.fromJson(json, classOfT);
				list.add(t);
			}
			return list;
		} finally {
			release(jedis);
		}
	}

	public static int llen(String key) {
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			Long size = jedis.llen(key);
			return size == null ? 0 : size.intValue();
		} finally {
			release(jedis);
		}
	}
	
	public static void ltrim(String key, int start, int end){
		Jedis jedis = null;
		try {
			jedis = JedisFactory.getInstance().getSource();
			jedis.ltrim(key, start, end);
		} finally {
			release(jedis);
		}
	}

	public static void release(Jedis jedis) {
		if (jedis != null) {
			JedisFactory.getInstance().release(jedis);
		}
	}
}
