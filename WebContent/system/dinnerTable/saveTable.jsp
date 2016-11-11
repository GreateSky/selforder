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
	    	String opt = request.getParameter("opt");//操作类型
	    	String id = request.getParameter("id");//餐桌ID
	    	String sid = request.getParameter("sid");//门店ID
	    %>
	    <script language="JavaScript" type="application/javascript">
	    	var opt = '<%=opt%>'
	    	var id = '<%=id%>';
			var sid = '<%=sid%>';
	    </script>
	</head>
	<body style="margin: 0; padding: 0; background-color: #ECF0F5; width: 100%; height: 100%;" >
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	餐桌管理
            <small>餐桌维护</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 餐桌管理</a></li>
            <li class="active">餐桌维护</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">餐桌维护</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--门店维护 start-->
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">编码：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="title" placeholder="编码" value="" onblur="checkTableCode()">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">所属包厢：</label>
					  <div class="col-sm-2">
						<select class="form-control" id="roomSelect"></select>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">用餐人数：</label>
					  <div class="col-sm-2">
						<input type="number" class="form-control" id="user_count" placeholder="用餐人数" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">最小消费价格(￥):</label>
					  <div class="col-sm-2">
						<input type="number" class="form-control" id="limit_price" placeholder="最小消费价格(￥)" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">排序(越大越靠前):</label>
					  <div class="col-sm-2">
						<input type="number" class="form-control" id="displayorder" placeholder="排序(越大越靠前)" value="0">
					  </div>
					</div>
					<div class="form-group has-warning" id="qrcodeDiv" style="display: none">
					  <label for="inputEmail3" class="col-sm-1 control-label">二维码：</label>
					  <div class="col-sm-2">
						<img src="" width="200px" height="200px" id="qrcodeid"></img>
					  </div>
					</div>
					<div class="form-group has-warning" id="service_qrcodeDiv" style="display: none">
					  <label for="inputEmail3" class="col-sm-1 control-label">呼叫服务：</label>
					  <div class="col-sm-2">
						<img src="" width="200px" height="200px" id="service_qrcodeid"></img>
					  </div>
					</div>
					<div class="form-group has-warning" id="update_qrcodeDiv" style="display: none">
					  <label for="inputEmail3" class="col-sm-1 control-label"></label>
					  <div class="col-sm-2">
						<button type="button" class="btn btn-info" onclick="updateQrcode()">更新餐桌二维码</button>
					  </div>
					</div><!-- /门店维护 start-->
                  </div><!-- /.box-body -->
                  <div class="box-footer">
                  	<button type="button" class="btn btn-default" onclick="javascript:history.go(-1)">返回</button>
                    <button type="button" class="btn btn-info pull-right" onclick="saveTable()">保存</button>
                  </div><!-- /.box-footer -->
                </form><!--/form end-->
              </div><!-- /.box -->
        </section><!-- /.content -->
	</body>
	<!-- jQuery 2.1.4 -->
    <script src="<%=cxtPath%>/js/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="<%=cxtPath%>/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="<%=cxtPath%>/plugins/layer/layer.js"></script>
    <script src="<%=cxtPath%>/js/common.js"></script>
    <script src="<%=cxtPath%>/js/jquery.twbsPagination.min.js"></script>
    <script src="savetable.js"></script>
</html>