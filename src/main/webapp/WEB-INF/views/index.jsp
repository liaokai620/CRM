<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Matrix Admin</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/static/index/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/static/index/css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="/static/index/css/fullcalendar.css" />
    <link rel="stylesheet" href="/static/index/css/jquery.gritter.css" />
    <link rel="stylesheet" href="/static/index/css/matrix-style2.css" />
    <link rel="stylesheet" href="/static/index/css/matrix-media.css" />
    <link href="/static/index/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
</head>
<body>

<div id="content">
    <!--图标区-->
    <div  class="quick-actions_homepage">
        <ul class="quick-actions">
            <li class="bg_lg"> <a href="/studentFollow"> <i class="icon-desktop"></i> 前台业务</a> </li>
            <li class="bg_lo"> <a href="/latenStudent"> <i class="icon-group"></i>招生 </a> </li>
            <li class="bg_lv"> <a href="/dayJob"> <i class="icon-credit-card"></i>待办事项</a> </li>
            <li class="bg_ly"> <a href="/signIn"> <i class="icon-legal"></i>签到打卡</a> </li>
            <li class="bg_lg"> <a href="/course"> <i class="icon-file-alt"></i>教务管理</a> </li>
            <li class="bg_ls"> <a href="/salary"> <i class="icon-money"></i> 财务管理</a> </li>
            <li class="bg_lv"> <a href="/undeveloped"> <i class="icon-comments"></i>师生信</a> </li>
            <li class="bg_lg"> <a href="/expenseReport"> <i class="icon-bar-chart"></i> 报表中心</a> </li>
            <li class="bg_ls"> <a href="/salary"> <i class="icon-print"></i>日常办公 </a> </li>
            <li class="bg_lb"> <a href="/chat"> <i class="icon-comments"></i>聊天室</a> </li>
            <li class="bg_lr"> <a href="/undeveloped"> <i class="icon-cloud"></i> 我的网盘</a> </li>
            <li class="bg_lv"> <a href="/undeveloped"> <i class="icon-cogs"></i> 设置</a> </li>
        </ul>
    </div>

    <%--轮播图片--%>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="carousel slide" id="carousel-627809">
                    <ol class="carousel-indicators">
                        <li class="active" data-slide-to="0" data-target="#carousel-627809">
                        </li>
                        <li data-slide-to="1" data-target="#carousel-627809">
                        </li>
                        <li data-slide-to="2" data-target="#carousel-627809">
                        </li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="item active">
                            <img alt="" src="/static/index/img/1.jpg" />
                        </div>
                        <div class="item">
                            <img alt="" src="/static/index/img/2.jpg" />
                        </div>
                        <div class="item">
                            <img alt="" src="/static/index/img/3.jpg" />
                        </div>
                    </div> <a data-slide="prev" href="#carousel-627809" class="left carousel-control">‹</a> <a data-slide="next" href="#carousel-627809" class="right carousel-control">›</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/static/index/js/excanvas.min.js"></script>
<script src="http://www.jq22.com/jquery/jquery-1.7.1.js"></script>
<script src="/static/index/js/jquery.ui.custom.js"></script>
<script src="/static/index/js/bootstrap.min.js"></script>
<script src="/static/index/js/jquery.flot.min.js"></script>
<script src="/static/index/js/jquery.flot.resize.min.js"></script>
<script src="/static/index/js/jquery.peity.min.js"></script>
<script src="/static/index/js/jquery.gritter.min.js"></script>
<script src="/static/index/js/matrix.js"></script>
<script src="/static/index/js/fullcalendar.min.js"></script>
<script src="/static/index/js/matrix.calendar.js"></script>
<script src="/static/index/js/matrix.chat.js"></script>
<script src="/static/index/js/jquery.validate.js"></script>
<script src="/static/index/js/matrix.form_validation.js"></script>
<script src="/static/index/js/jquery.wizard.js"></script>
<script src="/static/index/js/jquery.uniform.js"></script>
<script src="/static/index/js/select2.min.js"></script>
<script src="/static/index/js/matrix.popover.js"></script>
<script src="/static/index/js/jquery.dataTables.min.js"></script>
<script src="/static/index/js/matrix.tables.js"></script>
</body>
</html>
