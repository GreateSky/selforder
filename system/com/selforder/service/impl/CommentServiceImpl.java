package com.selforder.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.selforder.bean.Comment;
import com.selforder.dao.CommentDao;
import com.selforder.service.CommentService;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;

public class CommentServiceImpl implements CommentService {
	private CommentDao commentDao;
	private Logger logger = Logger.getLogger(CommentServiceImpl.class);

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	/**
	 * 获取评论列表
	 * @param comment
	 * @return
	 * @
	 */
	@Override
	public String getCommentList(Comment comment) {
		String result = "";
		Map resultMap = new HashMap();
		String storeid = new Context().getLoginUserInfo().getSid();
		try{
			if(comment == null){
				comment = new Comment();
			}
			comment.setStoreid(storeid);
			List<Map> commentList = commentDao.getCommentList(comment);
			if(commentList != null && commentList.size()>0){
				//查询统计数
				int count = commentDao.getCommengListCount(comment);
				resultMap.put("rows", commentList);
				resultMap.put("total", count);
				result = JsonResultUtil.getJsonResult(0, "success", "获取数据成功!", resultMap);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "无数据!");
			}
		}catch(Exception e){
			logger.error("获取评论列表异常！",e);
			return JsonResultUtil.getJsonResult(-1, "fail", "获取评论列表异常!");
		}
		return result;
	}

	/**
	 * 获取评论详情
	 * @param Comment
	 * @return
	 * @
	 */
	@Override
	public String getCommentDetailList(Comment Comment) {
		String result = "";
		try{
			
			List<Map> commentList = commentDao.getCommentDetailList(Comment);
			if(commentList != null && commentList.size()>0){
				result = JsonResultUtil.getJsonResult(0, "success", "获取数据成功!", commentList);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "无数据!");
			}
		}catch(Exception e){
			logger.error("获取评论详情异常！",e);
			return JsonResultUtil.getJsonResult(-1, "fail", "获取评论详情异常!");
		}
		return result;
	}

	/**
	 * 更新评论信息
	 * @param comment
	 * @return
	 * @
	 */
	@Override
	public String updateComment(Comment comment) {
		String result = "";
		String usercode = new Context().getLoginUserInfo().getCode();
		try{
			comment.setReplyer(usercode);
			comment.setReplydate(new Date());
			comment.setOpter(usercode);
			comment.setOptdate(new Date());
			int temp = commentDao.updateComment(comment);
			if(temp>0){
				result = JsonResultUtil.getJsonResult(0, "success", "更新评论信息成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "更新评论信息失败!");
			}
		}catch(Exception e){
			logger.error("更新评论信息异常！",e);
			return JsonResultUtil.getJsonResult(-1, "fail", "更新评论信息异常!");
		}
		return result;
	}
	
	/**
	 * 更新阅读状态
	 * @param comment
	 * @return
	 */
	public String updateIsRead(Comment comment){
		String result = "";
		String usercode = new Context().getLoginUserInfo().getCode();
		try{
			comment.setOpter(usercode);
			int temp = commentDao.updateIsRead(comment);
			if(temp>0){
				result = JsonResultUtil.getJsonResult(0, "success", "更新阅读状态成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "更新阅读状态失败!");
			}
		}catch(Exception e){
			logger.error("更新阅读状态异常！",e);
			return JsonResultUtil.getJsonResult(-1, "fail", "更新阅读状态异常!");
		}
		return result;
	}

}
