<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>header</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="css/header.css">
	<link rel="shortcut icon" href="images/header/icon.jpg">
	<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
    <style>

		.ul {
		  font-size: 0;
		  position: relative;
		  padding: 0;
		  width: 800px;
		  margin: 0px auto;
		  -webkit-user-select: none;
		  -moz-user-select: none;
		  -ms-user-select: none;
		  user-select: none;
		}
		
		.li {
		  display: inline-block;
		  width: 160px;
		  height: 60px;
		  font-size: 16px;
		  text-align: center;
		  line-height: 60px;
		  color: #fff;
		  text-transform: uppercase;
		  position: relative;
		  overflow: hidden;
		  cursor: pointer;
		}
		
		.slider {
		  display: block;
		  position: absolute;
		  bottom: 0;
		  left: 0;
		  height: 4px;
		  background: #0000EE;
		  -webkit-transition: all 0.5s;
		  transition: all 0.5s;
		}
		
		/*  Ripple */
		
		
		.ripple {
		  width: 0;
		  height: 0;
		  border-radius: 50%;
		  background: rgba(255, 255, 255, 0.4);
		  -webkit-transform: scale(0);
		  -ms-transform: scale(0);
		  transform: scale(0);
		  position: absolute;
		  opacity: 1;
		}
		
		.rippleEffect {
		  -webkit-animation: rippleDrop .6s linear;
		  animation: rippleDrop .6s linear;
		}
		 @-webkit-keyframes 
		rippleDrop {  100% {
		 -webkit-transform: scale(2);
		 transform: scale(2);
		 opacity: 0;
		}
		}
		 @keyframes 
		rippleDrop {  100% {
		 -webkit-transform: scale(2);
		 transform: scale(2);
		 opacity: 0;
		}
		}
</style>
</head>
<body >
<!-- style="background-color: #8B008B" -->
<div >
<div >
	<div >
		<ul id="ul" class="ul">
			<li id="welcome" class="li">首页</li>
			<li id="mood" class="li">心情</li>
			<li id="aticle" class="li">日志</li>
			<li class="li">留言</li>
			<li class="li">个人资料</li>
			<li class="slider li"></li>
		</ul>
	</div>
</div>
<div style="font-size: 42px;font-family:Microsoft YaHei; text-align: center; position: absolute; top:12px;left:40px;color: #fff" >

<img src="images/header/icon.jpg" alt="" style=" width: 42px; height: 42px; border: 1px solid transparent; -webkit-border-radius: 100%; -moz-border-radius: 100%; -ms-border-radius: 100%; -o-border-radius: 100%; border-radius: 100%; float: left; margin-right: 10px;margin-top: -1px">
 HOME</div>
 </div>
<script>
	$(function(){
		
		var locate='${locate}';
		if(locate == null ){
			locate="welcome";
			
		}
		var whatTab = $("#"+locate).index();
		var howFar = 160 * whatTab;

		$(".slider").css({
			left: howFar + "px"
		});
		
	})
	$("ul li").click(function (e) {
	
		var id =this.id;
		
		// make sure we cannot click the slider
		if ($(this).hasClass('slider')) {
			return;
		}

		/* Add the slider movement */

		// what tab was pressed
		var whatTab = $(this).index();

		// Work out how far the slider needs to go
		var howFar = 160 * whatTab;

		$(".slider").css({
			left: howFar + "px"
		});

		/* Add the ripple */

		// Remove olds ones
		$(".ripple").remove();

		// Setup
	      var posX = $(this).offset().left,
		  posY = $(this).offset().top,
		  buttonWidth = $(this).width(),
		  buttonHeight = $(this).height();

		// Add the element
		$(this).prepend("<span class='ripple'></span>");

		// Make it round!
		if (buttonWidth >= buttonHeight) {
			buttonHeight = buttonWidth;
		} else {
			buttonWidth = buttonHeight;
		}

		// Get the center of the element
		var x = e.pageX - posX - buttonWidth / 2;
		var y = e.pageY - posY - buttonHeight / 2;

		// Add the ripples CSS and start the animation
		$(".ripple").css({
			width: buttonWidth,
			height: buttonHeight,
			top: y + 'px',
			left: x + 'px'
		}).addClass("rippleEffect");
		
		setTimeout(function(){
			
			window.location.href=id+".do";

			
		},1000);
		
	});
</script>
</body>
</html>