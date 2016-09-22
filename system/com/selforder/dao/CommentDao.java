package com.selforder.dao;

import java.util.List;
import java.util.Map;

import com.selforder.bean.Comment;

/**
 * 评论管理
 * @author xingwanzhao
 *
 * 2016-9-20
 */
public interface CommentDao {
	/**
	 * 获取评论列表
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	public List<Map> getCommentList(Comment comment) throws Exception;
	
	/**
	 * 获取评论列表统计数
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	public int getCommengListCount(Comment comment) throws Exception;
	
	/**
	 * 获取评论详情
	 * @param Comment
	 * @return
	 * @throws Exception
	 */
	public List<Map> getCommentDetailList(Comment Comment) throws Exception;
	
	/**
	 * 更新评论信息
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	public int updateComment(Comment comment)throws Exception;
	
	/**
	 * 更新阅读状态
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	public int updateIsRead(Comment comment)throws Exception;
}
