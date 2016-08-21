package com.selforder.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.selforder.bean.Queue;
import com.selforder.bean.QueueSetting;
import com.selforder.dao.QueueDao;

public class QueueDaoImpl extends SqlSessionDaoSupport implements QueueDao {
	//*****************队列设置start*************************
	/**
	 * 保存队列设置
	 * @param queueSetting
	 * @return
	 * @throws Exception
	 */
	public int insertQueueSetting(QueueSetting queueSetting) throws Exception{
		return getSqlSession().insert("com.selforder.queue.insertQueueSetting", queueSetting);
	}
	
	/**
	 * 更新队列设置
	 * @param queueSetting
	 * @return
	 * @throws Exception
	 */
	public int updateQueueSetting(QueueSetting queueSetting) throws Exception{
		return getSqlSession().update("com.selforder.queue.updateQueueSetting", queueSetting);
	}
	
	/**
	 * 获取队列详情
	 * @param queueSetting
	 * @return
	 * @throws Exception
	 */
	public QueueSetting getQueueSettingInfo(QueueSetting queueSetting)throws Exception{
		return (QueueSetting)getSqlSession().selectOne("com.selforder.queue.getQueueSettingInfo", queueSetting);
	}
	
	/**
	 * 获取队列列表
	 * @param queueSetting
	 * @return
	 * @throws Exception
	 */
	public List<QueueSetting> getQueueSettingList(QueueSetting queueSetting)throws Exception{
		return getSqlSession().selectList("com.selforder.queue.getQueueSettingList", queueSetting);
	}
	
	/**
	 * 申请排号
	 * @param queueid 队列ID
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> applyQueueNum(Queue queue) throws Exception{
		return (Map<String,String>)getSqlSession().selectOne("com.selforder.queue.applyQueueNum", queue);
	}
	
	/**
	 * 获取下一个排号
	 * @param queueSetting
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> callNextQueueNum(Queue queue) throws Exception{
		return (Map<String,String>)getSqlSession().selectOne("com.selforder.queue.callNextQueueNum", queue);
	}
	
	/**
	 * 更新排号
	 * @param queue
	 * @return
	 * @throws Exception
	 */
	public int updateQueue(Queue queue) throws Exception{
		return getSqlSession().update("com.selforder.queue.updateQueue", queue);
	}
	
}
