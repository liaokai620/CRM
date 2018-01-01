<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>正式学员管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/formalStudent.js"></script>
</head>
<body>
<table id="formalStudent_datagrid"></table>
<!-- 定义对话框 -->
<div id="formalStudent_dialog">
    <form method="post" id="formalStudent_form">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 20px">
            <tr>
                <td>选择班级:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="student.name"/></td>
            </tr>
            <tr>
                <td>入学时间:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="student.name"/></td>
            </tr>
            <tr>
                <td>付款方式:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="student.name"/></td>
            </tr>
            <tr>
                <td>营销人员:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="student.name"/></td>
            </tr>
            <tr>
                <td>总学费:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="student.name"/></td>
            </tr>
            <tr>
                <td>已缴费:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="student.name"/></td>
            </tr>
            <tr>
                <td>未缴费:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="student.name"/></td>
            </tr>
        </table>
    </form>
</div>
<!-- 定义顶部按钮 -->
<div id="formalStudent_datagrid_tb" style="height:30px">
    <div id="formalStudent_datagrid_search_btn" style="float:left">
        <%--<a id="formalStudent_editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>--%>
        <a id="formalStudent_viewBtn" class="easyui-linkbutton" iconCls="icon-tip" plain="true" data-cmd="view">查看</a>
        <a id="formalStudent_deleteBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="remove">删除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        <%--<a id="formalStudent_searchBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="search">高级查询</a>--%>
        <a id="formalStudent_toggleBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="toogle">高级查询</a>
        <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="showAll">显示全部</a> |
        <a class="easyui-linkbutton" iconCls="icon-man" plain="true" data-cmd="turnDialog">转班</a>
        <a class="easyui-linkbutton" iconCls="icon-back" plain="true" data-cmd="disappearDialog">流失</a>
        <a class="easyui-linkbutton" iconCls="icon-lock" plain="true" data-cmd="disappearDialog">休学</a>
    </div>
    <div id="formalStudent_datagrid_tooggle_tb">
        <input class="easyui-combobox" name="studentId" data-options="label:'学生姓名:',width:160,height:23,labelAlign:'left',labelWidth:56,
                           url:'/formalStudent/queryFormalStudent',valueField:'id',textField:'name',prompt:'选择学生查询'"/>
        <input class="easyui-combobox" name="salesmanId" data-options="label:'营销人员:',width:160,height:23,labelAlign:'left',labelWidth:56,
                           url:'/employee/querySalesmanList',valueField:'id',textField:'realname',prompt:'营销人员查询'"/>
        <input class="easyui-textbox" name="paymentType" data-options="label:'付款方式:',width:145,height:23,labelAlign:'left',labelWidth:56,
                           prompt:'付款方式查询'"/>
        <input class="easyui-combobox" name="stateId" data-options="label:'付款状态:',width:160,height:23,labelAlign:'left',labelWidth:56,
                           data: [{label: '未缴清',value: '0'},{label: '已缴清',value: '1'}] ,valueField: 'value',textField: 'label',prompt:'付款状态查询'"/>
        <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="formalStudent_datagrid_search">查询</a>
    </div>
</div>
<!-- 对话框底部按钮 -->
<div id="formalStudent_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<%--查看窗口--%>
<div id="formalStudent_view_dialog">
    <form id="formalStudent_view_form" data-options="url:'/formalStudent/view'" style="margin-top:30px;margin-left:30px;margin-right:30px;margin-bottom:30px">
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
                <td>班级:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="grade.name"/></td>
                <td>入学时间:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="enrolTime"/></td>
                <td>营销人员:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="salesman.realname"/></td>
            </tr>
            <tr style="margin-top:30px">
                <td>优惠方式:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="cheapWay.name"/></td>
                <td>优惠金额:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="cheapSum"/></td>
                <td>其他优惠:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="otherCost"/></td>
            </tr>
            <tr style="margin-top:30px">
                <td>催款次数:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="reminderTimes"/></td>
                <td>最近付款时间:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="nearlyPaymentTime"/></td>
                <td>最后付款时间:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="lastPaymentTime"/></td>
            </tr>
            <tr style="margin-top:30px">
                <td>学费:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="tuition"/></td>
                <td>其他费用:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="otherCost"/></td>
                <td>销售流水:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="saleWater"/></td>
            </tr>
            <tr style="margin-top:30px">
                <td>总学费:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="totalAmount"/></td>
                <td>已缴费:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="prepaid"/></td>
                <td>未缴费:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="arrearage"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="formalStudent_view_dialog_btn">
    <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="cancel">ok</a>
</div>
<%--转班弹窗--%>
<div id="formalStudent_turn_dialog">
    <form method="post" id="formalStudent_turn_form">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 20px">
            <tr>
                <td>学生姓名:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="student.name"/></td>
            </tr>
            <tr>
                <td>以前班级:</td>
                <td><input class="easyui-textbox" data-options="readonly:true" name="grade.name"/></td>
            </tr>
            <tr>
                <td>转入班级:</td>
                <td><input class="easyui-combobox" data-options="url:'/grade/listAll',valueField:'id',textField:'name'" name="grade.id"/></td>
            </tr>
        </table>
    </form>
</div>
<%--转班弹窗按钮--%>
<div id="formalStudent_turn_dialog_btn">
    <a class="easyui-linkbutton" iconCls="icon-ok" data-cmd="turn">确定</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">取消</a>
</div>

<%--密码校验弹窗--%>
<div id="formalStudent_checkout_dialog">
    <form method="post" id="formalStudent_checkout_form">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 20px">
            <tr>
                <td>账号:</td>
                <td><input class="easyui-textbox" data-options="readonly:false" name="username"/></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="password" class="easyui-textbox" data-options="readonly:false" name="password"/></td>
            </tr>
        </table>
    </form>
</div>
<%--密码校验按钮--%>
<div id="formalStudent_checkout_dialog_btn">
    <a class="easyui-linkbutton" iconCls="icon-ok" data-cmd="disappear">确定</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">取消</a>
</div>

</body>
</html>