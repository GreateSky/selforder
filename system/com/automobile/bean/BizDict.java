package com.automobile.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务字典实体类
 * @author Administrator
 *
 */
public class BizDict implements Serializable {
	private static final long serialVersionUID = 3861089488896353236L;
	private String wid = "";
	private String wcode = "";
	private String wvalue = "";
	private String wtext = "";
	private String parentid = "";
	private String parentcode = "";
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	private int seq = 0;
	private String content = "";
	private Date crtdate;
	private String crter = "";
	private int deleted = 0;
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public String getWcode() {
		return wcode;
	}
	public void setWcode(String wcode) {
		this.wcode = wcode;
	}
	public String getWvalue() {
		return wvalue;
	}
	public void setWvalue(String wvalue) {
		this.wvalue = wvalue;
	}
	public String getWtext() {
		return wtext;
	}
	public void setWtext(String wtext) {
		this.wtext = wtext;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCrtdate() {
		return crtdate;
	}
	public void setCrtdate(Date crtdate) {
		this.crtdate = crtdate;
	}
	public String getCrter() {
		return crter;
	}
	public void setCrter(String crter) {
		this.crter = crter;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getParentcode() {
		return parentcode;
	}
	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}
}
