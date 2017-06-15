package com.ds365.commons.json;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.ds365.commons.utils.L;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonUtils {
	


		/** ObjectMapper */
		private static ObjectMapper mapper = new ObjectMapper();

		static {
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false); 
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}
		
		/**
		 * 不可实例化
		 */
		private JacksonUtils() {
		}

		/**
		 * 将对象转换为JSON字符串
		 * 
		 * @param value
		 *            对象
		 * @return JSOn字符串
		 */
		public static String toJson(Object value) {
			try {
				return mapper.writeValueAsString(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 将JSON字符串转换为对象
		 * 
		 * @param json
		 *            JSON字符串
		 * @param valueType
		 *            对象类型
		 * @return 对象
		 */
		public static <T> T toObject(String json, Class<T> valueType) {
			/*Assert.hasText(json);
			Assert.notNull(valueType);*/
			try {
				return mapper.readValue(json, valueType);
			} catch (Exception e) {
				throw new RuntimeException("参数不合法!");
			}
		}

		/**
		 * 将JSON字符串转换为对象
		 * 
		 * @param json
		 *            JSON字符串
		 * @param valueType
		 *            对象类型
		 * @return 对象
		 */
		public static Object toObject(String json, String valueType) {
			/*Assert.hasText(json);
			Assert.notNull(valueType);*/
			try {
				return mapper.readValue(json, Class.forName(valueType));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 将JSON字符串转换为对象
		 * 
		 * @param json
		 *            JSON字符串
		 * @param typeReference
		 *            对象类型
		 * @return 对象
		 */
		public static <T> T toObject(String json, TypeReference<?> typeReference) {
			/*Assert.hasText(json);
			Assert.notNull(typeReference);*/
			try {
				return mapper.readValue(json, typeReference);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 将JSON字符串转换为对象
		 * 
		 * @param json
		 *            JSON字符串
		 * @param javaType
		 *            对象类型
		 * @return 对象
		 */
		public static <T> T toObject(String json, JavaType javaType) {
			/*Assert.hasText(json);
			Assert.notNull(javaType);*/
			try {
				return mapper.readValue(json, javaType);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 将对象转换为JSON流
		 * 
		 * @param writer
		 *            writer
		 * @param value
		 *            对象
		 */
		public static void writeValue(Writer writer, Object value) {
			try {
				mapper.writeValue(writer, value);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * String转化为map
		 */
		public static Map stringToMap(String str){
			 Map<String, String> map;
			 map = toObject(str,Map.class);
			 return map;
		}
		
		/**
		 * Object转化为Map
		 */
		public static Map objectToMap(Object obj){
			 Map<String, String> map;
			 
			 String str=toJson(obj);					 
			 map = toObject(str,Map.class);
			 return map;
		}
	}
