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
	    <style>
	    	.table{
	    	}
	    	.table p{
	    		border: 1px solid #C7C7CC; 
	    		width: 80px; 
	    		height: 80px; 
	    		border-radius: 50%; 
	    		text-align: center; 
	    		vertical-align: middle;
	    		line-height: 80px; 
	    		background-color: #C7C7CC;
	    		color: white;
	    		font-size: 1.3em;
	    		margin-left:18px
	    	}
	    	.table div {
	    		font-size: 1em;
	    		margin-left:auto;margin-right:auto;
	    	}
	    	.table select {
	    		width: 65px;
	    	}
	    	.orderTable p{
	    		background-color: #0072B1;
	    		border: 1px solid #0072B1;
	    		
	    	}
	    	.beginTable p{
	    		background-color: #F24337;
	    		border: 1px solid #F24337;
	    		
	    	}
	    	.box-body ul li{
	    		list-style-type: none;
	    		float: left;
	    		margin: 2px;
	    		padding: 5px;
	    		border-radius: 5px;
	    		display: inline-block;
	    	}
	    </style>
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
	            <!--box-body-->
	            <div class="box box-body">
	            	<div class="form-group has-warning">
                      <label for="inputEmail3" class="col-sm-1 control-label">编码：</label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="title_search" placeholder="编码"  value="">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">状态：</label>
                      <div class="col-sm-2">
                        <select class="form-control" id="status_search">
                        	<option selected="selected" value="">全部</option>
							<option value="0">空闲</option>
							<option value="1">已下单</option>
							<option value="2">已开台</option>
						</select>
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">包厢：</label>
                      <div class="col-sm-2">
                        <select class="form-control" id="roomSelect"></select>
                      </div>
                      <div class="col-sm-2">
                        <button class="btn btn-default" type="button" onclick="search()"> 查询</button>
                        <button class="btn btn-default" type="button" onclick="clearParam()">重置</button>
                      </div>
                    </div>
	            	<div class="box box-warning" style="margin-top:50px">
	            		<ul id="tablelist">
			            		<!--  <li style="clear:both">
			            			<div class="table">
					            		<p>空闲</p> 
						            	<div>
						            		<span>C-001:</span>
						            		<select >
						            			<option selected="selected" >空闲</option>
						            			<option >已下单</option>
						            			<option >已开台</option>
						            		</select>
						            	</div>
					            	</div>
			            		</li>
			            		<li>
			            			<div class="table orderTable" >
					            		<p>已下单</p> 
						            	<div>
						            		<span>C-002:</span>
						            		<select >
						            			<option selected="selected" >空闲</option>
						            			<option >已下单</option>
						            			<option >已开台</option>
						            		</select>
						            	</div>
					            	</div>
			            		</li>
			            		<li>
			            			<div class="table beginTable" >
					            		<p>已开台</p> 
						            	<div>
						            		<span>C-002:</span>
						            		<select >
						            			<option selected="selected" >空闲</option>
						            			<option >已下单</option>
						            			<option >已开台</option>
						            		</select>
						            	</div>
					            	</div>
			            		</li>-->
			            	</ul>
	            	</div>
	            </div><!-- / box-body-->
          </div><!-- /.box -->
        </section><!-- /.content -->
	</body>
	<script src="<%=cxtPath%>/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="<%=cxtPath%>/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="tableView.js"></script>
    <script src="<%=cxtPath%>/js/common.js"></script>
    <script src="<%=cxtPath%>/plugins/layer/layer.js"></script>
    <script src="<%=cxtPath%>/js/jquery.twbsPagination.min.js"></script>
</html>
