$(function(){
	var rightw= $('.right-window');
	$('.right-tel,.right-qq',rightw).hover(function(){
		
		$(this).addClass('show-li').animate({right:"0px"},200);
		
		});
		$('.right-tel,.right-qq',rightw).bind("mouseleave",function(){
		
		$(this).removeClass('show-li').animate({right:"-91px"},200);
		
		});
	$('.right-wechat',rightw).hover(function(){
		$('.right-wechat-code',rightw).show();
		$('.right-form',rightw).hide();
		});
		$('.right-wechat',rightw).mouseleave(function(){
		$('.right-wechat-code',rightw).hide();
		
		});
	$('.right-msg',rightw).hover(function(){
		$('.right-form',rightw).show();
		$('.right-wechat-code',rightw).hide();
	});
	$('.right-form h2 em',rightw).click(function(){
		$('.right-form',rightw).hide();
		
		});
	
	var rightform = $('#right-windows-form');
	$('.right-button',rightform).bind('click',function(){
		var name = $('.right-text[name=name]',rightform).val();
		var tel = $('.right-text[name=tel]',rightform).val();
		var yijian = $('.right-textarea[name=yijian]',rightform).val();
		 var patrn = /^1\d{10}|^0\d{2,3}-?\d{7,8}$/;
		 
		
		if(name=='' || name == null){
			alert('请输入姓名');
			return false;
		} 
		//if(tel=='' || tel == null){
		if(!patrn.test(tel)){
			alert('请输入电话');
			return false;
		}
		if(yijian=='' || yijian == null){
			alert('请输入意见');
			return false;
		}
		alert('意见反馈成功，感谢您的参与。');
		
	});
	});