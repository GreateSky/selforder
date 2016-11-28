var total = 0;
var pageSize = 20; //默认每页20条数据
var pageStart = 0;
var first = true;//是否第一次加载
$(function(){
	//加载食谱列表
	loadGoodsList("init",null);
	keyEvent();
	loadGoodsCategoryCommbox();
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
        		loadGoodsList("pageQuery",param);
        	}
        }
    });
}
/**
 * 加载食谱列表
 * @param type 查询类型  init:初始化查询   pageQuery:分页查询
 * @param param 
 */
function loadGoodsList(type,param){
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
	$("tr[tag='appendGoodsTr']").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/goods/getGoodsList.action",
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
						var weid = row.weid ;
						var storeid = row.storeid ;
						var cname = row.cname ;
						var title = row.title;
						var marketprice = row.marketprice ;
						var unitname = row.unitname;
						var recommend = row.recommend ;
						var isspecial = row.isspecial;
						var displayorder = row.displayorder ;
						var status = row.status;
						var taste = row.taste;
						var thumb = row.thumb;
						var subcount = row.subcount;
						var imgsrc = "/selforder/api/fileutil?method=download&fileid="+thumb;
						var statusStr = "";
						var recommendStr = "";
						var isspecialStr = "";
						if(isspecial == "0"){
							isspecialStr = "否";
						}else if(isspecial == "1"){
							isspecialStr = "是";
						}
						if(recommend == "0"){
							recommendStr = "否";
						}else if(recommend == "1"){
							recommendStr = "是";
						}
						if(status == "0"){
							statusStr = "上架";
						}else if(status == "1"){
							statusStr = "下架";
						}else if(status == "2"){
							statusStr = "暂售";
						}
						tr +='<tr tag="appendGoodsTr" id="'+id+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td><img src="'+imgsrc+'" width="60px" height="60px"></img></td>';
						tr +='	<td>'+cname+'</td>    ';
						tr +='	<td>'+title+'</td>    ';
						tr +='	<td>'+marketprice+'</td>                        ';
						tr +='	<td>'+unitname+'</td>                  ';
						tr +='	<td>'+displayorder+'</td>';
						tr +='	<td>'+statusStr+'</td>';
						tr +='	<td>'+taste+'</td>';
						tr +='	<td>'+subcount+'</td>';
						tr +='	<td><button type="button" class="btn btn-warning" onclick="updateGoods(\''+id+'\')">编辑</button>&nbsp;<button type="button" class="btn btn-danger" onclick="delGoods(\''+id+'\')">删除</button></td>                    ';
						tr +='</tr>                                    ';
						$("#goodsList").append(tr);
					}
				}
			}
		}
	});
}


/**
 * 搜索
 */
function search(){
	var title = $("#title_search").val();
	var cid = $("#goodsCategoryCommbox").val();
	var param = {};
	param["goods.title"] = title;
	param["goods.cid"] = cid;
	first = true;
	loadGoodsList("init",param);
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
	loadGoodsList("init",null);
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
 * 删除食谱
 * @param sid
 */
function delGoods(id){
	layer.confirm("确定要删除该食谱吗?",
			{btn:["确定","取消"]},
			function(){//确定
				$.ajax({
					type:"POST",
					url:"/selforder/api/goods/updateGoods.action",
					data:{"goods.id":id,"goods.deleted":1},
					dataType:"json",
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0 ){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						loadGoodsList("init",null);
					}
				});
			},
			function(){//取消
				layer.closeAll();
			});
}

/**
 * 保存食谱分类
 */
function saveGoodsCategory(){
	var name = $("#name").val();
	var displayorder = $("#displayorder").val();
	if(!checkValue(name)){
		layer.msg("请输入食谱分类名称",{icon:5});
		return;
	}
	$.ajax({
		type:"POST",
		url:"/selforder/api/goods/insertGoodsCategory.action",
		data:{"goodsCategory.name":name,"goodsCategory.displayorder":displayorder},
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				layer.msg(message,{icon:6});
			}
			$("#name").val("");
			$("#displayorder").val("");
			loadGoodsCategoryList();
		}
	});
}

/**
 * 加载已创建食谱分类
 */
function loadGoodsCategoryList(){
	$("tr[tag='appendGoodsCategoryTr']").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/goods/goodsCategoryList.action",
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				var rows = res.data;
				if(rows.length > 0){
					for(var i=0;i<rows.length;i++){
						var tr = "";
						var row = rows[i];	
						var id = row.id;
						var name = row.name;
						var goodsnum = row.goodsnum;
						var displayorder = row.displayorder;
						tr +='<tr tag="appendGoodsCategoryTr" id="'+id+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+name+'</td>    ';
						tr +='	<td>'+goodsnum+'</td>    ';
						tr +='	<td>'+displayorder+'</td>    ';
						tr +='	<td><button type="button" class="btn btn-danger" onclick="delGoodsCategory(\''+id+'\')">删除</button></td>                    ';
						tr +='</tr>                                    ';
						$("#goodsCategoryList").append(tr);
					}
				}
			}
		}
	});
}

/**
 * 显示已创建食谱分类
 */
function showGoodsCategory(){
	$("#name").val("");
	$("#displayorder").val("");
	loadGoodsCategoryList();
	$("#createGoodsCategory").modal("show");
}

/**
 * 删除食谱分类
 * @param id
 */
function delGoodsCategory(id){
	layer.confirm("确定要删除该食谱分类吗？食谱分类中的食谱将取消于该食谱分类的关系。",
			{btn: ['确定','取消']},
			function(){
				$.ajax({
					type:"POST",
					url:"/selforder/api/goods/updateGoodsCategory.action",
					data:{"goodsCategory.id":id,"goodsCategory.deleted":1},
					dataType:"json",
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0 ){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						loadGoodsCategoryList();
					}
				});
			},
			function(){
				layer.closeAll();
			});
}

/**
 * 更新食谱信息
 * @param id 食谱ID
 */
function updateGoods(id){
	window.location.href = "saveGoods.jsp?opt=update&id="+id;
}

/**
 * 加载食谱分类
 */
function loadGoodsCategoryCommbox(){
	$("#goodsCategoryCommbox option").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/goods/goodsCategoryList.action",
		dataType:"json",
		async:false,
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				var rows = res.data;
				$("#goodsCategoryCommbox").append('<option value="" selected="selected">全部</option>');
				if(rows.length > 0){
					for(var i=0;i<rows.length;i++){
						var row = rows[i];	
						var id = row.id;
						var name = row.name;
						var option= "";
						option = "<option value='"+id+"'>"+name+"</option>";
						$("#goodsCategoryCommbox").append(option);
					}
				}
			}
		}
	});
}
