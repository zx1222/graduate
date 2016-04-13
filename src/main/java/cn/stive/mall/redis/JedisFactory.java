package cn.stive.mall.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisFactory {

	private final static Logger LOG = LoggerFactory
			.getLogger(JedisFactory.class);

	private static JedisFactory instance;

	private JedisPool luck_pool;

	private JedisPool user_pool;

	public static JedisFactory getInstance() {
		if (instance == null) {
			synchronized (JedisFactory.class) {
				if (instance == null) {
					instance = new JedisFactory();
				}
			}
		}
		return instance;
	}

	private JedisFactory() {
		initLuckPool();
		initUserPool();
	}

	private void initLuckPool() {
		// 加载redis配置文件
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		if (bundle == null) {
			LOG.error("redis.properties is not found!");
			throw new IllegalArgumentException("redis.properties is not found!");
		}
		// 创建jedis池配置实例
		JedisPoolConfig pool_config = new JedisPoolConfig();
		pool_config.setMaxIdle(Integer.valueOf(bundle
				.getString("redis.pool.maxIdle")));
		LOG.info("poolConfig.maxIdle " + pool_config.getMaxIdle());
		pool_config.setMaxTotal(Integer.valueOf(bundle
				.getString("redis.pool.maxTotal")));
		LOG.info("poolConfig.maxTotal " + pool_config.getMaxTotal());
		pool_config.setTestOnBorrow(Boolean.valueOf(bundle
				.getString("redis.pool.testOnBorrow")));
		LOG.info("poolConfig.testOnBorrow " + pool_config.getTestOnBorrow());

		String ip = bundle.getString("redis.pool.host");
		int port = Integer.valueOf(bundle.getString("redis.pool.port"));
		int timeout = Integer.valueOf(bundle.getString("redis.pool.timeout"));
		String passwd = bundle.getString("redis.pool.passwd");
		int database = Integer.valueOf(bundle.getString("redis.pool.database"));

		LOG.info("ip " + ip);
		LOG.info("port " + port);
		LOG.info("timeout " + timeout);
		LOG.info("passwd " + passwd);
		LOG.info("database " + database);

		luck_pool = new JedisPool(pool_config, ip, port, timeout, passwd,
				database);
	}

	private void initUserPool() {
		// 加载redis配置文件
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		if (bundle == null) {
			LOG.error("user_redis.properties is not found!");
			throw new IllegalArgumentException(
					"user_redis.properties is not found!");
		}
		// 创建jedis池配置实例
		JedisPoolConfig pool_config = new JedisPoolConfig();
		pool_config.setMaxIdle(Integer.valueOf(bundle
				.getString("redis.pool.maxIdle")));
		LOG.info("poolConfig.maxIdle " + pool_config.getMaxIdle());
		pool_config.setMaxTotal(Integer.valueOf(bundle
				.getString("redis.pool.maxTotal")));
		LOG.info("poolConfig.maxTotal " + pool_config.getMaxTotal());
		pool_config.setTestOnBorrow(Boolean.valueOf(bundle
				.getString("redis.pool.testOnBorrow")));
		LOG.info("poolConfig.testOnBorrow " + pool_config.getTestOnBorrow());

		String ip = bundle.getString("redis.pool.host");
		int port = Integer.valueOf(bundle.getString("redis.pool.port"));
		int timeout = Integer.valueOf(bundle.getString("redis.pool.timeout"));
		String passwd = bundle.getString("redis.pool.passwd");
		int database = Integer.valueOf(bundle.getString("redis.pool.database"));

		LOG.info("ip " + ip);
		LOG.info("port " + port);
		LOG.info("timeout " + timeout);
		LOG.info("passwd " + passwd);
		LOG.info("database " + database);
		
		user_pool = new JedisPool(pool_config, ip, port, timeout, passwd, database);
	}

	public Jedis getSource() {
		return this.luck_pool.getResource();
	}

	public void release(Jedis jedis) {
		luck_pool.returnResource(jedis);
	}

	public Jedis getUserSource() {
		return this.user_pool.getResource();
	}

	public void releaseUser(Jedis jedis) {
		user_pool.returnResource(jedis);
	}

	public void release() {
		luck_pool.destroy();
		user_pool.destroy();
	}

}