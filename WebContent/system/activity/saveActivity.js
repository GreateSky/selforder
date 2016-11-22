var imgid = "";//活动图片ID
$(function(){
	controlBtn();
	if(opt == "update"){
		getActivityInfo();
		getActivityGoodsList();
	}
});

/**
 * 获取活动详情
 */
function getActivityInfo(){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	if(!checkValueWithInfo(id,"查询参数为空！")) return;
	$.ajax({
		type:"POST",
		url:"/selforder/api/activity/getActivityInfo.action",
		data:{"activity.id":id},
		dataType:"json",
		success:function(res){
			layer.close(load);
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				var row = res.data;
				var begindate = formatDate(row.begindate);
				begindate = begindate.substring(0,10);
				var enddate = formatDate(row.enddate);
				enddate = enddate.substring(0, 10);
				var url = row.url;
				var imgid = row.imgid;
				$("#title").val(row.title);
				$("#type").val(row.type);
				$("#discount").val(row.discount);
				$("#leastcost").val(row.leastcost);
				$("#begindate").val(begindate);
				$("#enddate").val(enddate);
				$("#status").val(row.status);
				$("#url").val(url);
				$("#remark").val(row.remark);
				var imgsrc = "/selforder/api/fileutil?method=download&fileid="+imgid;
				$("#imgid").attr("src",imgsrc);
			}
		}
	});
}

/**
 * 保存活动
 */
function saveActivity(){
	//验证输入的数据
	if(!checkValueWithInfo($("#title").val(),"请输入名称！"))return;
	if(!checkValueWithInfo($("#discount").val(),"请输入折扣数！"))return;
	if(!checkValueWithInfo($("#begindate").val(),"请选择开始时间！"))return;
	if(!checkValueWithInfo($("#enddate").val(),"请选择结束时间！"))return;
	var begindateStr = $("#begindate").val();
	var enddateStr = $("#enddate").val();
	if(begindateStr> enddateStr){
		layer.msg("开始日期不能大于结束日期",{icon:5});
		return;
	}
	if(opt == "update"){
		updateActivity();
	}else{
		addActivity();
	}
}

/**
 * 新增活动
 */
function addActivity(){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	var title = $("#title").val();
	var type = $("#type").val();
	var url = $("#url").val();
	var discount = $("#discount").val();
	var leastcost = $("#leastcost").val();
	var begindate = $("#begindate").val();
	var enddate = $("#enddate").val();
	var status = $("#status").val();
	var remark = $("#remark").val();
	var goods = getAddGoods();
	var param = {};
	param["activity.title"] = title;
	param["activity.type"] = type;
	param["activity.url"] = url;
	param["activity.discount"] = discount;
	param["activity.leastcost"] = leastcost;
	param["activity.begindate"] = begindate;
	param["activity.enddate"] = enddate;
	param["activity.status"] = status;
	param["activity.imgid"] = imgid;
	param["activity.remark"] = remark;
	param["activity.goods"] = goods;
	$.ajax({
		type:"POST",
		url:"/selforder/api/activity/insertActivity.action",
		data:param,
		dataType:"json",
		success:function(res){
			layer.close(load);
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{icon:5});
			}else{
				layer.msg(message,{icon:6});
			}
			window.location.href = 'activityList.jsp';
		}
	});
}

/**
 * 更新活动
 */
function updateActivity(){
	var title = $("#title").val();
	var type = $("#type").val();
	var url = $("#url").val();
	var discount = $("#discount").val();
	var leastcost = $("#leastcost").val();
	var begindate = $("#begindate").val();
	var enddate = $("#enddate").val();
	var status = $("#status").val();
	var remark = $("#remark").val();
	var goods = getAddGoods();
	var param = {};
	param["activity.title"] = title;
	param["activity.type"] = type;
	param["activity.url"] = url;
	param["activity.discount"] = discount;
	param["activity.leastcost"] = leastcost;
	param["activity.begindate"] = begindate;
	param["activity.enddate"] = enddate;
	param["activity.status"] = status;
	param["activity.remark"] = remark;
	param["activity.id"] = id;
	param["activity.imgid"] = imgid;
	param["activity.goods"] = goods;
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	$.ajax({
		type:"POST",
		url:"/selforder/api/activity/updateActivity.action",
		data:param,
		dataType:"json",
		success:function(res){
			layer.close(load);
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.alert(message,{icon:5});
			}else{
				layer.msg(message,{icon:6});
				window.location.href = 'activityList.jsp';
			}
		}
	});
}

//********************************活动食谱操作start**************************
/**
 * 获取活动已关联食谱
 */
