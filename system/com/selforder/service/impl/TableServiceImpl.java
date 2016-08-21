package com.selforder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.automobile.dao.SystemMgrDao;
import com.automobile.listener.SystemConfig;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.selforder.bean.Table;
import com.selforder.dao.TableDao;
import com.selforder.service.TableService;
import com.selforder.util.Context;
import com.selforder.util.JsonResultUtil;
import com.selforder.util.QRcode.QRCodeEvents;

public class TableServiceImpl implements TableService {
	private SystemMgrDao systemMgrDao;
	private TableDao tableDao;

	public SystemMgrDao getSystemMgrDao() {
		return systemMgrDao;
	}

	public void setSystemMgrDao(SystemMgrDao systemMgrDao) {
		this.systemMgrDao = systemMgrDao;
	}

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
	public String tableList(Table table){
		String result = "";
		Map resultMap = new HashMap();
		String bid = new Context().getLoginUserInfo().getBid();
		String sid = new Context().getLoginUserInfo().getSid();
		table.setWeid(bid);
		table.setStoreid(sid);
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
			String bid = new Context().getLoginUserInfo().getBid();
			table.setCrter(crter);
			table.setWeid(bid);
			//根据餐桌信息生成二维码
			String content = "";//二维码内容
			String domain_selforder = SystemConfig.get("domain_selforder");//获取基本URL地址
			content = domain_selforder + "/system/order/createOrder.jsp?bid="+bid+"&sid="+table.getStoreid();//组装URL地址
			String qrcodeInfo = QRCodeEvents.createTableQRcode(content, bid, table.getStoreid());//创建二维码
			String saveFileInfo = "";//保存二维码结果
			String fileid = "";//二维码文件ID
			if(qrcodeInfo.indexOf("fail") != -1){//创建二维码失败
				return qrcodeInfo;
			}else{//二维码成功
				//保存附件信息至附件表
				Gson gson = new Gson();
				HashMap map = gson.fromJson(qrcodeInfo,HashMap.class);
				LinkedTreeMap fileInfo = (LinkedTreeMap)map.get("data");
				System.out.println(fileInfo.get("fileid"));
				fileid = fileInfo.get("fileid").toString();
				saveFileInfo = systemMgrDao.insertUpload(fileInfo);
				
			}
			if(saveFileInfo.indexOf("success") == -1){//保存附件信息至附件表异常
				return saveFileInfo;
			}
			table.setQrcodeid(fileid);//餐桌二维码ID赋值
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
	
	/**
	 * 新增包厢
	 * @param table
	 * @return
	 */
	public String insertRoom (Table table){
		String result = "";
		try{
			String crter = new Context().getLoginUserInfo().getCode();
			table.setCrter(crter);
			int temp = tableDao.insertRoom(table);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "新增包厢成功!");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "新增包厢失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 更新包厢
	 * @param table
	 * @return
	 */
	public String updateRoom(Table table){
		String result = "";
		try{
			String opter = new Context().getLoginUserInfo().getCode();
			table.setOpter(opter);
			int temp = tableDao.updateRoom(table);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "更新包厢成功!");
				//判断是否执行的删除包厢操作如果是则同步删除餐桌与该包厢的关联信息
				int deleted = table.getDeleted();
				if(deleted == 1){
					temp = tableDao.updateTableRoom(table);
				}
				
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "更新包厢失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 获取包厢列表 
	 * @param table
	 * @return
	 */
	public String roomList(Table table){
		String result = "";
		try{
			List<Table> tablelist = tableDao.roomList(table);
			if(null != tablelist && tablelist.size()> 0){
				result = JsonResultUtil.getJsonResult(0, "success", "查询数据成功!", tablelist);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "查询数据为空!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}
	
	/**
	 * 获取所有餐桌
	 * @param table
	 * @return
	 */
	public String allTableList(Table table){
		String result = "";
		try{
			List<Table> tablelist = tableDao.allTableList(table);
			if(null != tablelist && tablelist.size()> 0){
				result = JsonResultUtil.getJsonResult(0, "success", "查询数据成功!", tablelist);
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "查询数据为空!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return JsonResultUtil.getJsonResult(-1, "fail", "操作异常!");
		}
		return result;
	}

}
