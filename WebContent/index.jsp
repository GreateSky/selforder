<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page import="com.selforder.util.Context" %>
<%@page import="com.selforder.bean.UserInfo" %>
<%
	UserInfo userinfo = new Context().getLoginUserInfo();
	String empname = userinfo.getName();
	String empcode = userinfo.getCode();
	String empid = userinfo.getEmpid();
	String emp_type = userinfo.getType();
%> 
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>SelfOrder</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.4 -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- FontAwesome 4.3.0 -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons 2.0.0 -->
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link href="dist/css/skins/skin-yellow-light.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="plugins/iCheck/flat/blue.css" rel="stylesheet" type="text/css" />
    <!-- bootstrap wysihtml5 - text editor -->
    <link href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />
	<!-- 消息提醒 -->
	<link rel="stylesheet" type="text/css" href="messageTip/css/default.css">
	<link rel="stylesheet" type="text/css" href="messageTip/css/jquery.notify.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	@-webkit-keyframes twinkling{    /*透明度由0到1*/
		    0%{
		       opacity:0; /*透明度为0*/
		     }
		    100%{
		       opacity:1; /*透明度为1*/
		    }
		  }
    </style>
    <script type="text/javascript">
    	var empid = '<%=empid%>';
    	var empname = "<%=empname%>";
		var empcode = "<%=empcode%>";
		var emp_type = '<%=emp_type%>';
    </script>
  </head>
  <body class="skin-yellow-light sidebar-mini">
    <div class="wrapper">
      <header class="main-header">
        <!-- Logo展示start -->
        <a href="" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>S</b>elf</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>S</b>elfOrder&nbsp;<small></small></span>
        </a>
        <!-- Logo展示end -->
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- Messages: style can be found in dropdown.less-->
              <li class="dropdown messages-menu" id="comment">
                <a id="showCurrentDate" href="#" class="dropdown-toggle"></a>
              </li>
              <li class="dropdown messages-menu" id="comment">
                <a id="comment_a" href="#" class="dropdown-toggle" data-toggle="dropdown" onclick="cancelMessageNotify('comment')">
                  <i class="fa fa-comments"></i>
                  <span class="label label-success" id="comment_num"></span>
                </a>
                <ul class="dropdown-menu" style="width: 400px">
                  <li>
                    <!-- inner menu: contains the actual data -->
                    <ul class="menu" id="comment_menu">
                      
                    </ul>
                  </li>
                  <li class="footer"><a href="javascript:void();" onclick="clearDate('comment')">[清空列表]</a></li>
                </ul>
              </li>
              <!-- Notifications: style can be found in dropdown.less -->
              <li id="incomeOrder" class="dropdown notifications-menu">
                <a id="incomeOrder_a" href="javascript:void()" onclick="cancelMessageNotify('incomeOrder');" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-bell-o"></i>
                  <span id="incomeOrder_num" class="label label-info"></span>
                </a>
                <ul class="dropdown-menu" style="width: 400px">
                  <li>
                    <!-- inner menu: contains the actual data -->
                    <ul class="menu" id="incomeOrder_menu">
                      
                    </ul>
                  </li>
                  <li class="footer"><a href="javascript:void();" onclick="clearDate('incomeOrder')">[清空列表]</a></li>
                </ul>
              </li>
              <!-- Tasks: style can be found in dropdown.less -->
              <li class="dropdown tasks-menu" id="callService">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="callService_a" onclick="cancelMessageNotify('callService');">
                  <i class="fa  fa-bullhorn"></i>
                  <span class="label label-danger" id="callService_num"></span>
                </a>
                <ul class="dropdown-menu" style="width: 400px">
                  <li>
                    <!-- inner menu: contains the actual data -->
                    <ul class="menu" id="callService_menu">
                      
                    </ul>
                  </li>
                  <li class="footer"><a href="javascript:void();" onclick="clearDate('callService')">[清空列表]</a></li>
                </ul>
              </li>
              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image" />
                  <span class="hidden-xs" id="empnameBig"></span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                    <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" onclick="updateSelfInfo('<%=empid%>')" />
                    <p id="empname">
                      <small id="empcode"></small>
                    </p>
                  </li>
                  <!-- Menu Body -->
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="javascript:showUpdateWin()" class="btn btn-default btn-flat" >修改密码</a>
                    </div>
                    <div class="pull-right">
                      <a href="javascript:void();" onclick="logOut()" class="btn btn-default btn-flat">退出系统</a>
                    </div>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </nav>
      </header>
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          <!-- Sidebar user panel -->
          <div class="user-panel">
            <div class="pull-left image">
              <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" />
            </div>
            <div class="pull-left info">
              <p><%=empname%></p>
              <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
          </div>
          <!-- search form -->
          <!-- /.search form -->
          <!-- sidebar menu: : style can be found in sidebar.less -->
          <ul class="sidebar-menu">
            <li class="header">全功能菜单</li>
            <sec:authorize ifAnyGranted ="ROLE_BUSINESS_MGR">
            	<li class="treeview">
	              <a href="#">
	                <i class="fa fa-folder"></i>
	                <span>商户管理</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
	              <ul class="treeview-menu">
	                <li><a href="javascript:viod(0);" onclick="linkMainTab('system/business/businessList.jsp','businesslist','商户列表')"><i class="fa fa-circle-o"></i> 商户列表</a></li>
	              </ul>
	            </li>
            </sec:authorize >
            <sec:authorize ifAnyGranted ="ROLE_SHOP_MGR">
            	<li class="treeview">
	              <a href="#">
	                <i class="fa  fa-folder"></i> <span>门店管理</span> <i class="fa fa-angle-left pull-right"></i>
	              </a>
	              <ul class="treeview-menu">
	                <li class="active"><a href="javascript:viod(0);" onclick="linkMainTab('system/shop/shopList.jsp','shoplist','门店列表')"><i class="fa fa-circle-o"></i> 门店列表</a></li>
	              </ul>
	            </li>
            </sec:authorize >
            <sec:authorize ifAnyGranted ="ROLE_GOODS_MGR">
            	<li class="treeview">
	              <a href="#">
	                <i class="fa  fa-folder"></i> <span>食谱管理</span> <i class="fa fa-angle-left pull-right"></i>
	              </a>
	              <ul class="treeview-menu">
	                <li class="active"><a href="javascript:viod(0);" onclick="linkMainTab('system/goods/goodsList.jsp','goodsmgr','食谱列表')"><i class="fa fa-circle-o"></i> 食谱列表</a></li>
	              </ul>
	            </li>
            </sec:authorize >
            <sec:authorize ifAnyGranted ="ROLE_ORDER_MGR">
            	<li class="treeview">
	              <a href="#">
	                <i class="fa fa-folder"></i>
	                <span>订单管理</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
	              <ul class="treeview-menu">
	                <li><a href="javascript:viod(0);" onclick="linkMainTab('system/order/orderList.jsp','orderlist','订单列表')"><i class="fa fa-circle-o"></i> 订单列表</a></li>
	              </ul>
	            </li>
            </sec:authorize >
            <sec:authorize ifAnyGranted ="ROLE_ORDER_OUT_MGR">
            	<li class="treeview">
	              <a href="#">
	                <i class="fa fa-folder"></i>
	                <span>外卖订单</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
	              <ul class="treeview-menu">
	                <li><a href="javascript:viod(0);" onclick="linkMainTab('system/order_out/orderList.jsp','orderlist','外卖订单列表')"><i class="fa fa-circle-o"></i> 订单列表</a></li>
	              </ul>
	            </li>
            </sec:authorize >
            <sec:authorize ifAnyGranted ="ROLE_ORDER_RESERVE_MGR">
            	<li class="treeview">
	              <a href="#">
	                <i class="fa fa-folder"></i>
	                <span>预定订单</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
	              <ul class="treeview-menu">
	                <li><a href="javascript:viod(0);" onclick="linkMainTab('system/order_reserve/orderList.jsp','orderlist','预定外卖订单列表')"><i class="fa fa-circle-o"></i> 订单列表</a></li>
	              </ul>
	            </li>
            </sec:authorize >
            <sec:authorize ifAnyGranted ="ROLE_QUEUE_MGR">
            	<li class="treeview">
	              <a href="#">
	                <i class="fa fa-folder"></i>
	                <span>排号管理</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
	              <ul class="treeview-menu">
	                <li><a href="javascript:viod(0);" onclick="linkMainTab('system/queue/queueList.jsp','queuelist','排号列表')"><i class="fa fa-circle-o"></i> 排号列表</a></li>
	              </ul>
	            </li>
            </sec:authorize >
            <sec:authorize ifAnyGranted ="ROLE_TABLE_MGR">
            	<li class="treeview">
	              <a href="#">
	                <i class="fa fa-folder"></i>
	                <span>餐桌管理</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
	              <ul class="treeview-menu">
	                <li><a href="javascript:viod(0);" onclick="linkMainTab('system/dinnerTable/tableList.jsp','tablelist','餐桌列表')"><i class="fa fa-circle-o"></i> 餐桌列表</a></li>
	              </ul>
	            </li>
            </sec:authorize >
            <sec:authorize ifAnyGranted ="ROLE_ACTIVITY_MGR">
            	<li class="treeview">
	              <a href="#">
	                <i class="fa fa-folder"></i>
	                <span>活动管理</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
	              <ul class="treeview-menu">
	              	<li><a href="javascript:viod(0);" onclick="linkMainTab('system/activity/activityList.jsp','activitylist','活动列表')"><i class="fa fa-circle-o"></i> 活动列表</a></li>
	              </ul>
	            </li>
            </sec:authorize >
            <sec:authorize ifAnyGranted ="">
            	<li class="treeview">
	              <a href="#">
	                <i class="fa fa-folder"></i>
	                <span>评论管理</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
	              <ul class="treeview-menu">
	              	<li><a href="javascript:viod(0);" onclick="linkMainTab('system/comment/commentList.html','commentlist','评论列表')"><i class="fa fa-circle-o"></i> 评论列表</a></li>
	              </ul>
	            </li>
            </sec:authorize >
            <sec:authorize ifAnyGranted ="ROLE_ORGAN_MGR">
            	<li class="treeview">
	              <a href="#">
	                <i class="fa fa-folder"></i>
	                <span>组织架构</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
	              <ul class="treeview-menu">
	                <li><a href="javascript:viod(0);" onclick="linkMainTab('system/organization/organizationList.jsp','organizationlist','组织架构')"><i class="fa fa-circle-o"></i> 组织架构</a></li>
	              </ul>
	            </li>
            </sec:authorize >
            <sec:authorize ifAnyGranted ="ROLE_POWER_MGR">
            	<li class="treeview">
	              <a href="#">
	                <i class="fa fa-folder"></i>
	                <span>权限管理</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
	              <ul class="treeview-menu">
	                <li><a href="javascript:viod(0);" onclick="linkMainTab('system/power/roleList.jsp','rolelist','权限列表')"><i class="fa fa-circle-o"></i> 权限列表</a></li>
	                <li><a href="javascript:viod(0);" onclick="linkMainTab('system/power/resourceList.jsp','resourcelist','资源列表')"><i class="fa fa-circle-o"></i> 资源列表</a></li>
	                <li><a href="javascript:viod(0);" onclick="linkMainTab('system/power/businessRoleList.jsp','buinessrolelist','商户权限')"><i class="fa fa-circle-o"></i> 商户权限管理</a></li>
	              </ul>
	            </li>
            </sec:authorize >
            <sec:authorize ifAnyGranted ="ROLE_EMP_MGR">
            	<li class="treeview">
	              <a href="#">
	                <i class="fa fa-folder"></i>
	                <span>员工管理</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
	              <ul class="treeview-menu">
	              	<li><a href="javascript:viod(0);" onclick="linkMainTab('system/employee/employeeList.jsp','emplist','员工列表')"><i class="fa fa-circle-o"></i> 员工列表</a></li>
	              </ul>
	            </li>
            </sec:authorize >
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Custom Tabs -->
        <div class="nav-tabs-custom">
          <ul class="nav nav-tabs" id="mainTabs">
            <li class="active"><a href="#home_tab" data-toggle="tab" id="home">首页	</a></li>
          </ul>
          <div class="tab-content" id="tabcontent">
            <div class="tab-pane active" id="home_tab">
              <iframe src="" style="margin: 0; padding: 0; border: 0px;" width="100%" height="900px" ></iframe>
            </div><!-- /.tab-pane -->
          </div><!-- /.tab-content -->
        </div><!-- nav-tabs-custom -->
      </div><!-- /.content-wrapper -->
     <!-- <footer class="main-footer">
         <div class="pull-right hidden-xs">
          <b>Version</b> 2.2.0
        </div>
        <strong>Copyright &copy; 2014-2016 <a href="#">Self Order</a>.</strong> All rights reserved. 
      </footer>-->
    </div><!-- ./wrapper -->
	<!--modal 修改密码-->
    <div class="modal fade" id="updatePasswordWin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="myModalLabel" >修改密码</h4>
	      </div>
	      <div class="modal-body">
			<form role="form">
			   <div class="form-group has-warning" >
			      <label for="wcode">旧密码:</label>
			      <input type="password" class="form-control" id="password" name="employee.password" placeholder="旧密码" value="" >
			   </div>
			   <div class="form-group has-warning" >
			      <label for="wcode">新密码：</label>
			      <input type="password" class="form-control" id="newpassword" name="employee.newpassword" placeholder="新密码" value="" >
			   </div>
			   <div class="form-group has-warning">
			      <label for="content">密码确认：</label>
			      <input type="password" class="form-control" id="newpasswordagain" name="employee.newpasswordagain" placeholder="密码确认" value="" >
			   </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" onclick="saveBtn(this)">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /<!--modal 修改密码-->
    <!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- jQuery UI 1.11.4 -->
    <script src="js/jquery-ui.min.js" type="text/javascript"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script src="plugins/layer/layer.js"></script>
    <script type="text/javascript" src="messageTip/js/jquery.notify.js"></script>
    <script type="text/javascript" src="index.js"></script>
    <script type="text/javascript" language="javascript"> 
			$("#empname").html("您好，"+empname);
			$("#empcode").html("编码："+empcode);
			$("#empnameBig").html(empname);
			if(emp_type == "A"){
				linkMainTab('home_admin.jsp','home','首页');
			}else if(emp_type == "B"){
				linkMainTab('home_business.jsp','home','首页');
			}else if(emp_type == "S"){
				linkMainTab('home_shop.jsp','home','首页');
			}
			initWebSocket();
			showCurrentDate();
	</script>
  </body>
</html>


