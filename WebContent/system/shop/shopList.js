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
						var is_show = row.is_show;
						var is_meal = row.is_meal;
						var is_delivery = row.is_delivery;
						var is_reservation = row.is_reservation;
						var is_queue = row.is_queue;
						var begintime = row.begintime;
						var endtime = row.endtime;
						var delivery_radius = row.delivery_radius;
						
						tr +='<tr tag="appendShopTr" sid="'+id+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+title+'</td>    ';
						tr +='	<td>'+tel+'</td>                        ';
						tr +='	<td>'+begintime+'</td>                  ';
						tr +='	<td>'+endtime+'</td>                          ';
						tr +='	<td>'+is_meal+'</td>';
						tr +='	<td>'+is_delivery+'</td>                    ';
						tr +='	<td>'+is_reservation+'</td>                    ';
						tr +='	<td>'+is_show+'</td>                    ';
						tr +='	<td>'+is_queue+'</td>                    ';
						tr +='	<td>'+delivery_radius+'</td>                    ';
						tr +='	<td>'+address+'</td>                    ';
						tr +='	<td><button type="button" class="btn btn-warning" onclick="updateShop(\''+id+'\')">编辑</button>&nbsp;<button type="button" class="btn btn-danger" onclick="updateShop(\''+id+'\')">删除</button></td>                    ';
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
	var param = {};
	first = true;
	loadBusinessList("init",param);
}

/**
 * 重置查询条件
 * @returns
 */
function clearParam(){
	first = true;
	loadBusinessList("init",null);
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

