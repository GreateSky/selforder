$(function(){
	setIsRead()//更新阅读状态
	loadCommentDetail();
});

/**
 * 根据userid加载评论详情
 */
function loadCommentDetail(){
	if(!checkValueWithInfo(fromuser,"评论人信息为空，请刷新后重试！")){
		return;
	}
	$.ajax({
		type:"POST",
		url:"/selforder/api/comment/getCommentDetailList.action",
		data:{"comment.from_user":fromuser},
		dataType:"json",
		success:function(res){
			var retCode = res.retCode;
			var message = res.message;
			if(retCode < 0){
				layer.msg(message,{"icon":5});
				return;
			}else{
				var rows = res.data;
				if(null != rows && rows.length > 0){
					for(var i=0;i<rows.length;i++){
						var row = rows[i];
						var rid = row.rid;
						var from_user = row.from_user;
						var nickname = row.nickname;
						var username = row.username;
						var headimgurl = row.headimgurl;
						var description = row.description;
						var crtdate = row.crtdate;
						var crtdateStr = checkValue(crtdate)?formatDate(crtdate):"";
						var replyer = row.replyer;
						var replyname = row.replyname;
						var reply = row.reply;
						var replydate = row.replydate;
						var replyheadimg = row.replyheadimg;
						var replydateStr = checkValue(replydate)?formatDate(replydate):"";
						var status = row.status;
						if(!checkValue(headimgurl)){
							headimgurl = "../../img/customer2.png";
						}
						if(!checkValue(replyheadimg)){
							replyheadimg = "../../img/server.png";
						}
						var actionBtn = "";
						if(status == 0){
							actionBtn = '<button type="button" class="btn btn-default" onclick="optComment(\''+rid+'\')">审核</button>';
						}
						//组装评论内容
						var appendComment = "";
						appendComment += '<div class="direct-chat-msg" id="'+rid+'">                                                                                  ';
						appendComment += '  <div class="direct-chat-info clearfix">                                                                      ';
						appendComment += '    <span class="direct-chat-name pull-left">'+nickname+'('+username+')</span>                                                           ';
						appendComment += '    <span class="direct-chat-timestamp pull-left">'+crtdateStr+'</span>                                                      ';
						appendComment += '  </div>                                                                                                       ';
						appendComment += '  <img class="direct-chat-img" src="'+headimgurl+'" alt="头像" />              ';
						appendComment += '  <div class="direct-chat-text" >                                                                              ';
						appendComment += '    	 '+description+'                                                                                                        ';
						appendComment += '   '+actionBtn+' 	';
						appendComment += '  </div>                                                                                                       ';
						appendComment += '</div>                                                                                                         ';
						$("#commentDetaiList").append(appendComment);
						//有回复内容时显示回复的信息
						if(checkValue(reply)){
							var appendReply = "";
							appendReply += '<div class="direct-chat-msg right">                                                              ';
							appendReply += '  <div class="direct-chat-info clearfix">                                                        ';
							appendReply += '    <span class="direct-chat-name pull-right">'+replyname+'</span>                                            ';
							appendReply += '    <span class="direct-chat-timestamp pull-left">'+replydateStr+'</span>                                        ';
							appendReply += '  </div>                                                                                         ';
							appendReply += '  <img class="direct-chat-img" src="'+replyheadimg+'" alt="头像" />';
							appendReply += '  <div class="direct-chat-text" style="color: #F49C12;">                                         ';
							appendReply += '    '+reply+'	                                                                                           ';
							appendReply += '  </div>                                                                                         ';
							appendReply += '</div>                                                                                           ';
							$("#commentDetaiList").append(appendReply);
						}
					}
				}
			}
		}
	});
}

/**
 * 操作评论
 * @param rid  评论ID
 */
function optComment(rid){
	$("#commentWin").attr("commentid",rid);
	$("#commentWin").modal('show');
}

/**
 * 保存审核的信息
 */
function saveAuditComment(){
	var rid = $("#commentWin").attr("commentid");
	var status = $("input[type='radio']:checked").val();
	var content = $("#content").val();
	var param = {};
	param["comment.rid"] = rid;
	param["comment.status"] = status;
	param["comment.isread"] = 0;
	param["comment.reply"] = content;
	layer.confirm("确定要提交吗?",
				{"btn":["确定","取消"]},
				function(){
					layer.closeAll();
					$.ajax({
						type:"POST",
						url:"/selforder/api/comment/updateComment.action",
						data:param,
						dataType:"json",
						success:function(res){
							var retCode = res.retCode;
							var message = res.message;
							if(retCode < 0){
								layer.msg(message,{"icon":5});
							}else{
								layer.msg(message,{"icon":6});
							}
							$("#commentWin").attr("commentid","");
							$("#commentWin").modal('hide');
							window.location.href = "commentList.jsp";
						}
					});
				},
				function(){
					layer.closeAll();
				});
}

/**
 * 更新阅读状态
 */
function setIsRead(){
	$.ajax({
		type:"POST",
		url:"/selforder/api/comment/updateIsRead.action",
		data:{"comment.from_user":fromuser},
		dataType:"json",
		success:function(res){
		
		}
	});
}