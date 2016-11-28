var total = 0;
var pageSize = 20; //默认每页20条数据
var pageStart = 0;
var first = true;//是否第一次加载
$(function(){
	//加载商户列表
	loadResourceList("init",null);
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
        	var rurl = $("#rurl_search").val();
        	var param = {};
        	param["resource.rname"] = rname;
        	param["resource.rurl"] = rurl;
        	if(page >1){
        		first = false;
        	}
        	if(!first){
        		loadResourceList("pageQuery",param);
        	}
        	
        }
    });
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
		param["page"] = pageStart;
		param["limit"] = pageSize;
	}else{
		param["page"] = pageStart;
		param["limit"] = pageSize;
	}
	$.ajax({
		type:"POST",
		url:"/selforder/api/power/getResourceList.action",
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
						var rurl = row.rurl;
						var remark = row.remark;
						tr +='<tr tag="append" rid="'+rid+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+rname+'</td>    ';
						tr +='	<td>'+rurl+'</td>                        ';
						tr +='	<td>'+remark+'</td>                  ';
						tr +='	<td><button type="button" class="btn btn-danger" onclick="openResourceWin(\'update\',\''+rid+'\')">修改</button>&nbsp;<button type="button" class="btn btn-warning" onclick="delResource(\''+rid+'\')">删除</button></td>                    ';
						tr +='</tr>                                    ';
						$("#resourceList").append(tr);
					}
				}
			}
		},
		fail:function(){
			layer.close(load);
		},
		error:function(){
			layer.close(load);
		}
	});
}

/**
 * 打开资源详情窗口
 * @param type 打开类型  add，update
 * @param rid 资源ID
 */
function openResourceWin(type,rid){
	if('add' == type){
		$("#resourceWin").attr("openType","add");
		$("#rname").val("");
		$("#rurl").val("");
		$("#remark").val("");
		$('#resourceWin').modal('show');
	}else if("update" == type){
		$("#resourceWin").attr("openType","update");
		$("#resourceWin").attr("rid",rid);
		getResourceInfo(rid);
	}
}

/**
 * 提交按钮
 */
function saveBtn(){
	if(!checkValueWithInfo($("#rname").val(),"资源名称不能为空！")) return;
	if(!checkValueWithInfo($("#rurl").val(),"资源URL不能为空！")) return;
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
			$("#resourceWin").modal('hide');
			loadResourceList("init",null);
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
			$("#resourceWin").modal('hide');
			loadResourceList("init",null);
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
	  btn: ['取消','确定'] //按钮
	}, function(){
		layer.closeAll();
	}, function(){
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
				loadResourceList("init",null);
			}
		});
	});
}

/**
 * 搜索
 */
var search = function(){
	var rname = $("#rname_search").val();
	var rurl = $("#rurl_search").val();
	var param = {};
	param["resource.rname"] = rname;
	param["resource.rurl"] = rurl;
	first = true;
	loadResourceList("init",param);
}

/**
 * 重置查询条件
 * @returns
 */
function clearParam(){
	$("#rname_search").val("");
	$("#rurl_search").val("");
	first = true;
	loadResourceList("init",null);
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

