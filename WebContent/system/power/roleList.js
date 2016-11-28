var total = 0;
var pageSize = 20; //默认每页20条数据
var pageStart = 0;
var first = true;//是否第一次加载
$(function(){
	//加载商户列表
	roleList("init",null);
	keyEvent();
});

/**
 * 分页公共插件
 * @returns
 */
function pageOption(paginationid,totalpage){
	$("#"+paginationid).twbsPagination("destroy");
	$('#'+paginationid).twbsPagination({
        totalPages: totalpage,
        visiblePages: 10,
        first:"首页",
        prev:"上一页",
        next:"下一页",
        last:"尾页",
        onPageClick: function (event, page) {
            var tag = event.target;
            var data_optionStr = $(tag).attr("data-option");
            data_optionStr = "("+data_optionStr+")";
            data_option = eval(data_optionStr);
            pageStart = page;
            var temp_pageSize = data_option.pageSize;
            if(checkValue(temp_pageSize)){
            	pageSize = temp_pageSize;
            }else{
            	pageSize = 20;
            }
            var rname = $("#rname_search").val();
        	var rcode = $("#rcode_search").val();
        	var param = {};
        	param["role.rname"] = rname;
        	param["role.rcode"] = rcode;
        	if(page >1){
        		first = false;
        	}
        	if(!first){
        		roleList("pageQuery",param);
        	}
        }
    });
}

/**
 * 加载商户列表
 * @param type 查询类型  init:初始化查询   pageQuery:分页查询
 * @param param 
 */
function roleList(type,param){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	if(typeof(param) == "undefined" || param == null || param == ""){
		param = {};
		param["page"] = pageStart;
		param["limit"] = pageSize;
	}else{
		param["page"] = pageStart;
		param["limit"] = pageSize;
	}
	$.ajax({
		type:"POST",
		url:"/selforder/api/power/getRoleList.action",
		data:param,
		dataType:'json',
		success:function(data){
			layer.close(load);
			first = false;
			//清除历史数据
			$("tr[tag='append']").remove();
			if(typeof(data) == "undefined" || data == ""){
				return;
			}else{
				total = data.total;
				var totalpage = Math.ceil(total/pageSize);
				if("init" == type){
					pageOption("pagination",totalpage);
				}
				var rows = data.rows;
				if(rows.length > 0){
					for(var i=0;i<rows.length;i++){
						var tr = "";
						var row = rows[i];
						var rid = row.rid;
						var rname  = row.rname;
						var rcode = row.rcode;
						var remark = row.remark;
						var resourceid = row.resourceid;
						var resourcename = row.resourcename;
						tr +='<tr tag="append" rid="'+rid+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+rname+'</td>    ';
						tr +='	<td>'+rcode+'</td>                        ';
						tr +='	<td resourceid="'+resourceid+'">'+resourcename+'</td>                  ';
						tr +='	<td><button type="button" class="btn btn-warning" onclick="updateRole(\''+rid+'\')">修改</button>&nbsp;<button type="button" class="btn btn-danger" onclick="delRole(\''+rid+'\')">删除</button></td>                    ';
						tr +='</tr>                                    ';
						$("#roleList").append(tr);
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
 * 更新角色
 * @param role_id
 */
function updateRole(role_id){
	window.location.href="saveRole.jsp?rid="+role_id+"&opt=update";
}

/**
 * 提交按钮
 */
function saveBtn(){
	var openType = $("#resourceWin").attr("openType");
	if("add" == openType){
		addResource();
	}else if("update" == openType){
		var rid = $("#resourceWin").attr("rid");
		updateResource(rid);
	}
}

//新增商户
function addResource(){
	var param = {};
	var load = layer.load(2);
	$(".modal *[name *='resource.']").each(function(i){
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
				$("#resourceWin").modal('hide');
				roleList("init",null);
			}, 1000);
		}
	});
}

/**
 * 获取资源基本信息
 */
function getResourceInfo(rid){
	var getinfo_load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	if(typeof(rid) == "undefined" || rid == ""){
		layer.alert("获取资源基本信息参数异常！", {icon: 5});
		return;
	}else{
		$.ajax({
			type:"POST",
			url:"/selforder/api/power/getResourceInfo.action",
			data:{"resource.rid":rid},
			dataType:"json",
			success:function(data){
				var obj = data.data;
				layer.close(getinfo_load);
				if(typeof(obj) == "undefined" || obj == ""){
					layer.alert("获取资源基本信息异常！", {icon: 5});
					return;
				}else{
					var rname = obj.rname;
					var rurl = obj.rurl;
					var remark = obj.remark;
					$("#rname").val(rname);
					$("#rurl").val(rurl);
					$("#remark").val(remark);
					$("#resourceWin").modal('show');
				}
			}
		});
	}
}

/**
 * 更新资源
 */
function updateResource(rid){
	//组装参数
	var param = {};
	var load = layer.load(2);
	$(".modal *[name *='resource.']").each(function(i){
		var name = $(this).attr("name");
		var value = $(this).val();
		param[name] = value;
	});
	param["resource.rid"] = rid;
	//发送请求
	$.ajax({
		type:"POST",
		url:"/selforder/api/power/updateResource.action",
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
				$("#resourceWin").modal('hide');
				roleList("init",null);
			}, 1000);
		}
	});
}

/**
 * 删除资源
 * @param rid
 */
function delResource(rid){
	//询问框
	layer.confirm('确定要删除吗？', {
	  btn: ['确定','取消'] //按钮
	},
	function(){
		var load = layer.load(2);
		//组装参数
		var param = {};
		param["resource.rid"] = rid;
		param["resource.deleted"] = 1;
		//发送请求
		$.ajax({
			type:"POST",
			url:"/selforder/api/power/updateResource.action",
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
				$("#resourceWin").modal('hide');
				roleList("init",null);
			}
		});
	},
	function(){
		layer.closeAll();
	});
}

/**
 * 搜索
 */
function search(){
	var rname = $("#rname_search").val();
	var rcode = $("#rcode_search").val();
	var param = {};
	param["role.rname"] = rname;
	param["role.rcode"] = rcode;
	first = true;
	roleList("init",param);
}

/**
 * 重置查询条件
 * @returns
 */
function clearParam(){
	$("#rname_search").val("");
	$("#rcode_search").val("");
	first = true;
	roleList("init",null);
}

/**
 * 注册回车事件
 */
function keyEvent(){
	$("*[id *= '_search']").each(function(i){
		$(this).keydown(function (event){
			var keyCode=event.keyCode ? event.keyCode:event.which?event.which:event.charCode;//解决浏览器之间的差异问题 
			if(keyCode==13){ 
				search(); 
			} 
		});
	});
}

/**
 * 删除权限
 * @param rid
 */
function delRole(rid){
	if(!checkValue(rid)){
		layer.msg("操作参数异常,请刷新后重试!",{icon:5});
		return;
	}else{
		layer.confirm("确定要删除吗?",
				{btn:["确定","取消"]},
				function(){//确定
					//组装参数
					var param = {};
					param["role.rid"] = rid;
					//发送请求
					$.ajax({
						type:"POST",
						url:"/selforder/api/power/delRole.action",
						data:param,
						dataType:'json',
						success:function(data){
							//处理返回结果
							var retCode = data.retCode;
							var message = data.message;
							if(retCode < 0){
								layer.msg(message, {icon: 5});
							}else{
								layer.msg(message, {icon: 6});
							}
							roleList("init",null);
						}
					});
				},
				function(){//取消
					layer.closeAll();
				});
				 
	}
}

