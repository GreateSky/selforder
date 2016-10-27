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
            	员工管理
            <small>员工列表</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 员工管理</a></li>
            <li class="active">员工列表</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">员工列表</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--搜索信息 start-->
                    <div class="form-group has-warning">
                      <label for="inputEmail3" class="col-sm-1 control-label">员工名称：</label>
                      <div class="col-sm-1">
                        <input type="text" class="form-control" id="search_empname" placeholder="员工名称">
                      </div>
                       <label for="inputEmail3" class="col-sm-1 control-label">编码：</label>
                      <div class="col-sm-1">
                        <input type="text" class="form-control" id="search_empcode" placeholder="员工名称">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">员工类型：</label>
                      <div class="col-sm-1">
                        <select class="form-control" id="search_type">
                        	<option value="" selected="selected">全部</option>
                        	<option value="A">平台用户</option>
                        	<option value="B">商户用户</option>
                        	<option value="S">门店用户</option>
                        </select>
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">员工状态：</label>
                      <div class="col-sm-1">
                        <select class="form-control" id="search_status">
                        	<option value="" selected="selected">全部</option>
                        	<option value="1">实习</option>
                        	<option value="2">正式</option>
                        	<option value="3">其他</option>
                        	<option value="-1">离职</option>
                        </select>
                      </div>
                      
                    </div>
                    <div class="form-group has-warning">
	                	<div class="col-sm-6">
	                        <button type="button" class="btn btn-info" onclick="search()"><i class="fa fa-search"></i>&nbsp;查询</button>
	                        <button type="button" class="btn btn-info" onclick="clearParam()"><i class="fa fa-refresh"></i>&nbsp;重置</button>
	                        <button role="btn-shop-add" type="button" class="btn btn-warning" onclick="javascript:window.location.href='saveActivity.jsp'"><i class="fa fa-plus-circle"></i>&nbsp;新增</button>
                        </div>
                    </div><!--/搜索信息 start-->
                    <!--员工列表start-->
                    <table class="table table-striped animated flipInX" id="employeeList">
	                    <tr>
	                      <th>#</th>
	                      <th>门店</th>
	                      <th>名称</th>
	                      <th>编码</th>
	                      <th>性别</th>
	                      <th>状态</th>
	                      <th>电话</th>
	                      <th>类型</th>
	                      <th>操作</th>
	                    </tr>
                  	</table><!--/员工列表-->
                  	<!--分页条件-->
                  	<nav>
					  <ul class="pagination" id="pagination" data-option="{'pageSize':20,'loadData':'search()'}"></ul>
				    </nav><!--/分页条件-->
                  </div><!-- /.box-body -->
                </form><!--/ form start -->
              </div><!-- /.box -->
        </section><!-- /.content -->
	</body>
	<!-- jQuery 2.1.4 -->
    <script src="<%=cxtPath%>/js/jquery.twbsPagination.min.js"></script>
    <script src="employeeList.js"></script>
</html>