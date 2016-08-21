package com.selforder.bean;

import java.util.Date;

/**
 * 队列实体类
 * @author xingwanzhao
 *
 * 2016-8-20
 */
public class Queue extends baseBean {
	private static final long serialVersionUID = 6334639964476239278L;
	private String id;
	private String queueid;
	private String currnum;
	private String from_user;
	private String status;
	private String crter;
	private Date crtdate;
	private String opter;
	private Date optdate;
	private String deleted;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}
	public String getQueueid() {
		return queueid;
	}
	public String getCurrnum() {
		return currnum;
	}
	public String getFrom_user() {
		return from_user;
	}
	public String getStatus() {
		return status;
	}
	public String getCrter() {
		return crter;
	}
	public Date getCrtdate() {
		return crtdate;
	}
	public String getOpter() {
		return opter;
	}
	public Date getOptdate() {
		return optdate;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setQueueid(String queueid) {
		this.queueid = queueid;
	}
	public void setCurrnum(String currnum) {
		this.currnum = currnum;
	}
	public void setFrom_user(String from_user) {
		this.from_user = from_user;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCrter(String crter) {
		this.crter = crter;
	}
	public void setCrtdate(Date crtdate) {
		this.crtdate = crtdate;
	}
	public void setOpter(String opter) {
		this.opter = opter;
	}
	public void setOptdate(Date optdate) {
		this.optdate = optdate;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
}
