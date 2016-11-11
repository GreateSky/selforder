<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>     
<!DOCTYPE html >
<html>
	<head>
		<meta charset="UTF-8">
		<!-- Tell the browser to be responsive to screen width -->
	    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	    <!-- Bootstrap 3.3.4 -->
	    <link href="<%=cxtPath%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <!-- FontAwesome 4.3.0 -->
	    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	    <!-- Theme style -->
	    <link href="<%=cxtPath%>/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
	    <!-- AdminLTE Skins. Choose a skin from the css/skins
	         folder instead of downloading all of them to reduce the load. -->
	    <link href="<%=cxtPath%>/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
	    <link href="<%=cxtPath%>/plugins/datepicker/datepicker3.css"/>
	    <%
	    	String opt = request.getParameter("opt");
	    	String bid = "";
	    	if(null != opt && "update".equalsIgnoreCase(opt)){
	    		bid = request.getParameter("bid");
	    	}else{
	    		opt = "";
	    	}
	    %>
	    <script language="JavaScript" type="application/javascript">
	    	var opt = '<%=opt%>'
	    	var bid = '<%=bid%>';
	    </script>
	</head>
	<body style="margin: 0; padding: 0; background-color: #ECF0F5; width: 100%; height: 100%;" >
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	商户管理
            <small>商户维护</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 商户管理</a></li>
            <li class="active">商户维护</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">商户基本信息</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal" id="saveBusinessForm">
                  <div class="box-body">
                  	<!--门店维护 start-->
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label" >名称：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="bname" name="business.bname" placeholder="名称" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label" >编码：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="bcode" name="business.bcode" placeholder="编码" value="" onblur="checkCode()" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">法人：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="legaler" name="business.legaler" placeholder="法人" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">电话：</label>
					  <div class="col-sm-2">
						<input type="tel" class="form-control" id="phone" name="business.phone" placeholder="电话" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">邮箱：</label>
					  <div class="col-sm-2">
						<input type="email" class="form-control" id="email" name="business.email" placeholder="邮箱" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">地址：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="address" name="business.address" placeholder="地址" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">营业执照：</label>
					  <div class="col-sm-2">
						<input type="file" id="fileid" name="filename" class="form-control" id="inputEmail3" placeholder="营业执照" value=""  onchange="checkFile(this)" accept=".jpeg,.jpg,.png">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">合作状态：</label>
					  <div class="col-sm-2">
						<select class="form-control" id="status" name="business.status"> 
							<option value="0" selected="selected">试点</option>
							<option value="1">正式</option>
							<option value="2">暂停</option>
							<option value="-1">不合作</option>
						</select>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">开始合作时间：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="begindate" name="business.begindate" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">结束合作时间：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="enddate" name="business.enddate" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">微信Appid：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="appid" name="business.appid" placeholder="微信Appid" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">微信AppSecret：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="appsecret" name="business.appsecret" placeholder="微信AppSecret" value="" >
					  </div>
					</div>
					<div class="form-group has-warning" tag="add">
					  <label for="inputEmail3" class="col-sm-1 control-label">系统管理员：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="sysadmin" name="business.sysadmin" placeholder="系统管理员" value="" >
					  </div>
					</div>
					<div class="form-group has-warning" tag="add">
					  <label for="inputEmail3" class="col-sm-1 control-label">登录密码：</label>
					  <div class="col-sm-2">
						<input type="password" class="form-control" id="password" name="business.password" placeholder="登录密码" value="" >
					  </div>
					</div>
					<div class="form-group has-warning" tag="add">
					  <label for="inputEmail3" class="col-sm-1 control-label">密码确认：</label>
					  <div class="col-sm-2">
						<input type="password" class="form-control" id="passwordagain" name="" placeholder="密码确认" value="" >
					  </div>
					</div>
					<!-- /门店维护 start-->
                  </div><!-- /.box-body -->
                  <div class="box-footer">
                  	<button type="button" class="btn btn-default" onclick="javascript:history.go(-1)">返回</button>
                    <button type="button" onclick="uploadFile()" class="btn btn-info pull-right">保存</button>
                  </div><!-- /.box-footer -->
                </form><!--/form end-->
              </div><!-- /.box -->
        </section><!-- /.content -->
        <img id="licenseImg" src=""  style="position:absolute;top: 120px; left: 500px; border-radius: 5px;width: 400px"></img>
	</body>
	<script src="<%=cxtPath%>/js/ajaxfileupload.js"></script>
    <script src="saveBusiness.js"></script>
    <script src="<%=cxtPath%>/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="<%=cxtPath%>/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
    <script>
    	$("#begindate").datepicker();
    	$("#enddate").datepicker();
    </script>
</html>