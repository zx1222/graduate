package cn.stive.mall.redis;

import cn.stive.mall.bean.User;
import cn.stive.mall.util.CommonUtil;
import cn.stive.mall.util.JsonUtil;
import com.mysql.jdbc.StringUtils;

public class UserCacheUtil {

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

	/**
	 * 缓存可见用户基本信息
	 * 
	 * @param user
	 */
	public static void setUser(User user_info) {
		String key = getRootUserKey(user_info.getId());
		String field = KEY_HSET_USER_FIELD_USERINFO;
		JedisUtil.hset(key, field, JsonUtil.toJson(user_info));
	}

	public static User getUser(long user_id) {
		String key = getRootUserKey(user_id);
		String field = KEY_HSET_USER_FIELD_USERINFO;
		String json = JedisUtil.hget(key, field);
		if (StringUtils.isEmptyOrWhitespaceOnly(json)) {
			return null;
		} else {
			User user_info = JsonUtil.toObject(json, User.class);
			if(user_info == null || user_info.getId() == 0){
				// 2015-10-14
				// 设计失误- > 缓存结构改变 -> 解析异常 -> 清空缓存
				cleanUser(user_id);
			}
			return user_info;
		}
	}

	/**
	 * 保存手机最后一次发送验证码的时间。防止手机频繁发送验证码
	 * 
	 * @param phone
	 * @param time
	 *            单位秒
	 */
	public static void setLastVerifyCodeTime(String phone, long time) {
		JedisUtil.hset(KEY_HSET_PHONE_LAST_VERFIY_CODE_TIME, phone, time + "");
	}

	public static long getLastVerifyCodeTime(String phone) {
		String value = JedisUtil.hget(KEY_HSET_PHONE_LAST_VERFIY_CODE_TIME,
				phone);
		if (StringUtils.isEmptyOrWhitespaceOnly(value)) {
			return 0;
		}
		return CommonUtil.toLong(value, 0);
	}

	public static String getServerToken(long user_id) {
		String key = getRootUserKey(user_id);
		String field = KEY_HSET_USER_FIELD_TOKEN;
		return JedisUtil.hget(key, field);
	}

	public static void setServerToken(long user_id, String token) {
		String key = getRootUserKey(user_id);
		String field = KEY_HSET_USER_FIELD_TOKEN;
		JedisUtil.hset(key, field, token);
	}

	public static void cleanServerToken(long user_id) {
		String key = getRootUserKey(user_id);
		String field = KEY_HSET_USER_FIELD_TOKEN;
		JedisUtil.hdel(key, field);
	}
	
	public static void cleanUser(long user_id){
		String key = getRootUserKey(user_id);
		String field = KEY_HSET_USER_FIELD_USERINFO;
		JedisUtil.hdel(key, field);
	}
	
}
