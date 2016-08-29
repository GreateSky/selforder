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
	private int limit_price;
	private String title;
	private int user_count;
	private int displayorder;
	private String dateline;
	private String status;
	private String crter;
	private String crtdate;
	private String opter;
	private String optdate;
	private int deleted;
	private String room_id;
	private String room_name;
	private int tabnum;//餐桌数量
	private String qrcodeid;//餐桌二维码id
	public int getTabnum() {
		return tabnum;
	}
	public void setTabnum(int tabnum) {
		this.tabnum = tabnum;
	}
	public String getRoom_id() {
		return room_id;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
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
	
	public String getDateline() {
		return dateline;
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
	public void setDateline(String dateline) {
		this.dateline = dateline;
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
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getQrcodeid() {
		return qrcodeid;
	}
	public void setQrcodeid(String qrcodeid) {
		this.qrcodeid = qrcodeid;
	}
	public int getLimit_price() {
		return limit_price;
	}
	public int getUser_count() {
		return user_count;
	}
	public int getDisplayorder() {
		return displayorder;
	}
	public void setLimit_price(int limit_price) {
		this.limit_price = limit_price;
	}
	public void setUser_count(int user_count) {
		this.user_count = user_count;
	}
	public void setDisplayorder(int displayorder) {
		this.displayorder = displayorder;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
