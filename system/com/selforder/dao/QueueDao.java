package com.selforder.dao;

import java.util.List;
import java.util.Map;

import com.selforder.bean.Queue;
import com.selforder.bean.QueueSetting;

/**
 * 队列持久层
 * @author xingwanzhao
 *
 * 2016-8-15
 */
public interface QueueDao {
	
	//*****************队列设置start*************************
	/**
	 * 保存队列设置
	 * @param queueSetting
	 * @return
	 * @throws Exception
	 */
	public int insertQueueSetting(QueueSetting queueSetting) throws Exception;
	
	/**
	 * 更新队列设置
	 * @param queueSetting
	 * @return
	 * @throws Exception
	 */
	public int updateQueueSetting(QueueSetting queueSetting) throws Exception;
	
	/**
	 * 获取队列详情
	 * @param queueSetting
	 * @return
	 * @throws Exception
	 */
	public QueueSetting getQueueSettingInfo(QueueSetting queueSetting)throws Exception;
	
	/**
	 * 获取队列列表
	 * @param queueSetting
	 * @return
	 * @throws Exception
	 */
	public List<QueueSetting> getQueueSettingList(QueueSetting queueSetting)throws Exception;
	
	/**
	 * 申请排号
	 * @param queueSetting
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> applyQueueNum(Queue queue) throws Exception;
	
	/**
	 * 获取下一个排号
	 * @param queueSetting
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> callNextQueueNum(Queue queue) throws Exception;
	
	/**
	 * 更新排号
	 * @param queue
	 * @return
	 * @throws Exception
	 */
	public int updateQueue(Queue queue) throws Exception;
	//*****************队列设置end*************************
}
