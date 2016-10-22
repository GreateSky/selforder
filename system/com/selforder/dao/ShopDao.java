package com.selforder.dao;

import java.io.IOException;
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
	public String saveShop(Shop shop)throws Exception;
	
	/**
	 * 获取门店列表
	 * @param Shop
	 * @return
	 */
	public List<Shop> ShopList(Shop shop)throws Exception;
	
	/**
	 * 查询门店统计数
	 * @param Shop
	 * @return
	 */
	public int getShopCount(Shop shop)throws Exception;
	
	/**
	 * 获取门店详情
	 * @param Shop
	 * @return
	 */
	public Shop ShopInfo(Shop shop)throws Exception;
	
	/**
	 * 更新门店基本信息
	 * @param Shop
	 * @return
	 */
	public int updateShop(Shop shop)throws Exception;
	
	/**
	 * 删除门店
	 * @param shop
	 * @return
	 */
	public int delShop(Shop shop) throws Exception;
	
	/**
	 * 获取门店列表（不含分页）
	 * @param shop
	 * @return
	 */
	public List<Shop> getShopListNoPage(Shop shop)throws Exception;
}
