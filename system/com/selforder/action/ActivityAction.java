package com.selforder.action;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.greatesky.action.GreateSkyActionSupport;
import com.selforder.bean.Activity;
import com.selforder.bean.ActivityGoods;
import com.selforder.bean.Goods;
import com.selforder.service.ActivityService;

/**
 * 活动管理action
 * @author xingwanzhao
 *
 * 2016-5-16
 */
public class ActivityAction extends GreateSkyActionSupport {
	private Activity activity;
	private ActivityService activityService;
	private List<ActivityGoods> activityGoodsList;
	private ActivityGoods activityGoods;
	private Goods goods;
	private Logger logger = Logger.getLogger(ActivityAction.class);
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public List<ActivityGoods> getActivityGoodsList() {
		return activityGoodsList;
	}

	public ActivityGoods getActivityGoods() {
		return activityGoods;
	}

	public void setActivityGoodsList(List<ActivityGoods> activityGoodsList) {
		this.activityGoodsList = activityGoodsList;
	}

	public void setActivityGoods(ActivityGoods activityGoods) {
		this.activityGoods = activityGoods;
	}

	public Activity getActivity() {
		return activity;
	}

	public ActivityService getActivityService() {
		return activityService;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	/**
	 * 保存活动信息
	 * @return
	 */
	public String insertActivity(){
		HttpServletResponse response=ServletActionContext.getResponse();
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 * */
		response.setContentType("text/html;charset=utf-8");
		Writer out;
		String result;
		try{
			out = response.getWriter();
			result = activityService.insertActivity(activity);
			logger.info("保存活动结果==================："+result);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	/**
	 * 获取活动列表
	 * @return
	 */
	public String getActivityList(){
		HttpServletResponse response=ServletActionContext.getResponse();
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 * */
		response.setContentType("text/html;charset=utf-8");
		Writer out;
		String result;
		try{
			out = response.getWriter();
			if(null == activity){
				activity = new Activity();
			}
			activity.setPageSize(super.limit);
			activity.setPageStart(super.page);
			result = activityService.activityList(activity);
			logger.info("获取活动列表========"+result);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			return this.ERROR;
		}
		return this.SUCCESS;
	}	
	
	/**
	 * 获取活动详情
	 * @return
	 */
	public String getActivityInfo(){
		HttpServletResponse response=ServletActionContext.getResponse();
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 * */
		response.setContentType("text/html;charset=utf-8");
		Writer out;
		String result;
		try{
			out = response.getWriter();
			result = activityService.activityInfo(activity);
			System.out.println("获取活动详情========"+result);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	/**
	 * 更活动店详情
	 * @param Shop
	 * @return
	 */
	public String updateActivity(){
		HttpServletResponse response=ServletActionContext.getResponse();
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 * */
		response.setContentType("text/html;charset=utf-8");
		Writer out;
		String result;
		try{
			out = response.getWriter();
			result = activityService.updateActivity(activity);
			System.out.println("更新活动详情========"+result);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	/**
	 * 批量新增活动关联的食谱
	 * @param Shop
	 * @return
	 */
	public String insertAcrivcityGoods4Bach(){
		HttpServletResponse response=ServletActionContext.getResponse();
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 * */
		response.setContentType("text/html;charset=utf-8");
		Writer out;
		String result;
		try{
			out = response.getWriter();
			result = activityService.insertAcrivcityGoods4Bach(activityGoodsList);
			System.out.println("批量新增活动关联的食谱========"+result);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	/**
	 * 更新活动食谱
	 * @param Shop
	 * @return
	 */
	public String updateActivityGoods(){
		HttpServletResponse response=ServletActionContext.getResponse();
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 * */
		response.setContentType("text/html;charset=utf-8");
		Writer out;
		String result;
		try{
			out = response.getWriter();
			result = activityService.updateActivityGoods(activityGoods);
			System.out.println("更新活动食谱========"+result);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error("更新活动食谱异常", e);
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	/**
	 * 获取活动食谱列表
	 */
	public String getActivityInCouldGoods(){
		HttpServletResponse response=ServletActionContext.getResponse();
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 * */
		response.setContentType("text/html;charset=utf-8");
		Writer out;
		String result;
		try{
			out = response.getWriter();
			result = activityService.getActivityGoods(activityGoods);
			System.out.println("获取活动食谱列表========"+result);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error("获取活动食谱列表", e);
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	/**
	 * 获取活动未关联食谱列表
	 */
	public String getNotInActivityGoods(){
		HttpServletResponse response=ServletActionContext.getResponse();
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 * */
		response.setContentType("text/html;charset=utf-8");
		Writer out;
		String result;
		try{
			out = response.getWriter();
			result = activityService.getNotInActivityGoods(goods);
			System.out.println("获取活动食谱列表========"+result);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error("获取活动食谱列表", e);
			return this.ERROR;
		}
		return this.SUCCESS;
	}
}
