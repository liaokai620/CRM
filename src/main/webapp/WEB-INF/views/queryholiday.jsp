<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>请假条</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/queryholiday.js"></script>
    <script type="text/javascript" src="/static/js/plu/print.js"></script>
</head>
<body>

<table id="queryholiday_dataGrid">
</table>

<!-- 定义顶部按钮 -->
<div id="tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a id="employee_editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a id="employee_quitBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="quit">审核</a>
        <a class="easyui-linkbutton" iconCls="icon-print" plain="true" data-cmd="print">打印</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="out">导出Excel表</a>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="download">下载模板</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        <form id="upload" method="post"  enctype="multipart/form-data">
            <input id="upload_input" name="file" class="easyui-filebox" label="导入数据:"
                   labelPosition="left" data-options="prompt:'选择文件',buttonText:'选择文件'"
                   style="width:25%">
            <a class="easyui-linkbutton" width="20" data-cmd="upload">上传</a>
        </form>
    </div>
    <div>
        <input id="searchnameBtn" type="text">
        <input id="searchstartBtn" type="text">
        <input id="searchendBtn" type="text">
        状态:<select id="searchStateBtn" type="text">
        <option value="-1" selected="selected">--请选择--</option>
        <option value="0">已确认</option>
        <option value="1">未确认</option>
    </select>
    </div>
</div>
<!-- 定义对话框 -->
<div id="queryholiday_dialog">
    <form id="queryholiday_form" method="post">
        <input type="hidden" name="id">
        <div align="center" style="margin-top: 10px;">
            <div>
                <select name="employee.id" class="easyui-combobox"
                        data-options="
						 width:150,
						 label:'申请人:',
						 labelPosition:'top',
						 valueField:'id',
						 textField:'realname',
						 url:'/employee/selectListForEmployeeForm'
						">
                </select>
            </div>
            <div>
                <input type="text" name="starttime" class="easyui-datetimebox"
                       data-options="label:'起始时间:',labelPosition:'top', width:150">
            </div>
            <div>
                <input type="text" name="endtime" class="easyui-datetimebox"
                       data-options="label:'结束时间:',labelPosition:'top', width:150">
            </div>
            <div>
                <select type="text" name="querytype" class="easyui-combobox"
                        data-options="label:'请假类型:',labelPosition:'top', width:150">
                    <option value="1">病假</option>
                    <option value="2">事假</option>
                </select>
            </div>
            <div>
                <input type="text" name="queryresult" class="easyui-textbox"
                       data-options="label:'请假原因:',labelPosition:'top', width:250,height:250">
            </div>
        </div>
    </form>
</div>
<!-- 对话框底部按钮 -->
<div id="bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>