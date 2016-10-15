var orderStatus = 0;//订单状态
var tableid = "";//餐桌ID
var dining_mode=1;
$(function() {
	changeDivStyle(orderStatus);
	$("#dining_mode").val(dining_mode);
	if(opt == "update"){
		loadOrder();
	}
	
});

/**
 * 加载订单详情
 */
function loadOrder(){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	if(!checkValueWithInfo(oid,"获取订单详情参数异常,请刷新后重试!")){
		return;
	}
	//加载订单详情
	$.ajax({
		type:"POST",
		url:"/selforder/api/order/getOrderInfo.action",
		data:{"order.id":oid},
		dataType:"json",
		success:function(res){
			layer.close(load);
			$("tr[tag='appendDetailTr']").remove();
			var retCode = res.retCode;
			var message = res.message;
			var orderInfo = res.data.order;//订单信息
			var orderDetailList = res.data.orderDetailList;//订单明细列表
			if(retCode < 0){
				layer.msg(message,{icon:5});
				return;
			}
			//初始化订单信息
			var ordersn = orderInfo.ordersn;
			var status = orderInfo.status;
			orderStatus = status;
			var dining_mode = orderInfo.dining_mode;
			var crtdate = orderInfo.crtdate;
			var username = orderInfo.username;
			var tel = orderInfo.tel;
			var taste = orderInfo.taste;
			var remark = orderInfo.remark;
			var tablecode = orderInfo.tablecode;
			tableid = orderInfo.tableid;
			var totalprice = parseFloat(orderInfo.totalprice).toFixed(2);
			var realprice = parseFloat(orderInfo.realprice).toFixed(2);
			var dining_modeStr = "";
			if(dining_mode == 1){
				dining_modeStr = "到店";
			}else if(dining_mode == 2){
				dining_modeStr = "外卖";
			}else if(dining_mode == 3){
				dining_modeStr = "预定";
			}
			$("#title_ordersn").html("订单号："+ordersn);
			$("#ordersn").val(ordersn);
			$("#dining_mode").val(dining_mode);
			$("#crtdate").val(formatDate(crtdate));
			$("#taste").val(taste);
			$("#remark").val(remark);
			$("#totalprice").val(totalprice);
			$("#realprice").val(realprice);
			$("#tablecode").val(tablecode);
			$("#tableid").val(tableid);
			$("#username").val(username);
			$("#tel").val(tel);
			//已交易完成的订单取消操作功能
			if(status == 4){
				$("button[tag *='actionBtn']").attr("disabled","disabled");
				$("input").attr("readonly","readonly");
			}
			changeDivStyle(status);//根据状态值显示
			//订单非0和-1状态禁用确认订单按钮
			if(status !=0 && status != -1){
				$("#affirmBtn").attr("disabled","disabled");
			}
			var totalCost = 0;//合计费用
			//初始化订单明细
			if(null != orderDetailList && orderDetailList.length>0){
				for(var i=0;i<orderDetailList.length;i++){
					var detail = orderDetailList[i];
					var did = detail.did;
					var oid = detail.oid;
					var goods_id = detail.goods_id;
					var num = detail.num;
					var price = detail.price;
					var cost = detail.cost;
					var goods_name = detail.goods_name;
					totalCost += price*num;
					var tr = "";
					tr += '<tr tag="appendDetailTr" class="animated flipInX" did="'+did+'" optType="">                                                             ';
					tr += '	<td>'+(i+1)+'</td>                                                      ';
					tr += '	<td>'+goods_name+'</td>                                               ';
					tr += '	<td>'+price+'</td>                                              ';
					tr += '	<td><input tag="goodsnum" type="number" min="1" max="10" tag="num" value="'+num+'" onchange="editCost(this)"></input></td>';
					if(orderStatus != 4){
						tr += '	<td>                                                            ';
						tr += '		<button type="button" class="btn btn-warning" onclick="delGoods(\''+did+'\',\''+oid+'\')">取消</button>   ';
						tr += '	</td>                                                           ';
					}
					tr += '</tr>                                                            ';
					$("#orderDetailList").append(tr);
				}
				//组装合计行
				var total = "";
				total += '<tr tag="appendDetailTr">                                                                 ';
				total += '	<td colspan="4" align="right"><h4>合计：</h4></td>                 ';
				total += '	<td><h4><label id="totalCost" class="label label-info">￥'+totalCost+'&nbsp;元</label></h4></td>';
				total += '</tr>                                                                ';
				$("#orderDetailList").append(total);
			}
			
		}
	});
}

