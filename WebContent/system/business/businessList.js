var total = 0;
var pageSize = 20; //默认每页20条数据
var pageStart = 0;
$(function(){
	controlBtn();
	//加载商户列表
	loadBusinessList("init",null);
	keyEvent();
});

/**
 * 验证值
 * @param value
 * @returns
 */
function checkValue(value){
	if(typeof(value) != "undefined" && value != null && value != "" && value != "null"){
		return true;
	}else{
		return false;
	}
}

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
            var bname = $("#bname_search").val();
        	var phone = $("#phone_search").val();
        	var status = $("#status_search").val();
        	var legaler = $("#legaler_search").val();
        	var param = {};
        	if(checkValue(bname)){
        		param["business.bname"] = bname;
        	}
        	if(checkValue(phone)){
        		param["business.phone"] = phone;
        	}
        	if(checkValue(status)){
        		param["business.status"] = status;
        	}
        	if(checkValue(legaler)){
        		param["business.legaler"] = legaler;
        	}
        	loadBusinessList("pageQuery",param);
        }
    });
}

/**
 * 加载商户列表
 * @param type 查询类型  init:初始化查询   pageQuery:分页查询
 * @param param 
 */
function loadBusinessList(type,param){
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
		url:"/selforder/api/business/getBusinessList.action",
		data:param,
		dataType:'json',
		success:function(data){
			layer.close(load);
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
						var bcode = row.bcode;
						var bname  = row.bname;
						var legaler = row.legaler;
						var phone = row.phone;
						var email = row.email;
						var status = row.status;
						if(status == -1){
							status = "停止合作";
						}else if(status == 0){
							status = "试点";
						}else if(status == 1){
							status = "合作";
						}else if(status == 2){
							status = "暂停";
						}
						var address = row.address;
						var begindate = row.begindate;
						var enddate = row.enddate;
						if(typeof(begindate) != "undefined" && begindate != "" && begindate != null){
							begindate = getDateByTime(begindate.time,"yyyy-MM-dd");
						}else{
							begindate = "";
						}
						if(typeof(enddate) != "undefined" && enddate != "" && enddate != null){
							enddate = getDateByTime(enddate.time,"yyyy-MM-dd");
						}else{
							enddate = "";
						}
						tr +='<tr tag="append" bid="'+bid+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+bname+'</td>    ';
						tr +='	<td>'+bcode+'</td>    ';
						tr +='	<td>'+legaler+'</td>                        ';
						tr +='	<td>'+phone+'</td>                  ';
						tr +='	<td>'+status+'</td>                          ';
						tr +='	<td>'+address+'</td>';
						tr +='	<td>'+begindate+'</td>                    ';
						tr +='	<td>'+enddate+'</td>                    ';
						tr +='	<td><button role="btn-business-update" type="button" class="btn btn-danger" onclick="updateBusiness(\''+bid+'\')">修改</button></td>                    ';
						tr +='</tr>                                    ';
						$("#businessList").append(tr);
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
 * 根据bid修改商户信息
 * @param bid
 */
function updateBusiness(bid){
	if(typeof(bid) == "undefined" || bid == ""){
		return;
	}else{
		window.location.href="saveBusiness.jsp?opt=update&bid="+bid;
	}
}

/**
 * 搜索
 */
var search = function(){
	var bname = $("#bname_search").val();
	var phone = $("#phone_search").val();
	var status = $("#status_search").val();
	var legaler = $("#legaler_search").val();
	var param = {};
	param["business.bname"] = bname;
	param["business.phone"] = phone;
	param["business.status"] = status;
	param["business.legaler"] = legaler;
	loadBusinessList("init",param);
}

/**
 * 重置查询条件
 * @returns
 */
function clearParam(){
	loadBusinessList();
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

