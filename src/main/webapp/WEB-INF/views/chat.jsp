<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>我要咨询</title>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="/static/index/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/static/index/css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" href="/static/index/css/matrix-style2.css" />
	<link rel="stylesheet" href="/static/index/css/matrix-media.css" />
	<link href="/static/index/font-awesome/css/font-awesome.css" rel="stylesheet" />
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
</head>
<body>

<div id="content">
	<div id="content-header">
		<h1>咨询大厅</h1>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box widget-chat">
					<div class="widget-title"> <span class="icon"> <i class="icon-comment"></i> </span>
						<h5>Let's do a chat</h5>
					</div>
					<div class="widget-content nopadding">
						<div class="chat-users panel-right2">
							<div class="panel-title">
								<h5>在线用户</h5>
							</div>
							<div class="panel-content nopadding">
								<ul class="contact-list">
									<li id="user-Alex" class="online"><a href="#"><img alt="" src="/static/index/img/demo/av1.jpg" /> <span>王校长</span></a></li>
									<li id="user-Linda"><a href="#"><img alt="" src="/static/index/img/demo/av2.jpg" /> <span>李老师</span></a></li>
									<li id="user-John" class="online new"><a href="#"><img alt="" src="/static/index/img/demo/av3.jpg" /> <span>小明</span></a><span class="msg-count badge badge-info">3</span></li>
									<li id="user-Mark" class="online"><a href="#"><img alt="" src="/static/index/img/demo/av4.jpg" /> <span>老王</span></a></li>
									<li id="user-Maxi" class="online"><a href="#"><img alt="" src="/static/index/img/demo/av5.jpg" /> <span>张老师</span></a></li>
								</ul>
							</div>
						</div>
						<div class="chat-content panel-left2">
							<div class="chat-messages" id="chat-messages">
								<div id="chat-messages-inner"></div>
							</div>
							<div class="chat-message well">
								<button  class="btn btn-success">发送</button>
								<span class="input-box"><input type="text" name="msg-box" id="msg-box" /></span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="http://www.jq22.com/jquery/jquery-1.7.1.js"></script>
<script src="/static/index/js/jquery.ui.custom.js"></script>
<script src="/static/index/js/bootstrap.min.js"></script>
<script src="/static/index/js/matrix.js"></script>
<script src="/static/index/js/matrix.chat.js"></script>
</body>
</html>