/**
 * 根据订单状态值显示不同的状态
 * @param status
 */
function changeDivStyle(status){
	$("#stepinfo").children("div").each(function(){
		var statusnum = $(this).attr("statusnum");
		statusnum = parseInt(statusnum);
		if(statusnum <= status){
			$(this).css('background', '#DD0000');
			$(this).children("div").css('color', '#DD0000');
		}
	});
}

/**
 * 根据便跟后的数据重新计算合计费用
 */
function editCost(e){
	var totalCost = 0;//合计费用
	//遍历input[tag='goodsnum'] 的输入框
	$("input[tag='goodsnum']").each(function(){
		var value = $(this).val();
		var price = $(this).parent().prev("td").html();
		totalCost += price*value;
	});
	$("#totalCost").html("￥"+totalCost+"&nbsp元");
	//将tr行标记为已修改状态
	var currOptType = $(e).parent().parent().attr("optType");//获取当前修改的tr的optType类型
	if("add" != currOptType){
		//如果是新增的tr不是add标识则添加修改标识    add标识的tr不添加修改标识（add标识的只修改数量值，具体数据会添加至add数据中）
		$(e).parent().parent().attr("optType","update");
	}
}

/**
 * 删除子菜单
 * @param did 子菜单ID
 * @param oid 订单ID
 */
function delGoods(did,oid){
	layer.confirm("确定要删除该条数据吗？",
			{btn:["确定","取消"]},
			function(){
				layer.closeAll();
				$.ajax({
					type:"POSt",
					url:"/selforder/api/order/updateOrderDetail.action",
					data:{"orderDetail.deleted":1,"orderDetail.did":did,"orderDetail.oid":oid},
					dataType:"json",
					success:function(res){
						layer.closeAll();
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0 ){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						loadOrder();
					}
				});
			},
			function(){
				layer.closeAll();
			});
}

/***
 * 保存订单明细
 */
function saveOrderDetail(){
	if(opt == "update"){
		updateOrder();
	}else {
		addOrder();
	}
}

/**
 * 更新订单
 */
function updateOrder(){
	//获取订单基本信息
	var tableid = $("#tableid").val();
	var username = $("#username").val();
	var tel = $("#tel").val();
	var taste = $("#taste").val();
	var remark = $("#remark").val();
	var dining_mode = $("#dining_mode").val();
	var param = {};
	param["order.id"] = oid;
	param["order.tableid"] = tableid;
	param["order.username"] = username;
	param["order.tel"] = tel;
	param["order.taste"] = taste;
	param["order.remark"] = remark;
	param["order.dining_mode"] = dining_mode;
	//获取修改的明细集合
	$("tr[optType='update']").each(function(i){
		var did = $(this).attr("did");
		var num = $(this).children().children("input").val();
		param["updateDetailList["+i+"].did"] = did;
		param["updateDetailList["+i+"].num"] = num;
		param["updateDetailList["+i+"].oid"] = oid;
	});
	//获取新增的明细集合
	$("tr[optType='add']").each(function(i){
		var did = $(this).attr("did");
		var goods_id = $(this).attr("goods_id");
		var num = $(this).children().children("input").val();
		var price = $(this).children("td[tag='price']").html();
		var cost = price*num;
		param["addDetailList["+i+"].did"] = did;
		param["addDetailList["+i+"].oid"] = oid;
		param["addDetailList["+i+"].price"] = price;
		param["addDetailList["+i+"].num"] = num;
		param["addDetailList["+i+"].goods_id"] = goods_id;
		param["addDetailList["+i+"].cost"] = cost;
	});
	//保持新增和修改的订单明细
	$.ajax({
		type:"POSt",
		url:"/selforder/api/order/updateOrder.action",
		data:param,
		dataType:"json",
		success:function(res){
			layer.closeAll();
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				layer.msg(message,{icon:6});
			}
			window.location.href = 'orderList.jsp';
		}
	});
}

