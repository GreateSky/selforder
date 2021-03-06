<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>     
<!DOCTYPE html >
<html>
	<head>
		<meta charset="UTF-8">
	    <%
	    	String opt = request.getParameter("opt");//操作类型
	    	String id = request.getParameter("id");//队列ID
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
            	排号管理
            <small>队列维护</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 排号管理</a></li>
            <li class="active">队列维护</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">队列维护</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--门店维护 start-->
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;队列名称：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="title" placeholder="例如：1-2人桌" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;用餐人数少于等于：</label>
					  <div class="col-sm-2">
						<input type="number" class="form-control" id="limit_num" placeholder="" value="" min="2" >
					  </div>
					</div>
					<div class="form-group has-warning" style=" display: none">
					  <label for="inputEmail3" class="col-sm-2 control-label">营业时间区间：</label>
					  <div class="row">
	                    <div class="col-xs-1">
	                      <input type="text" class="form-control " id="starttime"  placeholder="例如：10点" />
	                    </div>
	                    <div class="col-xs-1">
	                      <input type="text" class="form-control " id="endtime" placeholder="例如：22点" />
	                    </div>
	                  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;队列号前缀：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="prefix" placeholder="例如:C-" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">提前通知人数：</label>
					  <div class="col-sm-2">
						<input type="number" class="form-control" id="notify_number" placeholder="提前通知人数" value="0" min="0" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">排序：</label>
					  <div class="col-sm-2">
						<input type="number" class="form-control" id="displayorder" placeholder="排序" value="1" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;是否启用：</label>
					  <div class="col-sm-2">
						<div class="radio">
	                        <label>
	                          <input type="radio" name="status" id="" value="1" checked />
	                          	启用
	                        </label>
	                        <label>
	                          <input type="radio" name="status" id="" value="0" />
	                          	不启用
	                        </label>
	                    </div>
					  </div>
					</div>
                  </div><!-- /.box-body -->
                  <div class="box-footer">
                  	<button type="button" class="btn btn-default" onclick="javascript:history.go(-1)">返回</button>
                    <button type="button" class="btn btn-info pull-right" onclick="saveQueueSetting()">保存</button>
                  </div><!-- /.box-footer -->
                </form><!--/form end-->
              </div><!-- /.box -->
        </section><!-- /.content -->
	</body>
    <script src="saveQueueSetting.js"></script>
</html>