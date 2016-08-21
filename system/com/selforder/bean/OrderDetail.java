package com.selforder.bean;

import java.util.Date;

import com.automobile.bean.baseBean;

public class OrderDetail extends baseBean {
	private static final long serialVersionUID = 2337363095593076679L;
	private String did;
	private String oid;
	private int dno;
	private String goods_id;
	private String goods_name;
	private int num;
	private double price;
	private double cost;
	private String crter;
	private Date crtdate;
	private String opter;
	private Date optdate;
	private int deleted;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDid() {
		return did;
	}
	public String getOid() {
		return oid;
	}
	public int getDno() {
		return dno;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public int getNum() {
		return num;
	}
	public double getPrice() {
		return price;
	}
	public double getCost() {
		return cost;
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
	public void setDid(String did) {
		this.did = did;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public void setDno(int dno) {
		this.dno = dno;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setCost(double cost) {
		this.cost = cost;
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
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
}
