<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>学校主要联系人</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/contact.js"></script>
</head>
<body>
<table id="contact_datagrid"></table>

<!--列表上按钮-->
<div id="contact_datagrid_btn">
    <div>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-cmd="delete">删除</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="query">高级查询</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-large-shapes',plain:true" data-cmd="see">查看</a>
    </div>
    <div align="right">
        关键字:<input id="scontact_search"/>
    </div>
</div>
<!--高级查询弹框-->
<div id="query_dialog">
    <form method="post" id="query_form">
        <fieldset class="fieldset-border">
            <legend style="color: #DEB887;font-size: 20px;font-weight: bold">高级查询</legend>
            <table>
                <tr>
                    <td>
                        <input type="text" name="name" class="easyui-textbox"
                               data-options="label:'姓名:',labelPosition:'top', width:150">
                    </td>
                    <td> <input type="text" name="schoolId" class="easyui-combobox"
                                data-options="
                            width:150,
                            label:'所属学校',
                            labelPosition:'top',
                            url:'/school/querySchoolListForContactForm',
                             valueField:'id',
						     textField:'name'
                           ">
                    </td>
                </tr>
            </table>
        </fieldset>

    </form>
</div>

<!-- 定义对话框 -->
<div id="contact_dialog_bt">
    <form id="contact_form" method="post">
        <input type="hidden" name="id">
        <table align="center">
            <tr>
                <td>
                    <input type="text" name="name" class="easyui-textbox"
                           data-options="label:'姓名:',labelPosition:'top', width:150">
                </td>
                <td>
                    <input type="text" name="school.id" class="easyui-combobox"
                           data-options="
                            width:150,
                            label:'所属学校',
                            labelPosition:'top',
                            url:'/school/querySchoolListForContactForm',
                             valueField:'id',
						     textField:'name'
                           ">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="tel" class="easyui-textbox"
                           data-options="label:'联系方式:',labelPosition:'top', width:150">
                </td>
                <td>
                    <input type="text" name="email" class="easyui-textbox"
                           data-options="label:'邮箱:',labelPosition:'top', width:150">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="position" class="easyui-textbox"
                           data-options="label:'职务:',labelPosition:'top', width:150">
                </td>
                <td>
                    主要联系人
                    否:<input type="checkbox" name="main" value="0">
                    是:<input type="checkbox" name="main" value="1">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="introduction" class="easyui-textbox"
                           data-options="label:'简介:',labelPosition:'top', width:150">
                </td>
                <td>
                    性别
                    女:<input type="checkbox" name="gender" value="0">
                    男:<input type="checkbox" name="gender" value="1">
                </td>
            </tr>
        </table>
    </form>
</div>
<!-- 查看框 -->
<div id="see_dialog">
    <form method="post" id="see_form">
        <input type="hidden" name="id">
        <table align="center">
            <tr>
                <td>
                    <input type="text" name="name" class="easyui-textbox"
                           data-options="label:'姓名:',labelPosition:'top', width:150,readonly:true">
                </td>
                <td>
                    <input type="text" name="school.id" class="easyui-combobox"
                           data-options="
                            width:150,
                            label:'所属学校',
                            labelPosition:'top',
                            url:'/school/querySchoolListForContactForm',
                             valueField:'id',
						     textField:'name'
                           ,readonly:true">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="tel" class="easyui-textbox"
                           data-options="label:'联系方式:',labelPosition:'top', width:150,readonly:true">
                </td>
                <td>
                    <input type="text" name="email" class="easyui-textbox"
                           data-options="label:'邮箱:',labelPosition:'top', width:150,readonly:true">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="position" class="easyui-textbox"
                           data-options="label:'职务:',labelPosition:'top', width:150,readonly:true">
                </td>
                <td>
                    主要联系人
                    否:<input type="checkbox" name="main" value="0,readonly:true">
                    是:<input type="checkbox" name="main" value="1,readonly:true">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="introduction" class="easyui-textbox"
                           data-options="label:'简介:',labelPosition:'top', width:150,readonly:true">
                </td>
                <td>
                    性别
                    女:<input type="checkbox" name="gender" value="0,readonly:true">
                    男:<input type="checkbox" name="gender" value="1,readonly:true">
                </td>
            </tr>
        </table>
    </form>
</div>
<!--弹框的底部按钮-->
<div id="contact_dialog_btn">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<!--高级查询底部按钮-->
<div id="query_dialog_btn">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="query_save">查询</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="query_cancel">取消</a>
</div>
</body>
</html>