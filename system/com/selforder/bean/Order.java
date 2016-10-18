package com.selforder.bean;

import java.util.Date;

import com.automobile.bean.baseBean;

/**
 * 订单实体类
 * @author xingwanzhao
 *
 * 2016-8-1
 */
public class Order extends baseBean {

	private static final long serialVersionUID = -1428432727825289907L;
	private String id;
	private String weid;
	private String storeid;
	private String from_user;
	private String ordersn;
	private String ordercode;
	private double totalprice;
	private String status;
	private int ispay;
	private int paytype;
	private String transid;
	private String username;
	private String address;
	private String tel;
	private String meal_time;
	private int counts;
	private int seat_type;
	private int carports;
	private double realprice;
	private int dining_mode;
	private String remark;
	private String taste;
	private String tableid;
	private int print_sta;
	private int isticket;
	private String title;
	private String dateline;
	private String crter;
	private Date crtdate;
	private String opter;
	private Date optdate;
	private int deleted;
	private Date begindate;
	private Date enddate;
	private long begindateLine;
	private long enddateLine;
	private String tablecode;//餐桌号
	private String transferid;
	private String transfername;
	public String getTransfername() {
		return transfername;
	}
	public void setTransfername(String transfername) {
		this.transfername = transfername;
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
	public String getFrom_user() {
		return from_user;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public int getIspay() {
		return ispay;
	}
	public int getPaytype() {
		return paytype;
	}
	public String getTransid() {
		return transid;
	}
	public String getUsername() {
		return username;
	}
	public String getAddress() {
		return address;
	}
	public String getTel() {
		return tel;
	}
	public String getMeal_time() {
		return meal_time;
	}
	public int getCounts() {
		return counts;
	}
	public int getSeat_type() {
		return seat_type;
	}
	public int getCarports() {
		return carports;
	}
	public double getRealprice() {
		return realprice;
	}
	public int getDining_mode() {
		return dining_mode;
	}
	public String getRemark() {
		return remark;
	}
	public String getTaste() {
		return taste;
	}
	public String getTableid() {
		return tableid;
	}
	public int getPrint_sta() {
		return print_sta;
	}
	public int getIsticket() {
		return isticket;
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
	public void setWeid(String weid) {
		this.weid = weid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	public void setFrom_user(String from_user) {
		this.from_user = from_user;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public void setIspay(int ispay) {
		this.ispay = ispay;
	}
	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}
	public void setTransid(String transid) {
		this.transid = transid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setMeal_time(String meal_time) {
		this.meal_time = meal_time;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public void setSeat_type(int seat_type) {
		this.seat_type = seat_type;
	}
	public void setCarports(int carports) {
		this.carports = carports;
	}
	public void setRealprice(double realprice) {
		this.realprice = realprice;
	}
	public void setDining_mode(int dining_mode) {
		this.dining_mode = dining_mode;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setTaste(String taste) {
		this.taste = taste;
	}
	public void setTableid(String tableid) {
		this.tableid = tableid;
	}
	public void setPrint_sta(int print_sta) {
		this.print_sta = print_sta;
	}
	public void setIsticket(int isticket) {
		this.isticket = isticket;
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
	public String getOrdersn() {
		return ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	public String getOrdercode() {
		return ordercode;
	}
	public String getTablecode() {
		return tablecode;
	}
	public void setTablecode(String tablecode) {
		this.tablecode = tablecode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public Date getBegindate() {
		return begindate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setBegindate(Date begindate) {
		this.begindate = begindate;
		if(begindate != null){
			this.setBegindateLine(begindate.getTime()/1000);
		}
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
		if(enddate != null){
			this.setEnddateLine(enddate.getTime()/1000);
		}
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getBegindateLine() {
		return begindateLine;
	}
	public long getEnddateLine() {
		return enddateLine;
	}
	public void setBegindateLine(long begindateLine) {
		this.begindateLine = begindateLine;
	}
	public void setEnddateLine(long enddateLine) {
		this.enddateLine = enddateLine;
	}
	public String getTransferid() {
		return transferid;
	}
	public void setTransferid(String transferid) {
		this.transferid = transferid;
	}
}
