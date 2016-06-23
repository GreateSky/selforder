package com.automobile.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.automobile.bean.Parts;
import com.automobile.dao.SystemMgrDao;
import com.automobile.util.JsonResultUtil;

public class SystemMgrDaoImpl extends SqlSessionDaoSupport implements SystemMgrDao {

	@Override
	public List<Map> getPartsList(Parts parts) {
		List<Map> partList = new ArrayList<Map>();
		try{
			partList = (List<Map>)getSqlSession().selectList("com.greatesky.systemmgr.getParts", parts);
		}catch(Exception e){
			e.printStackTrace();
		}
		return partList;
	}

	@Override
	public String insertParts(Parts parts) {
		String result = "";
		try{
			int temp = getSqlSession().insert("com.greatesky.systemmgr.insertParts", parts);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "保存成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "保存失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String updateParts(Parts parts) {
		String result = "";
		try{
			int temp = getSqlSession().insert("com.greatesky.systemmgr.updateParts", parts);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "操作成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "操作成功！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String insertUpload(Map file){
		String result = "";
		try{
			int temp = getSqlSession().insert("com.greatesky.systemmgr.insertUpload", file);
			if(temp > 0){
				result = JsonResultUtil.getJsonResult(0, "success", "操作成功！");
			}else{
				result = JsonResultUtil.getJsonResult(-1, "fail", "操作成功！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取附件详情
	 * @param fileid  文件ID
	 * @return
	 */
	public Map getFileInfo(String fileid){
		Map fileinfo = new HashMap();
		try{
			fileinfo = (Map)getSqlSession().selectOne("com.greatesky.systemmgr.getFileInfo", fileid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return fileinfo;
	}

}
