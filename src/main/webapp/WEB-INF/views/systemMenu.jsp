<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统菜单管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/static/js/views/systemMenu.js"></script> 
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div data-options="region:'west',width:230,border:false,border:false">
			<div class="easyui-layout" fit="true">
				<div data-options="region:'center',title:'菜单树',tools:'#systemMenu_menuTree_tt'">
					 <ul id="systemMenuTree"></ul>
				</div>
				<div data-options="region:'south',title:'父菜单',height:200,tools:'#systemMenu_parent_tt'">
					<form id="parentMenuFrom" method="post">
						<input type="hidden" name="id">
						<table style="margin-top: 10px;">
						 	<tbody>
						 		<tr>
									<td>父菜单</td>
									<td><input id="parentTree" name="parent.id"></td>
								</tr>
						 		<tr>
						 			<td>名称:</td>
						 			<td><input type="text" name="text"></td>
						 		</tr>
						 		<tr>
						 			<td>图标:</td>
						 			<td><input type="text" name="iconCls"></td>
						 		</tr>
						 		<tr>
						 			<td>url:</td>
						 			<td><input type="text" name="url"></td>
						 		</tr>
						 	</tbody>
						 </table>
					 </form>
				</div>
			</div>
		</div>
		<div data-options="region:'center',title:'子菜单'">
			<!-- 数据表格 -->
			<table id="systemMenu_datagrid">
				<thead>
					<tr>
						<th data-options="field:'text',width:1,align:'center'">名称</th>
						<th data-options="field:'iconCls',width:1,align:'center',formatter:iconFormatter">图标</th>
						<th data-options="field:'url',width:1,align:'center'">url</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<!-- 新增编辑对话框 -->
	<div id="systemMenu_dialog">
		<form id="systemMenu_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<input type="hidden" name="parent.id">
			<tr>
				<td>父菜单</td>
				<td><input id="systemMenu_form_parentMenu"  type="text" disabled="disabled"></td>
			</tr>
			<tr>
				<td>名称</td>
				<td><input type="text" name="text"></td>
			</tr>
			<tr>
				<td>图标</td>
				<td><input type="text" name="iconCls"></td>
			</tr>
			<tr>
				<td>url</td>
				<td><input type="text" name="url"></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="systemMenu_datagrid_tb">
		<div>
			<a class="easyui-linkbutton"  iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton"  iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
			<a class="easyui-linkbutton"  iconCls="icon-remove" plain="true" data-cmd="del">删除</a>
			<a class="easyui-linkbutton"  iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="systemMenu_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
	<!-- 父菜单工具栏按钮 -->
	<div id="systemMenu_parent_tt">
		<a class="icon-save" data-cmd="parentsave"></a>
		<a class="icon-no" data-cmd="removeParent"></a>
	</div>
	<!-- 菜单树工具栏按按钮 -->
	<div id="systemMenu_menuTree_tt">
		<a class="icon-remove" data-cmd="menutreedelete"></a>
		<a class="icon-reload" data-cmd="menutreereload"></a>
	</div>
</body>
</html>