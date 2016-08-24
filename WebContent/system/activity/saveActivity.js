$(function(){
	if(opt == "update"){
		getActivityInfo();
	}
});

/**
 * 获取活动详情
 */
function getActivityInfo(){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	if(!checkValueWithInfo(id,"查询参数为空！")) return;
	$.ajax({
		type:"POST",
		url:"/selforder/api/activity/getActivityInfo.action",
		data:{"activity.id":id},
		dataType:"json",
		success:function(res){
			layer.close(load);
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				var row = res.data;
				var begindate = formatDate(row.begindate);
				begindate = begindate.substring(0,10);
				var enddate = formatDate(row.enddate);
				enddate = enddate.substring(0, 10);
				$("#title").val(row.title);
				$("#type").val(row.type);
				$("#discount").val(row.discount);
				$("#leastcost").val(row.leastcost);
				$("#begindate").val(begindate);
				$("#enddate").val(enddate);
				$("#status").val(row.status);
			}
		}
	});
}

/**
 * 保存活动
 */
function saveActivity(){
	if(opt == "update"){
		updateActivity();
	}else{
		addActivity();
	}
}

/**
 * 新增活动
 */
function addActivity(){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	var title = $("#title").val();
	var type = $("#type").val();
	var url = $("#url").val();
	var discount = $("#discount").val();
	var leastcost = $("#leastcost").val();
	var begindate = $("#begindate").val();
	var enddate = $("#enddate").val();
	var status = $("#status").val();
	if(!checkValueWithInfo(title,"活动名称不能为空！"))return;
	if(!checkValueWithInfo(type,"活动类型不能为空！"))return;
	if(!checkValueWithInfo(discount,"优惠/折扣金额不能为空！"))return;
	if(!checkValueWithInfo(leastcost,"最少消费不能为空！"))return;
	if(!checkValueWithInfo(begindate,"活动开始时间不能为空！"))return;
	if(!checkValueWithInfo(enddate,"活动结束时间不能为空！"))return;
	if(!checkValueWithInfo(status,"活动状态不能为空！"))return;
	
	var param = {};
	param["activity.title"] = title;
	param["activity.type"] = type;
	param["activity.url"] = url;
	param["activity.discount"] = discount;
	param["activity.leastcost"] = leastcost;
	param["activity.begindate"] = begindate;
	param["activity.enddate"] = enddate;
	param["activity.status"] = status;
	
	$.ajax({
		type:"POST",
		url:"/selforder/api/activity/insertActivity.action",
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
			window.location.href = 'activityList.jsp';
		}
	});
}

/**
 * 更新活动
 */
function updateActivity(){
	var title = $("#title").val();
	var type = $("#type").val();
	var url = $("#url").val();
	var discount = $("#discount").val();
	var leastcost = $("#leastcost").val();
	var begindate = $("#begindate").val();
	var enddate = $("#enddate").val();
	var status = $("#status").val();
	if(!checkValueWithInfo(title,"活动名称不能为空！"))return;
	if(!checkValueWithInfo(type,"活动类型不能为空！"))return;
	if(!checkValueWithInfo(discount,"优惠/折扣金额不能为空！"))return;
	if(!checkValueWithInfo(leastcost,"最少消费不能为空！"))return;
	if(!checkValueWithInfo(begindate,"活动开始时间不能为空！"))return;
	if(!checkValueWithInfo(enddate,"活动结束时间不能为空！"))return;
	if(!checkValueWithInfo(status,"活动状态不能为空！"))return;
	
	var param = {};
	param["activity.title"] = title;
	param["activity.type"] = type;
	param["activity.url"] = url;
	param["activity.discount"] = discount;
	param["activity.leastcost"] = leastcost;
	param["activity.begindate"] = begindate;
	param["activity.enddate"] = enddate;
	param["activity.status"] = status;
	param["activity.id"] = id;
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	$.ajax({
		type:"POST",
		url:"/selforder/api/activity/updateActivity.action",
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
			window.location.href = 'activityList.jsp';
		}
	});
}