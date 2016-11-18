var shopimg = "";
var myValue;
var map;
var marker;
$(function(){
	dingwei();//定位
	addresskeyEvent();//搜索输入框回车按钮事件注册
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
			data:{"shop.id":sid},
			dataType:"json",
			success:function(data){
				var shopinfo = data.data;
				layer.close(getinfo_load);
				if(typeof(shopinfo) == "undefined" || shopinfo == ""){
					layer.close(getinfo_load);
					layer.alert("获取门店基本信息异常！", {icon: 5});
					return;
				}else{
					$("#title").val(shopinfo.title);
					var logo = shopinfo.logo;
					$("#tel").val(shopinfo.tel);
					$("#info").val(shopinfo.info);
					$("#announce").val(shopinfo.announce);
					$("#location_p").val(shopinfo.location_p);
					$("#location_c").val(shopinfo.location_c);
					$("#location_a").val(shopinfo.location_a);
					$("#address").val(shopinfo.address);
					$("#longitude").val(shopinfo.lng);
					$("#latitude").val(shopinfo.lat);
					$("#begintime").val(shopinfo.begintime);
					$("#endtime").val(shopinfo.endtime);
					$("#thumb_url").val(shopinfo.thumb_url);
					$("#delivery_radius").val(shopinfo.delivery_radius);
					$("#sendingprice").val(shopinfo.sendingprice);
					$("#delivery_within_days").val(shopinfo.delivery_within_days);
					if(shopinfo.enable_wifi == 1){
						$("#enable_wifi").prop("checked", true);
					}
					if(shopinfo.enable_card == 1){
						$("#enable_card").prop("checked", true);
					}
					if(shopinfo.enable_room == 1){
						$("#enable_room").prop("checked", true);
					}
					if(shopinfo.enable_park == 1){
						$("#enable_park").prop("checked", true);
					}
					if(shopinfo.is_rest == 1){
						$("#is_rest").prop("checked", true);
					}
					if(shopinfo.is_show == 1){
						$("#is_show").prop("checked", true);
					}
					if(shopinfo.is_meal == 1){
						$("#is_meal").prop("checked", true);
					}
					if(shopinfo.is_delivery == 1){
						$("#is_delivery").prop("checked", true);
					}
					if(shopinfo.is_reservation == 1){
						$("#is_reservation").prop("checked", true);
					}
					if(shopinfo.is_queue == 1){
						$("#is_queue").prop("checked", true);
					}
					var imgsrc = "/selforder/api/fileutil?method=download&fileid="+logo;
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
	if(!checkValueWithInfo($("#title").val(),"门店名称不能为空！"))return;
	if(!checkValueWithInfo($("#tel").val(),"电话不能为空！"))return;
	if(!checkValueWithInfo($("#location_p").val(),"省份不能为空！"))return;
	if(!checkValueWithInfo($("#location_c").val(),"城市不能为空！"))return;
	if(!checkValueWithInfo($("#location_a").val(),"县/区不能为空！"))return;
	if(!checkValueWithInfo($("#address").val(),"地址不能为空！"))return;
	if(!checkValueWithInfo($("#longitude").val(),"经度不能为空！"))return;
	if(!checkValueWithInfo($("#latitude").val(),"纬度不能为空！"))return;
	if(!checkValueWithInfo($("#begintime").val(),"营业开始时间不能为空！"))return;
	if(!checkValueWithInfo($("#endtime").val(),"营业结束时间不能为空！"))return;
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
	//组装参数
	var param = {};
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	param["shop.title"] = $("#title").val();
	param["shop.tel"] = $("#tel").val();
	param["shop.info"] = $("#info").val();
	param["shop.announce"] = $("#announce").val();
	param["shop.location_p"] = $("#location_p").val();
	param["shop.location_c"] = $("#location_c").val();
	param["shop.location_a"] = $("#location_a").val();
	param["shop.address"] = $("#address").val();
	param["shop.lng"] = $("#longitude").val();
	param["shop.lat"] = $("#latitude").val();
	param["shop.begintime"] = $("#begintime").val();
	param["shop.endtime"] = $("#endtime").val();
	param["shop.thumb_url"] = $("#thumb_url").val();
	param["shop.delivery_radius"] = $("#delivery_radius").val();
	param["shop.sendingprice"] = $("#sendingprice").val();
	param["shop.delivery_within_days"] = $("#delivery_within_days").val();
	param["shop.enable_wifi"] = $("#enable_wifi").prop("checked")?1:0;
	param["shop.enable_card"] = $("#enable_card").prop("checked")?1:0;
	param["shop.enable_room"] = $("#enable_room").prop("checked")?1:0;
	param["shop.enable_park"] = $("#enable_park").prop("checked")?1:0;
	param["shop.is_rest"] = $("#is_rest").prop("checked")?1:0;
	param["shop.is_show"] = $("#is_show").prop("checked")?1:0;
	param["shop.is_meal"] = $("#is_meal").prop("checked")?1:0;
	param["shop.is_delivery"] = $("#is_delivery").prop("checked")?1:0;
	param["shop.is_reservation"] = $("#is_reservation").prop("checked")?1:0;
	param["shop.is_queue"] = $("#is_queue").prop("checked")?1:0;
	param["shop.content"] = $("#content").prop("checked")?1:0;
	param["shop.logo"] = shopimg;
	param["shop.id"] = sid;
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
			window.location.href = 'shopList.jsp';
		}
	});
}
//新增商户
function addShop(){
	var param = {};
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	param["shop.title"] = $("#title").val();
	param["shop.tel"] = $("#tel").val();
	param["shop.info"] = $("#info").val();
	param["shop.announce"] = $("#announce").val();
	param["shop.location_p"] = $("#location_p").val();
	param["shop.location_c"] = $("#location_c").val();
	param["shop.location_a"] = $("#location_a").val();
	param["shop.address"] = $("#address").val();
	param["shop.lng"] = $("#longitude").val();
	param["shop.lat"] = $("#latitude").val();
	param["shop.begintime"] = $("#begintime").val();
	param["shop.endtime"] = $("#endtime").val();
	param["shop.thumb_url"] = $("#thumb_url").val();
	param["shop.delivery_radius"] = $("#delivery_radius").val();
	param["shop.sendingprice"] = $("#sendingprice").val();
	param["shop.delivery_within_days"] = $("#delivery_within_days").val();
	param["shop.enable_wifi"] = $("#enable_wifi").prop("checked")?1:0;
	param["shop.enable_card"] = $("#enable_card").prop("checked")?1:0;
	param["shop.enable_room"] = $("#enable_room").prop("checked")?1:0;
	param["shop.enable_park"] = $("#enable_park").prop("checked")?1:0;
	param["shop.is_rest"] = $("#is_rest").prop("checked")?1:0;
	param["shop.is_show"] = $("#is_show").prop("checked")?1:0;
	param["shop.is_meal"] = $("#is_meal").prop("checked")?1:0;
	param["shop.is_delivery"] = $("#is_delivery").prop("checked")?1:0;
	param["shop.is_reservation"] = $("#is_reservation").prop("checked")?1:0;
	param["shop.is_queue"] = $("#is_queue").prop("checked")?1:0;
	param["shop.content"] = $("#content").prop("checked")?1:0;
	param["shop.logo"] = shopimg;
	$.ajax({
		type:"POST",
		url:"/selforder/api/shop/saveShop.action",
		data:param,
		dataType:'json',
		success:function(data){
			layer.close(load);
			var retCode = data.retCode;
			var message = data.message;
			if(retCode < 0){
				layer.msg(message, {icon: 5});
			}else{
				layer.msg(message, {icon: 6});
			}
			window.location.href = 'shopList.jsp';
		}
	});
}

