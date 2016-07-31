<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<!DOCTYPE >
<%
	String error = request.getParameter("error");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>登录</title>
	<link rel="stylesheet" href="assets/css/reset.css">
    <link rel="stylesheet" href="assets/css/supersized.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
	<div class="page-container">
         <h1>欢迎登录</h1>
         <form action="/selforder/j_spring_security_check" method="post" id="loginForm">
             <input type="text" name='j_username' class="username" placeholder="请输入您的用户名！">
             <input type="password" id="password" name="j_password" class="password" placeholder="请输入您的用户密码！">
             <!-- <input type="Captcha" class="Captcha" name="Captcha" placeholder="请输入验证码！">-->
             <button type="submit" class="submit_button">登录</button>
             <div id="loginInfo" class="error"><span id="errmessage"></span></div>
         </form>
     </div>
        <script src="assets/js/jquery-1.8.2.min.js" ></script>
        <script src="assets/js/supersized.3.2.7.min.js" ></script>
        <script src="assets/js/supersized-init.js" ></script>
        <script src="assets/js/scripts.js" ></script>
        <script type="text/javascript">
			var error = '<%=error%>';
			if(error == "-1"){
				var loginstatus = '${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}';
				var message = "";
				if(loginstatus == "Cannot pass null or empty values to constructor"){
					message = "登录名不存在";
				}else if(loginstatus == "Bad credentials"){
					message = "密码错误";
				}else if(loginstatus == "LockedException"){
					message = "帐户被锁定";
				}else if(loginstatus == "DisabledException"){
					message = "帐户未启动";
				}else if(loginstatus == "CredentialExpiredException"){
					message = "密码过期";
				}else if(loginstatus == "A granted authority textual representation is required"){
					message = "无登录权限";
				}else{
					message = "登录失败";
				}
				$("#errmessage").html(message);
				$("#loginInfo").css('display','block');
			}
		</script>
</body>
</html>