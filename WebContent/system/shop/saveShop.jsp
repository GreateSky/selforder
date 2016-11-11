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
	    <style type="text/css">
	    	.checkbox label{
	    		margin-left: 10px;
	    	}
	    	#allmap{height:600px;width:560px}
	    </style>
	    <%
	    	String opt = request.getParameter("opt");
	    	String sid = "";
	    	if(null != opt && "update".equalsIgnoreCase(opt)){
	    		sid = request.getParameter("sid");
	    	}else{
	    		opt = "";
	    	}
	    %>
	    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FC766111331538a002147e7996dc6623" ></script>
	    <script async src="http://c.cnzz.com/core.php"></script>
	    <script language="JavaScript" type="application/javascript">
	    	var opt = '<%=opt%>'
	    	var sid = '<%=sid%>';
	    </script>
	</head>
	<body style="margin: 0; padding: 0; background-color: #ECF0F5; width: 100%; height: 100%;" >
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	门店管理
            <small>门店维护</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 门店管理</a></li>
            <li class="active">门店维护</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">门店维护</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body">
                  	<!--门店维护 start-->
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">门店：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="title" placeholder="门店" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">LOGO：</label>
					  <div class="col-sm-2">
						<input type="file" id="fileid" name="fileid" class="form-control" onchange="checkFile(this)" />
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">电话：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="tel" placeholder="电话" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">描述：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="info" placeholder="描述" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">通知：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="announce" placeholder="通知" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">省：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="location_p" placeholder="省" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">市：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="location_c" placeholder="市" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">县/区：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="location_a" placeholder="区" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">地址：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="address" name="shop.address" placeholder="地址" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">经/纬度：</label>
					  <div class="col-sm-1">
						<input type="text" class="form-control" id="longitude" name="shop.longitude" placeholder="经度" readonly="readonly" value="">
					  </div>
					  
					  <div class="col-sm-1">
						<input type="text" class="form-control" id="latitude" name="shop.latitude" placeholder="维度" readonly="readonly" value="">
					  </div>
					  <div class="col-sm-2">
						<button class="btn btn-default" type="button" onclick="showMap();">定位</button>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">营业开始时间：</label>
					  <div class="col-sm-2">
						<select id="begintime" class="form-control">
							<option value="0">00:00</option>
							<option value="1">01:00</option>
							<option value="2">02:00</option>
							<option value="3">03:00</option>
							<option value="4">04:00</option>
							<option value="5">05:00</option>
							<option value="6">06:00</option>
							<option value="7">07:00</option>
							<option value="8">08:00</option>
							<option value="9">09:00</option>
							<option value="10">10:00</option>
							<option value="11">11:00</option>
							<option value="12">12:00</option>
							<option value="13">13:00</option>
							<option value="14">14:00</option>
							<option value="15">15:00</option>
							<option value="16">16:00</option>
							<option value="17">17:00</option>
							<option value="18">18:00</option>
							<option value="19">19:00</option>
							<option value="20">20:00</option>
							<option value="21">21:00</option>
							<option value="22">22:00</option>
							<option value="23">23:00</option>
						</select>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">营业结束时间：</label>
					  <div class="col-sm-2">
						<select id="endtime" class="form-control">
							<option value="0">00:00</option>
							<option value="1">01:00</option>
							<option value="2">02:00</option>
							<option value="3">03:00</option>
							<option value="4">04:00</option>
							<option value="5">05:00</option>
							<option value="6">06:00</option>
							<option value="7">07:00</option>
							<option value="8">08:00</option>
							<option value="9">09:00</option>
							<option value="10">10:00</option>
							<option value="11">11:00</option>
							<option value="12">12:00</option>
							<option value="13">13:00</option>
							<option value="14">14:00</option>
							<option value="15">15:00</option>
							<option value="16">16:00</option>
							<option value="17">17:00</option>
							<option value="18">18:00</option>
							<option value="19">19:00</option>
							<option value="20">20:00</option>
							<option value="21">21:00</option>
							<option value="22">22:00</option>
							<option value="23">23:00</option>
						</select>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">商家详细：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="thumb_url" placeholder="商家详细介绍URL地址" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">配送半径(Km)：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="delivery_radius" placeholder="配送半径(Km)" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">起送价格(元)：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="sendingprice" placeholder="起送价格" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">允许提前几天点外卖：</label>
					  <div class="col-sm-2">
						<input type="text" class="form-control" id="delivery_within_days" placeholder="允许提前几天点外卖" value="">
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">服务配置：</label>
					  <div class="col-sm-4">
						<div class="checkbox">
	                        <label>
	                          <input type="checkbox" id="enable_wifi"  />
	                          	wifi
	                        </label>
	                        <label>
	                          <input type="checkbox" id="enable_card"  />
	                          	刷卡
	                        </label>
	                        <label>
	                          <input type="checkbox" id="enable_room"  />
	                          	包厢
	                        </label>
	                        <label>
	                          <input type="checkbox" id="enable_park"  />
	                          	停车
	                        </label>
	                        <label>
	                          <input type="checkbox" id="is_rest"  />
	                          	休息中
	                        </label>
	                    </div>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">功能配置：</label>
					  <div class="col-sm-4">
						<div class="checkbox">
	                        <label>
	                          <input type="checkbox" id="is_show"  />
	                          	手机端显示
	                        </label>
	                        <label>
	                          <input type="checkbox" id="is_meal"  />
	                          	店内点餐
	                        </label>
	                        <label>
	                          <input type="checkbox" id="is_delivery"  />
	                          	支持外卖
	                        </label>
	                        <label>
	                          <input type="checkbox" id="is_reservation"  />
	                          	支持预定
	                        </label>
	                        <label>
	                          <input type="checkbox" id="is_queue"  />
	                          	支持排队
	                        </label>
	                    </div>
					  </div>
					</div>
					<div class="form-group has-warning">
					  <label for="inputEmail3" class="col-sm-2 control-label">简介：</label>
					  <div class="col-sm-2">
						<textarea rows="3"  class="form-control" id="content"></textarea>
					  </div>
					</div> <!-- /门店维护 start-->
                  </div><!-- /.box-body -->
                  <div class="box-footer">
                  	<button type="button" class="btn btn-default">取消</button>
                    <button type="button" onclick="uploadFile()" class="btn btn-info pull-right">保存</button>
                  </div><!-- /.box-footer -->
                </form><!--/form end-->
              </div><!-- /.box -->
        </section><!-- /.content -->
        <img id="shopimg" src="" width="150px" height="150px" style="position:absolute;top: 110px; left: 600px; border-radius: 8px;"></img>
        <!--modal 百度地图-->
        <div class="modal fade" id="mapWin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">地图定位</h4>
		      </div>
		      <div class="modal-body">
		      	<div class="input-group " style="width: 97%;margin: 10px">
			      	<input type="text" class="form-control" id="point_address"  placeholder="请输入地址进行搜索" value="" >
			      	<span class="input-group-btn">
	                    <button class="btn btn-info btn-flat" type="button" onclick="poindByAddress();">Go!</button>
	                </span>
			    </div>
				<div id="allmap"></div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" onclick="javascript:history.go(-1)">返回</button>
		        <button type="button" class="btn btn-primary" onclick="pointOK()">保存</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /<!--modal 百度地图-->
	</body>
	<!-- jQuery 2.1.4 -->
    <script src="<%=cxtPath%>/js/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="<%=cxtPath%>/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="<%=cxtPath%>/js/ajaxfileupload.js"></script>
    <script src="<%=cxtPath%>/js/common.js"></script>
    <script src="<%=cxtPath%>/plugins/layer/layer.js"></script>
    <script src="saveShop.js"></script>
</html>