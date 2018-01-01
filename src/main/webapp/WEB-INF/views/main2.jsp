<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>后台首页</title>
	<%@include file="common.jsp" %>
	<script type="text/javascript" src="/static/js/views/main.js"></script>
</head>
<body>
	<div  class="easyui-layout" fit="true">
	    <div data-options="region:'north'" style="height:80px;background:#D6ECFA; ">
	    	<div style="float: left;">
	    		<h1>小码哥客户关系管理系统</h1>
	    	</div>
	    	<div style="float: right;margin-top: 50px;margin-right: 15px;">
	    		<%--当前用户:<shiro:principal property="realname"></shiro:principal>--%>
				<a id="clock"  style="text-decoration: none">上班打卡</a>
	    		<a href="/attendance" style="text-decoration: none">查看打卡记录</a>
	    		<a href="/logout" style="text-decoration: none">退出</a>
	    	</div>
	    </div>
	    <div data-options="region:'west',title:'菜单'" style="width:180px;">
	    	<!-- 菜单树 -->
	    	<ul id="main_menu"></ul>
	    </div>
	    <div data-options="region:'center',border:false">
	    	<div id="main_tabs">
	    		<div title="欢迎页" closable="true">
					<h1>欢迎登陆系统</h1>
				</div>
	    	</div>
	    </div>
	    <div data-options="region:'south'" style="height:30px;background:#D6ECFA; ">
	    	<center>版权信息</center>
	    </div>
	</div>
</body>
</html>
