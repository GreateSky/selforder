package com.selforder.dao;

import java.util.List;

import com.selforder.bean.Business;

/**
 * 商户管理dao
 * @author xingwanzhao
 *
 * 2016-5-16
 */
public interface BusinessDao {
	/**
	 * 保存商户信息
	 * @param business
	 * @return
	 */
	public String saveBusiness(Business business);
	
	/**
	 * 获取商户列表
	 * @param business
	 * @return
	 */
	public List<Business> businessList(Business business);
	
	/**
	 * 查询商户统计数
	 * @param business
	 * @return
	 */
	public int getBusinessCount(Business business);
	
	/**
	 * 获取商户详情
	 * @param business
	 * @return
	 */
	public Business businessInfo(Business business);
	
	/**
	 * 更新商户基本信息
	 * @param business
	 * @return
	 */
	public int updateBusiness(Business business);
}
