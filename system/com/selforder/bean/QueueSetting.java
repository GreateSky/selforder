package com.selforder.bean;

import java.util.Date;

/**
 * 队列设置实体类	
 * @author xingwanzhao
 *
 * 2016-8-15
 */
public class QueueSetting extends baseBean{
	private static final long serialVersionUID = -1994075987246802681L;
	private String id;
	private String weid;
	private String storeid;
	private String title;
	private String limit_num;
	private String prefix;
	private String starttime;
	private String endtime;
	private String notify_number;
	private String displayorder;
	private String dateline;
	private String status;
	private String crter;
	private Date crtdate;
	private String opter;
	private Date optdate;
	private int deleted;
	private String currnNum;
	private String nextNum; //下一个队列号
	private int waitCount; //等待人数
	private int tabNum;//可用餐桌数
	public String getId() {
		return id;
	}
	public String getWeid() {
		return weid;
	}
	public String getStoreid() {
		return storeid;
	}
	public String getTitle() {
		return title;
	}
	public String getLimit_num() {
		return limit_num;
	}
	public String getPrefix() {
		return prefix;
	}
	public String getStarttime() {
		return starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public String getNotify_number() {
		return notify_number;
	}
	public String getDisplayorder() {
		return displayorder;
	}
	public String getDateline() {
		return dateline;
	}
	public String getStatus() {
		return status;
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
	public void setTitle(String title) {
		this.title = title;
	}
	public void setLimit_num(String limit_num) {
		this.limit_num = limit_num;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public void setNotify_number(String notify_number) {
		this.notify_number = notify_number;
	}
	public void setDisplayorder(String displayorder) {
		this.displayorder = displayorder;
	}
	public void setDateline(String dateline) {
		this.dateline = dateline;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getNextNum() {
		return nextNum;
	}
	public int getWaitCount() {
		return waitCount;
	}
	public int getTabNum() {
		return tabNum;
	}
	public void setNextNum(String nextNum) {
		this.nextNum = this.prefix+nextNum;
	}
	public void setWaitCount(int waitCount) {
		this.waitCount = waitCount;
	}
	public void setTabNum(int tabNum) {
		this.tabNum = tabNum;
	}
	public String getCurrnNum() {
		return currnNum;
	}
	public void setCurrnNum(String currnNum) {
		this.currnNum = this.prefix+currnNum;
	}
}
