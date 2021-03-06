var orderStatus = 0;//订单状态
var tableid = "";//餐桌ID
var dining_mode=2;
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
			var address = orderInfo.address;
			var taste = orderInfo.taste;
			var remark = orderInfo.remark;
			var transid = orderInfo.transid;
			var paytype = orderInfo.paytype;
			$("input[name='payType']").each(function(){
				var value = $(this).val();
				if(value == paytype){
					$(this).prop("checked",true);
				}
			});
			if(typeof(transid)!= "undefined" && null != transid && "" != transid && "0" != transid){
				$("#payInfo").html("微信订单号(点击进行复制)："+transid);
				$("#payInfo").css("display","block");
				$("#checkPayInfo").css("display","block");
				$("#payInfo").attr("transid",transid);
				$('#payInfo').zclip({
			        path:"ZeroClipboard.swf",
			        copy:function(){
				        return $("#payInfo").attr("transid");
				　 　}, 
					afterCopy: function(){//复制成功 
						layer.msg("内容已复制至粘贴板",{icon:6});
					}
			    });
			}
			var transferid = orderInfo.transferid;
			var transfername = orderInfo.transfername;
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
			$("#transferid").val(transferid);
			$("#transfername").val(transfername);
			$("#realprice").val(realprice);
			$("#tableid").val(tableid);
			$("#username").val(username);
			$("#tel").val(tel);
			$("#address").val(address);
			//已交易完成的订单取消操作功能
			if(status == 4){
				$("button[tag *='actionBtn']").attr("disabled","disabled");
				$("input").attr("readonly","readonly");
			}
			changeDivStyle(orderStatus);//根据状态值显示
			//订单非付款状态禁用配送和完成按钮
			if(status <= 3){
				$("button[tag='actionBtn_translate']").attr("disabled","disabled");
				$("button[tag='actionBtn_done']").attr("disabled","disabled");
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
				total += '	<td colspan="5" align="right"><h4>合计：<label id="totalCost" class="label label-info">￥'+totalCost.toFixed(2)+'&nbsp;元</label></h4></td>                 ';
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
	$("#totalCost").html("￥"+totalCost.toFixed(2)+"&nbsp元");
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
	var address = $("#address").val();
	var taste = $("#taste").val();
	var remark = $("#remark").val();
	var dining_mode = $("#dining_mode").val();
	var transferid = $("#transferid").val();
	if(!checkValueWithInfo(username,"客户名称不能为空！")){
		return;
	}
	if(!checkValueWithInfo(tel,"客户电话不能为空！")){
		return;
	}
	if(!checkValueWithInfo(address,"收货地址不能为空！")){
		return;
	}
	var param = {};
	param["order.id"] = oid;
	param["order.tableid"] = tableid;
	param["order.username"] = username;
	param["order.tel"] = tel;
	param["order.address"] = address;
	param["order.taste"] = taste;
	param["order.remark"] = remark;
	param["order.dining_mode"] = dining_mode;
	param["order.transferid"] = transferid;
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
	var address = $("#address").val();
	var taste = $("#taste").val();
	var remark = $("#remark").val();
	var dining_mode = $("#dining_mode").val();
	var transferid = $("#transferid").val();
	if(!checkValueWithInfo(username,"客户名称不能为空！")){
		return;
	}
	if(!checkValueWithInfo(tel,"客户电话不能为空！")){
		return;
	}
	if(!checkValueWithInfo(address,"收货地址不能为空！")){
		return;
	}
	var param = {};
	param["order.tableid"] = tableid;
	param["order.username"] = username;
	param["order.tel"] = tel;
	param["order.taste"] = taste;
	param["order.remark"] = remark;
	param["order.dining_mode"] = dining_mode;
	param["order.transferid"] = transferid;
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

/**
 * 检查订单付款状态
 * @returns
 */
function checkOrderPayStatus(){
	//var transid = $("#payInfo").attr("transid");
	//copyText("checkPayInfo",transid)
	//window.open('https://pay.weixin.qq.com/index.php/core/home/login');
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
					var discount_price = row.discount_price;
					var tr = "";
					tr += '<tr tag="goodsTrAppend" id="'+id+'" class="animated flipInX">       ';
					tr += '	<td>'+(i+1)+'</td>';
					tr += '	<td>'+title+'</td>';
					tr += '	<td>￥'+marketprice+'</td>';
					tr += '	<td>￥'+discount_price+'</td>';
					tr += '	<td><button type="button" class="btn btn-warning" onclick="addGoods(\''+id+'\',\''+title+'\',\''+discount_price+'\',this)">添加</button></td>';
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
function addGoods(id,title,discount_price,e){
	$("tr[tag='appendDetailTr']:last").remove();//删除合计行
	var index = $("tr[tag='appendDetailTr']").length;//获取已有订单行数记录数
	//拼接订单行
	var tr = "";
	tr += '<tr tag="appendDetailTr" class="animated flipInX" did="" optType="add" goods_id="'+id+'">                                                             ';
	tr += '	<td>'+(index+1)+'</td>                                                      ';
	tr += '	<td>'+title+'</td>                                               ';
	tr += '	<td tag="price">'+discount_price+'</td>                                              ';
	tr += '	<td><input tag="goodsnum" type="number" min="1" max="10" tag="num" value="'+1+'" onchange="editCost(this)"></input></td>';
	tr += '	<td>                                                            ';
	tr += '		<button type="button" class="btn btn-warning" onclick="delNewGoods(this)">取消</button>   ';
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
	total += '	<td colspan="5" align="right"><h4>合计：<label id="totalCost" class="label label-info">￥'+totalCost.toFixed(2)+'&nbsp;元</label></h4></td>                 ';
	total += '</tr>                                                                ';
	$("#orderDetailList").append(total);
	$(e).parent().parent().remove();//清除改行菜谱
}

//删除新增的订单行
function delNewGoods(e){
	$(e).parent().parent().remove();
	//计算合计费用
	var totalCost = 0;//合计费用
	$("input[tag='goodsnum']").each(function(){//遍历input[tag='goodsnum'] 的输入框
		var value = $(this).val();
		var price = $(this).parent().prev("td").html();
		totalCost += price*value
	});
	$("#totalCost").html('￥'+totalCost.toFixed(2)+'&nbsp;元');
}

/**
 * 订单配送
 * @returns
 */
function orderTranslate(){
	var transferid = $("#transferid").val();
	if(!checkValueWithInfo(transferid,"请选择配送人！")) return;
	layer.confirm("确定要开始配送该订单吗？",
			{btn:["确定","取消"]},
			function(){
				layer.closeAll();
				$.ajax({
					type:"POST",
					url:"/selforder/api/order/updateOrderStatus.action",
					data:{"order.status":3,"order.id":oid},
					dataType:"json",
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						window.location.href = 'orderList.jsp';
					}
				});
			},
			function(){
				layer.closeAll();
			});
}

/**
 * 订单完成
 * @returns
 */
function done(){
	var totalprice = $("#totalprice").val();
	var realprice = $("#realprice").val();
	var payTypeRadio = $("input:checked");
	if(payTypeRadio.length != 1){
		layer.msg("请选择付款方式！",{icon:5});
		return;
	}
	var payType = $(payTypeRadio).val();
	if(!checkValueWithInfo(totalprice,"应付金额不能为空！")) return;
	if(!checkValueWithInfo(realprice,"实付金额不能为空！")) return;
	layer.confirm("本单应付金额为：【"+totalprice+"】,实付金额为【<font color='red'>"+realprice+"</font>】,确定已完成吗？",
			{btn:["确定","取消"]},
			function(){
				layer.closeAll();
				$.ajax({
					type:"POST",
					url:"/selforder/api/order/updateOrderStatus.action",
					data:{"order.id":oid,"order.realprice":realprice,"order.status":4,"order.paytype":payType},
					dataType:"json",
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
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

//*******************************************配送人员选择操作start**********************************
/**
 * 显示餐桌列表
 */
function showTransferWin(param){
	$("tr[tag='tablesTrAppend']").remove();
	if(param == null ){
		param = {};
	}
	param["employee.bid"] = weid;
	param["employee.sid"] = storeid;
	
	//加载食谱列表
	$.ajax({
		type:"POST",
		url:"/selforder/api/employee/getEmployeeListWithNoPage.action",
		data:param,
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
				return;
			}
			var rows = res.data;
			if(rows.length>0){
				for(var i=0;i<rows.length;i++){
					var row = rows[i];
					var empid = row.empid;
					var empname = row.empname;
					var empcode = row.empcode;
					var phone = row.phone;
					var tr = "";
					tr += '<tr tag="tablesTrAppend" id="'+empid+'" class="animated flipInX">       ';
					tr += '	<td>'+(i+1)+'</td>';
					tr += '	<td>'+empcode+'</td>';
					tr += '	<td>'+empname+'</td>';
					tr += '	<td>'+phone+'</td>';
					tr += '	<td><button type="button" class="btn btn-warning" onclick="selectTransfer(\''+empid+'\',\''+empname+'\')">选择</button></td>';
					tr += '</tr>      ';
					$("#transferList").append(tr);
				}
			}
		}
	});
	$("#transferWin").modal("show");
}

/**
 * 搜索食谱列表
 */
function searchTransferList(){
	var transferKeyword = $("#transferKeyword").val();
	var param = {};
	param["employee.keyword"] = transferKeyword;
	showTransferWin(param);
	
}

/**
 * 选择配送人
 * @param empid 配送人ID
 * @param empname 配送人name
 * @returns
 */
function selectTransfer(empid,empname){
	$("#transfername").val(empname);
	$("#transferid").val(empid);
	$("#transferWin").modal("hide");
	$.ajax({
		type:"POST",
		url:"/selforder/api/order/updateOrderStatus.action",
		data:{"order.transferid":empid,"order.id":oid},
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0){
				layer.msg(message,{icon:5});
			}else{
				layer.msg("分配配送人成功！",{icon:6});
			}
		}
	});
}

//*******************************************配送人员选择操作end**********************************

//打印订单
function doPrint(){
	var ifr = document.getElementById('printframe');
	ifr.src = "printOrder.jsp?oid="+oid;
}