package com.selforder.bean;

import java.util.Date;

public class Activity extends baseBean {
	private static final long serialVersionUID = 7515614870808465411L;
	private String id;
	private String weid;
	private String storeid;
	private String title;
	private String url;
	private String status;
	private Date begindate;
	private Date enddate;
	private double discount;
	private double leastcost;
	private String crter;
	private Date crtdate;
	private String opter;
	private Date optdate;
	private int deleted;
	private String type;
	private String remark;
	private String imgid;
	private String goods;//已关联食谱（多个食谱之间用逗号隔开）
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
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
	public String getCrter() {
		return crter;
	}
	public String getOpter() {
		return opter;
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
	public void setCrter(String crter) {
		this.crter = crter;
	}
	public void setOpter(String opter) {
		this.opter = opter;
	}
	public Date getBegindate() {
		return begindate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public Date getCrtdate() {
		return crtdate;
	}
	public Date getOptdate() {
		return optdate;
	}
	public int getDeleted() {
		return deleted;
	}
	public String getType() {
		return type;
	}
	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public void setCrtdate(Date crtdate) {
		this.crtdate = crtdate;
	}
	public void setOptdate(Date optdate) {
		this.optdate = optdate;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getDiscount() {
		return discount;
	}
	public double getLeastcost() {
		return leastcost;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public void setLeastcost(double leastcost) {
		this.leastcost = leastcost;
	}
	public String getRemark() {
		return remark;
	}
	public String getImgid() {
		return imgid;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setImgid(String imgid) {
		this.imgid = imgid;
	}

}
