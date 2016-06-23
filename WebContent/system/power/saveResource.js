$(function(){
	//判断是否更新操作
	if("update"== opt){
		getBusinessInfo();
	}
});

//获取商户基本信息
function getResourceInfo(){
	var getinfo_load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	if(typeof(bid) == "undefined" || bid == ""){
		layer.alert("获取商户基本信息参数异常！", {icon: 5});
		return;
	}else{
		$.ajax({
			type:"POST",
			url:"/selforder/api/business//getBusinessInfo.action",
			data:{"business.bid":bid},
			dataType:"json",
			success:function(data){
				var businessInfo = data.data;
				layer.close(getinfo_load);
				if(typeof(businessInfo) == "undefined" || businessInfo == ""){
					layer.alert("获取商户基本信息异常！", {icon: 5});
					return;
				}else{
					var bname = businessInfo.bname;
					var bcode = businessInfo.bcode;
					var legaler = businessInfo.legaler;
					var phone = businessInfo.phone;
					var email = businessInfo.email;
					var faxes = businessInfo.faxes;
					var address = businessInfo.address;
					var status = businessInfo.status;
					var begindate = businessInfo.begindate;
					var enddate = businessInfo.enddate;
					var appid = businessInfo.appid;
					var appsecret = businessInfo.appsecret;
					var licenseid = businessInfo.licenseid;
					$("#bname").val(bname);
					$("#legaler").val(legaler);
					$("#phone").val(phone);
					$("#email").val(email);
					$("#faxes").val(faxes);
					$("#begindate").val(getDateByTime(begindate.time,"yyyy-MM-dd"));
					$("#enddate").val(getDateByTime(enddate.time,"yyyy-MM-dd"));
					$("#address").val(address);
					$("#status").val(status);
					$("#appid").val(appid);
					$("#appsecret").val(appsecret);
				}
			}
		});
	}
}

//保存商户信息
function saveResource(){
	if("update"== opt){
		updateResource();
	}else{
		addResource();
	}
}

/**
 * 更新商户
 */
function updateResource(){
	//验证参数
	if(checkParam() < 0){
		return;
	}
	//组装参数
	var param = {};
	var load = layer.load(2);
	$(".form-horizontal *[name *='resource.']").each(function(i){
		var name = $(this).attr("name");
		var value = $(this).val();
		param[name] = value;
	});
	param["resource.rid"] = rid;
	//发送请求
	$.ajax({
		type:"POST",
		url:"/selforder/api/resource/updateResource.action",
		data:param,
		dataType:'json',
		success:function(data){
			//处理返回结果
			var retCode = data.retCode;
			var message = data.message;
			layer.close(load);
			if(retCode < 0){
				layer.msg(message, {icon: 2});
			}else{
				layer.msg(message, {icon: 1});
			}
			setTimeout(function(){
				window.location.href = 'businessList.jsp';
			}, 1000);
		}
	});
}
//新增商户
function addResource(){
	var param = {};
	var load = layer.load(2);
	$(".form-horizontal *[name *='resource.']").each(function(i){
		var name = $(this).attr("name");
		var value = $(this).val();
		param[name] = value;
	});
	$.ajax({
		type:"POST",
		url:"/selforder/api/power/saveResource.action",
		data:param,
		dataType:'json',
		success:function(data){
			var retCode = data.retCode;
			var message = data.message;
			layer.close(load);
			layer.msg(message, {icon: 1});
			setTimeout(function(){
				window.location.href = 'resourceList.jsp';
			}, 1000);
		}
	});
}

/**
 * 参数验证
 */
function checkParam(){
	var temp = -1;
	var rname = $("#rname").val();
	var rurl = $("#rurl").val();
	if(typeof(rname) == "undefined" || rname == "" || rname == null || rname == "null"){
		layer.alert("资源名称不能为空！",{icon:5});
	}else if(typeof(rurl) == "undefined" || rurl == "" || rurl == null || rurl == "null"){
		layer.alert("资源链接不能为空！",{icon:5});
	}else{
		temp = 0;
	}
	return temp;
}

