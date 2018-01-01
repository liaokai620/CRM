<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级管理</title>
<%@include file="common.jsp" %> 
<script type="text/javascript" src="/static/js/views/grade.js"></script>
</head>
<body>
<table id="grade_datagrid"></table>
<!-- 定义对话框 -->
<div id="grade_dialog">
	<form id="grade_form" method="post">
		<input type="hidden" name="id">
		<div align="center" style="margin-top: 10px;" >
			<div>
				<input type="text" name="name" class="easyui-textbox" data-options="label:'班级名称:',labelPosition:'top', width:150">
			</div>
			<div>
				<input type="text" name="number" class="easyui-textbox" data-options="label:'班级人数:',labelPosition:'top', width:150">
			</div>
			<div>
				<input type="text" name="beginTime" class= "easyui-datebox"  data-options="label:'开班时间:',labelPosition:'top', width:150">
			</div>
			<div>
				<select name="course.id" class="easyui-combobox"
						data-options="
						 width:150,
						 label:'课程:',
						 labelPosition:'top',
						 valueField:'id',
						 textField:'name',
						 url:'/course/queryListFromGrade',
						 panelHeight:'auto'
						">
				</select>
			</div>
		</div>
	</form>
</div>
<%--分配班主任的对话框--%>
<div id="grade_teacherDialog">
	<form id="grade_teacherForm" method="post">
		<input type="hidden" name="id">
		<div align="center" style="margin-top: 10px;" >
			<div>
				<select name="classTeacher.id" class="easyui-combobox"
						data-options="
						 width:150,
						 label:'班主任:',
						 labelPosition:'top',
						 valueField:'id',
						 textField:'username',
						 url:'/employee/queryListTeacher',
						 panelHeight:'auto'
						">
				</select>
			</div>
		</div>
	</form>
</div>

<!-- 定义顶部按钮 -->
<div id="grade_datagrid_tb">

	<div>
		<shiro:hasPermission name="grade:save">
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
		</shiro:hasPermission>

		<shiro:hasPermission name="grade:update">
			<a id="grade_editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true"  data-cmd="edit">编辑</a>
		</shiro:hasPermission>

		<shiro:hasPermission name="grade:quit">
			<a id="grade_quitBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="quit">删除</a>
		</shiro:hasPermission>

		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>&nbsp;&nbsp;&nbsp;
		<input id="searchBtn" type="text">&nbsp;&nbsp;&nbsp;

		<shiro:hasPermission name="grade:allotTeacher">
			<a id="grade_quitBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="allotTeacher">分配班主任</a>
		</shiro:hasPermission>

		<shiro:hasPermission name="grade:graduate">
			<a id="grade_quitBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="graduate">办理结业</a>
		</shiro:hasPermission>
	</div>

</div>
<!-- 对话框底部按钮 -->
<div id="grade_dialog_bt">
	<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
	<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<!-- 分配班主任的按钮 -->
<div id="grade_dialog_allot">
	<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="allot">保存</a>
	<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>