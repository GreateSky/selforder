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
			/**
			 * 消息推送接口
			 * 消息类型messageType:createOrder，updateOrder，payOrder,comment,callService
			 * 接口所需参数：messageType、id（各个业务实体ID）、
			 * 访问地址：http://127.0.0.1:7080/selforder/api/pushMessage?method=pushMessage&messageType=comment&id=sdfasdfasdfasdf
			 */
			String messageType = request.getParameter("messageType");
			//初始化dao层bean
			ServletContext application;     
			WebApplicationContext wac;     
			application = getServletContext();     
			application = getServletContext();     
			wac = WebApplicationContextUtils.getWebApplicationContext(application);//获取spring的context     
			PushMessage  pushMessage = (PushMessage) wac.getBean("pushMessage");
			if("createOrder".equals(messageType)||"updateOrder".equals(messageType)||"payOrder".equals(messageType)){
				//推送订单类消息
				String orderid = request.getParameter("id");//订单ID
				String result = pushMessage.sendOrderMessgeToUser(messageType, orderid);
			}else if("comment".equals(messageType)){
				//推送评论类消息
				String rid =  request.getParameter("id");//评论ID
				String result = pushMessage.sendCommentMessageToUser(rid);
			}else if("callService".equals(messageType)){
				//推送呼叫服务类消息
				String tableid =  request.getParameter("id");//餐桌ID
				String result = pushMessage.sendCallServiceMessageToUser(tableid);
			}
		}else{
			response.getWriter().write("无请求内容！");
		}
	}

}
