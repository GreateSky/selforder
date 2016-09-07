package com.selforder.weixin;

import com.selforder.util.JsonResultUtil;

/**
 * 微信接口统一服务类
 * @author xingwanzhao
 *
 * 2016-9-6
 */
public class WeixinApi {
	private static final String  AccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	/***
	 * 获取服务号accesstoken
	 * @param appid 
	 * @param secret
	 * @return
	 */
	public static String getAccessToken(String appid,String secret){
		String result = "";
		if(null == appid || "".equals(appid) || null == secret || "".equals(secret)){
			return JsonResultUtil.getJsonResult(-1, "fail", "获取accesstoken参数异常！");
		}
		String url = AccessTokenUrl;
		url = url.replace("APPID",appid).replace("APPSECRET",secret);
		result = HttpClientUtil.httpRequest(url, "GET", "");
		return result;
	}
}
