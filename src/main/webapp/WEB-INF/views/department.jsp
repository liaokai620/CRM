<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>部门管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/department.js"></script>
</head>
<body>
<table id="department_datagrid"></table>
<!-- 定义对话框 -->
<div id="department_dialog">
    <form id="department_form" method="post">
        <input type="hidden" name="id">
        <div align="center" style="margin-top: 10px;">
            <div>
                <input type="text" name="name" class="easyui-textbox"
                       data-options="label:'部门名称:',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="sn" class="easyui-textbox"
                       data-options="label:'部门编号:',labelPosition:'top', width:150">
            </div>
        </div>
    </form>
</div>
<!-- 定义顶部按钮 -->
<div id="department_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a id="department_editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a id="department_quitBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="delete">删除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    </div>
</div>
<!-- 对话框底部按钮 -->
<div id="department_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>