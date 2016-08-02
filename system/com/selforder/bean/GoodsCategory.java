package com.selforder.bean;

import com.automobile.bean.baseBean;

/**
 * 食谱分类实体
 * @author xingwanzhao
 *
 * 2016-7-30
 */
public class GoodsCategory extends baseBean {
	private static final long serialVersionUID = -5658506465603765348L;
	private String id;
	private String weid;
	private String storeid;
	private String name;
	private String parentid;
	private int displayorder;
	private int enabled;
	private String crter;
	private String crtdate;
	private String opter;
	private String optdate;
	private int deleted;
	private int goodsnum;//包含的食谱数量
	public int getGoodsnum() {
		return goodsnum;
	}
	public void setGoodsnum(int goodsnum) {
		this.goodsnum = goodsnum;
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
	public String getName() {
		return name;
	}
	public String getParentid() {
		return parentid;
	}
	public int getDisplayorder() {
		return displayorder;
	}
	public int getEnabled() {
		return enabled;
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
	public void setName(String name) {
		this.name = name;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public void setDisplayorder(int displayorder) {
		this.displayorder = displayorder;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
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
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
}
