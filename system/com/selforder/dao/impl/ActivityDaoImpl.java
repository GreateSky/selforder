package com.selforder.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.selforder.bean.Activity;
import com.selforder.bean.ActivityGoods;
import com.selforder.bean.Goods;
import com.selforder.dao.ActivityDao;

public class ActivityDaoImpl extends SqlSessionDaoSupport implements ActivityDao {
	
	/**
	 * 获取活动列表
	 * @param activity
	 * @return
	 */
	@Override
	public List<Activity> activityList(Activity activity) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("com.selforder.activity.getActivityList", activity);
	}

	/**
	 * 获取活动列表统计数
	 * @param activity
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int activityListCount(Activity activity) throws Exception {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().selectOne("com.selforder.activity.getTableListCount", activity);
	}

	/**
	 * 获取活动详情
	 * @param activity
	 * @return
	 */
	@Override
	public Activity activityInfo(Activity activity) throws Exception {
		// TODO Auto-generated method stub
		return (Activity)getSqlSession().selectOne("com.selforder.activity.getActivityInfo", activity);
	}

	/**
	 * 新增活动
	 * @param activity
	 * @return
	 */
	@Override
	public int insertActivity(Activity activity) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().insert("com.selforder.activity.insertActivity", activity);
	}

	/**
	 * 更新活动
	 * @param activity
	 * @return
	 */
	@Override
	public int updateActivity(Activity activity) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().update("com.selforder.activity.updateActivity", activity);
	}
	
	/**
	 * 批量新增活动关联的食谱
	 * @param activityGoods
	 * @return
	 * @throws Exception
	 */
	public int insertAcrivcityGoods4Bach(List<ActivityGoods> activityGoods) throws Exception{
		return getSqlSession().insert("com.selforder.activity.insertAcrivcityGoods4Bach", activityGoods);
	}
	
	/**
	 * 更新活动食谱
	 * @param activityGoods
	 * @return
	 */
	public int updateActivityGoods(ActivityGoods activityGoods)throws Exception{
		return getSqlSession().update("com.selforder.activity.updateActivityGoods", activityGoods);
	}
	
	/**
	 * 获取活动食谱列表
	 * @param activity
	 * @return
	 */
	public List<ActivityGoods> getActivityGoods(ActivityGoods activityGoods) throws Exception{
		return getSqlSession().selectList("com.selforder.activity.getActivityGoods", activityGoods);
	}
	
	/**
	 * 获取活动食谱列表
	 * @param activity
	 * @return
	 */
	public List<Goods> getNotInActivityGoods(Goods goods) throws Exception{
		return getSqlSession().selectList("com.selforder.activity.getNotInActivityGoods", goods);
	}
	
	/**
	 * 根据活动查询活动中包含的食谱已存在的当前有效的活动中的食谱个数
	 * @param activity
	 * @return
	 * @throws SQLException
	 */
	public int getIncludeGoodsCount(Activity activity) throws SQLException{
		return (Integer)getSqlSession().selectOne("com.selforder.activity.getIncludeGoodsCount", activity);
	}

}
