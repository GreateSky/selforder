<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<!-- Tell the browser to be responsive to screen width -->
	    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	    <!-- Bootstrap 3.3.4 -->
	    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <!-- FontAwesome 4.3.0 -->
	    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	    <!-- Ionicons 2.0.0 -->
	    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
	    <!-- Theme style -->
	    <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
	    <!-- AdminLTE Skins. Choose a skin from the css/skins
	         folder instead of downloading all of them to reduce the load. -->
	    <link href="dist/css/skins/skin-yellow-light.min.css" rel="stylesheet" type="text/css" />
	    <!-- iCheck -->
	    <link href="plugins/iCheck/flat/blue.css" rel="stylesheet" type="text/css" />
	    <!-- bootstrap wysihtml5 - text editor -->
	    <link href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />
	
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->
	</head>
	<body style="margin: 0; padding: 0; background-color: #ECF0F5; width: 100%; height: 100%;">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	主页
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <!-- Small boxes (Stat box) -->
          <div class="row">
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-green">
                <div class="inner">
                  <h3>商户查看</h3>
                  <p>查看已入住商户信息</p>
                </div>
                <div class="icon">
                  <i class="ion ion-stats-bars"></i>
                </div>
                <a href="javascript:void();" onclick="linkMainTab('system/business/businessList.jsp','businesslist','商户列表')" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-yellow">
                <div class="inner">
                  <h3>商户授权查看</h3>
                  <p>查看已为商户开通的权限</p>
                </div>
                <div class="icon">
                  <i class="ion ion-person-add"></i>
                </div>
                <a href="javascript:void(0);" onclick="linkMainTab('system/power/businessRoleList.jsp','buinessrolelist','商户权限')" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-red">
                <div class="inner">
                  <h3>权限查看</h3>
                  <p>查看现有的系统权限</p>
                </div>
                <div class="icon">
                  <i class="ion ion-pie-graph"></i>
                </div>
                <a href="javascript:void(0);" onclick="linkMainTab('system/power/roleList.jsp','rolelist','权限列表')" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-aqua">
                <div class="inner">
                  <h3>资源查看</h3>
                  <p>查看系统现有的资源</p>
                </div>
                <div class="icon">
                  <i class="ion ion-stats-bars"></i>
                </div>
                <a href="javascript:void(0);" onclick="linkMainTab('system/power/resourceList.jsp','resourcelist','资源列表')" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
          </div><!-- /.row -->
          <!-- Main row -->
          <!-- /.row (main row) -->
        </section><!-- /.content -->
	</body>
	<!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- jQuery UI 1.11.4 -->
    <script src="js/jquery-ui.min.js" type="text/javascript"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script>
    	function linkMainTab(url,tabcode,title){
    		parent.linkMainTab(url,tabcode,title);
    	}
    </script>
</html>
