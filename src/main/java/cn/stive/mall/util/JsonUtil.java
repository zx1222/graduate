package cn.stive.mall.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;

import java.io.IOException;

public class JsonUtil {

	private final static Gson GSON = new GsonBuilder().setDateFormat(
			"yyyy-MM-dd HH:mm:ss SSS").create();

	private final static ObjectMapper objectMapper = new ObjectMapper();

	public static Gson getGson(){
		return GSON;
	}
	public static String toJson(Object obj) {
		return GSON.toJson(obj);
	}

	public static <T> T fromJson(String json, Class<T> classOfT) {
		return GSON.fromJson(json, classOfT);
	}

	public static <T> T toObject(String json, Class<T> c) {
		ObjectReader or = objectMapper.reader(c);
		try {
			return or.readValue(json);
		} catch (JsonProcessingException e) {
		} catch (IOException e) {
		}
		return null;
	}
}