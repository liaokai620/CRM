<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改密码</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/static/js/views/password.js"></script>
</head>
<body>
<center>
    <form id="passwordForm" method="post">
        <div style="margin-top:100px;">
            <center><input class="easyui-passwordbox" data-options="onChange:checkPassword,label:'请输入旧密码',labelWidth:120,width:350,height:40 "
                           data-pwd="<shiro:principal property="password"/>"></center>
                <span id="error" style="color:red"></span>
        </div>
        <div style="margin-top:50px;">
            <input class="easyui-passwordbox" name="password" data-options="label:'请输入新密码',labelWidth:120,width:350,height:40">
        </div>
        <div style="margin-top:50px;">
            <input class="easyui-passwordbox" data-options="label:'    确认密码',labelWidth:120,width:350,height:40 ">
        </div>

        <div style="margin-top:50px;margin-left:80px;">
                <input class="easyui-numberbox" name="phone" data-options="label:'请输入手机号',labelWidth:135,width:370,height:40 ">
            <a id="getVerify" data-options="width:100,height:40">获取验证码</a>
        </div><span id="info" style="color:green"></span>

        <div  style="margin-top:50px;margin-right:50px">
                <input  id="varifyCode" data-options="label:'请输入语音验证码',labelWidth:150,width:380,height:40 ">
        </div>
        <span id="errorMsg" style="color:red"></span>


        <div style="margin-top:40px;">
            <a id="submit">修改密码</a>
        </div>
    </form>
</center>
</body>
</html>