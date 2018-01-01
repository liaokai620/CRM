<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教室管理</title>
<%@include file="common.jsp" %> 
<script type="text/javascript" src="/static/js/views/classroom.js"></script>
</head>
<body>
<table id="classroom_datagrid"></table>
<!-- 定义对话框 -->
<div id="classroom_dialog">
	<form id="classroom_form" method="post">
		<input type="hidden" name="id">
		<div align="center" style="margin-top: 10px;" >
			<div>
				<input type="text" name="name" class="easyui-textbox" data-options="label:'教室名称:',labelPosition:'top', width:150">
			</div>
			<div>
				<input type="text" name="stite" class="easyui-textbox" data-options="label:'教室地址:',labelPosition:'top', width:150">
			</div>
			<div>
				<input type="text" name="seatNumber" class="easyui-textbox" data-options="label:'座位数量:',labelPosition:'top', width:150">
			</div>

		</div>
	</form>
</div>
<!-- 定义顶部按钮 -->
<div id="classroom_datagrid_tb">

	<div>
		<shiro:hasPermission name="classroom:save">
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
		</shiro:hasPermission>

		<shiro:hasPermission name="classroom:update">
			<a id="classroom_editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true"  data-cmd="edit">编辑</a>
		</shiro:hasPermission>

		<shiro:hasPermission name="classroom:quit">
			<a id="classroom_quitBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="quit">删除</a>
		</shiro:hasPermission>

		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>

		<shiro:hasPermission name="grade:useRoom">
			<a id="grade_quitBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="useRoom">启用教室</a>
		</shiro:hasPermission>
		<div style="float:right;">
			<input id="searchBtn" type="text" >
		</div>
	</div>

</div>
<!-- 对话框底部按钮 -->
<div id="classroom_dialog_bt">
	<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
	<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>