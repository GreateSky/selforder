package com.selforder.bean;

import java.util.Date;

/**
 * 微信服务号Token
 * @author xingwanzhao
 *
 * 2016-9-6
 */
public class AccessToken extends baseBean {
	private static final long serialVersionUID = -8283205854019311718L;
	private String id;
	private String weid;
	private String cropid;
	private String secret;
	private String accesstoken;
	private Date expirydate;
	private String isvalid;
	private String crter;
	private Date crtdate;
	private String opter;
	private Date optdate;
	private int deleted;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}
	public String getWeid() {
		return weid;
	}
	public String getCropid() {
		return cropid;
	}
	public String getSecret() {
		return secret;
	}
	public String getAccesstoken() {
		return accesstoken;
	}
	public Date getExpirydate() {
		return expirydate;
	}
	public String getIsvalid() {
		return isvalid;
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
	public void setCropid(String cropid) {
		this.cropid = cropid;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}
	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
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
}
