$(function(){
	setTypeByLoginUserType(emp_type);//根据当前登录人类型设置用户类型
	if("update" == opt){
		$("div[tag='login']").remove();
		getEmployeeInfo();
	}
});

/**
 * 根据当前登录人类型设置用户类型
 * @param emp_type
 */
function setTypeByLoginUserType(emp_type){
	var option = "";
	if(emp_type == "A"){
		option += '<option selected="A" value="A">管理</option>              ';
		option += '<option value="B">商户</option> ';
		option += '<option value="S">门店</option>              ';
	}else if(emp_type == "B"){
		option += '<option selected="B" value="B">商户</option> ';
		option += '<option value="S">门店</option>              ';
	}else if(emp_type == "S"){
		option += '<option selected="S" value="S">门店</option>              ';
	}
	$("#type").append(option);
	
}

/**
 * 根据所选的操作员类型显示门店选择
 */
function showSelectShop(){
	var type = $("#type").val();
	if(type == "S"){
		$("#selectShop").css("display","block");
	}else{
		$("#selectShop").css("display","none");
	}
}
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
					var type = emp.type;
					$("#type").val(type);
					if(type == "S"){
						$("#sid").val(emp.sid);
						$("#sname").val(emp.sname);
						$("#selectShop").css("display","block");
					}
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
	var type = $("#type").val();
	if(type == "S"){
		var sid = $("#sid").val();
		if(typeof(sid) == "undefined" || sid == ""){
			layer.msg("请选择门店！",{icon:5});
			return -1;
		}
	}
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
	return 0;
}

/**
 * 加载门店信息
 */
function storeList(){
	$("tr[tag='appendStoreList']").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/shop/getShopListNoPage.action",
		data:"",
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
				return;
			}else{
				var rows = res.data;
				if(rows != null && rows.length>0){
					for(var i = 0 ;i<rows.length;i++){
						var row = rows[i];
						var tr = "";
						tr += '<tr tag="appendStoreList">               ';
						tr += '     <td>'+(i+1)+'</td>    ';
						tr += '     <td>'+row.title+'</td> ';
						tr += '     <td>'+row.tel+'</td> ';
						tr += '     <td><button type="button" class="btn btn-warning" onclick="selectShop(\''+row.id+'\',\''+row.title+'\')">选择</button></td> ';
						tr += '</tr>              ';
						$("#storeList").append(tr);
					}
				}
				$("#employeeWin").modal('show');
				
			}
		}
	});
}

/**
 * 
 * @param id
 */
function selectShop(id,title){
	$("#sid").val(id);
	$("#sname").val(title);
	$("#employeeWin").modal('hide');
}

/**
 * 重置密码
 * @returns
 */
function resetPwd(){
	if(!checkValueWithInfo(empid,"参数异常！")){
		return;
	}
	layer.confirm("确定要重置密码吗？",
		  {btn:["确定","取消"]},
		  function(){
			  layer.closeAll();
			  $.ajax({
				  type:"POST",
				  url:"/selforder/api/employee/resetPwd.action",
				  data:{"employee.empid":empid},
				  dataType:"json",
				  success:function(res){
					  var retCode = res.retCode;
					  var message = res.message;
					  if(retCode <0){
						  layer.msg(message,{icon:5});
					  }else{
						  layer.msg("密码已重置为【000000】",{icon:6});
					  }
				  }
			  })
		  },
		  function(){
			  layer.closeAll();
		  });
}