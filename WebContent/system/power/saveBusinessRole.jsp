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
	    <%
	    	String bid = request.getParameter("bid");
	    %>
	</head>
	<body style="margin: 0; padding: 0; background-color: #ECF0F5; width: 100%; height: 100%;" >
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	权限管理
            <small>商户权限维护</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 权限管理</a></li>
            <li class="active">商户权限维护</li>
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
						<input type="text" class="form-control" id="bname"  placeholder="名称" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">编码：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="bcode" placeholder="编码" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">法人：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="legaler" placeholder="法人">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">电话：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="phone" placeholder="电话">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">邮箱：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="email" placeholder="邮箱">
					  </div>
					</div>
					<!--关联权限列表 -->
					<div class="box box-warning">
						<div class="box-header with-border">
		                  <h3 class="box-title">已授权的权限</h3>
		                  <button type="button" onclick="javascript:loadBusNoRoleList();" class="btn btn-danger" style="margin-left: 5px">添加权限</button>
		                </div><!-- /.box-header -->
		                <div class="box box-body">
		                	<table id="businessRoleList" class="table table-striped ">
			                    <tr>
			                      <th>#</th>
			                      <th>名称</th>
			                      <th>编码</th>
			                      <th>操作</th>
			                    </tr>
			                </table><!--/门店列表-->
		                </div>
					</div><!--/关联权限列表 -->
					<!-- /门店维护 start-->
                  </div><!-- /.box-body -->
                </form><!--/form end-->
              </div><!-- /.box -->
        </section><!-- /.content -->
        
        <!--modal 未授权权限列表-->
        <div class="modal fade" id="busNoRoleWin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" opttype="">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">授权权限</h4>
		      </div>
		      <div class="modal-body">
			    <div class="form-group has-warning" >
			      <label for="wcode">名称</label>
			      <input type="text" class="form-control" id="rname" name="resource.rname" placeholder="名称" value="" >
			      <label for="wcode">URL</label>
			      <input type="text" class="form-control" id="rurl" name="resource.rurl" placeholder="URL" value="" >
				</div>
				<table id="busNoRoleList" class="table table-striped ">
                    <tr>
                      <th></th>
                      <th>#</th>
                      <th>名称</th>
                      <th>编码</th>
                    </tr>
                </table><!--/门店列表-->
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" onclick="saveSelectRole()">保存</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /<!--modal 未授权权限列表-->
	</body>
	<!-- jQuery 2.1.4 -->
    <script src="<%=cxtPath%>/js/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="<%=cxtPath%>/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="<%=cxtPath%>/plugins/layer/layer.js"></script>
    <script src="saveBusinessRole.js"></script>
    <script language="JavaScript" type="application/javascript">
	    var bid = '<%=bid%>';
	</script>
</html>