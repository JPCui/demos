package com.it_sucre;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.JavaType;

public class JacksonUtil {

	private static Logger logger = Logger.getLogger(JacksonUtil.class);

	private static ObjectMapper objectMapper;

	public static ObjectMapper me() {
		return objectMapper;
	}

	static {
		objectMapper = new ObjectMapper();
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		objectMapper
				.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper
				.configure(
						DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
						true);
		objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,
				false);
		// objectMapper.configure(SerializationConfig.Feature.WRITE_NULL_PROPERTIES,
		// false);
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,
				true);

		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}

	public static void filter(String filterName, String... properties) {
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter(
				filterName,
				SimpleBeanPropertyFilter.serializeAllExcept(properties));
		objectMapper.setFilters(filterProvider);
	}

	// 混入注解
	public static void addMixInAnnotations(Class<?> target, Class<?> mixinSource) {
		objectMapper.getSerializationConfig().addMixInAnnotations(target,
				mixinSource);
		objectMapper.getDeserializationConfig().addMixInAnnotations(target,
				mixinSource);
	}

	public static void setDateFormate(DateFormat dateFormat) {
		objectMapper.setDateFormat(dateFormat);
	}

	/**
	 * @deprecated use {@link #fromJsonToObj(String, Class)} instead
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T toObj(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("解析json错误");
		}
	}

	public static <T> T fromJsonToObj(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("解析json错误");
		}
	}

	public static String toJson(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("解析对象错误");
		}
	}

	private static org.codehaus.jackson.type.JavaType getCollectionType(
			Class<?> collectionClass, Class<?>... elementClasses) {
		return objectMapper.getTypeFactory().constructParametricType(
				collectionClass, elementClasses);
	}

	/**
	 * @deprecated use {@link #toList(String, Class)} instead
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> toList(String json, Class<T> clazz) {
		JavaType javaType = getCollectionType(ArrayList.class, clazz);
		try {
			return objectMapper.readValue(json, javaType);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("解析json错误");
		}
	}

	/**
	 * 默认返回 {@link ArrayList} 结果；<br>
	 * 如果要返回其他类型，use {@link #fromBytesToList(byte[], Class, Class)} instead
	 * 
	 * @param bytes
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> fromBytesToList(byte[] bytes, Class<T> clazz) {
		JavaType javaType = getCollectionType(ArrayList.class, clazz);
		try {
			return objectMapper.readValue(bytes, javaType);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("解析json错误");
		}
	}

	/**
	 * 指定返回的集合泛型类型
	 * 
	 * @param bytes
	 * @param listType
	 *            集合泛型类型
	 * @param clazz
	 *            泛型参数类型
	 * @return
	 * 
	 * @see List
	 * @see Map
	 */
	public static <T, C> List<T> fromBytes(byte[] bytes, Class<C> genericType,
			Class<T> clazz) {
		if (!genericType.equals(Collection.class)) {
			if (!genericType.equals(Map.class)) {
				logger.warn("unknown type(" + genericType + ")");
			}
		}
		JavaType javaType = getCollectionType(genericType, clazz);
		try {
			return objectMapper.readValue(bytes, javaType);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("解析json错误");
		}
	}

	/**
	 * @deprecated use {@link #fromJsonToMap(String, Class)} instead
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> toMap(String json, Class<T> clazz) {
		JavaType javaType = objectMapper.getTypeFactory()
				.constructParametricType(Map.class, clazz);
		try {
			return objectMapper.readValue(json, javaType);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("解析json错误");
		}
	}

	public static <T> List<T> fromJsonToMap(String json, Class<T> clazz) {
		JavaType javaType = objectMapper.getTypeFactory()
				.constructParametricType(Map.class, clazz);
		try {
			return objectMapper.readValue(json, javaType);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("解析json错误");
		}
	}

	public static byte[] toBytes(Object obj) {
		try {
			return objectMapper.writeValueAsBytes(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("解析对象错误");
		}
	}

	public static <T> T fromBytes(byte[] rsBytes, Class<T> clazz) {
		try {
			T t = objectMapper.readValue(rsBytes, clazz);
			return t;
		} catch (NullPointerException e) {
		} catch (JsonParseException e) {
			logger.error("Json parse error : " + e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}