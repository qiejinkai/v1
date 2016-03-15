	
	
	$(function(){ 
		
		loadMood();
		
	});
	
	/**
	 * 加载最新心情
	 */
	function loadMood(){
		
		$.post('loadMood.do', 
				{}, 
				function(data) {
					$("#moodId").val(data.objectId);
					$("#moodTitle").text(data.title);
					$("#moodContent").html(data.content);
					$("#moodCreateTime").text(new Date(data.createTime*1000).pattern("yyyy-MM-dd HH:mm:ss"));
					$("#moodCounter").text(data.attributes['favor']);
					$("#moodCreateLocate").text(data.location);
					$("#moodFavor").click(function(){
						doMoodFavor();
					});
				}, 
				'json'
			);
	}
	
	var moodFavorFlag=false;
	/**
	 * 心情 点赞
	 */
	function doMoodFavor(){
		if(moodFavorFlag){
			return;
		}
		
		moodFavorFlag=true;
		
		var moodId=$("#moodId").val();
		if(0 === moodId){
			return;
		}
		$.post('doMoodFavor.do', 
				{objectId:moodId}, 
				function(data) {
					
					$("#moodCounter").text(data.attributes['favor']);	
					
					moodFavorFlag=false;
				}, 
				'json'
			);
	}
	
