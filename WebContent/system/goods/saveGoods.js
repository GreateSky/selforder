var thumb = "";//图片ID
var saveing;
$(function(){
	loadGoodsCategoryCommbox();//加载食谱分类
	if(opt == "update"){
		getGoodsInfo();//获取食谱详情
	}
});

function getGoodsInfo(){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	$.ajax({
		type:"POST",
		url:"/selforder/api/goods/getGoodsInfo.action",
		data:{"goods.id":id},
		dataType:"json",
		success:function(res){
			layer.close(load);
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0 ){//食谱已存在
				layer.msg(message,{icon:5});
			}else{
				var row = res.data;
				var title = row.title;
				var cid = row.cid;
				var marketprice = row.marketprice;
				var displayorder = row.displayorder;
				var productprice = row.productprice;
				var unitname = row.unitname;
				var recommend = row.recommend;
				var isspecial = row.isspecial;
				var taste = row.taste;
				var thumb = row.thumb;
				var status = row.status;
				var description = row.description;
				var imgsrc = "/selforder/api/fileutil?method=download&fileid="+thumb;
				$("#title").val(title);
				$("#goodsCategoryCommbox").val(cid);
				$("#marketprice").val(marketprice);
				$("#displayorder").val(displayorder);
				$("#productprice").val(productprice);
				$("#unitname").val(unitname);
				$("input[name='recommend'][value="+recommend+"]").attr("checked","checked");
				$("input[name='isspecial'][value="+isspecial+"]").attr("checked","checked");
				$("#taste").val(taste);
				$("#status").val(status);
				$("#description").val(description);
				$("#goodsImg").attr("src",imgsrc);
			}
		}
	});
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

//上传附件
function uploadFile(){
	if(!checkValueWithInfo($("#title").val(),"食谱名称不能为空!"))return;
	if(!checkValueWithInfo($("#productprice").val(),"请输入成品价格!"))return;
	if(!checkValueWithInfo($("#marketprice").val(),"请输入市场价格!"))return;
	saveing = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	ajaxFileUpload("saveGoods");
}

/**
 * 保存食谱
 */
function saveGoods(args){
	if(typeof(args) != "undefined" && args != ""){ 
		thumb = args[0].fileid;
	}
	if(opt == "update"){
		updateGoods();
	}else{
		addGoods();
	}
}

/**
 * 新增食谱
 */
function addGoods(){
	var title = $("#title").val();
	var cid = $("#goodsCategoryCommbox").val();
	var marketprice = $("#marketprice").val();
	var productprice = $("#productprice").val();
	var unitname = $("#unitname").val();
	var recommend = $("input[type='radio'][name='recommend']:checked").val();
	var isspecial = $("input[type='radio'][name='isspecial']:checked").val();
	var taste = $("#taste").val();
	var status = $("#status").val();
	var description = $("#description").val();
	var displayorder = $("#displayorder").val();
	var param = {};
	param["goods.title"] = title;
	param["goods.cid"] = cid;
	param["goods.marketprice"] = marketprice;
	param["goods.productprice"] = productprice;
	param["goods.unitname"] = unitname;
	param["goods.displayorder"] = displayorder;
	param["goods.recommend"] = recommend;
	param["goods.isspecial"] = isspecial;
	param["goods.taste"] = taste;
	param["goods.description"] = description;
	param["goods.thumb"] = thumb;
	param["goods.status"] = status;
	$.ajax({
		type:"POST",
		url:"/selforder/api/goods/insertGoods.action",
		data:param,
		dataType:"json",
		success:function(res){
			layer.close(saveing);
			var retCode = res.retCode;
			var message = res.message;
			showResult(retCode,message);
			window.location.href = "/selforder/system/goods/goodsList.jsp";
		}
	});
}

/**
 * 更新食谱
 */
function updateGoods(){
	var title = $("#title").val();
	var cid = $("#goodsCategoryCommbox").val();
	var marketprice = $("#marketprice").val();
	var productprice = $("#productprice").val();
	var unitname = $("#unitname").val();
	var recommend = $("input[type='radio'][name='recommend']:checked").val();
	var isspecial = $("input[type='radio'][name='isspecial']:checked").val();
	var taste = $("#taste").val();
	var status = $("#status").val();
	var description = $("#description").val();
	var displayorder = $("#displayorder").val();
	if(!checkValueWithInfo(title,"食谱名称不能为空!")){
		layer.close(saveing);
		return;
	}
	if(!checkValueWithInfo(productprice,"请输入成品价格!")){
		layer.close(saveing);
		return;
	}
	if(!checkValueWithInfo(marketprice,"请输入市场价格!")){
		layer.close(saveing);
		return;
	}
	if(!checkValueWithInfo(unitname,"请输入计量单位!")){
		layer.close(saveing);
		return;
	}
	if(!checkValueWithInfo(taste,"请输入口味!")){
		layer.close(saveing);
		return;
	}
	var param = {};
	param["goods.title"] = title;
	param["goods.cid"] = cid;
	param["goods.marketprice"] = marketprice;
	param["goods.productprice"] = productprice;
	param["goods.unitname"] = unitname;
	param["goods.displayorder"] = displayorder;
	param["goods.recommend"] = recommend;
	param["goods.isspecial"] = isspecial;
	param["goods.taste"] = taste;
	param["goods.description"] = description;
	param["goods.thumb"] = thumb;
	param["goods.id"] = id;
	param["goods.status"] = status;
	$.ajax({
		type:"POST",
		url:"/selforder/api/goods/updateGoods.action",
		data:param,
		dataType:"json",
		success:function(res){
			layer.close(saveing);
			var retCode = res.retCode;
			var message = res.message;
			showResult(retCode,message);
			window.location.href = "/selforder/system/goods/goodsList.jsp";
		}
	});
}

/**
 * 检查食谱编码是否已存在
 */
function checkGoodsName(){
	var title = $("#title").val();
	if(!checkValue(title)){
		return;
	}else{
		$.ajax({
			type:"POST",
			url:"/selforder/api/goods/getGoodsInfo.action",
			data:{"goods.title":title},
			dataType:"json",
			success:function(res){
				var retCode = res.retCode;
				var message = res.message;
				if(retCode == 0 ){//食谱已存在
					layer.msg("该名称已存在，请更换!",{icon:5});
					$("#title").val("");
				}else{
					return;
				}
			}
		});
	}
}