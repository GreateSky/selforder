package com.selforder.dao;

import java.util.List;

import com.selforder.bean.Shop;

/**
 * 门店管理dao
 * @author xingwanzhao
 *
 * 2016-5-16
 */
public interface ShopDao {
	/**
	 * 保存门店信息
	 * @param Shop
	 * @return
	 */
	public String saveShop(Shop shop);
	
	/**
	 * 获取门店列表
	 * @param Shop
	 * @return
	 */
	public List<Shop> ShopList(Shop shop);
	
	/**
	 * 查询门店统计数
	 * @param Shop
	 * @return
	 */
	public int getShopCount(Shop shop);
	
	/**
	 * 获取门店详情
	 * @param Shop
	 * @return
	 */
	public Shop ShopInfo(Shop shop);
	
	/**
	 * 更新门店基本信息
	 * @param Shop
	 * @return
	 */
	public int updateShop(Shop shop);
}
