var total = 0;
var pageSize = 20; //默认每页20条数据
var pageStart = 0;
var first = true;//是否第一次加载
var currdate = (new Date()).Format("yyyy-MM")+"-01";//初始化时间
var dining_mode=3;
$("#begindate").val(currdate);
$(function(){
	var param = {"order.begindate":currdate,"order.dining_mode":dining_mode};
	//加载商户列表
	loadOrderList("init",param);
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
        	var param = {};
        	if(page >1){
        		first = false;
        	}
        	if(!first){
        		loadOrderList("pageQuery",param);
        	}
        }
    });
}
/**
 * 加载门店列表
 * @param type 查询类型  init:初始化查询   pageQuery:分页查询
 * @param param 
 */
function loadOrderList(type,param){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	if(typeof(param) == "undefined" || param == null || param == ""){
		param = {};
		param["page"] = pageStart;
		param["limit"] = pageSize;
	}else{
		param["page"] = pageStart;
		param["limit"] = pageSize;
	}
	param["order.dining_mode"] = dining_mode;
	//清除历史数据
	$("tr[tag='appendOrderTr']").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/order/reserveOrderList.action",
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
						var row = rows[i];
						var id = row.id;
						var ordersn = row.ordersn;
						var weid = row.weid;
						var storeid = row.storeid;
						var from_user = row.from_user;
						var username = row.username;
						var tel = row.tel;
						var meal_time = row.meal_time;
						var remark = row.remark;
						var crtdate = row.crtdate;
						var crtdateStr = formatDate(crtdate);
						var status = row.status;
						var statusStr = "";
						if(status == -1){
							statusStr = "已取消";
						}else if(status == 0){
							statusStr = "已下单";
						}else if(status == 1){
							statusStr = "已确认";
						}else if(status == 2){
							statusStr = "已付款";
						}else if(status == 3){
							statusStr = "配送中";
						}else if(status == 4){
							statusStr = "已到店";
						}
						var appendTr = "";
						appendTr += '<tr tag="appendOrderTr" id="'+id+'" class="animated flipInX">                                                  ';
						appendTr += '	<td>'+(i+1)+'</td>                                           ';
						appendTr += '	<td>'+ordersn+'</td>                          ';
						appendTr += '	<td>'+crtdateStr+'</td>                         ';
						appendTr += '	<td>'+statusStr+'</td>                         ';
						appendTr += '	<td>'+username+'</td>                         ';
						appendTr += '	<td>'+tel+'</td>                                       ';
						appendTr += '	<td>'+meal_time+'</td>                                            ';
						appendTr += '	<td>                                                 ';
						if(status == 0){
							appendTr += '		<button type="button" class="btn btn-warning" onclick="affirmOrder(\''+id+'\')">确认</button>      ';
						}
						if(status != 4){
							appendTr += '		<button type="button" class="btn btn-info" onclick="arriveStore(\''+id+'\')">已到店</button>         ';
							appendTr += '		<button type="button" class="btn btn-success" onclick="editOrder(\''+id+'\')">编辑</button>         ';
							appendTr += '		<button type="button" class="btn btn-danger" onclick="cancleOrder(\''+id+'\')">取消</button>       ';
						}
						appendTr += '	</td>                                                ';
						appendTr += '</tr>                                                 ';
						$("#orderList").append(appendTr);
					}
				}
			}
		},
		error:function(){
			layer.close(load);
		}
	});
}

/**
 * 查看订单详情
 * @param id
 */
function goDetail(id){
	window.location.href = 'orderDetail.jsp?opt=update&oid='+id;
}

/**
 * 根据sid修改门店信息
 * @param sid
 */
function updateShop(sid){
	if(typeof(sid) == "undefined" || sid == ""){
		return;
	}else{
		window.location.href="saveShop.jsp?opt=update&sid="+sid;
	}
}

/**
 * 编辑订单
 * @param id
 * @returns
 */
function editOrder(id){
	window.location.href = "saveOrder.jsp?opt=update&oid="+id;
}

/**
 * 搜索
 */
function search(){
	var begindate = $("#begindate").val();
	var enddate = $("#enddate").val();
	var username = $("#username_search").val();
	var tel = $("#tel_search").val();
	var param = {};
	param["order.begindate"] = begindate;
	param["order.enddate"] = enddate;
	param["order.dining_mode"] = dining_mode;
	param["order.username"] = username;
	param["order.tel"] = tel;
	first = true;
	loadOrderList("init",param);
}

/**
 * 重置查询条件
 * @returns
 */
function clearParam(){
	$("#ordersn").val("");
	$("#begindate").val("");
	$("#enddate").val("");
	$("#username_search").val("");
	$("#tel_search").val("");
	first = true;
	loadOrderList("init",null);
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
 * 取消订单
 * @param id 订单ID
 */
function cancleOrder(id){
	layer.confirm("确定要取消该订单吗？",
			{btn:["确定","取消"]},
			function(){
				layer.closeAll();
				$.ajax({
					type:"POST",
					url:"/selforder/api/order/updateOrderStatus.action",
					data:{"order.id":id,"order.status":-1},
					dataType:"json",
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0 ){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						search();
					}
				});
			},
			function(){
				layer.closeAll();
			}
			);
}

/**
 * 确认订单
 */
function affirmOrder(orderid){
	layer.confirm("确定要【确认】订单吗？",
			{btn:["确定","取消"]},
			function(){
				$.ajax({
					type:"POSt",
					url:"/selforder/api/order/updateOrderStatus.action",
					data:{"order.status":1,"order.id":orderid},
					dataType:"json",
					success:function(res){
						layer.closeAll();
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0 ){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						search();
					}
				});
			},
			function(){
				layer.closeAll();
			});
}

/**
 * 客户已到店
 */
function arriveStore(orderid){
	layer.confirm("确定客户已到店吗？",
			{btn:["确定","取消"]},
			function(){
				$.ajax({
					type:"POSt",
					url:"/selforder/api/order/updateOrderStatus.action",
					data:{"order.status":4,"order.id":orderid},
					dataType:"json",
					success:function(res){
						layer.closeAll();
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0 ){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						search();
					}
				});
			},
			function(){
				layer.closeAll();
			});
}
