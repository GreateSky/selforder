var fileid = "";
$(function(){
	//判断是否更新操作
	if("update"== opt){
		getBusinessInfo();
		$("div[tag='add']").remove();
	}
});

//获取商户基本信息
function getBusinessInfo(){
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
					var begindate = formatDate(businessInfo.begindate);
					begindate = begindate.substring(0,10);
					var enddate = formatDate(businessInfo.enddate);
					enddate = enddate.substring(0,10);
					var appid = businessInfo.appid;
					var appsecret = businessInfo.appsecret;
					var licenseid = businessInfo.licenseid;
					var sysadmin = businessInfo.sysadmin;
					$("#bname").val(bname);
					$("#legaler").val(legaler);
					$("#phone").val(phone);
					$("#email").val(email);
					$("#faxes").val(faxes);
					$("#begindate").val(begindate);
					$("#enddate").val(enddate);
					$("#address").val(address);
					$("#status").val(status);
					$("#appid").val(appid);
					$("#bcode").val(bcode);
					$("#sysadmin").val(sysadmin);
					$("#sysadmin").attr("disabled","disabled");
					$("#appsecret").val(appsecret);
					var imgsrc = "/selforder/api/fileutil?method=download&fileid="+licenseid;
					$("#licenseImg").attr("src",imgsrc);
				}
			}
		});
	}
}

//上传附件
function uploadFile(){
	var bname = $("#bname").val();
	var bcode = $("#bcode").val();
	var legaler = $("#legaler").val();
	var phone = $("#phone").val();
	var address = $("#address").val();
	var status = $("#status").val();
	var begindate = $("#begindate").val();
	var enddate = $("#enddate").val();
	var sysadmin = $("#sysadmin").val();
	var password = $("#password").val();
	var passwordagain = $("#passwordagain").val();
	if(!checkValueWithInfo(bname,"商户名称不能为空！")) return;
	if(!checkValueWithInfo(bcode,"商户编码不能为空！")) return;
	if(!checkValueWithInfo(legaler,"法人不能为空！")) return;
	if(!checkValueWithInfo(phone,"电话不能为空！")) return;
	if(!checkValueWithInfo(address,"地址不能为空！")) return;
	if(!checkValueWithInfo(status,"状态不能为空！")) return;
	if(!checkValueWithInfo(begindate,"开始合作日期不能为空！")) return;
	if(!checkValueWithInfo(enddate,"结束合作日期不能为空！")) return;
	if(begindate>enddate){
		layer.msg("开始日期不能大于结束日期!",{icon:"5"});
		return;
	}
	if(opt != "update"){
		if(!checkValueWithInfo(sysadmin,"系统管理员不能为空！")) return;
		if(!checkValueWithInfo(password,"登录密码不能为空！")) return;
		if(!checkValueWithInfo(passwordagain,"确认密码不能为空！"	)) return;
		if(password != passwordagain){
			layer.msg("两次输入密码不一致！",{icon:5});
			return;
		}
	}
	ajaxFileUpload("saveBusiness");
}

//保存商户信息
function saveBusiness(args){
	if(typeof(args) != "undefined" && args != ""){ 
		fileid = args[0].fileid;
	}
	if("update"== opt){
		updateBusiness();
	}else{
		addBusiness();
	}
}

/**
 * 更新商户
 */
function updateBusiness(){
	//组装参数
	var param = {};
	var load = layer.load(2);
	$(".form-horizontal *[name *='business.']").each(function(i){
		var name = $(this).attr("name");
		var value = $(this).val();
		param[name] = value;
	});
	param["business.bid"] = bid;
	param["business.licenseid"] = fileid;
	//发送请求
	$.ajax({
		type:"POST",
		url:"/selforder/api/business/updateBusiness.action",
		data:param,
		dataType:'json',
		success:function(data){
			//处理返回结果
			var retCode = data.retCode;
			var message = data.message;
			layer.close(load);
			if(retCode < 0){
				layer.msg(message, {icon: 5});
			}else{
				layer.msg(message, {icon: 6});
			}
			setTimeout(function(){
				window.location.href = 'businessList.jsp';
			}, 1000);
		}
	});
}
//新增商户
function addBusiness(){
	var param = {};
	var load = layer.load(2);
	$(".form-horizontal *[name *='business.']").each(function(i){
		var name = $(this).attr("name");
		var value = $(this).val();
		param[name] = value;
	});
	param["business.licenseid"] = fileid;
	$.ajax({
		type:"POST",
		url:"/selforder/api/business/saveBusiness.action",
		data:param,
		dataType:'json',
		success:function(data){
			var retCode = data.retCode;
			var message = data.message;
			layer.close(load);
			layer.msg(message, {icon: 1});
			setTimeout(function(){
				window.location.href = 'businessList.jsp';
			}, 1000);
		}
	});
}

//检查编码是否重复
function checkCode(){
	var bcode = $("#bcode").val();
	if(typeof(bcode) == "undefined" || bcode == "" || bcode == null || bcode == "null"){
		return "";
	}
	$.ajax({
		type:"POST",
		url:"/selforder/api/business//getBusinessInfo.action",
		data:{"business.bcode":bcode},
		dataType:"json",
		success:function(data){
			var data = data.data;
			if(typeof(data) !="undefined" && data != null){
				layer.alert("编码已存在，请重新输入！",{icon:5});
				$("#bcode").val("");
				return ;
			}
		}
	});
}

//检查管理员登录名是否存在
function checkSystemAdmin(){
	var sysadmin = $("#sysadmin").val();
	if(typeof(sysadmin) == "undefined" || sysadmin == "" || sysadmin == null || sysadmin == "null"){
		return "";
	}
	$.ajax({
		type:"POST",
		url:"/selforder/api/employee/getEmployeeInfo.action",
		data:{"employee.loginname":sysadmin},
		dataType:"json",
		success:function(data){
			var data = data.data;
			if(typeof(data) !="undefined" && data != null){
				layer.alert("登录名已存在，请重新输入！",{icon:5});
				$("#sysadmin").val("");
				return ;
			}
		}
	});
}

