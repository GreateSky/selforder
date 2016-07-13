var total = 0;
var pageSize = 20; //默认每页20条数据
var pageStart = 0;
$(function(){
	//判断是否更新操作
	if("update"== opt){
		getRoleInfo();
	}
});

//获取角色基本信息
function getRoleInfo(){
	var getinfo_load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	if(typeof(rid) == "undefined" || rid == ""){
		layer.alert("获取角色基本信息参数异常！", {icon: 5});
		return;
	}else{
		$.ajax({
			type:"POST",
			url:"/selforder/api/power/getRoleInfo.action",
			data:{"role.rid":rid},
			dataType:"json",
			success:function(data){
				var roleInfo = data.data.roleinfo;
				var reflist = data.data.reflist;
				layer.close(getinfo_load);
				if(typeof(roleInfo) == "undefined" || roleInfo == ""){
					layer.alert("获取角色基本信息异常！", {icon: 5});
					return;
				}else{
					var rname = roleInfo.rname;
					var rcode = roleInfo.rcode;
					var remark = roleInfo.remark;
					$("#rname").val(rname);
					$("#rcode").val(rcode);
					$("#remark").val(remark);
				}
				if(typeof(reflist) != "undefined" && reflist.length >0){
					for(var i=0;i<reflist.length;i++){
						var row = reflist[i];
						var appendTr = "";
						appendTr += '<tr tag="roleResource_append" resourceid="'+row.resource_id+'">           ';
						appendTr += '  <td>'+row.resource_name+'</td>';
						appendTr += '  <td>'+row.resource_url+'</td> ';
						appendTr += '  <td><button type="button" class="btn btn-warning" name="delResource" refid="'+row.ID+'" onclick="delResourceOne(this)">删除</button></td>';
						appendTr += '</tr>          ';
						$("#resourceRefList").append(appendTr);
					}
				}
			}
		});
	}
}

//保存角色信息
function saveRole(){
	if("update"== opt){
		updateRole();
	}else{
		addRole();
	}
}

/**
 * 更新角色
 */
