<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String cxtPath = request.getContextPath();%>
<%@page import="com.selforder.util.Context" %>
<%@page import="com.selforder.bean.UserInfo" %> 
<%
	UserInfo userinfo = new Context().getLoginUserInfo();
	String empname = userinfo.getName();
	String empcode = userinfo.getCode();
	String empid = userinfo.getEmpid();
	String weid = userinfo.getBid();
	String storeid = userinfo.getSid();
	String emp_type = userinfo.getType();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<!-- Tell the browser to be responsive to screen width -->
	    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	</head>
	<script src="<%=cxtPath%>/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="<%=cxtPath%>/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="<%=cxtPath%>/js/common.js"></script>
    <script src="<%=cxtPath%>/plugins/layer/layer.js"></script>
</html>
<script>
	var cxtPath = '<%=cxtPath %>';
	var weid = '<%=weid%>';
	var storeid = '<%=storeid%>';
	var empid = '<%=empid%>';
	var emp_type = '<%=emp_type%>';
	function controlBtn(){
		/*if(emp_type == "A"){
			$("button[role='btn-shop-add']").remove();
			$("button[role='btn-shop-update']").remove();
			$("button[role='btn-shop-remove']").remove();
			
			$("button[role='btn-business-add']").remove();
			$("button[role='btn-business-update']").remove();
			$("button[role='btn-business-remove']").remove();
		}else if(emp_type == "B"){
			$("button[role='btn-shop-add']").remove();
			$("button[role='btn-shop-update']").remove();
			$("button[role='btn-shop-remove']").remove();
			
			$("button[role='btn-admin-add']").remove();
			$("button[role='btn-admin-update']").remove();
			$("button[role='btn-admin-remove']").remove();
		}else if(emp_type == "S"){
			$("button[role='btn-business-add']").remove();
			$("button[role='btn-business-update']").remove();
			$("button[role='btn-business-remove']").remove();
			
			$("button[role='btn-admin-add']").remove();
			$("button[role='btn-admin-update']").remove();
			$("button[role='btn-admin-remove']").remove();
		}*/
	}
</script>