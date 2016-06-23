package com.automobile.service;

import java.util.List;
import java.util.Map;

import com.automobile.bean.Parts;

public interface SystemMgrService {
	/**
	 * 获取配件
	 * @param parts
	 * @return
	 */
	public List<Map> getPartsList(Parts parts);
	
	/**
	 * 新增配件
	 * @param parts
	 * @return
	 */
	public String insertParts(Parts parts);
	
	/**
	 * 更新配件
	 * @param parts
	 * @return
	 */
	public String updateParts(Parts parts);
	
	/**
	 * 获取微信JsApi信息
	 * @param url
	 * @return
	 */
	public Map<String,String> getJsApiInfo(String url);
}
