<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	body{
		margin: 0px 10px;padding: 0px;
		font-size: 10px;
	}
	table tr{
		height:30px;
	}
	.fooder{
		text-align: center;border-bottom: 1px dashed;
	}
</style>
</head>
<body >
	<div id="printContent" style="width: 100%;">
		<div>
			<p style="text-align: center;font-size: 15px;" id="title">欢迎光临</p>
			<p>桌号：<span style="font-size: 15px;" id="tableCode">C-001</span></p>
			<p>订单时间：<span id="crtdate">2016-11-12 13:32:40</span></p>
		</div>
		<hr style="border: 1px dashed">
		<div >
			<table style="width:100%;text-align: center; ">
				<thead>
					<tr>
						<th align="left">名称</th>
						<th align="right">价格</th>
						<th align="right">数量</th>
						<th align="right">小计</th>
					</tr>
				</thead>
				<tbody id="orderDetail">
					<tr tag="appendDetailTr">
						<td style="word-wrap:break-word;" align="left">顶顶顶</td>
						<td style="word-wrap:break-word;" align="center">12</td>
						<td style="word-wrap:break-word;" align="center">2</td>
						<td style="word-wrap:break-word;" align="center">24</td>
					</tr>
					<tr tag="appendDetailTr">
						<td colspan="3" style="font-size: 15px;" align="right">合计</td>
						<td  style="font-size: 15px;" align="center">24.00</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="fooder">
			<p>谢谢惠顾</p>
			<samp id="printTime">打印时间：</samp>
		</div>
	</div>
	<script type="text/javascript">
	var oid = "${param.oid}";
	var printType = "${param.printType}";
	var date = new Date();
	var currDate = date.Format("yyyy-MM-dd hh:mm:ss");
	$("#printTime").html("打印时间："+currDate);
	if(printType == "all"){//打印全部订单信息
		printOrder();
	}else if(printType=="add"){//打印加菜信息
		printAddOrderDetail();
	}
	
	//打印全部订单信息
	function printOrder(){
		//加载订单详情
		$.ajax({
			type:"POST",
			url:"/selforder/api/order/getOrderInfo.action",
			data:{"order.id":oid},
			dataType:"json",
			success:function(res){
				$("#title").html("欢迎光临");
				$("tr[tag='appendDetailTr']").remove();
				var retCode = res.retCode;
				var message = res.message;
				var orderInfo = res.data.order;//订单信息
				var orderDetailList = res.data.orderDetailList;//订单明细列表
				if(retCode < 0){
					layer.msg(message,{icon:5});
					return;
				}
				var tablecode = orderInfo.tablecode;
				var crtdate = orderInfo.crtdate;
				$("#tableCode").html(tablecode);		
				$("#crtdate").html(formatDate(crtdate));
				var totalCost = 0;//合计费用
				if(null != orderDetailList && orderDetailList.length>0){
					for(var i=0;i<orderDetailList.length;i++){
						var detail = orderDetailList[i];
						var num = detail.num;
						var price = detail.price;
						var cost = detail.cost;
						var goods_name = detail.goods_name;
						totalCost += price*num;
						var appendTr = "";
						appendTr += '<tr tag="appendDetailTr">                                  ';
						appendTr += '	<td style="word-wrap:break-word;" align="left">'+goods_name+'</td>';
						appendTr += '	<td style="word-wrap:break-word;" align="center">'+price+'</td>  ';
						appendTr += '	<td style="word-wrap:break-word;" align="center">'+num+'</td>   ';
						appendTr += '	<td style="word-wrap:break-word;" align="center">'+(price*num).toFixed(2)+'</td>  ';
						appendTr += '</tr>                                                      ';
						$("#orderDetail").append(appendTr);
					}
					var heji = "";
					heji += '<tr tag="appendDetailTr">                                                            ';
					heji += '	<td colspan="4" style="font-size: 15px;" align="right">合计:￥'+totalCost.toFixed(2)+'</td>';
					heji += '</tr>                                                            ';
					$("#orderDetail").append(heji);
					window.print();
					setIsPrintStatus();//设置菜单打印状态
				}
			}
		});
	}

	//打印加菜信息
	function printAddOrderDetail(){
		$.ajax({
			type:"POST",
			url:"/selforder/api/order/getOrderInfo.action",
			data:{"order.id":oid,"order.is_load_no_print":1},
			dataType:"json",
			success:function(res){
				$("#title").html("加菜单");
				$("tr[tag='appendDetailTr']").remove();
				var retCode = res.retCode;
				var message = res.message;
				var orderInfo = res.data.order;//订单信息
				var orderDetailList = res.data.orderDetailList;//订单明细列表
				if(retCode < 0){
					layer.msg(message,{icon:5});
					return;
				}
				var tablecode = orderInfo.tablecode;
				var crtdate = orderInfo.crtdate;
				$("#tableCode").html(tablecode);		
				$("#crtdate").html(formatDate(crtdate));
				if(null != orderDetailList && orderDetailList.length>0){
					for(var i=0;i<orderDetailList.length;i++){
						var detail = orderDetailList[i];
						var num = detail.num;
						var price = detail.price;
						var cost = detail.cost;
						var goods_name = detail.goods_name;
						var appendTr = "";
						appendTr += '<tr tag="appendDetailTr">                                  ';
						appendTr += '	<td style="word-wrap:break-word;" align="left">'+goods_name+'</td>';
						appendTr += '	<td style="word-wrap:break-word;" align="center">'+price+'</td>  ';
						appendTr += '	<td style="word-wrap:break-word;" align="center">'+num+'</td>   ';
						appendTr += '	<td style="word-wrap:break-word;" align="center">'+(price*num).toFixed(2)+'</td>  ';
						appendTr += '</tr>                                                      ';
						$("#orderDetail").append(appendTr);
						
					}
					window.print();
					setIsPrintStatus();//设置菜单打印状态
				}
			}
		});
	}
	
	//更新订单打印状态
	function setIsPrintStatus(){
		$.post(
			"/selforder/api/order/updateOrderPrintStatus.action",
			{"order.id":oid},
			"json"
		);
	}
	</script>
</body>
</html>