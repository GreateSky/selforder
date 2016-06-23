var shopimg = "";
$(function(){
	//判断是否更新操作
	if("update"== opt){
		getShopInfo();
	}
});

//获取商户基本信息
function getShopInfo(){
	var getinfo_load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	if(typeof(sid) == "undefined" || sid == ""){
		layer.close(getinfo_load);
		layer.alert("获取门店基本信息参数异常！", {icon: 5});
		return;
	}else{
		$.ajax({
			type:"POST",
			url:"/selforder/api/shop/getShopInfo.action",
			data:{"shop.sid":sid},
			dataType:"json",
			success:function(data){
				var shopinfo = data.data;
				layer.close(getinfo_load);
				if(typeof(shopinfo) == "undefined" || shopinfo == ""){
					layer.close(getinfo_load);
					layer.alert("获取门店基本信息异常！", {icon: 5});
					return;
				}else{
					var bname = shopinfo.bname;
					var sname = shopinfo.sname;
					var linkman = shopinfo.linkman;
					var phone = shopinfo.phone;
					var address = shopinfo.address;
					var isoutsell = shopinfo.isoutsell;
					var isarry = shopinfo.isarry;
					var longitude = shopinfo.longitude;
					var latitude = shopinfo.begindate;
					shopimg= shopinfo.shopimg;
					$("#bname").val(bname);
					$("#sname").val(sname);
					$("#linkman").val(linkman);
					$("#phone").val(phone);
					$("#address").val(address);
					$("#isoutsell").val(isoutsell);
					$("#isarry").val(isarry);
					$("#longitude").val(longitude);
					$("#latitude").val(latitude);
					var imgsrc = "/selforder/api/fileutil?method=download&fileid="+shopimg;
					$("#shopimg").attr("src",imgsrc);
				}
			}
		});
	}
}

//上传附件
function uploadFile(){
	ajaxFileUpload("saveShop");
}

//保存商户信息
function saveShop(args){
	if(typeof(args) != "undefined" && args != ""){ 
		shopimg = args[0].fileid;
	}
	if("update"== opt){
		updateShop();
	}else{
		addShop();
	}
}

/**
 * 更新商户
 */
function updateShop(){
	//验证参数
	if(checkParam() < 0){
		return;
	}
	//组装参数
	var param = {};
	var load = layer.load(2);
	$(".form-horizontal *[name *='shop.']").each(function(i){
		var name = $(this).attr("name");
		var value = $(this).val();
		param[name] = value;
	});
	param["shop.sid"] = sid;
	param["shop.shopimg"] = shopimg;
	//发送请求
	$.ajax({
		type:"POST",
		url:"/selforder/api/shop/updateShop.action",
		data:param,
		dataType:'json',
		success:function(data){
			//处理返回结果
			var retCode = data.retCode;
			var message = data.message;
			layer.close(load);
			if(retCode < 0){
				layer.msg(message, {icon: 2});
			}else{
				layer.msg(message, {icon: 1});
			}
			setTimeout(function(){
				window.location.href = 'shopList.jsp';
			}, 1000);
		}
	});
}
//新增商户
function addShop(){
	var param = {};
	var load = layer.load(2);
	$(".form-horizontal *[name *='shop.']").each(function(i){
		var name = $(this).attr("name");
		var value = $(this).val();
		param[name] = value;
	});
	param["shop.shopimg"] = shopimg;
	$.ajax({
		type:"POST",
		url:"/selforder/api/shop/saveShop.action",
		data:param,
		dataType:'json',
		success:function(data){
			var retCode = data.retCode;
			var message = data.message;
			layer.close(load);
			layer.msg(message, {icon: 1});
			setTimeout(function(){
				window.location.href = 'businessList.jsp';
			}, 1000);
		}
	});
}

/**
 * 参数验证
 */
function checkParam(){
	var temp = -1;
	var sname = $("#sname").val();
	var linkman = $("#linkman").val();
	var phone = $("#phone").val();
	var address = $("#address").val();
	var isoutsell = $("input[name='shop.isoutsell']:checked").val();
	var isarry =  $("input[name='shop.isarray']:checked").val();
	var longitude = $("#longitude").val();
	var latitude = $("#latitude").val();
	if(typeof(sname) == "undefined" || sname == "" || sname == null || sname == "null"){
		layer.alert("门店名称不能为空！",{icon:5});
	}else if(typeof(linkman) == "undefined" || linkman == "" || linkman == null || linkman == "null"){
		layer.alert("负责人不能为空！",{icon:5});
	}else if(typeof(phone) == "undefined" || phone == "" || phone == null || phone == "null"){
		layer.alert("电话不能为空！",{icon:5});
	}else if(typeof(isoutsell) == "undefined" || isoutsell == "" || isoutsell == null || isoutsell == "null"){
		layer.alert("请选择是否开启外卖！",{icon:5});
	}else if(typeof(isarry) == "undefined" || isarry == "" || isarry == null || isarry == "null"){
		layer.alert("请选择是否开启排号！",{icon:5});
	}else if(typeof(address) == "undefined" || address == "" || address == null || address == "null"){
		layer.alert("地址不能为空！",{icon:5});
	}else{
		temp = 0;
	}
	return temp;
}

