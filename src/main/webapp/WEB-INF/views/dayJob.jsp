<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 侯学博
  Date: 2017/11/9
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>当日任务</title>
    <jsp:include page="common.jsp"/>
    <script type="text/javascript" src="/static/js/views/dayJob.js"></script>
</head>
<body>


<%--一个表格--%>


<table id="dayJob_datagrid">


</table>
<%-- 表格的工具栏--%>
<div id="dayJob_datagrid_btns">
    <div>
        <%-- <shiro:hasRole name="manager">--%>
        <a class="easyui-linkbutton" style="background-color: orange" data-cmd="add">指派任务</a>
        <a class="easyui-linkbutton" style="background-color: orange" data-cmd="edit">修改任务</a>
        <a class="easyui-linkbutton" style="background-color: orange" data-cmd="delete">删除任务</a>


        <a class="easyui-linkbutton" style="background-color: orange" data-cmd="markSuccess">标记完成</a>
        <a class="easyui-linkbutton" style="background-color: orange" data-cmd="markFailed">标记失败</a>
        <%--   </shiro:hasRole>--%>


            <a type="hidden" id="editDescriptionBtn" class="easyui-linkbutton" style="background-color: orange"
               data-cmd="editDescription">修改任务描述</a>
    </div>

    <div>

        <input id="searchinputTime" name="queryTime" class="easyui-datebox" label="任务日期" data-options="
                   width:200">

        <input id="searchHandler" name="querHandlerId" class="easyui-combobox" label="负责人"
               data-options="
                   width:200,
                   url:'/dayJob/checkEmployeeForForm',
                   valueField:'id',
                   textField:'username'">
        <a class="easyui-linkbutton" style="background-color: orange" data-cmd="search">查询</a>
    </div>


</div>

<%--提交的表单--%>
<div id="dayJob_dialog">
    <form id="dayJobForm" method="post">
        <div>
            <input type="hidden" name="id">
            <input name="handler.id" class="easyui-combobox" label='负责人: '
                   data-options="
                   width:150,
                   url:'/dayJob/checkEmployeeForForm',
                   valueField:'id',
                   textField:'username'">
        </div>
        <div>
            <input class="easyui-textbox" multiline="true" style="height: 80px ;width:200px" name="jobDescription"
                   label='任务描述: '>
        </div>
        <div>
            <input class="easyui-textbox" multiline="true" style="height: 80px ;width:200px" name="dealDescription"
                   label='处理描述: '>
        </div>
    </form>


</div>
<div id="dayJob_dialogDeal">

    <form id="dealDescriptionForm" method="post">
        <div>
            <input type="hidden" name="id">
        </div>

        <div>
            <input class="easyui-textbox" multiline="true" style="height: 200px ;width:200px" name="dealDescription"
                   label='处理描述: '>
        </div>
    </form>

</div>
<%--表单的提交按钮--%>
<div id="dayJob_dialog_btns">
    <a class="easyui-linkbutton" style="background-color: orange" iconCls="icon-save" data-cmd="save">提交</a>
    <a class="easyui-linkbutton" style="background-color: orange" iconCls="icon-cancel" data-cmd="cancel">取消</a>
</div>
<%--描述修改表单的提交按钮--%>
<div id="dayJob_dialogDeal_btns">
    <a class="easyui-linkbutton" style="background-color: orange" iconCls="icon-save" data-cmd="saveDeal">提交</a>
    <a class="easyui-linkbutton" style="background-color: orange" iconCls="icon-cancel" data-cmd="cancelDeal">取消</a>
</div>


</body>
</html>
