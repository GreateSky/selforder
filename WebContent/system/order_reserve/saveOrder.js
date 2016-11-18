$(function(){
	//判断是否更新操作
	if("update"== opt){
		getOrderInfo();
	}
})

/**
 * 获取预定订单详情
 * @returns
 */
function getOrderInfo(){
	if(!checkValueWithInfo(oid,"获取订单详情参数异常！")) return;
	$.ajax({
		type:"POST",
		url:"/selforder/api/order/reserveOrderInfo.action",
		data:{"order.id":oid},
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			if(retCode<0){
				layer.msg(res.message,{icon:"5"});
				return;
			}else{
				var row = res.data;
				$("#username").val(row.username);
				$("#tel").val(row.tel);
				var time = row.meal_time;
				if(checkValue(time)){
					time = time.replace(" ","T");
					$("#meal_time").val(time);
				}
				$("#remark").val(row.remark);
			}
		}
	});
}

/**
 * 保存订单
 * @returns
 */
function saveOrder(){
	var username = $("#username").val();
	var tel = $("#tel").val();
	var meal_time = $("#meal_time").val();
	var remark = $("#remark").val();
	if(!checkValueWithInfo(username,"客户名称不能为空！")) return;
	if(!checkValueWithInfo(tel,"客户电话不能为空！")) return;
	if(!checkValueWithInfo(meal_time,"用餐时间不能为空！")) return;
	if(opt == "update"){
		updateOrder();
	}else{
		addOrder();
	}

}

/**
 * 新增预定订单
 * @returns
 */
function addOrder(){
	var username = $("#username").val();
	var tel = $("#tel").val();
	var meal_time = $("#meal_time").val();
	meal_time = meal_time.replace("T"," ");
	var remark = $("#remark").val();
	var param = {};
	param["order.username"] = username;
	param["order.tel"] = tel;
	param["order.meal_time"] = meal_time;
	param["order.remark"] = remark;
	$.ajax({
		type:"POST",
		url:"/selforder/api/order/insertReserveOrder.action",
		data:param,
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0){
				layer.msg(message,{icon:"5"});
			}else{
				layer.msg(message,{icon:"6"});
			}
			window.location.href = "orderList.jsp"
		}
	});
}

/**
 * 新增预定订单
 * @returns
 */
function updateOrder(){
	var username = $("#username").val();
	var tel = $("#tel").val();
	var meal_time = $("#meal_time").val();
	meal_time = meal_time.replace("T"," ");
	var remark = $("#remark").val();
	var param = {};
	param["order.username"] = username;
	param["order.tel"] = tel;
	param["order.meal_time"] = meal_time;
	param["order.remark"] = remark;
	param["order.id"] = oid;
	$.ajax({
		type:"POST",
		url:"/selforder/api/order/updateReserveOrder.action",
		data:param,
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0){
				layer.msg(message,{icon:"5"});
			}else{
				layer.msg(message,{icon:"6"});
			}
			window.location.href = "orderList.jsp"
		}
	});
}