/**
 * 新增订单
 */
function addOrder(){
	//获取订单基本信息
	var tableid = $("#tableid").val();
	var username = $("#username").val();
	var tel = $("#tel").val();
	var taste = $("#taste").val();
	var remark = $("#remark").val();
	var dining_mode = $("#dining_mode").val();
	var param = {};
	param["order.tableid"] = tableid;
	param["order.username"] = username;
	param["order.tel"] = tel;
	param["order.taste"] = taste;
	param["order.remark"] = remark;
	param["order.dining_mode"] = dining_mode;
	//获取新增的明细集合
	$("tr[optType='add']").each(function(i){
		var goods_id = $(this).attr("goods_id");
		var num = $(this).children().children("input").val();
		var price = $(this).children("td[tag='price']").html();
		var cost = price*num;
		param["addDetailList["+i+"].price"] = price;
		param["addDetailList["+i+"].num"] = num;
		param["addDetailList["+i+"].goods_id"] = goods_id;
		param["addDetailList["+i+"].cost"] = cost;
	});
	//保持新增和修改的订单明细
	$.ajax({
		type:"POSt",
		url:"/selforder/api/order/insertOrder.action",
		data:param,
		dataType:"json",
		success:function(res){
			layer.closeAll();
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				layer.msg(message,{icon:6});
			}
			window.location.href = 'orderList.jsp';
		}
	});
}

//****************************************************食谱选择操作start*************************************
/**
 * 显示菜谱列表
 */
function showGoodsWin(param){
	$("tr[tag='goodsTrAppend']").remove();
	if(param == null ){
		param = {};
	}
	param["goods.oid"] = oid;
	//加载食谱列表
	$.ajax({
		type:"POST",
		url:"/selforder/api/goods/getGoodsListIgnoreOrderId.action",
		data:param,
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
				return;
			}
			var goodsList = res.data;
			if(goodsList.length>0){
				for(var i=0;i<goodsList.length;i++){
					var row = goodsList[i];
					var id = row.id;
					var title = row.title;
					var marketprice = row.marketprice;
					var tr = "";
					tr += '<tr tag="goodsTrAppend" id="'+id+'" class="animated flipInX">       ';
					tr += '	<td>'+(i+1)+'</td>';
					tr += '	<td>'+title+'</td>';
					tr += '	<td>￥'+marketprice+'</td>';
					tr += '	<td><button type="button" class="btn btn-warning" onclick="addGoods(\''+id+'\',\''+title+'\',\''+marketprice+'\',this)">添加</button></td>';
					tr += '</tr>      ';
					$("#goodsList").append(tr);
				}
			}
		}
	});
	$("#goodsWin").modal("show");
}

/**
 * 搜索食谱列表
 */
function searchGoodsList(){
	var goods_name = $("#goods_name").val();
	var param = {};
	param["goods.title"] = goods_name;
	showGoodsWin(param);
	
}

/**
 * 将新选择的食谱添加至订单明细中
 * @param id  食谱id
 * @param title 食谱名称
 * @param price 价格
 */
