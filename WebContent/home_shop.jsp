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
	    <link href="dist/FontAwesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	    <!-- Ionicons 2.0.0 -->
	    <link href="dist/ionicons/css/ionicons.min.css" rel="stylesheet" type="text/css" />
	    <!-- Theme style -->
	    <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
	    <!-- AdminLTE Skins. Choose a skin from the css/skins
	         folder instead of downloading all of them to reduce the load. -->
	    <link href="dist/css/skins/skin-yellow-light.min.css" rel="stylesheet" type="text/css" />
	    <!-- iCheck -->
	    <link href="plugins/iCheck/flat/blue.css" rel="stylesheet" type="text/css" />
	    <!-- bootstrap wysihtml5 - text editor -->
	    <link href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />
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
          	<sec:authorize ifAnyGranted ="ROLE_GOODS_MGR">
	            <div class="col-lg-3 col-xs-6">
	              <!-- small box -->
	              <div class="small-box bg-aqua">
	                <div class="inner">
	                  <h3>食谱管理</h3>
	                  <p>查看食谱</p>
	                </div>
	                <div class="icon">
	                  <i class="ion ion-stats-bars"></i>
	                </div>
	                <a href="javascript:void(0);" onclick="linkMainTab('system/goods/goodsList.jsp','goodsmgr','食谱列表')" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
	              </div>
	            </div><!-- ./col -->
            </sec:authorize >
            <sec:authorize ifAnyGranted ="ROLE_QUEUE_MGR">
	            <div class="col-lg-3 col-xs-6">
	              <!-- small box -->
	              <div class="small-box bg-green">
	                <div class="inner">
	                  <h3>排号管理</h3>
	                  <p>查看排号详情</p>
	                </div>
	                <div class="icon">
	                  <i class="ion ion-stats-bars"></i>
	                </div>
	                <a href="javascript:void(0);" onclick="linkMainTab('system/queue/queueList.jsp','queuelist','排号列表')" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
	              </div>
	            </div><!-- ./col -->
            </sec:authorize >
            <sec:authorize ifAnyGranted ="ROLE_TABLE_MGR">
	            <div class="col-lg-3 col-xs-6">
	              <!-- small box -->
	              <div class="small-box bg-yellow">
	                <div class="inner">
	                  <h3>餐桌管理</h3>
	                  <p>查看餐桌详情</p>
	                </div>
	                <div class="icon">
	                  <i class="ion ion-person-add"></i>
	                </div>
	                <a href="javascript:void(0);" onclick="linkMainTab('system/dinnerTable/tableList.jsp','tablelist','餐桌列表')" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
	              </div>
	            </div><!-- ./col -->
            </sec:authorize >
            <sec:authorize ifAnyGranted ="ROLE_ACTIVITY_MGR">
	            <div class="col-lg-3 col-xs-6">
	              <!-- small box -->
	              <div class="small-box bg-red">
	                <div class="inner">
	                  <h3>活动管理</h3>
	                  <p>查看活动</p>
	                </div>
	                <div class="icon">
	                  <i class="ion ion-pie-graph"></i>
	                </div>
	                <a href="javascript:void(0);" onclick="linkMainTab('system/activity/activityList.jsp','activitylist','活动列表')" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
	              </div>
	            </div><!-- ./col -->
            </sec:authorize >
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
