package com.selforder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.selforder.bean.Business;
import com.selforder.bean.Shop;
import com.selforder.dao.ShopDao;
import com.selforder.service.ShopService;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;

public class ShopServiceImpl implements ShopService {
	private ShopDao shopDao;

	public ShopDao getShopDao() {
		return shopDao;
	}

	public void setShopDao(ShopDao shopDao) {
		this.shopDao = shopDao;
	}

	/**
	 * 保存门店信息
	 * @param shop
	 * @return
	 */
	@Override
	public String saveShop(Shop shop) {
		// TODO Auto-generated method stub
		return shopDao.saveShop(shop);
	}

	/**
	 * 获取门店列表
	 * @param Shop
	 * @return
	 */
	@Override
	public String shopList(Shop shop) {
		String result = "";
		Map resultMap = new HashMap();
		String bid = new Context().getLoginUserInfo().getBid();
		shop.setWeid(bid);
		try{
			//查询门店列表
			List<Shop> shoplist = shopDao.ShopList(shop);
			if(shoplist != null && shoplist.size()>0){
				//查询统计数
				int count = shopDao.getShopCount(shop);
				resultMap.put("rows", shoplist);
				resultMap.put("total", count);
				result = JsonResultUtil.MapToJsonStr(resultMap);
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		return result;
	}

	/**
	 * 获取门店详情
	 * @param shop
	 * @return
	 */
	@Override
	public String shopInfo(Shop shop) {
		String result = "";
		try{
			Shop shopinfo = shopDao.ShopInfo(shop);
			if(shopinfo != null){
				result = JsonResultUtil.getJsonResult(0, "success", "", shopinfo);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "未查询到门店详情");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 更新门店基本信息
	 * @param business
	 * @return
	 */
	@Override
	public String updateShop(Shop shop) {
		String result = "";
		if(shop != null && !"".equals(shop.getId())){
			int updateRes = shopDao.updateShop(shop);
			if(updateRes <=0){
				result = JsonResultUtil.getJsonResult(-1, "fail", "更新门店信息失败!");
			}else{
				result = JsonResultUtil.getJsonResult(0, "success", "更新门店信息成功!");
			}
		}else{
			result = JsonResultUtil.getJsonResult(-1, "fail", "更新门店信息参数异常!");
		}
		return result;
	}

}
