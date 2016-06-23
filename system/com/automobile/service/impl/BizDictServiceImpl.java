package com.automobile.service.impl;

import java.util.List;
import java.util.Map;

import com.automobile.bean.BizDict;
import com.automobile.dao.BizDictDao;
import com.automobile.service.BizDictService;

public class BizDictServiceImpl implements BizDictService {
	private BizDictDao bizDictDao;
	public BizDictDao getBizDictDao() {
		return bizDictDao;
	}
	public void setBizDictDao(BizDictDao bizDictDao) {
		this.bizDictDao = bizDictDao;
	}
	/**
	 * 获取业务字典集合
	 * @param param
	 * @return
	 */
	@Override
	public List<Map> getBizDictList(BizDict bizdict) {
		// TODO Auto-generated method stub
		return bizDictDao.getBizDictList(bizdict);
	}
	
	/**
	 * 新增业务字典
	 * @param bizdict
	 * @return
	 */
	public String insertBizDict(BizDict bizdict){
		return bizDictDao.insertBizDict(bizdict);
	}
	
	/**
	 * 更新业务字典
	 * @param bizdict
	 * @return
	 */
	public String updateBizDict(BizDict bizdict){
		return bizDictDao.updateBizDict(bizdict);
	}

}
