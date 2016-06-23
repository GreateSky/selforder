package com.automobile.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 配件实体类
 * @author xingwanzhao
 *
 * 2016-3-31
 */
public class Parts extends baseBean {
	private static final long serialVersionUID = -1513868053450287406L;
	private String pid;
	private String type;
	private String brand;
	private String pcode;
	private String pname;
	private double price;
	private double costprice;
	private double comprice;
	private String unit;
	private String remark;
	private String content;
	private String imgid;
	private String crter;
	private Date crtdate;
	private int deleted = 0;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCostprice() {
		return costprice;
	}
	public void setCostprice(double costprice) {
		this.costprice = costprice;
	}
	public double getComprice() {
		return comprice;
	}
	public void setComprice(double comprice) {
		this.comprice = comprice;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgid() {
		return imgid;
	}
	public void setImgid(String imgid) {
		this.imgid = imgid;
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
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
}
