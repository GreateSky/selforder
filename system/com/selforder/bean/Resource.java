package com.selforder.bean;

import com.automobile.bean.baseBean;

/**
 * 资源实体类
 * @author xingwanzhao
 *
 * 2016-6-2
 */
public class Resource extends baseBean {
	private static final long serialVersionUID = 892559373690178032L;
	private String rid;
	private String rname;
	private String rurl;
	private String remark;
	private String crter;
	private String crtdate;
	private String deleted;
	public String getRid() {
		return rid;
	}
	public String getRname() {
		return rname;
	}
	public String getRurl() {
		return rurl;
	}
	public String getRemark() {
		return remark;
	}
	public String getCrter() {
		return crter;
	}
	public String getCrtdate() {
		return crtdate;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public void setRurl(String rurl) {
		this.rurl = rurl;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setCrter(String crter) {
		this.crter = crter;
	}
	public void setCrtdate(String crtdate) {
		this.crtdate = crtdate;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
}
