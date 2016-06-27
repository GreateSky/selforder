package com.selforder.bean;

import java.util.Date;

import com.automobile.bean.baseBean;

/**
 * 组织架构实体
 * @author xingwanzhao
 *
 * 2016-6-24
 */
public class Organization extends baseBean {
	private static final long serialVersionUID = 8360842687704928878L;
	private String oid;
	private String oname;
	private int level;
	private String parentid;
	private String levelpath;
	private String bid;
	private String crter;
	private Date crtdate;
	private String opter;
	private Date optdate;
	private int deleted;
	private int seq;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getOid() {
		return oid;
	}
	public String getOname() {
		return oname;
	}
	public int getLevel() {
		return level;
	}
	public String getParentid() {
		return parentid;
	}
	public String getLevelpath() {
		return levelpath;
	}
	public String getBid() {
		return bid;
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
	public int getDeleted() {
		return deleted;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public void setLevelpath(String levelpath) {
		this.levelpath = levelpath;
	}
	public void setBid(String bid) {
		this.bid = bid;
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
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
}
