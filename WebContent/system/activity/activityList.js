var total = 0;
var pageSize = 20; //默认每页20条数据
var pageStart = 0;
var first = true;//是否第一次加载
$(function(){
	//加载活动列表
	loadActivityList("init",null);
	keyEvent();
	controlBtn();
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
        		loadActivityList("pageQuery",param);
        	}
        }
    });
}
/**
 * 加载活动列表
 * @param queueType 查询类型  init:初始化查询   pageQuery:分页查询
 * @param param 
 */
function loadActivityList(queueType,param){
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
	$("tr[tag='appendActivityTr']").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/activity/getActivityList.action",
		data:param,
		dataType:'json',
		success:function(data){
			layer.close(load);
			if(typeof(data) == "undefined" || data == ""){
				return;
			}else{
				total = data.total;
				var totalpage = Math.ceil(total/pageSize);
				if("init" == queueType){
					pageOption("pagination",totalpage);
				}
				var rows = data.rows;
				if(rows.length > 0){
					for(var i=0;i<rows.length;i++){
						var tr = "";
						var row = rows[i];
						var id = row.id;
						var weid = row.weid;
						var title = row.title;
						var url = row.url;
						var status = row.status;
						var type = row.type;
						var begindate = formatDate(row.begindate);
						var enddate = formatDate(row.enddate);
						var discount = row.discount;
						var leastcost = row.leastcost;
						if(status == 0 ){
							status = "未开启";
						}else if(status == 1 ){
							status = "已开启";
						}else{
							status = "已结束";
						}
						
						if(type == 1 ){
							type = "折扣";
						}else{
							type = "优惠";
						}
						tr +='<tr tag="appendActivityTr" id="'+id+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+title+'</td>    ';
						tr +='	<td>'+begindate+'</td>                        ';
						tr +='	<td>'+enddate+'</td>                  ';
						tr +='	<td>'+url+'</td>';
						tr +='	<td>'+type+'</td>                    ';
						tr +='	<td>'+status+'</td>                    ';
						tr +='	<td>'+discount+'</td>                    ';
						tr +='	<td>'+leastcost+'</td>                    ';
						tr +='	<td>'+0+'</td>                    ';
						tr +='	<td>'+0+'</td>                    ';
						tr +='	<td><button role="btn-shop-update" type="button" class="btn btn-warning" onclick="updateActivity(\''+id+'\')">编辑</button>&nbsp;<button role="btn-shop-remove" type="button" class="btn btn-danger" onclick="delActivity(\''+id+'\')">删除</button></td>                    ';
						tr +='</tr>                                    ';
						$("#activityList").append(tr);
					}
					controlBtn();
				}
			}
		},
		error:function(){
			layer.close(load);
		}
	});
}

/**
 * 修改活动信息
 * @param id
 */
function updateActivity(id){
	if(!checkValueWithInfo(id,'查询参数异常，请刷新后重试!')) return;
	window.location.href = "saveActivity.jsp?opt=update&id="+id;
}

/**
 * 搜索
 */
function search(){
	var title = $("#search_title").val();
	var type = $("#search_type").val();
	var status = $("#search_status").val();
	var param = {};
	param["activity.title"] = title;
	param["activity.type"] = type;
	param["activity.status"] = status;
	first = true;
	loadActivityList("init",param);
}

/**
 * 重置查询条件
 * @returns
 */
function clearParam(){
	$("#search_title").val("");
	$("#search_type").val("");
	$("#search_status").val("");
	first = true;
	loadActivityList("init",null);
}

/**
 * 注册回车事件
 */
function keyEvent(){
	$("*[id *= 'search']").each(function(i){
		$(this).keydown(function (event){
			var keyCode=event.keyCode ? event.keyCode:event.which?event.which:event.charCode;//解决浏览器之间的差异问题 
			if(keyCode==13){ 
				search(); 
			} 
		});
	});
}

/**
 * 删除活动
 * @param id
 */
function delActivity(id){
	layer.confirm("确定要删除该活动吗?",
			{btn:["确定","取消"]},
			function(){//确定
				$.ajax({
					type:"POST",
					url:"/selforder/api/activity/updateActivity.action",
					data:{"activity.id":id,"activity.deleted":"1"},
					dataType:"json",
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0 ){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						loadActivityList("init",null);
					}
				});
			},
			function(){//取消
				layer.closeAll();
			});
}

