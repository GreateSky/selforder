package com.selforder.bean;

/**
 * 餐桌实体类
 * @author xingwanzhao
 *
 * 2016-7-18
 */
public class Table extends baseBean {
	private static final long serialVersionUID = -253021167365451447L;
	private String id;
	private String weid;
	private String storeid;
	private String limit_price;
	private String title;
	private String user_count;
	private String displayorder;
	private String dateline;
	private String status;
	private String crter;
	private String crtdate;
	private String opter;
	private String optdate;
	private String deleted;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}
	public String getWeid() {
		return weid;
	}
	public String getStoreid() {
		return storeid;
	}
	public String getLimit_price() {
		return limit_price;
	}
	public String getTitle() {
		return title;
	}
	public String getUser_count() {
		return user_count;
	}
	public String getDisplayorder() {
		return displayorder;
	}
	public String getDateline() {
		return dateline;
	}
	public String getStatus() {
		return status;
	}
	public String getCrter() {
		return crter;
	}
	public String getCrtdate() {
		return crtdate;
	}
	public String getOpter() {
		return opter;
	}
	public String getOptdate() {
		return optdate;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setWeid(String weid) {
		this.weid = weid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	public void setLimit_price(String limit_price) {
		this.limit_price = limit_price;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setUser_count(String user_count) {
		this.user_count = user_count;
	}
	public void setDisplayorder(String displayorder) {
		this.displayorder = displayorder;
	}
	public void setDateline(String dateline) {
		this.dateline = dateline;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCrter(String crter) {
		this.crter = crter;
	}
	public void setCrtdate(String crtdate) {
		this.crtdate = crtdate;
	}
	public void setOpter(String opter) {
		this.opter = opter;
	}
	public void setOptdate(String optdate) {
		this.optdate = optdate;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
}
