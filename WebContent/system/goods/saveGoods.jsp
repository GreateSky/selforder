<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>     
<!DOCTYPE html >
<html>
	<head>
		<meta charset="UTF-8">
		<!-- Tell the browser to be responsive to screen width -->
	    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	    <!-- Bootstrap 3.3.4 -->
	    <link href="<%=cxtPath%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <!-- FontAwesome 4.3.0 -->
	    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	    <!-- Theme style -->
	    <link href="<%=cxtPath%>/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
	    <!-- AdminLTE Skins. Choose a skin from the css/skins
	         folder instead of downloading all of them to reduce the load. -->
	    <link href="<%=cxtPath%>/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
	    <%
	    	String opt = request.getParameter("opt");//操作类型
	    	String id = request.getParameter("id");//食谱ID
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
            	食谱管理
            <small>食谱维护</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 食谱管理</a></li>
            <li class="active">食谱维护</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">食谱维护</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--食谱维护 start-->
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">名称:</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="title" placeholder="名称" value="" onblur="checkGoodsName()">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">图片:</label>
					  <div class="col-sm-2">
						<input type="file" id="fileid" name="fileid" class="form-control" />
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">所属食谱分类:</label>
					  <div class="col-sm-2">
						<select class="form-control" id="goodsCategoryCommbox"></select>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">市场价格(￥):</label>
					  <div class="col-sm-2">
						<input type="number" class="form-control" id=marketprice placeholder="市场价格(￥)" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">成本价格(￥):</label>
					  <div class="col-sm-2">
						<input type="number" class="form-control" id="productprice" placeholder="成本价格(￥)" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">计量单位:</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="unitname" placeholder="计量单位" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">状态:</label>
					  <div class="col-sm-2">
						<select class="form-control" id="status">
							<option value="0">上架</option>
							<option value="1">下架</option>
							<option value="2">暂售</option>
						</select>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">是否推荐:</label>
					  <div class="col-sm-4">
						<div class="radio">
	                        <label>
	                          <input type="radio" name="recommend"  checked="checked" value="1"/>
	                          	是
	                        </label>
	                        <label>
	                          <input type="radio" name="recommend" value="0"  />
	                          	否
	                        </label>
	                    </div>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">是否特殊:</label>
					  <div class="col-sm-4">
						<div class="radio">
	                        <label>
	                          <input type="radio" name="isspecial"  checked="checked" value="1"/>
	                          	是
	                        </label>
	                        <label>
	                          <input type="radio" name="isspecial"  value="0" />
	                          	否
	                        </label>
	                    </div>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">口味:</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="taste" placeholder="口味" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-1 control-label">排序(越大越靠前):</label>
					  <div class="col-sm-2">
						<input type="number" class="form-control" id="displayorder" placeholder="排序(越大越靠前)" value="0">
					  </div>
					</div>
					<div class="form-group has-warning" >
					  <label for="inputEmail3" class="col-sm-1 control-label">描述:</label>
					  <div class="col-sm-2">
						<textarea rows="5" class="form-control" id="description"></textarea>
					  </div>
					</div><!-- /食谱维护 start-->
                  </div><!-- /.box-body -->
                  <div class="box-footer">
                  	<button type="submit" class="btn btn-default">取消</button>
                    <button type="button" class="btn btn-info pull-right" onclick="uploadFile()">保存</button>
                  </div><!-- /.box-footer -->
                </form><!--/form end-->
              </div><!-- /.box -->
        </section><!-- /.content -->
        <img id="goodsImg" src="" width="150px" height="150px" style="position:absolute;top: 110px; left: 600px; border-radius: 8px;"></img>
	</body>
	<!-- jQuery 2.1.4 -->
    <script src="<%=cxtPath%>/js/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="<%=cxtPath%>/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="<%=cxtPath%>/js/ajaxfileupload.js"></script>
    <script src="<%=cxtPath%>/js/common.js"></script>
    <script src="<%=cxtPath%>/plugins/layer/layer.js"></script>
    <script src="saveGoods.js"></script>
</html>