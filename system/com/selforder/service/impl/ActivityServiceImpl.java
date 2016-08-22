package com.selforder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.automobile.listener.SystemConfig;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.selforder.bean.Activity;
import com.selforder.bean.Table;
import com.selforder.dao.ActivityDao;
import com.selforder.service.ActivityService;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;
import com.selforder.util.QRcode.QRCodeEvents;

/**
 * 活动服务接口实现
 * @author xingwanzhao
 *
 * 2016-8-22
 */
public class ActivityServiceImpl implements ActivityService {
	private ActivityDao activityDao;
	public ActivityDao getActivityDao() {
		return activityDao;
	}
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}
	/**
	 * 获取活动列表
	 * @param activity
	 * @return
	 */
	public String activityList(Activity activity){
		String result = "";
		Map resultMap = new HashMap();
		String bid = new Context().getLoginUserInfo().getBid();
		String sid = new Context().getLoginUserInfo().getSid();
		activity.setWeid(bid);
		activity.setStoreid(sid);
		try{
			//查询门店列表
			List<Activity> activityList = activityDao.activityList(activity);
			if(activityList != null && activityList.size()>0){
				//查询统计数
				int count = activityDao.activityListCount(activity);
				resultMap.put("rows", activityList);
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
	 * 获取活动详情
	 * @param activity
	 * @return
	 */
	public String activityInfo(Activity activity){
		String result = "";
		try{
			Activity activityInfo = activityDao.activityInfo(activity);
			if(activityInfo != null){
				result = JsonResultUtil.getJsonResult(0, "success", "", activityInfo);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "未查询到活动详情");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "查询异常!");
		}
		return result;
	}
	
	/**
	 * 新增活动
	 * @param activity
	 * @return
	 */
	public String insertActivity (Activity activity){
		String result = "";
		try{
			String crter = new Context().getLoginUserInfo().getCode();
			String bid = new Context().getLoginUserInfo().getBid();
			String sid = new Context().getLoginUserInfo().getSid();
			activity.setCrter(crter);
			activity.setWeid(bid);
			activity.setStoreid(sid);
			int temp = activityDao.insertActivity(activity);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "新增活动成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "新增活动失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 更新活动
	 * @param activity
	 * @return
	 */
	public String updateActivity(Activity activity){
		String result = "";
		try{
			String opter = new Context().getLoginUserInfo().getCode();
			activity.setOpter(opter);
			int temp = activityDao.updateActivity(activity);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "更新活动成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "更新活动失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
}
