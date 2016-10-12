/**
 * 
 */
package com.selforder.WebSocket.MessageSend;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import net.sf.json.JSONObject;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

/**
 * @author xingwanzhao
 *
 * 2016-9-26
 */
public class WebSocketMessageInbound extends MessageInbound {
	
    private String userid;//当前连接的用户
    
    public WebSocketMessageInbound(String userid){
    	this.userid = userid;
    }
    public String getUserId(){
    	return this.userid;
    }
    
    // 触发关闭事件，在连接池中移除连接  
    @Override  
    protected void onClose(int status) {  
    	System.out.println("用户退出："+this.userid);
        WebSocketMessageInboundPool.removeMessageInbound(this);  
    }
    
    //建立连接的触发的事件  
    @Override  
    protected void onOpen(WsOutbound outbound) {  
        //向连接池添加当前的连接对象  
        WebSocketMessageInboundPool.addMessageInbound(this);  
        //WebSocketMessageInboundPool.sendMessage("服务器发送定时信息!!!");
    } 	
    
    //客户端发送二进制消息到服务器时触发事件  
	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Binary message not supported.");  
		
	}

	//客户端发送文本消息到服务器时触发事件  
	@Override
	protected void onTextMessage(CharBuffer arg0) throws IOException {
		// TODO Auto-generated method stub
	}
}
