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
		<!--zTree ç»ä»¶-->
		<link rel="stylesheet" href="../../plugins/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    <style type="text/css">
			div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #EBECEE;text-align: left; border: 1px solid #ECF0F5; }
			div#rMenu ul li span{
				color: black;
			}
			div#rMenu ul li {
				border-bottom: 1px solid #C9CCCF;
				height: 35px;
			}
		</style>
	</head>
	<body style="margin: 0; padding: 0; background-color: #ECF0F5; width: 100%; height: 100%;" >
        <!-- Content Header (Page header) -->
        <section class="content-header clearfix">
          <h1>
            	组织架构管理
            <small>组织架构维护</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 组织架构管理</a></li>
            <li class="active">组织架构维护</li>
          </ol>
        </section>
        <aside class="main-sidebar">
	        <!-- sidebar: style can be found in sidebar.less -->
	        <section class="sidebar" style="margin-top: 8px;margin-left: 4px;width: 200px;" >
	          	<div class="content_wrap" style="border: 1px solid #00C0EF; background-color: white;height: 100%;width: 100%;">
					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
				<div id="rMenu">
					<ul class="sidebar-menu" >
						<li class="treeview" id="m_add" onclick="addTreeNode();">
			              <a href="#">
			                <i ></i> <span >新增下级</span> </i>
			              </a>
			            </li>
			            <li class="treeview" id="m_add" onclick="checkTreeNode(true);">
			              <a href="#">
			                <i ></i> <span >修改</span> </i>
			              </a>
			            </li>
			            <li class="treeview" id="m_add" onclick="removeTreeNode();">
			              <a href="#">
			                <i ></i> <span >删除</span> </i>
			              </a>
			            </li>
					</ul>
				</div>
	        </section>
	        <!-- /.sidebar -->
      	</aside>
        <!-- Main content -->
        <section class="content" style="width: 85%; margin-left: 13%;">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">组织架构维护</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--搜索信息 start-->
                    <div class="form-group has-warning">
                      <label for="inputEmail3" class="col-sm-1 control-label">姓名：</label>
                      <div class="col-sm-2">
						<input type="text" class="form-control" id="empname_search" name="employee.empname" placeholder="姓名" value="" >
					  </div>
                      <label for="inputEmail3" class="col-sm-1 control-label">编码：</label>
                      <div class="col-sm-2">
                        <input type="text" class="form-control" id="empcode_search" name="employee.empcode" placeholder="编码" value="" >
                      </div>
                      <div class="col-sm-6">
                        <button class="btn btn-info"><i class="fa fa-search"></i>&nbsp;查询</button>
                        <button class="btn btn-info"><i class="fa fa-refresh"></i>&nbsp;重置</button>
                        <button class="btn btn-warning"><i class="fa fa-plus"></i>&nbsp;添加人员</button>
                        <button class="btn btn-danger"><i class="fa  fa-remove"></i>&nbsp;删除</button>
                       </div>
                    </div><!--/搜索信息 start-->
                    <!--门店列表start-->
                    <table id="employeelist" class="table table-striped animated flipInX">
                    <tr>
                      <th>#</th>
                      <th>姓名</th>
                      <th>编码</th>
                      <th>所属部门</th>
                      <th>操作</th>
                    </tr>
                  </table><!--/门店列表-->
                  <!--分页条件-->
                  <nav>
					  <ul class="pagination" id="pagination" data-option="{'pageSize':2,'loadData':'search()'}"> </ul>
				   </nav><!--/分页条件-->
                  </div><!-- /.box-body -->
                </form><!--/ form start -->
              </div><!-- /.box -->
        </section><!-- /.content -->
	</body>
	<!-- jQuery 2.1.4 -->
    <script src="<%=cxtPath%>/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="<%=cxtPath%>/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="<%=cxtPath%>/js/common.js"></script>
    <script src="<%=cxtPath%>/plugins/layer/layer.js"></script>
    <script src="<%=cxtPath%>/js/jquery.twbsPagination.min.js"></script>
    <!--zTree-->
    <script type="text/javascript" src="../../plugins/zTree/js/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="../../plugins/zTree/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="../../plugins/zTree/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="../../plugins/zTree/js/jquery.ztree.exedit.js"></script>
	<script src="tree.js"></script>
	<script type="text/javascript" src="organizationList.js"></script>
    
</html>
