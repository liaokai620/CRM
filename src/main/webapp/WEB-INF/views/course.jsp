<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程管理</title>
<%@include file="common.jsp" %> 
<script type="text/javascript" src="/static/js/views/course.js"></script>
</head>
<body>
<div  class="easyui-layout" fit="true">

	<div data-options="region:'west',title:'查询日期'" style="width:300px;">
		<%--日历查询--%>
		<div id="date" class="easyui-calendar" style="width:180px;height:180px;"></div>
		<%--班级查询列表--%>
			<table id="grade_datagrid"></table>
	</div>

	<div data-options="region:'center',border:false">
		<%--数据表格展示区--%>
		<table id="course_datagrid"></table>
		<!-- 定义对话框 -->
		<div id="course_dialog">
			<form id="course_form" method="post">
				<input type="hidden" name="id">
				<div align="center" style="margin-top: 10px;" >
					<div>
						<input type="text" name="name" class="easyui-textbox" data-options="label:'课程名称:',labelPosition:'top', width:150">
					</div>
					<div>
						<input type="text" name="schoolTime" class= "easyui-datebox"  data-options="label:'上课时间:',labelPosition:'top', width:150">
					</div>
					<div>
						<select name="classroom.id" class="easyui-combobox"
								data-options="
								 width:150,
								 label:'教室:',
								 labelPosition:'top',
								 valueField:'id',
								 textField:'name',
								 url:'/classroom/queryForListByCourse',
								 panelHeight:'auto'
								">
						</select>
					</div>
					<div>
						<select name="teacher.id" class="easyui-combobox"
								data-options="
								 width:150,
								 label:'上课老师:',
								 labelPosition:'top',
								 valueField:'id',
								 textField:'username',
								 url:'/employee/queryListTeacher',
								 panelHeight:'auto'
								">
						</select>
					</div>
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
					<div>
						<select name="grade.id" class="easyui-combobox"
								data-options="
								 width:150,
								 label:'上课班级:',
								 labelPosition:'top',
								 valueField:'id',
								 textField:'name',
								 url:'/grade/queryForListByCourse',
								 panelHeight:'auto'
								">
						</select>
					</div>
					<div>
						<input type="text" name="remark" class="easyui-textbox" data-options="label:'备注:',labelPosition:'top', width:150,height:100">
					</div>
				</div>
			</form>
		</div>
		<!-- 定义顶部按钮 -->
		<div id="course_datagrid_tb">

			<div>
				<shiro:hasPermission name="course:save">
					<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
				</shiro:hasPermission>

				<shiro:hasPermission name="course:update">
					<a id="course_editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true"  data-cmd="edit">编辑</a>
				</shiro:hasPermission>

				<shiro:hasPermission name="course:quit">
					<a id="course_quitBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="quit">删除</a>
				</shiro:hasPermission>

				<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>&nbsp;&nbsp;&nbsp;
				 班级:<select name="gradeId" class="easyui-combobox"
						data-options="
								 width:120,
								 prompt:'选择要查询的班级',
								 limitToList:true,
								 labelPosition:'top',
								 valueField:'id',
								 textField:'name',
								 url:'/grade/queryForListByCourse',
								 panelHeight:'auto'
								">
				</select>
				 老师:<select name="teacherId" class="easyui-combobox"
						data-options="
								 width:75,
								 prompt:'选择老师',
								 limitToList:true,
								 labelPosition:'top',
								 valueField:'id',
								 textField:'username',
								 url:'/employee/queryListTeacher',
								 panelHeight:'auto'
								">
				</select>
				 教室:<select name="classroomId" class="easyui-combobox"
						data-options="
								 width:75,
								 prompt:'选择教室',
								 limitToList:true,
								 labelPosition:'top',
								 valueField:'id',
								 textField:'name',
								 url:'/classroom/queryForListByCourse',
								 panelHeight:'auto'
								">
				</select>
				<a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="query">快查</a>
				<input  name="beginTime"  type= "text" class= "easyui-datebox" />~
				<input  name="endTime"  type= "text" class= "easyui-datebox" />
				<a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="queryDate">时间查询</a>
				<div style="float:right;">
					<form action="/course/upload" method="POST" enctype="multipart/form-data">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a style="right: 600px" href="http://localhost/course/download?filename=course.xls">下载课程模板</a>&nbsp;&nbsp;
						<input type="file" name="course">
							<input type="submit" data-cmd="sunbmitUpload" value="提交">
						<br>
					</form>
				</div>
			</div>
		</div>
		<!-- 对话框底部按钮 -->
		<div id="course_dialog_bt">
			<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
		</div>
	</div>
</div>
</body>
</html>