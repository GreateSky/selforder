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
	    	String opt = request.getParameter("opt");
	    	String sid = "";
	    	if(null != opt && "update".equalsIgnoreCase(opt)){
	    		sid = request.getParameter("sid");
	    	}else{
	    		opt = "";
	    	}
	    %>
	    <script language="JavaScript" type="application/javascript">
	    	var opt = '<%=opt%>'
	    	var sid = '<%=sid%>';
	    </script>
	</head>
	<body style="margin: 0; padding: 0; background-color: #ECF0F5; width: 100%; height: 100%;" >
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	门店管理
            <small>门店维护</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 门店管理</a></li>
            <li class="active">门店维护</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">门店维护</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--门店维护 start-->
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">商户：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="bname" placeholder="商户" value="" disabled="disabled">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">门店：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="sname" name="shop.sname" placeholder="门店" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">负责人：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="linkman"  name="shop.linkman" placeholder="负责人" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">负责人电话：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="phone" name="shop.phone" placeholder="负责人电话" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">地址：</label>
					  <div class="col-sm-4">
						<input type="text" class="form-control" id="address" name="shop.address" placeholder="地址" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">经度：</label>
					  <div class="col-sm-1">
						<input type="text" class="form-control" id="longitude" name="shop.longitude" placeholder="经度" readonly="readonly" value="">
					  </div>
					  <label for="inputEmail3" class="col-sm-1 control-label">维度：</label>
					  <div class="col-sm-1">
						<input type="text" class="form-control" id="latitude" name="shop.latitude" placeholder="维度" readonly="readonly" value="">
					  </div>
					  <div class="col-sm-1">
						<button class="btn btn-default">定位</button>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">门头照片：</label>
					  <div class="col-sm-2">
						<input type="file" id="fileid" name="fileid" class="form-control" />
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">支持外卖：</label>
					  <div class="col-sm-2">
						<div class="radio">
	                        <label>
	                          <input type="radio" name="shop.isoutsell" id="optionsRadios1" value="1" checked />
	                          	是
	                        </label>
	                        <label>
	                          <input type="radio" name="shop.isoutsell" id="optionsRadios1" value="0" />
	                          	否
	                        </label>
	                    </div>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">支持排号：</label>
					  <div class="col-sm-2">
						<div class="radio">
	                        <label>
	                          <input type="radio" name="shop.isarray" id="isarray" value="1" checked />
	                          	是
	                        </label>
	                        <label>
	                          <input type="radio" name="shop.isarray" id="isarray" value="0" />
	                          	否
	                        </label>
	                    </div>
					  </div>
					</div><!-- /门店维护 start-->
                  </div><!-- /.box-body -->
                  <div class="box-footer">
                  	<button type="button" class="btn btn-default">取消</button>
                    <button type="button" onclick="uploadFile()" class="btn btn-info pull-right">保存</button>
                  </div><!-- /.box-footer -->
                </form><!--/form end-->
              </div><!-- /.box -->
        </section><!-- /.content -->
        <img id="shopimg" src="" width="150px" height="150px" style="position:absolute;top: 110px; left: 600px; border-radius: 8px;"></img>
	</body>
	<!-- jQuery 2.1.4 -->
    <script src="<%=cxtPath%>/js/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="<%=cxtPath%>/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="<%=cxtPath%>/js/ajaxfileupload.js"></script>
    <script src="<%=cxtPath%>/js/common.js"></script>
    <script src="<%=cxtPath%>/plugins/layer/layer.js"></script>
    <script src="saveShop.js"></script>
</html>