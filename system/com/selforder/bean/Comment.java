package com.selforder.bean;

import java.util.Date;

/**
 * 评论实体
 * @author xingwanzhao
 *
 * 2016-9-19
 */
public class Comment extends baseBean {
	private static final long serialVersionUID = -6881492464565662848L;
	private String rid;
	private String from_user;
	private String weid;
	private String storeid;
	private String description;
	private String picture;
	private int status;
	private String replyer;
	private String replyname;
	private String replyheadimg;
	private String reply;
	private Date replydate;
	private String dateline;
	private Date crtdate;
	private String crter;
	private String opter;
	private Date optdate;
	private Date begindate;
	private Date enddate;
	private int deleted;
	private int isread;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRid() {
		return rid;
	}
	public String getFrom_user() {
		return from_user;
	}
	public String getWeid() {
		return weid;
	}
	public String getStoreid() {
		return storeid;
	}
	public String getDescription() {
		return description;
	}
	public String getPicture() {
		return picture;
	}
	public int getStatus() {
		return status;
	}
	public String getReplyer() {
		return replyer;
	}
	public String getReply() {
		return reply;
	}
	public Date getReplydate() {
		return replydate;
	}
	public String getDateline() {
		return dateline;
	}
	public Date getCrtdate() {
		return crtdate;
	}
	public String getCrter() {
		return crter;
	}
	public String getOpter() {
		return opter;
	}
	public Date getOptdate() {
		return optdate;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public void setFrom_user(String from_user) {
		this.from_user = from_user;
	}
	public void setWeid(String weid) {
		this.weid = weid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public void setReplydate(Date replydate) {
		this.replydate = replydate;
	}
	public void setDateline(String dateline) {
		this.dateline = dateline;
	}
	public void setCrtdate(Date crtdate) {
		this.crtdate = crtdate;
	}
	public void setCrter(String crter) {
		this.crter = crter;
	}
	public void setOpter(String opter) {
		this.opter = opter;
	}
	public void setOptdate(Date optdate) {
		this.optdate = optdate;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	public String getReplyname() {
		return replyname;
	}
	public String getReplyheadimg() {
		return replyheadimg;
	}
	public void setReplyname(String replyname) {
		this.replyname = replyname;
	}
	public void setReplyheadimg(String replyheadimg) {
		this.replyheadimg = replyheadimg;
	}
	public Date getBegindate() {
		return begindate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
}
