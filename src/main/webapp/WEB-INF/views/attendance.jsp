<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>考勤管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/attendance.js"></script>
</head>
<body>

<table id="attendance_dataGrid">
    <div id="tb">
        <input id="searchNameBtn" type="text">
        <input id="searchstartBtn" type="text">
        <input id="searchendBtn" type="text">
        <select id="searchStateBtn" type="text">
            <option value="-1" selected="selected">--请选择--</option>
            <option value="0">正常</option>
            <option value="1">迟到</option>
            <option value="2">休息</option>
            <option value="3">请假</option>
        </select>
    </div>
</table>

</body>
</html>