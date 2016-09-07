package com.selforder.weixin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.selforder.util.JsonResultUtil;

/**
 * httpclient 
 * @author xingwanzhao
 *
 * 2016-4-6
 */
public class HttpClientUtil {
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);
	/**
	 * 发起https请求并获取结果
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JsonObject(通过JsonObject.get(key)的方式获取json对象的属性值)
	 */
	public static String httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		String JsonObject = null;
		StringBuffer buffer = new StringBuffer();
		logger.info("httpclient请求提交的参数===================="+outputStr);
		try {			
			URL url = new URL(null,requestUrl,new sun.net.www.protocol.https.Handler());
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();			
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);	
			httpUrlConn.setRequestMethod(requestMethod);
			
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();			
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			JsonObject = buffer.toString();
			logger.info("httpclient返回结果=================="+buffer.toString());
		} catch (ConnectException ce) {
			//System.out.println("Weixin server connection timed out.");
			logger.error("链接微信服务器超时。"+ce.getMessage());
			JsonObject = JsonResultUtil.getJsonResult(-1, "fail", "链接微信服务器超时。");
		} catch (Exception e) {
			//System.out.println("https request error:{}" + e);
			logger.error("调用微信API异常。"+e.getMessage());
			JsonObject = JsonResultUtil.getJsonResult(-1, "fail", "调用微信API异常。");
		}
		return JsonObject;
	}
}
