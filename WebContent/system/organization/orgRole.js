/**
 * 加载部门已关联权限
 */
function loadOrgRoleList(param){
	if(typeof(param) == "undefined" || param == null || param == ""){
		param = {};
	}
	//判断是否查询的根节点
	if(isRoot == "root"){
		//是根节点查询该商户下的所有权限
		param["role.oid"] = "queryAll";
	}else{
		//不是根节点查询该部门下已关联的权限
		if(curr_oid == ""){
			layer.msg("请选择要查看的部门!",{icon:5});
			return;
		}else{
			param["role.oid"] = curr_oid;
		}
	}
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	$.ajax({
		type:"POST",
		url:"/selforder/api/organization/getOrgRoleList.action",
		data:param,
		dataType:'json',
		success:function(res){
			layer.close(load);
			//清除历史数据
			$("tr[tag='append_hasrole']").remove();
			var rows = res.data;
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0){
				layer.msg(message,{icon:5});
			}
			if(rows){
				for(var i=0;i<rows.length;i++){
					var  row = rows[i];
					var orid = row.orid;
					var rid = row.rid;
					var rcode = row.rcode;
					var rname = row.rname;
					var tr = "";
					tr +='<tr tag="append_hasrole" orid="'+orid+'" class="animated flipInX">                                     ';
					tr +='	<td>'+(i+1)+'</td>                             ';
					tr +='	<td>'+rcode+'</td>    ';
					tr +='	<td>'+rname+'</td>                  ';
					//判断是否根节点，如果是根节点不允许进行删除操作
					if(isRoot != "root"){
						tr +='	<td><button type="button" class="btn btn-warning" onclick="delOrgRoleRef(\''+orid+'\')">删除</button></td>                    ';
					}
					tr +='</tr>                                    ';
					$("#orgRoleList").append(tr);
				}
			}
			$("#orgRoleWin").modal("show");
		},
		fail:function(){
			layer.close(load);
		}
	});
}

/**
 * 加载部门未关联的权限
 */
function loadOrgNoRoleList(param){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	if(typeof(param) == "undefined" || param == null || param == ""){
		param = {};
	}
	if(!checkValue(curr_oid)){
		layer.msg("请选择部门!",{icon:5});
		return;
	}
	param["role.oid"] = curr_oid;
	$.ajax({
		type:"POST",
		url:"/selforder/api/organization/getOrgNoRoleList.action",
		data:param,
		dataType:'json',
		success:function(res){
			layer.close(load);
			//清除历史数据
			$("tr[tag='append_norole']").remove();
			var rows = res.data;
			var retCode = res.retCode;
			var message = res.message;
			//判断请求结果
			if(retCode < 0){
				layer.msg(message,{icon:5});
			}
			//组装返回数据
			if(rows){
				for(var i=0;i<rows.length;i++){
					var  row = rows[i];
					var rid = row.rid;
					var rcode = row.rcode;
					var rname = row.rname;
					var tr = "";
					tr +='<tr tag="append_norole" rid="'+rid+'" class="animated flipInX">                                     ';
					tr +='  <td><input type="checkbox"></input></td>';
					tr +='	<td>'+(i+1)+'</td>                             ';
					tr +='	<td>'+rcode+'</td>    ';
					tr +='	<td>'+rname+'</td>                  ';
					tr +='</tr>                                    ';
					$("#orgNoRoleList").append(tr);
				}
			}
			$("#orgNoRoleWin").modal("show");
		},
		fail:function(){
			layer.close(load);
		}
	});
}

/**
 * 显示已关联的权限
 */
function openOrgRoleWin(){
	//加载数据
	loadOrgRoleList("");
}

/**
 * 删除已关联的权限
 * @param orid	部门ID
 */
function delOrgRoleRef(orid){
	if(!checkValue(orid)){
		layer.msg("取消权限关联参数异常，请刷新后重试!",{icon:5});
		return;
	}else{
		layer.confirm("确定要删除该条记录吗？",
				{btn:["确定","取消"]},
				function(){//确定操作
					$.ajax({
						type:"POST",
						url:"/selforder/api/organization/deletedOrgRole.action",
						data:{"role.orid":orid},
						dataType:"json",
						success:function(res){
							var retCode = res.retCode;
							var message = res.message;
							if(retCode<0){//异常
								layer.msg(message,{icon:5});
							}else{
								layer.msg(message,{icon:6});
							}
							//加载数据
							loadOrgRoleList("");
							layer.closeAll();
						},
						fail:function(res){
							layer.msg("操作异常!",{icon:5});
							layer.closeAll();
						}
					});
				},
				function(){//取消
					layer.closeAll();
				});
	}
}

/**
 * 展示待添加权限
 */
function showAddRole(){
	loadOrgNoRoleList("");
}

/**
 * 保存已选择的权限
 */
function saveOrgNoRole(){
	var selectNum = -1;
	selectNum = $("#orgNoRoleWin input[type='checkbox']:checked").length;
	if(selectNum <=0 ){
		layer.msg("请选择需要关联的权限!", {icon: 5});
		return;
	}else{
		layer.confirm('确定保存数据吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				layer.closeAll();
				//确定保存
				var param = {};
				$("#orgNoRoleWin input[type='checkbox']:checked").each(function(i){
					var tr = $(this).parent().parent();
					var rid =  $(tr).attr("rid");
					param["roleList["+i+"].rid"] =  rid;
					param["roleList["+i+"].oid"] = curr_oid;
				});
				$.ajax({
					type:"POST",
					url:"/selforder/api/organization/insertOrgRole.action",
					data:param,
					dataType:"json",
					success:function(res){
						$("#orgNoRoleWin").modal("hide");
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						loadOrgRoleList("");
					}
				});
			}, function(){
				$("#orgNoRoleWin").modal("hide");
				//取消保存
				layer.closeAll();
			});
	}
}