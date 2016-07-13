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
            	门店管理
            <small>门店列表</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 门店管理</a></li>
            <li class="active">门店列表</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">门店列表</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--搜索信息 start-->
                    <div class="form-group has-warning">
                      <label for="inputEmail3" class="col-sm-1 control-label">店名：</label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="inputEmail3" placeholder="店名 "  value="">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">负责人：</label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="inputEmail3" placeholder="负责人">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">负责人电话：</label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="inputEmail3" placeholder="负责人电话">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">地址：</label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="inputEmail3" placeholder="地址">
                      </div>
                    </div>
                    <div class="form-group has-warning">
	                	<div class="col-sm-6">
	                        <button type="button" onclick="search();" class="btn btn-info"><i class="fa fa-search"></i>&nbsp;查询</button>
	                        <button type="button" onclick="clearParam();" class="btn btn-info"><i class="fa fa-refresh"></i>&nbsp;重置</button>
	                        <button type="button" onclick="javascript:window.location.href='saveShop.jsp'" class="btn btn-warning"><i class="fa fa-plus-circle"></i>&nbsp;新增</button>
                        </div>
                    </div><!--/搜索信息 start-->
                    <!--门店列表start-->
                    <table class="table table-striped animated flipInX" id="shoplist">
                    <tr>
                      <th>#</th>
                      <th>门店名</th>
                      <th>电话</th>
                      <th>开时间</th>
                      <th>结束时间</th>
                      <th>店内点餐</th>
                      <th>外卖订餐</th>
                      <th>支持预定</th>
                      <th>支持排队</th>
                      <th>手机端显示</th>
                      <th>半径</th>
                      <th>地址</th>
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
    <script src="shopList.js"></script>
    <script src="<%=cxtPath%>/js/common.js"></script>
    <script src="<%=cxtPath%>/plugins/layer/layer.js"></script>
    <script src="<%=cxtPath%>/js/jquery.twbsPagination.min.js"></script>
    <script>
    </script>
</html>