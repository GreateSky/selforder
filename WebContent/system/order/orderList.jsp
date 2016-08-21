<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<!-- Tell the browser to be responsive to screen width -->
	    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	    <!-- Bootstrap 3.3.4 -->
	    <link href="<%=cxtPath%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <!-- FontAwesome 4.3.0 -->
	    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	    <link href="<%=cxtPath%>/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
	    <link href="<%=cxtPath%>/css/animate.css" rel="stylesheet" type="text/css" />
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
            <li class="active">订单列表</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">订单列表</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--搜索信息 start-->
                    <div class="form-group has-warning">
                      <label for="inputEmail3" class="col-sm-1 control-label">编码：</label>
                      <div class="col-sm-1">
                        <input type="text" class="form-control" id="inputEmail3" placeholder="订单编码 "  value="">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">订单开始日期：</label>
                      <div class="col-sm-1">
                        <input type="datetime" class="form-control" id="inputEmail3" placeholder="订单开始日期">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">订单结束日期：</label>
                      <div class="col-sm-1">
                        <input type="datetime" class="form-control" id="inputEmail3" placeholder="订单结束日期">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">客户名称：</label>
                      <div class="col-sm-1">
                        <input type="text" class="form-control" id="inputEmail3" placeholder="客户名称">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">客户电话：</label>
                      <div class="col-sm-1">
                        <input type="text" class="form-control" id="inputEmail3" placeholder="客户电话">
                      </div>
                    </div>
                    <div class="form-group has-warning">
	                	<div class="col-sm-6">
	                        <button class="btn btn-info"><i class="fa fa-search"></i>&nbsp;查询</button>
	                        <button class="btn btn-info"><i class="fa fa-refresh"></i>&nbsp;重置</button>
	                        <button class="btn btn-warning"><i class="fa fa-plus-circle"></i>&nbsp;新增</button>
	                        <button class="btn btn-danger"><i class="fa fa-refresh"></i>&nbsp;修改</button>
                        </div>
                    </div><!--/搜索信息 start-->
                    <!--门店列表start-->
                    <table id="orderList" class="table table-striped animated flipInX">
                    <tr>
                      <th>#</th>
                      <th>订单编码</th>
                      <th>订单日期</th>
                      <th>餐桌号</th>
                      <th>客户姓名</th>
                      <th>电话</th>
                      <th>消费金额(元)</th>
                      <th>实收金额(元)</th>
                      <th>订单状态</th>
                      <th>口味</th>
                      <th>备注</th>
                      <th>操作</th>
                    </tr>
                  </table><!--/门店列表-->
                  <!--分页条件-->
                  <nav>
					  <ul class="pagination" id="pagination" data-option="{'pageSize':20,'loadData':'search()'}">
					  </ul>
				   </nav><!--/分页条件-->
                  </div><!-- /.box-body -->
                </form><!--/ form start -->
              </div><!-- /.box -->
        </section><!-- /.content -->
	</body>
	<!-- jQuery 2.1.4 -->
    <script src="<%=cxtPath%>/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="<%=cxtPath%>/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="orderList.js"></script>
    <script src="<%=cxtPath%>/js/common.js"></script>
    <script src="<%=cxtPath%>/plugins/layer/layer.js"></script>
    <script src="<%=cxtPath%>/js/jquery.twbsPagination.min.js"></script>
    <script>
    </script>
</html>