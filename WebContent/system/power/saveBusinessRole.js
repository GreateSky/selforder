$(function(){
	//加载商户已开通权限列表
	loadBusinessRoleList();
});

/**
 * 查询商户已授权权限
 */
function loadBusinessRoleList(){
	if(!checkValue(bid)){
		layer.msg("请重新选择要查看的商户!",{icon:5});
		return;
	}else{
		var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
		$.ajax({
			type:"POST",
			url:"/selforder/api/power/getBusinessRoleInfo.action",
			data:{"business.bid":bid},
			dataType:'json',
			success:function(res){
				layer.close(load);
				//清除历史数据
				$("tr[tag='append_busRole']").remove();
				var retCode = res.retCode;
				var message = res.message;
				
				if(retCode < 0){
					layer.msg(message,{icon:5});
					return;
				}else{
					var rows = res.data;
					if(rows.length > 0){
						//设置商户基本信息
						var business = rows[0];
						var bcode = business.bcode;
						var bname = business.bname;
						var phone = business.phone;
						var email = business.email;
						var legaler = business.legaler;
						$("#business").val(business);
						$("#bcode").val(bcode);
						$("#bname").val(bname);
						$("#phone").val(phone);
						$("#email").val(email);
						$("#legaler").val(legaler);
						for(var i=0;i<rows.length;i++){
							//组装已授权权限
							var tr = "";
							var row = rows[i];
							var rbid = row.rbid;
							var rcode  = row.rcode;
							var rname = row.rname;
							var rid = row.rid;
							tr +='<tr tag="append_busRole" rbid="'+rbid+'" class="animated flipInX">                                     ';
							tr +='	<td>'+(i+1)+'</td>                             ';
							tr +='	<td>'+rname+'</td>                        ';
							tr +='	<td>'+rcode+'</td>    ';
							tr +='	<td><button type="button" class="btn btn-danger" onclick="deletedRole(\''+rbid+'\',\''+bid+'\',\''+rid+'\')">删除</button></td>                    ';
							tr +='</tr>                                    ';
							$("#businessRoleList").append(tr);
						}
					}
				}
			},
			fail:function(){
				layer.close(load);
			}
		});
	}
}

/**
 * 删除已授权权限
 * @param rbid 商户与权限关联ID
 */
function deletedRole(rbid,bid,rid){
	if(!checkValue(rbid)){
		layer.msg("参数异常，请刷新后重试!",{icon:5});
	}else if(!checkValue(bid)){
		layer.msg("参数异常，请刷新后重试!",{icon:5});
	}else if(!checkValue(rid)){
		layer.msg("参数异常，请刷新后重试!",{icon:5});
	}else{
		layer.confirm("确定要删除权限吗？该商户下所有已分配该权限的部门也将同步删除该权限",
				{btn:["确定","取消"]},
				function(){//确定
					var param = {"business.rbid":rbid,
							"business.bid":bid,
							"business.rid":rid,
					};
					$.ajax({
						type:"POST",
						url:"/selforder/api/power/deletedBusRoleRef.action",
						data:param,
						dataType:"json",
						success:function(res){
							var retCode = res.retCode;
							var message = res.message;
							if(retCode < 0){
								layer.msg(message,{icon:5});
							}else{
								layer.msg(message,{icon:6});
							}
							//加载商户已开通权限列表
							loadBusinessRoleList();
						},
						fail:function(){
							layer.msg("网络异常请重试 !",{icon:5});
						}
					});
				},
				function(){//取消
					layer.closeAll();
				});
	}
}

/**
 * 加载商户未授权权限列表
 */
function loadBusNoRoleList(){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	$.ajax({
		type:"POST",
		url:"/selforder/api/power/getBusNoRoleList.action",
		data:{"business.bid":bid},
		dataType:"json",
		success:function(res){
			layer.close(load);
			//清除历史数据
			$("tr[tag='append_busNoRole']").remove();
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0){
				layer.msg(message,{icon:5});
				return;
			}else{
				var rows = res.data;
				for(var i=0;i<rows.length;i++){
					//组装数据
					var tr = "";
					var row = rows[i];
					var rcode  = row.rcode;
					var rname = row.rname;
					var rid = row.rid;
					tr +='<tr tag="append_busNoRole" rid="'+rid+'" class="animated flipInX">                                     ';
					tr +='	<td><input type="checkbox"></input></td>';
					tr +='	<td>'+(i+1)+'</td>                             ';
					tr +='	<td>'+rname+'</td>                        ';
					tr +='	<td>'+rcode+'</td>    ';
					tr +='</tr>                                    ';
					$("#busNoRoleList").append(tr);
				}
				$("#busNoRoleWin").modal("show");
			}
		},
		fail:function(){
			layer.msg("网络异常,请刷新后重试!",{icon:5});
		}
	});
}

/**
 * 保存已选择的权限
 */
function saveSelectRole(){
	var selectNum = -1;
	selectNum = $("#busNoRoleList input[type='checkbox']:checked").length;
	if(selectNum <=0 ){
		layer.msg("请选择需要添加的权限!", {icon: 5});
		return;
	}else{
		$("#busNoRoleWin").modal("hide");
		//组装参数
		var selectRoleArray = {};
		$("#busNoRoleList input[type='checkbox']:checked").each(function(i){
			var tr = $(this).parent().parent();
			var rid =  $(tr).attr("rid");
			var select = {"business.bid":bid,"business.rid":rid};
			selectRoleArray["businessList["+i+"].rid"] = rid;
			selectRoleArray["businessList["+i+"].bid"] = bid;
		});
		//保存数据
		$.ajax({
			type:"POST",
			url:"/selforder/api/power/insertBusRoleRef.action",
			data:selectRoleArray,
			dataType:"json",
			success:function(res){
				var retCode = res.retCode;
				var message = res.message;
				if(retCode < 0){
					layer.msg(message,{icon:5});
				}else{
					layer.msg(message,{icon:6});
				}
				//加载商户已开通权限列表
				loadBusinessRoleList();
			},
			fail:function(){
				layer.msg("网络异常请重试 !",{icon:5});
			}
		});
	}
}