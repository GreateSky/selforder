package com.selforder.action;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.greatesky.action.GreateSkyActionSupport;
import com.selforder.bean.Queue;
import com.selforder.bean.QueueSetting;
import com.selforder.service.QueueService;

/**
 * 队列管理action
 * @author xingwanzhao
 *
 * 2016-5-16
 */
public class QueueAction extends GreateSkyActionSupport {
	private Queue queue;
	private QueueSetting queueSetting;
	private QueueService queueService;
	private Logger logger = Logger.getLogger(QueueAction.class);

	public Queue getQueue() {
		return queue;
	}

	public QueueSetting getQueueSetting() {
		return queueSetting;
	}

	public QueueService getQueueService() {
		return queueService;
	}

	public void setQueue(Queue queue) {
		this.queue = queue;
	}

	public void setQueueSetting(QueueSetting queueSetting) {
		this.queueSetting = queueSetting;
	}

	public void setQueueService(QueueService queueService) {
		this.queueService = queueService;
	}

	/**
	 * 保存队列设置信息
	 * @return
	 */
	public String insertQueueSetting(){
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
			result = queueService.insertQueueSetting(queueSetting);
			logger.info("保存队列设置结果==================："+result);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			logger.error("保存队列设置异常:"+e.getMessage());
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	/**
	 * 更新队列设置
	 * @return
	 */
	public String updateQueueSetting(){
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
			result = queueService.updateQueueSetting(queueSetting);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error("更新队列设置异常"+e.getMessage());
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	/**
	 * 获取队列详情
	 * @return
	 */
	public String getQueueSettingInfo(){
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
			result = queueService.getQueueSettingInfo(queueSetting);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error("获取队列详情异常"+e.getMessage());
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	/**
	 * 获取队列列表
	 * @return
	 */
	public String getQueueSettingList(){
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
			result = queueService.getQueueSettingList(queueSetting);
			logger.info("获取队列列表==================："+result);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(logger.getClass().getName()+"异常："+e.getMessage());
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	/**
	 * 申请排号
	 * @return
	 */
	public String applyQueueNum(){
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
			result = queueService.applyQueueNum(queue);
			logger.info("申请排号==================："+result);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(logger.getClass().getName()+"申请排号异常："+e.getMessage());
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	/**
	 * 获取下一个排号
	 * @return
	 */
	public String callNextQueueNum(){
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
			result = queueService.callNextQueueNum(queue);
			logger.info("获取下一个排号==================："+result);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(logger.getClass().getName()+"获取下一个排号异常："+e.getMessage());
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	/**
	 * 更新排号
	 * @return
	 */
	public String updateQueue(){
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
			result = queueService.updateQueue(queue);
			logger.info("更新排号==================："+result);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(logger.getClass().getName()+"更新排号异常："+e.getMessage());
			return this.ERROR;
		}
		return this.SUCCESS;
	}
}
