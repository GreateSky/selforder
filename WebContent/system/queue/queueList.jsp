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
            	排号管理
            <small>排号列表</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 排号管理</a></li>
            <li class="active">排号列表</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">排号列表</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--搜索信息 start-->
                    <div class="form-group has-warning">
                      <label for="inputEmail3" class="col-sm-1 control-label">队列名称：</label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="inputEmail3" placeholder="队列名称 "  value="">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">编号前缀：</label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="inputEmail3" placeholder="队列编号前缀 "  value="">
                      </div>
                    </div>
                    <div class="form-group has-warning">
	                	<div class="col-sm-6">
	                        <button class="btn btn-info"><i class="fa fa-search"></i>&nbsp;查询</button>
	                        <button class="btn btn-info"><i class="fa fa-refresh"></i>&nbsp;重置</button>
	                        <button type="button" class="btn btn-warning" onclick="javascript:window.location.href='saveQueueSetting.jsp'"><i class="fa fa-plus-circle"></i>&nbsp;新增队列</button>
                        </div>
                    </div><!--/搜索信息 start-->
                    <!--门店列表start-->
                    <table class="table table-striped animated flipInX" id="queueList">
                    <tr>
                      <th>#</th>
                      <th>队列名称</th>
                      <th>状态</th>
                      <th>提前通知人数</th>
                      <th>当前排队人数</th>
                      <th>下一排号</th>
                      <th>操作</th>
                    </tr>
                  </table><!--/门店列表-->
                  </div><!-- /.box-body -->
                </form><!--/ form start -->
              </div><!-- /.box -->
        </section><!-- /.content -->
        
        <!--modal 申请排队-->
        <div class="modal fade" id="applyQueueNum" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">申请排队</h4>
		      </div>
		      <div class="modal-body">
		      	<table>
		      		<tr style="height: 50px">
		      			<td align="right">排号：</td>
		      			<td align="left"><label class="label label-warning" style="font-size:1.5em" id="queueNum"></label></td>
		      		</tr>
		      		<tr style="height: 50px">
		      			<td align="right">队列类型：</td>
		      			<td align="left"><label class="label label-info" style="font-size:1.5em" id="queueType"></label></td>
		      		</tr>
		      		<tr style="height: 50px">
		      			<td align="right">在您前面共有：</td>
		      			<td align="left"><label class="label label-info" style="font-size:1.5em" id="pervNum"></label></td>
		      		</tr>
		      	</table>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" onclick="pointOK()">打印</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /<!--modal 申请排队-->
		<!--modal 叫号-->
        <div class="modal fade" id="callNextQueueNumWin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">叫号</h4>
		      </div>
		      <div class="modal-body">
		      	<table>
		      		<tr style="height: 50px">
		      			<td align="right">当前排号：</td>
		      			<td align="left"><label class="label label-warning" style="font-size:1.5em" id="call_queueNum"></label>
		      				<input type="hidden" id="call_id">
		      			</td>
		      		</tr>
		      		<tr style="height: 50px">
		      			<td align="right">队列类型：</td>
		      			<td align="left"><label class="label label-info" style="font-size:1.5em" id="call_queueType"></label></td>
		      		</tr>
		      		<tr style="height: 50px">
		      			<td align="right">申请类型：</td>
		      			<td align="left"><label class="label label-info" style="font-size:1.5em" id="call_applyType"></label></td>
		      		</tr>
		      	</table>
		      </div>
		      <div class="modal-footer">
       			 <button type="button" class="btn btn-warning pull-right" onclick="updateStatus(1)" style="margin:auto 5px;">已叫号</button>
       			 <button type="button" class="btn btn-danger  pull-right" onclick="updateStatus(2)" style="margin:auto 5px;">过号</button>
       			 <button type="button" class="btn btn-default pull-right" style="margin:auto 5px;" onclick="javascript:$('#callNextQueueNumWin').modal('hide');" >取消</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /<!--modal 叫号-->
	</body>
    <script src="queueList.js"></script>
</html>