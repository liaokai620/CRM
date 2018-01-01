<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>学校</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/school.js"></script>
</head>
<body>
<table id="school_datagrid"></table>
<!--列表上按钮-->
<div id="school_datagrid_btn">
    <div>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="update">编辑</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-cmd="delete">删除</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="query">高级查询</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-large-shapes',plain:true" data-cmd="see">查看</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" data-cmd="train">实训</a>
        <div align="right">
            关键字:<input id="school_search"/>
        </div>
    </div>
</div>
<!--列表上按钮-->
<div id="train_datagrid_btn">
    <div>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="train_add">新增</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="train_update">编辑</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-cmd="train_delete">删除</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="train_reload">刷新</a>
    </div>
</div>
<!--高级查询弹框-->
<div id="query_dialog">
    <form method="post" id="query_form">
        <fieldset class="fieldset-border">
            <legend style="color: #DEB887;font-size: 20px;font-weight: bold">高级查询</legend>
            <table>
                <tr>
                   <td><input name="name" class="easyui-textbox" data-options="label:'学校名字  :',labelPosition:'top', width:150" id="name_query" /></td>
                    <td><input name="marketingId" class="easyui-combobox" id="marketingId_query"
                                             data-options="url:'/employee/queryEmployeeForSchoolContact',valueField:'id',textField:'realname',label:'营销人员:',labelPosition:'top', width:150" /></td>
                </tr>
            </table>
        </fieldset>

    </form>
</div>
<!--定义弹框-->
<div id="school_dialog">
    <form id="school_form" method="post">
        <input type="hidden" name="id">
        <table align="center">
            <tr>
                <td>
                    <input type="text" name="name" class="easyui-textbox"
                           data-options="label:'学校名称:',labelPosition:'top', width:150">
                </td>
                <td>
                    <input type="text" name="shortname" class="easyui-textbox"
                           data-options="label:'学校简称:',labelPosition:'top', width:150">
                </td>
                <td>
                    <input type="text" name="address" class="easyui-textbox"
                           data-options="label:'学校地址:',labelPosition:'top', width:150">
                </td>
            </tr>
            <tr>
                <td>
                    <select id="employeenumber" class="easyui-combobox" name="maket.id"
                            data-options="url:'/employee/queryEmployeeForSchoolContact',valueField:'id',textField:'realname',label:'营销人员:',labelPosition:'top', width:150">
                    </select>
                </td>
                <td>
                    <select id="cc" class="easyui-combobox" name="deptId" data-options="label:'所属部门:',labelPosition:'top', width:150">
                        <option value="0">--请选择部门--</option>
                        <option value="1">教育部</option>
                        <option value="2">工信部</option>
                        <option value="3">国防部</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <select id="natureId" class="easyui-combobox" name="nature.id"
                            data-options="url:'/dictionary/queryNatureForSchool',valueField:'id',textField:'name',label:'学校性质:',labelPosition:'top', width:150">
                    </select>
                </td>
                <td>
                    <input type="text" name="createtime" class="easyui-datebox"
                           data-options="label:'创建时间:',labelPosition:'top', width:150">
                </td>
                <td>
                    <select id="starId" class="easyui-combobox" name="star.id"
                            data-options="url:'/dictionary/queryStarForSchool',valueField:'id',textField:'name',label:'星级:',labelPosition:'top', width:150">
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="tel" class="easyui-textbox"
                           data-options="label:'联系电话:',labelPosition:'top', width:150">
                </td>
                <td>
                    <input type="text" name="studentnumber" class="easyui-textbox"
                           data-options="label:'学生人数:',labelPosition:'top', width:150">
                </td>
                <td>
                    <input type="text" name="teachernumber" class="easyui-textbox"
                           data-options="label:'教师人数',labelPosition:'top', width:150">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="employeenumber" class="easyui-textbox"
                           data-options="label:'员工人数:',labelPosition:'top', width:150">
                </td>
                <td>
                    <select id="academicid" class="easyui-combobox" name="academicid" data-options="label:'学制:',labelPosition:'top', width:150">
                        <option value="2">两年</option>
                        <option value="4">四年</option>
                        <option value="5">五年(3+2)</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <input name="introduction" class="easyui-textbox"
                           data-options="multiline:true,label:'学校简介:',labelPosition:'top', width:200,height:200">
                </td>
            </tr>
        </table>
    </form>
