var websocket;
var sound = "messageTip/audio/news.mp3";//提示音
var sound = "messageTip/audio/notify.wav";//提示音
var messageNo = "";

//Tab页初始化
function linkMainTab(url,tabcode,title){
	//首先查找tabcode的tab是否存在
	var tab = $("#"+tabcode).length;
	//如果找到了激活该tab
	if(tab> 0){
		$("#mainTabs li").removeClass("active");//取消当前已激活的tab卡选项
		$("#tabcontent div").removeClass("active");//取消当前已激活的tab卡内容
		$("#"+tabcode).parent().addClass("active");//将当前选项卡选项设置为激活状态
		$("#"+tabcode+"_tab").addClass("active");//将当前选项卡内容设置为激活状态
		$("#"+tabcode+'_tab').children().attr("src",url);
	}else{
	//未找到该选项卡则创建该选项卡并将d该选项卡激活
		$("#mainTabs li").removeClass("active");//取消当前已激活的tab卡选项
		$("#tabcontent div").removeClass("active");//取消当前已激活的tab卡内容
		var tabli = '<li class="active"><a href="#'+tabcode+'_tab" data-toggle="tab" id="'+tabcode+'">'+title+'</a></li>';//创建tab卡li 并设置为激活状态
		var tabcontentdiv = '';
		tabcontentdiv += '<div class="tab-pane active" id="'+tabcode+'_tab">                                                                 ';
		tabcontentdiv += '  <iframe src="'+url+'" style="margin: 0; padding: 0; border: 0px;" width="100%" height="900px" ></iframe>';
		tabcontentdiv += '</div>                                                                                                      ';
		$("#mainTabs").append(tabli);
		$("#tabcontent").append(tabcontentdiv);
	}
}
/**
 * 修改密码
 */
function updatePassword(){
	var password = $("#password").val();
	var newpassword = $("#newpassword").val();
	var newpasswordagain= $("#newpasswordagain").val();
	if(checkValue(password,"原始密码不能为空！")<0){
		return;
	}
	if(checkValue(newpassword,"新密码不能为空！")<0){
		return;
	}
	if(checkValue(newpasswordagain,"请输入确认密码！")<0){
		return;
	}
	if(newpassword != newpasswordagain){
		layer.alert("两次输入密码不一致！",{icon:5});
		return;
	}
	
	var param = {};
	param["employee.password"] = password;
	param["employee.newpassword"] = newpassword;
	$.ajax({
		type:"POST",
		url:"/selforder/api/employee/updatePassword.action",
		data:param,
		dataType:"json",
		success:function(data){
			$("#updatePasswordWin").modal('hide');
			var retCode = data.retCode;
			var message = data.message;
			if(retCode < 0 ){//修改失败！
				layer.msg(message, {icon: 5});
			}else{//修改成功注销当前用户
				window.location.href = "/selforder/j_spring_security_logout";
			}
		}
	});
}

/**
 * 显示修改密码窗口
 */
function showUpdateWin(){
	$("#password").val("");
	$("#newpassword").val("");
	$("#newpasswordagain").val("");
	$("#updatePasswordWin").modal('show');
}

/**
 * 提交修改
 */
function saveBtn(e){
	$(e).popover({
		placement:"left",
		trigger:"click",
		html:true,
		title:"确定操作？",
		content:'<button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button> <button type="button" class="btn btn-primary" onclick="updatePassword()">确定</button>',
	});
	$(e).popover('show');
}

//******************************webSocket操作start****************************
var localhost = "localhost";
localhost= "www.shcebang.com";
function initWebSocket(){ 
 	if(window.WebSocket){
 		websocket = new WebSocket(encodeURI('ws://'+localhost+':9700/selforder/api/SendMsg?empid='+empid));
 		websocket.onopen = function() {  
 		    //连接成功  
 		    //$("#content").append("<p>连接成功!  </p>");
 		    //alert("连接成功！");
 		}; 
 		websocket.onerror = function() {  
 		    //连接失败  
 		    //$("#content").append("<p>连接失败  ！  </p>");
 		    //alert("连接失败  ！");
 		};  
 		websocket.onmessage = function(messageObj) {
 			var message = messageObj.data;
 			message = JSON.parse(message);
 			var no = message.NO;//消息编码
 			if(messageNo.indexOf(no) == -1){//判断消息是否重复
 				messageNo += no;
 				showPushMessage(message);
 			}
 		};
 	}else{
 		alert("浏览器不支持WebSocket,请更新您的浏览器！");
 	}
 }
 //******************************webSocket操作end****************************
//退出系统
function logOut(){
	 websocket.close();
	 window.location.href = "/selforder/j_spring_security_logout";
}

/**
 * 处理推送的消息
 * @param message 消息对象
 */
function showPushMessage(message){
	var messageType = message.messageType;//消息类型 createOrder,updateOrder,payOrder,comment
	if(messageType == "createOrder" || messageType == "updateOrder" || messageType == "payOrder"){
		execOrderMsg(message);//处理订单推送的消息
	}else if(messageType == "comment"){
		execCommentMsg(message);//处理评论推送的消息
	}else if(messageType == "callService"){
		execCallServiceMsg(message);
	}
	
}

