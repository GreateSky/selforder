var total = 0;
var pageSize = 20; //默认每页20条数据
var pageStart = 0;
var first = true;//是否第一次加载
$(function(){
	//加载商户已开通权限列表
	loadBusinessRoleList("init",null);
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
            var bname_search = $("#bname_search").val();
        	var bcode_search = $("#bcode_search").val();
        	var param = {};
        	param["business.bname"] = bname_search;
        	param["business.bcode"] = bcode_search;
        	if(!first){
        		loadBusinessRoleList("pageQuery",param);
        	}
        	
            
        }
    });
}

/**
 * 加载商户已开通权限列表
 * @param type 查询类型  init:初始化查询   pageQuery:分页查询
 * @param param
 */
function loadBusinessRoleList(type,param){
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
		url:"/selforder/api/power/businessList.action",
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
						var bid = row.bid;
						var bcode  = row.bcode;
						var bname = row.bname;
						var rid = row.rid;
						var rname= row.rname;
						tr +='<tr tag="append" bid="'+bid+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+bname+'</td>                        ';
						tr +='	<td>'+bcode+'</td>    ';
						tr +='	<td>'+rname+'</td>                  ';
						tr +='	<td><button type="button" class="btn btn-warning" onclick="viewBusiness(\''+bid+'\')">查看</button></td>                    ';
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
 * 查看商户已授权详情
 * @param bid
 */
function viewBusiness(bid){
	window.location.href="saveBusinessRole.jsp?bid="+bid;
}


/**
 * 搜索功能
 */
function search(){
	var bname_search = $("#bname_search").val();
	var bcode_search = $("#bcode_search").val();
	var param = {};
	param["business.bname"] = bname_search;
	param["business.bcode"] = bcode_search;
	first = true;
	loadBusinessRoleList('init',param);
}

/**
 * 重置
 */
function resetSearch(){
	$("#bname_search").val("");
	$("#bcode_search").val("");
	first = true;
	loadBusinessRoleList('init',null);
}