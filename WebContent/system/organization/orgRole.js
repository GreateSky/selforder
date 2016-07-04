/**
 * 加载部门已关联权限
 */
function loadOrgRoleList(param){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	if(typeof(param) == "undefined" || param == null || param == ""){
		param = {};
	}else{
		param["employee.oid"] = curr_oid;
	}
	$.ajax({
		type:"POST",
		url:"/selforder/api/organization/getEmpOrgList.action",
		data:param,
		dataType:'json',
		success:function(res){
			layer.close(load);
			//清除历史数据
			$("tr[tag='append']").remove();
			var rows = res.data;
			if(rows){
				for(var i=0;i<rows.length;i++){
					var  row = rows[i];
					var orid = row.orid;
					var rid = row.rid;
					var rcode = row.rcode;
					var rname = row.rname;
					var tr = "";
					tr +='<tr tag="append" orid="'+orid+'" class="animated flipInX">                                     ';
					tr +='	<td>'+(i+1)+'</td>                             ';
					tr +='	<td>'+rcode+'</td>    ';
					tr +='	<td>'+empcode+'</td>                        ';
					tr +='	<td>'+rname+'</td>                  ';
					tr +='	<td><button type="button" class="btn btn-warning" onclick="delOrgRoleRef(\''+orid+'\')">删除</button></td>                    ';
					tr +='</tr>                                    ';
					$("#orgRoleList").append(tr);
				}
			}
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
	}else{
		param["employee.oid"] = curr_oid;
	}
	$.ajax({
		type:"POST",
		url:"/selforder/api/organization/getEmpOrgList.action",
		data:param,
		dataType:'json',
		success:function(res){
			layer.close(load);
			//清除历史数据
			$("tr[tag='append']").remove();
			var rows = res.data;
			if(rows){
				for(var i=0;i<rows.length;i++){
					var  row = rows[i];
					var orid = row.orid;
					var rid = row.rid;
					var rcode = row.rcode;
					var rname = row.rname;
					var tr = "";
					tr +='<tr tag="append" orid="'+orid+'" class="animated flipInX">                                     ';
					tr +='	<td>'+(i+1)+'</td>                             ';
					tr +='	<td>'+rcode+'</td>    ';
					tr +='	<td>'+empcode+'</td>                        ';
					tr +='	<td>'+rname+'</td>                  ';
					tr +='	<td><button type="button" class="btn btn-warning" onclick="delOrgRoleRef(\''+orid+'\')">删除</button></td>                    ';
					tr +='</tr>                                    ';
					$("#orgRoleList").append(tr);
				}
			}
		},
		fail:function(){
			layer.close(load);
		}
	});
}