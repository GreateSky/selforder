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
	    <style type="text/css">
	    	.checkbox label{
	    		margin-left: 10px;
	    	}
	    	#allmap{height:600px;width:560px}
	    </style>
	    <%
	    	String opt = request.getParameter("opt");
	    	String id = "";
	    	if(null != opt && "update".equalsIgnoreCase(opt)){
	    		id = request.getParameter("id");
	    	}else{
	    		opt = "";
	    	}
	    %>
	    <script language="JavaScript" type="application/javascript">
	    	var opt = '<%=opt%>'
	    	var id = '<%=id%>';
	    </script>
	</head>
	<body style="margin: 0; padding: 0; background-color: #ECF0F5; width: 100%; height: 100%;" >
       <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	活动管理
            <small>活动维护</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 活动管理</a></li>
            <li class="active">活动维护</li>
          </ol>
        </section>

       <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">活动维护</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--门店维护 start-->
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">活动名称：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="title" placeholder="活动名称" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">活动类型：</label>
					  <div class="col-sm-2">
						<select class="form-control" id="type">
							<option selected="selected" value="1">折扣</option>
							<option  value="2">优惠</option>
						</select>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">活动地址：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="url" placeholder="活动地址" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">折扣/优惠金额：</label>
					  <div class="col-sm-2">
						<input type="number" class="form-control" id="discount" placeholder="折扣/优惠金额" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">使用下限(元)：</label>
					  <div class="col-sm-2">
						<input type="number" class="form-control" id="leastcost" placeholder="使用下限(元)" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">开始时间：</label>
					  <div class="col-sm-2">
						<input type="date" class="form-control" id="begindate" placeholder="开始时间" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">结束日期：</label>
					  <div class="col-sm-2">
						<input type="date" class="form-control" id="enddate" placeholder="结束日期" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">活动状态：</label>
					  <div class="col-sm-2">
						<select class="form-control" id="status" >
							<option selected="selected" value="0">未开启</option>
							<option  value="1">已开启</option>
							<option  value="2">已结束</option>
							<option  value="-1">已取消</option>
						</select>
					  </div>
					</div>
					<!-- /门店维护 start-->
                  </div><!-- /.box-body -->
                  <div class="box-footer">
                  	<button type="submit" class="btn btn-default">取消</button>
                    <button type="button" class="btn btn-info pull-right" onclick="saveActivity()">保存</button>
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
    <script src="saveActivity.js"></script>
</html>