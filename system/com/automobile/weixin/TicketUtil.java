package com.automobile.weixin;

import com.automobile.util.JsonResultUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 各类微信验证票据关联类
 * @author xingwanzhao
 *
 * 2016-4-6
 */
public class TicketUtil {
	//获取access_token链接
	public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//获取JSAPI_TICKET_URL
	public final static String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	private final static String APPID = "wxd76d6dbb3d0f4cb6";
	private final static String APPSECRET = "e8a672a335c35921395651e9ef5ea976";
	private static Gson gson=new Gson();
	
	/**
	 * 获取accesstoken
	 * @param CORPID
	 * @param CORPSECRET
	 * @return
	 */
	public static String getAccessToken()  {
		String requestUrl = ACCESS_TOKEN_URL.replace("APPID", APPID).replace(
				"APPSECRET", APPSECRET);
		String str = HttpClientUtil.httpRequest(requestUrl, "GET", null);
		String access_token= null;
		if(null==str){
			return str;
		}else if(str.indexOf("ok") == -1){
			return JsonResultUtil.getJsonResult(-1, "fail", "获取accessToken失败！");
		}else{
			JsonObject object = gson.fromJson(str, JsonObject.class);
			access_token =(object.get("access_token")).getAsString();	
		}
		return access_token;
	}
	
	/**
	 * 获取jsapi_ticket
	 * @param ACCESS_TOKEN
	 * @return
	 */
	public static String getJsapi_ticket(String ACCESS_TOKEN)  {
		String requestUrl = JSAPI_TICKET_URL.replace("ACCESS_TOKEN", ACCESS_TOKEN);
		String str = HttpClientUtil.httpRequest(requestUrl, "GET", null);
		String ticket= null;
		if(null==str) return str;
		JsonObject object = gson.fromJson(str, JsonObject.class);
		
		ticket =(object.get("ticket")).getAsString();	
		return ticket;
	}
}