</div>
<!--查看弹框-->
<div id="see_dialog">
    <form method="post" id="see_form">
        <table align="center">
            <tr>
                <td>
                    <input type="text" name="name" class="easyui-textbox"
                           data-options="label:'学校名称:',labelPosition:'top', width:150, readonly:true">
                </td>
                <td>
                    <input type="text" name="shortname" class="easyui-textbox"
                           data-options="label:'学校简称:',labelPosition:'top', width:150, readonly:true">
                </td>
                <td>
                    <input type="text" name="address" class="easyui-textbox"
                           data-options="label:'学校地址:',labelPosition:'top', width:150, readonly:true">
                </td>
            </tr>
            <tr>
                <td>
                    <select class="easyui-combobox" name="maket.id"
                            data-options="url:'/employee/queryEmployeeForSchoolContact',readonly:true, valueField:'id',textField:'realname',label:'营销人员:',labelPosition:'top', width:150">
                    </select>
                </td>
                <td>
                    <select class="easyui-combobox" name="deptId" data-options="label:'所属部门:',labelPosition:'top', width:150, readonly:true">
                        <option value="0">--请选择部门--</option>
                        <option value="1">教育部</option>
                        <option value="2">工信部</option>
                        <option value="3">国防部</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <select class="easyui-combobox" name="nature.id"
                            data-options="url:'/dictionary/queryNatureForSchool',valueField:'id',readonly:true, textField:'name',label:'学校性质:',labelPosition:'top', width:150">
                    </select>
                </td>
                <td>
                    <input type="text" name="createtime" class="easyui-datebox"
                           data-options="label:'创建时间:',labelPosition:'top', width:150, readonly:true">
                </td>
                <td>
                    <select class="easyui-combobox" name="star.id"
                            data-options="url:'/dictionary/queryStarForSchool',readonly:true, valueField:'id',textField:'name',label:'星级:',labelPosition:'top', width:150">
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="tel" class="easyui-textbox"
                           data-options="label:'联系电话:',labelPosition:'top', width:150, readonly:true">
                </td>
                <td>
                    <input type="text" name="studentnumber" class="easyui-textbox"
                           data-options="label:'学生人数:',labelPosition:'top', width:150, readonly:true">
                </td>
                <td>
                    <input type="text" name="teachernumber" class="easyui-textbox"
                           data-options="label:'教师人数',labelPosition:'top', width:150, readonly:true">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="employeenumber" class="easyui-textbox"
                           data-options="label:'员工人数:',labelPosition:'top', width:150,readonly:true">
                </td>
                <td>
                    <select  class="easyui-combobox" name="academicid" data-options="label:'学制:',labelPosition:'top', width:150,readonly:true">
                        <option value="2">两年</option>
                        <option value="4">四年</option>
                        <option value="5">五年(3+2)</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <input name="introduction" class="easyui-textbox"
                           data-options="multiline:true,label:'学校简介:',labelPosition:'top', width:200,height:200, readonly:true">
                </td>
            </tr>
        </table>
    </form>
</div>
<!--弹框底部按钮-->
<div id="school_dialog_btn">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<!--高级查询底部按钮-->
<div id="query_dialog_btn">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="query_save">查询</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="query_cancel">取消</a>
</div>

<!--实训相关-->
<div id="school_dialogOfshowTraining">
    <table id="school_datagridOfshowTraining"></table>
</div>
<div id="school_dialogOfshowTrainingAdd">
    <input type="hidden" name="schoolId" id="schoolId"/>
    <form method="post" id="school_showTrainingForm">
        <input type="hidden" name="id" id="trainingID"/>
            <table>
                <tr>
                    <td>实训内容:</td><td><input name="content" class="easyui-textbox" /></td>
                    <td>实训时间:</td><td><input name="time" class="easyui-datebox"  /></td>
                </tr>
                <tr style="text-align: right;">
                    <td>实训地点:</td><td><input name="address" class="easyui-textbox"  /></td>
                    <td >实训效果:</td><td><input name="effect" class="easyui-textbox"  /></td>
                </tr>
                <tr style="text-align: right;">
                    <td> 备注:</td><td><input name="info" class="easyui-textbox"  /></td>
                </tr>
            </table>
    </form>
</div>
<!--弹框底部按钮-->
<div id="train_dialog_btn">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="train_save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="train_cancel">取消</a>
</div>
</body>
</html>