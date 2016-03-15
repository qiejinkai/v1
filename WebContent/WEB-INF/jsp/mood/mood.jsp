<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>心情</title>
<link rel="shortcut icon" href="images/header/icon.jpg">
<link href="css/mood.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="js/date/DateFormat.js" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" src="js/mood/mood.js"></script>

</head>
<body>
<div style="position:fixed; width:100%; height:100%; z-index:-1">  
<img src="images/welcome/bg2.jpg" height="100%" width="100%" style="min-width: 100%;min-height: 100%" />  
</div>
<%@ include file="../commen/header.jsp"%>
<%@ include file="../commen/rightWindow.jsp"%>

	<div id="moods_body" style="width: 60%;margin-left: 20%;margin-right: 20%; margin-top:40px; border : 0px solid #fff;height: 250px;min-width: 700px; background: rgba(0,0,0,0.5);" >
		<div style="position: relative; top:20px;left:20px;right:20px">
			<h1 style="color:#fff">主人的心情</h1>
			<div style="cursor: pointer;position:absolute ; right: 40px;bottom: -10px ">
			<span style="color:#fff" onclick="openAdd();">发心情</span>
		</div>
		</div>
		
		<br/>
		<hr style="height:1px;border:none;border-top:1px solid #555555;width: 95%" />
		<div style="font-size: 30px;text-align: center;margin-top: 20px"  id="noneMood">
			<span style="color:#fff">主人居然没有心情...</span>
		</div>

	</div>
	<div  style="width: 60%;margin-left: 20%;margin-right: 20%; border : 0px solid #fff;height:50px;min-width: 700px; background: rgba(0,0,0,0.5);" >
		<div style="font-size: 30px;text-align: center;"  id="more">
			<span style="color:#fff;cursor: pointer;" id="moreMsg" onmouseover="javascript:this.style.color='#00FFFF';"onmouseout="javascript:this.style.color='#fff';" >查看更多</span>
		</div>
	</div>
	
	<div id="addMoodbody" class="mask" ></div>
	<div id="addMoodinfo" style="background-color:rgba(255, 255, 255, 0.06); border:0px solid #fff;position: fixed; width:500px;left: 50%;margin-left: -250px;z-index: 2;top: 90px; height: 465px;color: #fff;">
        <form action="#" method="post" id="addMoodForm">
           <h3 class="add_mood_body_title">&nbsp;&nbsp;&nbsp;心情</h3>
           <div style="padding: 20px">
            <div >
                标题&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="text" class="text" pattern="^.{1,10}$"
                	 required name="title" placeholder="请输入标题，10位以内"/>
            	
            </div>
            <br/>
            <br/>
            <div >
                心情&nbsp;&nbsp;&nbsp;&nbsp;<select name="moodType" required>
	            	<option value="0" >无聊</option>
	            	<option value="1">开森</option>
	            	<option value="2">伤森</option>
	            	<option value="3">生气</option>
	            	<option value="4">失望</option>
            	</select>
            </div>
            <br/>
            <br/>
            <div>
             <textarea rows="1" cols="5" class="text" required="required" maxlength="200"
             	 style="width: 334px;height: 100px;" id="editor" name="content" placeholder="请输入心情，200位以内" ></textarea>
            </div>
            
            <br/>
            <br/>
            <div class="bind_phone_item">
                地点&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="text" class="text" 
                	style="width: 177px;" required  name="location" placeholder="请输入地点" value="北京·昌平"/>
                <input type="hidden" value="0" id="sequence" name="sequence">
                <input type="hidden" value="0" id="moodState" name="moodState">
                <span id="addMoodSequence" title="设置置顶"
                	style="display: inline-block;margin-left: 120px;border: 1px solid #fff;padding-top:1px;padding-bottom: 1px;padding-left: 3px;padding-right: 3px;cursor: pointer;"  
                		onload="this.style.border='1px solid #fff';"
                		onmouseover="this.style.border='1px solid blue';this.style.color='blue';" 
                		onmouseout="this.style.border='1px solid #fff';this.style.color='#fff';"
                		onclick="reloadSequence();"
                	>置顶</span>
            </div>
            <br/>
            <br/>
            <div class="add_mood_commit">
                <input type="submit" value="发表" class="button" id="commitAdd">
            </div>
           </div> 
        </form>
        <a href="javascript:void(0)" class="close" title="关闭" onclick="closeAdd();"></a>
      
    </div>
</body>
</html>