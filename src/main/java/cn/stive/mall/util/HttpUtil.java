package cn.stive.mall.util;

import com.mysql.jdbc.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class HttpUtil {

	private final static Logger LOG = LoggerFactory.getLogger(HttpUtil.class);

	private static int HTTP_MAX_TOTAL;
	private static int HTTP_MAX_ROUTE_TOTAL;
	private static int HTTP_REQUEST_TIMEOUT;
	private static int HTTP_REQUEST_SOCKET_TIMEOUT;

	private static CloseableHttpClient HTTP_CLIENT;

	public static void init(int httpMaxTotal, int httpMaxRouteTotal,
			int httpRequestTimeout, int httpRequestSocketTime) {

		HTTP_MAX_TOTAL = httpMaxTotal;
		HTTP_MAX_ROUTE_TOTAL = httpMaxRouteTotal;
		HTTP_REQUEST_TIMEOUT = httpRequestTimeout;
		HTTP_REQUEST_SOCKET_TIMEOUT = httpRequestSocketTime;

		PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
		httpClientConnectionManager.setMaxTotal(HTTP_MAX_TOTAL); // 设置连接池线程最大数量
		httpClientConnectionManager.setDefaultMaxPerRoute(HTTP_MAX_ROUTE_TOTAL); // 设置单个路由最大的连接线程数量
		// 创建http request的配置信息
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(HTTP_REQUEST_TIMEOUT)
				.setSocketTimeout(HTTP_REQUEST_SOCKET_TIMEOUT).build();
		// 设置重定向策略
		LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
		// 初始化httpclient客户端
		HTTP_CLIENT = HttpClients
				.custom()
				.setConnectionManager(httpClientConnectionManager)
				.setDefaultRequestConfig(requestConfig)
				.setUserAgent(
						"Mozilla/5.0 (X11; Linux x86_64; rv:28.0) Gecko/20100101 Firefox/28.0")
				.setRedirectStrategy(redirectStrategy).build();
	}

	public static String post(String url, String content) {

		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		long beginTime = System.currentTimeMillis();
		try {
			httpPost.addHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=utf8");
			httpPost.setEntity(new StringEntity(content, "UTF-8"));
			// 执行请求
			response = HTTP_CLIENT.execute(httpPost);
			System.out.println(response);

			HttpEntity entity = response.getEntity();
			String resTxt = in(entity);
			EntityUtils.consume(entity);
			return resTxt;
		} catch (Exception e) {
			LOG.error("", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					LOG.error("", e);
				}
			}
		}
		long endTime = System.currentTimeMillis();
		LOG.info("http post time " + (endTime - beginTime));
		return null;
	}

	private static String in(HttpEntity entity) throws IllegalStateException,
			IOException {
		final InputStream instream = entity.getContent();
		if (instream == null) {
			return null;
		}
		try {
			int i = (int) entity.getContentLength();
			if (i < 0) {
				i = 4096;
			}
			final Reader reader = new InputStreamReader(instream, "UTF-8");
			final CharArrayBuffer buffer = new CharArrayBuffer(i);
			final char[] tmp = new char[1024];
			int l;
			while ((l = reader.read(tmp)) != -1) {
				buffer.append(tmp, 0, l);
			}
			return buffer.toString();
		} finally {
			instream.close();
		}
	}

	public static String get(String url) {

		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		long beginTime = System.currentTimeMillis();
		try {
			// 执行请求
			response = HTTP_CLIENT.execute(httpGet);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			LOG.error("", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					LOG.error("", e);
				}
			}
		}
		long endTime = System.currentTimeMillis();
		LOG.info("http get time " + (endTime - beginTime));
		return null;
	}

	public static void release() {
		if (HTTP_CLIENT == null) {
			return;
		}
		try {
			HTTP_CLIENT.close();
		} catch (IOException e) {
			LOG.error("", e);
		}
	}

	public static String postXml(String url, String xml) {
		return post(url, xml, "application/x-www-form-urlencoded; charset="
				+ DEFAULT_CHARSET);
	}

	public static String postForm(String url, String xml) {
		return post(url, xml, "text/xml; charset=" + DEFAULT_CHARSET);
	}

	public static String post(String url, String content, String contentType) {

		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		long beginTime = System.currentTimeMillis();
		try {
			httpPost.addHeader("Content-Type", contentType);
			httpPost.setEntity(new StringEntity(content, DEFAULT_CHARSET));
			// 执行请求
			response = HTTP_CLIENT.execute(httpPost);

			HttpEntity entity = response.getEntity();
			String resTxt = in(entity);
			EntityUtils.consume(entity);
			return resTxt;
		} catch (Exception e) {
			LOG.error("", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					LOG.error("", e);
				}
			}
		}
		long endTime = System.currentTimeMillis();
		LOG.info("http post time " + (endTime - beginTime));
		return null;
	}

	private final static String DEFAULT_CHARSET = "UTF-8";

	public static String urldecode(String content) {
		try {
			return urldecode(content, DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			LOG.error("", e);
		}
		return null;
	}

	public static String urlencode(String content) {
		try {
			return urlencode(content, DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			LOG.error("", e);
		}
		return null;
	}

	public static String urldecode(String content, String charset)
			throws UnsupportedEncodingException {
		if (StringUtils.isEmptyOrWhitespaceOnly(content)) {
			return "";
		}
		return URLDecoder.decode(content, charset);
	}

	public static String urlencode(String content, String charset)
			throws UnsupportedEncodingException {
		if (StringUtils.isEmptyOrWhitespaceOnly(content)) {
			return "";
		}
		return URLEncoder.encode(content, charset);
	}
}
