package com.selforder.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.selforder.bean.Goods;
import com.selforder.bean.GoodsCategory;
import com.selforder.bean.Table;
import com.selforder.dao.GoodsDao;

/**
 * 食谱管理实现类
 * @author xingwanzhao
 *
 * 2016-7-30
 */
public class GoodsDaoImpl extends SqlSessionDaoSupport implements GoodsDao {
	/**
	 * 获取食谱列表
	 * @param goods
	 * @return
	 */
	public List<Goods> goodsList(Goods goods) throws SQLException{
		return getSqlSession().selectList("com.selforder.goods.getGoodsList", goods);
	}
	
	/**
	 * 获取食谱列表统计数
	 * @param goods
	 * @return
	 * @throws SQLException
	 */
	public int goodsListCount(Goods goods)throws SQLException{
		return (Integer)getSqlSession().selectOne("com.selforder.goods.getGoodsListCount",goods);
	}
	
	/**
	 * 获取食谱详情
	 * @param goods
	 * @return
	 */
	public Goods goodsInfo(Goods goods)throws SQLException{
		return (Goods)getSqlSession().selectOne("com.selforder.goods.getGoodsInfo", goods);
	}
	
	/**
	 * 新增食谱
	 * @param goods
	 * @return
	 */
	public int insertGoods (Goods goods)throws SQLException{
		return getSqlSession().insert("com.selforder.goods.insertGoods", goods);
	}
	
	/**
	 * 更新食谱
	 * @param goods
	 * @return
	 */
	public int updateGoods(Goods goods)throws SQLException{
		return getSqlSession().update("com.selforder.goods.updateGoods",goods);
	}
	
	/**
	 * 新增食谱分类
	 * @param goodsCategory
	 * @return
	 */
	public int insertGoodsCategory (GoodsCategory goodsCategory)throws SQLException{
		return getSqlSession().insert("com.selforder.goods.insertGoodsCategory", goodsCategory);
	}
	
	/**
	 * 更新食谱分类
	 * @param goodsCategory
	 * @return
	 */
	public int updateGoodsCategory(GoodsCategory goodsCategory)throws SQLException{
		return getSqlSession().update("com.selforder.goods.updateGoodsCategory",goodsCategory);
	}
	
	/**
	 * 获取食谱分类列表 
	 * @param goods
	 * @return
	 * @throws SQLException
	 */
	public List<GoodsCategory> goodsCategoryList(GoodsCategory goodsCategory)throws SQLException{
		return getSqlSession().selectList("com.selforder.goods.getGoodsCategoryList", goodsCategory);
	} 
}
