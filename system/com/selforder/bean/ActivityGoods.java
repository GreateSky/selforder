package com.selforder.bean;

import java.util.Date;

public class ActivityGoods extends baseBean{
	private static final long serialVersionUID = -4708333464596313285L;
	private String id;
	private String activityid;
	private String goodsid;
	private String title;
	private double marketprice;
	private double discount_price;//优惠后的价格
	private String crter;
	private Date crtdate;
	private String opter;
	private Date optdate;
	public String getTitle() {
		return title;
	}
	public double getMarketprice() {
		return marketprice;
	}
	public void setTitle(String title) { 
		this.title = title;
	}
	public void setMarketprice(double marketprice) {
		this.marketprice = marketprice;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}
	public String getActivityid() {
		return activityid;
	}
	public String getGoodsid() {
		return goodsid;
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
	public void setId(String id) {
		this.id = id;
	}
	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
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
	private int deleted;
	public double getDiscount_price() {
		return discount_price;
	}
	public void setDiscount_price(double discount_price) {
		this.discount_price = discount_price;
	}
	
}