function updateRole(){
	//验证参数
	if(checkParam() < 0){
		return;
	}
	//组装参数
	var param = {};
	var load = layer.load(2);
	$(".form-horizontal *[name *='role.']").each(function(i){
		var name = $(this).attr("name");
		var value = $(this).val();
		param[name] = value;
	});
	//获取关联的资源
	var resourid_ref = "";
	$("tr[tag='roleResource_append']").each(function(){
		var resourceid = $(this).attr("resourceid");
		resourid_ref +=resourceid+",";
		
	});
	if(resourid_ref != ""){
		param["role.resourceid"] = resourid_ref;
	}
	param["role.rid"] = rid;
	//发送请求
	$.ajax({
		type:"POST",
		url:"/selforder/api/power/updateRole.action",
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
				window.location.href = 'roleList.jsp';
			}, 1000);
		}
	});
}
//新增角色
function addRole(){
	if(checkParam() == -1){
		return;
	}
	var param = {};
	var load = layer.load(2);
	$(".form-horizontal *[name *='role.']").each(function(i){
		var name = $(this).attr("name");
		var value = $(this).val();
		param[name] = value;
	});
	//获取关联的资源
	var resourid_ref = "";
	$("tr[tag='roleResource_append']").each(function(){
		var resourceid = $(this).attr("resourceid");
		resourid_ref +=resourceid+",";
		
	});
	if(resourid_ref != ""){
		param["role.resourceid"] = resourid_ref;
	}
	$.ajax({
		type:"POST",
		url:"/selforder/api/power/saveRole.action",
		data:param,
		dataType:'json',
		success:function(data){
			var retCode = data.retCode;
			var message = data.message;
			layer.close(load);
			layer.msg(message, {icon: 1});
			setTimeout(function(){
				window.location.href = 'roleList.jsp';
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
	var rcode = $("#rcode").val();
	if(typeof(rname) == "undefined" || rname == "" || rname == null || rname == "null"){
		layer.alert("角色名称不能为空！",{icon:5});
	}else if(typeof(rcode) == "undefined" || rcode == "" || rcode == null || rcode == "null"){
		layer.alert("角色编码不能为空！",{icon:5});
	}else{
		temp = 0;
	}
	return temp;
}

/**
 * 检查编码是否重复
 */
function checkCode(){
	var rcode = $("#rcode").val();
	if(rcode == ""){
		return;
	}else{
		$.ajax({
			type:"POST",
			url:"/selforder/api/power/getRoleInfo.action",
			data:{"role.rcode":rcode},
			dataType:"json",
			success:function(data){
				var retCode = data.retCode;
				if(retCode == 0){
					var role = data.data;
					if(typeof(role) != "undefined" && role != null){
						layer.alert("编码已存在！",{icon:5});
						$("#rcode").val("");
					}
				}
			}
		});
	}
}

/**
 * 加载资源列表并打开对话框
 */
function loadRresourceList(){
	loadResourceList("init",null);
	$('#resourceWin').modal('show');
}

/**
 * 加载商户列表
 * @param type 查询类型  init:初始化查询   pageQuery:分页查询
 * @param param 
 */
function loadResourceList(type,param){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	if(typeof(param) == "undefined" || param == null || param == ""){
		param = {};
		//param["page"] = pageStart;
		//param["limit"] = pageSize;
	}else{
		//param["page"] = pageStart;
		//param["limit"] = pageSize;
	}
	
	//获取已经选择的资源
	var resourid_ref = "";
	$("tr[tag='roleResource_append']").each(function(){
		var resourceid = $(this).attr("resourceid");
		resourid_ref +="'"+resourceid+"',";
		
	});
	if(resourid_ref != ""){
		param["role.resourceid"] = resourid_ref.substring(0,resourid_ref.length-1);
	}
	var keyword = $("#keyword").val();
	param["role.rid"] = rid;
	param["role.keyword"] = keyword;
	$.ajax({
		type:"POST",
		url:"/selforder/api/power/selectResourceList.action",
		data:param,
		dataType:'json',
		success:function(data){
			layer.close(load);
			//清除历史数据
			$("tr[tag='resource_append']").remove();
			if(typeof(data) == "undefined" || data == ""){
				return;
			}else{
				var rows = data.data;
				if(rows.length > 0){
					for(var i=0;i<rows.length;i++){
						var tr = "";
						var row = rows[i];
						var rid = row.rid;
						var rname  = row.rname;
						var rurl = row.rurl;
						var remark = row.remark;
						tr +='<tr tag="resource_append" rid="'+rid+'" class="animated flipInX">                                     ';
						tr +='	<td><input type="checkbox"></input></td>';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+rname+'</td>    ';
						tr +='	<td>'+rurl+'</td>                        ';
						tr +='</tr>                                    ';
						$("#resourceList").append(tr);
					}
				}
			}
		},
		fail:function(){
			layer.close(load);
		}
	});
}

/**
 * 保存选择的资源
 */
function saveSelectResource(){
	var selectNum = -1;
	selectNum = $("#resourceList input[type='checkbox']:checked").length;
	if(selectNum <=0 ){
		layer.msg("请选择需要关联的资源!", {icon: 5});
		return;
	}else{
		//清除历史数据
		$("tr[tag='roleResource_append']").remove();
		$("#resourceList input[type='checkbox']:checked").each(function(i){
			var tr = $(this).parent().parent();
			var rid =  $(tr).attr("rid");
			var rname = $(this).parent().next().next().html();
			var rurl = $(this).parent().next().next().next().html();
			var appendTr = "";
			appendTr += '<tr tag="roleResource_append" resourceid="'+rid+'">           ';
			appendTr += '  <td>'+rname+'</td>';
			appendTr += '  <td>'+rurl+'</td> ';
			appendTr += '  <td><button type="button" class="btn btn-warning" name="delResource" refid="" onclick="delResourceOne(this)">删除</button></td>';
			appendTr += '</tr>          ';
			$("#resourceRefList").append(appendTr);
		});
		$('#resourceWin').modal('hide');
		//删除按钮注册点击事件
		delResource();
	}
}

/**
 * 删除资源（批量删除）
 */
function delResource(){
	$("button[name='delResource']").each(function(){
		$(this).on("click",function(){
			var tr = $(this).parent().parent();
			$(tr).remove();
		});
	});
}

/**
 * 单个删除资源
 * @param id
 */
function delResourceOne(e){
	var refid = $(e).attr("refid");
	if(typeof(refid) != "undefined" && refid != null && refid != ""){
		$.ajax({
			type:"POST",
			url:"/selforder/api/power/delRolResourceRef.action",
			data:{"role.rid":refid},
			dataType:"json",
			success:function(data){
				var retCode = data.retCode;
				var message = data.message;
				if(retCode == -1){
					layer.msg("操作失败!", {icon: 5});
				}else{
					layer.msg("操作成功!", {icon: 1});
					$(e).parent().parent().remove();
				}
			}
		});
	}else{
		$(e).remove();
	}
}

function resourceSearch(){
	
}

