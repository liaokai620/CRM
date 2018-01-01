<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>学员升班留级</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/promotion.js"></script>
</head>
<body>
<table id="promotion_datagrid"></table>
<!-- 定义对话框 -->
<div id="promotion_dialog">
    <form method="post" id="promotion_form">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 20px">
            <tr>
                <td>学生姓名:</td>
                <td><input class="easyui-combobox" name="student.id" data-options="
                           url:'/formalStudent/queryFormalStudent',valueField:'id',textField:'name'"/>
                </td>
            </tr>
            <tr>
                <td>升班/留级时间:</td>
                <td><input class="easyui-datebox" name="promotiontime"/></td>
            </tr>
            <tr>
                <td>流入班级:</td>
                <td><input class="easyui-combobox" name="aftergrade.id" data-options="
                           url:'/grade/listAll',valueField:'id',textField:'name'"/>
                </td>
            </tr>
            <tr>
                <td>销售流水:</td>
                <td><input class="easyui-textbox" name="account"/></td>
            </tr>
            <tr>
                <td>其他费用:</td>
                <td><input class="easyui-textbox" name="tip"/></td>
            </tr>
            <tr>
                <td>升级留级:</td>
                <td style="text-align:left">
                    <span class="radioSpan">
                        <input type="radio" name="state" value="1">升级</input>
                        <input type="radio" name="state" value="0">留级</input>
                    </span>
                </td>
            </tr>
        </table>
    </form>
</div>
<!-- 定义顶部按钮 -->
<div id="promotion_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">添加</a>
        <a id="promotion_editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a id="promotion_viewBtn" class="easyui-linkbutton" iconCls="icon-tip" plain="true" data-cmd="view">查看</a>
        <a id="promotion_deteleBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="remove">删除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="showAll">显示全部</a>
        <input class="easyui-textbox" name="keyword" data-options="prompt:'输入关键字查询',width:200,height:23" id="searchBtn" type="text">
        <input class="easyui-combobox" name="beforegradeId" data-options="label:'以前班级:',width:220,height:23,labelAlign:'left',labelWidth:56,
                           url:'/grade/listAll',valueField:'id',textField:'name',prompt:'选择班级查询'"/>
        <input class="easyui-combobox" name="salesmanId" data-options="label:'营销人员:',width:200,height:23,labelAlign:'left',labelWidth:56,
                           url:'/employee/querySalesmanList',valueField:'id',textField:'realname',prompt:'营销人员查询'"/>
        <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="promotion_datagrid_search">查询</a>
    </div>
</div>
<!-- 对话框底部按钮 -->
<div id="promotion_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<%--查看窗口--%>
<div id="promotion_view_dialog">
    <form id="promotion_view_form" data-options="url:'/promotion/view'" style="margin-top:30px;margin-left:30px;margin-right:30px;margin-bottom:30px">
        <table>
            <tr style="margin-top:30px">
                <td>学生:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="student.name"/></td>
                <td>qq:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="student.qq"/></td>
                <td>电话:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="student.tel"/></td>
            </tr>
            <tr style="margin-top:30px">
                <td>以前班级:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="beforegrade.name"/></td>
                <td>流入班级:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="aftergrade.name"/></td>
                <td>流入时间:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="promotiontime"/></td>
            </tr>
            <tr style="margin-top:30px">
                <td>营销人员:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="salesman.realname"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="promotion_view_dialog_btn">
    <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="cancel">ok</a>
</div>
</body>
</html>