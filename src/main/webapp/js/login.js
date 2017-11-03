$(function(){
	//获取屏幕高度
	$('.wrap').height($(window).height());
	
	//让登录框居中
	$('.login').css('left',($(window).width() - $('.login').width())/2);
	$('.login').css('top',($(window).height() - $('.login').height())/2);
	
	//自适应窗口大小
	$(window).resize(function(){	
		$('.wrap').height($(window).height());
		$('.login').css('left',($(window).width() - $('.login').width())/2);
		$('.login').css('top',($(window).height() - $('.login').height())/2);
	});
	
});
	

	
