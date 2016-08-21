package com.selforder.service;

import java.util.List;
import java.util.Map;

import com.selforder.bean.Queue;
import com.selforder.bean.QueueSetting;

/**
 * 队列服务类
 * @author xingwanzhao
 *
 * 2016-8-15
 */
public interface QueueService {
	//*****************队列设置start*************************
	/**
	 * 保存队列设置
	 * @param queueSetting
	 * @return
	 */
	public String insertQueueSetting(QueueSetting queueSetting)  ;
	
	/**
	 * 更新队列设置
	 * @param queueSetting
	 * @return
	 * @
	 */
	public String updateQueueSetting(QueueSetting queueSetting)  ;
	
	/**
	 * 获取队列详情
	 * @param queueSetting
	 * @return
	 * @
	 */
	public String getQueueSettingInfo(QueueSetting queueSetting);
	
	/**
	 * 获取队列列表
	 * @param queueSetting
	 * @return
	 * @
	 */
	public String getQueueSettingList(QueueSetting queueSetting);
	
	/**
	 * 申请排号
	 * @param queueSetting
	 * @return
	 */
	public String applyQueueNum(Queue queue);
	
	/**
	 * 获取下一个排号
	 * @param queueSetting
	 * @return
	 * @
	 */
	public String callNextQueueNum(Queue queue) ;
	
	/**
	 * 更新排号
	 * @param queue
	 * @return
	 * @
	 */
	public String updateQueue(Queue queue) ;
	//*****************队列设置end*************************
}
