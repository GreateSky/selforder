package com.selforder.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 返回json格式结果集工具类
 * @author xingwanzhao
 *
 * 2016-7-22
 */
public class JsonResultUtil {
	/**
	 * 返回通用json格式结果集
	 * @param retCode	返回编码
	 * @param status	返回状态
	 * @param message	返回文本描述
	 * @return	返回将参数组装成json格式的字符串
	 */
	public static String getJsonResult(int retCode,String status,String message){
		Map param = new HashMap();
		param.put("retCode",retCode);
		param.put("status",status);
		param.put("message",message);
		JSONObject paramjson = JSONObject.fromObject(param);
		return paramjson.toString();
	}
	
	/**
	 * 返回通用json格式结果集
	 * @param retCode	返回编码
	 * @param status	返回状态
	 * @param message	返回文本描述
	 * @param data		返回的自定义参数
	 * @return	返回将参数组装成json格式的字符串
	 */
	public static String getJsonResult(int retCode,String status,String message,Map data){
		Map param = new HashMap();
		param.put("retCode",retCode);
		param.put("status",status);
		param.put("message",message);
		param.put("data", data);
		JSONObject paramjson = JSONObject.fromObject(param);
		return paramjson.toString();
	}
	
	/**
	 * 返回通用json格式结果集
	 * @param retCode	返回编码
	 * @param status	返回状态
	 * @param message	返回文本描述
	 * @param data		返回的自定义参数
	 * @return	返回将参数组装成json格式的字符串
	 */
	public static String getJsonResult(int retCode,String status,String message,Object data){
		Map param = new HashMap();
		param.put("retCode",retCode);
		param.put("status",status);
		param.put("message",message);
		param.put("data", data);
		JSONObject paramjson = JSONObject.fromObject(param);
		return paramjson.toString();
	}	
	
	/**
	 * 返回通用json格式结果集
	 * @param retCode	返回编码
	 * @param status	返回状态
	 * @param message	返回文本描述
	 * @param data		返回的自定义参数
	 * @return	返回将参数组装成json格式的字符串
	 */
	public static String getJsonResult(int retCode,String status,String message,List data){
		Map param = new HashMap();
		param.put("retCode",retCode);
		param.put("status",status);
		param.put("message",message);
		param.put("data", data);
		JSONObject paramjson = JSONObject.fromObject(param);
		return paramjson.toString();
	}	
	
	/**
	 * 对象转json串
	 * @param obj
	 * @return
	 */
	public  static String ObjectToJsonStr(Object obj){
		JSONObject objectjson = JSONObject.fromObject(obj);
		return objectjson.toString();
	}
	
	/**
	 * Map转json串
	 * @param Map
	 * @return
	 */
	public  static String MapToJsonStr(Map param){
		JSONObject objectjson = JSONObject.fromObject(param);
		return objectjson.toString();
	}
	
	/**
	 * List转json串
	 * @param List
	 * @return
	 */
	public  static String ArrayListToJsonStr(List arraylist){
		String result = "";
		if(arraylist !=null && arraylist.size()> 0){
			JSONArray jsonarray = JSONArray.fromObject(arraylist);
			result = jsonarray.toString();
		}
		return result;
	}
}