/**
 * 处理订单推送的消息
 * @param message
 */
function execOrderMsg(message){
	var messageType = message.messageType;
	var diningMode = message.diningMode;//订单类型：1、店内点单   2、外卖订单  3、预定订单
	var ordersn = message.ordersn;//订单编号
	var message = "";
	//处理店内点单消息
	if(messageType == "createOrder"){
		message = "订单【"+ordersn+"】已创建,请及时确认！";
	}else if(messageType == "updateOrder"){
		message = "订单【"+ordersn+"】已更新,请及时查看！";
	}else if(messageType == "payOrder"){
		message = "订单【"+ordersn+"】已付款,请及时查看！";
	}
	$("#incomeOrder_a").css({"-webkit-animation":"twinkling 1s infinite ease-in-out"}); //店内点单图标动画
	$.notifySetup({sound:sound});//播放提示音
	$('<p>'+message+'</p>').notify();//弹出提示音
	var appendLi = "";
	appendLi += '<li>                                                ';
	appendLi += '  <a href="#">                                      ';
	appendLi += '    <i class="fa  fa-pencil-square-o text-aqua">'+message+'</i>';
	appendLi += '  </a>                                              ';
	appendLi += '</li>                                               ';
	$("#incomeOrder_menu").prepend(appendLi);//将最新的消息插入第一条
	var incomeOrder_menu_length = $("#incomeOrder_menu").children().length;//下拉列表中当前的数据行数
	$("#incomeOrder_num").html(incomeOrder_menu_length);
}

/**
 * 处理评论推送的消息
 * @param message
 */
function execCommentMsg(message){
	if(!checkValue(message)){
		return;
	}
	var username = message.username;
	var nickname = message.nickname;
	var description = message.description;
	var crtdate = message.crtdate;
	var rid = message.rid;
	var showMessage = "新评论："+description;
	$("#comment_a").css({"-webkit-animation":"twinkling 1s infinite ease-in-out"}); //店内点单图标动画
	$.notifySetup({sound:sound});//播放提示音
	$('<p>'+showMessage+'</p>').notify();//弹出提示音
	var message = "";
	message +='<li><!-- start message -->                                                        ';
	message +='  <a href="#">                                                                    ';
	message +='    <div class="pull-left">                                                       ';
	message +='      <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" />';
	message +='    </div>                                                                        ';
	message +='    <h4>                                                                          ';
	message +='      '+username+'                                                               ';
	message +='      <small><i class="fa fa-clock-o"></i> '+crtdate+'</small>                       ';
	message +='    </h4>                                                                         ';
	message +='    <p>'+description+'</p>                                                               ';
	message +='  </a>                                                                            ';
	message +='</li><!-- end message -->                                                         ';
	$("#comment_menu").prepend(message);//将最新的消息插入第一条
	var comment_menu_length = $("#comment_menu").children().length;//下拉列表中当前的数据行数
	$("#comment_num").html(comment_menu_length);
}

/**
 * 处理呼叫服务推送的消息
 * @param message
 */
function execCallServiceMsg(message){
	if(!checkValue(message)){
		return;
	}
	var tablecode = message.tablecode;
	var room_name = message.room_name;
	var callTime = message.callTime;
	var showMessage = "【"+tablecode+"】"+"号餐桌呼叫服务请及时处理！";
	$("#callService_a").css({"-webkit-animation":"twinkling 1s infinite ease-in-out"}); //店内点单图标动画
	$.notifySetup({sound:sound});//播放提示音
	$('<p>'+showMessage+'</p>').notify();//弹出提示音
	var appendLi = "";
	appendLi += '<li>                                                ';
	appendLi += '  <a href="#">                                      ';
	appendLi += '    <span>'+showMessage+'<small>'+callTime+'</small></span>';
	appendLi += '  </a>                                              ';
	appendLi += '</li>                                               ';
	$("#callService_menu").prepend(appendLi);//将最新的消息插入第一条
	var callService_menu_length = $("#callService_menu").children().length;//下拉列表中当前的数据行数
	$("#callService_num").html(callService_menu_length);
}

/**
 * 取消消息提示
 * @param em
 */
function cancelMessageNotify(em){
	$("#"+em+"_a").attr("style","");
	$("#"+em+"_num").html("");
}


/**
 * 清空数据
 * @param em
 */
function clearDate(em){
	$("#"+em+"_menu").children().remove();
	
}

/**
 * 更新个人信息
 * @param empid 
 */
function updateSelfInfo(empid){
	//window.location.href = "system/employee/saveEmployee.jsp?opt=update&empid="+empid;
}

function showCurrentDate(){
	setInterval(function(){
		var d=new Date();
		var day=new Array(7);
		day[0]="星期日";
		day[1]="星期一";
		day[2]="星期二";
		day[3]="星期三";
		day[4]="星期四";
		day[5]="星期五";
		day[6]="星期六";
		var week = day[d.getDay()];
		var currDate = d.Format("yyyy-MM-dd hh:mm:ss");
		var show = "今天是："+currDate+"，"+week;
		$("#showCurrentDate").html(show);
	}, 1000);
}

