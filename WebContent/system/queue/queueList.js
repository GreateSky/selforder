$(function(){
	loadQueueList(null);
});

/**
 * 加载队列列表
 * @param param
 */
function loadQueueList(param){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	$("tr[tag='appendQueueTr']").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/queue/getQueueSettingList.action",
		data:param,
		dataType:"json",
		success:function(res){
			layer.close(load);
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				var rows = res.data;
				if(typeof(rows) != "undefined" && rows.length>0){
					for(var i=0;i<rows.length;i++){
						var row  = rows[i];
						var title = row.title;
						var status = row.status;
						var statusStr = status == 1?"启用":"暂停";
						var notify_number = row.notify_number;
						var waitCount = row.waitCount;
						var nextNum = row.nextNum;
						var id = row.id;
						var tr = "";
						tr += '<tr tag="appendQueueTr" class="animated flipInX" id="'+id+'">                                                         ';
						tr += '	<td>'+(i+1)+'</td>                                                  ';
						tr += '	<td>'+title+'</td>                                            ';
						tr += '	<td>'+statusStr+'</td>                                                  ';
						tr += '	<td>'+notify_number+'</td>                                                  ';
						tr += '	<td>'+waitCount+'</td>                                                 ';
						tr += '	<td>'+nextNum+'</td>                                             ';
						tr += '	<td>                                                        ';
						//只有启用状态的队列开启排队和叫号功能
						if(status ==1){
							tr += '		<button type="button" class="btn btn-warning" onclick="callNextQueueNum(\''+id+'\',\''+waitCount+'\')">叫号</button>';
							tr += '		<button type="button" class="btn btn-info" onclick="applyQueueNum(\''+id+'\')">排队</button>  ';
						}
						tr += '		<button type="button" class="btn btn-info" onclick="editQueueSetting(\''+id+'\')">编辑</button>  ';
						tr += '		<button type="button" class="btn btn-danger" onclick="delQueue(\''+id+'\')">删除</button>  ';
						tr += '	</td>                                                       ';
						tr += '</tr>                                                        ';
						$("#queueList").append(tr);
					}
				}
			}
		}
	});
}

/**
 * 更新队列设置
 * @param id 队列ID
 */
function editQueueSetting(id){
	window.location.href = 'saveQueueSetting.jsp?opt=update&id='+id;
}
/**
 * 申请排号
 * @param queueid  队列id
 */
function applyQueueNum(queueid){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	$.ajax({
		type:"POST",
		url:"/selforder/api/queue/applyQueueNum.action",
		data:{"queue.queueid":queueid},
		dataType:"json",
		success:function(res){
			layer.close(load);
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				var queueNum = res.data.QUEUENUM;
				var pervNum = res.data.PERVNUM;
				var queueType = res.data.QUEUETYPE;
				$("#queueNum").html(queueNum);
				$("#pervNum").html(pervNum+"人");
				$("#queueType").html(queueType);
				loadQueueList(null);
				$("#applyQueueNum").modal('show');
			}
		}
	});
}

/**
 * 叫号
 * @param queueid 队列ID
 * @param waitCount 当前排队人数
 */
function callNextQueueNum(queueid,waitCount){
	if(waitCount <=0){
		layer.msg("当前无排队人员，无需叫号！",{icon:6});
		return;
	}
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	$.ajax({
		type:"POST",
		url:"/selforder/api/queue/callNextQueueNum.action",
		data:{"queue.queueid":queueid},
		dataType:"json",
		success:function(res){
			layer.close(load);
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				var currnNum = res.data.currnNum;
				var prefix = res.data.PREFIX;
				var id = res.data.ID;
				var title= res.data.TITLE;
				var applyType = res.data.APPLYTYPE;
				$("#call_queueNum").html(prefix+currnNum);
				$("#call_queueType").html(title);
				$("#call_applyType").html(applyType);
				$("#call_id").val(id);
				$("#callNextQueueNumWin").modal('show');
			}
		}
	});
}

/**
 * 更新排号状态
 * @param status 状态
 */
function updateStatus(status){
	var id = $("#call_id").val();
	layer.confirm("确定要修改排号的状态吗?",
			{btn:["确定","取消"]},
			function(){
				layer.closeAll();
				$.ajax({
					type:"POST",
					url:"/selforder/api/queue/updateQueue.action",
					data:{"queue.id":id,"queue.status":status},
					dataType:"json",
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0 ){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						$("#callNextQueueNumWin").modal('hide');
						loadQueueList(null);
					}
				});
			},
			function(){
				layer.closeAll();
			}
	);
}

/**
 * 删除队列
 * @param id 队列ID
 */
function delQueue(id){
	layer.confirm("确定要删除该队列吗？删除后该队列下的排号将被取消",
			{btn:["确定","取消"]},
			function(){
				layer.closeAll();
				$.ajax({
					type:"POST",
					url:"/selforder/api/queue/updateQueueSetting.action",
					data:{"queueSetting.id":id,"queueSetting.deleted":1},
					dataType:"json",
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						loadQueueList(null);
					}
				});
			},
			function(){
				layer.closeAll();
			});
}