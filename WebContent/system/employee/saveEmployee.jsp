<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>    
<%
	String opt = request.getParameter("opt");
	String empid = "";
	if(null != opt && "update".equalsIgnoreCase(opt)){
		empid = request.getParameter("empid");
	}else{
		opt = "";
	}
%> 
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
	    <script language="JavaScript" type="application/javascript">
	    	var opt = '<%=opt%>';
	    	var empid = '<%=empid%>';
	    </script>
	</head>
	<body style="margin: 0; padding: 0; background-color: #ECF0F5; width: 100%; height: 100%;" >
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	员工管理
            <small>个人信息维护</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 员工管理</a></li>
            <li class="active">个人信息维护</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">个人信息维护</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--门店维护 start-->
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">姓名：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="empname" name="employee.empname" placeholder="姓名" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">性别：</label>
					  <div class="col-sm-2">
						<select class="form-control" id="sex" name="employee.sex">
							<option selected="selected" value="1">男</option>
							<option value="2">女</option>
						</select>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">电话：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="phone" name="employee.phone" placeholder="电话" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">家庭住址：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="homeaddress" name="employee.homeaddress" placeholder="家庭住址" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">紧急联系人：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="contactname" name="employee.contactname" placeholder="紧急联系人" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">紧急联系人电话：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="contactphone" name="employee.contactphone" placeholder="紧急联系人电话" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label" ></label>
					  <label for="inputEmail3" class="col-sm-5 control-label" style="border-top: 1px dashed #C0C0C0" ></label>
					</div>
					<div class="form-group has-warning" tag="login">
					  <label for="inputEmail3" class="col-sm-1 control-label">登录名：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="loginname" name="employee.loginname" placeholder="登录名" onblur="checkLoginName()" >
					  </div>
					</div>
					<div class="form-group has-warning" tag="login">
					  <label for="inputEmail3" class="col-sm-1 control-label">登录密码：</label>
					  <div class="col-sm-2">
						<input type="password" class="form-control" id="password" name="employee.password" placeholder="登录密码" >
					  </div>
					</div>
					<div class="form-group has-warning" tag="login">
					  <label for="inputEmail3" class="col-sm-1 control-label">确认密码：</label>
					  <div class="col-sm-2">
						<input type="password" class="form-control" id="password_again"  placeholder="确认密码" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">微信号：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="wechatid" name="employee.wechatid" placeholder="微信号" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">状态：</label>
					  <div class="col-sm-2">
						<select class="form-control" id="status" name="employee.status">
							<option selected="selected" value="0">实习</option>
							<option value="1">正式</option>
							<option value="2">其他</option>
							<option value="-1">离职</option>
						</select>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">类型：</label>
					  <div class="col-sm-2">
						<select class="form-control" id="type" name="employee.type">
							<option selected="B" value="0">商户</option>
							<option value="S">门店</option>
							<option value="A">管理</option>
						</select>
					  </div>
					  <div class="col-sm-2">
						<button class="btn btn-default">选择</button>
					  </div>
					</div>
					<!-- /门店维护 start-->
                  </div><!-- /.box-body -->
                  <div class="box-footer">
                  	<button type="submit" class="btn btn-default">取消</button>
                    <button type="button" onclick="saveEmployee()" class="btn btn-info pull-right">保存</button>
                  </div><!-- /.box-footer -->
                </form><!--/form end-->
              </div><!-- /.box -->
        </section><!-- /.content -->
        <img id="headimgurl" src="" width="150px" height="150px" style="position:absolute;top: 120px; left: 550px; border-radius: 5px;"></img>
        <!--modal 百度地图-->
        <div class="modal fade" id="employeeWin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">地图定位</h4>
		      </div>
		      <div class="modal-body">
		      	<div class="input-group " style="width: 97%;margin: 10px">
			      	<input type="text" class="form-control" id="point_address"  placeholder="请输入名称进行搜索" value="" >
			      	<span class="input-group-btn">
	                    <button class="btn btn-info btn-flat" type="button" onclick="poindByAddress();">Go!</button>
	                </span>
			    </div>
				<table class="table table-striped animated flipInX" id="storeList">
			          <tr>
	                     <th>#</th>
	                     <th>名称</th>
	                     <th>电话</th>
	                     <th>操作</th>
			          </tr>
			    </table>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /<!--modal 百度地图-->
	</body>
	<!-- jQuery 2.1.4 -->
    <script src="<%=cxtPath%>/js/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="<%=cxtPath%>/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="<%=cxtPath%>/js/ajaxfileupload.js"></script>
    <script src="<%=cxtPath%>/js/common.js"></script>
    <script src="<%=cxtPath%>/plugins/layer/layer.js"></script>
    <script src="saveEmployee.js"></script>
</html>