function getActivityGoodsList(){
	if(typeof(id) == "undefined" || id == ""){
		return;
	}
	//清除历史数据
	$("tr[tag='appendActivityGoodsTr']").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/activity/getActivityInCouldGoods.action",
		data:{"activityGoods.activityid":id},
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){
				layer.msg(message,{"icon":5});
				return;
			}else{
				var rows = res.data;
				if(null != rows && rows.length>0){
					for(var i=0;i<rows.length;i++){
						var row = rows[i];
						var tr = "";
						tr +='<tr tag="appendActivityGoodsTr" id="'+row.id+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+row.title+'</td>    ';
						tr +='	<td>'+row.marketprice+'</td>                        ';
						tr +='	<td>'+row.discount_price+'</td>                        ';
						tr +='	<td><button type="button" class="btn btn-danger" onclick="delActivityGoods('+1+',\''+row.id+'\')">删除</button></td>                    ';
						tr +='</tr>                                    ';
						$("#activityGoodsList").append(tr);
					}
				}
			}
		}
		
	});
}

/**
 * 删除已关联食谱
 * @param type 删除类型：1、已关联的食谱需调用后台逻辑删除   2、新增类型的食谱删除只需删除DOM元素即可
 * @param id
 */
function delActivityGoods(type,id){
	layer.confirm("确定要移除该商品吗？",
				{"btn":["确定","取消"]},
				function(){
					layer.closeAll();
					if(type == "2"){
						$("tr[tag='appendActivityGoodsTr'][goodsid='"+id+"']").remove();
					}else{
						$.ajax({
							type:"POST",
							url:"/selforder/api/activity/updateActivityGoods.action",
							data:{"activityGoods.id":id},
							dataType:"json",
							success:function(res){
								var retCode = res.retCode;
								var message = res.message;
								if(retCode <0){
									layer.msg(message,{"icon":5});
								}else{
									layer.msg(message,{"icon":6});
								}
								getActivityGoodsList();
							}
						});
					}
				},
				function(){
					layer.closeAll();
				}
	);
}

/**
 * 显示未选择物料
 */
function showGoodsWin(){
	loadNoSelectGoods();
	$("#selectGoodsWin").modal('show');
}

/**
 * 加载未关联物料
 */
function loadNoSelectGoods(){
	$("tr[tag='appendNoSelectGoodsTr']").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/activity/getNotInActivityGoods.action",
		data:"",
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0){
				layer.msg(message,{"icon":5});
			}else{
				var rows = res.data;
				if(null != rows && rows.length>0){
					for(var i = 0; i < rows.length;i++){
						var row = rows[i];
						var tr = "";
						tr +='<tr tag="appendNoSelectGoodsTr" id="'+row.id+'" class="animated flipInX">                                     ';
						tr +='	<td>'+row.title+'</td>    ';
						tr +='	<td>'+row.marketprice+'</td>                        ';
						tr +='	<td><button type="button" class="btn btn-danger" onclick="selectGoods(\''+row.id+'\',\''+row.title+'\',\''+row.marketprice+'\')">选择</button></td>                    ';
						tr +='</tr>                                    ';
						$("#noSelectGoods").append(tr);
					}
				}
			}
		}
	});
}

/**
 * 添加选择的物料
 * @param goodsid  物料ID
 * @param title    名称
 * @param marketprice	价格
 */
function selectGoods(goodsid,title,marketprice){
	var length = $("tr[tag='appendActivityGoodsTr']").length;//获取当前已关联食谱个数
	var discount = $("#discount").val();
	var discount_price = marketprice*discount*0.1;
	discount_price = discount_price.toFixed(2);
	$("#"+goodsid).remove();//删除已选中的食谱
	//向已关联食谱列表中添加选择物料
	var tr = "";
	tr +='<tr tag="appendActivityGoodsTr" id="" goodsid="'+goodsid+'" opt="add" class="animated flipInX">                                     ';
	tr +='	<td>'+(length+1)+'</td>                             ';
	tr +='	<td>'+title+'</td>    ';
	tr +='	<td>'+marketprice+'</td>                        ';
	tr +='	<td>'+discount_price+'</td>                        ';
	tr +='	<td><button type="button" class="btn btn-danger" onclick="delActivityGoods('+2+',\''+goodsid+'\')">删除</button></td>                    ';
	tr +='</tr>                                    ';
	$("#activityGoodsList").append(tr);
}

/**
 * 获新添加的食谱
 * @returns {String}	返回新添加的食谱字符串以逗号隔开
 */
function getAddGoods(){
	var  goodsList = '';
	$("tr[opt='add']").each(function(){
		var goods = $(this).attr("goodsid");
		goodsList += goods+",";
	});
	goodsList = goodsList.substring(0,goodsList.length-1);
	return goodsList;
}
//********************************活动食谱操作end**************************