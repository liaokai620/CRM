<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>数据字典目录管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/systemDictionary.js"></script>
</head>
<body>
<div id="systemDictionary_layout" class="easyui-layout" fit="true" style="width:600px;height:400px;">
    <div data-options="region:'west',title:'字典目录'" style="width:50%;">
        <table id="systemDictionary_datagrid"></table>
    </div>
    <div data-options="region:'center',title:'字典目录明细'" style="padding:5px;background:#eee;">
        <table id="item_datagrid"></table>
    </div>
</div>
<div id="tb">
    <div>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
    </div>
    <div>
        <input id="dic_textbox" name="keyword">
    </div>
</div>
<div id="item_btn">
    <a id="add_btn" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="addItem">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="editItem">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-cmd="removeItem">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloadItem">刷新</a>
</div>

<!--dialog-->
<div id="systemDictionary_dialog">
    <div>
        <form id="systemDictionary_form" method="post">
            <input type="hidden" name="id">
            <div align="center" style="margin-top: 10px">
                <input name="sn" class="easyui-textbox" data-options="label:'编号:',labelWidth:40">
            </div>
            <div align="center" style="margin-top: 10px">
                <input name="name" class="easyui-textbox" data-options="label:'名称:',labelWidth:40">
            </div>
            <div align="center" style="margin-top: 10px">
                <input name="intro" class="easyui-textbox" data-options="label:'简介:',labelWidth:40">
            </div>
        </form>
    </div>
</div>
<%--item的dialog--%>
<div id="item_dialog">
    <div>
        <form id="item_form" method="post">
            <input id="itemId" type="hidden" name="id">
            <input id="parentId" type="hidden" name="parent.id">
            <div align="center" style="margin-top: 10px">
                <input id="parentName" class="easyui-textbox" readonly data-options="label:'字典目录:',labelWidth:80,width:200">
            </div>
            <div align="center" style="margin-top: 10px">
                <input name="name" class="easyui-textbox" data-options="label:'字典明细名称:',labelWidth:80,width:200">
            </div>
            <div align="center" style="margin-top: 10px">
                <input name="intro" class="easyui-textbox" data-options="label:'字典明细简介:',labelWidth:80,width:200">
            </div>
        </form>
    </div>
</div>

<!--提交&取消按钮-->
<div id="bt">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">提交</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<!--提交&取消按钮-->
<div id="item_bt">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="saveItem">提交</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancelItem">取消</a>
</div>
</body>
</html>