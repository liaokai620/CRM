<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>员工管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/history.js"></script>
</head>
<body>
<table id="historyDatagrid"></table>
<!-- 对话框底部按钮 -->
<div id="historyToolbar">
    <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="reload">刷新</a>
    <a class="easyui-linkbutton"  iconCls="icon-ok"  plain="true" data-cmd="showAll">显示全部</a>
    <input class="easyui-searchbox" id="keyword"  name="keyword" searcher="searcher" prompt="姓名/电话/QQ" />
</div>
</body>
</html>