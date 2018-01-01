<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>员工管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/studentFollow.js"></script>
</head>
<body>
<table id="followDatagrid"></table>
<!-- 定义对话框 -->
<div id="followDialog">
    <form id="followForm" method="post">
        <input type="hidden" name="id"/>
        <div style="margin-top:10px;margin-left:50px">
            <input name="time" class="easyui-datebox" data-options="label:'时间:',width:250">
            <input name="consultantWay" class="easyui-textbox" data-options="label:'咨询方式:',width:250">
            <input name="salesman.id" class="easyui-combobox" data-options="label:'营销人员:',width:250,
                url:'/employee/querySalesmanList',valueField:'id',textField:'username'">
        </div>
        <div style="margin-top:10px;margin-left:50px">
            <input name="student.id" id="latenStudent"   data-options="label:'潜在学员:',width:250,
                url:'/latenStudent/listAll',valueField:'id',textField:'name'">
            <input name="school.id" class="easyui-combobox" data-options="label:'所属学校:',width:250,
                url:'/school/listAll',valueField:'id',textField:'name'">
            <input name="contact.id" class="easyui-combobox" data-options="label:'学校联系人:',width:250,
                url:'/contact/listAll',valueField:'id',textField:'name'">
        </div>

        <div style="margin-top:10px;margin-left:50px">
            <input name="tel" class="easyui-textbox" data-options="label:'电话:',width:250">
            <input name="qq" class="easyui-textbox" data-options="label:'QQ:',width:250">
            <input name="advisorTime" class="easyui-textbox" data-options="label:'咨询时长:',width:250">
        </div>
        <div style="margin-top:10px;margin-left:50px">
            <input name="meetingTime" class="easyui-datebox" data-options="label:'约访日期:',width:250">
            <input name="contact.id" class="easyui-combobox" data-options="label:'当前状态:',width:250,
                url:'/dictionary/listItemByParentId?parentId=5',valueField:'id',textField:'name'">
            <input name="wantLevel.id" class="easyui-combobox" data-options="label:'意向程度:',width:250,
                url:'/dictionary/listItemByParentId?parentId=4',valueField:'id',textField:'name'">
        </div>

        <div style="margin-top:10px;margin-left:50px">
            <input name="nextMeetingTime" class="easyui-datebox" data-options="label:'下次访问时间:',width:250">
            <input name="order" class="easyui-textbox" data-options="label:'跟进目的:',width:250">
            <input name="grade.id" class="easyui-combobox" data-options="label:'意向班级:',width:250,
           url:'/grade/listAll',valueField:'id',textField:'name' ">
        </div>
        <div style="margin-top:10px;margin-left:50px">
            <input name="digest" class="easyui-textbox" data-options="label:'摘要:',width:600,height:100,multiline:true">
        </div>

        <div style="margin-top:10px;margin-left:50px">
            <input name="content" class="easyui-textbox" data-options="label:'交流内容:',width:600,height:100,multiline:true">
        </div>
    </form>
</div>
<div id="auditDialog">
    <form id="auditForm" method="post">
        <input type="hidden" name="id"/>
        <div style="margin-top:10px;margin-left:50px">
            <input name="time" readonly class="easyui-datebox" data-options="label:'时间:',width:250">
            <input name="consultant.username" readonly class="easyui-textbox" data-options="label:'咨询人员:',width:250"/>
            <input name="consultantWay"  readonly class="easyui-textbox" data-options="label:'咨询方式:',width:250">
        </div>
        <div style="margin-top:10px;margin-left:50px">
            <input name="student.name"  readonly class="easyui-textbox" data-options="label:'姓名:',width:250">
            <input name="qq" readonly class="easyui-textbox" data-options="label:'QQ:',width:250">
            <input name="tel"readonly  class="easyui-textbox" data-options="label:'电话:',width:250">
        </div>
        <div style="margin-top:10px;margin-left:50px">
            <input name="level.id" class="easyui-combobox" data-options="label:'评分:',width:250,
        url:'/dictionary/listItemByParentId?parentId=8',valueField:'id',textField:'name' ">
        </div>
        <div style="margin-top:10px;margin-left:50px">
            <input name="auditInfo" class="easyui-textbox" data-options="label:'审核说明:',width:700,height:100,multiline:true"/>
        </div>
        <div style="margin-top:10px;margin-left:50px">
            <input name="digest" class="easyui-textbox" readonly data-options="label:'咨询内容:',width:700,height:150,multiline:true"/>
        </div>
    </form>
</div>
<!-- 对话框底部按钮 -->
<div id="followToolbar">
    <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">添加</a>
    <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="remove">删除</a>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="audit">审核</a>
    <input class="easyui-searchbox" id="keyword" name="keyword" searcher="searcher" prompt="姓名/编号/QQ"/>
</div>
<div id="dialogbuttons">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>

<div id="auditButttons" >
    <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="doAudit">审核</a>
    <a class="easyui-linkbutton" iconCls="icon-no" plain="true" data-cmd="doCancel">取消</a>
</div>
</body>
</html>