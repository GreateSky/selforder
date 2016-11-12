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
            	商户管理
            <small>商户列表</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 商户管理</a></li>
            <li class="active">商户列表</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">商户列表</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--搜索信息 start-->
                    <div class="form-group has-warning">
                      <label for="inputEmail3" class="col-sm-1 control-label">名称：</label>
                      <div class="col-sm-2">
                        <input id="bname_search" type="text" class="form-control" id="inputEmail3" placeholder="名称 "  value="">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">法人：</label>
                      <div class="col-sm-2">
                        <input id="legaler_search" type="text" class="form-control" id="inputEmail3" placeholder="法人 "  value="">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">联系电话：</label>
                      <div class="col-sm-2">
                        <input id="phone_search" type="text" class="form-control" id="inputEmail3" placeholder="联系电话 "  value="">
                      </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">合作状态：</label>
                      <div class="col-sm-2">
                        <select id="status_search" class="form-control">
                        	<option selected="selected" value="">全部</option>
							<option value="0">试点</option>
							<option value="1">正式</option>
							<option value="2">暂停</option>
							<option value="-1">不合作</option>
						</select>
                      </div>
                    </div>
                    <div class="form-group has-warning">
	                	<div class="col-sm-6">
	                        <button type="button" onclick="search();" class="btn btn-info"><i class="fa fa-search"></i>&nbsp;查询</button>
	                        <button type="button" onclick="clearParam();" class="btn btn-info"><i class="fa fa-refresh"></i>&nbsp;重置</button>
	                        <button type="button" onclick="javascript:window.location.href='saveBusiness.jsp'" class="btn btn-warning"><i class="fa fa-plus-circle"></i>&nbsp;新增</button>
                        </div>
                    </div><!--/搜索信息 start-->
                    <!--门店列表start-->
                    <table id="businessList" class="table table-striped ">
                    <tr>
                      <th>#</th>
                      <th>名称</th>
                      <th>法人</th>
                      <th>联系电话</th>
                      <th>合作状态</th>
                      <th>地址</th>
                      <th>开始合作时间</th>
                      <th>终止合作时间</th>
                      <th>操作</th>
                    </tr>
                  </table><!--/门店列表-->
                  <!--分页条件-->
                  <nav>
					  <ul class="pagination" id="pagination" data-option="{'pageSize':2,'loadData':'search()'}">
					  </ul>
				   </nav><!--/分页条件-->
                  </div><!-- /.box-body -->
                </form><!--/ form start -->
              </div><!-- /.box -->
        </section><!-- /.content -->
	</body>
    <script src="<%=cxtPath%>/js/jquery.twbsPagination.min.js"></script>
    <script src="businessList.js"></script>
</html>