	var moodnum = 0;
	var pageSize = 5;
	var offSet = 0;
	var moreFlag = true;
	var editor;
    KindEditor.ready(function(K) {
		var options = {
    	cssPath : 'js/kindeditor-4.1.10/plugins/code/prettify.css',
		uploadJson : 'kindEditorFileUpload.do',
		fileManagerJson : 'kidnEditorFileManager.do',
		allowFileManager : true,
		width : '450px',
		height : '100px',
		items : ['emoticons','|', 'image'],
		syncType : 'form',
		afterBlur: function () { this.sync(); }
		};
		editor = K.create('#editor',options);
	
	});
   	
	
	$(function(){
		loadMoodList();
		$("#addMoodbody").hide();
		$("#addMoodinfo").hide();	
		$("#moreMsg").click(function(){
			loadMoodList();
		});
		var addMoodForm =$("#addMoodForm");
		
		addMoodForm.submit(function(){
            var AjaxURL= "addMood.do"; 
                $.ajax({
                    type: "POST",
                    dataType: "text",
                    url: AjaxURL ,
                    data: $('#addMoodForm').serialize(),
                    success: function (data) {
                    	
                        if(data == "success"){
                        	
                        	alert("发表成功");
                        	
                        	setTimeout(function(){
                        		
                        		window.location.href="mood.do";
                        		
                        	},1000);
                        	
                        	return ;
                        }else{
                        	
                        	alert(data);
                        	
                        	return ;
                        }
                    },
                    error: function(data) {
                        alert("error:"+data.responseText);
                     }
                });
        }
		);
	
	});

	/*
		<div  class="mood_info_body">
			<div class="mood_info_host" >
				<img alt="" src="images/commen/host.jpg" style=" width: 50px; height: 50px; border: 1px solid transparent; -webkit-border-radius: 100%; -moz-border-radius: 100%; -ms-border-radius: 100%; -o-border-radius: 100%; border-radius: 100%; float: left; margin-left: 18px;margin-top: 20px">
			</div>
			<div class="mood_info_main">
				<div class="arr" ></div>
				<div class="title_div">
					<span id="title" class="title_span"> 主人今天萌萌哒</span>
					<div class="title_top_div">
					<span id="top" class="title_top_span"><strong>[置顶]</strong></span>
					</div>
				</div>
				<div class="content_div">
					<span id="content" class="content_span">你知道我在想你吗</span>
				</div>
				<div class="info_div">
					<img title="日期" class="info_time_img"
			 						src="images/commen/time_ico.png" >
					<span id="info" class="info_time_span">2015-11-22 22:22:22</span>
					<img title="地点" class="info_locate_img"
			 						src="images/commen/locate.png" >
			 		<span id="locate" class="info_time_span">北京·昌平</span>
			 		<img title="赞一下" class="info_favor_img" id="moodFavor"
			 						src="images/commen/skim_before.png" 
			 						onmouseover="this.src='images/commen/skim_after.png';this.style.cursor='pointer';" 
			 						onmouseout="this.src='images/commen/skim_before.png';this.style.cursor='defalut';"/>
			 					<input type="hidden" id="moodCounter"/>
			 					<span class="info_favor_span" id="moodCounter" >0</span>
				</div>
			</div>		
		</div>
	 */
	function loadMoodList(){
		
		if(!moreFlag){
			return;
		}
		
		$.post('loadMoodList.do', 
				{offSet:offSet,pageSize:pageSize}, 
				function(data) {
					if(data.length === 0 && moodnum === 0){
						alert("i m here");
						$("#noneMood").show();
						$("#more").hide();
					}else{
						$("#noneMood").hide();
						$("#more").show();
					}
					if(data.length < pageSize){
						$("#moreMsg").text("主人没那么多心情了。。");
						moreFlag=false;
					}
					for(var i=1;i<=data.length;i++ ){
						var mood=data[i-1];
						moodnum += i;
						var div=makeMood(mood);
						
						$("#moods_body").append(div);
						
					}
					
					offSet+=data.length;
					
					var heigth = 170*offSet+130;
					
					$("#moods_body").css("height",heigth+"px");
				}, 
				'json'
			);
	}
	
	function makeMood(mood){
		var msg="<div  class='mood_info_body'><div class='mood_info_host' ><img  src='images/commen/host.jpg' style=' width: 50px; height: 50px; border: 1px solid transparent; -webkit-border-radius: 100%; -moz-border-radius: 100%; -ms-border-radius: 100%; -o-border-radius: 100%; border-radius: 100%; float: left; margin-left: 18px;margin-top: 20px'></div>";		
		msg+="<div class='mood_info_main'>";
		msg+="<div class='arr' ></div>";
		msg+="<div class='title_div'>";
		var state=mood.moodState;
		var lock="";
		if(state !=0){
			lock="<img class='lock' id='lockMood_"+mood.objectId+"' src='images/commen/lock.png' onclick='lockMood("+mood.objectId+");' />";
		}else{

			lock="<img class='lock' id='lockMood_"+mood.objectId+"' src='images/commen/unlock.png' onclick='lockMood("+mood.objectId+");' />";
		}
		msg+="<span class='title_span'>"+lock+mood.title+"</span>";
		msg+="<div class='title_top_div'>";
		var sequence = mood.sequence;
		if(sequence !=0){
			msg+="<span class='title_top_span' style='color:red' id='stickyMood_"+mood.objectId+"' onclick='stickyMood("+mood.objectId+");'>[置顶]</span>&nbsp;&nbsp;&nbsp;";
		}else{
			msg+="<span class='title_top_span' style='color:#fff' id='stickyMood_"+mood.objectId+"' onclick='stickyMood("+mood.objectId+");'>[置顶]</span>&nbsp;&nbsp;&nbsp;";
		}
		msg+="<span class='title_top_span' style='color:#fff;' id='deleteMood_"+mood.objectId+"' onclick='deleteMood("+mood.objectId+");'>删除</span>";
		msg+="</div>";
		msg+="</div>";
		msg+="<div class='content_div'>";
		msg+="<span  class='content_span'>"+mood.content+"</span>";
		msg+="</div>";
		msg+="<div class='info_div'>";
		msg+="<img title='日期' class='info_time_img'";
		msg+="src='images/commen/time_ico.png' >";
		msg+="<span  class='info_time_span'>"+new Date(mood.createTime*1000).pattern("yyyy-MM-dd HH:mm:ss")+"</span>";
		msg+="<img title='地点' class='info_locate_img'";
		msg+="src='images/commen/locate.png' >";
		msg+="<span class='info_time_span'>"+mood.location+"</span>";
		msg+="<img title='赞一下' class='info_favor_img' id ='favor_"+mood.objectId+"' ";
		msg+="src='images/commen/skim_before.png' ";
		msg+="onmouseover='changeImg("+mood.objectId+");' ";
		msg+="onclick='doMoodFavor("+mood.objectId+");' ";
		msg+="onmouseout='changeImg("+mood.objectId+");' "; 
		msg+=" />";
		msg+="<input type='hidden' id='mood_"+mood.objectId+"' value='"+mood.objectId+"'/>";
		msg+="<span class='info_favor_span' id='moodCounter_"+mood.objectId+"' >"+mood.attributes['favor']+"</span>";
		msg+="</div>";
		msg+="</div>";	
		msg+="</div>";
		return msg;
	}
	var moodFavorFlag=false;
	
	function doMoodFavor(id){
		if(moodFavorFlag){
			return;
		}
		
		moodFavorFlag=true;
		
		var moodId=id;
		if(0 === moodId){
			return;
		}
		$.post('doMoodFavor.do', 
				{objectId:moodId}, 
				function(data) {
					
					$("#moodCounter_"+id).text(data.attributes['favor']);	
					moodFavorFlag=false;
				}, 
				'json'
			);
	}
	
	function changeImg(id){
		
		var currImg=$("#favor_"+id).attr("src");
		
		if(currImg == "images/commen/skim_before.png"){
			$("#favor_"+id).attr("src","images/commen/skim_after.png");
		}else{
			$("#favor_"+id).attr("src","images/commen/skim_before.png");	
		}
		
	}
	
	function addMood(){
		$.post('addMood.do', 
				{objectId:moodId}, 
				function(data) {
					
					$("#moodCounter_"+id).text(data.attributes['favor']);	
					moodFavorFlag=false;
				}, 
				'json'
			);
	}
	
	function reloadSequence(){
		
		if($("#sequence").val() == 0){
			$("#sequence").val(1);
			$("#addMoodSequence").css("border","1px solid red").css("color","red");
			
			$("#addMoodSequence").mouseover(function(){
				$("#addMoodSequence").css("border","1px solid red").css("color","red");
			});
			$("#addMoodSequence").mouseout(function(){
				$("#addMoodSequence").css("border","1px solid red").css("color","red");
			});
		}else{

			$("#sequence").val(0);
			$("#addMoodSequence").css("border","1px solid #fff").css("color","#fff");
			
			$("#addMoodSequence").mouseover(function(){
				$("#addMoodSequence").css("border","1px solid blue").css("color","blue");
			});
			$("#addMoodSequence").mouseout(function(){
				$("#addMoodSequence").css("border","1px solid #fff").css("color","#fff");
			});
		}
		
	}
	
	function closeAdd(){
		$("#addMoodbody").hide();
		$("#addMoodinfo").hide(2000);	
	}
	function openAdd(){
		$("#addMoodbody").show();
		$("#addMoodinfo").show(2000);	
	}
	
	function lockMood(id){

		$.post('lockMood.do', 
				{objectId:id}, 
				function(data) {

					if(data == "1"){
						$("#lockMood_"+id).attr("src","images/commen/lock.png");
						
					}else if(data == "0"){
						
						$("#lockMood_"+id).attr("src","images/commen/unlock.png");
					}else{
						
						alert(data);
					}
				}, 
				'json'
			);
	}
	
	function deleteMood(id){
		$.post('deleteMood.do', 
				{objectId:id}, 
				function(data) {
					if(data == "success"){
						
						window.location.href ="mood.do";
					}else{
						
						alert(data);
					}
					
				}
			);
	}
	
	function stickyMood(id){
		$.post('stickyMood.do', 
				{objectId:id}, 
				function(data) {
					if(data == "1"){
						$("#stickyMood_"+id).css("color","red");
						
					}else if(data == "0"){
						
						$("#stickyMood_"+id).css("color","#fff");
					}else{
						
						alert(data);
					}
				}, 
				'json'
			);
	}
	
	
	