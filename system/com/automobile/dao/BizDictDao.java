package com.automobile.dao;

import java.util.List;
import java.util.Map;

import com.automobile.bean.BizDict;

public interface BizDictDao {
	/**
	 * 获取业务字典集合
	 * @param param
	 * @return
	 */
	public List<Map> getBizDictList(BizDict bizdict);
	
	/**
	 * 新增业务字典
	 * @param bizdict
	 * @return
	 */
	public String insertBizDict(BizDict bizdict);
	
	/**
	 * 更新业务字典
	 * @param bizdict
	 * @return
	 */
	public String updateBizDict(BizDict bizdict);
}
