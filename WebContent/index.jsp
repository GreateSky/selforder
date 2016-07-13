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
    <!-- Morris chart -->
    <link href="plugins/morris/morris.css" rel="stylesheet" type="text/css" />
    <!-- jvectormap -->
    <link href="plugins/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
    <!-- Date Picker -->
    <link href="plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
    <!-- Daterange picker -->
    <link href="plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
    <!-- bootstrap wysihtml5 - text editor -->
    <link href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
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
              <li class="dropdown messages-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-comments"></i>
                  <span class="label label-success">3</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">3条评论信息未处理</li>
                  <li>
                    <!-- inner menu: contains the actual data -->
                    <ul class="menu">
                      <li><!-- start message -->
                        <a href="#">
                          <div class="pull-left">
                            <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" />
                          </div>
                          <h4>
                            Support Team
                            <small><i class="fa fa-clock-o"></i> 5 分钟前</small>
                          </h4>
                          <p>服务还行</p>
                        </a>
                      </li><!-- end message -->
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="dist/img/user3-128x128.jpg" class="img-circle" alt="User Image" />
                          </div>
                          <h4>
                            AdminLTE Design Team
                            <small><i class="fa fa-clock-o"></i> 2 小时前</small>
                          </h4>
                          <p>这菜也太辣了吧？</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="dist/img/user4-128x128.jpg" class="img-circle" alt="User Image" />
                          </div>
                          <h4>
                            Developers
                            <small><i class="fa fa-clock-o"></i> 今天</small>
                          </h4>
                          <p>下次还是这</p>
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="footer"><a href="#">查看所有评论</a></li>
                </ul>
              </li>
              <!-- Notifications: style can be found in dropdown.less -->
              <li class="dropdown notifications-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-bell-o"></i>
                  <span class="label label-warning">2</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">2条订单信息</li>
                  <li>
                    <!-- inner menu: contains the actual data -->
                    <ul class="menu">
                      <li>
                        <a href="#">
                          <i class="fa  fa-pencil-square-o text-aqua"></i> 订单ORD-20160429-3321已创建等待确认
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-pencil-square-o text-yellow"></i> 订单ORD-20160429-3321已完成
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="footer"><a href="#">查看所有订单</a></li>
                </ul>
              </li>
              <!-- Tasks: style can be found in dropdown.less -->
              <li class="dropdown tasks-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa  fa-truck"></i>
                  <span class="label label-danger">2</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">2条外卖订单信息</li>
                  <li>
                    <!-- inner menu: contains the actual data -->
                    <ul class="menu">
                      <li>
                        <a href="#">
                          <i class="fa  fa-pencil-square-o text-aqua"></i> 订单OUT-20160429-3321已创建等待确认
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-pencil-square-o text-yellow"></i> 订单OUT-20160429-3321已支付完成等待配送
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="footer"><a href="#">查看所有订单</a></li>
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
                    <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" />
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
                      <a href="/selforder/j_spring_security_logout" class="btn btn-default btn-flat">退出系统</a>
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
              <p>SelfOrder</p>
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
	                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/business/businessList.jsp')"><i class="fa fa-circle-o"></i> 商户列表</a></li>
	                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/business/saveBusiness.jsp')"><i class="fa fa-circle-o"></i> 商户维护</a></li>
	              </ul>
	            </li>
            </sec:authorize >
            <li class="treeview">
              <a href="#">
                <i class="fa  fa-folder"></i> <span>门店管理</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li class="active"><a href="javascript:viod(0);" onclick="linkMainFrame('system/shop/shopList.html')"><i class="fa fa-circle-o"></i> 门店列表</a></li>
                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/shop/saveshop.html')"><i class="fa fa-circle-o" ></i> 门店维护</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-folder"></i>
                <span>订单管理</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/order/orderList.html')"><i class="fa fa-circle-o"></i> 订单列表</a></li>
                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/order/orderDetail.html')"><i class="fa fa-circle-o"></i> 订单详情</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-folder"></i>
                <span>外卖管理</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/outsell/outsellList.html')"><i class="fa fa-circle-o"></i> 订单列表</a></li>
                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/outsell/outsellDetail.html')"><i class="fa fa-circle-o"></i> 订单详情</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-folder"></i>
                <span>排号管理</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/array/arrayList.html')"><i class="fa fa-circle-o"></i> 排号列表</a></li>
                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/array/saveArray.html')"><i class="fa fa-circle-o"></i> 排号维护</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-folder"></i>
                <span>餐桌管理</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/dinnerTable/tableView.html')"><i class="fa fa-circle-o"></i> 餐桌运营</a></li>
                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/dinnerTable/tableList.html')"><i class="fa fa-circle-o"></i> 餐桌列表</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-folder"></i>
                <span>菜谱管理</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/cookbook/saveCookBook.html')"><i class="fa fa-circle-o"></i> 菜谱维护</a></li>
                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/cookbook/cookBookList.html')"><i class="fa fa-circle-o"></i> 菜谱列表</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-folder"></i>
                <span>评论管理</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/comment/commentList.html')"><i class="fa fa-circle-o"></i> 评论列表</a></li>
                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/comment/auditComment.html')"><i class="fa fa-circle-o"></i> 审核/回复</a></li>
              </ul>
            </li>
            <sec:authorize ifAnyGranted ="ROLE_ORG_MGR">
            	<li class="treeview">
	              <a href="#">
	                <i class="fa fa-folder"></i>
	                <span>组织架构</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              </a>
	              <ul class="treeview-menu">
	                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/organization/organizationList.jsp')"><i class="fa fa-circle-o"></i> 组织架构维护</a></li>
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
	                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/power/roleList.jsp')"><i class="fa fa-circle-o"></i> 权限列表</a></li>
	                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/power/resourceList.jsp')"><i class="fa fa-circle-o"></i> 资源列表</a></li>
	                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/power/businessRoleList.jsp')"><i class="fa fa-circle-o"></i> 商户权限管理</a></li>
	              </ul>
	            </li>
            </sec:authorize >
            
            <li class="treeview">
              <a href="#">
                <i class="fa fa-folder"></i>
                <span>员工管理</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="javascript:viod(0);" onclick="linkMainFrame('system/employee/saveEmployee.html')"><i class="fa fa-circle-o"></i> 个人信息维护</a></li>
              </ul>
            </li>
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <iframe id="mainframe" name="mainss" src="home.html" style="margin: 0; padding: 0; border: 0px;" width="100%" height="1000px" scrolling="no" onload = "height = document.frames(this.name).document.body.scrollHeight + 30"
        ></iframe>
      </div><!-- /.content-wrapper -->
      <footer class="main-footer">
        <div class="pull-right hidden-xs">
          <b>Version</b> 2.2.0
        </div>
        <strong>Copyright &copy; 2014-2016 <a href="#">Self Order</a>.</strong> All rights reserved.
      </footer>
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
    <script src="plugins/jQuery/jquery1.11.4-ui.min.js" type="text/javascript"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <!-- Bootstrap 3.3.2 JS -->
    <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- Morris.js charts -->
    <script src="plugins/raphael/raphael-min.js"></script>
    <script src="plugins/morris/morris.min.js" type="text/javascript"></script>
    <!-- Sparkline -->
    <script src="plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
    <!-- jvectormap -->
    <script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
    <script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script>
    <!-- jQuery Knob Chart -->
    <script src="plugins/knob/jquery.knob.js" type="text/javascript"></script>
    <!-- daterangepicker -->
    <script src="plugins/moment/moment2.10.2.min.js" type="text/javascript"></script>
    <script src="plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
    <!-- datepicker -->
    <script src="plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>
    <!-- Bootstrap WYSIHTML5 -->
    <script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>
    <!-- Slimscroll -->
    <script src="plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <!-- FastClick -->
    <script src="plugins/fastclick/fastclick.min.js" type="text/javascript"></script>
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js" type="text/javascript"></script>
    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
    <script src="dist/js/pages/dashboard.js" type="text/javascript"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="dist/js/demo.js" type="text/javascript"></script>
    <script type="text/javascript" src="index.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script src="plugins/layer/layer.js"></script>
    <script type="text/javascript" language="javascript"> 
		    var empname = "<%=empname%>";
			var empcode = "<%=empcode%>";
			$("#empname").html("您好，"+empname);
			$("#empcode").html("编码："+empcode);
			$("#empnameBig").html(empname);
			
			var iframeids = ["mainframe"];
			var iframehide = "yes";
			function dyniframesize() {
				var dyniframe = new Array()
				for (i = 0; i < iframeids.length; i++) {
					if (document.getElementById) {
						dyniframe[dyniframe.length] = document.getElementById(iframeids[i]);
						if (dyniframe[i] && !window.opera) {
							dyniframe[i].style.display = "block"
							if (dyniframe[i].contentDocument && dyniframe[i].contentDocument.body.offsetHeight)
								dyniframe[i].height = dyniframe[i].contentDocument.body.offsetHeight;
							else if (dyniframe[i].Document && dyniframe[i].Document.body.scrollHeight)
								dyniframe[i].height = dyniframe[i].Document.body.scrollHeight;
						}
					}
					if ((document.all || document.getElementById) && iframehide == "no") {
						var tempobj = document.all ? document.all[iframeids[i]] : document.getElementById(iframeids[i])
						tempobj.style.display = "block"
					}
				}
			}
			if (window.addEventListener)
				window.addEventListener("load", dyniframesize, false)
			else if (window.attachEvent)
				window.attachEvent("onload", dyniframesize)
			else
				window.onload = dyniframesize
			
			/**
			 * 点击菜单栏连接至主页面
			 * @param {Object} url  需要跳转的页面
			 */
			function linkMainFrame(url){
				$("#mainframe").attr('src',url);
				dyniframesize();
			}
		</script>
  </body>
</html>


