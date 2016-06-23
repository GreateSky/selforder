package com.automobile.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.automobile.bean.BizDict;
import com.automobile.dao.BizDictDao;
import com.automobile.util.JsonResultUtil;

public class BizDictDaoImpl extends SqlSessionDaoSupport implements BizDictDao {
	/**
	 * 获取业务字典集合
	 * @param param
	 * @return
	 */
	@Override
	public List<Map> getBizDictList(BizDict bizdict) {
		List<Map> bizdictList = new ArrayList<Map>();
		try{
			bizdictList = (List<Map>)getSqlSession().selectList("com.greatesky.systemmgr.getBizDict", bizdict);
		}catch(Exception e){
			e.printStackTrace();
		}
		return bizdictList;
	}
	
	/**
	 * 新增业务字典
	 * @param bizdict
	 * @return
	 */
	public String insertBizDict(BizDict bizdict){
		String result = "";
		try{
			int temp = getSqlSession().insert("com.greatesky.systemmgr.insertBizDict", bizdict);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "保存成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "保存失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 更新业务字典
	 * @param bizdict
	 * @return
	 */
	public String updateBizDict(BizDict bizdict){
		String result = "";
		try{
			int temp = getSqlSession().insert("com.greatesky.systemmgr.updateBizDict", bizdict);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "操作成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "操作成功！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
