package com.selforder.bean;

import com.automobile.bean.baseBean;

/**
 * 食谱实体类
 * @author xingwanzhao
 *
 * 2016-7-30
 */
public class Goods extends baseBean {
	private static final long serialVersionUID = 6765206105656911021L;
	private String id;
	private String weid;
	private String storeid;
	private int pcate;
	private int ccate;
	private int status;
	private String recommend;
	private int displayorder;
	private String title;
	private String thumb;
	private String unitname;
	private String description;
	private String taste;
	private int isspecial;
	private double marketprice;
	private double productprice;
	private int subcount;
	private String cid;
	private String cname;//所属分类名称
	private String crter;
	private String crtdate;
	private String opter;
	private String optdate;
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
	public String getStoreid() {
		return storeid;
	}
	public int getPcate() {
		return pcate;
	}
	public int getCcate() {
		return ccate;
	}
	public int getStatus() {
		return status;
	}
	public String getRecommend() {
		return recommend;
	}
	public int getDisplayorder() {
		return displayorder;
	}
	public String getTitle() {
		return title;
	}
	public String getThumb() {
		return thumb;
	}
	public String getUnitname() {
		return unitname;
	}
	public String getDescription() {
		return description;
	}
	public String getTaste() {
		return taste;
	}
	public int getIsspecial() {
		return isspecial;
	}
	public double getMarketprice() {
		return marketprice;
	}
	public double getProductprice() {
		return productprice;
	}
	public int getSubcount() {
		return subcount;
	}
	public String getCid() {
		return cid;
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
	public void setPcate(int pcate) {
		this.pcate = pcate;
	}
	public void setCcate(int ccate) {
		this.ccate = ccate;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public void setDisplayorder(int displayorder) {
		this.displayorder = displayorder;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setTaste(String taste) {
		this.taste = taste;
	}
	public void setIsspecial(int isspecial) {
		this.isspecial = isspecial;
	}
	public void setMarketprice(double marketprice) {
		this.marketprice = marketprice;
	}
	public void setProductprice(double productprice) {
		this.productprice = productprice;
	}
	public void setSubcount(int subcount) {
		this.subcount = subcount;
	}
	public void setCid(String cid) {
		this.cid = cid;
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}
