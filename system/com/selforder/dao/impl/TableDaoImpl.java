package com.selforder.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.selforder.bean.Table;
import com.selforder.dao.TableDao;

public class TableDaoImpl extends SqlSessionDaoSupport implements TableDao {

	/**
	 * 获取餐桌列表
	 * @param table
	 * @return
	 */
	@Override
	public List<Table> tableList(Table table) throws SQLException {
		return getSqlSession().selectList("com.selforder.table.getTableList", table);
	}
	
	/**
	 * 获取餐桌列表统计数
	 * @param table
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int tableListCount(Table table)throws SQLException{
		return (Integer)getSqlSession().selectOne("com.selforder.table.getTableListCount",table);
	}
	
	/**
	 * 获取餐桌详情
	 * @param table
	 * @return
	 */
	@Override
	public Table tableInfo(Table table) throws SQLException {
		return (Table)getSqlSession().selectOne("com.selforder.table.getTableInfo", table);
	}

	/**
	 * 新增餐桌
	 * @param table
	 * @return
	 */
	@Override
	public int insertTable(Table table) throws SQLException {
		return getSqlSession().insert("com.selforder.table.insertTable", table);
	}

	/**
	 * 更新餐桌
	 * @param table
	 * @return
	 */
	@Override
	public int updateTable(Table table) throws SQLException {
		return getSqlSession().update("com.selforder.table.updateTable",table);
	}
	
	/**
	 * 新增包厢
	 * @param table
	 * @return
	 */
	public int insertRoom (Table table)throws SQLException{
		return getSqlSession().insert("com.selforder.table.insertRoom", table);
	}
	
	/**
	 * 更新包厢
	 * @param table
	 * @return
	 */
	public int updateRoom(Table table)throws SQLException{
		return getSqlSession().update("com.selforder.table.updateRoom",table);
	}
	
	/**
	 * 获取包厢列表 
	 * @param table
	 * @return
	 * @throws SQLException
	 */
	public List<Table> roomList(Table table)throws SQLException{
		return getSqlSession().selectList("com.selforder.table.getRoomList", table);
	}
	
	/**
	 * 删除餐桌与包厢关联信息
	 * @param table
	 * @return
	 */
	public int updateTableRoom(Table table)throws SQLException{
		return getSqlSession().update("com.selforder.table.updateTableRoom",table);
	}
	
	/**
	 * 获取所有餐桌
	 * @param table
	 * @return
	 * @throws SQLException
	 */
	public List<Table> allTableList(Table table)throws SQLException{
		return getSqlSession().selectList("com.selforder.table.getAllTableList", table);
	}

}
