<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common.jsp"%>
<script type="text/javascript" src="/static/js/views/pool.js"></script>
<html>
<head>
    <title>潜在学生管理</title>
</head>
<body>

    <div id="poolDatagrid"></div>
    <div id="datagrid_toolbar">

        <a class="easyui-linkbutton" plain="true" iconCls="icon-reload" data-cmd="reload" >刷新</a>
        <a class="easyui-linkbutton" plain="true"  iconCls="icon-remove" data-cmd="moveTo" >移交</a>
        <input class="easyui-searchbox" data-cmd="dosearch" data-options="searcher:dosearch,prompt:'电话/QQ/姓名'"/>
    </div>

    <div id="moveDialog">
        <form id="moveForm" method="post">
            <input type="hidden" name="qq"/>
            <input type="hidden" name="id"/>
            <input type="hidden" name="name"/>
            <input type="hidden" name="tel"/>
            <div>
                <input class="easyui-combobox" name="targetId" data-options="label:'移交目标',labelAlign:'right',
                width:280,textField:'username',valueField:'id',url:'/employee/selectAllConsulting'">
            </div>
            <div>
                <input class="easyui-textbox" name="reason"
                       data-options="multiline:true,label:'移交原因',labelAlign:'right',width:450,height:100"/>
            </div>
        </form>
    </div>

    <div id="moveButtons">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-cmd="move">移交</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-cmd="moveCancel" >取消</a>
    </div>
</body>
</html>
