package com.selforder.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.selforder.bean.Comment;
import com.selforder.dao.CommentDao;

public class CommentDaoImpl extends SqlSessionDaoSupport implements CommentDao {

	/**
	 * 获取评论列表统计数
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map> getCommentList(Comment comment) throws Exception {
		return getSqlSession().selectList("com.selforder.comment.getCommentList", comment);
	}

	/**
	 * 获取评论列表统计数
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getCommengListCount(Comment comment) throws Exception {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().selectOne("com.selforder.comment.getCommentListCount",comment);
	}
	
	/**
	 * 获取评论详情
	 * @param Comment
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map> getCommentDetailList(Comment Comment) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("com.selforder.comment.getCommentDetail", Comment);
	}

	/**
	 * 更新评论信息
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateComment(Comment comment) throws Exception {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().update("com.selforder.comment.updateComment", comment);
	}
	
	/**
	 * 更新阅读状态
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateIsRead(Comment comment)throws Exception{
		return (Integer)getSqlSession().update("com.selforder.comment.updateIsRead", comment);
	}

}
