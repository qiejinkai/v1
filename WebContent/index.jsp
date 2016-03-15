<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome</title>
<link rel="shortcut icon" href="images/header/icon.jpg">
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<style type="text/css">
	wellcome{
	max-width: 100%;
	max-height: 100%;
	height: 100%;
	
	}
	div{
	width:500px;
	height:75px;
	color:gray;
	transform:rotate(315deg);
	-ms-transform:rotate(315deg); /* IE 9 */
	-moz-transform:rotate(315deg); /* Firefox */
	-webkit-transform:rotate(315deg); /* Safari and Chrome */
	-o-transform:rotate(315deg); /* Opera */
	position:absolute;
	top:115px;
	left:-50px;
	}

</style>
</head>
<body>
<img src="images/welcome.jpg" style="max-width: 100%;max-height: 100%;width: 100%"></img>
<script type="text/javascript">
	function changecolor(obj,color){
		var div=$(obj);
		div.css("color",color);
	}
	function changemouse(obj){
		var div=$(obj);
		div.css("cursor","pointer");
	}
</script>
<div class="div" onmouseover="changecolor(this,'blue');changemouse(this);" onmouseout="changecolor(this,'gray');" onclick="location.href='welcome.do'"><h1 >Welcome to qiejinkai's home</h1></div>
</body>
</html>