package com.selforder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.selforder.bean.Shop;
import com.selforder.bean.Table;
import com.selforder.dao.TableDao;
import com.selforder.service.TableService;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;

public class TableServiceImpl implements TableService {
	private TableDao tableDao;

	public TableDao getTableDao() {
		return tableDao;
	}

	public void setTableDao(TableDao tableDao) {
		this.tableDao = tableDao;
	}

	/**
	 * 获取餐桌列表
	 * @param table
	 * @return
	 */
	@Override
	public String tableList(Table table) {
		String result = "";
		Map resultMap = new HashMap();
		String bid = new Context().getLoginUserInfo().getBid();
		table.setWeid(bid);
		try{
			//查询门店列表
			List<Table> tableList = tableDao.tableList(table);
			if(tableList != null && tableList.size()>0){
				//查询统计数
				int count = tableDao.tableListCount(table);
				resultMap.put("rows", tableList);
				resultMap.put("total", count);
				result = JsonResultUtil.MapToJsonStr(resultMap);
			}else{
				result = JsonResultUtil.getJsonResult(-1,"fail", "无数据!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return result = JsonResultUtil.getJsonResult(-1,"fail", "查询异常!");
		}
		return result;
	}
	
	/**
	 * 获取餐桌详情
	 * @param table
	 * @return
	 */
	@Override
	public String tableInfo(Table table) {
		String result = "";
		try{
			Table tableInfo = tableDao.tableInfo(table);
			if(tableInfo != null){
				result = JsonResultUtil.getJsonResult(0, "success", "", tableInfo);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "未查询到餐桌详情");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "查询异常!");
		}
		return result;
	}

	/**
	 * 新增餐桌
	 * @param table
	 * @return
	 */
	@Override
	public String insertTable(Table table) {
		String result = "";
		try{
			String crter = new Context().getLoginUserInfo().getCode();
			table.setCrter(crter);
			int temp = tableDao.insertTable(table);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "新增餐桌成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "新增餐桌失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}

	/**
	 * 更新餐桌
	 * @param table
	 * @return
	 */
	@Override
	public String updateTable(Table table) {
		String result = "";
		try{
			String opter = new Context().getLoginUserInfo().getCode();
			table.setOpter(opter);
			int temp = tableDao.updateTable(table);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "更新餐桌成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "更新餐桌失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}

}
