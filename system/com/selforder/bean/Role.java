package com.selforder.bean;

import java.util.Date;

/**
 * 角色实体
 * @author xingwanzhao
 *
 * 2016-6-12
 */
public class Role extends baseBean{
	private static final long serialVersionUID = -2708643481339730001L;
	private String rid;
	private String rcode;
	private String rname;
	private String remark;
	private Date crtdate;
	private String crter;
	private Date optdate;
	private String opter;
	private String resourceid;
	private String resourcename;
	private String oid;//绑定的部门ID
	private String orid;//部门与权限关联ID
	private String bid;//商户ID
	private int deleted;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRid() {
		return rid;
	}
	public String getRcode() {
		return rcode;
	}
	public String getRname() {
		return rname;
	}
	public String getRemark() {
		return remark;
	}
	public Date getCrtdate() {
		return crtdate;
	}
	public String getCrter() {
		return crter;
	}
	public Date getOptdate() {
		return optdate;
	}
	public String getOpter() {
		return opter;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public void setRcode(String rcode) {
		this.rcode = rcode;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setCrtdate(Date crtdate) {
		this.crtdate = crtdate;
	}
	public void setCrter(String crter) {
		this.crter = crter;
	}
	public void setOptdate(Date optdate) {
		this.optdate = optdate;
	}
	public void setOpter(String opter) {
		this.opter = opter;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getResourceid() {
		return resourceid;
	}
	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}
	public String getResourcename() {
		return resourcename;
	}
	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOrid() {
		return orid;
	}
	public void setOrid(String orid) {
		this.orid = orid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
}
