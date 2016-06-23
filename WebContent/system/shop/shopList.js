var total = 0;
$(function(){
	//加载商户列表
	loadShopList();
	keyEvent();
});
/**
 * 加载商户列表
 */
function loadShopList(param){
	var load = layer.load(2, {shade: [1, 'rgba(0,0,0,.5)']});
	if(typeof(param) == "undefined" || param == null || param == ""){
		param = "";
	}
	//清除历史数据
	$("tr[tag='append']").remove();
	$.ajax({
		type:"POST",
		url:"/selforder/api/shop/getShopList.action",
		data:param,
		dataType:'json',
		success:function(data){
			layer.close(load);
			if(typeof(data) == "undefined" || data == ""){
				return;
			}else{
				total = data.total;
				var rows = data.rows;
				if(rows.length > 0){
					for(var i=0;i<rows.length;i++){
						var tr = "";
						var row = rows[i];
						var sid = row.sid;
						var bname = row.bname;
						var sname  = row.sname;
						var linkman = row.linkman;
						var phone = row.phone;
						var isoutsell = row.isoutsell;
						if(isoutsell == 0){
							isoutsell = "否";
						}else{
							isoutsell = "是";
						}
						var isarray = row.isarray;
						if(isarray == 0){
							isarray = "否";
						}else{
							isarray = "是";
						}
						var address = row.address;
						var longitude = row.longitude;
						var latitude = row.latitude;
						tr +='<tr tag="append" sid="'+sid+'" class="animated flipInX">                                     ';
						tr +='	<td>'+(i+1)+'</td>                             ';
						tr +='	<td>'+bname+'</td>    ';
						tr +='	<td>'+sname+'</td>                        ';
						tr +='	<td>'+linkman+'</td>                  ';
						tr +='	<td>'+phone+'</td>                          ';
						tr +='	<td>'+isoutsell+'</td>';
						tr +='	<td>'+isarray+'</td>                    ';
						tr +='	<td>'+address+'</td>                    ';
						tr +='	<td>'+longitude+'</td>                    ';
						tr +='	<td>'+latitude+'</td>                    ';
						tr +='	<td><button type="button" class="btn btn-danger" onclick="updateShop(\''+sid+'\')">修改</button></td>                    ';
						tr +='</tr>                                    ';
						$("#shoplist").append(tr);
					}
				}
			}
		}
	});
}

/**
 * 根据sid修改门店信息
 * @param sid
 */
function updateShop(sid){
	if(typeof(sid) == "undefined" || sid == ""){
		return;
	}else{
		window.location.href="saveShop.jsp?opt=update&sid="+sid;
	}
}

/**
 * 搜索
 */
function search(){
	var bname = $("#bname_search").val();
	var phone = $("#phone_search").val();
	var status = $("#status_search").val();
	var legaler = $("#legaler_search").val();
	var param = {};
	param["business.bname"] = bname;
	param["business.phone"] = phone;
	param["business.status"] = status;
	param["business.legaler"] = legaler;
	loadBusinessList(param);
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

