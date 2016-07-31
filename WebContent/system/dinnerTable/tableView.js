$(function(){
	loadRoomCommbox();//加载包厢
	getTableList();//获取所有餐桌情况
	keyEvent();//注册回车事件
});

/**
 * 获取所有餐桌情况
 */
function getTableList(param){
	if(!param){
		param = {};
	}
	param["table.storeid"] = sid;
	$("#tablelist").children().remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/table/allTableList.action",
		data:param,
		dataType:"json",
		async:false,
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				var rows = res.data;
				if(rows.length > 0){
					for(var i=0;i<rows.length;i++){
						var row = rows[i];
						var id = row.id;
						var title = row.title;
						var status = row.status;
						var option = "";
						var styleClass = "table";
						var statusStr = "";
						if(status == 0){
							option += '<option selected="selected" >空闲</option>';
							option += '<option >已下单</option>                  ';
							option += '<option >已开台</option>                  ';
							statusStr = "空闲";
						}else if(status == 1){
							option += '<option >空闲</option>';
							option += '<option selected="selected" >已下单</option>                  ';
							option += '<option >已开台</option>                  ';
							styleClass += "  orderTable";
							statusStr = "已下单";
						}else if(status == 2){
							option += '<option >空闲</option>';
							option += '<option >已下单</option>                  ';
							option += '<option selected="selected" >已开台</option>                  ';
							styleClass += "  beginTable";
							statusStr = "已开台";
						}
						var li = "";
						if(i==0){
							li += '<li style="clear:both">                           ';//第一条清除浮动样式
						}else{
							li += '<li >                           ';
						}
						li += '	<div class="'+styleClass+'">                             ';
						li += '		<p>'+statusStr+'</p>                                   ';
						li += '		<div>                                         ';
						li += '			<span>'+title+':</span>                         ';
						li += '			<select onchange="changeStatus(\''+id+'\',this)">'+option+'</select>';
						li += '		</div>                                        ';
						li += '	</div>                                          ';
						li += '</li>                                             ';
						$("#tablelist").append(li);
					}
				}
			}
		}
	});
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

/**
 * 餐桌搜索
 */
function search(){
	var title = $("#title_search").val();
	var status = $("#status_search").val();
	var roomid = $("#roomSelect").val();
	var param = {};
	param["table.title"] = title;
	param["table.status"] = status;
	param["table.room_id"] = roomid;
	getTableList(param);
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
 * 重置查询条件
 * @returns
 */
function clearParam(){
	$("#title_search").val("");
	$("#status_search").val("");
	$("#roomSelect").val("");
	getTableList();
}

/**
 * 修改餐桌状态
 */
function changeStatus(tid,e){
	var str = $(e).val();
	var status = null;
	if(str == "空闲"){
		status =-1;
	}else if(str == "已下单"){
		status =1;
	}else if(str == "已开台"){
		status =2;
	}
	layer.confirm("确定要将餐桌修改为【"+str+"】状态吗?",
			{btn:["确定","取消"]},
			function(){//确定
				var param = {};
				param["table.storeid"] = sid;
				param["table.id"] = tid;
				param["table.status"] = status;
				$.ajax({
					type:"POST",
					url:"/selforder/api/table/updateTable.action",
					data:param,
					dataType:"json",
					async:false,
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0 ){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						getTableList();
					}
				});
			},
			function(){//取消
				layer.closeAll();
			});
}