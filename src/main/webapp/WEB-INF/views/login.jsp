<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>

  <title>校管家信息管理系统</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- CSS -->
  <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
  <link rel="stylesheet" href="/static/login/assets/css/reset.css">
  <link rel="stylesheet" href="/static/login/assets/css/supersized.css">
  <link rel="stylesheet" href="/static/login/assets/css/style.css">

</head>
</head>
<body>

<div class="page-container">
  <h1>校管家信息管理系统</h1>
  <form id="loginForm"  method="post">
    <span style="color:red" id="error"></span>
    <input type="text" name="username" class="username" placeholder="Username">
    <input type="password" name="password" class="password" placeholder="Password">
    <button id="loginBtn" type="submit">登陆</button>
    <div class="error">
      <span>

      </span>
    </div>
  </form>
  <div class="connect">
    <p>
      <a class="twitter" href=""></a>
    </p>
    <p>
      小码哥JAVA十六期
    </p>
  </div>
</div><br>
<div align="center">版权所有 &copy; 如有雷同存属巧合 2015-2018</div>

<!-- Javascript -->
<script src="/static/login/assets/js/jquery-1.8.2.min.js"></script>
<script src="/static/login/assets/js/supersized.3.2.7.min.js"></script>
<script src="/static/login/assets/js/supersized-init.js"></script>
<script src="/static/login/assets/js/scripts.js"></script>
<script type="text/javascript" src="/static/js/jquery-easyui/jquery.min.js"></script><!-- 补丁包 -->
<script type="text/javascript" src="/static/js/jquery-easyui/jquery.easyui.min.js"></script><!-- EaysUI核心包 -->
<script type="text/javascript" src="/static/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script><!-- 中文包 -->
<script type="text/javascript" src="/static/js/jquery-easyui/base.js"></script><!-- 补丁包 -->
<shiro:authenticated>
  <script type="text/javascript">
      window.location.href = "/main";
  </script>
</shiro:authenticated>
<script type="text/javascript">
    $(function(){
        $("#loginBtn").click(function(){
            $("#loginForm").form("submit",{
                url : "/login",
                success : function(data) {
                    console.log(data);
                    data = $.parseJSON(data);
                    if(data.success){
                        window.location.href = "/main"
                    }else{
                        $("#error").text(data.msg)
                    }
                }

            });
        })
    })
</script>

</body>
</html>