package com.selforder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.selforder.bean.Business;
import com.selforder.bean.Shop;
import com.selforder.dao.ShopDao;
import com.selforder.service.ShopService;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;

public class ShopServiceImpl implements ShopService {
	private ShopDao shopDao;
	private Logger logger = Logger.getLogger(ShopServiceImpl.class);
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
		String result = "";
		String bid = new Context().getLoginUserInfo().getBid();
		shop.setWeid(bid);
		try{
			result = shopDao.saveShop(shop);
		}catch(Exception e){
			logger.error("保存门店信息:saveShop异常"+e.getMessage());
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
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
			logger.error("获取门店列表:shopList异常"+e.getMessage());
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
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
			logger.error("更新门店基本信息:updateShop异常"+e.getMessage());
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
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
		try{
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
		}catch(Exception e){
			logger.error("更新门店基本信息:updateShop异常"+e.getMessage());
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 删除门店
	 * @param shop
	 * @return
	 */
	public String delShop(Shop shop){
		String result = "";
		try{
			int temp = shopDao.delShop(shop);
			if(temp < 0 ){
				result = JsonResultUtil.getJsonResult(-1, "fail", "操作失败!");
			}else{
				result = JsonResultUtil.getJsonResult(0, "success", "操作成功!");
			}
		}catch(Exception e){
			logger.error("删除门店:delShop异常"+e.getMessage());
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 获取门店列表（不含分页）
	 * @param shop
	 * @return
	 */
	public String getShopListNoPage(Shop shop){
		String result = "";
		try{
			String bid = new Context().getLoginUserInfo().getBid();
			if(shop == null) shop = new Shop();
			shop.setWeid(bid);
			//查询门店列表
			List<Shop> shoplist = shopDao.getShopListNoPage(shop);
			if(null != shoplist && shoplist.size() >0){
				result = JsonResultUtil.getJsonResult(0, "success", "查询数据成功！", shoplist);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "查询数据失败！");
			}
		}catch(Exception e){
			logger.error("获取门店列表（不含分页）:getShopListNoPage异常"+e.getMessage());
			return JsonResultUtil.getJsonResult(-1, "fail", "查询数据异常！");
		}
		return result;
	}
}
