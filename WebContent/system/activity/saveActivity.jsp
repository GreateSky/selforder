<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>     
<!DOCTYPE html >
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="<%=cxtPath%>/plugins/datepicker/bootstrap-datepicker3.min.css"/>
	    <style type="text/css">
	    	.checkbox label{
	    		margin-left: 10px;
	    	}
	    	#allmap{height:600px;width:560px}
	    </style>
	    <%
	    	String opt = request.getParameter("opt");
	    	String id = "";
	    	if(null != opt && "update".equalsIgnoreCase(opt)){
	    		id = request.getParameter("id");
	    	}else{
	    		opt = "";
	    	}
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
            	活动管理
            <small>活动维护</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 活动管理</a></li>
            <li class="active">活动维护</li>
          </ol>
        </section>

       <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">活动维护</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  <div class="row">
                  	<div class="col-md-6" style="border-right: 1px #cccccc dashed">
                  		<!--活动维护 start-->
						<div class="form-group has-warning">
						  <label for="inputEmail3" class="col-sm-3 control-label"><font color="red">*</font>&nbsp;活动名称：</label>
						  <div class="col-sm-4">
							<input type="text" class="form-control" id="title" placeholder="活动名称" value="" >
						  </div>
						</div>
						<div class="form-group has-warning">
						  <label for="inputEmail3" class="col-sm-3 control-label"><font color="red">*</font>&nbsp;活动类型：</label>
						  <div class="col-sm-4">
							<select class="form-control" id="type">
								<option selected="selected" value="1">折扣</option>
							</select>
						  </div>
						</div>
						<div class="form-group has-warning">
						  <label for="inputEmail3" class="col-sm-3 control-label">活动地址：</label>
						  <div class="col-sm-4">
							<input type="text" class="form-control" id="url" placeholder="活动地址" value="" >
						  </div>
						</div>
						<div class="form-group has-warning">
						  <label for="inputEmail3" class="col-sm-3 control-label"><font color="red">*</font>&nbsp;折扣：</label>
						  <div class="col-sm-4">
							<input type="number" class="form-control" id="discount" placeholder="范围：1-9.9折" value="" min=1 max=9.9>
						  </div>
						</div>
						<div class="form-group has-warning">
						  <label for="inputEmail3" class="col-sm-3 control-label"><font color="red">*</font>&nbsp;开始时间：</label>
						  <div class="col-sm-4">
							<div class="input-group">
							    <input id="begindate"  type="text" class="form-control datepicker" value="">
							    <div class="input-group-addon" style="border-color: #f39c12">
							       <i class="fa fa-calendar"></i>
							    </div>
							</div>
						  </div>
						</div>
						<div class="form-group has-warning">
						  <label for="inputEmail3" class="col-sm-3 control-label"><font color="red">*</font>&nbsp;结束日期：</label>
						  <div class="col-sm-4">
							<div class="input-group">
							    <input id="enddate"  type="text" class="form-control datepicker" value="">
							    <div class="input-group-addon" style="border-color: #f39c12">
							       <i class="fa fa-calendar"></i>
							    </div>
							</div>
						  </div>
						</div>
						<div class="form-group has-warning">
						  <label for="inputEmail3" class="col-sm-3 control-label"><font color="red">*</font>&nbsp;活动状态：</label>
						  <div class="col-sm-4">
							<select class="form-control" id="status" >
								<option selected="selected" value="0">未开启</option>
								<option  value="1">已开启</option>
								<option  value="2">已结束</option>
								<option  value="-1">已取消</option>
							</select>
						  </div>
						</div>
						<div class="form-group has-warning">
						  <label for="inputEmail3" class="col-sm-3 control-label">描述：</label>
						  <div class="col-sm-4">
							<textarea class="form-control" rows="3" id="remark"></textarea>
						  </div>
						</div>
						<!-- /活动维护 start-->
                  	</div>
                  	<!-- 活动已关联食谱列表 -->
                  	<div class="col-md-6">
                  		<table class="table table-striped animated flipInX" id="activityGoodsList">
                			<tr>
			                      <th>#</th>
			                      <th>名称</th>
			                      <th>价格</th>
			                      <th>优惠价</th>
			                      <th>操作</th>
			                 </tr>
                		</table>
                		<div class="row">
      						<div class="col-md-12">
      							<button tag="actionBtn_add" type="button" class="btn btn-warning pull-left" onclick="showGoodsWin()">新增</button>
      						</div>
      					</div>
                  	</div><!--/end 活动已关联食谱列表 -->
                  </div>
                  </div><!-- /.box-body -->
                  <div class="box-footer">
                  	<button type="button" class="btn btn-default" onclick="javascript:history.go(-1)">返回</button>
                    <button role="btn-shop-update" type="button" class="btn btn-info pull-right" onclick="saveActivity()">保存</button>
                  </div><!-- /.box-footer -->
                </form><!--/form end-->
              </div><!-- /.box -->
        </section><!-- /.content -->
         <!--modal 未关联食谱选择-->
        <div class="modal fade" id="selectGoodsWin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">未关联食谱选择</h4>
		      </div>
		      <div class="modal-body">
		      	<div class="input-group " style="width: 97%;margin: 10px">
			      	<input type="text" class="form-control" id="point_address"  placeholder="请输入名称进行搜索" value="" >
			      	<span class="input-group-btn">
	                    <button class="btn btn-info btn-flat" type="button" onclick="poindByAddress();">Go!</button>
	                </span>
			    </div>
				<table class="table table-striped animated flipInX" id="noSelectGoods">
			          <tr>
	                     <th>名称</th>
	                     <th>原价</th>
	                     <th>操作</th>
			          </tr>
			    </table>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /<!--modal 未关联食谱选择-->
	</body>
	<script src="<%=cxtPath%>/js/ajaxfileupload.js"></script>
	<script src="<%=cxtPath%>/plugins/datepicker/bootstrap-datepicker.min.js"></script>
	<script src="<%=cxtPath%>/plugins/datepicker/bootstrap-datepicker.zh-CN.min.js"></script>
    <script src="saveActivity.js"></script>
    <script>
    	$('.datepicker').datepicker({
    	    language: "zh-CN"
    	});
    </script>
</html>