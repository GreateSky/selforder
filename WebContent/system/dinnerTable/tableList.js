var total = 0;
var pageSize = 20; //默认每页20条数据
var pageStart = 0;
var first = true;//是否第一次加载
$(function(){
	//加载餐桌列表
	loadTableList("init",null);
	keyEvent();
	loadRoomCommbox();
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
        	var param = {};
        	if(!first){
        		loadTableList("pageQuery",param);
        	}
        }
    });
}
/**
 * 加载门店列表
 * @param type 查询类型  init:初始化查询   pageQuery:分页查询
 * @param param 
 */
function loadTableList(type,param){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	if(typeof(param) == "undefined" || param == null || param == ""){
		param = {};
		param["page"] = pageStart;
		param["limit"] = pageSize;
	}else{
		param["page"] = pageStart;
		param["limit"] = pageSize;
	}
	//清除历史数据
	$("tr[tag='appendTableTr']").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/table/getTableList.action",
		data:param,
		dataType:'json',
		success:function(data){
			layer.close(load);
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
						var id = row.id;
						var weid = row.weid ;
						var storeid = row.storeid ;
						var limit_price = row.limit_price ;
						var title = row.title ;
						var room_name = row.room_name;
						var user_count = row.user_count ;
						var displayorder = row.displayorder ;
						var status = row.status;
						var qrcodeid = row.qrcodeid;
						var service_qrcodeid = row.service_qrcodeid;
						var imgsrc = "/selforder/api/fileutil?method=download&fileid="+qrcodeid;
						var simgsrc = "/selforder/api/fileutil?method=download&fileid="+service_qrcodeid;
						var statusStr = "";
						if(status == "0"){
							statusStr = "空闲";
						}else if(status == "1"){
							statusStr = "已下单";
						}else if(status == "2"){
							statusStr = "已开台";
						}else if(status == "3"){
							statusStr = "已预约";
						}
						tr +='<tr tag="appendTableTr" sid="'+id+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+room_name+'</td>    ';
						tr +='	<td>'+title+'</td>    ';
						tr +='	<td>'+user_count+'</td>                        ';
						tr +='	<td>'+limit_price+'</td>                  ';
						tr +='	<td>'+statusStr+'</td>';
						tr +='	<td>'+displayorder+'</td>';
						tr +='	<td><img src="'+imgsrc+'" width="60px" height="60px"></img></td>';
						tr +='	<td><img src="'+simgsrc+'" width="60px" height="60px"></img></td>';
						tr +='	<td><button type="button" class="btn btn-warning" onclick="updateTable(\''+id+'\')">编辑</button>&nbsp;<button type="button" class="btn btn-danger" onclick="delTable(\''+id+'\')">删除</button></td>                    ';
						tr +='</tr>                                    ';
						$("#tableList").append(tr);
					}
				}
			}
		}
	});
}


/**
 * 搜索
 */
function search(){
	var title = $("#title_search").val();
	var status = $("#status_search").val();
	var room_id = $("#roomSelect").val();
	var param = {};
	param["table.title"] = title;
	param["table.status"] = status;
	param["table.room_id"] = room_id;
	first = true;
	loadTableList("init",param);
}

/**
 * 重置查询条件
 * @returns
 */
function clearParam(){
	$("#title_search").val("");
	$("#status_search").val("");
	$("#roomSelect").val("");
	first = true;
	loadTableList("init",null);
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
 * 删除门店
 * @param sid
 */
function delTable(id){
	layer.confirm("确定要删除该餐桌吗?",
			{btn:["确定","取消"]},
			function(){//确定
				$.ajax({
					type:"POST",
					url:"/selforder/api/table/updateTable.action",
					data:{"table.id":id,"table.deleted":1},
					dataType:"json",
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0 ){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						loadTableList("init",null);
					}
				});
			},
			function(){//取消
				layer.closeAll();
			});
}

/**
 * 保存包厢
 */
function saveRoom(){
	var rname = $("#rname").val();
	if(!checkValue(rname)){
		layer.msg("请输入包厢名称",{icon:5});
		return;
	}
	//验证包厢名称是否已存在
	$.ajax({
		type:"POST",
		url:"/selforder/api/table/roomList.action",
		data:{"table.storeid":sid,"table.room_name":rname},
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode == 0 ){
				layer.msg("该名称已存在请重新输入!",{icon:5});
				return;
			}else{
				//保存包厢信息
				$.ajax({
					type:"POST",
					url:"/selforder/api/table/insertRoom.action",
					data:{"table.room_name":rname,"table.storeid":sid},
					dataType:"json",
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0 ){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						loadRoomList();
					}
				});
			}
		}
	});
}

/**
 * 加载已创建包厢
 */
function loadRoomList(){
	$("tr[tag='appendRoomTr']").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/table/roomList.action",
		data:{"table.storeid":sid},
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				var rows = res.data;
				if(rows.length > 0){
					for(var i=0;i<rows.length;i++){
						var tr = "";
						var row = rows[i];
						var room_id = row.room_id;
						var room_name = row.room_name;
						var tabnum = row.tabnum;
						tr +='<tr tag="appendRoomTr" room_id="'+room_id+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+room_name+'</td>    ';
						tr +='	<td>'+tabnum+'</td>    ';
						tr +='	<td><button type="button" class="btn btn-danger" onclick="delRoom(\''+room_id+'\')">删除</button></td>                    ';
						tr +='</tr>                                    ';
						$("#roomList").append(tr);
					}
				}
			}
		}
	});
}

/**
 * 显示已创建包厢
 */
function showRoom(){
	loadRoomList();
	$("#createRoom").modal("show");
}

/**
 * 删除包厢
 * @param room_id
 */
function delRoom(room_id){
	layer.confirm("确定要删除该包厢吗？包厢中的餐桌将取消于该包厢的关系。",
			{btn: ['确定','取消']},
			function(){
				$.ajax({
					type:"POST",
					url:"/selforder/api/table/updateRoom.action",
					data:{"table.room_id":room_id,"table.storeid":sid,"table.deleted":1},
					dataType:"json",
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0 ){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						loadRoomList();
					}
				});
			},
			function(){
				layer.closeAll();
			});
}

/**
 * 更新餐桌信息
 * @param id 餐桌ID
 */
function updateTable(id){
	window.location.href = "saveTable.jsp?sid="+sid+"&opt=update&id="+id;
}

/**
 * 加载包厢
 */
function loadRoomCommbox(){
	$("#roomSelect option").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/table/roomList.action",
		data:{"table.storeid":sid},
		dataType:"json",
		async:false,
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				var all = 'option = "<option value="">全部</option>"';
				$("#roomSelect").append(all);
				var rows = res.data;
				if(rows.length > 0){
					for(var i=0;i<rows.length;i++){
						var row = rows[i];
						var room_id = row.room_id;
						var room_name = row.room_name;
						var option= "";
						option = "<option value='"+room_id+"'>"+room_name+"</option>";
						$("#roomSelect").append(option);
					}
				}
			}
		}
	});
}
