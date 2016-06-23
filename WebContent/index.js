/**
 * 修改密码
 */
function updatePassword(){
	var password = $("#password").val();
	var newpassword = $("#newpassword").val();
	var newpasswordagain= $("#newpasswordagain").val();
	if(checkValue(password,"原始密码不能为空！")<0){
		return;
	}
	if(checkValue(newpassword,"新密码不能为空！")<0){
		return;
	}
	if(checkValue(newpasswordagain,"请输入确认密码！")<0){
		return;
	}
	if(newpassword != newpasswordagain){
		layer.alert("两次输入密码不一致！",{icon:5});
		return;
	}
	
	var param = {};
	param["employee.password"] = password;
	param["employee.newpassword"] = newpassword;
	$.ajax({
		type:"POST",
		url:"/selforder/api/employee/updatePassword.action",
		data:param,
		dataType:"json",
		success:function(data){
			$("#updatePasswordWin").modal('hide');
			var retCode = data.retCode;
			var message = data.message;
			if(retCode < 0 ){//修改失败！
				layer.msg(message, {icon: 5});
			}else{//修改成功注销当前用户
				window.location.href = "/selforder/j_spring_security_logout";
			}
		}
	});
}

/**
 * 显示修改密码窗口
 */
function showUpdateWin(){
	$("#password").val("");
	$("#newpassword").val("");
	$("#newpasswordagain").val("");
	$("#updatePasswordWin").modal('show');
}

/**
 * 提交修改
 */
function saveBtn(e){
	$(e).popover({
		placement:"left",
		trigger:"click",
		html:true,
		title:"确定操作？",
		content:'<button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button> <button type="button" class="btn btn-primary" onclick="updatePassword()">确定</button>',
	});
	$(e).popover('show');
}
