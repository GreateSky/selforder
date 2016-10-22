package com.selforder.bean;

import com.automobile.bean.baseBean;

/**
 * 门店实体类
 * @author xingwanzhao
 *
 * 2016-5-16
 */
public class Shop extends baseBean{
	private static final long serialVersionUID = 8435660058473839561L;
	private String id;
	private String weid;
	private String title;
	private String logo;
	private String info;
	private String announce;
	private String tel;
	private String location_p;
	private String location_c;
	private String location_a;
	private String address;
	private String lat;
	private String lng;
	private String password;
	private String hours;
	private String recharging_password;
	private String thumb_url;
	private String enable_wifi;
	private String enable_card;
	private String enable_room;
	private String enable_park;
	private String is_rest;
	private String is_show;
	private String is_meal;
	private String is_delivery;
	private String is_reservation;
	private String is_queue;
	private int delivery_within_days;
	private int delivery_radius;
	private int not_in_delivery_radius;
	private int sendingprice; 
	private int consume; 
	private int level;
	private String updatetime;
	private String dateline;
	private String content;
	private String begintime;
	private String endtime;
	private String bid;//商户ID
	private int seconsumendingprice;
	public int getSeconsumendingprice() {
		return seconsumendingprice;
	}
	public void setSeconsumendingprice(int seconsumendingprice) {
		this.seconsumendingprice = seconsumendingprice;
	}
	public int getDelivery_within_days() {
		return delivery_within_days;
	}
	public int getDelivery_radius() {
		return delivery_radius;
	}
	public int getNot_in_delivery_radius() {
		return not_in_delivery_radius;
	}
	public int getSendingprice() {
		return sendingprice;
	}
	public int getConsume() {
		return consume;
	}
	public int getLevel() {
		return level;
	}
	public void setDelivery_within_days(int delivery_within_days) {
		this.delivery_within_days = delivery_within_days;
	}
	public void setDelivery_radius(int delivery_radius) {
		this.delivery_radius = delivery_radius;
	}
	public void setNot_in_delivery_radius(int not_in_delivery_radius) {
		this.not_in_delivery_radius = not_in_delivery_radius;
	}
	public void setSendingprice(int sendingprice) {
		this.sendingprice = sendingprice;
	}
	public void setConsume(int consume) {
		this.consume = consume;
	}
	public void setLevel(int level) {
		this.level = level;
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
	public String getTitle() {
		return title;
	}
	public String getLogo() {
		return logo;
	}
	public String getInfo() {
		return info;
	}
	public String getAnnounce() {
		return announce;
	}
	public String getTel() {
		return tel;
	}
	public String getLocation_p() {
		return location_p;
	}
	public String getLocation_c() {
		return location_c;
	}
	public String getLocation_a() {
		return location_a;
	}
	public String getAddress() {
		return address;
	}
	public String getLat() {
		return lat;
	}
	public String getLng() {
		return lng;
	}
	public String getPassword() {
		return password;
	}
	public String getHours() {
		return hours;
	}
	public String getRecharging_password() {
		return recharging_password;
	}
	public String getThumb_url() {
		return thumb_url;
	}
	public String getEnable_wifi() {
		return enable_wifi;
	}
	public String getEnable_card() {
		return enable_card;
	}
	public String getEnable_room() {
		return enable_room;
	}
	public String getEnable_park() {
		return enable_park;
	}
	public String getIs_rest() {
		return is_rest;
	}
	public String getIs_show() {
		return is_show;
	}
	public String getIs_meal() {
		return is_meal;
	}
	public String getIs_delivery() {
		return is_delivery;
	}
	public String getIs_reservation() {
		return is_reservation;
	}
	public String getIs_queue() {
		return is_queue;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public String getDateline() {
		return dateline;
	}
	public String getContent() {
		return content;
	}
	public String getBegintime() {
		return begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setWeid(String weid) {
		this.weid = weid;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public void setAnnounce(String announce) {
		this.announce = announce;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setLocation_p(String location_p) {
		this.location_p = location_p;
	}
	public void setLocation_c(String location_c) {
		this.location_c = location_c;
	}
	public void setLocation_a(String location_a) {
		this.location_a = location_a;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public void setRecharging_password(String recharging_password) {
		this.recharging_password = recharging_password;
	}
	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}
	public void setEnable_wifi(String enable_wifi) {
		this.enable_wifi = enable_wifi;
	}
	public void setEnable_card(String enable_card) {
		this.enable_card = enable_card;
	}
	public void setEnable_room(String enable_room) {
		this.enable_room = enable_room;
	}
	public void setEnable_park(String enable_park) {
		this.enable_park = enable_park;
	}
	public void setIs_rest(String is_rest) {
		this.is_rest = is_rest;
	}
	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}
	public void setIs_meal(String is_meal) {
		this.is_meal = is_meal;
	}
	public void setIs_delivery(String is_delivery) {
		this.is_delivery = is_delivery;
	}
	public void setIs_reservation(String is_reservation) {
		this.is_reservation = is_reservation;
	}
	public void setIs_queue(String is_queue) {
		this.is_queue = is_queue;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public void setDateline(String dateline) {
		this.dateline = dateline;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	
}
