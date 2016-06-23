package com.automobile.weixin;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.automobile.util.JsonResultUtil;

/**
 * 各类微信服务接口
 * @author xingwanzhao
 *
 * 2016-4-6
 */
public class WeiXinUtil {
	/*设置服务号模板消息所属行业信息*/
	private static final String setDustry = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
	/*获取模板消息列表*/
	private static final String templatlist = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
	/*发送模板消息*/
	private static final String sendTemplat = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	/*创建卡券*/
	private static final String creatCard = "https://api.weixin.qq.com/card/create?access_token=ACCESS_TOKEN";
	public static void main(String[] args) {
//		String accesstoken = TicketUtil.getAccessToken();
//		String jsapiticket = TicketUtil.getJsapi_ticket(accesstoken);
//		System.out.println("accesstoken==========="+accesstoken);
//		System.out.println("jsapiticket==========="+jsapiticket);
		//设置所属行业
//		WeiXinUtil.setModelMsgDustry();
		//获取模板消息列表
//		getTemplatList();
		//发送模板消息
		//sendRepairTemplat();
		sendServiceTemplat();
		
	}
	
	/**
	 * 创建卡券
	 * @return
	 */
	public static String createCard(){
		String result = "";
		try{
			//获取accesstoken
			String accesstoken = TicketUtil.getAccessToken();
			String url = creatCard;
			url = url.replace("ACCESS_TOKEN", accesstoken);
			//组装参数
			String templatid= "Qq8g6r8G_ZLQUYMKF4l-t39FE85XB6C1T_uUVD2Myss";
			Map param = new HashMap();
			param.put("touser","oRXyzt86aKXrJr5KrDVSA3iVzEhM");
			param.put("template_id",templatid);
			param.put("url","http://www.baidu.com");
			
			Map data = new HashMap();
			Map nickname = new HashMap();
			nickname.put("value","第四飞龙");
			nickname.put("color","#E77622");
			
			Map items = new HashMap();
			items.put("value","轮胎更换、刹车检查");
			items.put("color","#E77622");
			
			Map money = new HashMap();
			money.put("value","￥360");
			money.put("color","#E77622");
			
			Map day = new HashMap();
			day.put("value","2016-04-07");
			
			data.put("nickname",nickname );
			data.put("items",items );
			data.put("money", money);
			data.put("day", day);
			param.put("data", data);
			result = HttpClientUtil.httpRequest(url, "POST",JsonResultUtil.MapToJsonStr(param));
			if(result.indexOf("ok") == -1){
				result = JsonResultUtil.getJsonResult(-1, "fail", "发送模板消息失败！");
				System.out.println(result);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 发送服务通知模板消息
	 * @return
	 */
	public static String sendServiceTemplat(){
		String result = "";
		try{
			//获取accesstoken
			String accesstoken = TicketUtil.getAccessToken();
			String url = sendTemplat;
			url = url.replace("ACCESS_TOKEN", accesstoken);
			//组装参数
			String templatid= "Qq8g6r8G_ZLQUYMKF4l-t39FE85XB6C1T_uUVD2Myss";
			Map param = new HashMap();
			param.put("touser","oRXyzt86aKXrJr5KrDVSA3iVzEhM");
			param.put("template_id",templatid);
			param.put("url","http://www.baidu.com");
			
			Map data = new HashMap();
			Map nickname = new HashMap();
			nickname.put("value","第四飞龙");
			nickname.put("color","#E77622");
			
			Map items = new HashMap();
			items.put("value","轮胎更换、刹车检查");
			items.put("color","#E77622");
			
			Map money = new HashMap();
			money.put("value","￥360");
			money.put("color","#E77622");
			
			Map day = new HashMap();
			day.put("value","2016-04-07");
			
			data.put("nickname",nickname );
			data.put("items",items );
			data.put("money", money);
			data.put("day", day);
			param.put("data", data);
			result = HttpClientUtil.httpRequest(url, "POST",JsonResultUtil.MapToJsonStr(param));
			if(result.indexOf("ok") == -1){
				result = JsonResultUtil.getJsonResult(-1, "fail", "发送模板消息失败！");
				System.out.println(result);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 发送保养通知模板消息
	 * @return
	 */
	public static String sendRepairTemplat(){
		String result = "";
		try{
			//获取accesstoken
			String accesstoken = TicketUtil.getAccessToken();
			String url = sendTemplat;
			url = url.replace("ACCESS_TOKEN", accesstoken);
			//组装参数
			String templatid= "0LLgwJ4gq7dLaO14KfB8A83GPCo931WoR72nZTJlgcc";
			Map param = new HashMap();
			param.put("touser","oRXyzt86aKXrJr5KrDVSA3iVzEhM");
			param.put("template_id",templatid);
			param.put("url","http://www.baidu.com");
			
			Map data = new HashMap();
			Map nickname = new HashMap();
			nickname.put("value","第四飞龙");
			nickname.put("color","#E77622");
			
			Map lastRepair = new HashMap();
			lastRepair.put("value","2015-12-22");
			lastRepair.put("color","#E77622");
			
			Map months = new HashMap();
			months.put("value","4");
			months.put("color","#E77622");
			
			Map remark = new HashMap();
			remark.put("value"," 点击查看保养详情...");
			
			data.put("nickname",nickname );
			data.put("lastRepair",lastRepair );
			data.put("months", months);
			data.put("remark", remark);
			param.put("data", data);
			result = HttpClientUtil.httpRequest(url, "POST",JsonResultUtil.MapToJsonStr(param));
			if(result.indexOf("ok") == -1){
				result = JsonResultUtil.getJsonResult(-1, "fail", "发送模板消息失败！");
				System.out.println(result);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获取模板消息列表
	 * @return
	 */
	public static String getTemplatList(){
		String result = "";
		try{
			//获取accesstoken
			String accesstoken = TicketUtil.getAccessToken();
			String url = templatlist;
			url = url.replace("ACCESS_TOKEN", accesstoken);
			result = HttpClientUtil.httpRequest(url, "POST",null);
			if(result.indexOf("ok") == -1){
				result = JsonResultUtil.getJsonResult(-1, "fail", "获取模板消息列表失败！");
				System.out.println(result);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 设置服务号模板消息所属行业信息
	 * @return
	 */
	public static String setModelMsgDustry(){
		String result = "";
		try{
			//获取accesstoken
			String accesstoken = TicketUtil.getAccessToken();
			String url = setDustry;
			url = url.replace("ACCESS_TOKEN", accesstoken);
			Map param = new HashMap();
			param.put("industry_id1","2");
			param.put("industry_id2","25");
			result = HttpClientUtil.httpRequest(url, "POST", JsonResultUtil.MapToJsonStr(param));
			if(result.indexOf("ok") == -1){
				result = JsonResultUtil.getJsonResult(-1, "fail", "设置所属行业失败！");
				System.out.println(result);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取jsapi签名信息
	 * @param url
	 * @return
	 */
	public static Map<String,String> getJsApiSignInfo(String jsapi_ticket,String url){
		Map<String,String> resultMap = new HashMap<String, String>();
		try{
			String nonce_str = create_nonce_str();
	        String timestamp = create_timestamp();
	        String string1;
	        String signature = "";
	        
	        //注意这里参数名必须全部小写，且必须有序
	        string1 = "jsapi_ticket=" + jsapi_ticket +
	                  "&noncestr=" + nonce_str +
	                  "&timestamp=" + timestamp +
	                  "&url=" + url;
	        System.out.println("获取Jsapi原始字符串：================="+string1);
	        try
	        {
	            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	            crypt.reset();
	            crypt.update(string1.getBytes("UTF-8"));
	            signature = byteToHex(crypt.digest());
	            System.out.println("生成的signature值：============="+signature);
	        }
	        catch (NoSuchAlgorithmException e)
	        {
	            e.printStackTrace();
	        }
	        catch (UnsupportedEncodingException e)
	        {
	            e.printStackTrace();
	        }

	        resultMap.put("url", url);
	        resultMap.put("jsapi_ticket", jsapi_ticket);
	        resultMap.put("nonceStr", nonce_str);
	        resultMap.put("timestamp", timestamp);
	        resultMap.put("signature", signature);
	        return resultMap;
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}
	
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
