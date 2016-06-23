package com.greatesky.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greatesky.bean.UserInfo;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		long ss = 1;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		 HttpServletRequest  httpReq  = (HttpServletRequest) request;  
         HttpServletResponse httpResp = (HttpServletResponse) response;  
         httpResp.setContentType("text/html");  
         String contextpath = httpReq.getContextPath();
         //判断是否是登陆页面     /login.jsp
         String servletPath = httpReq.getServletPath();  
         chain.doFilter(request, response);
//         if("/login.jsp".equalsIgnoreCase(servletPath)){
//        	 chain.doFilter(request, response);
//         }else{
//        	 UserInfo userinfo = (UserInfo)httpReq.getSession().getAttribute("userinfo");
//        	 if(userinfo != null && !"".equals(userinfo.getUsername())){
//        		 chain.doFilter(request, response);
//        	 }else{
//        		 httpResp.sendRedirect(contextpath+"/login.jsp");
//        	 }
//         }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init==================LoginFilter");
	}

}
