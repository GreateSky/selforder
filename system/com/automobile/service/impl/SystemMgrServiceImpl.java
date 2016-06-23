package com.automobile.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.automobile.bean.Parts;
import com.automobile.dao.SystemMgrDao;
import com.automobile.service.SystemMgrService;
import com.automobile.weixin.TicketUtil;
import com.automobile.weixin.WeiXinUtil;

public class SystemMgrServiceImpl implements SystemMgrService {
	private SystemMgrDao systemMgrDao;
	public SystemMgrDao getSystemMgrDao() {
		return systemMgrDao;
	}

	public void setSystemMgrDao(SystemMgrDao systemMgrDao) {
		this.systemMgrDao = systemMgrDao;
	}

	@Override
	public List<Map> getPartsList(Parts parts) {
		// TODO Auto-generated method stub
		return systemMgrDao.getPartsList(parts);
	}

	@Override
	public String insertParts(Parts parts) {
		// TODO Auto-generated method stub
		return systemMgrDao.insertParts(parts);
	}

	@Override
	public String updateParts(Parts parts) {
		// TODO Auto-generated method stub
		return systemMgrDao.updateParts(parts);
	}
	
	/**
	 * 获取微信JsApi信息
	 * @param url
	 * @return
	 */
	public Map<String,String> getJsApiInfo(String url){
		Map<String,String> res = new HashMap<String, String>();
		try{
			String accesstoken = TicketUtil.getAccessToken();
			String jsapi_ticket = TicketUtil.getJsapi_ticket(accesstoken);
			res = WeiXinUtil.getJsApiSignInfo(jsapi_ticket, url);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

}
