package com.selforder.action;

import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.greatesky.action.GreateSkyActionSupport;
import com.selforder.bean.Activity;
import com.selforder.bean.Comment;
import com.selforder.service.CommentService;

/**
 * 评论管理action
 * @author xingwanzhao
 *
 * 2016-5-16
 */
public class CommentAction extends GreateSkyActionSupport {
	private Logger logger = Logger.getLogger(CommentAction.class);
	private Comment comment;
	private CommentService commentService;
	
	public Logger getLogger() {
		return logger;
	}

	public Comment getComment() {
		return comment;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	/**
	 * 获取评论列表
	 * @return
	 */
	public String getCommentList(){
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
			if(null == comment){
				comment = new Comment();
			}
			comment.setPageSize(super.limit);
			comment.setPageStart(super.page);
			result = commentService.getCommentList(comment);
			logger.info("获取评论列表:"+result);
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
	 * 获取评论详情
	 * @return
	 */
	public String getCommentDetailList(){
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
			if(null == comment){
				comment = new Comment();
			}
			result = commentService.getCommentDetailList(comment);
			logger.info("获取评论详情:"+result);
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
	 * 更新评论信息
	 * @return
	 */
	public String updateComment(){
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
			result = commentService.updateComment(comment);
			logger.info("更新评论信息:"+result);
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
	 * 更新阅读状态
	 * @return
	 */
	public String updateIsRead(){
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
			result = commentService.updateIsRead(comment);
			logger.info("更新阅读状态:"+result);
			out.write(result);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	
}
