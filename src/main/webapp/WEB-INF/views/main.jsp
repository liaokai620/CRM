<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>校管家管理系统</title>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="/static/index/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/static/index/css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" href="/static/index/css/matrix-style.css" />
	<link rel="stylesheet" href="/static/index/css/matrix-media.css" />
	<link href="/static/index/font-awesome/css/font-awesome.css" rel="stylesheet" />
</head>
<body>
<div id="header">
	<h1><a href="dashboard.html">校管家信息管理系统平台</a></h1>
</div>

<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
	<ul class="nav">
		<li  class="dropdown" id="profile-messages" >
			<a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle">
				<i class="icon icon-user"></i>&nbsp;
				<span class="text">
					欢迎您:admin
				</span>&nbsp;
				<b class="caret"></b>
			</a>
			<ul class="dropdown-menu">
				<li><a href="#"><i class="icon-user"></i> 个人资料</a></li>
				<li class="divider"></li>
				<li><a href="/dayJob"><i class="icon-check"></i> 我的任务</a></li>
				<li class="divider"></li>
				<li><a href="login.html"><i class="icon-key"></i> 退出系统</a></li>
			</ul>
		</li>
		<li class="dropdown" id="menu-messages">
			<a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle">
				<i class="icon icon-envelope"></i>&nbsp;
				<span class="text">我的消息</span>&nbsp;
				<span class="label label-important">4</span>&nbsp;
				<b class="caret"></b>
			</a>
			<ul   class="dropdown-menu">
				<li><a class="sAdd" title="" href="#"><i class="icon-plus"></i> 新消息</a></li>
				<li class="divider"></li>
				<li><a class="sInbox" title="" href="#"><i class="icon-envelope"></i> 收件箱</a></li>
				<li class="divider"></li>
				<li><a class="sOutbox" title="" href="#"><i class="icon-arrow-up"></i> 发件箱</a></li>
				<li class="divider"></li>
				<li><a class="sTrash" title="" href="#"><i class="icon-trash"></i> 回收站</a></li>
			</ul>
		</li>
		<li class=""><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">&nbsp;设置</span></a></li>
		<li class=""><a title="" href="/logout"><i class="icon icon-share-alt"></i> <span class="text">&nbsp;退出系统</span></a></li>
	</ul>
</div>
<!--close-top-Header-menu-->

<!--搜索框-->
<div id="search">
	<input type="text"  placeholder="搜索..."/>
	<button type="submit"  class="tip-bottom" title="Search"><i class="icon-search icon-white"></i></button>
</div>


