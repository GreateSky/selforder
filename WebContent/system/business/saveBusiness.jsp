<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>     
<!DOCTYPE html >
<html>
	<head>
		<meta charset="UTF-8">
	    <%
	    	String opt = request.getParameter("opt");
	    	String bid = "";
	    	if(null != opt && "update".equalsIgnoreCase(opt)){
	    		bid = request.getParameter("bid");
	    	}else{
	    		opt = "";
	    	}
	    %>
	    <link rel="stylesheet" href="<%=cxtPath%>/plugins/datepicker/bootstrap-datepicker3.min.css"/>
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
					  <label for="inputEmail3" class="col-sm-2 control-label" ><font color="red">*</font>&nbsp;名称：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="bname" name="business.bname" placeholder="名称" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label" ><font color="red">*</font>&nbsp;编码：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="bcode" name="business.bcode" placeholder="编码" value="" onchange="checkCode()" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;法人：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="legaler" name="business.legaler" placeholder="法人" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;电话：</label>
					  <div class="col-sm-2">
						<input type="tel" class="form-control" id="phone" name="business.phone" placeholder="电话" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">邮箱：</label>
					  <div class="col-sm-2">
						<input type="email" class="form-control" id="email" name="business.email" placeholder="邮箱" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;地址：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="address" name="business.address" placeholder="地址" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">营业执照：</label>
					  <div class="col-sm-2">
						<input type="file" id="fileid" name="filename" class="form-control" id="inputEmail3" placeholder="营业执照" value=""  onchange="checkFile(this,'licenseImg')" accept=".jpeg,.jpg,.png">
					  	<small>最佳尺寸：</small>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;合作状态：</label>
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
					  <label for="inputEmail3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;开始合作时间：</label>
					  <div class="col-sm-2">
						<div class="input-group">
						    <input id="begindate" name="business.begindate" type="text" class="form-control datepicker" value="">
						    <div class="input-group-addon" style="border-color: #f39c12">
						       <i class="fa fa-calendar"></i>
						    </div>
						</div>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;结束合作时间：</label>
					  <div class="col-sm-2">
						<div class="input-group">
						    <input id="enddate" name="business.enddate" type="text" class="form-control datepicker" value="">
						    <div class="input-group-addon" style="border-color: #f39c12">
						       <i class="fa fa-calendar"></i>
						    </div>
						</div>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">微信Appid：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="appid" name="business.appid" placeholder="微信Appid" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">微信AppSecret：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="appsecret" name="business.appsecret" placeholder="微信AppSecret" value="" >
					  </div>
					</div>
					<div class="form-group has-warning" >
					  <label for="inputEmail3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;系统管理员：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="sysadmin" name="business.sysadmin" placeholder="系统管理员" value="" onchange="checkSystemAdmin();" >
					  </div>
					</div>
					<div class="form-group has-warning" tag="add">
					  <label for="inputEmail3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;登录密码：</label>
					  <div class="col-sm-2">
						<input type="password" class="form-control" id="password" name="business.password" placeholder="登录密码" value="" >
					  </div>
					</div>
					<div class="form-group has-warning" tag="add">
					  <label for="inputEmail3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;密码确认：</label>
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
        <img id="licenseImg" src=""  style="position:absolute;top: 120px; left: 550px; border-radius: 5px;width: 400px"></img>
	</body>
	<script src="<%=cxtPath%>/js/ajaxfileupload.js"></script>
	<script src="<%=cxtPath%>/plugins/datepicker/bootstrap-datepicker.min.js"></script>
	<script src="<%=cxtPath%>/plugins/datepicker/bootstrap-datepicker.zh-CN.min.js"></script>
    <script src="saveBusiness.js"></script>
    <script>
    	$('.datepicker').datepicker({
    	    language: "zh-CN"
    	});
    </script>
</html>