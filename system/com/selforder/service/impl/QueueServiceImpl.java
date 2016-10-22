package com.selforder.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.selforder.bean.Queue;
import com.selforder.bean.QueueSetting;
import com.selforder.dao.QueueDao;
import com.selforder.service.QueueService;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;

/**
 * 队列服务实现类
 * @author xingwanzhao
 *
 * 2016-8-15
 */
public class QueueServiceImpl implements QueueService{
	private static Logger logger=Logger.getLogger(QueueServiceImpl.class);
	private QueueDao queueDao;
	public QueueDao getQueueDao() {
		return queueDao;
	}
	public void setQueueDao(QueueDao queueDao) {
		this.queueDao = queueDao;
	}
	/**
	 * 保存队列设置
	 * @param queueSetting
	 * @return
	 */
	public String insertQueueSetting(QueueSetting queueSetting){
		String result = "";
		try{
			String crter = new Context().getLoginUserInfo().getCode();
			String bid = new Context().getLoginUserInfo().getBid();
			String sid = new Context().getLoginUserInfo().getSid();
			queueSetting.setCrter(crter);
			queueSetting.setWeid(bid);
			queueSetting.setStoreid(sid);
			int temp = queueDao.insertQueueSetting(queueSetting);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "新增队列设置成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "新增队列设置失败!");
			}
		}catch(Exception e){
			logger.error("新增队设置列异常："+e.getMessage());
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 更新队列设置
	 * @param queueSetting
	 * @return
	 * @throws Exception
	 */
	public String updateQueueSetting(QueueSetting queueSetting){
		String result = "";
		try{
			String crter = new Context().getLoginUserInfo().getCode();
			String bid = new Context().getLoginUserInfo().getBid();
			String sid = new Context().getLoginUserInfo().getSid();
			queueSetting.setCrter(crter);
			queueSetting.setWeid(bid);
			queueSetting.setStoreid(sid);
			int temp = queueDao.updateQueueSetting(queueSetting);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "修改队列设置成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "修改队列设置失败!");
			}
		}catch(Exception e){
			logger.error("修改队列设置异常："+e.getMessage());
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 获取队列详情
	 * @param queueSetting
	 * @return
	 * @
	 */
	public String getQueueSettingInfo(QueueSetting queueSetting){
		String result = "";
		try{
			QueueSetting temp = queueDao.getQueueSettingInfo(queueSetting);
			if(null == temp){
				result = JsonResultUtil.getJsonResult(-1, "fail", "无数据！");
			}else{
				result = JsonResultUtil.getJsonResult(0, "success", "获取数据成功！",temp);
			}
		}catch(Exception e){
			logger.error("获取队列详情异常："+e.getMessage());
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 获取队列列表
	 * @param queueSetting
	 * @return
	 * @
	 */
	public String getQueueSettingList(QueueSetting queueSetting){
		String result = "";
		String sid = new Context().getLoginUserInfo().getSid();
		if(queueSetting == null)
			queueSetting = new QueueSetting();
		queueSetting.setStoreid(sid);
		queueSetting.setStatus("0");
		try{
			List<QueueSetting> queueSettingList = queueDao.getQueueSettingList(queueSetting);
			if(null != queueSettingList && queueSettingList.size()>0){
				result = JsonResultUtil.getJsonResult(0, "success", "获取队列列表成功！",queueSettingList);
				
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "无数据！");
			}
		}catch(Exception e){
			logger.error("获取队列列表："+e.getMessage());
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 申请排号
	 * @param queueSetting
	 * @return
	 */
	public String applyQueueNum(Queue queue){
		String result = "";
		try{
			queue.setFrom_user("system");
			Map temp = queueDao.applyQueueNum(queue);
			if(null == temp){
				result = JsonResultUtil.getJsonResult(-1, "fail", "无数据！");
			}else{
				result = JsonResultUtil.getJsonResult(0, "success", "获取数据成功！",temp);
			}
		}catch(Exception e){
			logger.error("申请排号异常："+e.getMessage());
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 获取下一个排号
	 * @param queueSetting
	 * @return
	 * @
	 */
	public String callNextQueueNum(Queue queue) {
		String result = "";
		try{
			Map temp = queueDao.callNextQueueNum(queue);
			if(null == temp){
				result = JsonResultUtil.getJsonResult(-1, "fail", "无数据！");
			}else{
				result = JsonResultUtil.getJsonResult(0, "success", "获取数据成功！",temp);
			}
		}catch(Exception e){
			logger.error("获取下一个排号异常："+e.getMessage());
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 更新排号
	 * @param queue
	 * @return
	 * @
	 */
	public String updateQueue(Queue queue){
		String result = "";
		try{
			int temp = queueDao.updateQueue(queue);
			if(temp<0){
				result = JsonResultUtil.getJsonResult(-1, "fail", "更新排号失败！");
			}else{
				result = JsonResultUtil.getJsonResult(0, "success", "更新排号！");
			}
		}catch(Exception e){
			logger.error("更新排号："+e.getMessage());
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
}
