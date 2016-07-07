package com.selforder.bean;

import java.io.Serializable;
import java.util.Date;

import com.automobile.bean.baseBean;

/**
 * 商户实体类
 * @author xingwanzhao
 *
 * 2016-5-16
 */
public class Business extends baseBean{
	private static final long serialVersionUID = 4410066415146622186L;
	private String bid;
	private String bcode;
	private String bname;
	private String legaler;
	private String phone;
	private String email;
	private String faxes;
	private String address;
	private int status;
	private Date begindate;
	private Date enddate;
	private String appid;
	private String appsecret;
	private String licenseid;
	private String crter;
	private Date crtdate;
	private String opter;
	private Date optdate;
	private int deleted;
	private String sysadmin;
	private String password;
	private String rid;//权限ID
	private	String rname;//权限名称
	private String rcode;//权限编码
	private String rbid;//商户与权限关联ID
	public String getRbid() {
		return rbid;
	}
	public void setRbid(String rbid) {
		this.rbid = rbid;
	}
	public String getRcode() {
		return rcode;
	}
	public void setRcode(String rcode) {
		this.rcode = rcode;
	}
	private String rbrid;//商户与权限关联表ID
	public String getRbrid() {
		return rbrid;
	}
	public void setRbrid(String rbrid) {
		this.rbrid = rbrid;
	}
	public String getRid() {
		return rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBcode() {
		return bcode;
	}
	public void setBcode(String bcode) {
		this.bcode = bcode;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getLegaler() {
		return legaler;
	}
	public void setLegaler(String legaler) {
		this.legaler = legaler;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFaxes() {
		return faxes;
	}
	public void setFaxes(String faxes) {
		this.faxes = faxes;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getBegindate() {
		return begindate;
	}
	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	public String getLicenseid() {
		return licenseid;
	}
	public void setLicenseid(String licenseid) {
		this.licenseid = licenseid;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSysadmin() {
		return sysadmin;
	}
	public String getPassword() {
		return password;
	}
	public void setSysadmin(String sysadmin) {
		this.sysadmin = sysadmin;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
