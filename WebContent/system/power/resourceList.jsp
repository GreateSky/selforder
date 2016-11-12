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
            	权限管理
            <small>资源列表</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 权限管理</a></li>
            <li class="active">资源列表</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">资源列表</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--搜索信息 start-->
                    <div class="form-group has-warning">
                      <label for="inputEmail3" class="col-sm-1 control-label">名称：</label>
                      <div class="col-sm-2">
                        <input id="rname_search" type="text" class="form-control" id="inputEmail3" placeholder="名称 "  value="">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">URL：</label>
                      <div class="col-sm-2">
                        <input id="rurl_search" type="text" class="form-control" id="inputEmail3" placeholder="URL "  value="">
                      </div>
                      <div class="col-sm-6">
	                        <button type="button" onclick="search();" class="btn btn-info"><i class="fa fa-search"></i>&nbsp;查询</button>
	                        <button type="button" onclick="clearParam();" class="btn btn-info"><i class="fa fa-refresh"></i>&nbsp;重置</button>
	                        <button type="button" onclick="openResourceWin('add','')" class="btn btn-warning"><i class="fa fa-plus-circle"></i>&nbsp;新增</button>
                        </div>
                    </div><!--/搜索信息 start-->
                    <!--门店列表start-->
                    <table id="resourceList" class="table table-striped ">
                    <tr>
                      <th>#</th>
                      <th>名称</th>
                      <th>url</th>
                      <th>说明</th>
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
        <!--modal 资源详情-->
        <div class="modal fade" id="resourceWin" openType="" rid="" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">资源详情</h4>
		      </div>
		      <div class="modal-body">
				<form role="form">
				   <div class="form-group has-warning" >
				      <label for="wcode">名称</label>
				      <input type="text" class="form-control" id="rname" name="resource.rname" placeholder="名称" value="" >
				   </div>
				   <div class="form-group has-warning" >
				      <label for="wcode">URL</label>
				      <input type="text" class="form-control" id="rurl" name="resource.rurl" placeholder="URL" value="" >
				   </div>
				   <div class="form-group has-warning">
				      <label for="content">说明：</label>
				      <textarea class="form-control" rows="3" id="remark" name="resource.remark" placeholder="说明"></textarea>
				   </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" onclick="saveBtn()">保存</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /<!--modal 资源详情-->
	</body>
    <script src="<%=cxtPath%>/js/jquery.twbsPagination.min.js"></script>
    <script src="resourceList.js"></script>
</html>