package com.automobile.listener;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 存放systemConfig 的属性及值
 * @author Stephen.dong
 *
 */
public class SystemConfig implements Serializable{

	private static final Map<String,String> config = new HashMap<String,String>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 7972326004841396483L;
	
	/**
	 * 初始化属性值
	 * @param properties
	 */
	public static void config(Properties properties){
		Set<String> propertyNames=properties.stringPropertyNames();
		for(String propertyName:propertyNames){
			config.put(propertyName,properties.getProperty(propertyName));
		}
	}

	/**
	 * 获取属性值
	 * @param key
	 * @return
	 */
	public static String get(String key){
		return config.get(key);
	}
}
