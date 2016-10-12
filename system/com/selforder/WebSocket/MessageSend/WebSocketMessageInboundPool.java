package com.selforder.WebSocket.MessageSend;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.selforder.service.impl.WeixinApiServiceImpl;
import com.selforder.util.JsonResultUtil;

public class WebSocketMessageInboundPool {
	private static Logger logger = Logger.getLogger(WebSocketMessageInboundPool.class);
	//保存连接的MAP容器  
    private static final Map<String,WebSocketMessageInbound > connections = new HashMap<String,WebSocketMessageInbound>();  
      
    //向连接池中添加连接  
    public static void addMessageInbound(WebSocketMessageInbound inbound){  
        //添加连接  
    	logger.info("用户"+inbound.getUserId()+",登录webSocket服务器。");
        connections.put(inbound.getUserId(), inbound);  
    }  
      
    //获取所有的在线用户  
    public static Set<String> getOnlineUser(){  
        return connections.keySet();  
    }  
      
    public static void removeMessageInbound(WebSocketMessageInbound inbound){  
        //移除连接  
    	logger.info("用户"+inbound.getUserId()+",退出webSocket服务器。");
        connections.remove(inbound.getUserId());  
    }  
     
    //向单一用户发送消息
    public static String sendMessageToUser(String user,String message){ 
    	String result = "";
        try {  
        	if(null == user || "".equals(user.trim())){
        		return JsonResultUtil.getJsonResult(-1, "fail", "消息接受者不能为空！");
        	}
        	if(null == message || "".equals(message.trim())){
        		return JsonResultUtil.getJsonResult(-1, "fail", "消息内容不能为空！");
        	}
            //向特定的用户发送数据  
            System.out.println("向用户 : " + user + "推送消息 ,消息内容=: " + message);  
            WebSocketMessageInbound inbound = connections.get(user);  
            if(inbound != null){  
                inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));  
            }  
            result = JsonResultUtil.getJsonResult(0, "success", "向用户"+user+",推送消息成功！");
        } catch (IOException e) {  
            logger.error("向用户"+user+"推送消息失败！",e);
            return JsonResultUtil.getJsonResult(-1, "fail", "向用户"+user+",推送消息失败！");
        } 
        return result;
    }  
      
    //向所有的用户发送消息  
    public static void sendMessage(String message){  
        try {
        	Set<String> keySet1 = connections.keySet();  
            for (String key : keySet1) {  
                WebSocketMessageInbound inbound = connections.get(key);  
                if(inbound != null){  
                    System.out.println("5秒钟后开始发送定时消息");  
                    inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap("当前登录用户ID为："+key+"欢迎您登录\t5秒钟后开始发送定时消息"));  
                }  
            } 
            //Thread.sleep(5000);
        	int total = 0;
            do{
            	++total;
            	Set<String> keySet = connections.keySet();  
                for (String key : keySet) {  
                    WebSocketMessageInbound inbound = connections.get(key);  
                    if(inbound != null){  
                        System.out.println("No"+total+" send message to user : " + key + " ,message content : " + message);  
                        inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message+total));  
                    }  
                }  
                //Thread.sleep(2000);
            }while(total<5);
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }  
}
