<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome</title>
<link rel="shortcut icon" href="images/header/icon.jpg">
<link href="css/bannar/bannar_1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="js/date/DateFormat.js" charset="utf-8"></script>
<script type="text/javascript" src="js/welcome.js" lang="zh-Cn"></script>

</head>
<body>
<div style="position:fixed; width:100%; height:100%; z-index:-1">  
<img src="images/welcome/bg1.jpg" height="100%" width="100%" style="min-width: 100%;min-height: 100%" />  
</div>
<%@ include file="commen/header.jsp"%>
<%@ include file="commen/rightWindow.jsp"%>
	<table style="width: 100%;height: 50%">
		<tr>
			<td>
			    <div class="bannar_infos" style="width:300px;height: 400px">
			        <div class="bannar_project_infos" style="width:300px;height: 400px">
			 			<h1>心情</h1>
			 			<div style="width:252px;height: 255px;border:1px solid #fff;margin:15px;" >
			 				<div style="width:252px;height: 20px;margin:15px;">
			 					<span style="color:#fff;font-size:15px" id="moodTitle"></span>
			 				</div>
			 				<div style="width:200px;height: 130px;border:0px solid #fff;margin-top:25px;margin-left: 27px;line-height:130px;text-align: center;filter:glow(color=red,strength=10)">			 				
			 					<span style="color:#00FFFF;font-size:12px;margin: 10px;text-shadow: 5px 5px 5px #fff ;display:table-cell; "
			 						 id="moodContent">加载中。。。</span>
			 				</div>
			 				<div style="width:230px;height: 15px;text-align: right;margin: 5px">
			 				<img title="日期" style="width: 12px;"
			 						src="images/commen/time_ico.png" >
			 					<span style="width:252px;height: 15px;color:#00FFFF;font-size:7px;" id="moodCreateTime"></span>
			 				</div>
			 				<div style="width:230px;height: 15px;text-align:right; margin: 5px; line-height:15px" >
			 				
			 					<img title="地点" style="width: 14px;"
			 						src="images/commen/locate.png" >
			 					<span style="width:230px;height: 15px;color:#00FFFF;font-size:7px;" id="moodCreateLocate">北京·昌平</span>
			 					<img title="赞一下" style="width: 12px;" id="moodFavor"
			 						src="images/commen/skim_before.png" 
			 						onmouseover="this.src='images/commen/skim_after.png';this.style.cursor='pointer';" 
			 						onmouseout="this.src='images/commen/skim_before.png';this.style.cursor='defalut';"/>
			 					<input type="hidden" id="moodId"/>
			 					<span style="width:230px;height: 13px;color:#00FFFF;font-size:7px;" id="moodCounter" >0</span>
			 				
			 				</div>
			 				
			 			</div>
			 			<div style="position:relative; right: 11px;top:-12px; text-align: right;font-weight:bold;">
			 				<span style="font-size: 12px;color: gray" 
			 				onmouseover="javascript:this.style.color='blue';this.style.cursor='pointer';" 
			 				onmouseout="javascript:this.style.color='gray';this.style.cursor='default';"
			 				onclick="window.location.href='mood.do'"
			 				>
			 				MORE
			 				</span>
			 			</div>
			 			
			        </div>
			    </div>
			<td>
			<td>
			    <div class="bannar_infos" style="width:500px;height: 400px">
			        <div class="bannar_project_infos" style="width:500px;height: 400px">
			 		<h1>日志</h1>
			 			<div style="width:452px;height: 255px;border:1px solid #fff;margin:15px;" >
			 				
			 			</div>
			 			<div style="position:relative; right: 11px;top:-12px; text-align: right;font-weight:bold;">
			 				<span style="font-size: 12px;color: gray" 
			 				onmouseover="javascript:this.style.color='blue';this.style.cursor='pointer';" 
			 				onmouseout="javascript:this.style.color='gray';this.style.cursor='default';">
			 				MORE
			 				</span>
			 			</div>
			        </div>
			    </div>
			
			<td>
			<td>
				    <div class="bannar_infos" style="width:300px;height: 400px">
			        <div class="bannar_project_infos" style="width:300px;height: 400px">
			 		<h1>推荐</h1>
			 			<div style="width:252px;height: 255px;border:1px solid #fff;margin:15px;" >
			 				
			 			</div>
			 			<div style="position:relative; right: 11px;top:-12px; text-align: right;font-weight:bold;">
			 				<span style="font-size: 12px;color: gray" 
			 				onmouseover="javascript:this.style.color='blue';this.style.cursor='pointer';" 
			 				onmouseout="javascript:this.style.color='gray';this.style.cursor='default';">
			 				MORE
			 				</span>
			 			</div>
			        </div>
			    </div>
			   
			<td>
		</tr>
	</table>
	<table>
		<tr>
			<td style="width: 10%">
			<td>
		
			<td style="width: 25%">
			    <div class="bannar_infos" style="width:300px;height: 150px">
			        <div class="bannar_project_infos" style="width:300px;height: 150px">
			        <table>
			        	<tr>
			        		<td>
			        			<h1>公告</h1>
			        		</td>
			        		<td>
			        			<div style="width:152px;height: 100px;border:1px solid #fff;margin:15px;text-align: center;vertical-align: middle;" >
			 						<span style="font-size: 12px;color:white;position:relative ;top:45%">v1版本，敬请期待</span>
			 						
			 					</div>
			        		</td>
			        	</tr>
			        </table>
			        </div>
			    </div>
			<td>
			<td style="width: 55%">
			 <div class="bannar_infos" style="width:600px;height: 150px">
			        <div class="bannar_project_infos" style="width:600px;height: 150px">
				        <table>
			        	<tr>
			        		<td>
			        			<h1>留言</h1>
			        		</td>
			        		<td colspan="2">
			        			<div style="width:450px;height: 100px;border:1px solid #fff;margin:15px;" >
			 				
			 					</div>
			 					<div style="position:relative; right: 11px;top:-12px; text-align: right;font-weight:bold;">
					 				<span style="font-size: 12px;color: gray" 
					 				onmouseover="javascript:this.style.color='blue';this.style.cursor='pointer';" 
					 				onmouseout="javascript:this.style.color='gray';this.style.cursor='default';">
					 				MORE
					 				</span>
					 			</div>
			        		</td>
			        	</tr>
			        </table>
			        </div>
			    </div>
			<td>
			
			<td style="width: 10%">
			<td>
		</tr>
	</table>




</body>
</html>