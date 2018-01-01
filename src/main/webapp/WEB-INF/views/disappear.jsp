<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>学员流失管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/disappear.js"></script>
</head>
<body>
<table id="disappear_datagrid"></table>
<!-- 定义对话框 -->
<div id="disappear_dialog">
    <form method="post" id="disappear_form">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 20px">
            <tr>
                <td>流失学生:</td>
                <td><input class="easyui-combobox" name="student.id" data-options="
                           url:'/formalStudent/queryFormalStudent',valueField:'id',textField:'name'"
                /></td>
            </tr>
            <tr>
                <td>上课天数:</td>
                <td><input class="easyui-textbox" name="toclassdate"/></td>
            </tr>
            <tr>
                <td>流失班级:</td>
                <td><input class="easyui-combobox" name="grade.id" data-options="
                           url:'/grade/listAll',valueField:'id',textField:'name'"
                /></td>
            </tr>
            <tr>
                <td>流失原因:</td>
                <td><input class="easyui-textbox" name="disappearcause"/></td>
            </tr>
            <tr>
                <td>流失时间:</td>
                <td><input class="easyui-datebox" name="disappeartime"/></td>
            </tr>
            <tr>
                <td>营销人员:</td>
                <td><input class="easyui-combobox" name="salesman.id" data-options="
                           url:'/employee/querySalesmanList',valueField:'id',textField:'realname'"
                /></td>
            </tr>
            <tr>
                <td>是否退款:</td>
                <td><input id="refund" name="refund" class="easyui-switchbutton" data-options="
                            onText:'是',offText:'否',width:147"></td>
            </tr>
        </table>
    </form>
</div>
<!-- 定义顶部按钮 -->
<div id="disappear_datagrid_tb" style="height:30px">
    <div id="disappear_datagrid_search_btn" style="float:left">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">添加</a>
        <a id="disappear_editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a id="disappear_viewBtn" class="easyui-linkbutton" iconCls="icon-tip" plain="true" data-cmd="view">查看</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        <%--<a id="disappear_searchBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="search">高级查询</a>--%>
        <a id="disappear_toggleBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="toogle">高级查询</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="showAll">显示全部</a>
    </div>
    <div id="disappear_datagrid_tooggle_tb">
        <input class="easyui-combobox" name="studentId" data-options="label:'流失学生:',width:160,height:23,labelAlign:'left',labelWidth:56,
                           url:'/formalStudent/queryFormalStudent',valueField:'id',textField:'name',prompt:'选择学生查询'"/>
        <input class="easyui-textbox" name="toclassdate" data-options="label:'上课天数:',width:145,height:23,labelAlign:'left',labelWidth:56,
                           prompt:'上课天数查询'"/>
        <input class="easyui-combobox" name="gradeId" data-options="label:'流失班级:',width:160,height:23,labelAlign:'left',labelWidth:56,
                           url:'/grade/listAll',valueField:'id',textField:'name',prompt:'选择班级查询'"/>
        <input class="easyui-combobox" name="salesmanId" data-options="label:'营销人员:',width:160,height:23,labelAlign:'left',labelWidth:56,
                           url:'/employee/querySalesmanList',valueField:'id',textField:'realname',prompt:'营销人员查询'"/>
        <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="disappear_datagrid_search">查询</a>
    </div>
</div>
<!-- 对话框底部按钮 -->
<div id="disappear_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<%--高级查询弹窗--%>
<div id="disappearSearch_dialog">
    <form method="post" id="disappearSearch_form">
        <table align="center" style="margin-top: 20px">
            <tr>
                <td>流失学生:</td>
                <td><input class="easyui-combobox" name="studentId" data-options="
                           url:'/formalStudent/queryFormalStudent',valueField:'id',textField:'name'"/>
                </td>
            </tr>
            <tr>
                <td>上课天数:</td>
                <td><input class="easyui-textbox" name="toclassdate"/></td>
            </tr>
            <tr>
                <td>流失班级:</td>
                <td><input class="easyui-combobox" name="gradeId" data-options="
                           url:'/grade/listAll',valueField:'id',textField:'name'"
                /></td>
            </tr>
            <tr>
                <td>流失时间:</td>
                <td><input class="easyui-textbox" name="disappeartime"/></td>
            </tr>
            <tr>
                <td>营销人员:</td>
                <td><input class="easyui-combobox" name="salesmanId" data-options="
                           url:'/employee/querySalesmanList',valueField:'id',textField:'realname'"
                /></td>
            </tr>
        </table>
    </form>
</div>

<div id="disappearSearch_dialog_buttons">
    <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="highsearch">查询</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>