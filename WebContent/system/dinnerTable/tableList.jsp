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
	    <%
	    	String sid = request.getParameter("sid");
	    %>
	    <script type="text/javascript">
	    	var sid = '<%=sid%>';
	    </script>
	</head>
	<body style="margin: 0; padding: 0; background-color: #ECF0F5; width: 100%; height: 100%;" >
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	餐桌管理
            <small>餐桌列表</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 餐桌管理</a></li>
            <li class="active">餐桌列表</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">餐桌列表</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--搜索信息 start-->
                    <div class="form-group has-warning">
                      <label for="inputEmail3" class="col-sm-1 control-label">编码：</label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="title_search" placeholder="编码"  value="">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">状态：</label>
                      <div class="col-sm-2">
                        <select class="form-control" id="status_search">
							<option value="">全部</option>
							<option value="0">空闲</option>
							<option value="1">已下单</option>
							<option value="2">已开台</option>
							<option value="3">已预约</option>
						</select>
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">包厢：</label>
                      <div class="col-sm-2">
                        <select class="form-control" id="roomSelect"></select>
                      </div>
                    </div>
                    <div class="form-group has-warning">
                    	<div class="col-sm-6">
	                        <button type="button" class="btn btn-info" onclick="search()">查询</button>
	                        <button type="button" class="btn btn-info" onclick="clearParam()">重置</button>
	                        <button type="button" class="btn btn-warning" onclick="javascript:window.location.href='saveTable.jsp?sid='+sid">创建餐桌</button>
	                        <button type="button" class="btn btn-warning" data-toggle="modal" onclick="showRoom()">创建包厢</button>
	                    </div>
                    </div>
                    <!--/搜索信息 start-->
                    <!--门店列表start-->
                    <table class="table table-striped animated flipInX" id="tableList">
                    	<tr>
	                      <th >#</th>
	                      <th >所属包厢</th>
	                      <th >编码</th>
	                      <th >用餐人数</th>
	                      <th >最低消费(￥)</th>
	                      <th >状态</th>
	                      <th >排序</th>
	                      <th >二维码</th>
	                      <th >服务码</th>
	                      <th >操作</th>
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
        <!--modal 创建包厢-->
        <div class="modal fade" id="createRoom" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" opttype="">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">包厢管理</h4>
		      </div>
		      <div class="modal-body">
			    <div class="row" style="margin-bottom: 10px">
			      <div class="col-xs-12">
			      	  <div class="input-group input-group-sm">
				      	<input type="text" class="form-control" id="rname"  placeholder="名称" value="" >
				      	<span class="input-group-btn">
	                      <button class="btn btn-info btn-flat" type="button" onclick="saveRoom()">创建</button>
	                    </span>
				      </div>
			      </div>
			    </div>
				<table id="roomList" class="table table-striped " title="包厢列表">
                    <tr>
                      <th>#</th>
                      <th>名称</th>
                      <th>包含餐桌数</th>
                      <th>操作</th>
                    </tr>
                </table><!--/门店列表-->
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /<!--modal 创建包厢-->
	</body>
	<!-- jQuery 2.1.4 -->
    <script src="<%=cxtPath%>/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="<%=cxtPath%>/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="tableList.js"></script>
    <script src="<%=cxtPath%>/js/common.js"></script>
    <script src="<%=cxtPath%>/plugins/layer/layer.js"></script>
    <script src="<%=cxtPath%>/js/jquery.twbsPagination.min.js"></script>
</html>