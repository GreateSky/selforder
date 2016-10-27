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
			*{margin:0;padding:0;list-style-type:none;}
			a,img{border:0;}
			body{background:#f2f2f2;font:12px/180% Arial, Helvetica, sans-serif, "新宋体";}
			/* stepInfo 
				border-radius：0为正方形，0~N，由正方形向圆形转化，N越大越圆。
				padding：图形的内边距
				background：图形背景色
				text-align：文本对齐
				line-height：行高
				color：文字颜色
				position：定位
				width：宽度
				height：高度
			*/
			.stepInfo{position:relative;background:#f2f2f2;margin:20px auto 0 auto;width:500px;}
			.stepInfo li{float:left;width:40%;height:0.15em;background:#bbb;}
			.stepIco{border-radius:1em;padding:0.03em;background:#bbb;text-align:center;line-height:1.5em;color:#fff; position:absolute;width:1.4em;height:1.4em;}
			.stepIco1{top:-0.7em;left:-1%;}
			.stepIco2{top:-0.7em;left:20%;}
			.stepIco3{top:-0.7em;left:40%;}
			.stepIco4{top:-0.7em;left:60%;}
			.stepIco5{top:-0.7em;left:80%;}
			.stepText{color:#666;margin-top:0.2em;width:4em;text-align:center;margin-left:-1.4em;}
		</style>
	    <%
	    	String oid = request.getParameter("oid");
	    	String opt = request.getParameter("opt");
	    %>
	    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FC766111331538a002147e7996dc6623" ></script>
	    <script async src="http://c.cnzz.com/core.php"></script>
	    <script language="JavaScript" type="application/javascript">
	    	var oid = '<%=oid%>';
			var opt = '<%=opt%>';
	    </script>
	</head>
	<body style="margin: 0; padding: 0; background-color: #ECF0F5; width: 100%; height: 100%;" >
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	订单管理
            <small>订单详情</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 订单管理</a></li>
            <li class="active">订单详情</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">订单详情</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal">
                  <div class="box-body ">
                  	<!--时间轴 box-->
                  	<div class="box box-warning">
                  		<div class="box-header with-border">
                  			<h4 class="box-title">
                  				<b id="title_ordersn" style="float:left;margin: 2px;">订单号：</b>
                  				<label class="label label-info" id="payInfo" style="display: none;float:left;margin: 2px;"></label>
								<label class="label label-info" id="checkPayInfo" style="display: none;float:left;margin: 2px;" onclick="javascript:window.open('https://pay.weixin.qq.com/index.php/core/home/login')">去商户平台查看</label>
                  			</h4>
                  		</div>
                  		<div class="box-body" style="height: 80px;">
                  			<div id="stepinfo" class="stepInfo">
								<ul>
									<li></li>
									<li></li>
								</ul>
								<div statusnum="0" class="stepIco stepIco1" id="create">1
									<div class="stepText" id="createText">用户下单</div>
								</div>
								<div statusnum="1" class="stepIco stepIco2" id="check">2
									<div class="stepText" id="checkText">商户确认</div>
								</div>
								<div statusnum="2" class="stepIco stepIco3" id="pay">3
									<div class="stepText" id="payText">客户付款</div>
								</div>
								<div statusnum="3" class="stepIco stepIco4" id="tran">4
									<div class="stepText" id="tranText">商家配送</div>
								</div>
								<div statusnum="4" class="stepIco stepIco5" id="received">5
									<div class="stepText" id="receivedText">交易完成</div>
								</div>
							</div>
                  		</div>
                  	</div><!--/时间轴 box-->
                  	<!--订单明细 box-->
                  	<div class="box box-danger">
                  		<div class="box-header with-border">
                  			<h4 class="box-title">订单明细</h4>
                  		</div>
                  		<div class="box-body">
                  			<div class="row">
                  				<div class="col-md-6" >
                  					<table class="table table-striped animated flipInX" id="orderDetailList">
                  						<tr>
					                      <th>#</th>
					                      <th>名称</th>
					                      <th>价格</th>
					                      <th>数量</th>
					                      <th>操作</th>
					                    </tr>
                  					</table>
                  					<div class="row">
                  						<div class="col-md-12">
                  							<button tag="actionBtn_add" type="button" class="btn btn-warning pull-right" onclick="showGoodsWin()">新增</button>
                  						</div>
                  					</div>
                  				</div>
                  				<div class="col-md-6" style="border-left: 1px dashed #C0C0C0">
                  					<form class="form-horizontal">
                  						<div class="form-group has-warning">
										  <label for="inputEmail3" class="col-sm-2 control-label">订单号：</label>
										  <div class="col-sm-3">
											<input type="text" class="form-control" id="ordersn" placeholder="订单号" value="" readonly="readonly" >
										  </div>
										</div>
										<div class="form-group has-warning">
										  <label for="inputEmail3" class="col-sm-2 control-label">订单类型：</label>
										  <div class="col-sm-3">
										  	<select class="form-control" id="dining_mode" disabled="disabled">
										  		<option value="1">到店</option>
										  		<option value="2">外卖</option>
										  		<option value="2">预定</option>
										  	</select>
										  </div>
										</div>
										<div class="form-group has-warning">
										  <label for="inputEmail3" class="col-sm-2 control-label">创建时间：</label>
										  <div class="col-sm-3">
											<input type="text" class="form-control" id="crtdate" placeholder="创建时间" value="" readonly="readonly" >
										  </div>
										</div>
										<div class="form-group has-warning" >
										  <label for="inputEmail3" class="col-sm-2 control-label">配送人：</label>
										  <div class="col-sm-3">
										  	<input type="hidden" id="transferid"></input>
											<input type="text" class="form-control" id="transfername" placeholder="配送人" value="" readonly="readonly" >
										  </div>
										  <div class="col-sm-1">
											<button tag="actionBtn_select" type="button" class="btn btn-warning" onclick="showTransferWin()">选择</button>
										  </div>
										</div>
										<div class="form-group has-warning">
										  <label for="inputEmail3" class="col-sm-1 control-label" ></label>
										  <label for="inputEmail3" class="col-sm-5 control-label" style="border-top: 1px dashed #C0C0C0" ></label>
										</div>
										<div class="form-group has-warning" >
										  <label for="inputEmail3" class="col-sm-2 control-label">客户名称：</label>
										  <div class="col-sm-3">
											<input type="text" class="form-control" id="username" placeholder="客户名称" value="" >
										  </div>
										</div>
										<div class="form-group has-warning" >
										  <label for="inputEmail3" class="col-sm-2 control-label">客户电话：</label>
										  <div class="col-sm-3">
											<input type="text" class="form-control" id="tel" placeholder="客户电话" value="" >
										  </div>
										</div>
										<div class="form-group has-warning" >
										  <label for="inputEmail3" class="col-sm-2 control-label">收货地址：</label>
										  <div class="col-sm-3">
											<input type="text" class="form-control" id="address" placeholder="收货地址" value="" >
										  </div>
										</div>
										<div class="form-group has-warning" >
										  <label for="inputEmail3" class="col-sm-2 control-label">口味：</label>
										  <div class="col-sm-3">
											<input type="text" class="form-control" id="taste" placeholder="微辣" value="" >
										  </div>
										</div>
										<div class="form-group has-warning" >
										  <label for="inputEmail3" class="col-sm-2 control-label">客户留言：</label>
										  <div class="col-sm-3">
											<input type="text" class="form-control" id="remark" placeholder="客户留言" value="微辣"  >
										  </div>
										</div>
										<div class="form-group has-warning">
										  <label for="inputEmail3" class="col-sm-1 control-label" ></label>
										  <label for="inputEmail3" class="col-sm-5 control-label" style="border-top: 1px dashed #C0C0C0" ></label>
										</div>
										<div class="form-group has-warning" >
										  <label for="inputEmail3" class="col-sm-2 control-label">应付金额：</label>
										  <div class="col-sm-3">
											<input type="number" class="form-control" id="totalprice" placeholder="￥0.00元" value=""  >
										  </div>
										</div>
										<div class="form-group has-warning" >
										  <label for="inputEmail3" class="col-sm-2 control-label">实付金额：</label>
										  <div class="col-sm-3">
											<input type="number" class="form-control" id="realprice" placeholder="￥0.00元" value="" >
										  </div>
										</div>
										<div class="form-group has-warning" >
										  <label for="inputEmail3" class="col-sm-2 control-label">支付方式：</label>
										  <div class="col-sm-3">
											<div class="radio">
						                        <label>
						                          <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked />
						                          	支付宝
						                        </label>
						                        <label>
						                          <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1"  />
						                          	微信
						                        </label>
						                        <label>
						                          <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1"  />
						                          	现金
						                        </label>
						                      </div>
										  </div>
										</div>
										<div class="form-group has-warning">
										  <label for="inputEmail3" class="col-sm-1 control-label" ></label>
										  <label for="inputEmail3" class="col-sm-5 control-label" style="border-top: 1px dashed #C0C0C0" ></label>
										</div>
										<div class="form-group has-warning">
										  <label for="inputEmail3" class="col-sm-1 control-label" ></label>
										  <div class="col-sm-4">
										  	<button tag="actionBtn_save" type="button" class="btn  btn-info" onclick="saveOrderDetail()">保存</button>
											<button tag="actionBtn_balance" type="button" class="btn  btn-danger" onclick="balanceOrder()">结算</button>
										  </div>
										</div>
                  					</form>
                  				</div>
                  			</div>
                  		</div>
                  	</div><!--/订单明细 box-->
                  </div><!-- /.box-body -->
                </form><!--/form end-->
              </div><!-- /.box -->
        </section><!-- /.content -->
        <!--modal 食谱-->
        <div class="modal fade" id="goodsWin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">食谱列表</h4>
		      </div>
		      <div class="modal-body">
		      	<div class="input-group " style="width: 97%;margin: 10px">
			      	<input type="text" class="form-control" id="goods_name"  placeholder="请输入菜谱名称搜索" value="" >
			      	<span class="input-group-btn">
	                    <button class="btn btn-info btn-flat" type="button" onclick="searchGoodsList();">Go!</button>
	                </span>
			    </div>
				<table class="table table-striped animated flipInX" id="goodsList">
			          <tr>
	                     <th>#</th>
	                     <th>名称</th>
	                     <th>价格</th>
	                     <th>操作</th>
			          </tr>
			   </table>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /<!--modal 食谱-->
		<!--modal 配送人员选择-->
        <div class="modal fade" id="transferWin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">餐桌列表</h4>
		      </div>
		      <div class="modal-body">
		      	<div class="input-group " style="width: 97%;margin: 10px">
			      	<input type="text" class="form-control" id="transferKeyword"  placeholder="名称/电话" value="" >
			      	<span class="input-group-btn">
	                    <button class="btn btn-info btn-flat" type="button" onclick="searchTransferList();">Go!</button>
	                </span>
			    </div>
				<table class="table table-striped animated flipInX" id="transferList">
			          <tr>
	                     <th>#</th>
	                     <th>编号</th>
	                     <th>姓名</th>
	                     <th>电话</th>
	                     <th>操作</th>
			          </tr>
			   </table>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /<!--modal 配送人员选择-->
	</body>
	<!-- jQuery 2.1.4 -->
    <script src="<%=cxtPath%>/js/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="<%=cxtPath%>/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="<%=cxtPath%>/js/common.js"></script>
    <script src="<%=cxtPath%>/plugins/layer/layer.js"></script>
    <script src="orderDetail.js"></script>
    <script type="text/javascript">
	</script>
</html>