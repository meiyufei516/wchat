package com.myf.wchat.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author MeiYF
 * @time 2018/11/30 17:02
 **/
public class ObjectMapperUtil {

	public static final ObjectMapper mapper=new ObjectMapper();


	/**
	 * 字符串转Map
	 * @param jsonStr
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public static Map stringToMap(String jsonStr,Map map) throws IOException {
		return mapper.readValue(jsonStr, Map.class);
	}

	/**
	 * Map转为字符串
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public static String mapToString(Map map) throws IOException {
		return mapper.writeValueAsString(map);
	}

	/**
	 * 字符串转集合
	 * @param jsonStr
	 * @param list
	 * @return
	 * @throws IOException
	 */
	public static List stringToList(String jsonStr,List list) throws IOException {
		return mapper.readValue(jsonStr, List.class);
	}

	/**
	 * 集合转为字符串
	 * @param list
	 * @return
	 * @throws IOException
	 */
	public static String listToString(List list) throws IOException {
		return mapper.writeValueAsString(list);
	}


	/**
	 * byte数组转对象
	 * @param byteArr
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static Object arrayToObject(byte[] byteArr,Object object) throws IOException {
		return mapper.readValue(byteArr,Object.class);
	}

	/**
	 * 对象转byte数组
	 * @param object
	 * @return
	 * @throws JsonProcessingException
	 */
	public static byte[] objctToArray(Object object) throws JsonProcessingException {
		return mapper.writeValueAsBytes(object);
	}

	/**
	 * json转对象
	 * @param json
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static Object jsonToObject(String json,Object object) throws IOException {
		return mapper.readValue(json,Object.class);
	}

	/**
	 * 对象转json
	 * @param object
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String objctToJson(Object object) throws JsonProcessingException {
		return mapper.writeValueAsString(object);
	}

	/**
	 * 把对象写到本地文件
	 * @param localPath
	 * @param object
	 * @throws IOException
	 */
	public static void writeObjectToLocal(String localPath,Object object) throws IOException {
		mapper.writeValue(new File(localPath), object);
	}


}
