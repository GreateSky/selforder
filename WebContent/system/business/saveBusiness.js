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
					$("#begindate").val(null == begindate?"":getDateByTime(begindate.time,"yyyy-MM-dd"));
					$("#enddate").val(null == enddate?"":getDateByTime(enddate.time,"yyyy-MM-dd"));
					$("#address").val(address);
					$("#status").val(status);
					$("#appid").val(appid);
					$("#bcode").val(bcode);
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
	//验证参数
	if(checkParam() < 0){
		return;
	}
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
function addBusiness(){
	if(checkParam() < 0 ){
		return;
	}
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

/**
 * 参数验证
 */
function checkParam(){
	var temp = -1;
	var bname = $("#bname").val();
	var bcode = $("#bcode").val();
	var legaler = $("#legaler").val();
	var phone = $("#phone").val();
	var email = $("#email").val();
	var address = $("#address").val();
	var status = $("#status").val();
	var begindate = $("#begindate").val();
	var sysadmin = $("#sysadmin").val();
	var password = $("#password").val();
	var passwordagain = $("#passwordagain").val();
	if(typeof(bname) == "undefined" || bname == "" || bname == null || bname == "null"){
		layer.alert("商户名称不能为空！",{icon:5});
	}else if(typeof(bcode) == "undefined" || bcode == "" || bcode == null || bcode == "null"){
		layer.alert("编码不能为空！",{icon:5});
	}else if(typeof(legaler) == "undefined" || legaler == "" || legaler == null || legaler == "null"){
		layer.alert("法人不能为空！",{icon:5});
	}else if(typeof(phone) == "undefined" || phone == "" || phone == null || phone == "null"){
		layer.alert("电话不能为空！",{icon:5});
	}else if(typeof(email) == "undefined" || email == "" || email == null || email == "null"){
		layer.alert("邮箱不能为空！",{icon:5});
	}else if(typeof(address) == "undefined" || address == "" || address == null || address == "null"){
		layer.alert("地址不能为空！",{icon:5});
	}else if(typeof(status) == "undefined" || status == "" || status == null || status == "null"){
		layer.alert("状态不能为空！",{icon:5});
	}else if(typeof(begindate) == "undefined" || begindate == "" || begindate == null || begindate == "null"){
		layer.alert("开始合作时间不能为空！",{icon:5});
	}else if((typeof(sysadmin) == "undefined" || sysadmin == "" || sysadmin == null || sysadmin == "null") && opt !="update"){
		layer.alert("系统管理员不能为空！",{icon:5});
	}else if((typeof(password) == "undefined" || password == "" || password == null || password == "null") && opt !="update"){
		layer.alert("登录密码不能为空！",{icon:5});
	}else if((typeof(passwordagain) == "undefined" || passwordagain == "" || passwordagain == null || passwordagain == "null") && opt != "update"){
		layer.alert("登录密码确认不能为空！",{icon:5});
	}else if((passwordagain !=  password) && opt !="update"){
		layer.alert("两次密码不一致！",{icon:5});
	}else{
		temp = 0;
	}
	return temp;
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
