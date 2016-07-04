var zTree, rMenu;
var autoExpand = 0;
$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting);
	zTree = $.fn.zTree.getZTreeObj("treeDemo");
	rMenu = $("#rMenu");
});

/**
 * 显示右键菜单
 * @param type
 * @param x
 * @param y
 */
function showRMenu(type, x, y) {
	$("#rMenu ul").show();
	if (type=="root") {
		$("#m_del").hide();
		$("#m_check").hide();
		$("#m_unCheck").hide();
	} else {
		$("#m_del").show();
		$("#m_check").show();
		$("#m_unCheck").show();
	}
	rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

	$("body").bind("mousedown", onBodyMouseDown);
}

/**
 * 隐藏右键菜单
 */
function hideRMenu() {
	if (rMenu) rMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}

/**
 * 注册鼠标右键事件
 * @param event
 */
function onBodyMouseDown(event){
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
		rMenu.css({"visibility" : "hidden"});
	}
}

/**
 * 显示新增节点信息
 */
function addTreeNode() {
	hideRMenu();
	clearWinData();
	var selectTreeNode = zTree.getSelectedNodes()[0];
	if (selectTreeNode) {
		$("#editTreeWin").modal("show");
	} else {
		layer.msg("请选择要操作的组",{icon:5});
	}
}

/**
 * 新增树节点
 */
function saveEditTree(){
	var optType = $("#editTreeWin").attr("openType");
	if("update" == optType){//修改节点
		var oid = $("#editTreeWin").attr("oid");
		if(typeof(oid) == "undefined" || oid == "" || oid == null){
			layer.msg("保存参数异常，请重试!",{icon:5});
			return;
		}
		var oname = $("#edit_oname").val();
		var seq = $("#edit_seq").val();
		var param = {};
		param["organization.oname"] = oname;
		param["organization.parentid"] = parentid;
		param["organization.seq"] = seq;
		param["organization.oid"] = oid;
		$.ajax({
			type:"POST",
			url:"/selforder/api/organization/updateOrganization.action",
			data:param,
			dataType:"json",
			success:function(res){
				$("#editTreeWin").modal("hide");
				zTree.refresh();
			}
		});
	}else{//新增节点
		var selectTreeNode = zTree.getSelectedNodes()[0];
		var parentid = "";
		if(selectTreeNode){
			parentid =  selectTreeNode.id;
		}else{
			layer.msg("获取父节点参数异常，请重试！",{icon:5});
			return;
		}
		var oname = $("#edit_oname").val();
		var seq = $("#edit_seq").val();
		var param = {};
		param["organization.oname"] = oname;
		param["organization.parentid"] = parentid;
		param["organization.seq"] = seq;
		$.ajax({
			type:"POST",
			url:"/selforder/api/organization/saveOrganization.action",
			data:param,
			dataType:"json",
			success:function(res){
				$("#editTreeWin").modal("hide");
				zTree.refresh();
			}
		});
	}
}

/**
 * 机构树配置参数（ajax请求动态加载数据）
 */
var setting = {
	async: {
		enable: true,
		url:"/selforder/api/organization/getorganizationList4Tree.action",
		autoParam:["id=organization.oid", "parentNode=organization.parentid", "level=organization.level"],
		dataFilter:filter
	},
	view:{
		dblClickExpand:true
	},
	check:{
		enable:true
	},
	callback:{
		onRightClick:treeRightClick,
		onAsyncSuccess:function(){
			if(autoExpand == 0 ){
				try{
					var rootNode = zTree.getNodeByParam("isRoot","root",null);
					zTree.expandNode(rootNode);
					autoExpand++;
				}catch(err){
					console.error(err);
				}
			}
			
		}
	}
};

/**
 * 过滤组装数据
 * @param treeId
 * @param parentNode
 * @param childNodes
 * @returns
 */
