$(function(){
	loadRoomCommbox();//加载包厢
	if(opt == "update"){
		getTableInfo();//获取餐桌详情
		//显示二维码图片
		$("#qrcodeDiv").css("display","block");
		$("#service_qrcodeDiv").css("display","block");
		$("#update_qrcodeDiv").css("display","block");
	}
});

function getTableInfo(){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	$.ajax({
		type:"POST",
		url:"/selforder/api/table/getTableInfo.action",
		data:{"table.id":id},
		dataType:"json",
		success:function(res){
			layer.close(load);
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){//餐桌已存在
				layer.msg(message,{icon:5});
			}else{
				var row = res.data;
				var title = row.title;
				var room_id = row.room_id;
				var user_count = row.user_count;
				var limit_price = row.limit_price;
				var displayorder = row.displayorder;
				var qrcodeid = row.qrcodeid;
				var service_qrcodeid = row.service_qrcodeid;
				var imgsrc = "/selforder/api/fileutil?method=download&fileid="+qrcodeid;
				var simg = "/selforder/api/fileutil?method=download&fileid="+service_qrcodeid;
				$("#title").val(title);
				$("#roomSelect").val(room_id);
				$("#user_count").val(user_count);
				$("#limit_price").val(limit_price);
				$("#displayorder").val(displayorder);
				$("#qrcodeid").attr("src",imgsrc);
				$("#service_qrcodeid").attr("src",simg);
			}
		}
	});
}
/**
 * 加载包厢
 */
function loadRoomCommbox(){
	$("#roomSelect option").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/table/roomList.action",
		data:{"table.storeid":sid},
		dataType:"json",
		async:false,
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				var rows = res.data;
				if(rows.length > 0){
					for(var i=0;i<rows.length;i++){
						var row = rows[i];
						var room_id = row.room_id;
						var room_name = row.room_name;
						var option= "";
						option = "<option value='"+room_id+"'>"+room_name+"</option>";
						$("#roomSelect").append(option);
					}
				}
			}
		}
	});
}

/**
 * 保存餐桌
 */
function saveTable(){
	if(opt == "update"){
		updateTable();
	}else{
		addTable();
	}
}

/**
 * 新增餐桌
 */
function addTable(){
	var title = $("#title").val();
	var room_id = $("#roomSelect").val();
	var user_count = $("#user_count").val();
	var limit_price = $("#limit_price").val();
	var displayorder = $("#displayorder").val();
	if(!checkValueWithInfo(title,"餐桌编码不能为空!")){
		return;
	}
	if(!checkValueWithInfo(room_id,"请选择餐桌所属包厢!")){
		return;
	}
	if(!checkValueWithInfo(user_count,"请输入最大用餐人数!")){
		return;
	}
	var param = {};
	param["table.storeid"] = sid;
	param["table.title"] = title;
	param["table.room_id"] = room_id;
	param["table.user_count"] = user_count;
	param["table.limit_price"] = limit_price;
	param["table.displayorder"] = displayorder;
	$.ajax({
		type:"POST",
		url:"/selforder/api/table/insertTable.action",
		data:param,
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			showResult(retCode,message);
			window.location.href = "/selforder/system/dinnerTable/tableList.jsp?sid="+sid;
		}
	});
}

/**
 * 更新餐桌
 */
function updateTable(){
	var title = $("#title").val();
	var room_id = $("#roomSelect").val();
	var user_count = $("#user_count").val();
	var limit_price = $("#limit_price").val();
	var displayorder = $("#displayorder").val();
	if(!checkValueWithInfo(title,"餐桌编码不能为空!")){
		return;
	}
	if(!checkValueWithInfo(room_id,"请选择餐桌所属包厢!")){
		return;
	}
	if(!checkValueWithInfo(user_count,"请输入最大用餐人数!")){
		return;
	}
	var param = {};
	param["table.storeid"] = sid;
	param["table.title"] = title;
	param["table.room_id"] = room_id;
	param["table.user_count"] = user_count;
	param["table.limit_price"] = limit_price;
	param["table.displayorder"] = displayorder;
	param["table.id"] = id;
	$.ajax({
		type:"POST",
		url:"/selforder/api/table/updateTable.action",
		data:param,
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			showResult(retCode,message);
			window.location.href = "/selforder/system/dinnerTable/tableList.jsp?sid="+sid;
		}
	});
}

/**
 * 检查餐桌编码是否已存在
 */
function checkTableCode(){
	var title = $("#title").val();
	if(!checkValue(title)){
		return;
	}else{
		$.ajax({
			type:"POST",
			url:"/selforder/api/table/getTableInfo.action",
			data:{"table.storeid":sid,"table.title":title},
			dataType:"json",
			success:function(res){
				var retCode = res.retCode;
				var message = res.message;
				if(retCode == 0 ){//餐桌已存在
					layer.msg("该名称已存在，请更换!",{icon:5});
					$("#title").val("");
				}else{
					return;
				}
			}
		});
	}
}

/**
 * 更新餐桌二维码
 */
function updateQrcode(){
	if(!checkValueWithInfo(id,"餐桌ID不能为空！")){
		return;
	}
	$.ajax({
		type:"POST",
		url:"/selforder/api/table/updateQrcode.action",
		data:{"table.id":id},
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode >= 0){
				layer.msg(message,{"icon":6});
			}else{
				layer.msg(message,{"icon":5});
			}
			getTableInfo();
		}
	});
}