<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/11
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支出报表</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/plugins/dialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/static/js/plugins/dialog/iframeTools.js"></script>
    <script type="text/javascript" src="/static/js/views/expenseReport.js"></script>
</head>
<body>
<form id="expense_form">
    <a class="easyui-linkbutton" data-options="plain:true">分组查询</a>
    <select id="expense_combobox" name="groupBy" style="width:200px;">
        <c:forEach items="${groupByMap}" var="item">
            <option value="${item.key}">${item.value}</option>
        </c:forEach>
    </select>
    <input id="beginTime" name="beginTime"> </input>~
    <input id="endTime" name="endTime"> </input>
    <a id="search_btn">查询</a>
    <a id="expense_linkbutton" plain="true">查看报表</a>
</form>
<table id="expense_datagrid"></table>
</body>
</html>
