package com.selforder.WebSocket.MessageSend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

public class SocketServerServlet extends WebSocketServlet {
	private static final long serialVersionUID = 4237029018363339214L;
	public static int ONLINE_USER_COUNT = 1;  
	public String getUserInfo(HttpServletRequest request){
		String empid = (String)request.getParameter("empid");
		return empid;
	}
	@Override
	protected StreamInbound createWebSocketInbound(String arg0,
			HttpServletRequest req) {
		// TODO Auto-generated method stub
		return new WebSocketMessageInbound(getUserInfo(req));
	}
}
