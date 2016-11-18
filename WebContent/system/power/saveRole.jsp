<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>     
<!DOCTYPE html >
<html>
	<head>
		<meta charset="UTF-8">
	    <%
	    	String opt = request.getParameter("opt");
	    	String rid = "";
	    	if(null != opt && "update".equalsIgnoreCase(opt)){
	    		rid = request.getParameter("rid");
	    	}else{
	    		opt = "";
	    	}
	    %>
	    <script language="JavaScript" type="application/javascript">
	    	var opt = '<%=opt%>'
	    	var rid = '<%=rid%>';
	    </script>
	</head>
	<body style="margin: 0; padding: 0; background-color: #ECF0F5; width: 100%; height: 100%;" >
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	权限管理
            <small>角色维护</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 角色维护</a></li>
            <li class="active">角色维护</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">角色基本信息</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal" id="saveBusinessForm">
                  <div class="box-body">
                  	<!--门店维护 start-->
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label" ><font color="red">*</font>&nbsp;名称：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="rname" name="role.rname" placeholder="名称" value="" >
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label"><font color="red">*</font>&nbsp;编码：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="rcode" name="role.rcode" placeholder="编码" value="" onchange="checkCode()">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">说明：</label>
					  <div class="col-sm-2">
						<textarea class="form-control" rows="4" name="role.remark" id="remark"></textarea>
					  </div>
					</div>
					<!--关联资源列表 -->
					<div class="box box-warning">
						<div class="box-header with-border">
		                  <h3 class="box-title">已关联的资源</h3>
		                  <button type="button" onclick="loadRresourceList()" class="btn btn-danger" style="margin-left: 5px">关联资源</button>
		                </div><!-- /.box-header -->
		                <div class="box box-body">
		                	<table id="resourceRefList" class="table table-striped ">
			                    <tr>
			                      <th>名称</th>
			                      <th>URL</th>
			                      <th>操作</th>
			                    </tr>
			                </table><!--/门店列表-->
		                </div>
					</div><!--/关联资源列表 -->
					<!-- /门店维护 start-->
                  </div><!-- /.box-body -->
                  <div class="box-footer">
                  	<button type="button" class="btn btn-default" onclick="javascript:history.go(-1)">返回</button>
                    <button type="button" onclick="saveRole()" class="btn btn-info pull-right">保存</button>
                  </div><!-- /.box-footer -->
                </form><!--/form end-->
              </div><!-- /.box -->
        </section><!-- /.content -->
        
        <!--modal 资源列表-->
        <div class="modal fade" id="resourceWin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" opttype="">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">资源选择</h4>
		      </div>
		      <div class="modal-body">
			    <div class="row" style="margin-bottom: 10px">
			      <div class="col-xs-12">
			      	  <div class="input-group input-group-sm">
				      	<input type="text" class="form-control" id="keyword"  placeholder="名称/URL" value="" >
				      	<span class="input-group-btn">
	                      <button class="btn btn-info btn-flat" type="button" onclick="loadResourceList('init',null)">Go!</button>
	                    </span>
				      </div>
			      </div>
			    </div>
				<table id="resourceList" class="table table-striped ">
                    <tr>
                      <th></th>
                      <th>#</th>
                      <th>名称</th>
                      <th>URL</th>
                    </tr>
                </table><!--/门店列表-->
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" onclick="javascript:history.go(-1)">返回</button>
		        <button type="button" class="btn btn-primary" onclick="saveSelectResource()">保存</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /<!--modal 资源列表-->
	</body>
    <script src="<%=cxtPath%>/js/jquery.twbsPagination.min.js"></script>
    <script src="saveRole.js"></script>
</html>