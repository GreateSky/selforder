package com.selforder.service;

import java.util.List;

import com.selforder.bean.Business;

/**
 * 商户管理业务处理
 * @author xingwanzhao
 *
 * 2016-5-16
 */
public interface BusinessService {
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
	public String businessList(Business business);
	
	/**
	 * 获取商户详情
	 * @param business
	 * @return
	 */
	public String businessInfo(Business business);
	
	/**
	 * 更新商户基本信息
	 * @param business
	 * @return
	 */
	public String updateBusiness(Business business);
}
