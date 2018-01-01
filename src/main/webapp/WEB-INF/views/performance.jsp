<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/10
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>销售业绩报表</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/plugins/dialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/static/js/plugins/dialog/iframeTools.js"></script>
    <script type="text/javascript" src="/static/js/views/performance.js"></script>
</head>
<body>
<table id="sale_datagrid"></table>
<form id="sale_form" method="post">
    <div id="tb">
        <div>
            <input id="beginTime" name="beginTime"> </input>~
            <input id="endTime" name="endTime"> </input>
            <input id="sale_textbox" name="keyword">
            <a id="search_btn">查询</a>
        </div>
        <div style="margin-top: 20px">
            <a id="report_btn">查看报表</a>
        </div>
    </div>
</form>
</body>
</html>
