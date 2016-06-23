$(function(){
	if("update" == opt){
		$("div[tag='login']").remove();
		getEmployeeInfo();
	}
});

/**
 * 获取员工详情
 */
function getEmployeeInfo(){
	if(typeof(empid) == "undefined" || empid == null){
		layer.alert("获取员工详情参数异常！",{icon:5});
		return;
	}else{
		$.ajax({
			type:"POST",
			url:"/selforder/api/employee/getEmployeeInfo.action",
			data:{"employee.empid":empid},
			dataType:"json",
			success:function(data){
				var emp = data.data;
				if(emp != null){
					$("#empname").val(emp.empname);
					$("#sex").val(emp.sex);
					$("#phone").val(emp.phone);
					$("#homeaddress").val(emp.homeaddress);
					$("#contactname").val(emp.contactname);
					$("#contactphone").val(emp.contactphone);
					$("#wechatid").val(emp.wechatid);
					$("#status").val(emp.status);
					$("#headimgurl").attr("src","/selforder/api/fileutil?method=download&fileid="+emp.headimgurl);
				}
			}
		});
	}
}

/**
 * 检查登录名是否重复
 */
function checkLoginName(){
	var loginname = $("#loginname").val();
	if(typeof(loginname) == "undefined" || loginname == null){
		$("#loginname").val("");
		return;
	}else{
		$.ajax({
			type:"POST",
			url:"/selforder/api/employee/getEmployeeInfo.action",
			data:{"employee.loginname":loginname},
			dataType:"json",
			success:function(data){
				var employee = data.data;
				if(employee != null){
					layer.alert("登录名已存在，请重新输入！",{icon:5});
					$("#loginname").val("");
				}
			}
		});
	}
}

/**
 * 更新员工信息
 */
function updateEmplpyeeInfo(){
	if(checkParam()<0){
		return;
	}
	var param = {};
	$(".form-horizontal *[name *='employee.']").each(function(i){
		var name = $(this).attr("name");
		var value = $(this).val();
		param[name] = value;
	});
	param["employee.empid"] = empid;
	var saveing = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	$.ajax({
		type:"POST",
		url:"/selforder/api/employee/updateEmployeeInfo.action",
		data:param,
		dataType:"json",
		success:function(data){
			layer.close(saveing);
			var retCode = data.retCode;
			var message = data.message;
			if(retCode < 0){
				layer.msg(message, {icon: 5});
			}else{
				layer.msg(message, {icon: 1});
			}
		}
	});
}

/**
 * 保存员工基本信息
 */
function saveEmployee(){
	if("update" == opt){
		updateEmplpyeeInfo();
	}else{
		addEmployee();
	}
}

/**
 * 保存元工信息
 */
function addEmployee(){
	if(checkParam()<0){
		return;
	}
	var param = {};
	$(".form-horizontal *[name *='employee.']").each(function(i){
		var name = $(this).attr("name");
		var value = $(this).val();
		param[name] = value;
	});
	var saveing = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	$.ajax({
		type:"POST",
		url:"/selforder/api/employee/saveEmployee.action",
		data:param,
		dataType:"json",
		success:function(data){
			layer.close(saveing);
			var retCode = data.retCode;
			var message = data.message;
			if(retCode < 0){
				layer.msg(message, {icon: 5});
			}else{
				layer.msg(message, {icon: 1});
			}
		}
	});
}

/**
 * 参数验证
 */
function checkParam(){
	var empname = $("#empname").val();
	var sex = $("#sex").val();
	var phone = $("#phone").val();
	var homeaddress = $("#homeaddress").val();
	var contactname = $("#contactname").val();
	var contactphone = $("#contactphone").val();
	var loginname = $("#loginname").val();
	var password = $("#password").val();
	var password_again = $("#password_again").val();
	if(checkValue(empname,"用户名不能为空！")<0){
		return -1;
	}
	if(checkValue(sex,"性别不能为空！")<0){
		return -1;
	}
	if(checkValue(phone,"电话不能为空！")<0){
		return -1;
	}
	if(checkValue(homeaddress,"家庭住址不能为空！")<0){
		return -1;
	}
	if(checkValue(contactname,"紧急联系人不能空！")<0){
		return -1;
	}
	if(checkValue(contactphone,"紧急联系人电话不能为空！")<0){
		return -1;
	}
	if(opt != "update"){
		if(checkValue(loginname,"登录名不能为空！")<0){
			return -1;
		}
		if(checkValue(password,"登录密码不能为空！")<0){
			return -1;
		}
		if(checkValue(password_again,"密码确认不能为空！")<0){
			return -1;
		}
		if(password != password_again){
			layer.alert("两次密码不一致",{icon:5});
			return -1;
		}
	}
	return 0
}
