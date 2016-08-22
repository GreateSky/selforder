package com.selforder.dao;

import java.sql.SQLException;
import java.util.List;

import com.selforder.bean.Activity;
import com.selforder.bean.Table;

/**
 * 活动管理持久层
 * @author xingwanzhao
 *
 * 2016-7-18
 */
public interface ActivityDao {
	/**
	 * 获取活动列表
	 * @param activity
	 * @return
	 */
	public List<Activity> activityList(Activity activity) throws Exception;
	
	/**
	 * 获取活动列表统计数
	 * @param activity
	 * @return
	 * @throws SQLException
	 */
	public int activityListCount(Activity activity)throws Exception;
	
	/**
	 * 获取活动详情
	 * @param activity
	 * @return
	 */
	public Activity activityInfo(Activity activity)throws Exception;
	
	/**
	 * 新增活动
	 * @param activity
	 * @return
	 */
	public int insertActivity (Activity activity)throws Exception;
	
	/**
	 * 更新活动
	 * @param activity
	 * @return
	 */
	public int updateActivity(Activity activity)throws Exception;
	
}
