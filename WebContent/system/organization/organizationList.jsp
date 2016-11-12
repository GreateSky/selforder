<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<!--zTree-->
		<link rel="stylesheet" href="../../plugins/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	    <style type="text/css">
			div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #EBECEE;text-align: left; border: 1px solid #ECF0F5; }
			div#rMenu ul li span{
				color: black;
			}
			div#rMenu ul li {
				border-bottom: 1px solid #C9CCCF;
				height: 35px;
			}
			.main-sidebar, .left-side{
				position: absolute;
			    top: 0;
			    left: 0;
			    padding-top: 50px;
			    min-height: 100%;
			    width: 13%;
			    z-index: 810;
			    -webkit-transition: -webkit-transform .3s ease-in-out,width .3s ease-in-out;
			    -moz-transition: -moz-transform .3s ease-in-out,width .3s ease-in-out;
			    -o-transition: -o-transform .3s ease-in-out,width .3s ease-in-out;
			    transition: transform .3s 
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
	        <section class="sidebar" style="margin-top: 8px;margin-left: 4px;width: 100%;" >
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
			            <li class="treeview" id="m_update" onclick="showTreeNodeInfo();">
			              <a href="#">
			                <i ></i> <span >修改</span> </i>
			              </a>
			            </li>
			            <li class="treeview" id="m_roles" onclick="showRoles();">
			              <a href="#">
			                <i ></i> <span >查看权限</span> </i>
			              </a>
			            </li>
			            <li class="treeview" id="m_remove" onclick="removeTreeNode();">
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
                        <button type="button" onclick="search()" class="btn btn-info"><i class="fa fa-search"></i>&nbsp;查询</button>
                        <button class="btn btn-info"><i class="fa fa-refresh"></i>&nbsp;重置</button>
                        <button type="button" onclick="showEmployeeWin()" class="btn btn-warning"><i class="fa fa-plus"></i>&nbsp;添加人员</button>
                       </div>
                    </div><!--/搜索信息 start-->
                    <!--门店列表start-->
                    <table id="employeelist" class="table table-striped animated flipInX">
                    <tr>
                      <th>#</th>
                      <th>姓名</th>
                      <th>编码</th>
                      <th>所属门店</th>
                      <th>所属部门</th>
                      <th>操作</th>
                    </tr>
                  </table><!--/门店列表-->
                  <!--分页条件-->
                  <nav>
					  <ul class="pagination" id="pagination" data-option="{'pageSize':20,'loadData':'search()'}"> </ul>
				   </nav><!--/分页条件-->
                  </div><!-- /.box-body -->
                </form><!--/ form start -->
              </div><!-- /.box -->
        </section><!-- /.content -->
        
        <!--modal 添加员工-->
        <div class="modal fade" id="employeeListWin" openType="" rid="" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 	<div class="modal-dialog">
			    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">员工选择</h4>
				      </div>
				      <div class="modal-body">
					    <div class="input-group " style="width: 97%;margin: 10px">
					      	<input type="text" class="form-control" id="employuee_keyword"  placeholder="请输入名称/编码进行搜索" value="" >
					      	<span class="input-group-btn">
			                    <button class="btn btn-info btn-flat" type="button" onclick="loadEmployeeList('');">Go!</button>
			                </span>
					    </div>
						<table id="noEmpOrglist" class="table table-striped ">
		                    <tr>
		                      <th></th>
		                      <th>#</th>
		                      <th>名称</th>
		                      <th>编码</th>
		                    </tr>
		                </table><!--/门店列表-->
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
				        <button type="button" class="btn btn-primary" onclick="saveEmpOrg()">保存</button>
				      </div>
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
		</div><!-- /<!--modal 添加员工-->
		
		<!--modal 添加组织架构-->
        <div class="modal fade" id="editTreeWin" openType="" oid="" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 	<div class="modal-dialog">
			    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">编辑部门</h4>
				      </div>
				      <div class="modal-body">
					    <div class="form-group has-warning" >
					      <label for="wcode">名称</label>
					      <input type="text" class="form-control" id="edit_oname"  placeholder="名称" value="" >
					      <label for="wcode">排序(越大越靠前)</label>
					      <input type="number" class="form-control" id="edit_seq" placeholder="排序(越大越靠前)" value="" >
						</div>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
				        <button type="button" class="btn btn-primary" onclick="saveEditTree()">保存</button>
				      </div>
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
		</div><!-- /<!--modal 添加组织架构-->
		
		<!--modal 组织架构权限管理-->
        <div class="modal fade" id="orgRoleWin" openType="" oid="" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 	<div class="modal-dialog">
			    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">权限选择</h4>
				      </div>
				      <div class="modal-body has-warning">
				      	<div class="form-group">
	                      	<button class="btn btn-warning" onclick="showAddRole()"><i class="fa fa-plus"></i>&nbsp;添加权限</button>
	                    </div>
						<table id="orgRoleList" class="table table-striped ">
		                    <tr>
		                      <th>#</th>
		                      <th>名称</th>
		                      <th>编码</th>
		                       <th>操作</th>
		                    </tr>
		                </table><!--/门店列表-->
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
				        <button type="button" class="btn btn-primary" onclick="saveOrgRole()">保存</button>
				      </div>
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
		</div><!-- /<!--modal 组织架构权限管理-->
		
		<!--modal 组织架构未关联的权限-->
        <div class="modal fade" id="orgNoRoleWin" openType="" oid="" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 	<div class="modal-dialog">
			    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">权限选择</h4>
				      </div>
				      <div class="modal-body">
					    <div class="input-group " style="width: 97%;margin: 10px">
					      	<input type="text" class="form-control" id="role_keyword"  placeholder="请输入名称/编码进行搜索" value="" >
					      	<span class="input-group-btn">
			                    <button class="btn btn-info btn-flat" type="button" onclick="loadOrgNoRoleList('');">Go!</button>
			                </span>
					    </div>
						<table id="orgNoRoleList" class="table table-striped ">
		                    <tr>
		                      <th></th>
		                      <th>#</th>
		                      <th>名称</th>
		                      <th>编码</th>
		                    </tr>
		                </table><!--/门店列表-->
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
				        <button type="button" class="btn btn-primary" onclick="saveOrgNoRole()">保存</button>
				      </div>
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
		</div><!-- /<!--modal 组织架构未关联的权限-->
	</body>
    <script src="<%=cxtPath%>/js/jquery.twbsPagination.min.js"></script>
    <!--zTree-->
    <script type="text/javascript" src="../../plugins/zTree/js/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="organizationList.js"></script>
	<script src="tree.js"></script>
    <script src="orgRole.js"></script>
</html>
