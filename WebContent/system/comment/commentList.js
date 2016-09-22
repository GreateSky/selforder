var total = 0;
var pageSize = 20; //默认每页20条数据
var pageStart = 0;
var first = true;//是否第一次加载
var currDate = (new Date()).Format("yyyy-MM")+"-01";//获取当前月
$(function(){
	//默认查询当月的数据
	$("#search_begindate").val(currDate);
	var param = {"comment.begindate":currDate};
	loadCommentList("init",param);//加载商户列表
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
        		loadCommentList("pageQuery",param);
        	}
        }
    });
}
/**
 * 加载评论列表
 * @param type 查询类型  init:初始化查询   pageQuery:分页查询
 * @param param 
 */
function loadCommentList(type,param){
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
	$("tr[tag='appendCommentTr']").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/comment/getCommentList.action",
		data:param,
		dataType:'json',
		success:function(res){
			layer.close(load);
			var retCode = res.retCode;
			var message = res.message;
			if(retCode<0){
				layer.msg(message,{"icon":5});
				return;
			}else{
				var data = res.data;
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
						var from_user = row.from_user;
						var nickname = row.nickname;
						var username = row.username;
						var headimgurl = row.headimgurl;
						var description = row.description;
						var isread = row.isread;
						var crtdate = row.crtdate;
						var crtdateStr = formatDate(crtdate);
						if(isread == 0 ){
							isread = "<span style='color:green'>未读</spn>";
						}else{
							isread = "已读";
						}
						
						tr +='<tr tag="appendCommentTr" fromuser="'+from_user+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+nickname+'('+username+')</td>    ';
						tr +='	<td>'+description+'</td>                        ';
						tr +='	<td>'+crtdateStr+'</td>';
						tr +='	<td>'+isread+'</td>                  ';
						tr +='	<td><button type="button" class="btn btn-warning" onclick="goCommentDetail(\''+from_user+'\')">查看</button></td>                    ';
						tr +='</tr>                                    ';
						$("#commentList").append(tr);
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
 * 获取评论详情
 * @param from_user 评论人ID
 */
function goCommentDetail(from_user){
	if(typeof(from_user) == "undefined" || from_user == ""){
		return;
	}else{
		window.location.href="commentDetail.jsp?fromuser="+from_user;
	}
}

/**
 * 搜索
 */
function search(){
	var keyword = $("#search_username").val();
	var search_begindate = $("#search_begindate").val();
	var search_enddate = $("#search_enddate").val();
	var param = {};
	param["comment.keyword"] = keyword;
	param["comment.begindate"] = search_begindate;
	param["comment.enddate"] = search_enddate;
	first = true;
	loadCommentList("init",param);
}

/**
 * 重置查询条件
 * @returns
 */
function clearParam(){
	$("#search_username").val("");
	$("#search_begindate").val("");
	$("#search_enddate").val("");
	first = true;
	loadCommentList("init",null);
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
