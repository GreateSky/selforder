package com.selforder.service;

import com.selforder.bean.Business;
import com.selforder.bean.Shop;

/**
 * 门店管理业务处理
 * @author xingwanzhao
 *
 * 2016-5-16
 */
public interface ShopService {
	/**
	 * 保存门店信息
	 * @param shop
	 * @return
	 */
	public String saveShop(Shop shop);
	
	/**
	 * 获取门店列表
	 * @param Shop
	 * @return
	 */
	public String shopList(Shop shop);
	
	/**
	 * 获取门店详情
	 * @param shop
	 * @return
	 */
	public String shopInfo(Shop shop);
	
	/**
	 * 更新门店基本信息
	 * @param business
	 * @return
	 */
	public String updateShop(Shop shop);
	
	/**
	 * 删除门店
	 * @param shop
	 * @return
	 */
	public String delShop(Shop shop);
}
