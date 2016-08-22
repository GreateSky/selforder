package com.selforder.bean;

public class Activity extends baseBean {
	private static final long serialVersionUID = 7515614870808465411L;
	private String id;
	private String weid;
	private String storeid;
	private String title;
	private String url;
	private String status;
	private String begindate;
	private String enddate;
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
	public String getTitle() {
		return title;
	}
	public String getUrl() {
		return url;
	}
	public String getStatus() {
		return status;
	}
	public String getBegindate() {
		return begindate;
	}
	public String getEnddate() {
		return enddate;
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
	public void setTitle(String title) {
		this.title = title;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
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
