package com.selforder.dao;

import java.sql.SQLException;
import java.util.List;

import com.selforder.bean.Activity;
import com.selforder.bean.ActivityGoods;
import com.selforder.bean.Goods;

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
	
	/**
	 * 批量新增活动关联的食谱
	 * @param activityGoods
	 * @return
	 * @throws Exception
	 */
	public int insertAcrivcityGoods4Bach(List<ActivityGoods> activityGoods) throws Exception;
	
	/**
	 * 更新活动食谱
	 * @param activityGoods
	 * @return
	 */
	public int updateActivityGoods(ActivityGoods activityGoods)throws Exception;
	
	/**
	 * 获取活动食谱列表
	 * @param activity
	 * @return
	 */
	public List<ActivityGoods> getActivityGoods(ActivityGoods activityGoods) throws Exception;
	
	/**
	 * 获取活动食谱列表
	 * @param activity
	 * @return
	 */
	public List<Goods> getNotInActivityGoods(Goods goods) throws Exception;
	
	/**
	 * 根据活动查询活动中包含的食谱已存在的当前有效的活动中的食谱个数
	 * @param activity
	 * @return
	 * @throws SQLException
	 */
	public int getIncludeGoodsCount(Activity activity) throws SQLException;
	
	
	
}
