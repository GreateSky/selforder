<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body style="margin: 0; padding: 0; background-color: #ECF0F5; width: 100%; height: 100%;" >
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	活动管理
            <small>活动列表</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 活动管理</a></li>
            <li class="active">活动列表</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">活动列表</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--搜索信息 start-->
                    <div class="form-group has-warning">
                      <label for="inputEmail3" class="col-sm-1 control-label">名称：</label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="search_title" placeholder="活动名称">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">状态：</label>
                      <div class="col-sm-2">
                        <select class="form-control" id="search_status">
                        	<option value="" selected="selected">全部</option>
                        	<option value="0">未开启</option>
                        	<option value="1">已开启</option>
                        	<option value="2">已结束</option>
                        	<option value="-1">取消</option>
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
                    <!--门店列表start-->
                    <table class="table table-striped animated flipInX" id="activityList">
	                    <tr>
	                      <th>#</th>
	                      <th>活动名称</th>
	                      <th>折扣</th>
	                      <th>活动状态</th>
	                      <th>活动类型</th>
	                      <th>开始日期</th>
	                      <th>结束日期</th>
	                      <th>操作</th>
	                    </tr>
                  	</table><!--/门店列表-->
                  	<!--分页条件-->
                  	<nav>
					  <ul class="pagination" id="pagination" data-option="{'pageSize':20,'loadData':'search()'}"></ul>
				    </nav><!--/分页条件-->
                  </div><!-- /.box-body -->
                </form><!--/ form start -->
              </div><!-- /.box -->
        </section><!-- /.content -->
	</body>
    <script src="<%=cxtPath%>/js/jquery.twbsPagination.min.js"></script>
    <script src="activityList.js"></script>
</html>