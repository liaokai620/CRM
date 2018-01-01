<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>收款管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/income.js"></script>
</head>
<body>
<table id="income_datagrid"></table>
<!-- 定义对话框 -->
<div id="income_dialog">
    <form method="post" id="income_form">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 20px">
            <tr>
                <td>学员:</td>
                <td><input id="studentCombobox" class="easyui-combobox" name="student.id" data-options="
                           url:'/latenStudent/queryStudentForIncome',valueField:'id',textField:'name'"
                /></td>
                <td>收款方式:</td>
                <td><input class="easyui-combobox" name="incometype" data-options="
                           url:'/static/js/data/incometype.json',valueField:'text',textField:'text'"/>
                </td>
            </tr>
            <tr>
                <td>收款金额:</td>
                <td><input class="easyui-textbox" name="incomeamount"/></td>
                <td>收款时间:</td>
                <td><input class="easyui-datebox" name="incometime"/></td>
            </tr>
            <tr>
                <td>班级:</td>
                <td><input class="easyui-combobox" name="grade.id" data-options="
                           url:'/grade/listAll',valueField:'id',textField:'name'"/>
                </td>
                <td>学科:</td>
                <td><input class="easyui-combobox" name="subject" data-options="
                           url:'/static/js/data/subject.json',valueField:'text',textField:'text'"/>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td colspan="3"><input class="easyui-textbox" name="remark" data-options="width:354"/></td>
            </tr>
        </table>
    </form>
</div>
<!-- 定义顶部按钮 -->
<div id="income_datagrid_tb">
    <div>
        <a id="income_addBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">添加</a>
        <a id="income_editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a id="income_quitBtn" class="easyui-linkbutton" iconCls="icon-tip" plain="true" data-cmd="view">查看</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        <a id="income_searchBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="search">高级查询</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">显示全部</a>
        <a id="income_totalBtn" class="easyui-linkbutton" iconCls="icon-sum" plain="true" data-cmd="total">统计</a>
        <a id="income_recheckBtn" class="easyui-linkbutton" iconCls="icon-redo" plain="true" data-cmd="recheck">复核</a>
        <a id="income_backBtn" class="easyui-linkbutton" iconCls="icon-undo" plain="true" data-cmd="backrecheck">取消复核</a>
        <a id="income_auditBtn" class="easyui-linkbutton" iconCls="icon-man" plain="true" data-cmd="audit">提交审核</a>
        <input id="searchBtn" type="text">
    </div>
</div>
<!-- 对话框底部按钮 -->
<div id="income_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<%--高级查询弹窗--%>
<div id="incomeSearch_dialog">
    <form method="post" id="incomeSearch_form">
        <table align="center" style="margin-top: 20px">
            <tr>
                <td>收款时间:</td>
                <td><input class="easyui-datebox" name="beginTime" id="incomeTimeBegin"/></td>
                <td>&nbsp &nbsp → &nbsp</td>
                <td><input class="easyui-datebox" name="endTime" id="incomeTimeEnd"/></td>
            </tr>
            <tr>
                <td>收款金额:</td>
                <td><input class="easyui-textbox" name="minIncomeAmount" id="minReceiptAmount"/></td>
                <td><----></td>
                <td><input class="easyui-textbox" name="maxIncomeAmount" id="maxReceiptAmount"/></td>

            </tr>
            <tr>
                <td>收款人:</td>
                <td><input class="easyui-combobox" name="payeeId" id="payeeName" data-options="
                           url:'/employee/queryFinanceList',valueField:'id',textField:'realname'"
                /></td>
                <td>收款方式:</td>
                <td><input class="easyui-combobox" name="incomeTypeId" id="receiptMethodId" data-options="
                           url:'/static/js/data/incometype.json',valueField:'text',textField:'text'"
                /></td>
            </tr>
            <tr>
                <td>收款类型:</td>
                <td><input class="easyui-combobox" name="incomemodeId" id="receiptTypeId" data-options="
                           url:'/static/js/data/incomemode.json',valueField:'text',textField:'text'"
                /></td>
                <td>班级:</td>
                <td><input class="easyui-combobox" name="gradeId" id="schoolClassId" data-options="
                           url:'/grade/listAll',valueField:'id',textField:'name'"
                /></td>

            </tr>
            <tr>
                <td>学科:</td>
                <td><input class="easyui-combobox" name="subjectId" id="subjectId" data-options="
                           url:'/static/js/data/subject.json',valueField:'text',textField:'text'"
                /></td>
                <td>营销人员:</td>
                <td><input class="easyui-combobox" name="salesmanId" id="salesmanId" data-options="
                           url:'/employee/querySalesmanList',valueField:'id',textField:'realname'"
                /></td>
            </tr>
        </table>
    </form>
</div>
<div id="incomeSearch_dialog_buttons">
    <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="search">查询</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>

<%--统计弹窗--%>
<div id="income_total_dialog">
    <form method="post" id="income_total_form">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 20px">
            <tr>
                <td>收款总个数:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="totalcount"/></td>
            </tr>
            <tr>
                <td>收款总金额:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="totalamount"/></td>
            </tr>
        </table>
    </form>
</div>
<%--统计弹窗按钮--%>
<div id="income_total_dialog_btn">
    <a class="easyui-linkbutton" iconCls="icon-ok" data-cmd="cancel">确定</a>
</div>

</body>
</html>