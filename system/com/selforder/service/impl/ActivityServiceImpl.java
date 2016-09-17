package com.selforder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.automobile.listener.SystemConfig;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.selforder.bean.Activity;
import com.selforder.bean.ActivityGoods;
import com.selforder.bean.Goods;
import com.selforder.bean.Table;
import com.selforder.dao.ActivityDao;
import com.selforder.service.ActivityService;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;
import com.selforder.util.Uuid;
import com.selforder.util.QRcode.QRCodeEvents;

/**
 * 活动服务接口实现
 * @author xingwanzhao
 *
 * 2016-8-22
 */
public class ActivityServiceImpl implements ActivityService {
	private ActivityDao activityDao;
	private Logger logger = Logger.getLogger(ActivityServiceImpl.class);
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
			String uuid = Uuid.getUuid();
			String crter = new Context().getLoginUserInfo().getCode();
			String bid = new Context().getLoginUserInfo().getBid();
			String sid = new Context().getLoginUserInfo().getSid();
			activity.setCrter(crter);
			activity.setWeid(bid);
			activity.setStoreid(sid);
			activity.setId(uuid);
			int temp = activityDao.insertActivity(activity);
			//保存关联的食谱信息
			String goods = activity.getGoods();//获取食谱IDs
			if(null != goods && !"".equals(goods)){
				List<ActivityGoods> activityGoodsList = new ArrayList<ActivityGoods>();//新增物料集合
				String[] goodsArray = goods.split(",");//分割食谱IDs为数组
				//遍历goodsArray，去重后添加至list进行批量新增
				for(int i=0;i<goodsArray.length;i++){
					String g = goodsArray[i];
					ActivityGoods ag = new ActivityGoods();
					ag.setActivityid(uuid);
					ag.setGoodsid(g);
					if(!activityGoodsList.contains(ag)){
						activityGoodsList.add(ag);
					}
				}
				//activityGoodsList有数据时批量新增
				if(activityGoodsList.size()>0){
					int insertGoods = activityDao.insertAcrivcityGoods4Bach(activityGoodsList);
				}
			}
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
			//保存关联的食谱信息
			String goods = activity.getGoods();//获取食谱IDs
			if(null != goods && !"".equals(goods)){
				List<ActivityGoods> activityGoodsList = new ArrayList<ActivityGoods>();//新增物料集合
				String[] goodsArray = goods.split(",");//分割食谱IDs为数组
				//遍历goodsArray，去重后添加至list进行批量新增
				for(int i=0;i<goodsArray.length;i++){
					String g = goodsArray[i];
					ActivityGoods ag = new ActivityGoods();
					ag.setActivityid(activity.getId());
					ag.setGoodsid(g);
					if(!activityGoodsList.contains(ag)){
						activityGoodsList.add(ag);
					}
				}
				//activityGoodsList有数据时批量新增
				if(activityGoodsList.size()>0){
					int insertGoods = activityDao.insertAcrivcityGoods4Bach(activityGoodsList);
				}
			}
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
	
	/**
	 * 批量新增活动关联的食谱
	 * @param activityGoods
	 * @return
	 */
	public String insertAcrivcityGoods4Bach(List<ActivityGoods> activityGoods){
		String result = "";
		try{
			if(null == activityGoods || activityGoods.size()<=0){
				return JsonResultUtil.getJsonResult(-1, "fail", "保存参数为空!");
			}
			int temp = activityDao.insertAcrivcityGoods4Bach(activityGoods);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "保存关联食谱成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "保存关联食谱失败!");
			}
		}catch(Exception e){
			logger.error("操作异常!",e);
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 更新活动食谱
	 * @param activityGoods
	 * @return
	 */
	public String updateActivityGoods(ActivityGoods activityGoods){
		String result = "";
		try{
			String opter = new Context().getLoginUserInfo().getCode();
			activityGoods.setOpter(opter);
			int temp = activityDao.updateActivityGoods(activityGoods);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "更新食谱成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "更新食谱失败!");
			}
		}catch(Exception e){
			logger.error("操作异常!",e);
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 获取活动食谱列表
	 * @param activity
	 * @return
	 */
	public String getActivityGoods(ActivityGoods activityGoods){
		String result = "";
		try{
			//查询门店列表
			List<ActivityGoods> activityGoodsList = activityDao.getActivityGoods(activityGoods);
			if(activityGoodsList != null && activityGoodsList.size()>0){
				result = JsonResultUtil.getJsonResult(0, "success", "获取数据成功！", activityGoodsList);
			}else{
				result = JsonResultUtil.getJsonResult(-1,"fail", "无数据!");
			}
		}catch(Exception e){
			logger.error("查询异常!", e);
			return result = JsonResultUtil.getJsonResult(-1,"fail", "查询异常!");
		}
		return result;
	}
	
	/**
	 * 获取活动食谱列表
	 * @param activity
	 * @return
	 */
	public String getNotInActivityGoods(Goods goods){
		String result = "";
		String sid = new Context().getLoginUserInfo().getSid();
		if(null == goods) goods = new Goods();
		goods.setStoreid(sid);
		try{
			//查询门店列表
			List<Goods> goodsList = activityDao.getNotInActivityGoods(goods);
			if(goodsList != null && goodsList.size()>0){
				result = JsonResultUtil.getJsonResult(0, "success", "获取数据成功！", goodsList);
			}else{
				result = JsonResultUtil.getJsonResult(-1,"fail", "无数据!");
			}
		}catch(Exception e){
			logger.error("查询异常!", e);
			return result = JsonResultUtil.getJsonResult(-1,"fail", "查询异常!");
		}
		return result;
	}
}
