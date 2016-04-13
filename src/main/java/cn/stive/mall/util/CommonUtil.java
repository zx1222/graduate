package cn.stive.mall.util;

import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommonUtil {

	private final static Logger LOG = LoggerFactory.getLogger(CommonUtil.class);

	/**
	 * format ： yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            must be not null
	 * @return
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static boolean isMobile(String phone) {
		return true;
	}

	public static boolean emptyList(List<?> list) {
		return list == null || list.isEmpty();
	}

	public static boolean nonEmptyList(List<?> list) {
		return !emptyList(list);
	}

	public static int toInt(String val) {
		return toInt(val, 0);
	}

	public static int toInt(String val, int defVal) {
		if (StringUtils.isEmptyOrWhitespaceOnly(val)) {
			return defVal;
		}
		try {
			return Integer.parseInt(val);
		} catch (Exception e) {
			LOG.error("", e);
		}
		return defVal;
	}

	public static double toDouble(String val) {
		return toDouble(val, 0);
	}

	public static double toDouble(String val, double defVal) {
		if (val == null) {
			return defVal;
		}
		try {
			return Double.parseDouble(val);
		} catch (Exception e) {
			LOG.error("", e);
		}
		return defVal;
	}

	public static long toLong(String val) {
		return toLong(val, 0);
	}

	public static long toLong(String val, long defVal) {
		if (StringUtils.isEmptyOrWhitespaceOnly(val)) {
			return defVal;
		}
		try {
			return Long.parseLong(val);
		} catch (Exception e) {
			LOG.error("", e);
		}
		return defVal;
	}

	public static String urldecode(String content) {
		if (StringUtils.isEmptyOrWhitespaceOnly(content)) {
			return "";
		}
		try {
			return URLDecoder.decode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error("编码错误, 只支持UTF-8格式. " + content, e);
			throw new RuntimeException();
		}
	}
	
	public static String urlencode(String content) {
		if (StringUtils.isEmptyOrWhitespaceOnly(content)) {
			return "";
		}
		try {
			return URLEncoder.encode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error("编码错误, 只支持UTF-8格式. " + content, e);
			throw new RuntimeException();
		}
	}
}
