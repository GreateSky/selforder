<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>     
<!DOCTYPE html >
<html>
	<head>
		<meta charset="UTF-8">
	    <style type="text/css">
	    	.checkbox label{
	    		margin-left: 10px;
	    	}
	    	#allmap{height:600px;width:560px}
	    </style>
	    <%
	    	String opt = request.getParameter("opt");
	    	String oid = "";
	    	if(null != opt && "update".equalsIgnoreCase(opt)){
	    		oid = request.getParameter("oid");
	    	}else{
	    		opt = "";
	    	}
	    %>
 	    <script language="JavaScript" type="application/javascript">
	    	var opt = '<%=opt%>'
	    	var oid = '<%=oid%>';
	    </script>
	</head>
	<body style="margin: 0; padding: 0; background-color: #ECF0F5; width: 100%; height: 100%;" >
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	订单管理
            <small>订单列表</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 订单管理</a></li>
            <li class="active">订单维护</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">订单维护</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--门店维护 start-->
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">姓名：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="username" placeholder="姓名" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">电话：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="tel" placeholder="电话" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">用餐时间：</label>
					  <div class="col-sm-2">
						<input type="datetime-local" class="form-control" id="meal_time" placeholder="用餐时间" value="">
					  </div>
					</div>
					<div class="form-group has-warning" >
					  <label for="inputEmail3" class="col-sm-2 control-label">备注:</label>
					  <div class="col-sm-2">
						<textarea rows="5" class="form-control" id="remark"></textarea>
					  </div>
					</div> <!-- /门店维护 start-->
                  </div><!-- /.box-body -->
                  <div class="box-footer">
                  	<button type="button" class="btn btn-default">取消</button>
                    <button type="button" onclick="saveOrder()" class="btn btn-info pull-right">保存</button>
                  </div><!-- /.box-footer -->
                </form><!--/form end-->
              </div><!-- /.box -->
        </section><!-- /.content -->
	</body>
    <script src="saveOrder.js"></script>
</html>