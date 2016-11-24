<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	body{
		margin: 0px;padding: 0px;
		font-size: 15px;
	}
</style>
</head>
<body>
	<div id="printContent">
		<p>您当前的排号为:</p>
		<p style="text-align: center;font-size: 30px;" id="queueCode">${param.queueCode}</p>
		<p>在您前面还有<span style="font-size: 20px" id="waitNum">${param.waitNum}</span>桌客人在等待！</p>
		<p id="printDate">时间:</p>
	</div>
	<script type="text/javascript">
		var date = new Date();
		var currDate = date.Format("yyyy-MM-dd hh:mm:ss");
		$("#printDate").html("时间："+currDate);
		window.print();
	</script>
</body>
</html>