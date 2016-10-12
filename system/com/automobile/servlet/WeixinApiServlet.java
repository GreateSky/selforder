package com.automobile.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.selforder.service.WeixinApiService;
import com.selforder.util.PushMessage;

/**
 * Servlet implementation class weixinServlet
 */
public class WeixinApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeixinApiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String method = request.getParameter("method");//方法标识
		//获取AccessToken
		if("getAccessToken".equals(method)){
			String weid = request.getParameter("weid");//商户ID
			//初始化dao层bean
			ServletContext application;     
			WebApplicationContext wac;     
			application = getServletContext();     
			wac = WebApplicationContextUtils.getWebApplicationContext(application);//获取spring的context     
			WeixinApiService  weixinApiService = (WeixinApiService) wac.getBean("weixinApiService");
			String result = weixinApiService.getAccessToken(weid);
			response.getWriter().write(result);
		}else if("pushMessage".equals(method)){
			//消息推送
			String messageType = request.getParameter("messageType");//消息类型
			//初始化dao层bean
			ServletContext application;     
			WebApplicationContext wac;     
			application = getServletContext();     
			application = getServletContext();     
			wac = WebApplicationContextUtils.getWebApplicationContext(application);//获取spring的context     
			PushMessage  pushMessage = (PushMessage) wac.getBean("pushMessage");
			if(!"comment".equals(messageType)){
				//推送订单类消息
				String orderid = request.getParameter("orderid");//订单ID
				String result = pushMessage.sendOrderMessgeToUser(messageType, orderid);
			}
		}else{
			response.getWriter().write("无请求内容！");
		}
	}

}
