var total = 0;
var pageSize = 20; //默认每页20条数据
var pageStart = 0;
var first = true;//是否第一次加载
$(function(){
	//加载活动列表
	loadEmployeeList("init",null);
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
        		loadEmployeeList("pageQuery",param);
        	}
        }
    });
}
/**
 * 加载活动列表
 * @param queueType 查询类型  init:初始化查询   pageQuery:分页查询
 * @param param 
 */
function loadEmployeeList(queueType,param){
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
	$("tr[tag='appendEmployeeTr']").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/employee/getEmployeeList.action",
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
						var empid = row.empid;
						var empcode = row.empcode;
						var empname = row.empname;
						var sex = row.sex;
						sex = sex==1?"男":"女";
						var status = row.status;//状态:0实习 1正式  2其他  -1 离职
						var type = row.type;//类型：A系统管理员  B商户人员  S门店员工
						var phone = row.phone;
						var bname = row.bname;
						var sname = row.sname;
						var isadmin = row.isadmin;
						if(status == 0 ){
							status = "实习";
						}else if(status == 1 ){
							status = "正式";
						}else if(status == 2){
							status = "其他";
						}else if(status == -1){
							status = " 离职";
						}
						
						if(type == "A" ){
							type = "平台员工";
						}else if(type == "B" ){
							type = "商户员工";
						}else if(type == "S" ){
							type = "门店员工";
						}
						tr +='<tr tag="appendEmployeeTr" id="'+empid+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+sname+'</td>    ';
						tr +='	<td>'+empname+'</td>                        ';
						tr +='	<td>'+empcode+'</td>                  ';
						tr +='	<td>'+sex+'</td>';
						tr +='	<td>'+status+'</td>                    ';
						tr +='	<td>'+phone+'</td>                    ';
						tr +='	<td>'+type+'</td>                    ';
						if(isadmin == "1"){
							tr +='	<td><button role="btn-shop-update" type="button" class="btn btn-warning" onclick="updateEmployee(\''+empid+'\')">编辑</button>&nbsp</td>                    ';
						}else{
							tr +='	<td><button role="btn-shop-update" type="button" class="btn btn-warning" onclick="updateEmployee(\''+empid+'\')">编辑</button>&nbsp;<button role="btn-shop-remove" type="button" class="btn btn-danger" onclick="delEmployee(\''+empid+'\')">删除</button></td>                    ';
						}
						tr +='</tr>                                    ';
						$("#employeeList").append(tr);
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
function updateEmployee(id){
	if(!checkValueWithInfo(id,'查询参数异常，请刷新后重试!')) return;
	window.location.href = "saveEmployee.jsp?opt=update&empid="+id;
}

/**
 * 搜索
 */
function search(){
	var empname = $("#search_empname").val();
	var empcode = $("#search_empcode").val();
	var type = $("#search_type").val();
	var status = $("#search_status").val();
	var param = {};
	param["employee.empname"] = empname;
	param["employee.empcode"] = empcode;
	param["employee.type"] = type;
	param["employee.status"] = status;
	first = true;
	loadEmployeeList("init",param);
}

/**
 * 重置查询条件
 * @returns
 */
function clearParam(){
	$("#search_empname").val("");
	$("#search_empcode").val("");
	$("#search_type").val("");
	$("#search_status").val("");
	first = true;
	loadEmployeeList("init",null);
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
function delEmployee(id){
	layer.confirm("确定要删除该员工吗?",
			{btn:["确定","取消"]},
			function(){//确定
				$.ajax({
					type:"POST",
					url:"/selforder/api/employee/updateEmployeeInfo.action",
					data:{"employee.empid":id,"employee.deleted":"1"},
					dataType:"json",
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0 ){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						loadEmployeeList("init",null);
					}
				});
			},
			function(){//取消
				layer.closeAll();
			});
}