function addGoods(id,title,price,e){
	$("tr[tag='appendDetailTr']:last").remove();//删除合计行
	var index = $("tr[tag='appendDetailTr']").length;//获取已有订单行数记录数
	//拼接订单行
	var tr = "";
	tr += '<tr tag="appendDetailTr" class="animated flipInX" did="" optType="add" goods_id="'+id+'">                                                             ';
	tr += '	<td>'+(index+1)+'</td>                                                      ';
	tr += '	<td>'+title+'</td>                                               ';
	tr += '	<td tag="price">'+price+'</td>                                              ';
	tr += '	<td><input tag="goodsnum" type="number" min="1" max="10" tag="num" value="'+1+'" onchange="editCost(this)"></input></td>';
	tr += '	<td>                                                            ';
	tr += '		<button type="button" class="btn btn-warning" onclick="delGoods()">取消</button>   ';
	tr += '	</td>                                                           ';
	tr += '</tr>                                                            ';
	$("#orderDetailList").append(tr);
	//计算合计费用
	var totalCost = 0;//合计费用
	$("input[tag='goodsnum']").each(function(){//遍历input[tag='goodsnum'] 的输入框
		var value = $(this).val();
		var price = $(this).parent().prev("td").html();
		totalCost += price*value;
	}); 
	//组装合计行
	var total = "";
	total += '<tr tag="appendDetailTr">                                                                 ';
	total += '	<td colspan="4" align="right"><h4>合计：</h4></td>                 ';
	total += '	<td><h4><label id="totalCost" class="label label-info">￥'+totalCost+'&nbsp;元</label></h4></td>';
	total += '</tr>                                                                ';
	$("#orderDetailList").append(total);
	$(e).parent().parent().remove();//清除改行菜谱
}

/**
 * 结算订单
 */
function balanceOrder(){
	var totalprice = $("#totalprice").val();
	var realprice = $("#realprice").val();
	if(!checkValueWithInfo(totalprice,"应付金额不能为空！")) return;
	if(!checkValueWithInfo(realprice,"实付金额不能为空！")) return;
	layer.confirm("本单应付金额为：【"+totalprice+"】,实付金额为【"+realprice+"】,确定已结算成功吗？",
			{btn:["确定","取消"]},
			function(){
				layer.closeAll();
				$.ajax({
					type:"POST",
					url:"/selforder/api/order/updateOrderStatus.action",
					data:{"order.status":1,"order.id":oid},
					data:{"order.id":oid,"order.realprice":realprice,"order.status":4},
					dataType:"json",
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0){
							layer.msg(message,{icon:5});
						}else{
							//更新餐桌状态为空闲
							$.ajax({
								type:"POST",
								url:"/selforder/api/table/updateTable.action",
								data:{"table.id":tableid,"table.status":0},
								dataType:"json",
								success:function(res){
									
								}
							});
						}
						window.location.href = 'orderList.jsp';
					}
				});
			},
			function(){
				layer.closeAll();
			});
}
//****************************************************食谱选择操作end*************************************

//*******************************************餐桌选择操作start**********************************
/**
 * 显示餐桌列表
 */
function showTableWin(param){
	$("tr[tag='tablesTrAppend']").remove();
	if(param == null ){
		param = {};
	}
	param["table.weid"] = weid;
	param["table.storeid"] = storeid;
	param["table.status"] = 0;
	
	//加载食谱列表
	$.ajax({
		type:"POST",
		url:"/selforder/api/table/allTableList.action",
		data:param,
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
				return;
			}
			var tableList = res.data;
			if(tableList.length>0){
				for(var i=0;i<tableList.length;i++){
					var row = tableList[i];
					var id = row.id;
					var title = row.title;
					var user_count = row.user_count;
					var tr = "";
					tr += '<tr tag="tablesTrAppend" id="'+id+'" class="animated flipInX">       ';
					tr += '	<td>'+(i+1)+'</td>';
					tr += '	<td>'+title+'</td>';
					tr += '	<td>'+user_count+'</td>';
					tr += '	<td><button type="button" class="btn btn-warning" onclick="selectTab(\''+id+'\',\''+title+'\')">选择</button></td>';
					tr += '</tr>      ';
					$("#tableList").append(tr);
				}
			}
		}
	});
	$("#tablesWin").modal("show");
}

/**
 * 搜索食谱列表
 */
function searchTableList(){
	var table_title = $("#table_title").val();
	var param = {};
	param["table.title"] = table_title;
	showTableWin(param);
	
}

/**
 * 选择餐桌
 * @param id 餐桌id
 * @param title 餐桌名称
 */
function selectTab(id,title){
	$("#tableid").val(id);
	$("#tablecode").val(title);
	$("#tablesWin").modal("hide");
}
//*******************************************餐桌选择操作end**********************************