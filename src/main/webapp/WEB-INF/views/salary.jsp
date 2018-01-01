<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>工资管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/salary.js"></script>
    <script type="text/javascript" src="/static/js/plu/print.js"></script>

</head>

<body>
<table id="salary_dataGrid"></table>
<!-- 定义对话框 -->
<div id="salary_dialog">
    <form id="salary_form" method="post">
        <input type="hidden" name="id">
        <div align="center" style="margin-top: 20px;">
            <div>
                <select name="employee.id" class="easyui-combobox"
                        data-options="
						 width:230,
						 label:'员工姓名:',
						 labelPosition:'left',
						 valueField:'id',
						 textField:'realname',
						 url:'/employee/selectListForEmployeeForm'
						">
                </select>
                <input type="text" name="salarytime" class="easyui-datebox"
                       data-options="label:'日期:',labelPosition:'left', width:230">
            </div>
            <div style="margin-top: 10px;">
                <input type="text" name="basesalary" class="easyui-textbox"
                       data-options="label:'基本工资:',labelPosition:'left', width:230">
                <input type="text" name="alldaysalary" class="easyui-textbox"
                       data-options="label:'全勤奖金:',labelPosition:'left', width:230">
            </div>
            <div style="margin-top: 10px;">
                <input type="text" name="subsidy" class="easyui-textbox"
                       data-options="label:'补贴:',labelPosition:'left', width:230">
                <input type="text" name="fivesalary" class="easyui-textbox"
                       data-options="label:'五险一金:',labelPosition:'left', width:230">
            </div>
            <div style="margin-top: 10px;">
                <input type="text" name="yearendsalary" class="easyui-textbox"
                       data-options="label:'年终奖:',labelPosition:'left', width:230">
            </div>

        </div>
    </form>
</div>
<!-- 定义顶部按钮 -->
<div id="tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a id="salary_editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a id="salary_quitBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="quit">确认</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="out">导出Excel</a>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="print">打印</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    </div>
    <div>
        <input id="searchnameBtn" type="text">
        <input id="searchstartBtn" type="text">
        <input id="searchendBtn" type="text">

    </div>
    <div>
        状态:<select id="searchStateBtn" type="text">
            <option value="-1" selected="selected">--请选择--</option>
            <option value="0">已确认</option>
            <option value="1">未确认</option>
        </select>
        <input editable="false"
               class="easyui-datebox"
               name="makedate" id="makedate"
               data-option="formatter:myformatter,parser:myparser,"
               style="width:150px;">
    </div>

</div>
<!-- 对话框底部按钮 -->
<div id="bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>