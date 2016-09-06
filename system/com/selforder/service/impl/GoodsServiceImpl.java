package com.selforder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.automobile.listener.SystemConfig;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.selforder.bean.Goods;
import com.selforder.bean.GoodsCategory;
import com.selforder.bean.Table;
import com.selforder.dao.GoodsDao;
import com.selforder.service.GoodsService;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;
import com.selforder.util.QRcode.QRCodeEvents;

/**
 * 食谱管理服务实现类
 * @author xingwanzhao
 *
 * 2016-7-30
 */
public class GoodsServiceImpl implements GoodsService {
	private GoodsDao goodsDao;
	public GoodsDao getGoodsDao() {
		return goodsDao;
	}

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	/**
	 * 获取食谱列表
	 * @param goods
	 * @return
	 */
	public String goodsList(Goods goods) {
		String result = "";
		Map resultMap = new HashMap();
		String bid = new Context().getLoginUserInfo().getBid();
		goods.setWeid(bid);
		try{
			//查询食谱列表
			List<Goods> goodsList = goodsDao.goodsList(goods);
			if(goodsList != null && goodsList.size()>0){
				//查询统计数
				int count = goodsDao.goodsListCount(goods);
				resultMap.put("rows", goodsList);
				resultMap.put("total", count);
				result = JsonResultUtil.MapToJsonStr(resultMap);
			}else{
				result = JsonResultUtil.getJsonResult(-1,"fail", "无数据!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return result = JsonResultUtil.getJsonResult(-1,"fail", "查询异常!");
		}
		return result;
	}
	
	/**
	 * 获取食谱详情
	 * @param goods
	 * @return
	 */
	public String goodsInfo(Goods goods){
		String result = "";
		String bid = new Context().getLoginUserInfo().getBid();
		goods.setWeid(bid);
		try{
			Goods goodsinfo = goodsDao.goodsInfo(goods);
			if(goodsinfo != null){
				result = JsonResultUtil.getJsonResult(0, "success", "", goodsinfo);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "未查询到食谱详情");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "查询异常!");
		}
		return result;
	}
	
	/**
	 * 新增食谱
	 * @param goods
	 * @return
	 */
	public String insertGoods (Goods goods){
		String result = "";
		try{
			String crter = new Context().getLoginUserInfo().getCode();
			String bid = new Context().getLoginUserInfo().getBid();
			goods.setCrter(crter);
			goods.setWeid(bid);
			int temp = goodsDao.insertGoods(goods);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "新增食谱成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "新增食谱失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 更新食谱
	 * @param goods
	 * @return
	 */
	public String updateGoods(Goods goods){
		String result = "";
		try{
			String opter = new Context().getLoginUserInfo().getCode();
			goods.setOpter(opter);
			int temp = goodsDao.updateGoods(goods);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "更新食谱成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "更新食谱失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 新增食谱分类
	 * @param goodsCategory
	 * @return
	 */
	public String insertGoodsCategory (GoodsCategory goodsCategory){
		String result = "";
		try{
			String crter = new Context().getLoginUserInfo().getCode();
			String bid = new Context().getLoginUserInfo().getBid();
			goodsCategory.setCrter(crter);
			goodsCategory.setWeid(bid);
			int temp = goodsDao.insertGoodsCategory(goodsCategory);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "新增食谱分类成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "新增食谱分类失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 更新食谱分类
	 * @param goodsCategory
	 * @return
	 */
	public String updateGoodsCategory(GoodsCategory goodsCategory){
		String result = "";
		try{
			String opter = new Context().getLoginUserInfo().getCode();
			goodsCategory.setOpter(opter);
			int temp = goodsDao.updateGoodsCategory(goodsCategory);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "更新食谱分类成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "更新食谱分类失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 获取食谱分类列表 
	 * @param goods
	 * @return
	 * @
	 */
	public String goodsCategoryList(GoodsCategory goodsCategory){
		String result = "";
		String bid = new Context().getLoginUserInfo().getBid();
		if(null == goodsCategory){
			goodsCategory = new GoodsCategory();
		}
		goodsCategory.setWeid(bid);
		try{
			List<GoodsCategory> goodsCategoryList = goodsDao.goodsCategoryList(goodsCategory);
			if(null != goodsCategoryList && goodsCategoryList.size()> 0){
				result = JsonResultUtil.getJsonResult(0, "success", "查询数据成功!", goodsCategoryList);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "查询数据为空!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 加载是食谱列表（按订单id过滤掉订单中已包含的食谱）
	 * @param oid 订单ID
	 * @return
	 */
	public String getGoodsListIgnoreOrderId(Goods goods){
		String result = "";
		String bid = new Context().getLoginUserInfo().getBid();
		goods.setWeid(bid);
		try{
			List<Goods> goodsList = goodsDao.getGoodsListIgnoreOrderId(goods);
			if(null != goodsList && goodsList.size()> 0){
				result = JsonResultUtil.getJsonResult(0, "success", "查询数据成功!", goodsList);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "查询数据为空!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
}
