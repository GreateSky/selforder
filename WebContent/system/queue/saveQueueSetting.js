$(function(){
	if(opt == "update") getQueueSettingInfo();
});

/**
 * 获取队列详情
 */
function getQueueSettingInfo(){
	if(id == ""){
		layer.msg("参数异常！",{icon:5});
		return;
	}
	var getinfo_load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	$.ajax({
		type:"POST",
		url:"/selforder/api/queue/getQueueSettingInfo.action",
		data:{"queueSetting.id":id},
		dataType:"json",
		success:function(res){
			layer.close(getinfo_load);
			var retCode = res.retCode;
			if(retCode < 0 ){
				var message = res.message;
				layer.msg(message,{icon:5});
				return;
			}else{
				var row = res.data;
				$("#title").val(row.title);
				$("#limit_num").val(row.limit_num);
				$("starttime").val(row.starttime);
				$("#endtime").val(row.endtime);
				$("#prefix").val(row.prefix);
				$("#notify_number").val(row.notify_number);
				$("#displayorder").val(row.displayorder);
				var status = row.status;
				$("input[type='radio'][name='status']").each(function(){
					var value = $(this).val();
					if(value == status)$(this).attr("checked","checked");
				});
			}
		}
	});
}
/**
 * 保存队列设置
 */
function saveQueueSetting(){
	if(opt == "update"){
		updateQueueSetting();
	}else{
		addQueue();
	}
}

/**
 * 新增队列
 */
function addQueue(){
	var title = $("#title").val();
	var limit_num = $("#limit_num").val();
	var starttime = $("starttime").val();
	var endtime = $("#endtime").val();
	var prefix = $("#prefix").val();
	var notify_number = $("#notify_number").val();
	var displayorder = $("#displayorder").val();
	var status = $("input[type='radio'][name='status']:checked").val();
	if(!checkValueWithInfo(title,"请输入队列名称！")) return;
	if(!checkValueWithInfo(limit_num,"请输入用餐人数！")) return;
	if(!checkValueWithInfo(prefix,"请输入队列编号！")) return;
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	var param = {};
	param["queueSetting.title"] = title;
	param["queueSetting.limit_num"] = limit_num;
	param["queueSetting.starttime"] = starttime;
	param["queueSetting.endtime"] = endtime;
	param["queueSetting.prefix"] = prefix;
	param["queueSetting.notify_number"] = notify_number;
	param["queueSetting.displayorder"] = displayorder;
	param["queueSetting.status"] = status;
	$.ajax({
		type:"POST",
		url:"/selforder/api/queue/insertQueueSetting.action",
		data:param,
		dataType:"json",
		success:function(res){
			layer.close(load);
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				layer.msg(message,{icon:6});
			}
			window.location.href = 'queueList.jsp';
		}
	});
}

/**
 * 更新队列设置
 */
function updateQueueSetting(){
	if(id == ""){
		layer.msg("参数异常！",{icon:5});
		return;
	}
	var title = $("#title").val();
	var limit_num = $("#limit_num").val();
	var starttime = $("starttime").val();
	var endtime = $("#endtime").val();
	var prefix = $("#prefix").val();
	var notify_number = $("#notify_number").val();
	var displayorder = $("#displayorder").val();
	var status = $("input[type='radio'][name='status']:checked").val();
	if(!checkValueWithInfo(title,"请输入队列名称！")) return;
	if(!checkValueWithInfo(limit_num,"请输入用餐人数！")) return;
	if(!checkValueWithInfo(prefix,"请输入队列编号！")) return;
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	var param = {};
	param["queueSetting.title"] = title;
	param["queueSetting.limit_num"] = limit_num;
	param["queueSetting.starttime"] = starttime;
	param["queueSetting.endtime"] = endtime;
	param["queueSetting.prefix"] = prefix;
	param["queueSetting.notify_number"] = notify_number;
	param["queueSetting.displayorder"] = displayorder;
	param["queueSetting.status"] = status;
	param["queueSetting.id"] = id;
	$.ajax({
		type:"POST",
		url:"/selforder/api/queue/updateQueueSetting.action",
		data:param,
		dataType:"json",
		success:function(res){
			layer.close(load);
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				layer.msg(message,{icon:6});
			}
			window.location.href = 'queueList.jsp';
		}
	});
}