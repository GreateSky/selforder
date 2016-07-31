package com.selforder.service;

import java.sql.SQLException;
import java.util.List;

import com.selforder.bean.Table;

/**
 * 餐桌管理业务层
 * @author xingwanzhao
 *
 * 2016-7-18
 */
public interface TableService {
	/**
	 * 获取餐桌列表
	 * @param table
	 * @return
	 */
	public String tableList(Table table) ;
	
	/**
	 * 获取餐桌详情
	 * @param table
	 * @return
	 */
	public String tableInfo(Table table);
	
	/**
	 * 新增餐桌
	 * @param table
	 * @return
	 */
	public String insertTable (Table table);
	
	/**
	 * 更新餐桌
	 * @param table
	 * @return
	 */
	public String updateTable(Table table);
	
	/**
	 * 新增包厢
	 * @param table
	 * @return
	 */
	public String insertRoom (Table table);
	
	/**
	 * 更新包厢
	 * @param table
	 * @return
	 */
	public String updateRoom(Table table);
	
	/**
	 * 获取包厢列表 
	 * @param table
	 * @return
	 */
	public String roomList(Table table);
	
	/**
	 * 获取所有餐桌
	 * @param table
	 * @return
	 */
	public String allTableList(Table table);
}
