package com.selforder.dao;

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
public interface GoodsDao {
	/**
	 * 获取食谱列表
	 * @param goods
	 * @return
	 */
	public List<Goods> goodsList(Goods goods) throws SQLException;
	
	/**
	 * 获取食谱列表统计数
	 * @param goods
	 * @return
	 * @throws SQLException
	 */
	public int goodsListCount(Goods goods)throws SQLException;
	
	/**
	 * 获取食谱详情
	 * @param goods
	 * @return
	 */
	public Goods goodsInfo(Goods goods)throws SQLException;
	
	/**
	 * 新增食谱
	 * @param goods
	 * @return
	 */
	public int insertGoods (Goods goods)throws SQLException;
	
	/**
	 * 更新食谱
	 * @param goods
	 * @return
	 */
	public int updateGoods(Goods goods)throws SQLException;
	
	/**
	 * 新增食谱分类
	 * @param goodsCategory
	 * @return
	 */
	public int insertGoodsCategory (GoodsCategory goodsCategory)throws SQLException;
	
	/**
	 * 更新食谱分类
	 * @param goodsCategory
	 * @return
	 */
	public int updateGoodsCategory(GoodsCategory goodsCategory)throws SQLException;
	
	/**
	 * 获取食谱分类列表 
	 * @param goods
	 * @return
	 * @throws SQLException
	 */
	public List<GoodsCategory> goodsCategoryList(GoodsCategory goodsCategory)throws SQLException; 
	
	/**
	 * 加载是食谱列表（按订单id过滤掉订单中已包含的食谱）
	 * @param oid 订单ID
	 * @return
	 * @throws SQLException
	 */
	public List<Goods> getGoodsListIgnoreOrderId(Goods goods) throws SQLException;
}
