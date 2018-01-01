<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>员工管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/employee.js"></script>
</head>
<body>
<table id="employee_datagrid"></table>
<!-- 定义对话框 -->
<div id="employee_dialog">
    <form id="employee_form" method="post">
        <input type="hidden" name="id">
        <div align="center" style="margin-top: 10px;">
            <div>
                <input type="text" name="username" class="easyui-textbox"
                       data-options="label:'用户名:',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="realname" class="easyui-textbox"
                       data-options="label:'真实姓名:',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="tel" class="easyui-textbox"
                       data-options="label:'联系方式:',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="email" class="easyui-textbox"
                       data-options="label:'邮箱:',labelPosition:'top', width:150">
            </div>
            <div>
                <select name="dept.id" class="easyui-combobox"
                        data-options="
						 width:150,
						 label:'所属部门:',
						 labelPosition:'top',
						 valueField:'id',
						 textField:'name',
						 url:'/department/selectListForEmployeeForm'
						">
                </select>
            </div>
            <div>
                <select id="roleId" class="easyui-combobox"
                        data-options="
						 width:150,
						 label:'角色:',
						 labelPosition:'top',
						 multiple:true,
						 valueField:'id',
						 textField:'name',
						 url:'/role/selectListForEmployeeForm'
						">
                </select>
            </div>
        </div>
    </form>
</div>
<!-- 定义顶部按钮 -->
<div id="employee_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a id="employee_editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a id="employee_quitBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="quit">离职</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        <input id="searchBtn" type="text">
    </div>
</div>
<!-- 对话框底部按钮 -->
<div id="employee_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>