package com.selforder.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.selforder.bean.Activity;
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

}
