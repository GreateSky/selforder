package com.selforder.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.selforder.bean.AccessToken;
import com.selforder.bean.Business;
import com.selforder.dao.BusinessDao;
import com.selforder.dao.WeixinApiDao;
import com.selforder.service.WeixinApiService;
import com.selforder.util.JsonResultUtil;
import com.selforder.weixin.WeixinApi;

/**
 * 微信接口服务实现类
 * @author xingwanzhao
 *
 * 2016-9-6
 */
public class WeixinApiServiceImpl implements WeixinApiService {
	private static Logger logger = Logger.getLogger(WeixinApiServiceImpl.class);
	private WeixinApiDao weixinApiDao;
	private BusinessDao businessDao;
	public WeixinApiDao getWeixinApiDao() {
		return weixinApiDao;
	}
	public void setWeixinApiDao(WeixinApiDao weixinApiDao) {
		this.weixinApiDao = weixinApiDao;
	}
	public BusinessDao getBusinessDao() {
		return businessDao;
	}
	public void setBusinessDao(BusinessDao businessDao) {
		this.businessDao = businessDao;
	}
	
	/**
	 * 获取微信服务号AccessToken
	 * @param weid
	 * @return
	 */
	@Override
	public String getAccessToken(String weid) {
		String result = "";
		try{
			//检查参数
			if(null == weid || "".equals(weid)){
				logger.info("获取Accesstoken的weid为空");
				return JsonResultUtil.getJsonResult(-1, "fail", "获取Accesstoken的weid为空");
			}
			//查询商户当前有效的token
			AccessToken token = weixinApiDao.getAccessToken(weid);
			//当前商户存在有效的token
			if(null != token){
				//判断当前token的失效 
				return "{\"retCode\":0,\"status\":\"success\",\"accesstoken\":\""+token.getAccesstoken()+"\"}";
			}else{
			//不存在有效token
				//根据weid获取商户详情
				Business temp = new Business();
				temp.setBid(weid);
				Business business = businessDao.businessInfo(temp);//根据bid查询商户信息
				if(null == business){
					logger.info("商户信息为空");
					return JsonResultUtil.getJsonResult(-1, "fail", "商户信息为空");
				}
				//将当前商户下所有有效标识的token置为无效
				int failuerToken = weixinApiDao.updateToken(weid);
				//获取新的token
				String weixin_result = WeixinApi.getAccessToken(business.getAppid(), business.getAppsecret());
				Gson gson = new Gson();
				Map wmp = gson.fromJson(weixin_result, Map.class);
				String currToken = "";//获取到的新的token
				Double expires_in = 0.00;//有效时间间隔
				//判断获取的token
				if(weixin_result.indexOf("access_token") != -1){
					currToken = (String)wmp.get("access_token");
					expires_in = (Double)wmp.get("expires_in");
				}else{
					logger.info("获取服务号accesstoken失败！");
					return JsonResultUtil.getJsonResult(-1, "fail", "获取服务号accesstoken失败！");
				}
				//计算新的有效时间
				Calendar calendar = Calendar.getInstance();
				calendar.add(calendar.SECOND, expires_in.intValue());
				Date expirydate = calendar.getTime();
				//插入新的token信息
				AccessToken temp_token = new AccessToken();
				temp_token.setWeid(weid);
				temp_token.setCropid(business.getAppid());
				temp_token.setSecret(business.getAppsecret());
				temp_token.setAccesstoken(currToken);
				temp_token.setExpirydate(expirydate);
				temp_token.setIsvalid("1");
				temp_token.setDeleted(0);
				int insertToken = weixinApiDao.insertToken(temp_token);
				result = "{\"retCode\":0,\"status\":\"success\",\"accesstoken\":\""+currToken+"\"}";
			}
		}catch(Exception e){
			logger.error("获取微信服务号AccessToken异常",e);
			return JsonResultUtil.getJsonResult(-1, "fail", "获取微信服务号AccessToken异常");
		}
		return result;
	}

}
