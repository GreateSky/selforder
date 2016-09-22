package com.selforder.service;

import java.util.List;
import java.util.Map;

import com.selforder.bean.Comment;

public interface CommentService {
	/**
	 * 获取评论列表
	 * @param comment
	 * @return
	 * @
	 */
	public String getCommentList(Comment comment) ;
	
	/**
	 * 获取评论详情
	 * @param Comment
	 * @return
	 * @
	 */
	public String getCommentDetailList(Comment comment) ;
	
	/**
	 * 更新评论信息
	 * @param comment
	 * @return
	 * @
	 */
	public String updateComment(Comment comment);
	
	/**
	 * 更新阅读状态
	 * @param comment
	 * @return
	 */
	public String updateIsRead(Comment comment);
}