/**
 * 参数验证
 */
function checkParam(){
	
}

/**
 * 显示地图
 */
function showMap(){
	$("#mapWin").modal('show');
}

/**
 * 百度地图定位
 */
function dingwei(){
	var longitude = "";
	var latitude = "";
	// 百度地图API功能
	map = new BMap.Map("allmap");
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	//var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom("杭州",12);
	//进行定位
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			marker = new BMap.Marker(r.point);
			map.addOverlay(marker);
			map.panTo(r.point);
			map.centerAndZoom(r.point,12);
			marker.enableDragging();//可拖拽
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true});
	// 添加带有定位的导航控件
	var navigationControl = new BMap.NavigationControl({
	    // 靠左上角位置
	    anchor: BMAP_ANCHOR_TOP_LEFT,
	    // LARGE类型
	    type: BMAP_NAVIGATION_CONTROL_LARGE,
	    // 启用显示定位
	    enableGeolocation: true
	  });
	map.addControl(navigationControl);
}

/**
 * 根据输入的地址进行定位
 */
function poindByAddress(){
	var address = $("#point_address").val();
	if(!checkValue(address)){
		layer.msg("请输入地址进行定位.",{icon:5});
		return;
	}else{
		// 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		// 将地址解析结果显示在地图上,并调整地图视野
		myGeo.getPoint(address, function(point){
			if (point) {
				map.centerAndZoom(point, 16);
				marker = new BMap.Marker(point);
				map.addOverlay(marker);
				marker.enableDragging();//可拖拽
			}else{
				layer.msg("您选择地址没有解析到结果!",{icon:5});
			}
		}, "杭州市");//默认定位杭州市
	}
}

/**
 * 位置选择成功
 */
function pointOK(){
	if(!marker){
		layer.msg("定位失败,请刷新浏览器重试!",{icon:5});
		return;
	}else{
		var point = marker.getPosition();
		var lng = point.lng;
		var lat = point.lat;
		//根据坐标进行逆地址解析
		var geoc = new BMap.Geocoder();   
		geoc.getLocation(point, function(rs){
			var addComp = rs.addressComponents;
			var province = addComp.province;
			var city = addComp.city;
			var district = addComp.district;
			var street = addComp.street;
			var streetNumber = addComp.streetNumber;
			$("#location_p").val(province);
			$("#location_c").val(city);
			$("#location_a").val(district);
			$("#address").val(street+streetNumber);
			$("#longitude").val(lng);
			$("#latitude").val(lat);
			$("#mapWin").modal("hide");
			//alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
		});        
	}
}

/**
 * 地址搜索回车按钮事件注册
 */
function addresskeyEvent(){
	$("#point_address").each(function(i){
		$(this).keydown(function (event){
			var keyCode=event.keyCode ? event.keyCode:event.which?event.which:event.charCode;//解决浏览器之间的差异问题 
			if(keyCode==13){ 
				poindByAddress(); 
			} 
		});
	});
}
