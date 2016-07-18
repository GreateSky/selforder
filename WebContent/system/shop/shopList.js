var total = 0;
var pageSize = 20; //默认每页20条数据
var pageStart = 0;
var first = true;//是否第一次加载
$(function(){
	//加载商户列表
	loadShopList("init",null);
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
        	if(!first){
        		roleList("pageQuery",param);
        	}
        }
    });
}
/**
 * 加载门店列表
 * @param type 查询类型  init:初始化查询   pageQuery:分页查询
 * @param param 
 */
function loadShopList(type,param){
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
	$("tr[tag='appendShopTr']").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/shop/getShopList.action",
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
						var weid = row.weid;
						var title = row.title;
						var tel = row.tel;
						var location_p = row.location_p;
						var location_c = row.location_c;
						var location_a = row.location_a;
						var address = row.address;
						address = location_p + location_c + location_a + address;
						var is_show = row.is_show;
						var is_meal = row.is_meal;
						var is_delivery = row.is_delivery;
						var is_reservation = row.is_reservation;
						var is_queue = row.is_queue;
						var begintime = row.begintime;
						var endtime = row.endtime;
						var delivery_radius = row.delivery_radius;
						var begintimeStr = initTime(begintime);
						var endtimeStr = initTime(endtime);
						var hours = begintimeStr + "-" + endtimeStr;
						if(is_show == 1 ){
							is_show = "是";
						}else{
							is_show = "否";
						}
						if(is_meal == 1 ){
							is_meal = "是";
						}else{
							is_meal = "否";
						}
						if(is_delivery == 1 ){
							is_delivery = "是";
						}else{
							is_delivery = "否";
						}
						if(is_reservation == 1 ){
							is_reservation = "是";
						}else{
							is_reservation = "否";
						}
						if(is_queue == 1 ){
							is_queue = "是";
						}else{
							is_queue = "否";
						}
						
						tr +='<tr tag="appendShopTr" sid="'+id+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+title+'</td>    ';
						tr +='	<td>'+tel+'</td>                        ';
						tr +='	<td>'+hours+'</td>                  ';
						tr +='	<td>'+is_meal+'</td>';
						tr +='	<td>'+is_delivery+'</td>                    ';
						tr +='	<td>'+is_reservation+'</td>                    ';
						tr +='	<td>'+is_show+'</td>                    ';
						tr +='	<td>'+is_queue+'</td>                    ';
						tr +='	<td>'+delivery_radius+'Km</td>                    ';
						tr +='	<td>'+address+'</td>                    ';
						tr +='	<td><button type="button" class="btn btn-warning" onclick="updateShop(\''+id+'\')">编辑</button>&nbsp;<button type="button" class="btn btn-danger" onclick="delShop(\''+id+'\')">删除</button></td>                    ';
						tr +='</tr>                                    ';
						$("#shoplist").append(tr);
					}
				}
			}
		}
	});
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
 * 搜索
 */
function search(){
	var title = $("#title_search").val();
	var tel = $("#tel_search").val();
	var address = $("#address_search").val();
	var param = {};
	param["shop.title"] = title;
	param["shop.tel"] = tel;
	param["shop.address"] = address;
	first = true;
	loadShopList("init",param);
}

/**
 * 重置查询条件
 * @returns
 */
function clearParam(){
	$("#title_search").val("");
	$("#tel_search").val("");
	$("#address_search").val("");
	first = true;
	loadShopList("init",null);
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
function delShop(sid){
	layer.confirm("确定要删除该门店吗?",
			{btn:["确定","取消"]},
			function(){//确定
				$.ajax({
					type:"POST",
					url:"/selforder/api/shop/delShop.action",
					data:{"shop.id":sid},
					dataType:"json",
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0 ){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						loadShopList("init",null);
					}
				});
			},
			function(){//取消
				layer.closeAll();
			});
}

/**
 * 初始化时间
 * @param value 值
 */
function initTime(value){
	var time ="";
	var intvalue = parseInt(value);
	if(intvalue){
		switch(intvalue){
			case 0:
				time = "00:00";
				break;
			case 1:
				time = "01:00";
				break;
			case 2:
				time = "02:00";
				break;	
			case 3:
				time = "04:00";
				break;
			case 4:
				time = "04:00";
				break;
			case 5:
				time = "05:00";
				break;
			case 6:
				time = "06:00";
				break;
			case 7:
				time = "07:00";
				break;
			case 8:
				time = "08:00";
				break;
			case 9:
				time = "09:00";
				break;
			case 10:
				time = "10:00";
				break;
			case 11:
				time = "11:00";
				break;
			case 12:
				time = "12:00";
				break;
			case 13:
				time = "13:00";
				break;	
			case 14:
				time = "14:00";
				break;
			case 15:
				time = "15:00";
				break;
			case 16:
				time = "16:00";
				break;
			case 17:
				time = "17:00";
				break;
			case 18:
				time = "18:00";
				break;
			case 19:
				time = "19:00";
				break;
			case 20:
				time = "20:00";
				break;
			case 21:
				time = "21:00";
				break;
			case 22:
				time = "22:00";
				break;
			case 23:
				time = "23:00";
				break;	
			default:	
				time = "";
				break;
		}
	}
	return time;
}