function filter(treeId, parentNode, childNodes) {
	var treeNodes = new Array;
	var tempNodes = childNodes.data;
	//if (!tempNodes) return null;
	if(!parentNode){
		//设置根节点
		var rootNode = {};
		rootNode["name"] = "全部";
		rootNode["id"] = "";
		rootNode["parentid"] = "";
		rootNode["level"] = "";
		rootNode["levelpath"] = "";
		rootNode["seq"] = "";
		rootNode["isRoot"] = "root";
		rootNode["open"] = true;
		rootNode["isParent"] = true;
		rootNode["click"] = "loadEmpList('','')";
		treeNodes.push(rootNode);
	}else{
		//设置子节点
		for (var i=0, l=tempNodes.length; i<l; i++) {
			var treeNode = {};
			var child_oid = tempNodes[i].child_oid;
			treeNode["name"] = tempNodes[i].oname;
			treeNode["id"] = tempNodes[i].oid;
			treeNode["parentid"] = tempNodes[i].parentid==null?tempNodes[i].parentid:"";
			treeNode["level"] = tempNodes[i].level;
			treeNode["levelpath"] = tempNodes[i].levelpath;
			treeNode["seq"] = tempNodes[i].seq;
			treeNode["click"] = "loadEmpList('"+tempNodes[i].oid+"','"+tempNodes[i].oname+"')";
			if(typeof(child_oid) == "undefined" || child_oid == ""){
				treeNode["isParent"] = false;
				treeNode["icon"] = "../../img/users.png";
			}else{
				treeNode["isParent"] = true;
			}
			treeNodes.push(treeNode);
		}
	}
	return treeNodes;
}

/**
 * 根据OID加载员工
 * @param oid
 */
function loadEmpList(oid,oname){
	curr_oid = oid;
	curr_oname = oname;
	var param = {};
	param["employee.oid"] = curr_oid;
	loadOrganizationList("init",param);
}

/**
 * 右键菜单
 * @param event
 * @param treeId
 * @param treeNode
 */
function treeRightClick(event, treeId, treeNode) {
	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		zTree.cancelSelectedNode();
		showRMenu("root", event.clientX, event.clientY);
	}else if (treeNode && !treeNode.noR) {
		zTree.selectNode(treeNode);
		showRMenu("node", event.clientX, event.clientY);
	}
}

/**
 * 加载树节点信息
 */
function showTreeNodeInfo(){
	hideRMenu();
	clearWinData();
	var nodes = zTree.getSelectedNodes();
	if (nodes && nodes.length>0) {
		var oid = nodes[0].id;
		var oname = nodes[0].name;
		var seq = nodes[0].seq;
		$("#edit_oname").val(oname);
		$("#edit_seq").val(seq);
		$("#editTreeWin").attr("openType","update");
		$("#editTreeWin").attr("oid",oid);
		$("#editTreeWin").modal("show");
	}else{
		layer.msg("请选择部门进行操作",{icon:5});
	}
}

/**
 * 删除部门
 */
function removeTreeNode() {
	hideRMenu();
	var nodes = zTree.getSelectedNodes();
	if (nodes && nodes.length>0) {
		var oid = nodes[0].id;
		layer.confirm("删除该节点的同时将会同步删除该节点下的子节点，且改节点下的所有员工将全部移除。确定要删除该节点吗？",
			{btn:["确定","取消"]},
			function(){//确定
				$.ajax({
					type:"POST",
					url:"/selforder/api/organization/removeOrgAndEmp.action",
					data:{"organization.oid":oid},
					dataType:"json",
					success:function(res){
						var retCode = res.retCode;
						var message = res.message;
						if(retCode < 0 ){
							layer.msg(message,{icon:5});
						}else{
							layer.msg(message,{icon:6});
						}
						zTree.refresh();
					}
				});
			},
			function(){//取消
				layer.closeAll();
			});
	}else{
		layer.msg("请选择部门进行操作",{icon:5});
	}
}

/**
 * 清除历史数据
 */
function clearWinData(){
	$("#edit_oname").val("");
	$("#edit_seq").val("");
	$("#editTreeWin").attr("openType","");
	$("#editTreeWin").attr("oid","");
}

/**
 * 查看部门关联的权限
 */
function showRoles(){
	
}