<!--菜单-->
<div id="sidebar" style="OVERFLOW-Y: auto; OVERFLOW-X:hidden;">
	<ul>
		<li class="submenu active">
			<a class="menu_a" link="/main"><i class="icon icon-home"></i> <span>欢迎登陆</span></a>
		</li>
		<li class="submenu">
			<a href="#">
				<i class="icon icon-table"></i>
				<span>营销管理(售前)</span>
				<span class="label label-important">7</span>
			</a>
			<ul>
				<li><a class="menu_a" link="/latenStudent"><i class="icon icon-caret-right"></i>潜在学员管理</a></li>
				<li><a class="menu_a" link="/studentFollow"><i class="icon icon-caret-right"></i>学员跟踪</a></li>
				<li><a class="menu_a" link="/history"><i class="icon icon-caret-right"></i>移交历史</a></li>
				<li><a class="menu_a" link="/school"><i class="icon icon-caret-right"></i>大客户(学校)</a></li>
				<li><a class="menu_a" link="/contact"><i class="icon icon-caret-right"></i>学校联系人</a></li>
				<li><a class="menu_a"  link="/customerPool" ><i class="icon icon-caret-right"></i>潜在客户池</a></li>
				<li><a class="menu_a" link="/exam"><i class="icon icon-caret-right"></i>考试管理</a></li>
			</ul>
		</li>
		<li class="submenu">
			<a href="#">
				<i class="icon icon-table"></i>
				<span>学员管理(售后)</span>
				<span class="label label-important">6</span>
			</a>
			<ul>
				<li><a class="menu_a" link="/formalStudent"><i class="icon icon-caret-right"></i>正式学员管理</a></li>
				<li><a class="menu_a" link="/promotion"><i class="icon icon-caret-right"></i>学员升班留级</a></li>
				<li><a class="menu_a" link="/disappear"><i class="icon icon-caret-right"></i>学员流失</a></li>
				<li><a class="menu_a" link="/income"><i class="icon icon-caret-right"></i>收款管理</a></li>
				<li><a class="menu_a" link="/expense"><i class="icon icon-caret-right"></i>支出管理</a></li>
			</ul>
		</li>
		<li class="submenu">
			<a href="#">
				<i class="icon icon-table"></i>
				<span>教务管理</span>
				<span class="label label-important">3</span>
			</a>
			<ul>
				<li><a class="menu_a" link="/grade"><i class="icon icon-caret-right"></i>班级管理</a></li>
				<li><a class="menu_a" link="/course"><i class="icon icon-caret-right"></i>课程表</a></li>
				<li><a class="menu_a" link="/classroom"><i class="icon icon-caret-right"></i>教室管理</a></li>
			</ul>
		</li>
		<li class="submenu">
			<a href="#">
				<i class="icon icon-table"></i>
				<span>日常办公</span>
				<span class="label label-important">3</span>
			</a>
			<ul>
				<li><a class="menu_a" link="/salary"><i class="icon icon-caret-right"></i>工资管理</a></li>
                <li><a class="menu_a" link="/queryholiday"><i class="icon icon-caret-right"></i>请假条</a></li>
				<li><a class="menu_a" link="/attendance"><i class="icon icon-caret-right"></i>考勤管理</a></li>

            </ul>
		</li>
		<li class="submenu">
			<a href="">
				<i class="icon icon-table"></i>
				<span>报表管理</span>
				<span class="label label-important">2</span>
			</a>
			<ul>
				<li><a class="menu_a" link="/expenseReport"><i class="icon icon-caret-right"></i>支出报表</a></li>
				<li><a class="menu_a" link="/performance"><i class="icon icon-caret-right"></i>销售报表</a></li>
			</ul>
		</li>
		<li class="submenu">
			<a href="#">
				<i class="icon icon-table"></i>
				<span>系统管理</span>
				<span class="label label-important">7</span>
			</a>
			<ul>
				<li><a class="menu_a" link="/employee"><i class="icon icon-caret-right"></i>员工管理</a></li>
				<li><a class="menu_a" link="/department"><i class="icon icon-caret-right"></i>部门管理</a></li>
				<li><a class="menu_a" link="/permission"><i class="icon icon-caret-right"></i>系统权限管理</a></li>
				<li><a class="menu_a" link="/role"><i class="icon icon-caret-right"></i>系统角色管理</a></li>
				<li><a class="menu_a" link="/dictionary"><i class="icon icon-caret-right"></i>数据字典</a></li>
				<li><a class="menu_a" link="/"><i class="icon icon-caret-right"></i>系统日志</a></li>
				<li><a class="menu_a" link="/systemMenu"><i class="icon icon-caret-right"></i>菜单管理</a></li>
			</ul>
		</li>
		<li class="submenu">
			<a href="#">
				<i class="icon icon-table"></i>
				<span>密码管理</span>
				<span class="label label-important">1</span>
			</a>
			<ul>
				<li><a class="menu_a" link="/password"><i class="icon icon-caret-right"></i>修改密码</a></li>
			</ul>
		</li>
	</ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
	<!--点击home图标跳转-->
	<div id="content-header">
		<div id="breadcrumb"> <a href="/main" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>主页</a></div>
	</div>
	<!--End-breadcrumbs-->
	<iframe src="/index" id="iframe-main" frameborder='0' style="width:100%;"></iframe>
</div>
<!--end-main-container-part-->

<script src="/static/index/js/excanvas.min.js"></script>
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
<script src="/static/index/js/jquery.ui.custom.js"></script>
<script src="/static/index/js/bootstrap.min.js"></script>
<script src="/static/index/js/nicescroll/jquery.nicescroll.min.js"></script>
<script src="/static/index/js/matrix.js"></script>

<script type="text/javascript">

    //初始化相关元素高度
    function init(){
        $("body").height($(window).height()-80);
        $("#iframe-main").height($(window).height()-90);
        $("#sidebar").height($(window).height()-50);
    }

    $(function(){
        init();
        $(window).resize(function(){
            init();
        });
    });

    // This function is called from the pop-up menus to transfer to
    // a different page. Ignore if the value returned is a null string:
    function goPage (newURL) {
        // if url is empty, skip the menu dividers and reset the menu selection to default
        if (newURL != "") {
            // if url is "-", it is this page -- reset the menu:
            if (newURL == "-" ) {
                resetMenu();
            }
            // else, send page to designated URL
            else {
                document.location.href = newURL;
            }
        }
    }

    // resets the menu selection upon entry to this page:
    function resetMenu() {
        document.gomenu.selector.selectedIndex = 2;
    }
</script>
</body>
</html>