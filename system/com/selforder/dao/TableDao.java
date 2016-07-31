package com.selforder.dao;

import java.sql.SQLException;
import java.util.List;

import com.selforder.bean.Table;

/**
 * 餐桌管理持久层
 * @author xingwanzhao
 *
 * 2016-7-18
 */
public interface TableDao {
	/**
	 * 获取餐桌列表
	 * @param table
	 * @return
	 */
	public List<Table> tableList(Table table) throws SQLException;
	
	/**
	 * 获取餐桌列表统计数
	 * @param table
	 * @return
	 * @throws SQLException
	 */
	public int tableListCount(Table table)throws SQLException;
	
	/**
	 * 获取餐桌详情
	 * @param table
	 * @return
	 */
	public Table tableInfo(Table table)throws SQLException;
	
	/**
	 * 新增餐桌
	 * @param table
	 * @return
	 */
	public int insertTable (Table table)throws SQLException;
	
	/**
	 * 更新餐桌
	 * @param table
	 * @return
	 */
	public int updateTable(Table table)throws SQLException;
	
	/**
	 * 新增包厢
	 * @param table
	 * @return
	 */
	public int insertRoom (Table table)throws SQLException;
	
	/**
	 * 更新包厢
	 * @param table
	 * @return
	 */
	public int updateRoom(Table table)throws SQLException;
	
	/**
	 * 获取包厢列表 
	 * @param table
	 * @return
	 * @throws SQLException
	 */
	public List<Table> roomList(Table table)throws SQLException; 
	
	/**
	 * 删除餐桌与包厢关联信息
	 * @param table
	 * @return
	 */
	public int updateTableRoom(Table table)throws SQLException;
	
	/**
	 * 获取所有餐桌
	 * @param table
	 * @return
	 * @throws SQLException
	 */
	public List<Table> allTableList(Table table)throws SQLException;
}
