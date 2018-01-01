<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>员工管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/exam.js"></script>
</head>
<body>
<table id="examDatagrid"></table>
<!-- 定义对话框 -->
<div id="examDialog">
    <form id="examForm" method="post">
        <input type="hidden" name="id"/>
        <div style="margin-top:10px;margin-left:50px">
            <input name="name"  class="easyui-textbox" data-options="label:'考生姓名:',width:250">
        </div>

        <div style="margin-top:10px;margin-left:50px">
            <input  name="salesman.id" class="easyui-combobox" data-options="label:'营销人员:',width:250,
                url:'/employee/listAllSalesMan',valueField:'id',textField:'username'">
        </div>
        <div style="margin-top:10px;margin-left:50px">
            <input  name="qq" class="easyui-textbox" data-options="label:'QQ:',width:250">
        </div>

        <div style="margin-top:10px;margin-left:50px">
            <input  name="tel" class="easyui-textbox" data-options="label:'电话:',width:250">
        </div>

        <div style="margin-top:10px;margin-left:50px">
            <input  name="wantGrade.id" class="easyui-combobox" data-options="label:'意向班级:',width:250,
           url:'/grade/listAll',valueField:'id',textField:'name' ">
        </div>

        <div style="margin-top:10px;margin-left:50px">
            <input  name="sn" class="easyui-textbox" data-options="label:'考试编号:',width:250">
        </div>
        <div style="margin-top:10px;margin-left:50px">
            <input name="examTime"  class="easyui-datebox"  data-options="label:'考试时间:',width:250">
        </div>
    </form>
</div>

</div>
<!-- 对话框底部按钮 -->
<div id="examToolbar">
    <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">添加</a>
    <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="remove">删除</a>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    <a class="easyui-linkbutton"  iconCls="icon-ok"  plain="true" data-cmd="result">登记结果</a>
    <input class="easyui-searchbox" id="keyword"  name="keyword" searcher="searcher" prompt="姓名/编号/QQ" />
</div>
<div id="dialogbuttons">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="saveItem">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>