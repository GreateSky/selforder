var total = 0;
var pageSize = 20; //默认每页20条数据
var pageStart = 0;
var curr_oid = "";//当前的组织架构ID
var curr_oname = "";
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
            	pageSize = 20;
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
        	loadOrganizationList("pageQuery",param);
        }
    });
}

function loadOrganizationList(type,param){
	//var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	if(typeof(param) == "undefined" || param == null || param == ""){
		param = {};
		param["page"] = pageStart;
		param["limit"] = pageSize;
	}else{
		param["page"] = pageStart;
		param["limit"] = pageSize;
	}
	param["employee.oid"] = curr_oid;
	$.ajax({
		type:"POST",
		url:"/selforder/api/organization/getEmpOrgList.action",
		data:param,
		dataType:'json',
		success:function(data){
			//layer.close(load);
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
						var oname = row.oname;
						tr +='<tr tag="append" empid="'+empid+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+empname+'</td>    ';
						tr +='	<td>'+empcode+'</td>                        ';
						tr +='	<td>'+oname+'</td>                  ';
						tr +='	<td><button type="button" class="btn btn-warning" onclick="delRef(\''+empid+'\')">删除</button></td>                    ';
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
 * 删除用户关联
 * @param oeid
 */
function delRef(empid){
	if(typeof(empid) == "undefined" || empid == ""){
		layer.msg("员工参数异常，请刷新后重试!",{icon:5});
	}else if(typeof(curr_oid) == "undefined" || curr_oid == ""){
		layer.msg("部门参数异常，请选择左边部门后重试!",{icon:5});
	}else{
		layer.confirm("确定要从【"+curr_oname+"】中移除该员工吗？",
			{btn:["确定","取消"]},
			function(){//确定
				var param = {};
				param["employee.empid"] = empid;
				param["employee.oid"] = curr_oid;
				param["employee.deleted"] = 1;
				$.ajax({
					type:"POST",
					url:"/selforder/api/organization/updateEmpOrg.action",
					data:param,
					dataType:"json",
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						loadOrganizationList("init",null);
					}
				});
			},
			function(){//取消
				layer.closeAll();
			}
		);
	}
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

/**
 * 显示选择人员列表
 */
function showEmployeeWin(){
	loadEmployeeList("");
	$("#employeeListWin").modal("show");
}

/**
 * 加载当前组织架构中未包含的员工列表
 */
function loadEmployeeList(param){
	var noemporg_load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	if(!param){
		param = {};
	}
	param["employee.oid"] = curr_oid;
	$.ajax({
		type:"POST",
		url:"/selforder/api/organization/getNoEmpOrgList.action",
		data:param,
		dataType:"json",
		success:function(res){
			layer.close(noemporg_load);
			//清除历史数据
			$("tr[tag='emporglist_append']").remove();
			var retCode = res.retCode;
			var message = res.message;
			var noemporglist = res.data;
			if(retCode < 0){
				layer.msg(message,{icon:2});
			}else{
				for(var i=0;i<noemporglist.length;i++){
					var row = noemporglist[i];
					var empid = row.empid;
					var empname = row.empname;
					var empcode = row.empcode;
					var appendTr = "";
					appendTr += '<tr tag="emporglist_append" empid="'+empid+'">';
					appendTr +='   <td><input type="checkbox"></input></td>';
					appendTr += '  <td>'+(i+1)+'</td>';
					appendTr += '  <td>'+empname+'</td>';
					appendTr += '  <td>'+empcode+'</td> ';
					appendTr += '</tr>          ';
					$("#noEmpOrglist").append(appendTr);
				}
			}
		}
	});
}

/**
 * 保存选择的员工与组织关系
 */
function saveEmpOrg(){
	var selectNum = -1;
	selectNum = $("#noEmpOrglist input[type='checkbox']:checked").length;
	if(selectNum <=0 ){
		layer.msg("请选择需要关联的员工!", {icon: 5});
		return;
	}else{
		layer.confirm('确定保存数据吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				layer.closeAll();
				//确定保存
				var param = {};
				$("#noEmpOrglist input[type='checkbox']:checked").each(function(i){
					var tr = $(this).parent().parent();
					var empid =  $(tr).attr("empid");
					param["employeeList["+i+"].oid"] =  curr_oid;
					param["employeeList["+i+"].empid"] = empid;
				});
				$.ajax({
					type:"POST",
					url:"/selforder/api/organization/saveEmpOrg.action",
					data:param,
					dataType:"json",
					success:function(res){
						$("#employeeListWin").modal("hide");
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:1});
						}
					}
				});
			}, function(){
				$("#employeeListWin").modal("hide");
				//取消保存
				layer.closeAll();
			});
	}
}