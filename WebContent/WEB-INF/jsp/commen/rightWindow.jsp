<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>header</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="css/flotingwindow.css">
	<link rel="shortcut icon" href="images/header/icon.jpg">
	<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="js/right-window.js"></script>

</head>
<body >
<div class="right-window">
	<ul>
    	<li class="right-tel" style="right: -91px;"><i></i><em>主人电话<br>13051701098</em></li>
        <li class="right-qq" style="right: -91px;"><i></i><em>主人QQ<br>450290467</em></li>
        <li class="right-wechat"><i></i><span>微信公众号</span></li> 
        <!-- <li class="right-add-mood" style="right: -91px;"><i></i><em><span style="color:#fff" onclick="openAdd();">发心情</span></em></li> -->
         <li class="right-top"><a href="javascript:scroll(0,0)"><i></i><span>返回顶部</span></a></li>
    </ul>
    <div class="right-wechat-code" style="display: none;">
    	<img src="images/commen/wechat.jpg" width="105" height="105">
        <p>扫一扫，关注我</p>
    </div>
 <!--    <div class="right-form" style="display: none;">
    	<h2><span>意见反馈</span><em></em></h2>
    	<form action="" method="post" id="right-windows-form">
    	<input type="hidden" name="action" value="Sendmsg">
        <input type="text" class="right-text" name="name" placeholder="您的姓名">
        <input type="text" class="right-text" name="tel" placeholder="手机号码">
        <textarea class="right-textarea" name="yijian" placeholder="请写下您宝贵的意见"></textarea>
        <input class="right-button" type="submit" value="确认提交">
    	</form>
    </div> -->
</div>
</body>
</html>