package com.selforder.bean;

import java.io.Serializable;
import java.util.Date;

import com.automobile.bean.baseBean;

/**
 * 门店实体类
 * @author xingwanzhao
 *
 * 2016-5-16
 */
public class Shop extends baseBean{
	private static final long serialVersionUID = -9098139579195531642L;
	private String sid;
	private String sname;
	private String scode;
	private String bid;
	private String bname;
	private String linkman;
	private String phone;
	private String address;
	private Double longitude;
	private Double latitude;
	private int isoutsell;
	private int isarray;
	private String shopimg;
	private String crter;
	private Date crtdate;
	private String opter;
	private Date optdate;
	private int deleted;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public int getIsoutsell() {
		return isoutsell;
	}
	public void setIsoutsell(int isoutsell) {
		this.isoutsell = isoutsell;
	}
	public int getIsarray() {
		return isarray;
	}
	public void setIsarray(int isarray) {
		this.isarray = isarray;
	}
	public String getCrter() {
		return crter;
	}
	public void setCrter(String crter) {
		this.crter = crter;
	}
	public Date getCrtdate() {
		return crtdate;
	}
	public void setCrtdate(Date crtdate) {
		this.crtdate = crtdate;
	}
	public String getOpter() {
		return opter;
	}
	public void setOpter(String opter) {
		this.opter = opter;
	}
	public Date getOptdate() {
		return optdate;
	}
	public void setOptdate(Date optdate) {
		this.optdate = optdate;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getShopimg() {
		return shopimg;
	}
	public void setShopimg(String shopimg) {
		this.shopimg = shopimg;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}

	
}
