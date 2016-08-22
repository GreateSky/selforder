package com.selforder.service;

import java.sql.SQLException;
import java.util.List;

import com.selforder.bean.Activity;

/**
 * 活动服务接口
 * @author xingwanzhao
 *
 * 2016-8-22
 */
public interface ActivityService {
	/**
	 * 获取活动列表
	 * @param activity
	 * @return
	 */
	public String activityList(Activity activity) ;
	
	/**
	 * 获取活动详情
	 * @param activity
	 * @return
	 */
	public String activityInfo(Activity activity);
	
	/**
	 * 新增活动
	 * @param activity
	 * @return
	 */
	public String insertActivity (Activity activity);
	
	/**
	 * 更新活动
	 * @param activity
	 * @return
	 */
	public String updateActivity(Activity activity);
}
