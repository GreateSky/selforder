package com.selforder.service;

import java.sql.SQLException;
import java.util.List;

import com.selforder.bean.Goods;
import com.selforder.bean.GoodsCategory;
import com.selforder.bean.Table;

/**
 * 食谱管理持久层
 * @author xingwanzhao
 *
 * 2016-7-18
 */
public interface GoodsService {
	/**
	 * 获取食谱列表
	 * @param goods
	 * @return
	 */
	public String goodsList(Goods goods) ;
	
	/**
	 * 获取食谱详情
	 * @param goods
	 * @return
	 */
	public String goodsInfo(Goods goods);
	
	/**
	 * 新增食谱
	 * @param goods
	 * @return
	 */
	public String insertGoods (Goods goods);
	
	/**
	 * 更新食谱
	 * @param goods
	 * @return
	 */
	public String updateGoods(Goods goods);
	
	/**
	 * 新增食谱分类
	 * @param goodsCategory
	 * @return
	 */
	public String insertGoodsCategory (GoodsCategory goodsCategory);
	
	/**
	 * 更新食谱分类
	 * @param goodsCategory
	 * @return
	 */
	public String updateGoodsCategory(GoodsCategory goodsCategory);
	
	/**
	 * 获取食谱分类列表 
	 * @param goods
	 * @return
	 * @
	 */
	public String goodsCategoryList(GoodsCategory goodsCategory); 
}
