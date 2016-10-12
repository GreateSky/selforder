<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@page import="com.selforder.util.Context" %>
<%@page import="com.selforder.bean.UserInfo" %>     
<!DOCTYPE >

<%
	UserInfo userinfo = new Context().getLoginUserInfo();
	String empname = userinfo.getName();
	String empcode = userinfo.getCode();
	String userid = userinfo.getEmpid();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>登录</title>
</head>
<body>
	<div class="page-container">
         <h1>欢迎登录</h1>
         <div id="content"></div>
     </div>	
     <script type="text/javascript" src="js/jQuery-2.1.4.min.js"></script>				
      <script type="text/javascript">
      	var empid = '<%=userid%>';
      	var websocket;
      	initWebSocket();
      	function initWebSocket(){
      		if(window.WebSocket){
      			websocket = new WebSocket(encodeURI('ws://localhost:7080/selforder/api/SendMsg?empid='+empid));
      		}else{
      			alert("浏览器不支持WebSocket,请更新您的浏览器！");
      		}
      	}
      	
      	websocket.onopen = function() {  
            //连接成功  
            $("#content").append("<p>连接成功!  </p>");
            //alert("连接成功！");
        }  
        websocket.onerror = function() {  
            //连接失败  
            $("#content").append("<p>连接失败  ！  </p>");
            //alert("连接失败  ！");
        }  
        websocket.onmessage = function(message) {  																		
            //连接失败  
            $("#content").append('<p>服务端发回信息:'+message.data+'</p>');
        }  
      </script> 
</body>
</html>