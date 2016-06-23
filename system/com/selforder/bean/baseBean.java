package com.selforder.bean;

import java.io.Serializable;

/**
 * 基础实体类
 * @author xingwanzhao
 *
 * 2016-4-2
 */
public class baseBean implements Serializable {
	private static final long serialVersionUID = 3745355757797128150L;
	private int pageStart = 0;
	private int pageSize = 0;
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		int start = 0;
		if(pageStart > 0){
			start = (pageStart-1)*pageSize>0?(pageStart-1)*pageSize:0;
		}
		this.pageStart = start;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		pageSize = pageSize>0?pageSize:0;
		this.pageSize = pageSize;
	}
}
