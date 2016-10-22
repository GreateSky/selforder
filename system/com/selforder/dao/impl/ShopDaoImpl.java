package com.selforder.dao.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.selforder.bean.Business;
import com.selforder.bean.Shop;
import com.selforder.dao.ShopDao;
import com.selforder.util.JsonResultUtil;

/**
 * 门店管理接口实现类
 * @author xingwanzhao
 *
 * 2016-5-25
 */
public class ShopDaoImpl extends SqlSessionDaoSupport implements ShopDao {
	
	/**
	 * 保存门店信息
	 * @param Shop
	 * @return
	 */
	@Override
	public String saveShop(Shop shop) throws Exception{
		String result = "";
		try{
			int temp = getSqlSession().insert("com.selforder.shop.insertShop", shop);
			if(temp >0){
				result = JsonResultUtil.getJsonResult(0, "success", "保存成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "保存失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "保存商户信息失败！");
		}
		return result;
	}
	
	/**
	 * 获取门店列表
	 * @param Shop
	 * @return
	 */
	@Override
	public List<Shop> ShopList(Shop shop)throws Exception {
		List<Shop> shoplist = new ArrayList<Shop>();
		try{
			shoplist = getSqlSession().selectList("com.selforder.shop.getShopList", shop);
		}catch(Exception e){
			e.printStackTrace();
		}
		return shoplist;
	}

	/**
	 * 查询门店统计数
	 * @param Shop
	 * @return
	 */
	@Override
	public int getShopCount(Shop shop)throws Exception {
		int count = 0;
		try{
			count = (Integer)getSqlSession().selectOne("com.selforder.shop.getShopCount", shop);
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 获取门店详情
	 * @param Shop
	 * @return
	 */
	@Override
	public Shop ShopInfo(Shop shop)throws Exception {
		Shop shopinfo = new Shop();
		try{
			if(shopinfo != null){
				shopinfo = (Shop)getSqlSession().selectOne("com.selforder.shop.getShopInfo", shop);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return shopinfo;
	}

	/**
	 * 更新门店基本信息
	 * @param Shop
	 * @return
	 */
	@Override
	public int updateShop(Shop shop)throws Exception {
		int result = -1;
		try{
			result = getSqlSession().update("com.selforder.shop.updateShop", shop);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除门店
	 * @param shop
	 * @return
	 */
	public int delShop(Shop shop) throws Exception{
		return getSqlSession().delete("com.selforder.shop.delShop", shop);
	}
	
	/**
	 * 获取门店列表（不含分页）
	 * @param shop
	 * @return
	 */
	public List<Shop> getShopListNoPage(Shop shop)throws Exception{
		return getSqlSession().selectList("com.selforder.shop.getShopListNoPage", shop);
	}

}
