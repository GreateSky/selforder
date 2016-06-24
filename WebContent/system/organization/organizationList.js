var total = 0;
var pageSize = 20; //默认每页20条数据
var pageStart = 0;
$(function(){
	//加载商户列表
	loadOrganizationList("init",null);
	keyEvent();
	//pageOption();
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
            	pageSize = 2;
            }
            var empname = $("#empname_search").val();
        	var empcode = $("#empcode_search").val();
        	var param = {};
        	if(checkValue(empname)){
        		param["employee.empname"] = empname;
        	}
        	if(checkValue(empcode)){
        		param["employee.empcode"] = empcode;
        	}
        	loadOrganizationList("pageQuery",null);
        }
    });
}

function loadOrganizationList(type,param){
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
		url:"/selforder/api/employee/getEmployeeList.action",
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
						var empid = row.empid;
						var empcode  = row.empcode;
						var empname = row.empname;
						var remark = row.remark;
						tr +='<tr tag="append" empid="'+empid+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+empname+'</td>    ';
						tr +='	<td>'+empcode+'</td>                        ';
						tr +='	<td></td>                  ';
						tr +='	<td><button type="button" class="btn btn-danger" onclick="openResourceWin(\'update\',\''+empid+'\')">修改</button>&nbsp;<button type="button" class="btn btn-warning" onclick="delResource(\''+empid+'\')">删除</button></td>                    ';
						tr +='</tr>                                    ';
						$("#employeelist").append(tr);
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
 * 搜索
 */
function search(){
	var empname = $("#empname_search").val();
	var empcode = $("#empcode_search").val();
	var param = {};
	param["employee.empname"] = empname;
	param["employee.empcode"] = empcode;
	loadOrganizationList("init",param);
}

/**
 * 重置查询条件
 * @returns
 */
function clearParam(){
	$("#empname_search").val("");
	$("#empcode_search").val("");
	loadOrganizationList("init",null);
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