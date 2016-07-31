package com.selforder.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpClient {
	 public static void main(String[] args) throws Exception {
	        //服务的地址
	        URL wsUrl = new URL("https://api.10646.cn/ws/service/Sms");
	        
	        HttpsURLConnection conn = (HttpsURLConnection) wsUrl.openConnection();
	        //HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();
	        
	        conn.setDoInput(true);
	        conn.setDoOutput(true);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
	        conn.setRequestProperty("SOAPAction", "http://api.jasperwireless.com/ws/service/sms/SendSMS");
	        OutputStream out = conn.getOutputStream();
	        
	        StringBuffer soap = new StringBuffer();
	        soap.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sch=\"http://api.jasperwireless.com/ws/schema\">                       ");
        	soap.append("   <soapenv:Header>                                                                                                                                    ");
        	soap.append("   	<wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" soapenv:mustUnderstand=\"1\">         ");
        	soap.append("		<wsse:UsernameToken xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"UsernameToken-16847597\"> ");
        	soap.append("			<wsse:Username>gaoqiang</wsse:Username>                                                                                                           ");
        	soap.append("			<wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">147258</wsse:Password>      ");
        	soap.append("		</wsse:UsernameToken>                                                                                                                               ");
        	soap.append("	</wsse:Security>                                                                                                                                      ");
        	soap.append("   </soapenv:Header>                                                                                                                                   ");
        	soap.append("   <soapenv:Body>                                                                                                                                      ");
        	soap.append("      <sch:SendSMSRequest messageTextEncoding=\"UCS2\">                                                                                                  ");
        	soap.append("         <sch:messageId> </sch:messageId>                                                                                                              ");
        	soap.append("         <sch:version>1</sch:version>                                                                                                                  ");
        	soap.append("         <sch:licenseKey>06d074a4-c884-496a-92b6-aac6efbe6aa0</sch:licenseKey>                                                                         ");
        	soap.append("         <sch:sentToIccid>89860616020006912617</sch:sentToIccid>                                                                                       ");
        	soap.append("         <sch:messageText>服务端调用发送</sch:messageText>                                                                                                   ");
        	soap.append("         <sch:tpvp>1</sch:tpvp>                                                                                                                        ");
        	soap.append("      </sch:SendSMSRequest>                                                                                                                            ");
        	soap.append("   </soapenv:Body>                                                                                                                                     ");
        	soap.append("</soapenv:Envelope>                                                                                                                                    ");
	        System.out.println(soap.toString());
	        out.write(soap.toString().getBytes());
	        out.flush();
	        out.close();
	        int code = conn.getResponseCode(); // 用来获取服务器响应状态
	        String tempString = null;
	        StringBuffer sb1 = new StringBuffer();
	        if (code == HttpURLConnection.HTTP_OK) {
	         BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	         while ((tempString = reader.readLine()) != null) {
	          sb1.append(tempString);
	          System.out.println(tempString);
	         }
	         if (null != reader) {
	          reader.close();
	         }
	        } else {
	        	BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
	            // 一次读入一行，直到读入null为文件结束
	        	System.out.println("报文相应内容：");
	            while ((tempString = reader.readLine()) != null) {
	             sb1.append(tempString);
	             System.out.print(tempString);
	            }
	            if (null != reader) {
	             reader.close();
	            }
	        }
	        //响应报文
	        //System.out.println(sb1.toString());
	        System.out.println("结束");
	        conn.disconnect();
	    }
}
