<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="<%=cxtPath%>/plugins/datepicker/bootstrap-datepicker3.min.css"/>
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
                      <label for="inputEmail3" class="col-sm-1 control-label">姓名：</label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="username_search" placeholder="姓名 "  value="">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">电话：</label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="tel_search" placeholder="电话 "  value="">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">订单开始日期：</label>
                      <div class="col-sm-2">
                        <input id="begindate"  type="text" class="form-control datepicker" value="">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">订单结束日期：</label>
                      <div class="col-sm-2">
                         <input id="enddate"  type="text" class="form-control datepicker" value="">
                      </div>
                    </div>
                    <div class="form-group has-warning">
	                	<div class="col-sm-6">
	                        <button type="button" class="btn btn-info" onclick="search()"><i class="fa fa-search"></i>&nbsp;查询</button>
	                        <button type="button"  class="btn btn-info" onclick="clearParam()"><i class="fa fa-refresh"></i>&nbsp;重置</button>
	                        <button type="button"  class="btn btn-warning" onclick="javascript:window.location.href='saveOrder.jsp'"><i class="fa fa-plus-circle"></i>&nbsp;新增</button>
                        </div>
                    </div><!--/搜索信息 start-->
                    <!--门店列表start-->
                    <table id="orderList" class="table table-striped animated flipInX">
                    <tr>
                      <th>#</th>
                      <th>订单编码</th>
                      <th>订单日期</th>
                      <th>状态</th>
                      <th>客户姓名</th>
                      <th>电话</th>
                      <th>用餐时间</th>
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
    <script src="<%=cxtPath%>/js/jquery.twbsPagination.min.js"></script>
    <script src="<%=cxtPath%>/plugins/datepicker/bootstrap-datepicker.min.js"></script>
	<script src="<%=cxtPath%>/plugins/datepicker/bootstrap-datepicker.zh-CN.min.js"></script>
    <script src="orderList.js"></script>
    <script>
    	$('.datepicker').datepicker({
    	    language: "zh-CN"
    	});
    </script>
</html>