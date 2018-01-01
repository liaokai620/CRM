<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>支出管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/expense.js"></script>
</head>
<body>
<table id="expense_datagrid"></table>
<!-- 定义对话框 -->
<div id="expense_dialog">
    <form method="post" id="expense_form">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 20px">
            <tr>
                <td>经手人:</td>
                <td><input class="easyui-combobox" name="person.id" data-options="
                           url:'/latenStudent/queryStudentForIncome',valueField:'id',textField:'name'"/>
                </td>
            </tr>
            <tr>
                <td>支出时间:</td>
                <td><input class="easyui-datebox" name="expendtime"/></td>
                <td>支出金额:</td>
                <td><input class="easyui-textbox" name="expendamount"/></td>
            </tr>
            <tr>
                <td>支出方式:</td>
                <td><input class="easyui-combobox" name="expendtype" data-options="
                           url:'/static/js/data/incometype.json',valueField:'text',textField:'text'"/>
                </td>
                <td>支出类型:</td>
                <td><input class="easyui-combobox" name="expendmode" data-options="
                           url:'/static/js/data/expendmode.json',valueField:'text',textField:'text'"/>
                </td>
            </tr>
            <tr>
                <td>详细分类:</td>
                <td><input class="easyui-combobox" name="classify" data-options="
                           url:'/static/js/data/classify.json',valueField:'text',textField:'text'"/>
                </td>
                <td>所属学科:</td>
                <td><input class="easyui-combobox" name="subject" data-options="
                           url:'/static/js/data/subject.json',valueField:'text',textField:'text'"/>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td colspan="3"><input class="easyui-textbox" name="note" data-options="width:354"/></td>
            </tr>
        </table>
    </form>
</div>
<!-- 定义顶部按钮 -->
<div id="expense_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">添加</a>
        <a id="expense_editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a id="expense_deteleBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="remove">删除</a>
        <a id="expense_auditBtn" class="easyui-linkbutton" iconCls="icon-man" plain="true" data-cmd="audit">审核</a>
        <a id="expense_viewBtn" class="easyui-linkbutton" iconCls="icon-tip" plain="true" data-cmd="view">查看</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        <a id="expense_searchBtn" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="search">高级查询</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="showAll">显示全部</a>
        <input class="easyui-textbox" name="keyword" data-options="prompt:'输入关键字查询',width:200,height:23" id="searchBtn" type="text">
        <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="expense_datagrid_search">查询</a>
    </div>
</div>
<!-- 对话框底部按钮 -->
<div id="expense_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<%--高级查询弹窗--%>
<div id="expenseSearch_dialog">
    <form method="post" id="expenseSearch_form">
        <table align="center" style="margin-top: 20px">
            <tr>
                <td>支出时间:</td>
                <td><input class="easyui-datebox" name="beginTime" id="expenseTimeBegin"/></td>
                <td> &nbsp; <--> </td>
                <td><input class="easyui-datebox" name="endTime" id="expenseTimeEnd"/></td>
            </tr>
            <tr>
                <td>支出金额:</td>
                <td><input class="easyui-textbox" name="minexpendamount"/></td>
                <td> &nbsp; <--> </td>
                <td><input class="easyui-textbox" name="maxexpendamount"/></td>
            </tr>
            <tr>
                <td>出纳人:</td>
                <td><input class="easyui-combobox" name="tellerId" data-options="
                           url:'/employee/queryFinanceList',valueField:'id',textField:'realname'"
                /></td>
                <td>经手人:</td>
                <td><input class="easyui-combobox" name="personId" data-options="
                           url:'/latenStudent/queryStudentForIncome',valueField:'id',textField:'name'"
                /></td>
            </tr>
            <tr>
                <td>支出方式:</td>
                <td><input class="easyui-combobox" name="expendtype" data-options="
                           url:'/static/js/data/incometype.json',valueField:'text',textField:'text'"
                /></td>
                <td>支出类型:</td>
                <td><input class="easyui-combobox" name="expendmode" data-options="
                           url:'/static/js/data/expendmode.json',valueField:'text',textField:'text'"
                /></td>
            </tr>
            <tr>
                <td>详细分类:</td>
                <td><input class="easyui-combobox" name="classify" data-options="
                           url:'/static/js/data/classify.json',valueField:'text',textField:'text'"
                /></td>
                <td>所属学科:</td>
                <td><input class="easyui-combobox" name="subject" data-options="
                           url:'/static/js/data/subject.json',valueField:'text',textField:'text'"
                /></td>
            </tr>
        </table>
    </form>
</div>

<div id="expenseSearch_dialog_buttons">
    <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="highsearch">查询</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>

<%--查看弹窗--%>
<div id="expense__view_dialog">
    <form method="post" id="expense_view_form">
        <table align="center" style="margin-top: 20px">
            <tr>
                <td>支出时间:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="expendtime"/></td>
                <td>支出金额:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="expendamount"/></td>
            </tr>
            <tr>
                <td>出纳人:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="teller.realname"/></td>
                <td>经手人:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="person.name"/></td>
            </tr>
            <tr>
                <td>支出方式:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="expendtype"/></td>
                <td>支出类型:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="expendmode"/></td>
            </tr>
            <tr>
                <td>详细分类:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="classify"/></td>
                <td>所属学科:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="subject"/></td>
            </tr>
            <tr>
                <td>单据号:</td>
                <td><input class="easyui-textbox" name="billnumber"/></td>
                <td>备注:</td>
                <td><input class="easyui-textbox" name="note"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="expense_view_dialog_buttons">
    <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="cancel">ok</a>
</div>
</body>
</html>