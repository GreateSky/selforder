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
	    	String rid = "";
	    	if(null != opt && "update".equalsIgnoreCase(opt)){
	    		rid = request.getParameter("bid");
	    	}else{
	    		opt = "";
	    	}
	    %>
	    <script language="JavaScript" type="application/javascript">
	    	var opt = '<%=opt%>'
	    	var rid = '<%=rid%>';
	    </script>
	</head>
	<body style="margin: 0; padding: 0; background-color: #ECF0F5; width: 100%; height: 100%;" >
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	权限管理
            <small>资源维护</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 权限管理</a></li>
            <li class="active">资源维护</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">资源基本信息</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal" id="saveBusinessForm">
                  <div class="box-body">
                  	<!--门店维护 start-->
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label" >名称：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="rname" name="resource.rname" placeholder="名称" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">URL：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="rurl" name="resource.rurl" placeholder="URL" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">说明：</label>
					  <div class="col-sm-2">
						<textarea class="form-control" rows="4" name="resource.remark" id="remark"></textarea>
					  </div>
					</div>
					<!-- /门店维护 start-->
                  </div><!-- /.box-body -->
                  <div class="box-footer">
                  	<button type="submit" class="btn btn-default">取消</button>
                    <button type="button" onclick="saveResource()" class="btn btn-info pull-right">保存</button>
                  </div><!-- /.box-footer -->
                </form><!--/form end-->
              </div><!-- /.box -->
        </section><!-- /.content -->
	</body>
	<!-- jQuery 2.1.4 -->
    <script src="<%=cxtPath%>/js/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="<%=cxtPath%>/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="<%=cxtPath%>/js/ajaxfileupload.js"></script>
    <script src="<%=cxtPath%>/js/common.js"></script>
    <script src="<%=cxtPath%>/plugins/layer/layer.js"></script>
    <script src="saveResource.js"></script>
</html>