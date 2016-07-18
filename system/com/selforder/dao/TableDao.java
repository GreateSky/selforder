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
}
