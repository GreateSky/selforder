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
	    	String fromuser = request.getParameter("fromuser");
	    %>
	</head>
	<script type="text/javascript">
		var fromuser = '<%=fromuser%>';
	</script>
	<body style="margin: 0; padding: 0; background-color: #ECF0F5; width: 100%; height: 100%;" >
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	评论管理
            <small>评论审核/回复</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 评论管理</a></li>
            <li class="active">评论审核/回复</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">评论审核/回复</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--评论维护 start-->
					<div id="commentDetaiList" class="direct-chat-messages" style="width: 60%; height: auto;">
                    	<!--评论内容 -->
                    </div><!--/.direct-chat-messages-->
                  </div><!-- /.box-body -->
                </form><!--/form end-->
              </div><!-- /.box -->
        </section><!-- /.content -->
        <!--modal 评论审核/回复-->
        <div class="modal fade" id="commentWin" commentid="" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" opttype="">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">业务类型保存</h4>
		      </div>
		      <div class="modal-body">
				<form role="form">
				   <div class="form-group has-warning" >
				      <label for="wcode">审核</label>
				      <div class="radio">
                        <label>
                          <input type="radio" name="audit" id="" value="1" checked="checked" />
                          	通过
                        </label>
                        <label>
                          <input type="radio" name="audit" id="-1" value="option2"  />
                          	不通过
                        </label>
                      </div>
				   </div>
				   <div class="form-group has-warning">
				      <label for="content">回复：</label>
				      <textarea class="form-control" rows="3" id="content" placeholder="回复"></textarea>
				   </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" onclick="saveAuditComment()">保存</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /<!--modal 评论审核/回复-->
	</body>
	<!-- jQuery 2.1.4 -->
    <script src="<%=cxtPath%>/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="<%=cxtPath%>/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="commentDetail.js"></script>
    <script src="<%=cxtPath%>/js/common.js"></script>
    <script src="<%=cxtPath%>/plugins/layer/layer.js"></script>
    <script src="<%=cxtPath%>/js/jquery.twbsPagination.min.js"></script>
</html>
